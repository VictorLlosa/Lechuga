package model.Entidad.Balas;

import model.CompositeCoordenada.Coordenada;
import model.Entidad.EntidadAbstracta;
import model.GeneradorId;
import model.Tipos.TipoEntidad;

public abstract class BalaAbstracta extends EntidadAbstracta {

    public void moverEnEspacio() {
        getCoord().moverEnEspacio(0, -1, TipoEntidad.bala, getId());
    }

    public void ponerEnEspacio() {
        getCoord().colocarEnEspacio(TipoEntidad.bala, getId());
    }

    public boolean estaFuera() {
        return !getCoord().sePuedeMover(0, -1);
    }
}

