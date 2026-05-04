package model.Entidad.Enemigos;

import model.ColisionEvent;
import model.Espacio;
import model.Factorias.FactoriaEnemigo;
import model.Tipos.TipoEnemigo;

import java.util.*;

public class ListaEnemigos implements Observer {
    private ArrayList<EnemigoAbstracto> listaEnemigos;

    private boolean enemigoHaLlegadoAbajo;
    private static ListaEnemigos miListaEnemigos = null;
    private ListaEnemigos() {
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
        listaEnemigos.forEach(enem -> enem.ponerEnEspacio());
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
        return listaEnemigos.stream()
                .filter(enem -> enem.getId() == pIdEnem)
                .findFirst()
                .orElse(null);
    }
    /**
     * Le dice a todos los enemigos que se muevan. Si han llegado abajo y se elimina de la lista.
     * Llama a lethalHit() para ver si se ha matado al enemigo y
     * en ese caso lo borra
     */
    public void moverTodosEnemigos() {
        listaEnemigos.stream()
                .forEach(enem -> enem.moverEnEspacio());
        listaEnemigos.stream()
                .filter(enem -> enem.haLLegadoAbajo())
                .forEach(enem -> {
                    enem.matar();
                    enemigoHaLlegadoAbajo = true;
                });
    }
    public void borrarMuertos(){
        listaEnemigos.removeIf( enemigo -> {
            if (enemigo.estaMuerta()) {
                enemigo.borrar();
                return true;
            }
            return false;
        });
    }
    //Java8 aplicado
    public void borrarEnemigos() {
        listaEnemigos.forEach(enem -> enem.borrar());
        borrarListaEnemigos();
    }

    //Java8 aplicado
    public void toggleMovimiento(TipoEnemigo pTipoEnem){
        listaEnemigos.stream()
                .filter(enem -> enem.eresTipo(pTipoEnem))
                .forEach(enem -> enem.toggleMoverStrategy());
    }

    //Java8 aplicado
    public void spawnearMinions(TipoEnemigo pTipoMaster, TipoEnemigo pTipoMinion) {
        ArrayList<Integer> posicionesY = new ArrayList<>();

        listaEnemigos.stream()
                .filter(enem-> enem.eresTipo(pTipoMaster))
                .map(enem -> enem.getSpawnY())
                .forEach(y -> posicionesY.add(y));

        posicionesY.forEach(y ->  anadirEnemigo(Espacio.getEspacio().getRanX(), y, pTipoMinion));
    }

    //Java8 aplicado
    public void disparar() {
        listaEnemigos.forEach(enem -> enem.disparar());
    }

    /**
     * La casilla nos notifica dos tuplas: [TipoEntidad.eltipo, pCasilla.getId()},{pEnt, pIdEntidad (entidad movida)} ].
     *              Java8 aplicado
     * @param o     the observable object.
     * @param arg   an argument passed to the {@code notifyObservers}
     *                 method.
     */
    @Override
    public void update(Observable o, Object arg) {
        ArrayList<ColisionEvent> eventos = (ArrayList<ColisionEvent>)arg;
        eventos.stream()
                .filter(evento -> evento.getCambio() && evento.getTipo().esEnemigo())
                .map(evento -> findEnemigo(evento.getIdEntidad()))
                .filter(Objects::nonNull)
                .forEach(enemigo -> enemigo.lethalHit());
    }
}
