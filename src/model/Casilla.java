package model;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
/**
 * Es parte del espacio. La casilla nos sirve para saber de qué color pintar su jframe.
 */
public class Casilla extends Observable{
	private Color color;

	
	public Casilla() {
		color = null;
	}

	public void asignarObserver(Observer o) {
		this.addObserver(o);
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