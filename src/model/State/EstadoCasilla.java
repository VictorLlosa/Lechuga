package model.State;

import model.Tipos.TipoEntidad;

public interface EstadoCasilla {
    void ponerEntidad(Casilla pCasilla, TipoEntidad pEnt, int pIdEntidad);
}
