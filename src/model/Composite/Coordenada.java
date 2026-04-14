package model.Composite;

import model.Tipos.TipoEntidad;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface Coordenada {
    void actualizarCoord(int dx, int dy);

    Coordenada generarNuevaCoord(int dx, int dy);

    boolean estasEnIntervalo(int pX0, int pX1, int pY0, int pY1);

    /**
     * Se usa en MoverEnEspacio()
     * @return
     */
    boolean sePuedeMover();

    boolean moverEnEspacio(int dx, int dy, TipoEntidad pEnt, int pIdEnt);

    void borrar();
}
