package model.Naves;

import model.Balas.BalaAbstracta;
import model.Balas.ListaBalas;
import model.CompositeCoordenada.Coordenada;
import model.CompositeCoordenada.Pixel;
import model.Espacio;
import model.Strategy.DisparoStrategy;
import model.Tipos.TipoEntidad;

public class NaveAbstracta {
    private Coordenada coord;
    private Pixel cannon;
    private int id; // ID único de cada instancia
    private DisparoStrategy disparo;
    private DisparoStrategy[] strategies;
    private int stratAct;
    private ListaBalas listaBalas;
    private boolean muerta = false;

    protected NaveAbstracta(){
        //TODO: id = GeneradorId.getGeneradorId().nextId();
        id = 0;
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
        return this.id == idNave;
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


    /**
     * ListaNaves llama a este metodo cuando se pulsa un boton para mover la nave.
     * Si la nave se ha intentado mover fuera de Espacio, no se actualiza cannon
     * @param dx
     * @param dy
     */
    public void moverNave(int dx, int dy) {
        if(!coord.sePuedeMover(dx, dy)) return; //Si la nave se intenta salir no se mueve pero no se borra
        coord.moverEnEspacio(dx,dy, TipoEntidad.nave, this.id);
        this.cannon.actualizarCoord(dx,dy);
    }



    /**
     * Metodo que llama ListaNaves el cual mueve la entidad por el espacio
     */
    public void ponerEnEspacio(){
        coord.colocarEnEspacio(TipoEntidad.nave, this.id);
    }

    public void borrarNave(){
        coord.borrar();
    }
    public Coordenada getForma() {
        return coord;
    }

    protected void setStrategies(DisparoStrategy[] pStrategies) {
        this.strategies = pStrategies;
        stratAct = 0;
        disparo = strategies[stratAct];
    }

    protected void setCoord(Coordenada pCoordForma) {
        this.coord = pCoordForma;
    }

    protected void setCannon(Pixel pCoordCannon) {
        this.cannon = pCoordCannon;
    }

    public ListaBalas getListaBalas() {
        return listaBalas;
    }
}
