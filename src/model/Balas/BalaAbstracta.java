package model.Balas;

import model.CompositeCoordenada.Coordenada;
import model.GeneradorId;
import model.Tipos.TipoEntidad;

public abstract class BalaAbstracta {
    private Coordenada coord;
    private final int id; // ID único de cada instancia
    private boolean muerta = false;

    public BalaAbstracta() {
        id = GeneradorId.getGeneradorId().nextId();
    }

    /**
     * hace "coord.moverEnEspacio(0, -1, Entidad.bala);", que internamente ya mueve todas las coordenadas
     * que componen la Bala. Si no se ha podido mover, es que se ha salido la bala del tablero
     *
     * @return la nueva coordenada de la bala
     */
    public void moverEnEspacio() {
        coord.moverEnEspacio(0, -1, TipoEntidad.bala, this.id);
    }

    public void ponerEnEspacio() {
        coord.colocarEnEspacio(TipoEntidad.bala, this.id);
    }

    /**
     * Metodo llamado por borrarListaBalas para borrar la lista de balas
     */
    public void borrar() {
        coord.borrar();
    }

    public int getId() {
        return id;
    }

    protected void setCoord(Coordenada pCoordForma) {
        this.coord = pCoordForma;
    }

    public void matar() {
        this.muerta = true;
    }

    public boolean estaMuerta() {
        return this.muerta;
    }


    public boolean estaFuera() {
        return !coord.sePuedeMover(0, -1);
    }
}

