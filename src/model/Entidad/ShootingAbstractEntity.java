package model.Entidad;

import model.CompositeCoordenada.Pixel;
import model.DisparoStrategy.DisparoStrategy;
import model.Formas.FormaAbstractShootingEntity;
import model.Tipos.TipoEntidad;

public abstract class ShootingAbstractEntity extends EntidadAbstracta {
    private DisparoStrategy disparo;
    private Pixel cannon;

    public ShootingAbstractEntity(DisparoStrategy pDisparo, TipoEntidad pTipoEntidad){
        super(pTipoEntidad);
        disparo = pDisparo;
    }
    /**
     * Le decimos a la estrategia actual que dispare. Si ha podido disparar, añadimo la bala a listaBalas.
     * @return El composite de la bala o null si no se ha podido disparar.
     */
    public abstract void disparar();

    protected void  setDisparo(DisparoStrategy pDisparo){
        disparo = pDisparo;
    }

    protected Pixel getCannon(){
        return cannon;
    }
    protected void inicializarCannon(int cX, int cY) {
        FormaAbstractShootingEntity forma = (FormaAbstractShootingEntity)getForma();
        cannon = forma.getCannon(cX, cY);
    }

    protected DisparoStrategy getDisparo(){
        return disparo;
    }

    public void changeDisparoStrategy(DisparoStrategy pStrat){
        disparo = pStrat;
    }
}
