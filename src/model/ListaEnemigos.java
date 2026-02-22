package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ListaEnemigos {
    private static final int MAX_ENEMIGOS_POSIBLES=8;
    private final ArrayList<Enemigo> listaEnemigos;
    private boolean enemigoHaLlegadoAbajo;
    public ListaEnemigos() {
        this.listaEnemigos = new ArrayList<>();
        enemigoHaLlegadoAbajo = false;
    }

    public synchronized void anadirEnemigo(int idEnemigo, Coordenada coord) {
        if (listaEnemigos.size()<MAX_ENEMIGOS_POSIBLES) {
            Enemigo enemigo = new Enemigo(idEnemigo, coord);
            listaEnemigos.add(enemigo);
        }
    }

    public void borrarListaEnemigos(){
        listaEnemigos.clear();
    }

    public synchronized void moverEnemigos() {
       Iterator<Enemigo> it = listaEnemigos.iterator();
        while (it.hasNext()) {
            Enemigo enemigo = it.next();
            enemigo.actualizarPos();
            Coordenada coord = enemigo.getCoord();
            // si el enemigo ha llegado abajo (y > 60) eliminarlo y marcar el fin de la partida
            if (coord.getY() > 59) {
                it.remove();
                enemigoHaLlegadoAbajo = true;
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

    public void eliminarEnemigo(int posEnemigo) {
        if (posEnemigo >= 0 && posEnemigo < listaEnemigos.size()) {
            listaEnemigos.remove(posEnemigo);
        }

    }

    public boolean enemigoHaLLegadoAbajo() {
        return enemigoHaLlegadoAbajo;
    }

}

