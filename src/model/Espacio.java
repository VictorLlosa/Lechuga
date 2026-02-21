package model;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;


public class Espacio {

	private static Espacio miEspacio;
	private final int hDim = 60 ;
	private final int vDim = 100;

	
	private Casilla[][] matriz; //fila, columna
	
	private Espacio() {
		
		matriz = new Casilla[hDim][vDim];
		for(int i = 0; i<hDim; i++) {
			for(int j = 0; j<vDim; j++) {
				matriz[i][j] = new Casilla();
			}
		}
	}
	
	public void añadirNave(Coordenada centro, Color[][] nave) {
		int cX = centro.getX();
		int cY = centro.getY();

		matriz[55][50].cambiarColor(nave[cX][cY]);
		/* NAVE DE + DE 1 PIXEL
		for(int i = 55 - cX; i<= 55 + cX; i++) {
			for(int j = 50 - cY; j<= 50 + cY; j++) {
				matriz[i][j].cambiarColor(nave[55 - i][50 - j]);
			}
		}
		*/
	}

	@SuppressWarnings("deprecation")
	public void asignarObserver(Observer o, int pX, int pY) {
		matriz[pX][pY].asignarObserver(o);
	}
	
	public static Espacio getEspacio() {
		if(miEspacio == null) {
			miEspacio = new Espacio();
		}
		return miEspacio;
	}
}