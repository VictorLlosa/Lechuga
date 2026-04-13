package model.Enemigos;

import model.Composite.*;
import model.Espacio;
import model.Factorias.FactoriaEnemigo;
import model.Tipos.TipoEnem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

public class ListaEnemigos implements Observer {
    private final int MAX_ENEMIGOS_POSIBLES=8;
    private ArrayList<EnemigoAbstracto> listaEnemigos;

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

    /**
     * Crea un enemigo a partir de su centro, usa el metodo "esCoordValidaAlCrear(enemigo.getCoord() )"
     * @param pCentro
     * @param pTipo
     * @return Coordenada del enemigo o null si no ha podido crearlo
     */
    public Coordenada anadirEnemigo(Pixel pCentro, TipoEnem pTipo) {
        if (listaEnemigos.size()<MAX_ENEMIGOS_POSIBLES) {
            EnemigoAbstracto enemigo = FactoriaEnemigo.getFactoriaEnemigo().generar(pTipo, pCentro);
            if(Espacio.getEspacio().esCoordValidaAlCrear(enemigo.getCoord())){
                listaEnemigos.add(enemigo);
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
        enemigoHaLlegadoAbajo = false;
    }

    public Coordenada getCoordEnemigo(int pId) {
        EnemigoAbstracto enem = findEnemigo(pId);
        if( enem != null) return enem.getCoord();
        else return null;
    }

    /**
     * Devuelve un CompositeCoordenada con las coordenadas de todos los enemigos. Se usa en Espacio, para 'moverEnemigos()'
     */
    public CompositeCoordenada getCoordAllEnemigos() {
        CompositeCoordenada coordsEnem = new CompositeCoordenada();
        for(EnemigoAbstracto enem : listaEnemigos){
            coordsEnem.addComponent(enem.getCoord());
        }
        return coordsEnem;
    }

    public int getNumEnemigos() { return listaEnemigos.size(); }

    public boolean enemigoHaLLegadoAbajo() {
        return enemigoHaLlegadoAbajo;
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

    @Override
    public void update(Observable o, Object arg) {

    }

    public void moverEnemigos() {
        Iterator<EnemigoAbstracto> itr = listaEnemigos.iterator();
        while(itr.hasNext()){
            if(!itr.next().moverEnEspacio()) itr.remove();
        }
    }
}
