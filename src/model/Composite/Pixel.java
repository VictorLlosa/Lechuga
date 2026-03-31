package model.Composite;

public class Pixel implements Coordenada {
	private int x, y;
	
	public Pixel(int pX, int pY) {
		x = pX;
		y = pY;
	}

    public void setCoord(int pX, int pY) {
		x= pX;
		y= pY;
    }

    public void setY(int pY) {
		y = pY;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Pixel otraCoord = (Pixel) o;
		return x == otraCoord.x && y == otraCoord.y;
	}

	@Override
	public void actualizarCoord(int dx, int dy) {
		this.x = x + dx;
		this.y = y + dy;
	}

	@Override
	public boolean esPixel() {
		return true;
	}

	@Override
	public boolean estasEnIntervalo(int pX0, int pX1, int pY0, int pY1) {
		return x>=pX0 && x<=pX1 && y>=pY0 && y<=pY1;
	}
}
