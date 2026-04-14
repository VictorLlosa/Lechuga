package model.Balas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

public class ListaBalas implements Observer {
    private final ArrayList<BalaAbstracta> listaBalas;

    public ListaBalas() {
        this.listaBalas = new ArrayList<>();
    }

    public synchronized void anadirBala(BalaAbstracta pBala) {
        listaBalas.add(pBala);
    }

    /**
     * Le dice a todas las balas que se muevan. Si la bala se ha salido del espacio
     * (exito = false) se le dice a la bala que se borre y se elimina de la lista.
     */
    public synchronized void moverBalas() {
        Iterator<BalaAbstracta> iterator = listaBalas.iterator();
        while (iterator.hasNext()) {
            BalaAbstracta bala = iterator.next();
            boolean exito = bala.moverEnEspacio();
            if (!exito) {
                bala.borrar();
                iterator.remove();
            }
        }
    }

    public synchronized int getNumBalas() {
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

    @Override
    public void update(Observable o, Object arg) {

    }
}
