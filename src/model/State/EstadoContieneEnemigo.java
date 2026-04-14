package model.State;

import model.Tipos.TipoEntidad;

public class EstadoContieneEnemigo implements EstadoCasilla {
    @Override
    public void ponerEntidad(Casilla pCasilla, TipoEntidad pEnt, int pIdEntidad) {
        switch (pEnt) {
            case bala:
                pCasilla.cambiarDeEstadoA(new EstadoCasillaVacia()); //colisión
                pCasilla.notificarObservers(TipoEntidad pEnt);
                break;
            default:
                break;
        }
    }
}
