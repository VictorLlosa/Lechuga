package model.Composite;

import model.Espacio;
import model.Tipos.TipoEntidad;

import java.security.spec.ECGenParameterSpec;
import java.util.ArrayList;
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
	public Coordenada generarNuevaCoord(int dx, int dy) {
		return new Pixel(x + dx, y + dy);
	}

	@Override
	public boolean estasEnIntervalo(int pX0, int pX1, int pY0, int pY1) {
		return x>=pX0 && x<=pX1 && y>=pY0 && y<=pY1;
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
	public boolean sePuedeMover() {
		return Espacio.getEspacio().esCoordenadaValida(x, y);
	}

	@Override
	public boolean moverEnEspacio(int dx, int dy, TipoEntidad pEnt, int pIdEnt) {
		boolean exito = Espacio.getEspacio().moverEntidad(x, y, x + dx, y + dy, pEnt, pIdEnt);
		if(exito) actualizarCoord(dx,dy);
		return exito;
	}

	@Override
	public void borrar(){
		Espacio.getEspacio().vaciarCasilla(x,y);
	}
}
