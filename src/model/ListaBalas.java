package model;

import model.Balas.BalaAbstracta;
import model.Composite.CompositeCoordenada;
import model.Composite.Coordenada;

import java.util.ArrayList;

public class ListaBalas {
    private final ArrayList<BalaAbstracta> listaBalas;

    public ListaBalas() {
        this.listaBalas = new ArrayList<>();
    }

    public synchronized void anadirBala(BalaAbstracta pBala) {
        listaBalas.add(pBala);
    }

    /**
     * Si no ha podido mover una Bala, la elimina de la lista
     * (//TODO (de momento)
     */
    public synchronized void moverBalas() {
        for (BalaAbstracta bala : listaBalas) {
            bala.moverEnEspacio();
        }
    }

    public synchronized int getNumBalas() {
        return listaBalas.size();
    }

    public void borrarListaBalas() {
        listaBalas.clear();
    }

    /**
     * Devuelve
     * @return
     */
    public CompositeCoordenada getCoordBalas() {
        CompositeCoordenada composite = new CompositeCoordenada();
        for (BalaAbstracta b : listaBalas){
            composite.addComponent(b.getCoord());
        }
        return composite;
    }

    /**
     *
     * @return
     */
    public void eliminarBalaEn(Coordenada pCoord){
        BalaAbstracta bala = findBala(pCoord);
        if (bala !=null) listaBalas.remove(bala);
    }


    /**
     * para cada bala en la lista, hasta que demos con ella, le preguntamos si .estaEn(pCoord)
     * @return null si no la encontramos
     */
    private BalaAbstracta findBala(Coordenada pCoord){
        for (BalaAbstracta bala : listaBalas){
            if (bala.estasEn(pCoord)){
                return bala;
            }
        }
        return null;
    }

    public boolean existeBalaEn(Coordenada pCoord){
        for (BalaAbstracta bala : listaBalas){
            if (bala.estasEn(pCoord)){
                return true;
            }
        }
        return false;
    }
}
