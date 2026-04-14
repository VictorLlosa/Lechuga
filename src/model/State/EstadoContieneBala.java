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
    public boolean ponerEntidad(Casilla pCasilla, TipoEntidad pEnt, int pIdEntidad) {
        boolean colision;
        switch (pEnt) {
            case TipoEntidad.enemigo:
                pCasilla.cambiarDeEstadoA(new EstadoCasillaVacia());
                pCasilla.setIdEntidad(-1);
                EventoEntidad[] arg = {new EventoEntidad(TipoEntidad.bala, pCasilla.getId()),
                                        new EventoEntidad(pEnt, pIdEntidad),
                                        new EventoEntidad(pEnt)
                };
                pCasilla.notificarObservers(arg);
                colision = true;
            break;
            case TipoEntidad.nave://no hay colision pero si que pintamos la nave por encima, solo para que se vea esteticamente
                pCasilla.cambiarDeEstadoA(new EstadoContieneNave());
                pCasilla.cambiarObjeto(pEnt);
                pCasilla.setIdEntidad(pIdEntidad);
                colision = false;
            break;
            //en caso de casilla vacia y bala no se hace nada
            default:
                colision = false;
        }
        return !colision;
    }
}
