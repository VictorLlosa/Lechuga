package model;
import java.awt.Color;
import java.util.Observable;

public class Enemigo extends Observable {
    private final int idEnemigo;
    private static Coordenada coord;
    private Coordenada centro;
    private final Color color;


    public Enemigo (int pIdEnemigo, Coordenada pCoord) {
        idEnemigo = pIdEnemigo;
        coord = pCoord;
        color = Color.blue;
    }

    public Color getColor(){
        return this.color;
    }
    public int getId() {
        return idEnemigo;
    }
    public void setCoord (int pX, int pY) {
        this.coord.setCoord(pX, pY);
    }

    public void actualizarPos() {
        coord.setCoord(coord.getX(), coord.getY() + 1);
    }


    public Coordenada getCoord() {
        if (coord == null) return null;
        // Devolver una copia para evitar que terceros modifiquen la coordenada directamente
        return new Coordenada(coord.getX(), coord.getY());
    }

    public static boolean estaEn(int cX, int cY){
        return cX == coord.getX() && cY == coord.getY();
    }
}
