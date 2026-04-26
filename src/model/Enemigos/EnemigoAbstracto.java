package model.Enemigos;

import model.CompositeCoordenada.Coordenada;
import model.GeneradorId;
import model.Tipos.TipoEntidad;

public abstract class EnemigoAbstracto{

    private Coordenada coord;
    private final int id; // ID único de cada instancia
    private boolean muerto = false;

    public EnemigoAbstracto () {
        id = GeneradorId.getGeneradorId().nextId();
    }

    int getId() {
        return id;
    }

    Coordenada getCoord() {return coord;}


    protected void setCoord(Coordenada pCoordForma) {
        this.coord = pCoordForma;
    }

    /**
     * Se usa para mover los enemigos para abajo cada cierto tiempo. Si el movimiento es valido, actualizar la posicion
     */
    public void moverEnEspacio() {
        coord.moverEnEspacio(0,1, TipoEntidad.enemigo, this.id);
    }
    public void ponerEnEspacio(){
        coord.colocarEnEspacio(TipoEntidad.enemigo, this.id);
    }

    public void borrar(){
        coord.borrar();
    }

    public boolean haLLegadoAbajo() {
        return !coord.sePuedeMover(0, 1);
    }

    /**
     * Lo usamos en el update de ListaEnemigos
     */
    public void lethalHit(){
        this.muerto = true;
    }
    public boolean estaMuerto(){
        return this.muerto;
    }
    public void matar(){
        this.muerto = true;
    }
}

