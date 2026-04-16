package model.Enemigos;

import model.Composite.CompositeCoordenada;
import model.Composite.Coordenada;
import model.GeneradorId;
import model.Tipos.TipoEntidad;

public abstract class EnemigoAbstracto{

    private Coordenada coord;
    private int id; // ID único de cada instancia

    public EnemigoAbstracto () {
        id = GeneradorId.getGeneradorId().nextId();
    }

    public boolean estaEn(Coordenada pCoord){
        return coord.equals(pCoord);
    }

    public Integer getId() {
        return id;
    }

    protected void setCoord(Coordenada pCoordForma) {
        this.coord = pCoordForma;
    }

    /**
     * Se usa para mover los enemigos para abajo cada cierto tiempo. Si el movimiento es valido, actualizar la posicion
     */
    public boolean moverEnEspacio() {
        return coord.moverEnEspacio(0,1, TipoEntidad.enemigo, this.id);
    }
    public void ponerEnEspacio(){
        coord.colocarEnEspacio(TipoEntidad.enemigo, this.id);
    }

    public void borrar() {
        coord.borrar();
    }

    public boolean haLLegadoAbajo() {
        return coord.abajo();
    }
}

