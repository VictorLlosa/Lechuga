package model.StateCasilla;

import model.ColisionEvent;
import model.Tipos.TipoEntidad;

import java.util.ArrayList;

public interface EstadoCasilla {
    ArrayList<ColisionEvent> colision(Casilla pCasilla, TipoEntidad pEnt, int pIdEntidad);

    void ponerEntidad(Casilla casilla, TipoEntidad pEnt, int pIdEntidad);
}
