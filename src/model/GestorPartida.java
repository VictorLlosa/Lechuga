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
	private int numEnemigos; // número de enemigos a generar (ajustable para dificultad)
	private final int MIN_ENEM = 4;
	private final int MAX_ENEM = 8;

	private Timer gameTimer = null; // Timer único para el bucle del juego
									//(mejor que tener un timer por nave o bala,
									// porque la GUI tiene un thread único y esto
									// evita conflictos de concurrencia)
	private final int gameDelay = 10; // ms

	private String estadoFinal = "";

	// estado de disparo para evitar disparo infinito
	private volatile boolean disparoPressed = false;
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
	/**
	 * Metodo dentro de la ctr de GestorPartida, lo usamos en caso de que el n. de
	 * querer generar los enemigos aleatoriamente (de 4 a 8)
	 */
	public void numeroEnemigosAleatorio(){
		Random r = new Random();
		numEnemigos = MIN_ENEM + r.nextInt(MAX_ENEM - MIN_ENEM+1);
	}

	public void iniciarPartida(){
		//Añadir Nave
		anadirNaves();
		//Añadir Enegmigos
		numeroEnemigosAleatorio();
		anadirEnemigos();

		iniciarLoopJuego();

		setChanged();
		notifyObservers("jugar");
	}

	public void reiniciarPartida(){
		borrarEnemigos();
		borrarNaves();
		borrarBalas();
		//reiniciarTeclas();
		numeroEnemigosAleatorio();
		setChanged();
		notifyObservers("reiniciar");
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
			setChanged();
			notifyObservers("repaint");
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
		espacio.anadirNave(0, Color.red, new Coordenada(55,50));
	}

	private void borrarNaves(){
		espacio.borrarNaves();
	}

	private void borrarBalas(){
		espacio.borrarBalas();
	}

	private void anadirEnemigos() {
		int random = 5;
		for (int i = 0; i < numEnemigos; i++) {
			do {
				random += new Random().nextInt(10, espacio.getMaxEspaciado(numEnemigos));
			}
			while(!espacio.esCoordenadaValida(random,5));

			espacio.anadirEnemigos(0,new Coordenada(random,5));
		}
	}

	private void borrarEnemigos(){
		espacio.borrarEnemigos();
	}

	public void asignarObserverCasilla(Observer o, int pX, int pY) {
		espacio.asignarObserverCasilla(o,pX,pY);
	}

	public void moverNave(int idNave, int dx, int dy) {
		espacio.moverNave(idNave, dx, dy);
	}

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

    public void disparar() {
		espacio.disparar(0);
    }
}
