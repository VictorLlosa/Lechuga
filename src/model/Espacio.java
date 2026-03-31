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
		return hDim / pNumEnem;	}

	@SuppressWarnings("deprecation")
	public void asignarObserverCasilla(Observer o, int pX, int pY) {
		matriz[pX][pY].asignarObserver(o);
	}

	public boolean esCoordenadaValida(Coordenada pCoord) {
		return pCoord.estasEnIntervalo(0,hDim-1,0,vDim-1);
	}

	public boolean puedeDisparar(int x, int y) {
		return x < this.hDim && y < this.vDim + 1 && x >= 0 && y >= 0;
	}

	/**
	 * Anadimos una nave a listaNaves. Hacemos un for con anadirNave, que devuelve cada coordenada que compone la nave
	 * @param pColor
	 * @param pCoord
	 */
	public void anadirNave(String pColor, Pixel pCoord) {
		CompositeCoordenada coord = ListaNaves.getListaNaves().anadirNave(pColor, pCoord);
		for(Coordenada p : coord.getChildren()){
			if(p.esPixel()) matriz[p.getX()][p.getY()].cambiarObjeto(Entidad.nave);
		}
		
	}

	/**
	 * Mueve SOLO EL CENTRO de la nave
	 * @param idNave
	 * @param dx debe ser valida (por un margen de 1)
	 * @param dy debe ser valida
	 */
	public void moverNave(int idNave, int dx, int dy) {
		boolean movValido = true;

		if(!ListaNaves.getListaNaves().existeNave(idNave)) return;
		CompositeCoordenada coordNave = ListaNaves.getListaNaves().getCoordNave(idNave);
		ArrayList<Coordenada> pixels = coordNave.getChildren();

		for(Coordenada p : pixels) {
			if(p.esPixel()) {
				int cX = p.getX();
				int cY = p.getY();

				int nX = cX + dx;
				int nY = cY + dy;
				movValido = esCoordenadaValida(nX, nY);
				if (!movValido) break;
			}
		}
		for(Coordenada p : pixels) {
			if(p.esPixel()) {
				int cX = p.getX();
				int cY = p.getY();
				matriz[cX][cY].vaciar();
			}
		}
		if(movValido) {
			ListaNaves.getListaNaves().actualizarCoordNave(idNave, dx, dy);
			pixels = coordNave.getChildren();
			for(Coordenada p : pixels) {
				if(p.esPixel()) {
					Entidad objAnt = matriz[p.getX()][p.getY()].getObjeto();
					if (objAnt.equals(Entidad.alien)) {//si habia un enemigo eliminamos la nave
						ListaNaves.getListaNaves().matarNave(idNave);
					} else { // si no hay colision o hay una bala, movemos la nave
						matriz[p.getX()][p.getY()].cambiarObjeto(Entidad.nave);

					}
				}
			}
		}

	}

	/**
	 * Borramos de la pantalla las naves que siguen vivas. SI estan muertas ya no aparecen por lo que
	 * no hace fala eliminarla se casilla.
	 */
	public void borrarNaves(){
		//los borraremos de la pantalla primero y despues de la lista
		ArrayList<Integer> listaIds = ListaNaves.getListaNaves().getListaIds();
		for (int idNave : listaIds){
			CompositeCoordenada coordNave = ListaNaves.getListaNaves().getCoordNave(idNave);
			ArrayList<Coordenada> pixeles = coordNave.getChildren();
			for (Coordenada p: pixeles) {
				int cX = p.getX();
				int cY = p.getY();
				matriz[cX][cY].vaciar();
			}
		}
		ListaNaves.getListaNaves().borrarListaNaves();
	}

	/**
	 * Primero miramos si la casilla es una casilla vlida para disparar y en el caso de que sí, creamos una bala en esa posición.
	 * @param pIdNave
	 */
	public void disparar(int pIdNave) {
		Coordenada cannonNave = ListaNaves.getListaNaves().getCannonNave(pIdNave);

		if (puedeDisparar(cannonNave.getX(), cannonNave.getY())) {
			CompositeCoordenada coordBala = ListaNaves.getListaNaves().disparar(pIdNave);
			ArrayList<Coordenada> pixels = coordBala.getChildren();

			for (Coordenada p : pixels) {
				if (p.esPixel()) {
					Pixel pixel = (Pixel) p;
					if (esCoordenadaValida(pixel.getX(), pixel.getY())) {
						matriz[pixel.getX()][pixel.getY()].cambiarObjeto(Entidad.bala);
					}
				}
			}
		}
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
		ArrayList<Integer> listaIdsNave = ListaNaves.getListaNaves().getListaIds();
		for (int idNave : listaIdsNave) {
			for (Pixel coordBala : ListaNaves.getListaNaves().getCoordBalasNave(idNave)){
				matriz[coordBala.getX()][coordBala.getY()].vaciar();
			}

			ListaNaves.getListaNaves().moverBalasNave(idNave);

			for (Pixel coordBala : ListaNaves.getListaNaves().getCoordBalasNave(idNave)){
				Casilla casillaNueva = matriz[coordBala.getX()][coordBala.getY()];
				Entidad objAnt = casillaNueva.getObjeto();
				switch (objAnt) {
				case Entidad.alien: // Si había un alien, lo eliminamos junto a la bala y vaciamos la casilla
					ListaEnemigos.getListaEnemigos().matarEnemigoEn(coordBala.getX(), coordBala.getY());
					ListaNaves.getListaNaves().eliminarBalaPorCoord(idNave, coordBala.getX(), coordBala.getY());
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

		int numNaves = ListaNaves.getListaNaves().getNumNaves();
		for (int i = 0; i < numNaves; i++) { //el id es 0
			for (Pixel coordBala : ListaNaves.getListaNaves().getCoordBalasNave(i)) {
				matriz[coordBala.getX()][coordBala.getY()].vaciar();
			}
			ListaNaves.getListaNaves().borrarBalasNave(i);
		}

	}

	//Creación y movimiento de Enemigos
	public void anadirEnemigos(Pixel coord) {
		if (esCoordenadaValida(coord.getX(), coord.getY())) {
			ListaEnemigos.getListaEnemigos().anadirEnemigo(coord,"normal");
			matriz[coord.getX()][coord.getY()].cambiarObjeto(Entidad.alien);
		}
	}

	/**
	 * Iteramos sobre los enemigos que siguen vivos
	 */
	public void moverEnemigos() {
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
			Pixel coordEnem = ListaEnemigos.getListaEnemigos().getCoordEnemigo(idEnem);
			Casilla casillaNueva = matriz[coordEnem.getX()][coordEnem.getY()];
			Entidad objAnt = casillaNueva.getObjeto();
			switch (objAnt){
				case Entidad.bala: //Si habia una bala eliminamos al enemigo, la bala y ponemos vacio en la casilla
					ListaEnemigos.getListaEnemigos().matarEnemigoEn(coordEnem.getX(), coordEnem.getY());
					ListaNaves.getListaNaves().eliminarBalaPorCoord(coordEnem.getX(), coordEnem.getY());
					casillaNueva.cambiarObjeto(Entidad.vacio);
				break;
				case Entidad.nave: //Si habia una nave, la eliminamos, pero el alien SE QUEDA (lo dejamos, no hay que repintarlo)
					ListaNaves.getListaNaves().matarNaveEn(coordEnem.getX(), coordEnem.getY());
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

	public void vaciarCasillas(CompositeCoordenada coord) {
		//Hay que iterar sobre los pixeles de coord (getchilds) y hacer matriz[pixel.getx()][].vaciar()

	}
}
