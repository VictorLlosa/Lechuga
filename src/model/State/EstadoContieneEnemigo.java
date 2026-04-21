package model.State;

import model.ColisionEvent;
import model.Tipos.TipoEntidad;

public class EstadoContieneEnemigo implements EstadoCasilla {
    @Override
    public ColisionEvent colision(Casilla pCasilla, TipoEntidad pEnt) {
        ColisionEvent evento;
        switch (pEnt) {
            case TipoEntidad.bala://Tanto con bala y nave pasa lo mismo
            case TipoEntidad.nave:
                evento = new ColisionEvent(pCasilla.getEntidad(), pCasilla.getId());
                pCasilla.cambiarDeEstadoA(new EstadoCasillaVacia());
                pCasilla.cambiarObjeto(TipoEntidad.vacio, -1);
            break;
            default:
                evento = null;
        }
        return evento;
    }
    public void ponerEntidad(Casilla pCasilla, TipoEntidad pEnt, int pIdEntidad) {
        switch (pEnt){
            case TipoEntidad.vacio:
                break;
            case TipoEntidad.nave: //no va a ocurrir
                pCasilla.cambiarDeEstadoA(new EstadoContieneNave());
                pCasilla.cambiarObjeto(pEnt, pIdEntidad);
                break;
            case TipoEntidad.bala: //no va a ocurrir
                pCasilla.cambiarDeEstadoA(new EstadoContieneBala());
                pCasilla.cambiarObjeto(pEnt, pIdEntidad);
                break;
            case TipoEntidad.enemigo: //no va a ocurrir pero se pone
                pCasilla.cambiarDeEstadoA(new EstadoContieneEnemigo());
                pCasilla.cambiarObjeto(pEnt, pIdEntidad);
                break;
        }

    }
}
