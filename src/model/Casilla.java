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
		cambiarObjeto("vacio");
	}


	public String getObjeto() {
		return objeto;
	}
	public void update(Observable o, Object arg){
		objeto = (String)arg;
	}
}