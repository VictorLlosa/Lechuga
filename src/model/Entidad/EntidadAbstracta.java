package model.Entidad;

import model.CompositeCoordenada.Coordenada;
import model.GeneradorId;

public abstract class EntidadAbstracta {
    private Coordenada coord;
    private final int id;
    private boolean muerta = false;

    protected EntidadAbstracta() {
        this.id = GeneradorId.getGeneradorId().nextId();
    }

    public int getId() {
        return id;
    }

    public void matar() {
        muerta = true;
    }

    public boolean estaMuerta() {
        return muerta;
    }

    public void borrar() {
        coord.borrar();
    }

    protected Coordenada getCoord() {
        return coord;
    }

    protected void setCoord(Coordenada coord) {
        this.coord = coord;
    }

    public abstract void ponerEnEspacio();

}
