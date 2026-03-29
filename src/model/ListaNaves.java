package model;

import model.Factorias.FactoriaNave;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ListaNaves {
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
     * buscamos la nave con id "pIdNave" y le hacemos .disparar()
     *
     * @param pIdNave
     * @return
     */
    public Coordenada disparar(int pIdNave) {
        NaveAbstracta nave = findNave(pIdNave);
        if (nave != null) return nave.disparar();
        else return null;
    }

    /**
     * anade una nave a ListaNaves
     *
     * @param pColor
     * @param pCoord
     */
    public void anadirNave(String pColor, Coordenada pCoord) {
        NaveAbstracta nave = FactoriaNave.getFactoriaNave().generar("normal", pColor, pCoord);
        listaNaves.add(nave);
    }

    public Coordenada getCoordNave(int pIdNave) {
        NaveAbstracta nave = findNave(pIdNave);
        if (nave != null) {
            return nave.getCoord();
        }
        return null;
    }

    public void setCoordNave(int pIdNave, int cX, int cY) {
        NaveAbstracta nave = findNave(pIdNave);
        if (nave != null) {
            nave.setCoord(cX, cY);
        }
    }

    public int getNumNaves() {
        return listaNaves.size();
    }

    public void matarNave(int pIdNave) {
        NaveAbstracta nave = findNave(pIdNave);
        if (nave != null) {
            nave.matar();
        }
    }

    public void borrarListaNaves() {
        listaNaves.clear();
    }

    private NaveAbstracta findNave(int idNave) {
        for (NaveAbstracta nave : listaNaves) {
            if (nave.tienesId(idNave)) return nave;
        }
        return null;
    }

    private NaveAbstracta findNaveEn(int cX,int cY){
        for (NaveAbstracta nave : listaNaves) {
            if (nave.estasEn(cX,cY)) return nave;
        }
        return null;
    }

    public boolean existeNave(int idNave) {
        return findNave(idNave) != null;
    }

//TODO
    /*
    public void alternarModoDisparo(int pIdNave) {
        this.findNave(pIdNave).changeStrategy();
    }
*/
    public ArrayList<Coordenada> getCoordBalasNave(int pIdNave) {
        if (existeNave(pIdNave)) return listaNaves.get(pIdNave).getCoordBalas();
        else return null;
    }

    public void moverBalasNave(int pIdNave) {
        if (existeNave(pIdNave)) findNave(pIdNave).moverBalas();

    }

    public void borrarBalasNave(int pIdNave) {
        if (existeNave(pIdNave)) findNave(pIdNave).borrarBalas();
    }

    private Iterator<NaveAbstracta> getItr() {
        return listaNaves.iterator();
    }


    /**
     * Le llama Espacio
     *
     * @param cX
     * @param cY
     */
    public void matarNaveEn(int cX, int cY) {
        NaveAbstracta nave = this.findNaveEn(cX,cY);
        if ( nave != null) this.matarNave(nave.getId());
    }

    /**
     * Mira si TODAS las naves .estanMuertas()
     * @return
     */
    public boolean quedanNaves() {
        for(NaveAbstracta nave : listaNaves){
            if(!nave.estaMuerta()) return true;
        }
        return false;
    }
}
