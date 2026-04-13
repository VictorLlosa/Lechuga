package model.Composite;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface Coordenada {
    void actualizarCoord(int dx, int dy);

    Coordenada generarNuevaCoord(int dx, int dy);

    /**
     * Lo usa el Composite, para devolver su lista de pixeles
     * @return
     */
    ArrayList<Pixel> getPixeles();

    boolean estasEnIntervalo(int pX0, int pX1, int pY0, int pY1);

    boolean validarMovimiento(int dx, int dy);
}
