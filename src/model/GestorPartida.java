package model;

import java.awt.Color;
import java.util.Observer;

public class GestorPartida {

	private static GestorPartida miGestorPartida;
	private Nave nave;
	
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
		this.nave = new Nave(Color.red, new Coordenada(55,50));
		Espacio.getEspacio().anadirNave(nave.getCentro(), nave);
	}

	public void asignarObserver(Observer o, int pX, int pY) {
		Espacio.getEspacio().asignarObserver(o,pX,pY);
	}

	public void moverNave(String tecla) {
		Coordenada nCoord = Espacio.getEspacio().moverNave(nave.getCoord(), nave.getColor(), tecla);
		if(nCoord != null){
			this.nave.setCoord(nCoord.getX(), nCoord.getY());
		}

	}
}
