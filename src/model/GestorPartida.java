package model;

import java.awt.Color;
import java.util.Observer;

public class GestorPartida {

	private static GestorPartida miGestorPartida;

	
	private GestorPartida() {
		Espacio espacio = Espacio.getEspacio();
	}


	public static GestorPartida getGestorPartida() {
		if(miGestorPartida == null) {
			miGestorPartida = new GestorPartida();
		}
		return miGestorPartida;
	}

	public void iniciarPartida(){
		Nave nave = new Nave(Color.red);
		Espacio.getEspacio().añadirNave(nave.getCentro(), nave.getMatriz());
	}

	public void asignarObserver(Observer o, int pX, int pY) {
		Espacio.getEspacio().asignarObserver(o,pX,pY);
	}
}
