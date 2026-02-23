package model;

import java.awt.*;
import java.util.ArrayList;

public class ListaNaves {
    private ArrayList<Nave> listaNaves;

    public ListaNaves() {
        this.listaNaves = new ArrayList<>();
    }

    public void anadirNave(Color pColor, Coordenada pCoord) {
        Nave nave = new Nave(pColor, pCoord);
        listaNaves.add(nave);
    }

    public Color getColorNave(int posNave) {
        if (posNave>= 0 && posNave < listaNaves.size()) {
            return listaNaves.get(posNave).getColor();
        }
        return null; // o lanzar una excepción
    }

    public Coordenada getCoordNave(int posNave) {
        if (posNave >= 0 && posNave < listaNaves.size()) {
            return listaNaves.get(posNave).getCoord();
        }
        return null; // o lanzar una excepción
    }

    public void setCoordNave(int posNave, int cX, int cY) {
        if (posNave >= 0 && posNave < listaNaves.size()) {
            listaNaves.get(posNave).setCoord(cX, cY);
        }
    }

    public int getNumNaves() {
        return listaNaves.size();
    }

    public void eliminarNave(int posNave) {
        if (posNave >= 0 && posNave < listaNaves.size()) {
            listaNaves.remove(posNave);
}
    }
    public void borrarListaNaves() {
       listaNaves.clear();
    }

}
