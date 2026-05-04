package model.Entidad.Naves;

import model.ColisionEvent;
import model.Factorias.FactoriaNave;
import model.Tipos.TipoEntidad;
import model.Tipos.TipoNave;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class ListaNaves implements Observer {
    private ArrayList<NaveAbstracta> listaNaves;
    private static ListaNaves miListaNaves;

    private ListaNaves() {
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
        NaveAbstracta nave = findNaveJugador(pJugador);
        if(nave != null) nave.disparar();
    }

    /**
     * anade una nave a ListaNaves
     * @param pTipo
     * @param cX
     * @param cY
     * @return
     */
    public void anadirNave(TipoNave pTipo, int pJugador, int cX, int cY) {
        NaveAbstracta nave = FactoriaNave.getFactoriaNave().generar(pTipo,pJugador, cX, cY);
        listaNaves.add(nave);
    }

    public void reniciarListaNaves() {
        listaNaves.clear();
    }

    private NaveAbstracta findNaveId(int idNave) {
        return listaNaves.stream()
                .filter(nave -> nave.tienesId(idNave) && !nave.estaMuerta())
                .findFirst()
                .orElse(null);
    }
    private NaveAbstracta findNaveJugador(int pJugador) {
        return listaNaves.stream()
                .filter(nave -> nave.eresDelJugador(pJugador) && !nave.estaMuerta())
                .findFirst()
                .orElse(null);
    }


    public void alternarModoDisparo(int pJugador) {
        NaveAbstracta nave = findNaveJugador(pJugador);
        if(nave != null) nave.toggleStrategy();
    }

    /**
     * Se usa en Espacio para mover la nave a las coordenadas seleccionadas. Actualiza cada coordenada que compone a la nave
     * @param pJugador
     * @param dx
     * @param dy
     */
    public void moverNave(int pJugador, int dx, int dy) {
        NaveAbstracta nave = findNaveJugador(pJugador);
        if(nave != null) nave.moverNave(dx, dy);
    }

    /**
     * Metodo que llama gestorPartida para poner cada una de las naves en el espacio
     */
    public void ponerNavesEnEspacio(){
        listaNaves.stream() //J8
                .filter(nave -> !nave.estaMuerta())
                .forEach(nave -> nave.ponerEnEspacio());
    }

    //J8
    public void borrarNaves() {
        listaNaves.forEach(nave -> nave.borrar());
        reniciarListaNaves();
    }

    /**
     * Borra las Naves muertas y también borra las balas muertas de cada Nave
     */
    public void borrarMuertos(){
        listaNaves.removeIf(nave -> {
            if (nave.estaMuerta()) {
                nave.borrar();
                return true;
            }
            return false;
        });
    }

    public boolean quedanNaves() {
        return listaNaves.stream().anyMatch(nave -> !nave.estaMuerta());
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
        eventos.stream()
                .filter(evento -> evento.getCambio() && evento.getTipo() == TipoEntidad.nave)
                .map(evento -> findNaveId(evento.getIdEntidad()))
                .filter(Objects::nonNull)
                .forEach(nave -> nave.matar());

    }
}
