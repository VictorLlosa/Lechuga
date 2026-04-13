package model;

import model.Composite.CompositeCoordenada;
import model.Composite.Coordenada;
import model.Composite.Pixel;
import model.Enemigos.ListaEnemigos;
import model.Naves.ListaNaves;
import model.Tipos.TipoEntidad;
import model.Tipos.TipoEnem;
import model.Tipos.TipoNave;

import java.util.*;


public class Espacio{

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
			if (matriz[p.getX()][p.getY()].getObjeto() != TipoEntidad.vacio) return false;
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
		CompositeCoordenada coordNave = ListaNaves.getListaNaves().anadirNave(pTipo, pCoord);
		colocarEntidad(coordNave, TipoEntidad.nave);
	}

	/**
	 * Colocamos solo los píxeles que están dentro del espacio
	 * @param coordenada
	 * @param pEnt
	 * @return true si se ha podido colocar la Entidad. Lo usamos en .disparar() de NaveAbstracta (y por tanto en todas las naves)
	 */
	public boolean colocarEntidad(Coordenada coordenada, TipoEntidad pEnt) {
		ArrayList<Pixel> pixeles = coordenada.getPixeles();
		boolean entero = true;
		for (Pixel p : pixeles) {
			if (p.estasEnIntervalo(0, hDim - 1, 0, vDim - 1)) {
				matriz[p.getX()][p.getY()].cambiarObjeto(pEnt);
			} else {
				entero = false;
			}
		}
		return entero;
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
	 * Todos los píxeles que conforman la Coordenada pertencen al mismo tipo de Entidad. mira que había en las casillas donde ahora hay una Entidada
	 * especifica (la que se le pasa como parametro)
	 * @param pCoord las coordenadas de la bala o la Entidad que le pasemos
	 * @return un hashSet de Entidad. Es un hashSet para que no se puedan repetir las referencias a los objetos (de ser varios) con los que ha colisionado
	 */
	private boolean colision(Coordenada pCoord, TipoEntidad pEnt){
		ArrayList<Pixel> pixeles = pCoord.getPixeles();
		for(Pixel p : pixeles){
			Casilla casilla = matriz[p.getX()][p.getY()];
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
		Coordenada coordEnem = ListaEnemigos.getListaEnemigos().anadirEnemigo(pCentro, TipoEnem.normal);
		if (coordEnem != null){ //Se ha podido crear
			ArrayList<Pixel> pixeles = coordEnem.getPixeles();
			for(Pixel p: pixeles){
				matriz[p.getX()][p.getY()].cambiarObjeto(TipoEntidad.alien);
			}
			return true;
		}else return false;
	}


	/**
	 * Si un enemigo y la nave chocan, el enemigo no debe morir (no debe desaparecer el id de la lista de id's)
	 */
	public void borrarEnemigos(){
		//los borraremos de la pantalla primero y despues de la lista
		Coordenada coords = ListaEnemigos.getListaEnemigos().getCoordAllEnemigos();
		ArrayList<Pixel> pixelesEnem = coords.getPixeles();
		for (Pixel p : pixelesEnem) {
			matriz[p.getX()][p.getY()].vaciar();
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

	public boolean moverEntidad(Coordenada pCoordAnt, Coordenada pCoordNueva, TipoEntidad pEnt){
		colision(pCoordNueva, pEnt);
	}

}
