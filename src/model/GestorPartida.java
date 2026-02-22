package model;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.swing.Timer;
import viewController.PantallaJuego;

public class GestorPartida extends Observable {

	private static GestorPartida miGestorPartida;
	private final Espacio espacio = Espacio.getEspacio();
	private int numEnemigos = 4; // número de enemigos a generar (ajustable para dificultad)

	private Timer gameTimer = null; // Timer único para el bucle del juego
									//(mejor que tener un timer por nave o bala,
									// porque la GUI tiene un thread único y esto
									// evita conflictos de concurrencia)
	private final int gameDelay = 10; // ms

	private String estadoFinal = "";


	// estado de teclas para movimiento continuo
	private volatile boolean upPressed = false;
	private volatile boolean downPressed = false;
	private volatile boolean leftPressed = false;
	private volatile boolean rightPressed = false;

	// estado de disparo para evitar disparo infinito
	private volatile boolean disparoPressed = false;
	private int contDisparo = 0; // contador para limitar velocidad de disparo
	private final int contDisparoMax = 5; // ajustar para controlar cadencia de disparo (más bajo = más rápido)
	// contador general para controlar acciones periódicas (movimiento enemigos, balas, etc.)
	private int contadorAcciones = 0;

	private GestorPartida() {

	}


	public static GestorPartida getGestorPartida() {
		if(miGestorPartida == null) {
			miGestorPartida = new GestorPartida();
		}
		return miGestorPartida;
	}

	public void iniciarPartida(){
		//Añadir Nave
		anadirNaves();
		//Añadir Enegmigos
		anadirEnemigos();
		iniciarLoopJuego();
	}

	private void iniciarLoopJuego() {
		if (gameTimer == null) {
			gameTimer = new Timer(gameDelay, e -> {

				if (esFinPartida()) {
					detenerGameTimer();
					setChanged();
					notifyObservers(estadoFinal);
				}
				LoopJuego();
			});
			gameTimer.setInitialDelay(0);
			gameTimer.start();
		}
	}

	private void LoopJuego() {
		contadorAcciones++;
		if(contadorAcciones % 3 == 0) { // 30 ms
			// repintar pantalla
			//menos fluido que llamar directamente
			//a PantallaJuego.repaint() pero mantiene el modelo desacoplado de la vista
			setChanged();
			notifyObservers("repaint");
			// procesar movimiento continuo de la nave
			procesarMovimientoContinuo();
			// procesar disparo
			contDisparo++;
			if(contDisparo > contDisparoMax) contDisparo = contDisparoMax;
			procesarDisparo(0);
		}
		// mover balas y mover enemigos con su respectivo contador para controlar velocidad de movimiento
		if (contadorAcciones % 5 == 0) { // 50 ms
			espacio.moverBalas();
		}
		if (contadorAcciones % 20 == 0) { // 200 ms
			espacio.moverEnemigos();
			contadorAcciones = 0; // reset contador para evitar overflow a largo plazo
		}

		comprobarColisiones();
	}

	private void anadirNaves() {
		espacio.anadirNave(Color.red, new Coordenada(55,50));
	}

	private void anadirEnemigos() {
		int random = 5;
		for (int i = 0; i < numEnemigos; i++) {

			do {
				random += new Random().nextInt(10, 30);
			}
			while(!espacio.esCoordenadaValida(random,5));

			espacio.anadirEnemigos(0,new Coordenada(random,5));
		}
	}

	public void asignarObserverCasilla(Observer o, int pX, int pY) {
		espacio.asignarObserverCasilla(o,pX,pY);
	}

	public void moverNave(String tecla) {
		espacio.moverNave(0, tecla);
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
			espacio.disparar(idNave);
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
		if (upPressed) espacio.moverNave(0, "w");
		if (downPressed) espacio.moverNave(0, "s");
		if (leftPressed) espacio.moverNave(0, "a");
		if (rightPressed) espacio.moverNave(0, "d");
	}

	//
	private void comprobarColisiones() {
		espacio.comprobarColisiones();
	}

	private boolean esFinPartida() {
		if(!espacio.quedanEnemigos() && !espacio.enemigoGana()){
			estadoFinal = "ganado";
			return true;
		}
		else if(!espacio.quedanNaves() || espacio.enemigoGana()){
			estadoFinal = "perdido";
			return true;
		}else{
			return false;
		}
	}
	public void detenerGameTimer() {
		if (gameTimer != null) {
			gameTimer.stop();
			gameTimer = null;
		}
	}


	public void asignarObserver(Observer o) {
		this.addObserver(o);
	}
}
