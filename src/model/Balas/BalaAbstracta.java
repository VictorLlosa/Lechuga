package model.Balas;

import model.Composite.CompositeCoordenada;
import model.Composite.Coordenada;
import model.Tipos.TipoEntidad;

import java.util.Observer;

public abstract class BalaAbstracta{
    private Coordenada coord;
    private static int contadorId = 0; // Contador global para IDs
    private int id; // ID único de cada instancia

    public BalaAbstracta(Coordenada pCoordForma) {
        this.coord = pCoordForma;
        id = contadorId++;
    }

    public Coordenada getCoord() {
      return coord;
    }

    /*
    public void setCoord(int cX, int cY) {
        this.coord.setCoord(cX, cY);
    }
    */

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
     * La usamos en ExisteBalaEn y en findBala de ListaBalas
     * @param pCoord
     * @return true si la Bala esta en la coordenada
     */
    public boolean estasEn(Coordenada pCoord){
        return coord.equals(pCoord);
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
}
