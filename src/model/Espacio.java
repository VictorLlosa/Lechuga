package model;

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
		matriz[55][50].cambiarObjeto(Entidad.nave);
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
		matriz[cX][cY].vaciar();

		int nX = cX + dx;
		int nY = cY + dy;
		if (esCoordenadaValida(nX, nY)) {
			Entidad objAnt = matriz[nX][nY].getObjeto();
			if (objAnt.equals(Entidad.alien)) {//si habia un enemigo eliminamos la nave
				ListaNaves.getListaNaves().matarNave(idNave);
			}else{ // si no hay colision o hay una bala, movemos la nave
				matriz[nX][nY].cambiarObjeto(Entidad.nave);
				ListaNaves.getListaNaves().setCoordNave(idNave, nX, nY);
			}
		}
		else {
			if(nX > this.hDim - 1){//si se ha salido por el borde que aparezca por el otro lado
				matriz[cX][cY].vaciar();
				matriz[0][nY].cambiarObjeto(Entidad.nave);
				ListaNaves.getListaNaves().setCoordNave(idNave, 0, nY);
			}
			else if(nX<0){
				matriz[cX][cY].vaciar();
				matriz[hDim-1][nY].cambiarObjeto(Entidad.nave);
				ListaNaves.getListaNaves().setCoordNave(idNave, hDim-1, nY);
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
			Coordenada coordNave = ListaNaves.getListaNaves().getCoordNave(idNave);
			matriz[coordNave.getX()][coordNave.getY()].vaciar();
		}
		ListaNaves.getListaNaves().borrarListaNaves();
	}

	/**
	 *
	 * @param pIdNave
	 */
	public void disparar(int pIdNave){
		Coordenada coordNave =  ListaNaves.getListaNaves().getCoordNave(pIdNave);

		if (puedeDisparar(coordNave.getX(), coordNave.getY())) {
			Coordenada coordBala = ListaNaves.getListaNaves().disparar(pIdNave);
			matriz[coordBala.getX()][coordBala.getY()].cambiarObjeto(Entidad.bala);
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
			for (Coordenada coordBala : ListaNaves.getListaNaves().getCoordBalasNave(idNave)){
				matriz[coordBala.getX()][coordBala.getY()].vaciar();
			}

			ListaNaves.getListaNaves().moverBalasNave(idNave);

			for (Coordenada coordBala : ListaNaves.getListaNaves().getCoordBalasNave(idNave)){
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
			for (Coordenada coordBala : ListaNaves.getListaNaves().getCoordBalasNave(i)) {
				matriz[coordBala.getX()][coordBala.getY()].vaciar();
			}
			ListaNaves.getListaNaves().borrarBalasNave(i);
		}

	}

	//Creación y movimiento de Enemigos
	public void anadirEnemigos(Coordenada coord) {
		if (esCoordenadaValida(coord.getX(), coord.getY())) {
			ListaEnemigos.getListaEnemigos().anadirEnemigo(coord);
			matriz[coord.getX()][coord.getY()].cambiarObjeto(Entidad.alien);
		}
	}

	/**
	 * Iteramos sobre los enemigos que siguen vivos
	 */
	public void moverEnemigos() {
		ArrayList<Integer> listaIdsEnem = ListaEnemigos.getListaEnemigos().getListaIds();
		for (int idEnem : listaIdsEnem) {
			Coordenada coordEnem = ListaEnemigos.getListaEnemigos().getCoordEnemigo(idEnem);
			matriz[coordEnem.getX()][coordEnem.getY()].vaciar();
		}

		// Mover los enemigos en la lista (actualiza coordenadas internamente)
		ListaEnemigos.getListaEnemigos().moverEnemigos();

		// Dibujar los enemigos en sus nuevas posiciones
		listaIdsEnem = ListaEnemigos.getListaEnemigos().getListaIds();
		for (int idEnem : listaIdsEnem) {
			Coordenada coordEnem = ListaEnemigos.getListaEnemigos().getCoordEnemigo(idEnem);
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
			Coordenada coordEnemigo = ListaEnemigos.getListaEnemigos().getCoordEnemigo(idEnem);
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
}
