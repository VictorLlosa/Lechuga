package model;

import model.Strategy.*;

import java.util.ArrayList;
import java.util.Observable;

/**
 * id Empieza en 0, es un atributo autoincremental
 */
public abstract class NaveAbstracta extends Observable {

    private Coordenada coord;
    private  Coordenada cannon;
    private static int id= -1;
    private DisparoStrategy disparo;
    private ListaBalas listaBalas;

    protected NaveAbstracta(){
        this.disparo = new DisparoPixel();
        this.id++;
        listaBalas = new ListaBalas();
    }

    protected void setCannon(int pX, int pY) {
        cannon.setCoord(pX,pY);
    }

    protected void setCoord(int pX, int pY) {
        coord.setCoord(pX,pY);
    }

    protected Coordenada getCoord() {
        return coord;
    }

    public Coordenada disparar(){
        return disparo.disparar(cannon.getX(), cannon.getY());
    }

    public boolean tienesId(int idNave) {
        return this.id == idNave;
    }

    public void changeStrategy(DisparoStrategy pSt){
        this.disparo = pSt;
    }

    public void moverBalas(){
        int num = listaBalas.getNumBalas();
        for (int i = 0; i < num; i++) {
            Coordenada coordBala = listaBalas.getCoordBala(i);
        }
    }

    public ArrayList<Coordenada> getCoordBalas() {
        return listaBalas.getCoordBalas();
    }

    public void borrarBala() {
        //TODO
    }

}
