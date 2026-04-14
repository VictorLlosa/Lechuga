package model.State;

import model.Tipos.TipoEntidad;

public interface EstadoCasilla {
    boolean ponerEntidad(Casilla pCasilla, TipoEntidad pEnt, int pIdEntidad);
}
