package model.State;

import model.Tipos.TipoEntidad;

public class EstadoCasillaVacia implements EstadoCasilla {
    @Override
    public boolean ponerEntidad(Casilla pCasilla, TipoEntidad pEnt, int pIdEntidad){
        switch(pEnt){
            case enemigo:
                pCasilla.setIdEntidad(pIdEntidad);
                pCasilla.cambiarDeEstadoA(new EstadoContieneEnemigo());
                pCasilla.cambiarObjeto(pEnt);
                break;
            case nave:
                pCasilla.setIdEntidad(pIdEntidad);
                pCasilla.cambiarDeEstadoA(new EstadoContieneNave());
                pCasilla.cambiarObjeto(pEnt);
                break;
            case bala:
                pCasilla.setIdEntidad(pIdEntidad);
                pCasilla.cambiarDeEstadoA(new EstadoContieneBala());
                pCasilla.cambiarObjeto(pEnt);
                break;
            default:
                break;
        }
        return true;
    }
}
