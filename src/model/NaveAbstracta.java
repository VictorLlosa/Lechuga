package model;

import model.Balas.BalaAbstracta;
import model.Composite.CompositeCoordenada;
import model.Composite.Coordenada;
import model.Composite.Pixel;
import model.Strategy.*;

import java.util.Observable;

/**
 * id Empieza en 0, es un atributo autoincremental
 */
public abstract class NaveAbstracta extends Observable {

    private CompositeCoordenada coord;
    private Pixel cannon;
    private static int contadorId = 0; // Contador global para IDs
    private int id; // ID único de cada instancia
    private DisparoStrategy disparo;
    private DisparoStrategy[] strategies;
    private int stratAct;
    private ListaBalas listaBalas;
    private boolean muerta;

    protected NaveAbstracta(int pX, int pY, DisparoStrategy[] pStrategies){
        this.id = contadorId++; // Asignar ID único y luego incrementar el contador
        coord = new CompositeCoordenada();
        crearForma(pX, pY);
        listaBalas = new ListaBalas();
        strategies = pStrategies;
        stratAct = 0;
        disparo = strategies[stratAct];
        muerta = false;
    }

    /**
     * Entra las coordenadas del centro
     * @param cX
     * @param cY
     */
    private void crearForma(int cX, int cY) {
        coord.addComponent(new Pixel(cX,cY)); //centro
        coord.addComponent(new Pixel(cX,cY-1)); //arriba (tb es el cannon)
        cannon = new Pixel(cX,cY-1); //cannon de la nave
        coord.addComponent(new Pixel(cX-1,cY)); //izq
        coord.addComponent(new Pixel(cX+1,cY)); //derecha
    }

    protected CompositeCoordenada getCoord() {
        return coord;
    }

    protected Pixel getCannon() {
        return cannon;
    }

    /**
     * Le decimos a la estrategia actual que dispare. Si ha podido disparar, añadimo la bala a listaBalas.
     * @return El composite de la bala o null si no se ha podido disparar.
     */
    public void disparar(){
        BalaAbstracta bala = disparo.disparar(cannon.getX(), cannon.getY());
        Espacio.getEspacio().colocarEntidad(bala.getCoord(),Entidad.bala);
        listaBalas.anadirBala(bala);
        bala.getCoord();
    }

    public boolean tienesId(int idNave) {
        return this.id == idNave;
    }

    public boolean estasEn(Coordenada pCoord) {
        return coord.equals(pCoord);
    }

    public void changeStrategy(){
        this.stratAct ++;
        if(stratAct > strategies.length -1 ) stratAct = 0;
        this.disparo = strategies[stratAct];
    }

    /**
     * Llama a listaBalas.moverBalas()
     */
    public void moverBalas(){listaBalas.moverBalas();}

    public Coordenada getCoordBalas() { return listaBalas.getCoordBalas();}

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
     *
     */
    public void eliminarBalaPorCoord(Coordenada pCoord){
        if(tieneBalaEnCoord(pCoord)) listaBalas.eliminarBalaEn(pCoord);
    }

    /**
     * Se usa en eliminarBalaPorCoord(x,y)
     * @return
     */
    public boolean tieneBalaEnCoord(Coordenada pCoord){
       return listaBalas.existeBalaEn(pCoord);
    }

    /**
     * Controlador llama a este metodo cuando se pulsa un boton para mover la nave.
     * WIP ahora el CompositeCoordenada debe llamar a Espacio
     * @param dx
     * @param dy
     */
    public void actualizarCoord(int dx, int dy) {
        coord.moverEnEspacio(dx,dy,Entidad.nave);
    }

    public CompositeCoordenada getForma() {
        return coord;
    }
}