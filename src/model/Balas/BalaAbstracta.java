package model.Balas;

import model.Composite.CompositeCoordenada;
import model.Composite.Coordenada;
import model.Entidad;

import java.util.Observable;

public class BalaAbstracta extends Observable {
    private CompositeCoordenada coord;

    public BalaAbstracta(CompositeCoordenada coord) {
        this.coord = coord;
    }

    public CompositeCoordenada getCoord() {
      return coord;
    }

    /*
    public void setCoord(int cX, int cY) {
        this.coord.setCoord(cX, cY);
    }
    */

    /**
     * hace " coord.moverEnEspacio(0, -1, Entidad.bala);", que internamente ya mueve todas las coordenadas
     * que componen la Bala
     * @return la nueva coordenada de la bala
     */
    public Coordenada actualizarPos() {
        coord.moverEnEspacio(0, -1, Entidad.bala);
        return coord;
    }

    /**
     * La usamos en ExisteBalaEn y en findBala de ListaBalas
     * @param pCoord
     * @return true si la Bala esta en la coordenada
     */
    public boolean estasEn(Coordenada pCoord){
        return coord.equals(pCoord);
    }

}
