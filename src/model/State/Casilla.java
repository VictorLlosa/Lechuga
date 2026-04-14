package model.State;

import model.EventoEntidad;
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
		estado = new EstadoCasillaVacia();
		idEntidad = -1;
	}

	public void asignarObserver(Observer o) {
		this.addObserver(o);
	}

	public void cambiarObjeto(TipoEntidad pEnt) {
		// Notificar solo si el color cambia (evitar repaints redundantes)
		if (pEnt.equals(this.entidad)) return;
		entidad = pEnt;

		EventoEntidad[] arg = {new EventoEntidad(pEnt)};
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
		return estado.ponerEntidad(this, pEnt, pIdEntidad);
	}

	public void setIdEntidad(int pId){
		this.idEntidad = pId;
	}
	public int getId(){
		return idEntidad;
	}
}