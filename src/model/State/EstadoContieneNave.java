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
    public EventoEntidad colision(Casilla pCasilla, TipoEntidad pEnt ) {
        EventoEntidad evento;
        switch (pEnt) {
            case TipoEntidad.enemigo:
                evento = new EventoEntidad(pCasilla.getEntidad(), pCasilla.getId());
                pCasilla.cambiarDeEstadoA(new EstadoCasillaVacia());
                pCasilla.cambiarObjeto(TipoEntidad.vacio,-1);
            break;
            case TipoEntidad.nave: //La nave con otra nave colisiona, pero no se elimina ninguna
                evento = new EventoEntidad();
            break;
            default:
                evento = null;
        }
        return evento;
    }
    public void ponerEntidad(Casilla pCasilla, TipoEntidad pEnt, int pIdEntidad) {
        switch (pEnt) {
            case TipoEntidad.vacio:
                break;
            case TipoEntidad.nave:
                pCasilla.cambiarDeEstadoA(new EstadoContieneNave());
                pCasilla.cambiarObjeto(pEnt, pIdEntidad);
                break;
            case TipoEntidad.bala: //Se dibuja la nave encima de la bala
                break;
            case TipoEntidad.enemigo: //no va a ocurrir pero se pone
                pCasilla.cambiarDeEstadoA(new EstadoContieneEnemigo());
                pCasilla.cambiarObjeto(pEnt, pIdEntidad);
                break;
        }
    }
}
