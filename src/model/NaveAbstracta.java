package model;

import Strategy.DisparoStrategy;

/**
 *
 */
public abstract class NaveAbstracta {

    private Coordenada coord;
    private Coordenada[] arrayCoord;
    private String tipo;
    private int id;
    private DisparoStrategy disparo;

    protected NaveAbstracta(String pTipo){
        this.tipo = pTipo;
        this.disparo = new disparoPixel();
    }

    protected void setCoord(int pX, int pY) {
        coord.setCoord(pX,pY);
    }

    protected Coordenada getCoord() {
        return coord;
    }

    protected void disparar(){
        disparo.disparar();
    }

    protected  String getTipo(){
        return this.tipo;
    }

    public boolean tienesId(int idNave) {
        return this.id == idNave;
    }
}
