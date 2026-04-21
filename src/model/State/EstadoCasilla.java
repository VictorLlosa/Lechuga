package model.State;

import model.ColisionEvent;
import model.Tipos.TipoEntidad;

public interface EstadoCasilla {
    ColisionEvent colision(Casilla pCasilla, TipoEntidad pEnt);

    void ponerEntidad(Casilla pCasilla, TipoEntidad pEnt, int pIdEntidad);
}
