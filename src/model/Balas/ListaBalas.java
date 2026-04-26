package model.Balas;

import model.ColisionEvent;
import model.Enemigos.EnemigoAbstracto;
import model.Naves.NaveAbstracta;
import model.Tipos.TipoEntidad;

import java.util.ArrayList;
import java.util.Iterator;
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
        for(BalaAbstracta bala: listaBalas){
            bala.moverEnEspacio();
            if(bala.estaFuera()) bala.matar();
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

    public void borrarMuertos(){
        Iterator<BalaAbstracta> itr = listaBalas.iterator();
        while(itr.hasNext()){
            BalaAbstracta bala = itr.next();
            if(bala.estaMuerta()){
                bala.borrar();
                itr.remove();
            }
        }
    }

    private BalaAbstracta findBala(int pId) {
        for (BalaAbstracta bala : listaBalas){
            if(bala.getId() == pId) return bala;
        }
        return null;
    }

    @Override
    public void update(Observable o, Object arg) {
        ArrayList<ColisionEvent> eventos = (ArrayList<ColisionEvent>)arg;
        for(ColisionEvent evento : eventos){
            if(evento.getCambio() && evento.getTipo() == TipoEntidad.bala){
                BalaAbstracta bala = findBala(evento.getIdEntidad());
                if(bala != null) bala.matar();
            }
        }
    }


}
