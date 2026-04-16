package model.State;

import model.EventoEntidad;
import model.Tipos.TipoEntidad;

public class EstadoContieneBala implements EstadoCasilla {
    /**
     * Devuelve un evento indicando el objeto que habia en esta casilla que ha colisionado
     * @param pCasilla
     * @param pEnt
     * @return si se ha movido (true)
     */
    @Override
    public EventoEntidad colision(Casilla pCasilla, TipoEntidad pEnt){
        EventoEntidad evento;
        switch (pEnt) {
            case TipoEntidad.enemigo:
                evento = new EventoEntidad(pCasilla.getEntidad(), pCasilla.getId());
            break;
            //en caso de casilla vacia, nave y bala no se hace nada
            default:
                evento = null;
        }
        return evento;
    }

    @Override
    public void ponerEntidad(Casilla pCasilla, TipoEntidad pEnt, int pIdEntidad) {
        switch (pEnt){
            case TipoEntidad.vacio:
                break;
            case TipoEntidad.nave:
                pCasilla.cambiarDeEstadoA(new EstadoContieneNave());
                pCasilla.cambiarObjeto(pEnt, pIdEntidad);
                break;
            case TipoEntidad.bala:
                pCasilla.cambiarDeEstadoA(new EstadoContieneBala());
                pCasilla.cambiarObjeto(pEnt, pIdEntidad);
                break;
            case TipoEntidad.enemigo://no va a ocurrir pero se pone
                pCasilla.cambiarDeEstadoA(new EstadoContieneEnemigo());
                pCasilla.cambiarObjeto(pEnt, pIdEntidad);
                break;
        }

    }
}
