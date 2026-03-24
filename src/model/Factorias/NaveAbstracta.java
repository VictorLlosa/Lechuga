package model.Factorias;

import model.Coordenada;

import java.awt.*;

/**
 *
 */
public abstract class NaveAbstracta {

    private Coordenada coord;
    private Coordenada[] arrayCoord;
    private String tipo;


    protected NaveAbstracta(Color pColor, Coordenada pCoord){

        coord = pCoord;
    }

    protected void setCoord(int pX, int pY) {
        coord.setCoord(pX,pY);
    }

    protected Coordenada getCoord() {
        return coord;
    }

}
