package model.Composite;

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
	public ArrayList<Pixel> getPixeles() {
		ArrayList<Pixel> pixel = new ArrayList<Pixel>();
		pixel.add(this);
		return pixel;
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

	public void moverEnEspacio(int dx, int dy, Entidad pEnt){

	}
}
