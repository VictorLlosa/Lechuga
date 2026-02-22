package viewController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

import model.GestorPartida;

public class Controlador implements ActionListener {

	private static final Controlador miControlador = new Controlador();

	private Controlador() {
	}
	
	public static Controlador getControlador() {
		return miControlador;
	}
	
	public void asignarObserverCasilla(Observer o, int pX, int pY) {
		GestorPartida.getGestorPartida().asignarObserverCasilla(o, pX, pY);
	}
	public void asignarObserverGestor(Observer o) {
		GestorPartida.getGestorPartida().asignarObserver(o);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}

	public void iniciarModelo() {
		GestorPartida.getGestorPartida();
	}

	public void iniciarPartida() {
		GestorPartida.getGestorPartida().iniciarPartida();
	}

	public void reiniciarPartida(){
		GestorPartida.getGestorPartida().reiniciarPartida();
	}

	public void startDisparar(int idNave) {
		GestorPartida.getGestorPartida().startDisparar(idNave);
	}
	public void stopDisparar(int idNave) {
		GestorPartida.getGestorPartida().stopDisparar(idNave);
	}

	public void startMover(String tecla) {
		GestorPartida.getGestorPartida().startMover(tecla);
	}

	public void stopMover(String tecla) {
		GestorPartida.getGestorPartida().stopMover(tecla);
	}
}
