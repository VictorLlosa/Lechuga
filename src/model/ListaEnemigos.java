package model;

import model.Composite.Coordenada;
import model.Composite.Pixel;
import model.Factorias.FactoriaEnemigo;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaEnemigos {
    private final int MAX_ENEMIGOS_POSIBLES=8;
    private ArrayList<EnemigoAbstracto> listaEnemigos;
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

    /**
     * Crea un enemigo a partir de su centro, usa el metodo "esCoordValidaAlCrear(enemigo.getCoord() )"
     * @param pCentro
     * @param pTipo
     * @return Coordenada del enemigo o null si no ha podido crearlo
     */
    public Coordenada anadirEnemigo(Pixel pCentro, String pTipo) {
        if (listaEnemigos.size()<MAX_ENEMIGOS_POSIBLES) {
            Enemigo enemigo = (Enemigo) FactoriaEnemigo.getFactoriaEnemigo().generar(pTipo, pCentro);
            if(Espacio.getEspacio().esCoordValidaAlCrear(enemigo.getCoord())){
                listaEnemigos.add(enemigo);
                listaIds.add(enemigo.getId());
                return enemigo.getCoord();
            }else{
                return null;
            }
        }else{
            return null;
        }

    }

    public void borrarListaEnemigos(){
        listaEnemigos.clear();
        listaIds.clear();
        enemigoHaLlegadoAbajo = false;
    }

    public void moverEnemigos() {
        Iterator<EnemigoAbstracto> it = listaEnemigos.iterator();

        while (it.hasNext()) {
            EnemigoAbstracto enem = it.next();
            enem.actualizarPos();
            Pixel coord = enem.getCoord();

            // si el enemigo ha llegado abajo eliminarlo y marcar fin
            if (coord.getY() > 59) {
                listaIds.remove(enem.getId());
                it.remove();   // eliminar correctamente
                enemigoHaLlegadoAbajo = true;
            }
        }
    }

    public Pixel getCoordEnemigo(int pId) {
        EnemigoAbstracto enem = findEnemigo(pId);
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
     */
    public void matarEnemigoEn(Coordenada pCoord){
        EnemigoAbstracto enem = this.findEnemigoEn(pCoord);
        if(enem != null) {
            enem.matar();
            listaIds.remove(enem.getId());
        }
    }

    private EnemigoAbstracto findEnemigoEn(Coordenada pCoord){
        for (EnemigoAbstracto enem : listaEnemigos) {
            if (enem.estaEn(pCoord)) return enem;
        }
        return null;
    }


    public boolean quedanEnemigos() {
        return !listaEnemigos.isEmpty();
    }

    public void eliminarEnemigosMuertos() {
        // Locura de java
        listaEnemigos.removeIf(EnemigoAbstracto::estaMuerto);
    }

    /**
     *
     * @param pId
     * @return devuelve si un enemigo esta muelto. si no existe, devuelve true.
     */
    public boolean enemigoMuerto(int pId) {
        EnemigoAbstracto enem = findEnemigo(pId);
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
    public EnemigoAbstracto findEnemigo(int pIdEnem){
        for (EnemigoAbstracto e : listaEnemigos){
            if (e.getId()==pIdEnem) return e;
        }
        return null;
    }
}
