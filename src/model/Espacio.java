package model;

import java.awt.Color;
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

	public boolean esCoordenadaValida(int x, int y) {
		return x < this.hDim && y < this.vDim && x >= 0 && y >= 0;
	}

	public boolean puedeDisparar(int x, int y) {
		return x < this.hDim && y < this.vDim + 1 && x >= 0 && y >= 0;
	}

	/**
	 * Anadimos una nave a listaNaves
	 * @param pColor
	 * @param pCoord
	 */
	public void anadirNave(String pColor, Coordenada pCoord) {

		ListaNaves.getListaNaves().anadirNave(pColor, pCoord);
		matriz[55][50].cambiarObjeto("Nave");
	}

	/**
	 * Mueve SOLO EL CENTRO de la nave
	 * @param idNave
	 * @param dx debe ser valida (por un margen de 1)
	 * @param dy debe ser valida
	 */
	public void moverNave(int idNave, int dx, int dy) {
		if(!ListaNaves.getListaNaves().existeNave(idNave)) return;
		Coordenada coordNave = ListaNaves.getListaNaves().getCoordNave(idNave);

		int cX = coordNave.getX();
		int cY = coordNave.getY();

		int nx = cX + dx;
		int ny = cY + dy;
		if (esCoordenadaValida(nx, ny)) {
			if (!this.comprobarColEnemigoNave(idNave,coordNave)) { //si no hay colision, movemos la nave
				matriz[cX][cY].vaciar();
				matriz[nx][ny].cambiarObjeto("Nave");
				ListaNaves.getListaNaves().setCoordNave(idNave, nx, ny);
			}
		}
		else {
			if(nx > this.hDim - 1){
				matriz[cX][cY].vaciar();
				matriz[0][ny].cambiarObjeto("Nave");
				ListaNaves.getListaNaves().setCoordNave(idNave, 0, ny);
			}
			else if(nx<0){
				matriz[cX][cY].vaciar();
				matriz[hDim-1][ny].cambiarObjeto("Nave");
				ListaNaves.getListaNaves().setCoordNave(idNave, hDim-1, ny);
			}
		}

	}
	public void borrarNaves(){
		//los borraremos de la pantalla primero y despues de la lista
		for (int i=0;i < ListaNaves.getListaNaves().getNumNaves();i++){
			Coordenada coordNave = ListaNaves.getListaNaves().getCoordNave(i);
			matriz[coordNave.getX()][coordNave.getY()].vaciar();
		}
		ListaNaves.getListaNaves().borrarListaNaves();
	}

	public void disparar(int pIdNave){
		Coordenada coordNave =  ListaNaves.getListaNaves().getCoordNave(pIdNave);

		if (puedeDisparar(coordNave.getX(), coordNave.getY())) {
			Coordenada coordBala = ListaNaves.getListaNaves().disparar(pIdNave);
			matriz[coordBala.getX()][coordBala.getY()].cambiarObjeto("Bala");
		}
	}

	/**
	 * Primero vaciamos las casillas que tenian las balas y luego delegamos a la lista de balas de las naves el movimiento (que actualiza las coordenadas internamente)
	 * 	y finalmente dibujamos las balas en sus nuevas posiciones.
	 * 	Usamos getObjeto() de Casilla para saber que tiene en cada momento
	 */
	public void moverBalas() {
		int numNaves = ListaNaves.getListaNaves().getNumNaves();
		for (int i = 0; i < numNaves; i++) {
			for (Coordenada coordBala : ListaNaves.getListaNaves().getCoordBalasNave(i)){
				matriz[coordBala.getX()][coordBala.getY()].vaciar();
			}
			ListaNaves.getListaNaves().moverBalasNave(i);
			for (Coordenada coordBala : ListaNaves.getListaNaves().getCoordBalasNave(i)){
				String objAnt = matriz[coordBala.getX()][coordBala.getY()].getObjeto();
				if(objAnt.equals("enemigo")){
					ListaEnemigos.getListaEnemigos().eliminarEnemigo();
				}
				matriz[coordBala.getX()][coordBala.getY()].cambiarObjeto("Bala");
			}
		}

	}

	/**
	 * Borramos de la pantalla y despues de la lista
	 */
	public void borrarBalas(){

		int numNaves = ListaNaves.getListaNaves().getNumNaves();
		for (int i = 0; i < numNaves; i++) {
			for (Coordenada coordBala : ListaNaves.getListaNaves().getCoordBalasNave(i)) {
				matriz[coordBala.getX()][coordBala.getY()].vaciar();
			}
			ListaNaves.getListaNaves().borrarBalasNave(i);
		}

	}

	//Creación y movimiento de Enemigos
	public void anadirEnemigos(int idEnemigo, Coordenada coord) {
		if (esCoordenadaValida(coord.getX(), coord.getY())) {
			ListaEnemigos.getListaEnemigos().anadirEnemigo(idEnemigo, coord);
			matriz[coord.getX()][coord.getY()].cambiarObjeto("Enemigo");
		}
	}

	//TODO hay que cambiar este metodo por el cambio en colisiones
	public void moverEnemigos() {
		int num = ListaEnemigos.getListaEnemigos().getNumEnemigos();
		for (int i = 0; i < num; i++) {
			Coordenada coordEnem = ListaEnemigos.getListaEnemigos().getCoordEnemigos(i);
			matriz[coordEnem.getX()][coordEnem.getY()].vaciar();
		}

		// Mover los enemigos en la lista (actualiza coordenadas internamente)
		ListaEnemigos.getListaEnemigos().moverEnemigos();

		// Dibujar los enemigos en sus nuevas posiciones
		num = ListaEnemigos.getListaEnemigos().getNumEnemigos();
		for (int i = 0; i < num; i++) {
			Coordenada coordEnem = ListaEnemigos.getListaEnemigos().getCoordEnemigos(i);
			matriz[coordEnem.getX()][coordEnem.getY()].cambiarObjeto("Enemigo");
		}
	}
	public void borrarEnemigos(){
		//los borraremos de la pantalla primero y despues de la lista
		for (int i=0;i < ListaEnemigos.getListaEnemigos().getNumEnemigos();i++){
			Coordenada coordEnemigo = ListaEnemigos.getListaEnemigos().getCoordEnemigos(i);
			matriz[coordEnemigo.getX()][coordEnemigo.getY()].vaciar();
		}
		//ahora los borramos de la lista
		ListaEnemigos.getListaEnemigos().borrarListaEnemigos();
	}

	/*
	public void comprobarColisiones() {
		//Balas vs Enemigos
		comprobarColBalaEnemigo();
		//Enemigos vs Nave
		comprobarColEnemigoNave(0);
	}
	*/

	/**
	 * Recorremos toda la longitud de la lista de Naves y en cada iteracion, obtenemos la coordenada en la que esta la nave.
	 * Devuelve (true) si ha habido colision de alguno de los enemigos  con alguna de las Naves
	 * @param pId
	 * @param coordNave se la pasamos de parametro (usado en el metodo de MoverNave)
	 */

	private boolean comprobarColEnemigoNave(int pId, Coordenada coordNave) {
		for(int j = ListaEnemigos.getListaEnemigos().getNumEnemigos() - 1; j>=0; j--) {
			Coordenada coordEnem = ListaEnemigos.getListaEnemigos().getCoordEnemigos(j);
			if(coordNave.equals(coordEnem)) {
				matriz[coordNave.getX()][coordNave.getY()].vaciar();
				ListaNaves.getListaNaves().eliminarNave(pId);
				ListaEnemigos.getListaEnemigos().eliminarEnemigo(j);
				return true; // si la nave ya ha sido eliminada no hay que seguir
			}
		}
		return false;
	}


	private void comprobarColBalaEnemigo() {
		// Iterar de atrás hacia adelante para evitar problemas con índices al eliminar
		for (int i = ListaEnemigos.getListaEnemigos().getNumEnemigos() - 1; i >= 0; i--) {
			Coordenada coordEnem = ListaEnemigos.getListaEnemigos().getCoordEnemigos(i);

			for (int j = ListaBalas.getListaBalas().getNumBalas() - 1; j >= 0; j--) {
				Coordenada coordBala = ListaBalas.getListaBalas().getCoordBala(j);

				if (coordEnem.equals(coordBala) || coordEnem.debajo(coordBala)) {
					//Hay que tener en cuenta que si la bala y el enemigo estan en posiciones contiguas
					//y se mueven a la vez, la bala va a quedar arriba y el enemigo abajo.

					matriz[coordEnem.getX()][coordEnem.getY()].vaciar();
					matriz[coordBala.getX()][coordBala.getY()].vaciar();

					// Eliminar bala y enemigo
					ListaBalas.getListaBalas().eliminarBala(j);
					ListaEnemigos.getListaEnemigos().eliminarEnemigo(i);

					break; // Si el enemigo ya ha sido eliminado no hay que seguir
				}
			}
		}
	}

	public boolean quedanEnemigos() {
		return ListaEnemigos.getListaEnemigos().getNumEnemigos() > 0;
	}

	public boolean enemigoGana() {
		return ListaEnemigos.getListaEnemigos().enemigoHaLLegadoAbajo();
	}

	public boolean quedanNaves() {
		return ListaNaves.getListaNaves().getNumNaves() > 0;
	}


}