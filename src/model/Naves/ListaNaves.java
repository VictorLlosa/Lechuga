package model.Naves;

import model.Composite.CompositeCoordenada;
import model.Composite.Coordenada;
import model.Composite.Pixel;
import model.Factorias.FactoriaNave;
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
     *
     * @param pTipo
     * @param pCoord
     */
    public CompositeCoordenada anadirNave(TipoNave pTipo, Pixel pCoord) {
        NaveAbstracta nave = FactoriaNave.getFactoriaNave().generar(pTipo, pCoord);
        listaNaves.add(nave);
        listaIds.add(nave.getId());
        return nave.getForma();
    }

    public CompositeCoordenada getCoordNave(int pIdNave) {
        NaveAbstracta nave = findNave(pIdNave);
        if (nave != null) return nave.getCoord();
        return null;
    }

    public Pixel getCannonNave(int pIdNave) {
        NaveAbstracta nave = findNave(pIdNave);
        if (nave != null) return nave.getCannon();
        return null;
    }

    public int getNumNaves() {
        return listaNaves.size();
    }

    /**
     * En vez de eliminar la nave la marcamos como muerta. Además, eliminamos su id de listaIds
     * para que el espacio solo itere sobre las naves vivas
     * @param pIdNave
     */
    public void matarNave(int pIdNave) {
        NaveAbstracta nave = findNave(pIdNave);
        if (nave != null) {
            nave.matar();
            listaIds.remove(nave.getId());
        }
    }

    public void borrarListaNaves() {
        listaNaves.clear();
        listaIds.clear();
    }

    private NaveAbstracta findNave(int idNave) {
        for (NaveAbstracta nave : listaNaves) {
            if (nave.tienesId(idNave)) return nave;
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
    /**
     * Devuelve una Coordenada (ya sea pixel o composite) de la nave pIdNave
     * @param pIdNave
     * @return null si no existe la nave o no tiene balas
     */
    public CompositeCoordenada getCoordBalasNave(int pIdNave) {
        NaveAbstracta nave = findNave(pIdNave);
        if (nave != null) return nave.getCoordBalas();
        else return null;
    }

    public void moverBalas(){
        for(NaveAbstracta nave : listaNaves){
            nave.moverBalas();
        }
    }
    /*
    TODO: public CompositeCoordenada moverBalasNave(int pIdNave) {
        NaveAbstracta nave = findNave(pIdNave);
        if(nave != null) return nave.moverBalas();
        else return null;
    }
    */

    public void borrarBalasNave(int pIdNave) {
        NaveAbstracta nave = findNave(pIdNave);
        if(nave != null) nave.borrarBalas();

    }
    /**
     * Le llama Espacio
     */
    public void matarNaveEn(Coordenada pCoord) {
        NaveAbstracta nave = this.findNaveEn(pCoord);
        if (nave != null) this.matarNave(nave.getId());
    }

    /**
     * Mira si TODAS las naves .estanMuertas()
     *
     * @return
     */
    public boolean quedanNaves() {
        return !listaIds.isEmpty();
    }

    /**
     * Como el id de las naves es un atributo estatico basta con modificarlo en una intancia
     */
    public void reiniciarContadorNaves() {
        listaNaves.getFirst().reiniciarContadorNaves();
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
     * Llama a eliminarBalaPorCoord de la naveAbstracta con el id que pasaos como parametro
     */
    public void eliminarBalaPorCoord(int pIdNave, Coordenada pCoord){
        NaveAbstracta nave = findNave(pIdNave);
        if (nave != null) nave.eliminarBalaPorCoord(pCoord);
    }

    /**
     * Aqui no pasamos el id de la nave como parametro. Tenemos que ir nave por nave buscando la que tenga una
     * bala en las componentes de coordenadna dadas

     */
    public void eliminarBalaPorCoord(Coordenada pCoord){
        for (NaveAbstracta nave : listaNaves){
            nave.eliminarBalaPorCoord(pCoord);
        }

    }

    /**
     * Se usa en Espacio para mover la nave a las coordenadas seleccionadas. Actualiza cada coordenada que compone a la nave
     * @param pIdNave
     * @param dx
     * @param dy
     */
    public void moverNave(int pIdNave, int dx, int dy) {
        NaveAbstracta nave = findNave(pIdNave);
        if (nave != null) nave.moverNave(dx, dy);
    }

    //TODO ahora
    @Override
    public void update(Observable o, Object arg) {

    }
}
