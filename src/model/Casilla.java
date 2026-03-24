package model;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
/**
 * Es parte del espacio. La casilla nos sirve para saber de qué color pintar su jframe.
 */
public class Casilla extends Observable{
	private String objeto;

	
	public Casilla() {
		objeto = "vacio";
	}

	public void asignarObserver(Observer o) {
		this.addObserver(o);
	}
	
	public void cambiarObjeto(String pObjeto) {
		// Notificar sólo si el color cambia (evitar repaints redundantes)
		if (pObjeto.equals(this.objeto)) return;
		objeto = pObjeto;
		Object[] arg ={pObjeto};
		setChanged();
		notifyObservers(arg); //notifica al Observer que se ha cambiado el color
	}

	public void vaciar() {
		cambiarObjeto("Vacio");
	}
}