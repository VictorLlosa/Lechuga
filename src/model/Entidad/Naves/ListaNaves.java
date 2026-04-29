package model.Entidad.Naves;

import model.Entidad.Balas.ListaBalas;
import model.ColisionEvent;
import model.CompositeCoordenada.Coordenada;
import model.Factorias.FactoriaNave;
import model.Tipos.TipoEntidad;
import model.Tipos.TipoNave;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ListaNaves implements Observer {
    private ArrayList<NaveAbstracta> listaNaves;
    private static ListaNaves miListaNaves;

    public ListaNaves() {
        this.listaNaves = new ArrayList<>();
    }

    public static ListaNaves getListaNaves() {
        if (miListaNaves == null) {
            miListaNaves = new ListaNaves();
        }
        return miListaNaves;
    }

    /**
     * Llama al disparar() de la nave.
     * @return devuelve un CompositeCoordenada (las coordenadas de la bala) o null si no se ha podido crear.
     */
    public void disparar(int pJugador) {
        NaveAbstracta nave = listaNaves.get(pJugador);
        nave.disparar();
    }

    /**
     * anade una nave a ListaNaves
     * @param pTipo
     * @param cX
     * @param cY
     * @return
     */
    public void anadirNave(TipoNave pTipo, int cX, int cY) {
        NaveAbstracta nave = FactoriaNave.getFactoriaNave().generar(pTipo, cX, cY);
        listaNaves.add(nave);
    }

    public int getNumNaves() {
        return listaNaves.size();
    }


    public void reniciarListaNaves() {
        listaNaves.clear();
    }

    private NaveAbstracta findNave(int idNave) {
        for (NaveAbstracta nave : listaNaves) {
            if (nave.tienesId(idNave) && !nave.estaMuerta()) return nave;
        }
        return null;
    }

    public void alternarModoDisparo(int pJugador) {
        NaveAbstracta nave = listaNaves.get(pJugador);
        nave.toggleStrategy();
    }

    /**
     * Se usa en Espacio para mover la nave a las coordenadas seleccionadas. Actualiza cada coordenada que compone a la nave
     * @param pJugador
     * @param dx
     * @param dy
     */
    public void moverNave(int pJugador, int dx, int dy) {
        NaveAbstracta nave = listaNaves.get(pJugador);
        nave.moverNave(dx, dy);
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

    /**
     * Borra las Naves muertas y también borra las balas muertas de cada Nave
     */
    public void borrarMuertos(){
        for (NaveAbstracta nave : listaNaves) {
            nave.borrarBalasMuertas();
            if (nave.estaMuerta()) {
                nave.borrarNave();
            }
        }
    }

    public boolean quedanNaves() {
        boolean vivas = false;
        for(NaveAbstracta nave : listaNaves){
            if(!nave.estaMuerta()){
                vivas = true;
                break;
            }
        }
        return vivas;
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
        ArrayList<ColisionEvent> eventos = (ArrayList<ColisionEvent>)arg;
        for(ColisionEvent evento : eventos){
            if(evento.getCambio() && evento.getTipo() == TipoEntidad.nave){
                NaveAbstracta nave = findNave(evento.getIdEntidad());
                if(nave != null) nave.matar();
            }
        }

    }
}
