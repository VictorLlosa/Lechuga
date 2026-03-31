package model;

import model.Composite.CompositeCoordenada;
import model.Composite.Coordenada;
import model.Composite.Pixel;

import java.util.Observable;

public class Bala extends Observable {
    private Coordenada coord;

    public Bala(Coordenada coord) {
        this.coord = coord;
    }

    public Pixel getCoord() {
        if (coord == null) return null;
        // Devolver una copia para evitar que terceros modifiquen la coordenada directamente
        return new Pixel(coord.getX(), coord.getY());
    }

    /*
    public void setCoord(int cX, int cY) {
        this.coord.setCoord(cX, cY);
    }
    */

    public void actualizarPos() {
        coord.actualizarCoord(0, -1);
    }

}
