package model.State;

import model.EventoEntidad;
import model.Tipos.TipoEntidad;

public class EstadoContieneBala implements EstadoCasilla {
    /**
     * notificamos a los observers si  (ver notificarObservers en Casilla)
     * @param pCasilla
     * @param pEnt
     * @return si se ha movido sin colisiones (true)
     */
    @Override
    public void ponerEntidad(Casilla pCasilla, TipoEntidad pEnt, int pIdEntidad) {
        switch (pEnt) {
            case TipoEntidad.enemigo:
                pCasilla.cambiarDeEstadoA(new EstadoCasillaVacia());
                pCasilla.setIdEntidad(-1);
                EventoEntidad[] arg = {new EventoEntidad(TipoEntidad.bala, pCasilla.getId()),new EventoEntidad(pEnt, pIdEntidad)};
                pCasilla.notificarObservers(arg);
            case TipoEntidad.nave://no hay colision pero si que pintamos la nave por encima, solo para que se vea esteticamente
                pCasilla.cambiarDeEstadoA(new EstadoContieneNave());
                pCasilla.setIdEntidad(pIdEntidad);
            //en caso de casilla vacia y bala no se hace nada
            default:
        }
    }
}
