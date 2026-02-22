package model;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class Casilla extends Observable{
	private boolean ocupado;
	private Color color;
	//private int x = 0;
	//private int y = 0;
	
	public Casilla() {
		ocupado = false;
		color = null;
	}

	public void asignarObserver(Observer o) {
		this.addObserver(o);
	}
	public Casilla(boolean pOcup, Color pColor, Observer o) {
		this.addObserver(o);
		ocupado = pOcup;
		color = pColor;	
	}
	
	public void cambiarColor(Color pColor) {
		// Notificar sólo si el color cambia (evitar repaints redundantes)
		if (pColor.equals(this.color)) return;
		color = pColor;
		Object[] arg ={pColor};
		setChanged();
		notifyObservers(arg); //notifica al Observer que se ha cambiado el color
	}

	public void vaciar() {
		cambiarColor(Color.BLACK);
	}

	public void dibujarNave(Color colorNave) {
		cambiarColor(colorNave);
	}
	public void dibujarBala() {
		cambiarColor(Color.WHITE);
	}
	public void dibujarEnemigo() {cambiarColor(Color.BLUE);}
}