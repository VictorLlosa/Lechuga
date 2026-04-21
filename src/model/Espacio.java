package model;

import model.Balas.ListaBalas;
import model.Enemigos.ListaEnemigos;
import model.Naves.ListaNaves;
import model.State.Casilla;
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
		for(ListaBalas lb : ListaNaves.getListaNaves().getListaBalas()){
			addObserver(lb);
		}//TODO: VER SI HAY FORMA MEJOR de hacerlo sin hacer u getter de listabalas
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
	 * Comprueba si se sale del espacio o si va a colisionar
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean esCoordenadaValida(int x, int y) {
		return x > 0 && x < hDim && y >= 0 && y < vDim;
	}

	public boolean colision(int x, int y, TipoEntidad pEnt, int pIdEntidad) {
		ColisionEvent evento = matriz[x][y].colision(pEnt, pIdEntidad);
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
	public void vaciarCasilla(int x, int y){
		matriz[x][y].vaciar();
	}

	public boolean abajo(int y) {
		return y >= vDim;
	}
}
