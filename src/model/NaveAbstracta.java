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
    private static int contadorId = 0; // Contador global para IDs
    private int id; // ID único de cada instancia
    private DisparoStrategy disparo;
    private ListaBalas listaBalas;
    private boolean muerta;

    protected NaveAbstracta(int pX, int pY){
        this.disparo = new DisparoPixel();
        this.id = contadorId++; // Asignar ID único y luego incrementar el contador
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
        return id; // Devolver el ID único de la instancia
    }

    public void matar() {
        muerta = true;
    }

    /**
     *
     * @return Devuelve el valor del atributo "muerta"
     */
    public boolean estaMuerta() {
        return muerta;
    }

    public void reiniciarContadorNaves() {
        contadorId = 0; // Reiniciar el contador global
    }

    /**
     * Solo elimina la bala si la nave la tiene. Lo usamos en ListaNaves (eliminarbala por coord)
     * @param cX
     * @param cY
     */
    public void eliminarBalaPorCoord(int cX,int cY){
        if(tieneBalaEnCoord(cX,cY)) listaBalas.eliminarBalaEn(cX, cY);
    }

    /**
     * Se usa en eliminarBalaPorCoord(x,y)
     * @param cX
     * @param cY
     * @return
     */
    public boolean tieneBalaEnCoord(int cX, int cY){
       return listaBalas.existeBalaEn(cX, cY);
    }

}