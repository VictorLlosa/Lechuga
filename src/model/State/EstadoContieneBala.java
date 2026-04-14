package model.State;

import model.EventoEntidad;
import model.Tipos.TipoEntidad;

public class EstadoContieneBala implements EstadoCasilla {
    /**
     * notificamos a los observers si  (ver notificarObservers en Casilla)
     * @param pCasilla
     * @param pEnt
     */
    @Override
    public void ponerEntidad(Casilla pCasilla, TipoEntidad pEnt, int pIdEntidad) {
        switch (pEnt) {
            case enemigo:
                pCasilla.cambiarDeEstadoA(new EstadoCasillaVacia());
                EventoEntidad[] arg = {new EventoEntidad(TipoEntidad.bala, pCasilla.getId()),new EventoEntidad(pEnt, pIdEntidad)};
                pCasilla.notificarObservers(arg);
                break;
            default:
                break;
        }
    }
}
