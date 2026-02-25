package viewController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observer;

import model.GestorPartida;

import javax.swing.*;

/**
 * Implementa el KeyListener para poder usar las teclas
 */
public class Controlador implements KeyListener {


	private static Controlador miControlador =null;

	// estado de teclas para movimiento continuo
	private volatile boolean upPressed = false;
	private volatile boolean downPressed = false;
	private volatile boolean leftPressed = false;
	private volatile boolean rightPressed = false;

	private Controlador() {
	}
	
	public static Controlador getControlador() {
		if(miControlador == null){
			miControlador = new Controlador();
		}
		return miControlador;
	}
	
	public void asignarObserverCasilla(Observer o, int pX, int pY) {
		GestorPartida.getGestorPartida().asignarObserverCasilla(o, pX, pY);
	}
	public void asignarObserverGestor(Observer o) {
		GestorPartida.getGestorPartida().asignarObserver(o);
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

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
			case  KeyEvent.VK_W:
				this.upPressed = true;
				GestorPartida.getGestorPartida().moverNave("w");
				break;
			case KeyEvent.VK_A:
				this.leftPressed = true;
				GestorPartida.getGestorPartida().moverNave("a");
				break;
			case KeyEvent.VK_S:
				this.downPressed = true;
				GestorPartida.getGestorPartida().moverNave("s");
				break;
			case KeyEvent.VK_D:
				this.rightPressed = true;
				GestorPartida.getGestorPartida().moverNave("d");
				break;
			case KeyEvent.VK_SPACE:
				//this.spacePressed = true;
				GestorPartida.getGestorPartida().startDisparar(0);
				break;
			default:

		}
	}

	public void procesarMovimiento() {
		if (upPressed) GestorPartida.getGestorPartida().moverNave("w");
		if (downPressed) GestorPartida.getGestorPartida().moverNave("a");
		if (leftPressed) GestorPartida.getGestorPartida().moverNave("s");
		if (rightPressed) GestorPartida.getGestorPartida().moverNave("d");
	}
	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()){
			case  KeyEvent.VK_W:
				this.upPressed = false;
				GestorPartida.getGestorPartida().moverNave("w");
				break;
			case KeyEvent.VK_A:
				this.leftPressed = false;
				GestorPartida.getGestorPartida().moverNave("a");
				break;
			case KeyEvent.VK_S:
				this.downPressed = false;
				GestorPartida.getGestorPartida().moverNave("s");
				break;
			case KeyEvent.VK_D:
				this.rightPressed = false;
				GestorPartida.getGestorPartida().moverNave("d");
				break;
			case KeyEvent.VK_SPACE:
				//this.spacePressed = false;
				GestorPartida.getGestorPartida().stopDisparar(0);
				break;
			default:

		}

	}
}
