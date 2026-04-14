package model.State;

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
	private EstadoCasilla estado;

	
	public Casilla() {
		entidad = TipoEntidad.vacio;
		idEntidad = -1;
	}

	public void asignarObserver(Observer o) {
		this.addObserver(o);
	}

	public void cambiarObjeto(TipoEntidad pObjeto) {
		// Notificar sólo si el color cambia (evitar repaints redundantes)
		if (pObjeto.equals(this.entidad)) return;
		entidad = pObjeto;

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

	/**
	 * Se usa cuando hay cualguier casilla cambia de estado
	 * @param estadoNuevo
	 */
	public void cambiarDeEstadoA(EstadoCasilla estadoNuevo){
		this.estado= estadoNuevo;
	}

	/**
	 * Notifica a los observers con el argumento (y tb setChanged()).
	 * @param arg es son dos tuplas [TipoEntidad.eltipo, pCasilla.getId()},{pEnt, pIdEntidad} ]
	 */
	public void notificarObservers (Object arg) {
		setChanged();
		notifyObservers(arg);
	}

	/**
	 * Metodoo principal del patron State
	 * @param pEnt
	 */
	public boolean ponerEntidad(TipoEntidad pEnt, int pIdEntidad){
		estado.ponerEntidad(this, pEnt, pIdEntidad);
		return true; //todo no se que devuelve
	}

	public void setIdEntidad(int pId){
		this.idEntidad = pId;
	}
	public int getId(){
		return idEntidad;
	}
}