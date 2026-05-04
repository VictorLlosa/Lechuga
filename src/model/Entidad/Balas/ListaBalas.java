package model.Entidad.Balas;

import model.ColisionEvent;
import model.Tipos.TipoEntidad;

import java.util.*;

public class ListaBalas implements Observer {
    private ArrayList<BalaAbstracta> listaBalas;
    private static ListaBalas miListaBalas;

    private ListaBalas() {
        this.listaBalas = new ArrayList<>();
    }
    public static ListaBalas getListaBalas(){
        if(miListaBalas == null){
            miListaBalas = new ListaBalas();
        }
        return miListaBalas;
    }

    public void anadirBala(BalaAbstracta pBala) {
        listaBalas.add(pBala);
    }

    /**
     * Le dice a todas las balas que se muevan. Si la bala se ha salido del espacio
     * (exito = false) se le dice a la bala que se borre y se elimina de la lista.
     */
    public void moverBalas() {
        listaBalas.forEach(bala->bala.moverEnEspacio());
        listaBalas.stream()
                .filter(bala -> bala.estaFuera())
                .forEach(bala -> bala.matar());
    }

    //Java8 aplicado
    public void borrarBalas() {
        listaBalas.forEach(bala -> bala.borrar());
        borrarListaBalas();
    }

    /**
     * Este metodo es llamado por naveAbstracta al reiniciar y borra la lista de balas de la nave
     */
    public void borrarListaBalas() {
        listaBalas.clear();
    }

    public void borrarMuertos(){
        listaBalas.removeIf(bala -> {
            if (bala.estaMuerta()) {
                bala.borrar();
                return true;
            }
            return false;
        });
    }

    private BalaAbstracta findBala(int pId) {
        return listaBalas.stream()
                .filter(bala -> bala.getId() == pId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(Observable o, Object arg) {
        ArrayList<ColisionEvent> eventos = (ArrayList<ColisionEvent>)arg;
        eventos.stream()
                .filter(evento -> evento.getCambio() && evento.getTipo() == TipoEntidad.bala)
                .map(evento -> findBala(evento.getIdEntidad()))
                .filter(Objects::nonNull)
                .forEach(bala -> bala.matar()); //J8 asin
    }
}
