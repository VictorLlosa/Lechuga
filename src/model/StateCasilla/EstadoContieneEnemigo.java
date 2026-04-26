package model.StateCasilla;

import model.ColisionEvent;
import model.Tipos.TipoEntidad;

import java.util.ArrayList;

public class EstadoContieneEnemigo implements EstadoCasilla {
    @Override
    public ArrayList<ColisionEvent> colision(Casilla pCasilla, TipoEntidad pEnt, int pIdEntidad) {
        ArrayList<ColisionEvent> eventos = new ArrayList<>();
        switch (pEnt) {
            case TipoEntidad.bala://Tanto con bala y nave pasa lo mismo
            case TipoEntidad.nave:
                eventos.add(new ColisionEvent(pCasilla.getEntidad(), pCasilla.getId())) ;
                eventos.add(new ColisionEvent(pEnt, pIdEntidad));
            break;
            default:
                eventos = null;
        }
        return eventos;
    }

    /**
     * Contienene el "changeState" de todas las entidades posibles
     * @param casilla
     * @param pEnt
     * @param pIdEntidad
     */
    @Override
    public void ponerEntidad(Casilla casilla, TipoEntidad pEnt, int pIdEntidad) {
        switch (pEnt){
            case nave:
                casilla.cambiarDeEstadoA(new EstadoContieneNave());
                break;
            case enemigo:
            case boss1:
            case boss2:
                casilla.cambiarDeEstadoA(new EstadoContieneEnemigo());
                break;
            case bala:
                casilla.cambiarDeEstadoA(new EstadoContieneBala());
                break;

        }
        casilla.cambiarObjeto(pEnt, pIdEntidad);

    }
}
