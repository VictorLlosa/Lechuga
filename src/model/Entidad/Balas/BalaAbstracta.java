package model.Entidad.Balas;

import model.Entidad.EntidadAbstracta;
import model.MoverStrategy.MoverStrategy;
import model.Tipos.TipoEnemigo;
import model.Tipos.TipoEntidad;

public abstract class BalaAbstracta extends EntidadAbstracta {

    protected MoverStrategy movimiento;

    protected BalaAbstracta(MoverStrategy pMovStrat){
        movimiento = pMovStrat;
        super(TipoEntidad.bala);
    }
    public void moverEnEspacio() {
        movimiento.mover(TipoEntidad.bala, getId(), getCoord());
    }


    public void ponerEnEspacio() {
        getCoord().colocarEnEspacio(TipoEntidad.bala, getId());
    }

    public boolean estaFuera() {
        return !getCoord().sePuedeMover(0, -1) || !getCoord().sePuedeMover(0, 1);
    }
}

