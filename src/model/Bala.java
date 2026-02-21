package model;

public class Bala {
    private Coordenada coord;
    private int idNave;

    public Bala(int idNave, Coordenada coord) {
        this.idNave = idNave;
        this.coord = coord;
    }

    public Coordenada getCoord() {
        if (coord == null) return null;
        // Devolver una copia para evitar que terceros modifiquen la coordenada directamente
        return new Coordenada(coord.getX(), coord.getY());
    }

    public void setCoord(int cX, int cY) {
        this.coord.setCoord(cX, cY);
    }

    public void actualizarPos() {
        coord.setCoord(coord.getX(), coord.getY() - 1);
    }

    public int getIdNave() {
        return idNave;
    }
}
