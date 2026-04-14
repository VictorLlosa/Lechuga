package model;

import model.Balas.ListaBalas;
import model.Enemigos.ListaEnemigos;
import model.Naves.ListaNaves;
import model.State.Casilla;
import model.Tipos.TipoEntidad;

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
				asignarObserverCasilla(ListaEnemigos.getListaEnemigos(),i,j); //listaEnemigos
				asignarObserverCasilla(ListaNaves.getListaNaves(),i,j); //ListaNaves
				for(ListaBalas lb : ListaNaves.getListaNaves().getListaBalas()){
					asignarObserverCasilla(lb,i,j);
				}//TODO: VER SI HAY FORMA MEJOR de hacerlo sin hacer u getter de listabalas
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
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean esCoordenadaValida(int x, int y) {
		return x > 0 && x < hDim -1 && y > 0 && y < vDim -1;
	}


	public boolean enemigoGana() {
		return ListaEnemigos.getListaEnemigos().enemigoHaLLegadoAbajo();
	}

	/**
	 * Lo usa CompositeCoordenada y Pixel para moverse
	 * @param actX componente X hacia donde queremos mover el objeto
	 * @param actY componente Y hacia donde queremos mover el objeto
	 * @param antX componente X del objeto que llama al metodo (coord anterior)
	 * @param antY componente Y del objeto que llama al metodo (coord anterior)
	 * @param pEnt tipo de entidad del objeto a mover
	 * @param pEnt id de la entidad a mover
	 * @return
	 */
	public boolean moverEntidad(int antX, int antY,int actX, int actY, TipoEntidad pEnt, int pIdEntidad){
		boolean exito = matriz[actX][actY].ponerEntidad(pEnt, pIdEntidad);
		if(exito) matriz[antX][antY].vaciar();
		return exito;

	}
	public void vaciarCasilla(int x, int y){
		matriz[x][y].vaciar();
	}

}
