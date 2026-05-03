package model.Entidad.Naves;

import model.Entidad.Balas.BalaAbstracta;
import model.Entidad.Balas.ListaBalas;
import model.Entidad.ShootingAbstractEntity;
import model.DisparoStrategy.DisparoStrategy;
import model.MoverStrategy.MoverArriba;
import model.Tipos.TipoEntidad;

public class NaveAbstracta extends ShootingAbstractEntity {
    private DisparoStrategy[] strategies;
    private int stratAct;

    protected NaveAbstracta(DisparoStrategy pDisparo){
        super(pDisparo, TipoEntidad.nave);
    }

    @Override
    public void disparar() {
        BalaAbstracta bala = getDisparo().disparar(getCannon().getX(), getCannon().getY(), new MoverArriba());
        if(bala != null) ListaBalas.getListaBalas().anadirBala(bala);
    }


    public boolean tienesId(int idNave) {
        return getId() == idNave;
    }


    public void toggleStrategy(){
        this.stratAct ++;
        if(stratAct > strategies.length -1 ) stratAct = 0;
        changeDisparoStrategy(strategies[stratAct]);
    }


    /**
     * Metodo usado por la lista de naves que sirve para borrar todas las balas de la lista de balas
     */
    public void borrarBalas() {
        ListaBalas.getListaBalas().borrarListaBalas();
    }
    /**
     * ListaNaves llama a este metodo cuando se pulsa un boton para mover la nave.
     * Si la nave se ha intentado mover fuera de Espacio, no se actualiza cannon
     * @param dx
     * @param dy
     */
    public void moverNave(int dx, int dy) {
        if(getCoord().moverEnEspacio(dx,dy, TipoEntidad.nave, getId())) getCannon().actualizarCoord(dx,dy);
    }

    /**
     * Metodo que llama ListaNaves el cual mueve la entidad por el espacio
     */
    public void ponerEnEspacio(){
        getCoord().colocarEnEspacio(TipoEntidad.nave, getId());
    }

    @Override
    public void moverEnEspacio() {
    }

    public void borrarNave(){
        getCoord().borrar();
    }

    protected void setStrategies(DisparoStrategy[] pStrategies) {
        this.strategies = pStrategies;
        stratAct = 0;
        setDisparo(strategies[stratAct]);
    }



}
