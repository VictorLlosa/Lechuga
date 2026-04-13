package model.Enemigos;

import model.Composite.CompositeCoordenada;
import model.Composite.Coordenada;
import model.Espacio;
import model.Tipos.TipoEntidad;

import java.util.Observer;

public abstract class EnemigoAbstracto{

    private CompositeCoordenada coord;
    private static int contadorId = 0; // Contador global para IDs
    private int id; // ID único de cada instancia

    public EnemigoAbstracto () {
        id = contadorId++;
    }

    public void actualizarPos() {
        coord.actualizarCoord(0, 1);
    }

    public Coordenada getCoord() {
        return coord;
    }

    public boolean estaEn(Coordenada pCoord){
        return coord.equals(pCoord);
    }

    public Integer getId() {
        return id;
    }

    protected void setCoord(CompositeCoordenada pCoordForma) {
        this.coord = pCoordForma;
    }

    /**
     * Se usa para mover los enemigos para abajo cada cierto tiempo. Si el movimiento es valido, actualizar la posicion
     */
    public boolean moverEnEspacio() {
        return coord.moverEnEspacio(0,1, TipoEntidad.alien);
    }
}

