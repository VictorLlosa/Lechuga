package model;

import java.awt.Color;
import java.util.Observer;


public class Espacio {

	private static Espacio miEspacio;
	private final int hDim = 100;
	private final int vDim = 60;

	private final Casilla[][] matriz;

	private Espacio() {
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
	public void anadirNave(int pId, Color pColor, Coordenada pCoord) {

		ListaNaves.getListaNaves().anadirNave(pId, pColor, pCoord);
		matriz[55][50].dibujarNave(ListaNaves.getListaNaves().getColorNave(0));
		/* NAVE DE + DE 1 PIXEL
		for(int i = 55 - cX; i<= 55 + cX; i++) {
			for(int j = 50 - cY; j<= 50 + cY; j++) {
				matriz[i][j].cambiarColor(nave[55 - i][50 - j]);
			}
		}
		*/
	}

	/**
	 * Si no existe la nave (listaNaves.lenght()!=0), no haremos nada. Esto es para que
	 * @param idNave
	 * @param dx
	 * @param dy
	 */
	public void moverNave(int idNave, int dx, int dy) {
		if(!ListaNaves.getListaNaves().existeNave(idNave)) return;
		Coordenada coordNave = ListaNaves.getListaNaves().getCoordNave(idNave);

		Color colorNave = ListaNaves.getListaNaves().getColorNave(idNave);

		int cX = coordNave.getX();
		int cY = coordNave.getY();

		int nx = cX + dx;
		int ny = cY + dy;
		if (esCoordenadaValida(nx, ny)) {
			matriz[cX][cY].vaciar();
			matriz[nx][ny].dibujarNave(colorNave);
			ListaNaves.getListaNaves().setCoordNave(idNave, nx, ny);
		}else{
			if(nx > this.hDim - 1){
				matriz[cX][cY].vaciar();
				matriz[0][ny].dibujarNave(colorNave);
				ListaNaves.getListaNaves().setCoordNave(idNave, 0, ny);
			}
			else if(nx<0){
				matriz[cX][cY].vaciar();
				matriz[hDim-1][ny].dibujarNave(colorNave);
				ListaNaves.getListaNaves().setCoordNave(idNave, hDim-1, ny);
			}
		}

	}
	public void borrarNaves(){
		//los borraremos de la pantalla primero y despues de la lista
		for (int i=0;i < ListaNaves.getListaNaves().getNumNaves();i++){
			Coordenada coordNave = ListaNaves.getListaNaves().getCoordNave(i);
			matriz[coordNave.getX()][coordNave.getY()].vaciar();
		}
		ListaNaves.getListaNaves().borrarListaNaves();
	}

	//Creación y Movimiento de Balas
	public void disparar(int idNave) {
		Coordenada coordNave = ListaNaves.getListaNaves().getCoordNave(idNave);
		Coordenada coordBala = new Coordenada(coordNave.getX(), coordNave.getY() - 1);
		if (esCoordenadaValida(coordBala.getX(), coordBala.getY())) {
			ListaBalas.getListaBalas().anadirBala(idNave, coordBala);
			matriz[coordBala.getX()][coordBala.getY()].dibujarBala();
		}
	}
	public void moverBalas() {

		// Primero vaciamos las casillas que tenian las balas
		// luego delegamos a la lista de balas el movimiento (que actualiza las coordenadas internamente)
		// y finalmente dibujamos las balas en sus nuevas posiciones

		int num = ListaBalas.getListaBalas().getNumBalas();
		for (int i = 0; i < num; i++) {
			Coordenada coordBala = ListaBalas.getListaBalas().getCoordBala(i);
			matriz[coordBala.getX()][coordBala.getY()].vaciar();
		}

		// Mover las balas en la lista (actualiza coordenadas internamente)
		ListaBalas.getListaBalas().moverBalas();

		// Dibujar las balas en sus nuevas posiciones
		num = ListaBalas.getListaBalas().getNumBalas();
		for (int i = 0; i < num; i++) {
			Coordenada coordBala = ListaBalas.getListaBalas().getCoordBala(i);
			matriz[coordBala.getX()][coordBala.getY()].dibujarBala();
		}
	}
	public void borrarBalas(){
		//los borraremos de la pantalla primero y despues de la lista
		for (int i=0;i < ListaBalas.getListaBalas().getNumBalas();i++){
			Coordenada coordBala = ListaBalas.getListaBalas().getCoordBala(i);
			matriz[coordBala.getX()][coordBala.getY()].vaciar();
		}
		ListaBalas.getListaBalas().borrarListaBalas();
	}

	//Creación y movimiento de Enemigos
	public void anadirEnemigos(int idEnemigo, Coordenada cord) {
		if (esCoordenadaValida(cord.getX(), cord.getY())) {
			ListaEnemigos.getListaEnemigos().anadirEnemigo(idEnemigo, cord);
			matriz[cord.getX()][cord.getY()].dibujarEnemigo();
		}
	}
	public void moverEnemigos() {
		int num = ListaEnemigos.getListaEnemigos().getNumEnemigos();
		for (int i = 0; i < num; i++) {
			Coordenada coordEnem = ListaEnemigos.getListaEnemigos().getCoordEnemigos(i);
			matriz[coordEnem.getX()][coordEnem.getY()].vaciar();
		}

		// Mover los enemigos en la lista (actualiza coordenadas internamente)
		ListaEnemigos.getListaEnemigos().moverEnemigos();

		// Dibujar los enemigos en sus nuevas posiciones
		num = ListaEnemigos.getListaEnemigos().getNumEnemigos();
		for (int i = 0; i < num; i++) {
			Coordenada coordEnem = ListaEnemigos.getListaEnemigos().getCoordEnemigos(i);
			matriz[coordEnem.getX()][coordEnem.getY()].dibujarEnemigo();
		}
	}
	public void borrarEnemigos(){
		//los borraremos de la pantalla primero y despues de la lista
		for (int i=0;i < ListaEnemigos.getListaEnemigos().getNumEnemigos();i++){
			Coordenada coordEnemigo = ListaEnemigos.getListaEnemigos().getCoordEnemigos(i);
			matriz[coordEnemigo.getX()][coordEnemigo.getY()].vaciar();
		}
		//ahora los borramos de la lista
		ListaEnemigos.getListaEnemigos().borrarListaEnemigos();
	}

	public void comprobarColisiones() {
		//Balas vs Enemigos
		comprobarColBalaEnemigo();
		//Enemigos vs Nave
		comprobarColEnemigoNave(0);
	}

	/**
	 * Recorremos toda la longitud de la lista de Naves y en cada iteracion, obtenemos la coordenada en la que esta la nave.
	 */
	private void comprobarColEnemigoNave(int pId) {
		Coordenada coordNave = ListaNaves.getListaNaves().getCoordNave(pId);
		for(int j = ListaEnemigos.getListaEnemigos().getNumEnemigos() - 1; j>=0; j--) {
			Coordenada coordEnem = ListaEnemigos.getListaEnemigos().getCoordEnemigos(j);
			if(coordNave.equals(coordEnem)) {
				matriz[coordNave.getX()][coordNave.getY()].vaciar();
				ListaNaves.getListaNaves().eliminarNave(pId);
				ListaEnemigos.getListaEnemigos().eliminarEnemigo(j);
				break; // si la nave ya ha sido eliminada no hay que seguir
			}
		}
	}

	private void comprobarColBalaEnemigo() {
		// Iterar de atrás hacia adelante para evitar problemas con índices al eliminar
		for (int i = ListaEnemigos.getListaEnemigos().getNumEnemigos() - 1; i >= 0; i--) {
			Coordenada coordEnem = ListaEnemigos.getListaEnemigos().getCoordEnemigos(i);

			for (int j = ListaBalas.getListaBalas().getNumBalas() - 1; j >= 0; j--) {
				Coordenada coordBala = ListaBalas.getListaBalas().getCoordBala(j);

				if (coordEnem.equals(coordBala) || coordEnem.debajo(coordBala)) {
					//Hay que tener en cuenta que si la bala y el enemigo estan en posiciones contiguas
					//y se mueven a la vez, la bala va a quedar arriba y el enemigo abajo.

					matriz[coordEnem.getX()][coordEnem.getY()].vaciar();
					matriz[coordBala.getX()][coordBala.getY()].vaciar();

					// Eliminar bala y enemigo
					ListaBalas.getListaBalas().eliminarBala(j);
					ListaEnemigos.getListaEnemigos().eliminarEnemigo(i);

					break; // Si el enemigo ya ha sido eliminado no hay que seguir
				}
			}
		}
	}

	public boolean quedanEnemigos() {
		return ListaEnemigos.getListaEnemigos().getNumEnemigos() > 0;
	}

	public boolean enemigoGana() {
		return ListaEnemigos.getListaEnemigos().enemigoHaLLegadoAbajo();
	}

	public boolean quedanNaves() {
		return ListaNaves.getListaNaves().getNumNaves() > 0;
	}


}