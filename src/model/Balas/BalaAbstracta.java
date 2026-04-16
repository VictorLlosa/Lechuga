package model.Balas;

import model.Composite.CompositeCoordenada;
import model.Composite.Coordenada;
import model.GeneradorId;
import model.Tipos.TipoEntidad;

import java.util.Observer;

public abstract class BalaAbstracta{
    private Coordenada coord;
    private final int id; // ID único de cada instancia

    public BalaAbstracta(Coordenada pCoordForma) {
        this.coord = pCoordForma;
        id = GeneradorId.getGeneradorId().nextId();
    }

    /**
     * hace "coord.moverEnEspacio(0, -1, Entidad.bala);", que internamente ya mueve todas las coordenadas
     * que componen la Bala. Si no se ha podido mover, es que se ha salido la bala del tablero
     * @return la nueva coordenada de la bala
     */
    public boolean moverEnEspacio() {
        return coord.moverEnEspacio(0, -1, TipoEntidad.bala, this.id);
    }

    public void ponerEnEspacio(){ coord.moverEnEspacio(0, 0, TipoEntidad.bala, this.id);}

    /**
     * Metodo llamado por borrarListaBalas para borrar la lista de balas
     */
    public void borrar() {
        coord.borrar();
    }

    public int getId() {
        return id;
    }
}
