package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ListaEnemigos {
    private final int MAX_ENEMIGOS_POSIBLES=8;
    private final ArrayList<Enemigo> listaEnemigos;
    private boolean enemigoHaLlegadoAbajo;
    private static ListaEnemigos miListaEnemigos = null;
    public ListaEnemigos() {
        this.listaEnemigos = new ArrayList<>();
        enemigoHaLlegadoAbajo = false;
    }

    public static ListaEnemigos getListaEnemigos(){
        if(miListaEnemigos == null){
            miListaEnemigos = new ListaEnemigos();
        }
        return miListaEnemigos;
    }

    public void anadirEnemigo(int idEnemigo, Coordenada coord) {
        if (listaEnemigos.size()<MAX_ENEMIGOS_POSIBLES) {
            Enemigo enemigo = new Enemigo(idEnemigo, coord);
            listaEnemigos.add(enemigo);
        }
    }

    public void borrarListaEnemigos(){
        listaEnemigos.clear();
        enemigoHaLlegadoAbajo = false;
    }

    public void moverEnemigos() {
        Iterator<Enemigo> it = listaEnemigos.iterator();

        while (it.hasNext()) {
            Enemigo enem = it.next();
            enem.actualizarPos();
            Coordenada coord = enem.getCoord();

            // si el enemigo ha llegado abajo eliminarlo y marcar fin
            if (coord.getY() > 59) {
                it.remove();   // eliminar correctamente
                enemigoHaLlegadoAbajo = true;
            }
        }
    }

    public Coordenada getCoordEnemigos(int pPos) {
        if (pPos >= 0 && pPos < listaEnemigos.size()) {
            return listaEnemigos.get(pPos).getCoord();
        }
        return null;
    }
    public int getNumEnemigos() { return listaEnemigos.size(); }

    public void eliminarEnemigo(int posEnemigo) {
        if (posEnemigo >= 0 && posEnemigo < listaEnemigos.size()) {
            listaEnemigos.remove(posEnemigo);
        }

    }

    public boolean enemigoHaLLegadoAbajo() {
        return enemigoHaLlegadoAbajo;
    }

    /**
     *
     * @param cX
     * @param cY
     */
    public void eliminarEnemigoEn(int cX, int cY){
        Enemigo en = this.findEnemigoEn(cX,cY);
        if (en != null) this.eliminarEnemigo(en.getId());
    }

    private Enemigo findEnemigoEn(int cX, int cY){
        for (Enemigo nave : listaEnemigos) {
            if (Enemigo.estaEn(cX,cY)) return nave;
        }
        return null;
    }


}

