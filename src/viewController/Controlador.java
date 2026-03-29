package viewController;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observer;

import model.GestorPartida;

import javax.swing.*;

/**
 * Implementa el KeyListener para poder usar las teclas. De momento, la logica del disparo de la nave está implementada pensando
 * que sólo hay 1
 */
public class Controlador implements KeyListener {


	private static Controlador miControlador =null;
	private Timer timer;

	// estado de teclas para movimiento continuo
	private volatile boolean upPressed = false;
	private volatile boolean downPressed = false;
	private volatile boolean leftPressed = false;
	private volatile boolean rightPressed = false;
	private volatile boolean spacePressed = false;

	//cambio de modo de disparo
	private volatile boolean cambioDisparo = false;

	//nave seleccionada
	private String colorNave = "rojo";


	private int contDisparo = 0; // contador para limitar velocidad de disparo
	private final int CADENCIA = 5; // ajustar para controlar cadencia de disparo (más bajo = más rápido)

	/**
	 * Si el atrib. disparoPressed es T y el numero de disparos en pantalla (contDisparo) es
	 * >= a la CADENCIA, dispararemos automaticamente.
	 */
	private Controlador() {
		timer = new Timer(40, e -> {
			procesarMovimiento();

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
		timer.start();
	}

	public static Controlador getControlador() {
		if(miControlador == null){
			miControlador = new Controlador();
		}
		return miControlador;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * Tratamos todas las acciones de las teclas. Desde SpaceInvaders le llamamos en su metodo 'update' de Observer, al metodo setPantallaActual,
	 * para cambiar el atributo de Controlador de "pantallaActual". Lo usamos de forma parecida a una maquina de estados, donde dependiendo de que
	 * estado (pantalla actual) tengamos activa, "activaremos o desactivaremos" una serie de teclas
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		switch (SpaceInvaders.getSpaceInvaders().pantallaActual) {
			case("Inicio"):
				//ENTER
				if (e.getKeyCode() == KeyEvent.VK_ENTER){

					GestorPartida.getGestorPartida().iniciarPartida(colorNave);
				}
				//Seleccion de nave. Dependiendo de la tecla pulsada, la Nave es de un Color
				else if(e.getKeyCode() == KeyEvent.VK_A){
					this.colorNave="azul";
					PantallaJuego.getPantallaJuego().cambiarColorNave(Color.BLUE);
				}
				else if(e.getKeyCode() == KeyEvent.VK_R){
					this.colorNave="rojo";
					PantallaJuego.getPantallaJuego().cambiarColorNave(Color.RED);
				}
				else if(e.getKeyCode() == KeyEvent.VK_V){
					this.colorNave="verde";
					PantallaJuego.getPantallaJuego().cambiarColorNave(Color.GREEN);
				}

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
	 * Cuando reiniciamos la partida sin salirnos, dandole a la R
	 */
	private void reiniciarTeclas(){
		upPressed = false;
		downPressed = false;
		leftPressed = false;
		rightPressed = false;
		spacePressed = false;
		cambioDisparo = false;
	}

	/**
	 * Si estamos en juego, llama a setTecla, que pone el WASD y el ENTER a false (y la M)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if (SpaceInvaders.getSpaceInvaders().pantallaActual.equals("Juego")) {
			setTecla(e.getKeyCode(), false);
		}

	}

	/**
	 *
	 * @param keyCode el VK_(tecla)
	 * @param pressed booleano que dice si se ha presionado (true) o no la tecla
	 */
	private void setTecla(int keyCode, boolean pressed) {
		switch(keyCode) {
			case KeyEvent.VK_W: upPressed = pressed; break;
			case KeyEvent.VK_S: downPressed = pressed; break;
			case KeyEvent.VK_A: leftPressed = pressed; break;
			case KeyEvent.VK_D: rightPressed = pressed; break;
			case KeyEvent.VK_SPACE: spacePressed = pressed; break;
			case KeyEvent.VK_M: cambioDisparo = pressed; break;
		}
	}

	private void procesarMovimiento() {
		int dx = 0, dy = 0;

		if (upPressed) dy -= 1;
		if (leftPressed) dx -= 1;
		if (downPressed) dy += 1;
		if (rightPressed) dx += 1;

		GestorPartida.getGestorPartida().moverNave(0,dx,dy);
	}

	/**
	 * Llama a "toggleModoDisparo() de GestorPartida si se ha cambiado el modo de Disparo"
	 * De momento, solo tenemos la nave de id 0.
	 */
	private void procesarModoDisparo(){
		if (cambioDisparo) GestorPartida.getGestorPartida().alternarModoDisparo();
	}
}