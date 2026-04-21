package model.Naves;

import model.Balas.ListaBalas;
import model.Composite.CompositeCoordenada;
import model.Composite.Coordenada;
import model.ColisionEvent;
import model.Factorias.FactoriaNave;
import model.Tipos.TipoEntidad;
import model.Tipos.TipoNave;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ListaNaves implements Observer {
    private ArrayList<NaveAbstracta> listaNaves;
    ArrayList<Integer> listaIds;
    private static ListaNaves miListaNaves;

    public ListaNaves() {
        this.listaNaves = new ArrayList<>();
        this.listaIds = new ArrayList<>();
    }

    public static ListaNaves getListaNaves() {
        if (miListaNaves == null) {
            miListaNaves = new ListaNaves();
        }
        return miListaNaves;
    }

    /**
     * Llama al disparar() de la nave.
     * @param pIdNave es el id de la nave
     * @return devuelve un CompositeCoordenada (las coordenadas de la bala) o null si no se ha podido crear.
     */
    public void disparar(int pIdNave) {
        NaveAbstracta nave = findNave(pIdNave);
        if (nave != null) nave.disparar();
    }

    /**
     * anade una nave a ListaNaves
     * @param pTipo
     * @param cX
     * @param cY
     * @return
     */
    public CompositeCoordenada anadirNave(TipoNave pTipo, int cX, int cY) {
        NaveAbstracta nave = FactoriaNave.getFactoriaNave().generar(pTipo, cX, cY);
        listaNaves.add(nave);
        listaIds.add(nave.getId());
        return nave.getForma();
    }

    public int getNumNaves() {
        return listaNaves.size();
    }


    public void reniciarListaNaves() {
        listaNaves.clear();
        listaIds.clear();
    }

    private NaveAbstracta findNave(int idNave) {
        for (NaveAbstracta nave : listaNaves) {
            if (nave.tienesId(idNave) && !nave.estaMuerta()) return nave;
        }
        return null;
    }

    private NaveAbstracta findNaveEn(Coordenada pCoord) {
        for (NaveAbstracta nave : listaNaves) {
            if (nave.estasEn(pCoord)) return nave;
        }
        return null;
    }

    public boolean existeNave(int idNave) {
        return listaIds.contains(idNave);
    }

    public void alternarModoDisparo(int pIdNave) {
        NaveAbstracta nave = findNave(pIdNave);
        if(nave != null) nave.changeStrategy();
    }

    public void moverBalas(){
        for(NaveAbstracta nave : listaNaves){
            nave.moverBalas();
        }
    }

    public void borrarBalasNave(int pIdNave) {
        NaveAbstracta nave = findNave(pIdNave);
        if(nave != null) nave.borrarBalas();

    }

    /**
     * Devuelve una lista de id's (un ArrayList de enteros), para evitar tratar las "posiciones" de las naves en la ListaNaves,
     * y asi las tratamos por su id directamente
     * @return
     */
    public ArrayList<Integer> getListaIds() {
        return listaIds;
    }

    /**
     * Se usa en Espacio para mover la nave a las coordenadas seleccionadas. Actualiza cada coordenada que compone a la nave
     * @param pIdNave
     * @param dx
     * @param dy
     */
    public void moverNave(int pIdNave, int dx, int dy) {
        NaveAbstracta nave = findNave(pIdNave);
        if (nave != null){
            if(!nave.moverNave(dx, dy)){
                nave.borrarNave();
                nave.matar();
                listaIds.remove(nave.getId());
            }
        }
    }

    /**
     * Metodo que llama gestorPartida para poner cada una de las naves en el espacio
     */
    public void ponerNavesEnEspacio(){
        for(NaveAbstracta nave : listaNaves){
            nave.ponerEnEspacio();
        }
    }

    /**
     * Igual que en el update de ListaEnemigos
     * @param o     La casilla
     * @param arg   es un array de dos posiciones:
     *              (0: tipo de entidad
     *              1: id de la entidad)
     */
    @Override
    public void update(Observable o, Object arg) {
        ColisionEvent evento = (ColisionEvent) arg;
        if(evento.getCambio() && evento.getTipo() == TipoEntidad.nave) borrarNave(evento.getIdEntidad());
    }

    /**
     * Metodo que borra todas las balas de la lista de balas, el cual es utilizado por el gestorPartida para borrarlas
     */
    public void borrarBalas() {
        for(NaveAbstracta nave: listaNaves){
            nave.borrarBalas();
        }
    }

    public void borrarNaves() {
        for(NaveAbstracta nave: listaNaves){
            nave.borrarNave();
        }
        reniciarListaNaves();
    }

    public void borrarNave(int pId) {
        NaveAbstracta nave = findNave(pId);
        if(nave != null){
            nave.borrarNave();
            nave.matar();
        }
    }

    public boolean quedanNaves() {
        return !listaIds.isEmpty();
    }

    public ArrayList<ListaBalas> getListaBalas() {
        ArrayList<ListaBalas> lb = new ArrayList<>();
        for(NaveAbstracta nave: listaNaves){
            lb.add(nave.getListaBalas());
        }
        return lb;
    }
}
