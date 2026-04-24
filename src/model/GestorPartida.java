package model;

import model.Enemigos.ListaEnemigos;
import model.Naves.ListaNaves;
import model.Tipos.TipoEnem;
import model.Tipos.TipoEventoJuego;
import model.Tipos.TipoNave;

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

	private TipoEventoJuego estadoFinal;

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
		anadirEnemigos();

		iniciarLoopJuego();

		setChanged();
		notifyObservers(TipoEventoJuego.JUGAR);
	}

	/**
	 * El orden de los metodos de esta funcion es lo que mas importa de cara al jeugo
	 */
	public void reiniciarPartida(){
		borrarEnemigos();
		reiniciarContadorIds();
		borrarBalas();
		borrarNaves();
		setChanged();
		notifyObservers(TipoEventoJuego.REINICIAR);
	}

	/**
	 * esFinPartida SOLO comprueba que no haya naves.
	 */
	private void iniciarLoopJuego() {
		if (gameTimer == null) {
			gameTimer = new Timer(gameDelay, e -> {
				if (entrarFaseBoss()) {//TODO: HACER CON STATE
					ListaEnemigos.getListaEnemigos().anadirEnemigo(50, 10, TipoEnem.boss);
				}else if(esFinPartida()) {
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

	/**
	 * Lo usamos en el iniciarLoopJuego()
	 * @return devuelve si quedan enemigos.
	 */
	private boolean entrarFaseBoss() {
		return !ListaEnemigos.getListaEnemigos().quedanEnemigos();
	}

	private void LoopJuego() {
		contadorAcciones++;
		if(contadorAcciones % 3/6 == 0) { // 30 ms  //CAMBIADO de % 3 == 0 a % 1 == 0
			setChanged();
			notifyObservers(TipoEventoJuego.REPAINT);
		}
		// mover balas y mover enemigos con su respectivo contador para controlar velocidad de movimiento
		if (contadorAcciones % 5/6 == 0) { // 50 ms //ahora tb esta cambiado (diferente a 50ms)
			ListaNaves.getListaNaves().moverBalas();
		}
		if (contadorAcciones % 20/2 == 0) { // 200 ms //(lo mismo)
			ListaEnemigos.getListaEnemigos().moverEnemigos();
			contadorAcciones = 0; // reset contador para evitar overflow a largo plazo
		}
	}

	/**
	 * Anadimos una nave de pid=0,"roja" y en la Coordenada (55,50)
	 * Por defecto el id es 0. Es aqui donde se lo asignamos. De momento, solo
	 * @param pTipo Tipo de nave(s) que queremos iniciar
	 */
	private void anadirNaves(TipoNave pTipo) {
		ListaNaves.getListaNaves().anadirNave(pTipo, 55, 50);
		ListaNaves.getListaNaves().ponerNavesEnEspacio();
	}

	private void borrarNaves(){
		ListaNaves.getListaNaves().borrarNaves();
	}

	/**
	 * Metodo que borra todas las balas de la lista de naves, el cual llama a la lista de naves para borrarlas
	 */
	private void borrarBalas(){
		ListaNaves.getListaNaves().borrarBalas();
	}


	private void anadirEnemigos() {
		int random = 5;
		boolean creado;
		int numEnemigos = numeroEnemigosAleatorio();
		for (int i = 0; i < numEnemigos; i++) {
			do {
				random += new Random().nextInt(10, Espacio.getEspacio().getMaxEspaciado(numEnemigos));
				creado = ListaEnemigos.getListaEnemigos().anadirEnemigo(random, 5, TipoEnem.normal );
			}
			while(!creado);
		}
		ListaEnemigos.getListaEnemigos().ponerEnemigosEnEspacio();
	}

	private void borrarEnemigos(){
		ListaEnemigos.getListaEnemigos().borrarEnemigos();
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
		if(!ListaEnemigos.getListaEnemigos().quedanEnemigos() && !Espacio.getEspacio().enemigoGana()){
			estadoFinal = TipoEventoJuego.GANADO;
			return true;
		}
		else if(!ListaNaves.getListaNaves().quedanNaves() || Espacio.getEspacio().enemigoGana()){
			estadoFinal = TipoEventoJuego.PERDIDO;
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
	 * LLama a naveAbstracta.reiniciarcontador naves
	 */
	private void reiniciarContadorIds(){
		GeneradorId.getGeneradorId().reset();
	}


}
