package model.State;

import model.EventoEntidad;
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
            case TipoEntidad.enemigo:
                int idNave = pCasilla.getId();
                pCasilla.cambiarDeEstadoA(new EstadoCasillaVacia());
                pCasilla.setIdEntidad(-1);
                //Ha habido colision, se vacia la casilla y se notifica a las listas diciendo que eliminen la nave y el enemigo
                EventoEntidad[] arg = {new EventoEntidad(TipoEntidad.nave, idNave),new EventoEntidad(pEnt, pIdEntidad)};
                pCasilla.notificarObservers(arg);
            break;
            default:
                //Con bala enemigo y vacio no hay colision y queremos que
                // la nave siempre esté por encima, solo para que se vea esteticamente
                //Como ya habia una nave no hacemos nada.
                break;
        }
    }
}
