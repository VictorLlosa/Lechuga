package model;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
/**
 * Es parte del espacio. La casilla es Observable de LabelCasilla.
 * Es Observer del objeto que esta representando. Desde Bala,Enemigo o nave tenemos un Eliminar, que le
 * avisa a esta clase de que esta vacia
 */
public class Casilla extends Observable implements Observer{
	private Entidad entidad;


	
	public Casilla() {
		entidad = Entidad.vacio;
	}

	public void asignarObserver(Observer o) {
		this.addObserver(o);
	}
	
	public void cambiarObjeto(Entidad pObjeto) {
		// Notificar sólo si el color cambia (evitar repaints redundantes)
		if (pObjeto.equals(this.entidad)) return;
		entidad = pObjeto;
		Object[] arg ={pObjeto};
		setChanged();
		notifyObservers(arg); //notifica al Observer que se ha cambiado el color
	}

	public void vaciar() {
		cambiarObjeto(Entidad.vacio);
	}


	public Entidad getObjeto() {
		return entidad;
	}
	public void update(Observable o, Object arg){
		entidad = (Entidad)arg;
	}
}