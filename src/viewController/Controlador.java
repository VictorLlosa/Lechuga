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
	
	public void asignarObserver(Observer o, int pX, int pY) {
		GestorPartida.getGestorPartida().asignarObserver(o, pX, pY);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}

	public void moverNave(String tecla){
		GestorPartida.getGestorPartida().moverNave(tecla);
	}
	
	public void iniciarModelo() {
		GestorPartida.getGestorPartida();
	}

	public void iniciarPartida() {
		GestorPartida.getGestorPartida().iniciarPartida();
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
