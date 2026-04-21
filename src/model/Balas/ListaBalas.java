package model.Balas;

import model.ColisionEvent;
import model.Tipos.TipoEntidad;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ListaBalas implements Observer {
    private final ArrayList<BalaAbstracta> listaBalas;

    public ListaBalas() {
        this.listaBalas = new ArrayList<>();
    }

    public void anadirBala(BalaAbstracta pBala) {
        listaBalas.add(pBala);
    }

    /**
     * Le dice a todas las balas que se muevan. Si la bala se ha salido del espacio
     * (exito = false) se le dice a la bala que se borre y se elimina de la lista.
     */
    public void moverBalas() {
        ArrayList<BalaAbstracta> toRemove = new ArrayList<>();
        for(BalaAbstracta bala: listaBalas){
            boolean exito = bala.moverEnEspacio();
            if (!exito) {
                toRemove.add(bala);
            }
        }
        for(BalaAbstracta bala: toRemove){
            bala.borrar();
            listaBalas.remove(bala);
        }
    }

    public int getNumBalas() {
        return listaBalas.size();
    }

    /**
     * Este metodo es llamado por naveAbstracta al reiniciar y borra la lista de balas de la nave
     */
    public void borrarListaBalas() {
        for(BalaAbstracta bala: listaBalas){
            bala.borrar();
        }
        listaBalas.clear();
    }
    private void borrarBala(int pId) {
        BalaAbstracta bala = findBala(pId);
        if(bala != null) bala.borrar();
    }

    private BalaAbstracta findBala(int pId) {
        for (BalaAbstracta bala : listaBalas){
            if(bala.getId() == pId) return bala;
        }
        return null;
    }

    @Override
    public void update(Observable o, Object arg) {
        ColisionEvent evento = (ColisionEvent) arg;
        if(evento.getCambio() && evento.getTipo() == TipoEntidad.bala) borrarBala(evento.getIdEntidad());
    }


}
