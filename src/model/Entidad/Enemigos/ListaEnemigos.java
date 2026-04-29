package model.Entidad.Enemigos;

import model.ColisionEvent;
import model.Factorias.FactoriaEnemigo;
import model.Tipos.TipoEnemigo;
import model.Tipos.TipoEntidad;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

public class ListaEnemigos implements Observer {
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
    public boolean anadirEnemigo(int pX, int pY, TipoEnemigo pTipo) {
        boolean exito;

        EnemigoAbstracto enemigo = FactoriaEnemigo.getFactoriaEnemigo().generar(pTipo, pX, pY);
        if(enemigo!=null){
            listaEnemigos.add(enemigo);
            exito = true;
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
     * Le dice a todos los enemigos que se muevan. Si han llegado abajo y se elimina de la lista.
     * Llama a lethalHit() para ver si se ha matado al enemigo y
     * en ese caso lo borra
     */
    public void moverEnemigos() {
        for(EnemigoAbstracto enem : listaEnemigos){ //primero marcamos como muerto
            enem.moverEnEspacio();
            if(enem.haLLegadoAbajo()){
                enemigoHaLlegadoAbajo = true;
                enem.matar();
            }
        }

    }
    public void borrarMuertos(){
        Iterator<EnemigoAbstracto> itr = listaEnemigos.iterator();
        while(itr.hasNext()){
            EnemigoAbstracto enem = itr.next();
            if(enem.estaMuerta()){
                enem.borrar();
                itr.remove();
            }
        }
    }

    public void borrarEnemigos() {
        for(EnemigoAbstracto enem: listaEnemigos){
            enem.borrar();
        }
        borrarListaEnemigos();
    }
    /**
     * La casilla nos notifica dos tuplas: [TipoEntidad.eltipo, pCasilla.getId()},{pEnt, pIdEntidad (entidad movida)} ].
     *
     * @param o     the observable object.
     * @param arg   an argument passed to the {@code notifyObservers}
     *                 method.
     */
    @Override
    public void update(Observable o, Object arg) {
        ArrayList<ColisionEvent> eventos = (ArrayList<ColisionEvent>)arg;
        for(ColisionEvent evento : eventos){
            if(evento.getCambio() && evento.getTipo().esEnemigo()){
                EnemigoAbstracto enem = findEnemigo(evento.getIdEntidad());
                enem.lethalHit();
            }
        }

    }

}
