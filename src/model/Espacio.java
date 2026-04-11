package model;

import model.Composite.CompositeCoordenada;
import model.Composite.Coordenada;
import model.Composite.Pixel;

import java.util.ArrayList;
import java.util.Observer;


public class Espacio {

	private static Espacio miEspacio;
	private final int hDim = 100;
	private final int vDim = 60;

	private final Casilla[][] matriz;

	private Espacio() {
		matriz = new Casilla[hDim][vDim];
		for (int i = 0; i < hDim; i++) {
			for (int j = 0; j < vDim; j++) {
				matriz[i][j] = new Casilla();
			}
		}
	}
	public static Espacio getEspacio() {
		if (miEspacio == null) {
			miEspacio = new Espacio();
		}
		return miEspacio;
	}

	public int getMaxEspaciado(int pNumEnem){
		return hDim / pNumEnem;
	}

	@SuppressWarnings("deprecation")
	public void asignarObserverCasilla(Observer o, int pX, int pY) {
		matriz[pX][pY].asignarObserver(o);
	}

	/**
	 * Tiene en cuenta tanto si es una coordenada como si son varias (composite), para ver si es/son valida(s),
	 * es decir, si se encuentran dentro de las dimensiones del espacio.
	 * @param pCoord
	 * @return
	 */
	public boolean esCoordenadaValida(Coordenada pCoord) {
		return (pCoord.estasEnIntervalo(0,hDim-1,0,vDim-1));
	}

	/**
	 * Es igual que el 'esCoordenadaValida', pero tambien comprueba que la coordenada del Espacio este vacia. Se usa para crear los enemigos, ya que
	 * necesitamos que no se solapen entre si. No es el mismo metodo que 'esCoordenadaValida' porque este se usa cuando se mueven los enemigos.
	 * @param pCoord
	 * @return
	 */
	public boolean esCoordValidaAlCrear(Coordenada pCoord){
		return (pCoord.estasEnIntervalo(0,hDim-1,0,vDim-1) && this.noHayColisionAlCrear(pCoord));
	}

	/**
	 * Lo usamos en esCoordenadaValida, que a su vez se usa en anadirEnemigos de
	 * @param pCoord
	 * @return devuelve true si No hay colisiones al crear, es decir: se puede crear el objeto nuevo que creemos en pCoord
	 */
	public boolean noHayColisionAlCrear(Coordenada pCoord){
		ArrayList<Pixel> pixeles = pCoord.getPixeles();
		for(Pixel p : pixeles){ //como Coordenada puede ser una lista de Pixeles o un Composite, tenemos que hacer este bucle
			if (matriz[p.getX()][p.getY()].getObjeto() != Entidad.vacio) return false;
		}
		return true;
	}

	public boolean puedeDisparar(int x, int y) {
		return x < this.hDim && y < this.vDim + 1 && x >= 0 && y >= 0;
	}

	/**
	 * Anadimos una nave a listaNaves. Hacemos un for con CompositeCoordenada.getChildren().  sacamos los pixeles de cada coordenada (que van a ser siempre
	 * 1 pixel por coordenada, aunque lo hemos hecho asi para que sea mas modular) y por cada pixel, hacemos un getx,gety y cambiamos el objeto en esa coordenada por
	 * una nave en la matriz de aqui.
	 * @param pTipo
	 * @param pCoord es un Pixel, el centro de la nave
	 */
	public void anadirNave(TipoNave pTipo, Pixel pCoord) {
		CompositeCoordenada comp = ListaNaves.getListaNaves().anadirNave(pTipo, pCoord);
		for(Coordenada coords : comp.getChildren()){
			ArrayList<Pixel> pixeles = coords.getPixeles();
			for(Pixel p : pixeles){
				matriz[p.getX()][p.getY()].cambiarObjeto(Entidad.nave);
			}
		}
	}

	/**
	 * Colocamos solo los píxeles que están dentro del espacio
	 * @param coordenada
	 * @param pEnt
	 */
	public void colocarEntidad(Coordenada coordenada, Entidad pEnt) {
		ArrayList<Pixel> pixeles = coordenada.getPixeles();
		for(Pixel p : pixeles){
			if(p.estasEnIntervalo(0, hDim - 1, 0, vDim -1)) {
				matriz[p.getX()][p.getY()].cambiarObjeto(pEnt);
			}
		}
	}

	/**
	 * Borramos de la pantalla las naves que siguen vivas. SI estan muertas ya no aparecen por lo que
	 * no hace fala eliminarla de casilla.
	 */
	public void borrarNaves(){
		//los borraremos de la pantalla primero y despues de la lista
		ArrayList<Integer> listaIds = ListaNaves.getListaNaves().getListaIds();
		for (int idNave : listaIds){
			CompositeCoordenada comp = ListaNaves.getListaNaves().getCoordNave(idNave);
			ArrayList<Coordenada> coords = comp.getChildren();
			for (Coordenada coord: coords) {
				ArrayList<Pixel> pixeles = coord.getPixeles();
				for(Pixel p: pixeles){
					matriz[p.getX()][p.getY()].vaciar();
				}
			}
		}
		ListaNaves.getListaNaves().borrarListaNaves();
	}

	/**
	 * Primero vaciamos las casillas que tenian las balas y luego delegamos a la lista de balas de las naves el movimiento (que actualiza las coordenadas internamente)
	 * 	y finalmente dibujamos las balas en sus nuevas posiciones.
	 * 	Usamos getObjeto() de Casilla para saber que tiene en cada momento.
	 * 	Ya tenemos la casilla a donde queremos mover. Hay que mirar directamente que hay en la casilla.
	 *
	 * 	Pedimos los ids de las naves que siguen vivas para iterar sobre sus balas y eliminarlas
	 */
	public void moverBalas() {
		//TODO: Hay que revisra/cambiar todo
		ArrayList<Integer> listaIdsNave = ListaNaves.getListaNaves().getListaIds();
		for (int idNave : listaIdsNave) {
			Coordenada coordBalas = ListaNaves.getListaNaves().getCoordBalasNave(idNave);
			ArrayList<Pixel> pixelesBalas = coordBalas.getPixeles();
			for (Pixel coordBala : pixelesBalas){
				matriz[coordBala.getX()][coordBala.getY()].vaciar();
			}

			ListaNaves.getListaNaves().moverBalasNave(idNave);

			coordBalas = ListaNaves.getListaNaves().getCoordBalasNave(idNave);
			pixelesBalas = coordBalas.getPixeles();
			for (Pixel coordBala : pixelesBalas){
				Casilla casillaNueva = matriz[coordBala.getX()][coordBala.getY()];
				Entidad objAnt = casillaNueva.getObjeto();
				switch (objAnt) {
				case Entidad.alien: // Si había un alien, lo eliminamos junto a la bala y vaciamos la casilla
					ListaEnemigos.getListaEnemigos().matarEnemigoEn(coordBala);
					ListaNaves.getListaNaves().eliminarBalaPorCoord(idNave, coordBala);
					casillaNueva.cambiarObjeto(Entidad.vacio
					);
					break;
				case Entidad.nave: // Decidimos que se vea la nave si hay una bala en su misma posición
					casillaNueva.cambiarObjeto(Entidad.nave);
					break;
				default: // Si había una bala o estaba vacío
					casillaNueva.cambiarObjeto(Entidad.bala);
					break;
				}

			}
		}

	}

	/**
	 * Borramos de la pantalla y despues de la lista
	 */
	public void borrarBalas(){
		//TODO: Hay que revisra/cambiar todo
		int numNaves = ListaNaves.getListaNaves().getNumNaves();
		for (int i = 0; i < numNaves; i++) { //el id es 0
			Coordenada coordBalas = ListaNaves.getListaNaves().getCoordBalasNave(i);
			ArrayList<Pixel> pixelesBalas = coordBalas.getPixeles();
			for (Pixel coordBala : pixelesBalas){
				matriz[coordBala.getX()][coordBala.getY()].vaciar();
			}
			ListaNaves.getListaNaves().borrarBalasNave(i);
		}

	}

	/**
	 * Comprueba si la posicion del enemigo es correcta antes de anadirlo. Le decimos a
	 * listaEnemigos que cree un enemigo con el centro que le demos
	 */
	public boolean anadirEnemigos(Pixel pCentro) {
		Coordenada coordEnem = ListaEnemigos.getListaEnemigos().anadirEnemigo(pCentro,"normal");
		if (coordEnem != null){ //Se ha podido crear
			ArrayList<Pixel> pixeles = coordEnem.getPixeles();
			for(Pixel p: pixeles){
				matriz[p.getX()][p.getY()].cambiarObjeto(Entidad.alien);
			}
			return true;
		}else return false;
	}

	/**
	 * Iteramos sobre los enemigos que siguen vivos
	 */
	public void moverEnemigos() {
		//TODO: REVISAR
		ArrayList<Integer> listaIdsEnem = ListaEnemigos.getListaEnemigos().getListaIds();
		for (int idEnem : listaIdsEnem) {
			Pixel coordEnem = ListaEnemigos.getListaEnemigos().getCoordEnemigo(idEnem);
			matriz[coordEnem.getX()][coordEnem.getY()].vaciar();
		}

		// Mover los enemigos en la lista (actualiza coordenadas internamente)
		ListaEnemigos.getListaEnemigos().moverEnemigos();

		// Dibujar los enemigos en sus nuevas posiciones
		listaIdsEnem = ListaEnemigos.getListaEnemigos().getListaIds();
		for (int idEnem : listaIdsEnem) {
			Pixel coordEnem = ListaEnemigos.getListaEnemigos().getCoordEnemigo(idEnem);//TODO: PIXEL NO
			Casilla casillaNueva = matriz[coordEnem.getX()][coordEnem.getY()];
			Entidad objAnt = casillaNueva.getObjeto();
			switch (objAnt){
				case Entidad.bala: //Si habia una bala eliminamos al enemigo, la bala y ponemos vacio en la casilla
					ListaEnemigos.getListaEnemigos().matarEnemigoEn(coordEnem);
					ListaNaves.getListaNaves().eliminarBalaPorCoord(coordEnem);
					casillaNueva.cambiarObjeto(Entidad.vacio);
				break;
				case Entidad.nave: //Si habia una nave, la eliminamos, pero el alien SE QUEDA (lo dejamos, no hay que repintarlo)
					ListaNaves.getListaNaves().matarNaveEn(coordEnem);
				break;
				default: //Si habia otro alien o estaba vacio
					casillaNueva.cambiarObjeto(Entidad.alien);
				break;
			}

		}
	}

	/**
	 * Si un enemigo y la nave chocan, el enemigo no debe morir (no debe desaparecer el id de la lista de id's)
	 */
	public void borrarEnemigos(){
		//los borraremos de la pantalla primero y despues de la lista
		ArrayList<Integer> listaIdsEnem = ListaEnemigos.getListaEnemigos().getListaIds();
		for (int idEnem : listaIdsEnem){
			Pixel coordEnemigo = ListaEnemigos.getListaEnemigos().getCoordEnemigo(idEnem);
			matriz[coordEnemigo.getX()][coordEnemigo.getY()].vaciar();
		}
		//ahora los borramos de la lista
		ListaEnemigos.getListaEnemigos().borrarListaEnemigos();
	}

	/**
	 * Vamos enemigo por enemigo de la listaEnemigos para ver si .estanMuertos()
	 * @return
	 */
	public boolean quedanEnemigos() {
		return ListaEnemigos.getListaEnemigos().quedanEnemigos();
	}

	public boolean enemigoGana() {
		return ListaEnemigos.getListaEnemigos().enemigoHaLLegadoAbajo();
	}

	/**
	 * Ve si todas las naves estan vivas; llama a listanaves.quedanNaves()
	 * @return
	 */
	public boolean quedanNaves() {
		return ListaNaves.getListaNaves().quedanNaves();
	}

	public void vaciarCasillas(Coordenada pCoord) {
		ArrayList<Pixel> pixeles = pCoord.getPixeles();
		for(Pixel p : pixeles){
			matriz[p.getX()][p.getY()].vaciar();
		}
	}

}
