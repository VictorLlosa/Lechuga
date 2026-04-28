package model.StateCasilla;

import model.ColisionEvent;
import model.Tipos.TipoEntidad;

import java.util.ArrayList;
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

	public void cambiarObjeto(TipoEntidad pEnt, int pId) {
		// Notificar solo si el color cambia (evitar repaints redundantes)
		if (pEnt.equals(this.entidad) && pId == this.idEntidad) return;
		entidad = pEnt;
		idEntidad = pId;

        setChanged();
		notifyObservers(pEnt); //notifica al Observer que se ha cambiado el color
	}

	public void vaciar() {
		cambiarDeEstadoA(new EstadoCasillaVacia());
		cambiarObjeto(TipoEntidad.vacio,-1);
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
	 * @param pEnt
	 */
	public void ponerEntidad(TipoEntidad pEnt, int pIdEntidad){
		estado.ponerEntidad(this, pEnt, pIdEntidad);
	}

	public void setIdEntidad(int pId){
		this.idEntidad = pId;
	}
	public int getId(){
		return idEntidad;
	}

	public ArrayList<ColisionEvent> colision(TipoEntidad pEnt, int pIdEntidad) {
		if(this.idEntidad == pIdEntidad) return null;
		return estado.colision(this, pEnt, pIdEntidad);
	}
}