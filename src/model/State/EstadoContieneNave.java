package model.State;

import model.Tipos.TipoEnem;
import model.Tipos.TipoEntidad;

public class EstadoContieneNave implements EstadoCasilla {
    /**
     * Recibimos la entidad
     * @param pCasilla
     * @param pEnt
     */
    @Override
    public void ponerEntidad(Casilla pCasilla, TipoEntidad pEnt, int pIdEntidad) {
        switch (pEnt) {
            case enemigo:
                //colision!!
                pCasilla.cambiarDeEstadoA(new EstadoCasillaVacia());
                pCasilla.notificarObservers(TipoEntidad pEnt);
            break;
            default:
                break;
        }
    }
}
