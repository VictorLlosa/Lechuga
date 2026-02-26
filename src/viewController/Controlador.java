package viewController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observer;

import model.GestorPartida;
import model.ListaNaves;

import javax.swing.*;

/**
 * Implementa el KeyListener para poder usar las teclas. De momento, la logica del disparo de la nave está implementada pensando
 * que sólo hay 1
 */
public class Controlador implements KeyListener {


	private static Controlador miControlador =null;
	private String pantallaActual;
	private Timer timer;

	// estado de teclas para movimiento continuo
	private volatile boolean upPressed = false;
	private volatile boolean downPressed = false;
	private volatile boolean leftPressed = false;
	private volatile boolean rightPressed = false;
	private volatile boolean spacePressed = false;

	private int contDisparo = 0; // contador para limitar velocidad de disparo
	private final int CADENCIA = 5; // ajustar para controlar cadencia de disparo (más bajo = más rápido)

	private Controlador() {
		timer = new Timer(40, e -> {
			procesarMovimiento();
			/**
			 * Si el atrib. disparoPressed es T y el numero de disparos en pantalla (contDisparo) es
			 * >= a la CADENCIA, dispararemos automaticamente.
			 */

			if (spacePressed) {
				contDisparo++;
				if (contDisparo >= CADENCIA) {
					GestorPartida.getGestorPartida().disparar();
					contDisparo = 0;
				}
			} else {
				contDisparo = CADENCIA; // permite disparar instantáneamente al pulsar
			}
		});
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

	/**
	 * Empezamos el timer aqui par que de tiempo a crearse la nave y podamos acceder a sus coordenadas en .disparar()
	 * @param pPantalla String que entra como parametro, que nos dice la pantalla
	 */
	public void setPantallaActual(String pPantalla){
		pantallaActual = pPantalla;
		if(pantallaActual.equals("Juego")) timer.start();
		else timer.stop();
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	/**
	 * Tratamos todas las acciones de las teclas. Desde SpaceInvaders le llamamos en su metodo 'update' de Observer, al metodo setPantallaActual,
	 * para cambiar el atributo de Controlador de "pantallaActual". Lo usamos de forma parecida a una maquina de estados, donde dependiendo de que
	 * estado (pantalla actual) tengamos activa, "activaremos o desactivaremos" una serie de teclas
	 */
	public void keyPressed(KeyEvent e) {
		switch (pantallaActual) {
			case("Inicio"):
				//ENTER
				if (e.getKeyCode() == KeyEvent.VK_ENTER) GestorPartida.getGestorPartida().iniciarPartida();
			break;
			case("Juego"):
				setTecla(e.getKeyCode(),true);
			break;
			case("Fin"):
				if (e.getKeyCode() == KeyEvent.VK_R){
					GestorPartida.getGestorPartida().reiniciarPartida();
					reiniciarTeclas();
				}
			break;
		}
	}

	/**
	 * Cuando reiniciamos la partida sin salirnos dandole a la R
	 */
	private void reiniciarTeclas(){
		upPressed = false;
		downPressed = false;
		leftPressed = false;
		rightPressed = false;
		spacePressed = false;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (pantallaActual.equals("Juego")) {
			setTecla(e.getKeyCode(), false);
		}

	}


	private void setTecla(int keyCode, boolean pressed) {
		switch(keyCode) {
			case KeyEvent.VK_W: upPressed = pressed; break;
			case KeyEvent.VK_S: downPressed = pressed; break;
			case KeyEvent.VK_A: leftPressed = pressed; break;
			case KeyEvent.VK_D: rightPressed = pressed; break;
			case KeyEvent.VK_SPACE: spacePressed = pressed; break;
		}
	}

	public void procesarMovimiento() {
		int dx = 0, dy = 0;

		if (upPressed) dy -= 1;
		if (leftPressed) dx -=1;
		if (downPressed) dy += 1;
		if (rightPressed) dx +=1;

		GestorPartida.getGestorPartida().moverNave(0,dx,dy);
	}

}