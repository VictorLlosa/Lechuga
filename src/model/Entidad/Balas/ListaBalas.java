package model.Entidad.Balas;

import model.ColisionEvent;
import model.Tipos.TipoEntidad;

import java.util.*;

public class ListaBalas implements Observer {
    private final HashMap<Integer, BalaAbstracta> listaBalas;
    private static ListaBalas miListaBalas;

    private ListaBalas() {
        this.listaBalas = new HashMap<>();
    }
    public static ListaBalas getListaBalas(){
        if(miListaBalas == null){
            miListaBalas = new ListaBalas();
        }
        return miListaBalas;
    }

    public void anadirBala(BalaAbstracta pBala) {
        listaBalas.put(pBala.getId(), pBala);
    }

    /**
     * Le dice a todas las balas que se muevan. Si la bala se ha salido del espacio
     * (exito = false) se le dice a la bala que se borre y se elimina de la lista.
     */
    public void moverBalas() {
        for(BalaAbstracta bala: listaBalas.values()){
            bala.moverEnEspacio();
            if(bala.estaFuera()) bala.matar();
        }
    }


    /**
     * Este metodo es llamado por naveAbstracta al reiniciar y borra la lista de balas de la nave
     */
    public void borrarListaBalas() {
        for(BalaAbstracta bala: listaBalas.values()){
            bala.borrar();
        }
        listaBalas.clear();
    }

    public void borrarMuertos(){
        listaBalas.values().removeIf(bala -> {
            if (bala.estaMuerta()) {
                bala.borrar();
                return true;
            }
            return false;
        });
    }

    private BalaAbstracta findBala(int pId) {
        return listaBalas.get(pId);
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
