package model;

import java.awt.Color;

public class GestorPartida {

	private static GestorPartida miGestorPartida;

	
	private GestorPartida() {
		Espacio espacio = Espacio.getEspacio();
		Nave nave = new Nave(Color.red);
		
		espacio.añadirNave(nave.getCentro(), nave.getMatriz(), nave.getColor());
		
	}


	public static GestorPartida getGestorPartida() {
		// TODO Auto-generated method stub
		if(miGestorPartida == null) {
			miGestorPartida = new GestorPartida();
		}
		return miGestorPartida;
	}

	
}
