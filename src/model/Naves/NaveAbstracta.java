package model.Naves;

import model.Balas.BalaAbstracta;
import model.Composite.CompositeCoordenada;
import model.Composite.Coordenada;
import model.Composite.Pixel;
import model.Entidad;
import model.Espacio;
import model.Balas.ListaBalas;
import model.Strategy.*;
import model.Tipos.TipoEntidad;

import java.util.Observable;

/**
 * id Empieza en 0, es un atributo autoincremental
 */
public abstract class NaveAbstracta extends Entidad {

    private CompositeCoordenada coord;
    private Pixel cannon;
    private static int contadorId = 0; // Contador global para IDs
    private int id; // ID único de cada instancia
    private DisparoStrategy disparo;
    private DisparoStrategy[] strategies;
    private int stratAct;
    private ListaBalas listaBalas;
    private boolean muerta;

    protected NaveAbstracta(){
        this.id = contadorId++; // Asignar ID único y luego incrementar el contador
        listaBalas = new ListaBalas();
        stratAct = 0;
        disparo = strategies[stratAct];
        muerta = false;
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
        BalaAbstracta bala = disparo.disparar(cannon);
        boolean puesto = Espacio.getEspacio().colocarEntidad(bala.getCoord(), TipoEntidad.bala);
        if(puesto) listaBalas.anadirBala(bala);
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
    public void moverBalas(){
        listaBalas.moverBalas();
    }

    public CompositeCoordenada getCoordBalas() { return listaBalas.getCoordBalas();}

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
     * ListaNaves llama a este metodo cuando se pulsa un boton para mover la nave.
     * @param dx
     * @param dy
     */
    public void moverNave(int dx, int dy) {
        if(coord.validarMovimiento(dx,dy)) {//Si no se puede mover no hace nada
            coord.moverEnEspacio(dx,dy, TipoEntidad.nave);
            this.cannon.actualizarCoord(dx,dy);
        }

    }

    public CompositeCoordenada getForma() {
        return coord;
    }

    protected void setStrategies(DisparoStrategy[] pStrategies) {
        this.strategies = pStrategies;
    }

    protected void setCoord(CompositeCoordenada pCoordForma) {
        this.coord = pCoordForma;
    }

    protected void setCannon(Pixel pCoordCannon) {
        this.cannon = pCoordCannon;
    }
}