package model.StateCasilla;

import model.ColisionEvent;
import model.Tipos.TipoEntidad;

import java.util.ArrayList;

public class EstadoCasillaVacia implements EstadoCasilla {
    @Override
    public ArrayList<ColisionEvent> colision(Casilla pCasilla, TipoEntidad pEnt, int pIdEntidad){
        return null;
    }

    @Override
    public void ponerEntidad(Casilla casilla, TipoEntidad pEnt, int pIdEntidad) {
        switch (pEnt){
            case nave:
                casilla.cambiarDeEstadoA(new EstadoContieneNave());
                break;
            case enemigo:
            case boss1:
            case boss2:
                casilla.cambiarDeEstadoA(new EstadoContieneEnemigo());
                break;
            case bala:
                casilla.cambiarDeEstadoA(new EstadoContieneBala());
            break;

        }
        casilla.cambiarObjeto(pEnt, pIdEntidad);

    }

}
