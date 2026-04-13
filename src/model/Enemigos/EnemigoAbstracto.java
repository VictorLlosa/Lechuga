package model.Enemigos;

import model.Composite.CompositeCoordenada;
import model.Composite.Coordenada;

import java.util.Observer;

public abstract class EnemigoAbstracto{

    private CompositeCoordenada coord;
    private boolean muerto;
    private static int contadorId = 0; // Contador global para IDs
    private int id; // ID único de cada instancia

    public EnemigoAbstracto () {
        muerto = false;
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

    /**
     * Devuelve el valor del atributo "muerto"
     * @return
     */
    public boolean estaMuerto(){
        return this.muerto;
    }

    public void matar() {
        muerto = true;
    }

    public Integer getId() {
        return id;
    }

    protected void setCoord(CompositeCoordenada pCoordForma) {
        this.coord = pCoordForma;
    }
}

