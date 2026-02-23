package model;

public class Coordenada {
	private int x, y;
	
	public Coordenada(int pX, int pY) {
		x = pX;
		y = pY;
	}
	
	public int getX() {
		return x;
	}
	
	
	public int getY() {
		return y;
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
		Coordenada otraCoord = (Coordenada) o;
		return x == otraCoord.x && y == otraCoord.y;
	}

	public boolean debajo(Coordenada pCoord) {
		return (this.x == pCoord.getX()) && ((this.y == pCoord.getY()+1));
	}
}
