package model;

import model.Tipos.TipoEntidad;

import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
/**
 * Es parte del espacio. La casilla es Observable de LabelCasilla.
 * Es Observer del objeto que esta representando. Desde Bala,Enemigo o nave tenemos un Eliminar, que le
 * avisa a esta clase de que esta vacia
 */
public class Casilla extends Observable{
	private TipoEntidad entidad;
	private int idEntidad;


	
	public Casilla() {
		entidad = TipoEntidad.vacio;
	}

	public void asignarObserver(Observer o) {
		this.addObserver(o);
	}
	
	public void cambiarObjeto(TipoEntidad pObjeto, int pId) {
		// Notificar sólo si el color cambia (evitar repaints redundantes)
		if (pObjeto.equals(this.entidad)) return;
		entidad = pObjeto;
		idEntidad = pId;
		Object[] arg ={pObjeto};
		setChanged();
		notifyObservers(arg); //notifica al Observer que se ha cambiado el color
	}

	public void vaciar() {
		cambiarObjeto(TipoEntidad.vacio);
	}
	public TipoEntidad getEntidad() {
		return entidad;
	}

}