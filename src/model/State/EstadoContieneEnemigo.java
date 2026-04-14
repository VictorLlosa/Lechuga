package model.State;

import model.EventoEntidad;
import model.Tipos.TipoEntidad;

public class EstadoContieneEnemigo implements EstadoCasilla {
    @Override
    public boolean ponerEntidad(Casilla pCasilla, TipoEntidad pEnt, int pIdEntidad) {
        boolean colision;
        switch (pEnt) {
            case TipoEntidad.bala://Tanto con bala y nave pasa lo mismo
            case TipoEntidad.nave:
                int idEnemigo = pCasilla.getId();
                pCasilla.cambiarDeEstadoA(new EstadoCasillaVacia());
                pCasilla.setIdEntidad(-1);
                EventoEntidad[] arg = {new EventoEntidad(TipoEntidad.enemigo, idEnemigo),
                                       new EventoEntidad(pEnt, pIdEntidad),
                                        new EventoEntidad(pEnt)
                                       };
                pCasilla.notificarObservers(arg);
                colision = true;
            default:
                colision=false;
        }
        return !colision;
    }
}
