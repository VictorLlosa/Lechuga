package model;

import model.Entidad.Balas.ListaBalas;
import model.Entidad.Enemigos.ListaEnemigos;
import model.Entidad.Naves.ListaNaves;
import model.StateCasilla.Casilla;
import model.Tipos.TipoEnemigo;
import model.Tipos.TipoEntidad;

import java.util.*;


public class Espacio extends Observable{

	private static Espacio miEspacio;
	private final int hDim = 200;
	private final int vDim = 120;

	private final Casilla[][] matriz;

	private Espacio() {
		matriz = new Casilla[hDim][vDim];
		for (int i = 0; i < hDim; i++) {
			for (int j = 0; j < vDim; j++) {
				matriz[i][j] = new Casilla();
			}
		}
		addObserver(ListaEnemigos.getListaEnemigos());
		addObserver(ListaNaves.getListaNaves());
		addObserver(ListaBalas.getListaBalas());
	}
	public static Espacio getEspacio() {
		if (miEspacio == null) {
			miEspacio = new Espacio();
		}
		return miEspacio;
	}

	public int getMaxEspaciado(int pNumEnt){
		return hDim / pNumEnt;
	}

	@SuppressWarnings("deprecation")
	public void asignarObserverCasilla(Observer o, int pX, int pY) {
		matriz[pX][pY].asignarObserver(o);
	}

	/**
	 * Comprueba si se sale del espacio o si va a colisionar
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean esCoordenadaValida(int x, int y) {
		return x > 0 && x < hDim && y >= 0 && y < vDim;
	}

	public boolean colision(int x, int y, TipoEntidad pEnt, int pIdEntidad) {
		ArrayList<ColisionEvent> evento = matriz[x][y].colision(pEnt, pIdEntidad);
		if(evento != null){
			setChanged();
			notifyObservers(evento);
			return true;
		}else return false;
	}


	public boolean enemigoGana() {
		return ListaEnemigos.getListaEnemigos().enemigoHaLLegadoAbajo();
	}

	/**
	 * para cuando se quiere colocar, no mover, una entidad en una posición
	 * Precondicion: el movimiento es valido
	 * @param actX
	 * @param actY
	 * @param pEnt
	 * @param pIdEntidad
	 * @return
	 */
	public void colocarEntidad(int actX, int actY, TipoEntidad pEnt, int pIdEntidad){
		matriz[actX][actY].ponerEntidad(pEnt, pIdEntidad);
	}

	/**
	 * Solo vacia la casilla (pone una Entidad de "vacio" en esa casilla) si en la casilla esta la misma entidad que quieres
	 * que desaparezca. P.ej: Si una bala esta debajo de una nave y la bala se mueve,
	 * al vaciar la posicion anterior de la bala, no se pone vacio, porque la casilla contiene nave (aunque haya
	 * una nave y bala a la vez)
	 */
	public void vaciarCasilla(int x, int y){
		matriz[x][y].vaciar();
	}


    public int getRanX() {
		return new Random().nextInt(10, hDim);
    }
}
