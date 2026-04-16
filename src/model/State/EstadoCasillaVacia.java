package model.State;

import model.EventoEntidad;
import model.Tipos.TipoEntidad;

public class EstadoCasillaVacia implements EstadoCasilla {
    @Override
    public EventoEntidad colision(Casilla pCasilla, TipoEntidad pEnt){
        return null;
    }

    @Override
    public void ponerEntidad(Casilla pCasilla, TipoEntidad pEnt, int pIdEntidad) {
        switch (pEnt){
            case TipoEntidad.vacio:
                pCasilla.cambiarDeEstadoA(new EstadoCasillaVacia());
                break;
            case TipoEntidad.nave:
                pCasilla.cambiarDeEstadoA(new EstadoContieneNave());
                break;
            case TipoEntidad.bala:
                pCasilla.cambiarDeEstadoA(new EstadoContieneBala());
                break;
            case TipoEntidad.enemigo:
                pCasilla.cambiarDeEstadoA(new EstadoContieneEnemigo());
                break;
        }
        pCasilla.cambiarObjeto(pEnt, pIdEntidad);
    }
}
