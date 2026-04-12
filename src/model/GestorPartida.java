package model;

import model.Composite.Pixel;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.swing.Timer;


/**
 * Es observado por SpaceInvaders. Esta MAE se encarga de gestionar
 */
public class GestorPartida extends Observable {

	private static GestorPartida miGestorPartida;
	private final int MIN_ENEM = 4;
	private final int MAX_ENEM = 8;

	private Timer gameTimer = null; // Timer único para el bucle del juego
									//(mejor que tener un timer por nave o bala,
									// porque la GUI tiene un thread único y esto
									// evita conflictos de concurrencia)
	private final int gameDelay = 10; // ms

	private String estadoFinal = "";

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
	public int numeroEnemigosAleatorio(){
		Random r = new Random();
		return MIN_ENEM + r.nextInt(MAX_ENEM - MIN_ENEM+1);
	}

	/**
	 * Llama a this.anadirNaves con el parametro del tipo de la nave inciial
	 * @param pTipoNave establece el tipo de todas las naves
	 */
	public void iniciarPartida(TipoNave pTipoNave){
		//Añadir Nave
		anadirNaves(pTipoNave);
		//Añadir Enegmigos
		numeroEnemigosAleatorio();
		anadirEnemigos();

		iniciarLoopJuego();

		setChanged();
		notifyObservers("jugar");
	}

	/**
	 * El orden de los metodos de esta funcion es lo que mas importa de cara al jeugo
	 */
	public void reiniciarPartida(){
		borrarEnemigos();
		reiniciarContadorNaves();
		borrarBalas();
		borrarNaves();
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
				}else{
					LoopJuego();
				}
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
			ListaNaves.getListaNaves().moverBalas();
		}
		if (contadorAcciones % 20 == 0) { // 200 ms
			Espacio.getEspacio().moverEnemigos();
			contadorAcciones = 0; // reset contador para evitar overflow a largo plazo
		}
	}

	/**
	 * Anadimos una nave de pid=0,"roja" y en la Coordenada (55,50)
	 * Por defecto el id es 0. Es aqui donde se lo asignamos. De momento, solo
	 * @param pTipo Tipo de nave(s) que queremos iniciar
	 */
	private void anadirNaves(TipoNave pTipo) {
		Espacio.getEspacio().anadirNave(pTipo, new Pixel(55,50));
	}

	private void borrarNaves(){
		Espacio.getEspacio().borrarNaves();
	}

	private void borrarBalas(){
		Espacio.getEspacio().borrarBalas();
	}


	private void anadirEnemigos() {
		int random = 5;
		int numEnemigos = numeroEnemigosAleatorio();
		for (int i = 0; i < numEnemigos; i++) {
			do {
				random += new Random().nextInt(10, Espacio.getEspacio().getMaxEspaciado(numEnemigos));
			}
			while(!Espacio.getEspacio().anadirEnemigos(new Pixel(random,5)));
		}
	}

	private void borrarEnemigos(){
		Espacio.getEspacio().borrarEnemigos();
	}

	public void asignarObserverCasilla(Observer o, int pX, int pY) {
		Espacio.getEspacio().asignarObserverCasilla(o,pX,pY);
	}
	/**
	 * La partida se pierde cuando no "getEspacio.quedanNaves()" o el ".enemigoGana()"
	 * porque ha llegado abajo
	 * @return
	 */
	private boolean esFinPartida() {
		if(!Espacio.getEspacio().quedanEnemigos() && !Espacio.getEspacio().enemigoGana()){
			estadoFinal = "ganado";
			return true;
		}
		else if(!Espacio.getEspacio().quedanNaves() || Espacio.getEspacio().enemigoGana()){
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

	/**
	 * Cambia el modo disparo de una nave. TODO poner que se haga por IDNave (o sea, por cada nave si las hubiese)
	 * Ahora esta puesto por defecto a la nave 0
	 */
	public void alternarModoDisparo(){
		//ListaNaves.getListaNaves().alternarModoDisparo(0);
	}

	/**
	 * LLama a naveAbstracta.reiniciarcontador naves
	 */
	private void reiniciarContadorNaves(){
		ListaNaves.getListaNaves().reiniciarContadorNaves();
	}


}
