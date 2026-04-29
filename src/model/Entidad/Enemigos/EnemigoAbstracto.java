package model.Entidad.Enemigos;

import model.DisparoStrategy.DisparoStrategy;
import model.Entidad.ShootingAbstractEntity;
import model.MoverStrategy.MoverStrategy;

public abstract class EnemigoAbstracto extends ShootingAbstractEntity {


    private int vidas;
    private MoverStrategy movimiento;

    protected EnemigoAbstracto(int pVidas, DisparoStrategy pDisparo){
        vidas = pVidas;
        super(pDisparo);
    }

    public abstract void moverEnEspacio();


    public boolean haLLegadoAbajo() {
        return !getCoord().sePuedeMover(0, 1);
    }

    public void lethalHit(){
        vidas--;
        if (vidas == 0) matar();
    }

}