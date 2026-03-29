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

    public void anadirEnemigo(Coordenada coord) {
        if (listaEnemigos.size()<MAX_ENEMIGOS_POSIBLES) {
            Enemigo enemigo = new Enemigo(coord);
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

    public Coordenada getCoordEnemigo(int pPos) {
        return listaEnemigos.get(pPos).getCoord();
    }

    public int getNumEnemigos() { return listaEnemigos.size(); }

    public void matarEnemigo(int pPos) {
        listaEnemigos.get(pPos).matar();
    }

    public boolean enemigoHaLLegadoAbajo() {
        return enemigoHaLlegadoAbajo;
    }

    /**
     *
     * @param cX
     * @param cY
     */
    public void matarEnemigoEn(int cX, int cY){
        Enemigo enem = this.findEnemigoEn(cX,cY);
        if(enem != null) enem.matar();
    }

    private Enemigo findEnemigoEn(int cX, int cY){
        for (Enemigo enem : listaEnemigos) {
            if (enem.estaEn(cX,cY)) return enem;
        }
        return null;
    }


    public boolean quedanEnemigos() {
        for(Enemigo enem : listaEnemigos){
            if(!enem.estaMuerto()) return true;
        }
        return false;
    }

    public void eliminarEnemigosMuertos() {
        // Locura de java
        listaEnemigos.removeIf(Enemigo::estaMuerto);
    }

    public boolean enemigoMuerto(int pPos) {
        return listaEnemigos.get(pPos).estaMuerto();
    }
}
