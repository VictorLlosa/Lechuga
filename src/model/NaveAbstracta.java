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
    private boolean muerta;

    protected NaveAbstracta(int pX, int pY){
        this.disparo = new DisparoPixel();
        id++;
        coord = new Coordenada(pX, pY);
        cannon = new Coordenada(pX, pY);
        listaBalas = new ListaBalas();
        muerta = false;

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

    /**
     * para disparar, tenemos que actualizar el cannon antes. hacemos "disparo.disparar(coords del disparo) y anadimos la Bala a la Lista de Balas.
     * @return
     */
    public Coordenada disparar(){
        Coordenada coordBala = disparo.disparar(coord.getX(), coord.getY());
        listaBalas.anadirBala(coordBala);
        return coordBala;
    }

    public boolean tienesId(int idNave) {
        return this.id == idNave;
    }
    public boolean estasEn(int cX, int cY) {
        return coord.equals(new Coordenada(cX,cY));
    }

    public void changeStrategy(DisparoStrategy pSt){
        this.disparo = pSt;
    }

    /**
     * Llama a listaBalas.moverBalas()
     */
    public void moverBalas(){
        listaBalas.moverBalas();
    }

    public ArrayList<Coordenada> getCoordBalas() {
        return listaBalas.getCoordBalas();
    }

    public void borrarBalas() {
        listaBalas.borrarListaBalas();
    }

    public int getId() {
        return id;
    }

    public void matar() {
        muerta = true;
    }

    /**
     * Devuelve el valor del atributo "muerta"
     * @return
     */
    public boolean estaMuerta() {
        return muerta;
    }
}

