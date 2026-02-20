package model;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class Casilla extends Observable{
	private boolean ocupado = false;
	private Color color;
	//private int x = 0;
	//private int y = 0;
	
	public Casilla(Observer o) {
		this.addObserver(o);
	}
	public Casilla(boolean pOcup, Color pColor, Observer o) {
		this.addObserver(o);
		ocupado = pOcup;
		color = pColor;	
	}
	
	public void cambiarColor(Color pColor) {
		color = pColor;
	}

}