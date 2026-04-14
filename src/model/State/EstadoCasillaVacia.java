package model.State;

import model.Tipos.TipoEntidad;

public class EstadoCasillaVacia implements EstadoCasilla {
    @Override
    public void ponerEntidad(Casilla pCasilla, TipoEntidad pEnt, int pIdEntidad){
        switch(pEnt){
            case enemigo:
                pCasilla.setIdEntidad(pIdEntidad);
                pCasilla.cambiarDeEstadoA(new EstadoContieneEnemigo());
                break;
            case nave:
                pCasilla.setIdEntidad(pIdEntidad);
                pCasilla.cambiarDeEstadoA(new EstadoContieneNave());
                break;
            case bala:
                pCasilla.setIdEntidad(pIdEntidad);
                pCasilla.cambiarDeEstadoA(new EstadoContieneBala());
                break;
            default:
                break;
        }
    }
}
