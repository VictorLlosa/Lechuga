package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ListaEnemigos {
    private final ArrayList<Enemigo> listaEnemigos;
    public ListaEnemigos() {this.listaEnemigos = new ArrayList<>();}

    public synchronized void anadirEnemigo(int idEnemigo, Coordenada coord) {
        Enemigo enemigo = new Enemigo(idEnemigo, coord);
        listaEnemigos.add(enemigo);
    }

    public synchronized void moverEnemigos() {
       Iterator<Enemigo> it = listaEnemigos.iterator();
        while (it.hasNext()) {
            Enemigo enemigo = it.next();
            enemigo.actualizarPos();
            Coordenada coord = enemigo.getCoord();
            // si el enemigo ha llegado a abajo (y > 60) eliminarla
            if (coord.getY() > 59) {
                it.remove();
            }
        }

    }

    public synchronized Coordenada getCoordEnemigos(int pPos) {
        if (pPos >= 0 && pPos < listaEnemigos.size()) {
            return listaEnemigos.get(pPos).getCoord();
        }
        return null;
    }
    public synchronized int getNumEnemigos() { return listaEnemigos.size(); }

    /*
    public synchronized void eliminarEnemigo(int id) {
        Iterator<Enemigo> it = listaEnemigos.iterator();
        while (it.hasNext()) {
            Enemigo enemigo = it.next();
            int idEnem = enemigo.getId();
            if (id == idEnem) {
                listaEnemigos.remove(enemigo);
                it.remove();
            }
        }
    }

     */
}

