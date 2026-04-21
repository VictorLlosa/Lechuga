package model.Enemigos;

import model.ColisionEvent;
import model.Factorias.FactoriaEnemigo;
import model.Tipos.TipoEnem;
import model.Tipos.TipoEntidad;

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
     * @param pX
     * @param pY
     * @param pTipo
     * @return Coordenada del enemigo o null si no ha podido crearlo
     */
    public boolean anadirEnemigo(int pX, int pY, TipoEnem pTipo) {
        boolean exito = true;
        if (listaEnemigos.size()<MAX_ENEMIGOS_POSIBLES) {
            EnemigoAbstracto enemigo = FactoriaEnemigo.getFactoriaEnemigo().generar(pTipo, pX, pY);
            if(enemigo!=null){
                listaEnemigos.add(enemigo);
                exito = true;
            }else{
                exito = false;
            }
        }else{
            exito = false;
        }
        return exito;

    }

    public void ponerEnemigosEnEspacio(){
        for(EnemigoAbstracto enem: listaEnemigos){
            enem.ponerEnEspacio();
        }
    }

    public void borrarListaEnemigos(){
        listaEnemigos.clear();
        enemigoHaLlegadoAbajo = false;
    }

    public int getNumEnemigos() { return listaEnemigos.size(); }

    public boolean enemigoHaLLegadoAbajo() {
        return enemigoHaLlegadoAbajo;
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

    /**
     * La casilla nos notifica dos tuplas: [TipoEntidad.eltipo, pCasilla.getId()},{pEnt, pIdEntidad (entidad movida)} ]
     * @param o     the observable object.
     * @param arg   an argument passed to the {@code notifyObservers}
     *                 method.
     */
    @Override
    public void update(Observable o, Object arg) {
        ColisionEvent evento = (ColisionEvent) arg;
        if(evento.getCambio() && evento.getTipo() == TipoEntidad.enemigo) borrarEnemigo(evento.getIdEntidad());
    }

    /**
     * Le dice a todos los enemigos que se muevan. Si el enemigo se ha intentado mover fuera
     * del espacio (exito = false) se le dice que se borre, se marca que los enemigos
     * han llegado abajo y se elimina de la lista.
     */
    public void moverEnemigos() {
        Iterator<EnemigoAbstracto> it = listaEnemigos.iterator();

        while (it.hasNext()) {
            EnemigoAbstracto enem = it.next();
            boolean exito = enem.moverEnEspacio();
            if(enem.haLLegadoAbajo()) enemigoHaLlegadoAbajo = true;
            if (!exito) {
                enem.borrar();
                it.remove();
            }
        }

    }

    public void borrarEnemigos() {
        for(EnemigoAbstracto enem: listaEnemigos){
            enem.borrar();
        }
        borrarListaEnemigos();
    }
    public void borrarEnemigo(int pId) {
        EnemigoAbstracto enemigo = findEnemigo(pId);
        if (enemigo != null) {
            enemigo.borrar();
            listaEnemigos.remove(enemigo);
        }
    }
}
