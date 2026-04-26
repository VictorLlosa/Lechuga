package model.StateCasilla;

import model.ColisionEvent;
import model.Tipos.TipoEntidad;

import java.util.ArrayList;

public class EstadoContieneNave implements EstadoCasilla {
    /**
     * Recibimos la entidad
     *
     * @param pCasilla
     * @param pEnt
     */
    @Override
    public ArrayList<ColisionEvent> colision(Casilla pCasilla, TipoEntidad pEnt, int pIdEntidad ) {
        ArrayList<ColisionEvent> eventos = new ArrayList<>();
        switch (pEnt) {
            case TipoEntidad.nave: //La nave con otra nave colisiona, pero no se elimina ninguna
                eventos.add(new ColisionEvent());
                eventos.add(new ColisionEvent());
            break;
            default:
                if(pEnt.esEnemigo()){
                    eventos.add(new ColisionEvent(pCasilla.getEntidad(), pCasilla.getId()));
                    eventos.add(new ColisionEvent(pEnt, pIdEntidad));
                    pCasilla.cambiarDeEstadoA(new EstadoCasillaVacia());
                    pCasilla.cambiarObjeto(TipoEntidad.vacio,-1);
                }else{
                    eventos = null;
                }
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
