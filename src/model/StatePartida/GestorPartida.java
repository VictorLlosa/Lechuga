package model.StatePartida;

import model.Entidad.Balas.ListaBalas;
import model.Entidad.Enemigos.ListaEnemigos;
import model.Espacio;
import model.GeneradorId;
import model.Entidad.Naves.ListaNaves;
import model.Tipos.TipoEnemigo;
import model.Tipos.TipoEntidad;
import model.Tipos.TipoEventoJuego;
import model.Tipos.TipoNave;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.swing.Timer;


/**
 * Es observado por SpaceInvaders. Esta MAE se encarga de gestionar
 */
public class GestorPartida extends Observable {

	private static GestorPartida miGestorPartida;
	private int numJugadores = 1;
	private final int MIN_ENEM = 4;
	private final int MAX_ENEM = 8;

	private Timer gameTimer = null; // Timer único para el bucle del juego
									//(mejor que tener un timer por nave o bala,
									// porque la GUI tiene un thread único y esto
									// evita conflictos de concurrencia)
	private final int gameDelay = 10; // ms

    private EstadoPartida estadoPartida; //estado actual del gestor (implementa EstadoPartida)

	// contador general para controlar acciones periódicas (movimiento enemigos, balas, etc.)
    int contadorAcciones = 0;

	private GestorPartida() {
		estadoPartida = new EstadoFase1();
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
	private int numeroEnemigosAleatorio(){
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
		ListaEnemigos.getListaEnemigos().borrarEnemigos();
		GeneradorId.getGeneradorId().reset();
		ListaNaves.getListaNaves().borrarBalas();
		ListaNaves.getListaNaves().borrarNaves();
		setChanged();
		notifyObservers(TipoEventoJuego.REINICIAR);
	}

	private void iniciarLoopJuego() {
		if (gameTimer == null) {
			gameTimer = new Timer(gameDelay, e -> {
				ListaEnemigos.getListaEnemigos().borrarMuertos();
				ListaNaves.getListaNaves().borrarMuertos();
				ListaBalas.getListaBalas().borrarMuertos();
				estadoPartida.loopJuego(miGestorPartida);
			});
			gameTimer.setInitialDelay(0);
			gameTimer.start();
		}
	}
	public void detenerGameTimer() {
		if (gameTimer != null) {
			gameTimer.stop();
			gameTimer = null;
		}
	}


	/**
	 * @param pTipo Tipo de nave(s) que queremos iniciar
	 */
	private void anadirNaves(TipoNave pTipo) {
		for (int i = 0; i < numJugadores; i++) {
			int x = (i + 1) * Espacio.getEspacio().getMaxEspaciado(numJugadores + 1);
			int y = 100;
			ListaNaves.getListaNaves().anadirNave(pTipo, x, y);
		}
		ListaNaves.getListaNaves().ponerNavesEnEspacio();
	}


	private void anadirEnemigos() {
		int random = 10;
		boolean creado;
		int numEnemigos = numeroEnemigosAleatorio();
		for (int i = 0; i < numEnemigos; i++) {
			do {
				random += new Random().nextInt(10, Espacio.getEspacio().getMaxEspaciado(numEnemigos));
				creado = ListaEnemigos.getListaEnemigos().anadirEnemigo(random, 5, TipoEnemigo.normal );
			}
			while(!creado);
		}
		ListaEnemigos.getListaEnemigos().ponerEnemigosEnEspacio();
	} //TODO: MOVER A CADA ESTADO

	public void asignarObserverCasilla(Observer o, int pX, int pY) {
		Espacio.getEspacio().asignarObserverCasilla(o,pX,pY);
	}

	/**
	 * La partida se pierde cuando no "getEspacio.quedanNaves()" o el ".enemigoGana()"
	 * porque ha llegado abajo. Este métoodo nos sirve para saber que tenemos que pasar de fase
	 * @return si no hemos ganado ni perdido, devuelve un TipoEventoJuego.Jugar, ya que llamamos a este métoodo en cada vuelta del bucle
	 */
	TipoEventoJuego esFinPartida() {
        TipoEventoJuego estadoFinal;
        if(!ListaEnemigos.getListaEnemigos().quedanEnemigos() && !Espacio.getEspacio().enemigoGana()){
			estadoFinal = TipoEventoJuego.GANADO;
		}
		else if(!ListaNaves.getListaNaves().quedanNaves() || Espacio.getEspacio().enemigoGana()){
			estadoFinal = TipoEventoJuego.PERDIDO;

		}else{
			estadoFinal = TipoEventoJuego.JUGAR;
		}
		return estadoFinal;
	}

	public void asignarObserver(Observer o) {
		this.addObserver(o);
	}

	public void cambiarEstado(EstadoPartida pEst){
		this.estadoPartida= pEst;
	}
	void cambiarPantalla(TipoEventoJuego pEvento) {
		setChanged();
		notifyObservers(pEvento);
	}

    public void setNumeroJugadores(int pNumeroJugadores) {
		numJugadores = pNumeroJugadores;
    }
}

