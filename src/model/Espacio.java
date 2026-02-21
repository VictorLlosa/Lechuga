package model;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;


public class Espacio {

	private static Espacio miEspacio;
	private final int hDim = 100;
	private final int vDim = 60;

	
	private Casilla[][] matriz;
	
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
		
		for(int i = 55 - cX; i<= 55 + cX; i++) {
			for(int j = 50 - cY; i<= 50 + cY; j++) {
				matriz[i][j].cambiarColor(nave[55 - i][50 - j]);
			}
		}
		
		
		
		
	}
	
	@SuppressWarnings("deprecation")
	public void asignarObserver(Observer o, int pX, int pY) {
		matriz[pX][pY].addObserver(o);
	}
	
	public static Espacio getEspacio() {
		if(miEspacio == null) {
			miEspacio = new Espacio();
		}
		return miEspacio;
	}
}