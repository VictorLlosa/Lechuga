package model.Entidad.Balas;

import model.CompositeCoordenada.Coordenada;
import model.Entidad.EntidadAbstracta;
import model.GeneradorId;
import model.MoverStrategy.MoverArriba;
import model.MoverStrategy.MoverStrategy;
import model.Tipos.TipoEntidad;

public abstract class BalaAbstracta extends EntidadAbstracta {

    MoverStrategy movimiento;

    protected BalaAbstracta(){
        movimiento = new MoverArriba();
    }
    public void moverEnEspacio() {
        movimiento.mover(TipoEntidad.bala, getId(), getCoord());
    }


    public void ponerEnEspacio() {
        getCoord().colocarEnEspacio(TipoEntidad.bala, getId());
    }

    public boolean estaFuera() {
        return !getCoord().sePuedeMover(0, -1);
    }
}

