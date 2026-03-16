package model;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaBalas {
    private final ArrayList<Bala> listaBalas;
    private static ListaBalas miListaBalas;

    public ListaBalas() {
        this.listaBalas = new ArrayList<>();
    }

    public static ListaBalas getListaBalas(){
        if(miListaBalas == null){
            miListaBalas = new ListaBalas();
        }
        return miListaBalas;
    }

    public synchronized void anadirBala(int idNave, Coordenada coord) {
        Bala bala = new Bala(idNave, coord);
        listaBalas.add(bala);
    }

    public synchronized void moverBalas() {
        Iterator<Bala> it = listaBalas.iterator();
        while (it.hasNext()) {
            Bala bala = it.next();
            bala.actualizarPos();
            Coordenada coord = bala.getCoord();
            // si la bala salió por encima (y < 0) eliminarla
            if (coord.getY() < 0) {
                it.remove();
            }
        }
    }

    public synchronized void moverBala(int pPos) {

        Bala bala =listaBalas.get(pPos);
        bala.actualizarPos();

        Coordenada coord = bala.getCoord();
        // si la bala salió por encima (y < 0) eliminarla
        if (coord.getY() < 0) {
            listaBalas.remove(bala);
        }
    }


    public synchronized Coordenada getCoordBala(int pPos) {
        if (pPos >= 0 && pPos < listaBalas.size()) {
            return listaBalas.get(pPos).getCoord();

        }
        return null;
    }

    public synchronized int getNumBalas() {
        return listaBalas.size();
    }

    public void eliminarBala(int posBala) {
        if (posBala >= 0 && posBala < listaBalas.size()) {
            listaBalas.remove(posBala);
        }
    }

    public void borrarListaBalas() {
        listaBalas.clear();
    }
}
