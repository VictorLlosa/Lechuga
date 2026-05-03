package viewController;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.StatePartida.GestorPartida;
import model.Entidad.Naves.ListaNaves;
import model.Tipos.TipoNave;

import javax.swing.*;

public class Controlador implements KeyListener {

	private static Controlador miControlador = null;
	private Timer timer;

	private InputJugador jugador1 = new InputJugador();
	private InputJugador jugador2 = new InputJugador();

	private TipoNave tipoNave = TipoNave.red;
	private boolean dosJugadores = false;

	private Controlador() {
		timer = new Timer(8, e -> procesarNaves());
		timer.start();
	}

	public static Controlador getControlador() {
		if (miControlador == null) {
			miControlador = new Controlador();
		}
		return miControlador;
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (SpaceInvaders.getSpaceInvaders().getPantallaActual()) {
			case INICIO:
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					GestorPartida.getGestorPartida().iniciarPartida(tipoNave);
				}
				break;
			case JUEGO:
				if (e.getKeyCode() == KeyEvent.VK_M) {
					ListaNaves.getListaNaves().alternarModoDisparo(0);
				} else if (e.getKeyCode() == KeyEvent.VK_MINUS) {
					ListaNaves.getListaNaves().alternarModoDisparo(1);
				}else {
					setTecla(e.getKeyCode(), true);
				}
				break;
			case FIN:
				if (e.getKeyCode() == KeyEvent.VK_R) {
					GestorPartida.getGestorPartida().reiniciarPartida();
					reiniciarTeclas();
				}
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (SpaceInvaders.getSpaceInvaders().getPantallaActual() == EstadoPantalla.JUEGO) {
			setTecla(e.getKeyCode(), false);
		}
	}

	private void setTecla(int keyCode, boolean pressed) {
		switch (keyCode) {
			// Jugador 1 (WASD + SPACE)
			case KeyEvent.VK_W:     jugador1.setAccion(Accion.UP,    pressed); break;
			case KeyEvent.VK_S:     jugador1.setAccion(Accion.DOWN,  pressed); break;
			case KeyEvent.VK_A:     jugador1.setAccion(Accion.LEFT,  pressed); break;
			case KeyEvent.VK_D:     jugador1.setAccion(Accion.RIGHT, pressed); break;
			case KeyEvent.VK_SPACE: jugador1.setAccion(Accion.SHOOT, pressed); break;

			// Jugador 2 (Flechas + ENTER)
			case KeyEvent.VK_UP:    jugador2.setAccion(Accion.UP,    pressed); break;
			case KeyEvent.VK_DOWN:  jugador2.setAccion(Accion.DOWN,  pressed); break;
			case KeyEvent.VK_LEFT:  jugador2.setAccion(Accion.LEFT,  pressed); break;
			case KeyEvent.VK_RIGHT: jugador2.setAccion(Accion.RIGHT, pressed); break;
			case KeyEvent.VK_ENTER: jugador2.setAccion(Accion.SHOOT, pressed); break;
		}
	}

	private void procesarNaves() {
		procesarNaveJugador(0, jugador1);
		if (dosJugadores) procesarNaveJugador(1, jugador2);
	}

	private void procesarNaveJugador(int pJugador, InputJugador input) {
		int dx = 0, dy = 0;

		if (input.estaActiva(Accion.UP))    dy -= 1;
		if (input.estaActiva(Accion.DOWN))  dy += 1;
		if (input.estaActiva(Accion.LEFT))  dx -= 1;
		if (input.estaActiva(Accion.RIGHT)) dx += 1;

		if (dx != 0 || dy != 0) {
			ListaNaves.getListaNaves().moverNave(pJugador, dx, dy);
		}

		if (input.estaActiva(Accion.SHOOT)) {
			ListaNaves.getListaNaves().disparar(pJugador);
		}
	}

	private void reiniciarTeclas() {
		jugador1.reiniciar();
		jugador2.reiniciar();
	}

	public void seleccionarNave(TipoNave tipo) {
		this.tipoNave = tipo;
		switch (tipo) {
			case TipoNave.red:   PantallaJuego.getPantallaJuego().cambiarColorNave(Color.RED);   break;
			case TipoNave.green: PantallaJuego.getPantallaJuego().cambiarColorNave(Color.GREEN); break;
			case TipoNave.blue:  PantallaJuego.getPantallaJuego().cambiarColorNave(Color.BLUE);  break;
		}
	}

	public void seleccionarModoJugadores(boolean pDosJugadores) {
		dosJugadores = pDosJugadores;
		GestorPartida.getGestorPartida().setNumeroJugadores(dosJugadores ? 2 : 1);
	}
}