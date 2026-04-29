package model.StateCasilla;

import model.ColisionEvent;
import model.Tipos.TipoEntidad;

import java.util.ArrayList;

public class EstadoContieneBala implements EstadoCasilla {
    /**
     * Devuelve un evento indicando el objeto que habia en esta casilla que ha colisionado. Le llama Espacio antes de mover una bala
     *
     * @param pCasilla
     * @param pEnt
     * @return si se ha movido (true)
     */
    @Override
    public ArrayList<ColisionEvent> colision(Casilla pCasilla, TipoEntidad pEnt, int pIdEntidad){
        ArrayList<ColisionEvent> eventos = new ArrayList<>();
        if(pEnt.esEnemigo() || pEnt == TipoEntidad.nave){
            eventos.add(new ColisionEvent(pCasilla.getEntidad(), pCasilla.getId()));
            eventos.add(new ColisionEvent(pEnt, pIdEntidad));
        }else{
            eventos = null;
        }
        return eventos;
    }

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
