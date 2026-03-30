package model;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaEnemigos {
    private final int MAX_ENEMIGOS_POSIBLES=8;
    private ArrayList<Enemigo> listaEnemigos;
    private ArrayList<Integer> listaIds;
    private boolean enemigoHaLlegadoAbajo;
    private static ListaEnemigos miListaEnemigos = null;
    public ListaEnemigos() {
        this.listaEnemigos = new ArrayList<>();
        this.listaIds = new ArrayList<>();
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
            listaIds.add(enemigo.getId());
        }
    }

    public void borrarListaEnemigos(){
        listaEnemigos.clear();
        listaIds.clear();
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
                listaIds.remove(enem.getId());
                it.remove();   // eliminar correctamente
                enemigoHaLlegadoAbajo = true;
            }
        }
    }

    public Coordenada getCoordEnemigo(int pId) {
        Enemigo enem = findEnemigo(pId);
        if( enem != null) return enem.getCoord();
        else return null;
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
        if(enem != null) {
            enem.matar();
            listaIds.remove(enem.getId());
        }
    }

    private Enemigo findEnemigoEn(int cX, int cY){
        for (Enemigo enem : listaEnemigos) {
            if (enem.estaEn(cX,cY)) return enem;
        }
        return null;
    }


    public boolean quedanEnemigos() {
        return !listaEnemigos.isEmpty();
    }

    public void eliminarEnemigosMuertos() {
        // Locura de java
        listaEnemigos.removeIf(Enemigo::estaMuerto);
    }

    /**
     *
     * @param pId
     * @return devuelve si un enemigo esta muelto. si no existe, devuelve true.
     */
    public boolean enemigoMuerto(int pId) {
        Enemigo enem = findEnemigo(pId);
        if( enem != null) return enem.estaMuerto();
        else return true;
    }

    public ArrayList<Integer> getListaIds() {
        return listaIds;
    }

    /**
     * Lo usamos en buscar coord de enemigo
     * @param pIdEnem
     * @return
     */
    public Enemigo findEnemigo(int pIdEnem){
        for (Enemigo e : listaEnemigos){
            if (e.getId()==pIdEnem) return e;
        }
        return null;
    }
}
