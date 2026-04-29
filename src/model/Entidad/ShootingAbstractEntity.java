package model.Entidad;

import model.CompositeCoordenada.Pixel;
import model.DisparoStrategy.DisparoStrategy;
import model.Entidad.Balas.BalaAbstracta;
import model.Entidad.Balas.ListaBalas;
import model.Formas.FormaAbstractShootingEntity;
import model.Formas.FormaAbstracta;
import model.Formas.FormaNave;

public abstract class ShootingAbstractEntity extends EntidadAbstracta {
    private DisparoStrategy disparo;
    private Pixel cannon;

    public ShootingAbstractEntity(DisparoStrategy pDisparo){
        disparo = pDisparo;
    }
    /**
     * Le decimos a la estrategia actual que dispare. Si ha podido disparar, añadimo la bala a listaBalas.
     * @return El composite de la bala o null si no se ha podido disparar.
     */
    public void disparar(){
        BalaAbstracta bala = disparo.disparar(cannon.getX(), cannon.getY());
        if(bala != null) ListaBalas.getListaBalas().anadirBala(bala);
    }

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

    public void changeStrategy(DisparoStrategy pStrat){
        disparo = pStrat;
    }
}
