package model;

import java.awt.*;

public class ListaNaves {
    private Nave[] listaNaves;
    private int numNaves;
    private final int MAX_NAVES = 3;

    public ListaNaves() {
        this.listaNaves = new Nave[MAX_NAVES];
        this.numNaves = 0;
    }

    public void anadirNave(Color pColor, Coordenada pCoord) {
        if (numNaves < MAX_NAVES) {
            Nave nave = new Nave(pColor, pCoord);
            listaNaves[numNaves] = nave;
            numNaves++;
        }
    }

    public Color getColorNave(int i) {
        if (i >= 0 && i < numNaves) {
            return listaNaves[i].getColor();
        }
        return null; // o lanzar una excepción
    }

    public Coordenada getCoordNave(int idNave) {
        if (idNave >= 0 && idNave < numNaves) {
            return listaNaves[idNave].getCoord();
        }
        return null; // o lanzar una excepción
    }

    public void setCoordNave(int idNave, int cX, int cY) {
        if (idNave >= 0 && idNave < numNaves) {
            listaNaves[idNave].setCoord(cX, cY);
        }
    }
}
