package model;

import java.awt.Color;
import java.util.Iterator;
import java.util.Observer;


public class Espacio {

	private static Espacio miEspacio;
	private final int hDim = 100;
	private final int vDim = 60;

	private ListaNaves listaNaves;
	private ListaBalas listaBalas;
	private ListaEnemigos listaEnemigos;

	private Casilla[][] matriz;

	private Espacio() {

		listaNaves = new ListaNaves();
		listaBalas = new ListaBalas();
		listaEnemigos = new ListaEnemigos();

		matriz = new Casilla[hDim][vDim];
		for (int i = 0; i < hDim; i++) {
			for (int j = 0; j < vDim; j++) {
				matriz[i][j] = new Casilla();
			}
		}
	}

	public void anadirNave(Color pColor, Coordenada pCoord) {

		listaNaves.anadirNave(pColor, pCoord);
		matriz[55][50].dibujarNave(listaNaves.getColorNave(0));
		/* NAVE DE + DE 1 PIXEL
		for(int i = 55 - cX; i<= 55 + cX; i++) {
			for(int j = 50 - cY; j<= 50 + cY; j++) {
				matriz[i][j].cambiarColor(nave[55 - i][50 - j]);
			}
		}
		*/
	}

	@SuppressWarnings("deprecation")
	public void asignarObserver(Observer o, int pX, int pY) {
		matriz[pX][pY].asignarObserver(o);
	}

	public static Espacio getEspacio() {
		if (miEspacio == null) {
			miEspacio = new Espacio();
		}
		return miEspacio;
	}

	public boolean esCoordenadaValida(int x, int y) {
		return x < this.hDim && y < this.vDim && x >= 0 && y >= 0;
	}

	public void moverNave(int idNave, String tecla) {

		Coordenada coordNave = listaNaves.getCoordNave(idNave);

		Color colorNave = listaNaves.getColorNave(idNave);

		int cX = coordNave.getX();
		int cY = coordNave.getY();

		int dx = 0, dy = 0;
		switch (tecla) {
			case "w":
				dy = -1;
				break;
			case "a":
				dx = -1;
				break;
			case "s":
				dy = 1;
				break;
			case "d":
				dx = 1;
				break;
			default:
				return;
		}

		int nx = cX + dx;
		int ny = cY + dy;
		if (esCoordenadaValida(nx, ny)) {
			matriz[cX][cY].vaciar();
			matriz[nx][ny].dibujarNave(colorNave);
			listaNaves.setCoordNave(idNave, nx, ny);
		}

	}
//Creación y Movimiento de Balas
	public void disparar(int idNave) {
		Coordenada coordNave = listaNaves.getCoordNave(idNave);
		Coordenada coordBala = new Coordenada(coordNave.getX(), coordNave.getY() - 1);
		if (esCoordenadaValida(coordBala.getX(), coordBala.getY())) {
			listaBalas.anadirBala(idNave, coordBala);
			matriz[coordBala.getX()][coordBala.getY()].dibujarBala();
		}
	}

	public void moverBalas() {


		// Primero vaciamos las casillas que tenian las balas
		// luego delegamos a la lista de balas el movimiento (que actualiza las coordenadas internamente)
		// y finalmente dibujamos las balas en sus nuevas posiciones

		int num = listaBalas.getNumBalas();
		for (int i = 0; i < num; i++) {
			Coordenada coordBala = listaBalas.getCoordBala(i);
			matriz[coordBala.getX()][coordBala.getY()].vaciar();
		}

		// Mover las balas en la lista (actualiza coordenadas internamente)
		listaBalas.moverBalas();

		// Dibujar las balas en sus nuevas posiciones
		num = listaBalas.getNumBalas();
		for (int i = 0; i < num; i++) {
			Coordenada coordBala = listaBalas.getCoordBala(i);
			matriz[coordBala.getX()][coordBala.getY()].dibujarBala();
		}
	}
//Creación y movimiento de Enemigos
	public void anadirEnemigos(int idEnemigo, Coordenada cord) {
		if (esCoordenadaValida(cord.getX(), cord.getY())) {
			listaEnemigos.anadirEnemigo(idEnemigo, cord);
			matriz[cord.getX()][cord.getY()].dibujarEnemigo();
		}
	}
	public void moverEnemigos() {
		int num = listaEnemigos.getNumEnemigos();
		for (int i = 0; i < num; i++) {
			Coordenada coordEnem = listaEnemigos.getCoordEnemigos(i);
			matriz[coordEnem.getX()][coordEnem.getY()].vaciar();
		}

		// Mover los enemigos en la lista (actualiza coordenadas internamente)
		listaEnemigos.moverEnemigos();

		// Dibujar los enemigos en sus nuevas posiciones
		num = listaEnemigos.getNumEnemigos();
		for (int i = 0; i < num; i++) {
			Coordenada coordEnem = listaEnemigos.getCoordEnemigos(i);
			matriz[coordEnem.getX()][coordEnem.getY()].dibujarEnemigo();
		}
	}

	/*
	public void eliminarEnemigo() {
		Iterator<Enemigo> it = listaEnemigos.iterator();
		while (it.hasNext()) {
			Enemigo enemigo = it.next();
			Iterator<Bala> itBala = listaBalas.iterator();
			while (itBala.hasNext()) {
			Bala bala = itBala.next();
				if (bala.getCoord().equals(enemigo.getCoord())) {
					listaEnemigos.eliminarEnemigo(enemigo.getId());
				}
			}
		}

	}
	*/
}