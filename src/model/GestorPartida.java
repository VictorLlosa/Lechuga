package model;

import java.awt.Color;
import java.util.Observer;
import javax.swing.Timer;
import viewController.PantallaJuego;

public class GestorPartida {

	private static GestorPartida miGestorPartida;
	private Timer gameTimer = null; // Timer único para el bucle del juego
									//(mejor que tener un timer por nave o bala,
									// porque la GUI tiene un thread único y esto
									// evita conflictos de concurrencia)
	private final int gameDelay = 10; // ms
	private int frameCount = 0;

	// estado de teclas para movimiento continuo
	private volatile boolean upPressed = false;
	private volatile boolean downPressed = false;
	private volatile boolean leftPressed = false;
	private volatile boolean rightPressed = false;

	// estado de disparo para evitar disparo infinito
	private volatile boolean disparoPressed = false;
	private int contDisparo = 0; // contador para limitar velocidad de disparo
	private final int contDisparoMax = 5; // ajustar para controlar cadencia de disparo (más bajo = más rápido)

	private GestorPartida() {
		Espacio.getEspacio();
	}


	public static GestorPartida getGestorPartida() {
		if(miGestorPartida == null) {
			miGestorPartida = new GestorPartida();
		}
		return miGestorPartida;
	}

	public void iniciarPartida(){
		Espacio.getEspacio().anadirNave(Color.red, new Coordenada(55,50));  //Añadir Nave

		//Añadir Enegmigos
		Espacio.getEspacio().anadirEnemigos(1,new Coordenada(15,5));
		Espacio.getEspacio().anadirEnemigos(2,new Coordenada(25,5));
		Espacio.getEspacio().anadirEnemigos(3,new Coordenada(65,5));
		Espacio.getEspacio().anadirEnemigos(4,new Coordenada(90,5));
		// iniciar game loop si no existe
		if (gameTimer == null) {
			gameTimer = new Timer(gameDelay, e -> {
				frameCount++;
				// mover balas y mover enemigos, cambiar 2 por 5 para que sea cada 50ms
				if (frameCount % 2 == 0) {
					Espacio.getEspacio().moverBalas();
					procesarMovimientoContinuo();
				}
				if (frameCount % 20 == 0) {
					Espacio.getEspacio().moverEnemigos();
				}
				// repintar pantalla
				PantallaJuego.getPantallaJuego().repaint();
				// procesar movimiento continuo de la nave

				// procesar disparo
				contDisparo++;
				if(contDisparo > contDisparoMax) contDisparo = contDisparoMax;
				procesarDisparo(0);

			});
			gameTimer.setInitialDelay(0);
			gameTimer.start();
		}
	}

	public void asignarObserver(Observer o, int pX, int pY) {
		Espacio.getEspacio().asignarObserver(o,pX,pY);
	}

	public void moverNave(String tecla) {
		Espacio.getEspacio().moverNave(0, tecla);
	}

	// start/stop disparo continuo
	public void startDisparar(int idNave) {
		// preparar disparo continuo si se mantiene pulsada
		disparoPressed = true;
	}
	public void stopDisparar(int i) {
		disparoPressed = false;
	}

	private void procesarDisparo(int idNave) {
		if(disparoPressed && contDisparo >= contDisparoMax) {
			Espacio.getEspacio().disparar(idNave);
			contDisparo = 0; // reset tras disparo automático
		}
	}

	// start/stop movimiento continuo
	public void startMover(String tecla) {
		switch (tecla) {
			case "w": upPressed = true; break;
			case "a": leftPressed = true; break;
			case "s": downPressed = true; break;
			case "d": rightPressed = true; break;
		}
	}

	public void stopMover(String tecla) {
		switch (tecla) {
			case "w": upPressed = false; break;
			case "a": leftPressed = false; break;
			case "s": downPressed = false; break;
			case "d": rightPressed = false; break;
		}
	}

	// mover la nave según el estado actual de las teclas
	private void procesarMovimientoContinuo() {
		if (upPressed) Espacio.getEspacio().moverNave(0, "w");
		if (downPressed) Espacio.getEspacio().moverNave(0, "s");
		if (leftPressed) Espacio.getEspacio().moverNave(0, "a");
		if (rightPressed) Espacio.getEspacio().moverNave(0, "d");
	}

	public void detenerGameTimer() {
		if (gameTimer != null) {
			gameTimer.stop();
			gameTimer = null;
		}
	}


}
