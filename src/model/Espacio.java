package model;

import java.awt.Color;
import java.util.Observer;


public class Espacio {

	private static Espacio miEspacio;
	private final int hDim = 100;
	private final int vDim = 60;

	private final ListaNaves listaNaves;
	private final ListaBalas listaBalas;
	private final ListaEnemigos listaEnemigos;

	private final Casilla[][] matriz;

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
	public static Espacio getEspacio() {
		if (miEspacio == null) {
			miEspacio = new Espacio();
		}
		return miEspacio;
	}

	public int getMaxEspaciado(int pNumEnem){
		return hDim / pNumEnem;	}

	@SuppressWarnings("deprecation")
	public void asignarObserverCasilla(Observer o, int pX, int pY) {
		matriz[pX][pY].asignarObserver(o);
	}

	public boolean esCoordenadaValida(int x, int y) {
		//en el eje X, añadimos un margen de -1 a +1 para permitir que la nave salga por el otro lado
		return x < this.hDim && y < this.vDim && x >= 0 && y >= 0;
	}

	//Creación y Movimiento de Naves
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
		}else{
			if(nx > this.hDim - 1){
				matriz[cX][cY].vaciar();
				matriz[0][ny].dibujarNave(colorNave);
				listaNaves.setCoordNave(idNave, 0, ny);
			}
			else if(nx<0){
				matriz[cX][cY].vaciar();
				matriz[hDim-1][ny].dibujarNave(colorNave);
				listaNaves.setCoordNave(idNave, hDim-1, ny);
			}
		}

	}
	public void borrarNaves(){
		//los borraremos de la pantalla primero y despues de la lista
		for (int i=0;i < listaNaves.getNumNaves();i++){
			Coordenada coordNave = listaNaves.getCoordNave(i);
			matriz[coordNave.getX()][coordNave.getY()].vaciar();
		}
		listaNaves.borrarListaNaves();
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
	public void borrarBalas(){
		//los borraremos de la pantalla primero y despues de la lista
		for (int i=0;i < listaBalas.getNumBalas();i++){
			Coordenada coordBala = listaBalas.getCoordBala(i);
			matriz[coordBala.getX()][coordBala.getY()].vaciar();
		}
		listaBalas.borrarListaBalas();
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
	public void borrarEnemigos(){
		//los borraremos de la pantalla primero y despues de la lista
		for (int i=0;i < listaEnemigos.getNumEnemigos();i++){
			Coordenada coordEnemigo = listaEnemigos.getCoordEnemigos(i);
			matriz[coordEnemigo.getX()][coordEnemigo.getY()].vaciar();
		}
		//ahora los borramos de la lista
		listaEnemigos.borrarListaEnemigos();
	}

	public void comprobarColisiones() {
		//Balas vs Enemigos
		comprobarColBalaEnemigo();
		//Enemigos vs Nave
		comprobarColEnemigoNave();
	}

	private void comprobarColEnemigoNave() {
		for(int i = listaNaves.getNumNaves() - 1; i>=0; i--) {
			Coordenada coordNave = listaNaves.getCoordNave(i);
			for(int j = listaEnemigos.getNumEnemigos() - 1; j>=0; j--) {
				Coordenada coordEnem = listaEnemigos.getCoordEnemigos(j);
				if(coordNave.equals(coordEnem)) {
					matriz[coordNave.getX()][coordNave.getY()].vaciar();
					listaNaves.eliminarNave(i);
					listaEnemigos.eliminarEnemigo(j);
					break; // si la nave ya ha sido eliminada no hay que seguir
				}
			}
		}
	}

	private void comprobarColBalaEnemigo() {
		// Iterar de atrás hacia adelante para evitar problemas con índices al eliminar
		for (int i = listaEnemigos.getNumEnemigos() - 1; i >= 0; i--) {
			Coordenada coordEnem = listaEnemigos.getCoordEnemigos(i);

			for (int j = listaBalas.getNumBalas() - 1; j >= 0; j--) {
				Coordenada coordBala = listaBalas.getCoordBala(j);

				if (coordEnem.equals(coordBala) || coordEnem.debajo(coordBala)) {
					//Hay que tener en cuenta que si la bala y el enemigo estan en posiciones contiguas
					//y se mueven a la vez, la bala va a quedar arriba y el enemigo abajo.

					matriz[coordEnem.getX()][coordEnem.getY()].vaciar();

					// Eliminar bala y enemigo
					listaBalas.eliminarBala(j);
					listaEnemigos.eliminarEnemigo(i);

					break; // Si el enemigo ya ha sido eliminado no hay que seguir
				}
			}
		}
	}

	public boolean quedanEnemigos() {
		return listaEnemigos.getNumEnemigos() > 0;
	}

	public boolean quedanNaves() {
		return listaNaves.getNumNaves() > 0;
	}

	public boolean enemigoGana() {
		return listaEnemigos.enemigoHaLLegadoAbajo();
	}
}