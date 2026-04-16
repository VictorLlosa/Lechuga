package model.Composite;

import model.Espacio;
import model.Tipos.TipoEntidad;

import java.util.Objects;

public class Pixel implements Coordenada {
	private int x, y;
	
	public Pixel(int pX, int pY) {
		x = pX;
		y = pY;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}
    public void setCoord(int pX, int pY) {
		x= pX;
		y= pY;
    }
	@Override
	public void actualizarCoord(int dx, int dy) {
		this.x = x + dx;
		this.y = y + dy;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass())
			return false;

		Pixel pixel = (Pixel) o;

		return x == pixel.getX() && y == pixel.getY();
	}
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean sePuedeMover(int dx, int dy) {
		return Espacio.getEspacio().esCoordenadaValida(x+dx, y+dy);
	}

	@Override
	public boolean moverEnEspacio(int dx, int dy, TipoEntidad pEnt, int pIdEnt) {
		this.borrar();
		this.actualizarCoord(dx, dy);
		this.colocarEnEspacio(pEnt, pIdEnt);
		return true;
	}
	@Override
	public boolean colocarEnEspacio(TipoEntidad pEnt, int pIdEnt) {
		Espacio.getEspacio().colocarEntidad(x, y, pEnt, pIdEnt);
		return true;
	}

	@Override
	public boolean abajo() {
		return Espacio.getEspacio().abajo(y);
	}

	@Override
	public boolean colisiona(int dx, int dy, TipoEntidad pEnt, int pIdEnt) {
		return Espacio.getEspacio().colision(x+dx,y+dy, pEnt, pIdEnt);
	}

	@Override
	public void borrar(){
		if(Espacio.getEspacio().esCoordenadaValida(x,y)) Espacio.getEspacio().vaciarCasilla(x,y);
	}


}
