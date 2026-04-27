package model.Entidad.Naves;

import model.Entidad.Balas.BalaAbstracta;
import model.Entidad.Balas.ListaBalas;
import model.CompositeCoordenada.Coordenada;
import model.CompositeCoordenada.Pixel;
import model.Entidad.EntidadAbstracta;
import model.Espacio;
import model.Strategy.DisparoStrategy;
import model.Tipos.TipoEntidad;

public class NaveAbstracta extends EntidadAbstracta {
    private Pixel cannon;
    private DisparoStrategy disparo;
    private DisparoStrategy[] strategies;
    private int stratAct;
    private ListaBalas listaBalas;

    protected NaveAbstracta(){
        listaBalas = new ListaBalas();
        Espacio.getEspacio().addObserver(this.listaBalas);
    }

    /**
     * Le decimos a la estrategia actual que dispare. Si ha podido disparar, añadimo la bala a listaBalas.
     * @return El composite de la bala o null si no se ha podido disparar.
     */
    public void disparar(){
        BalaAbstracta bala = disparo.disparar(cannon.getX(), cannon.getY());
        if(bala != null) listaBalas.anadirBala(bala);
    }

    public boolean tienesId(int idNave) {
        return getId() == idNave;
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

    /**
     * Metodo usado por la lista de naves que sirve para borrar todas las balas de la lista de balas
     */
    public void borrarBalas() {
        listaBalas.borrarListaBalas();
    }
    public void borrarBalasMuertas(){
        listaBalas.borrarMuertos();
    }


    /**
     * ListaNaves llama a este metodo cuando se pulsa un boton para mover la nave.
     * Si la nave se ha intentado mover fuera de Espacio, no se actualiza cannon
     * @param dx
     * @param dy
     */
    public void moverNave(int dx, int dy) {
        if(!getCoord().sePuedeMover(dx, dy)) return; //Si la nave se intenta salir no se mueve pero no se borra
        getCoord().moverEnEspacio(dx,dy, TipoEntidad.nave, getId());
        this.cannon.actualizarCoord(dx,dy);
    }



    /**
     * Metodo que llama ListaNaves el cual mueve la entidad por el espacio
     */
    public void ponerEnEspacio(){
        getCoord().colocarEnEspacio(TipoEntidad.nave, getId());
    }

    public void borrarNave(){
        getCoord().borrar();
    }

    protected void setStrategies(DisparoStrategy[] pStrategies) {
        this.strategies = pStrategies;
        stratAct = 0;
        disparo = strategies[stratAct];
    }

    protected void setCannon(Pixel pCoordCannon) {
        this.cannon = pCoordCannon;
    }

}
