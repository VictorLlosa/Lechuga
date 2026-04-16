package model.State;

import model.EventoEntidad;
import model.Tipos.TipoEntidad;

public interface EstadoCasilla {
    EventoEntidad colision(Casilla pCasilla, TipoEntidad pEnt);

    void ponerEntidad(Casilla pCasilla, TipoEntidad pEnt, int pIdEntidad);
}
