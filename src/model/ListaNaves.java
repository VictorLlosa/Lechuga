package model;

import model.Factorias.FactoriaNave;

import java.awt.*;
import java.util.ArrayList;

public class ListaNaves {
    private ArrayList<NaveAbstracta> listaNaves;
    private static ListaNaves miListaNaves;

    public ListaNaves() {
        this.listaNaves = new ArrayList<>();
    }

    public static ListaNaves getListaNaves(){
        if(miListaNaves == null){
            miListaNaves = new ListaNaves();
        }
        return miListaNaves;
    }

    /**
     * buscamos la nave con id "pIdNave" y le hacemos .disparar()
     * @param pIdNave
     * @return
     */
    public Coordenada disparar(int pIdNave){
        NaveAbstracta nave = findNave(pIdNave);
        return nave.disparar();
    }

    /**
     * anade una nave a ListaNaves
     * @param pId
     * @param pTipo
     * @param pCoord
     */
    public void anadirNave(int pId, String pTipo, Coordenada pCoord) {
        NaveAbstracta nave = FactoriaNave.getFactoriaNave().generar(pTipo);
        listaNaves.add(nave);
    }

    public String getColorNave(int pIdNave) {
        NaveAbstracta nave = findNave(pIdNave);
        if(nave != null){
            return nave.getTipo();
        }
        return null;
    }

    public Coordenada getCoordNave(int pIdNave) {
        NaveAbstracta nave = findNave(pIdNave);
        if(nave != null){
            return nave.getCoord();
        }
        return null;
    }

    public void setCoordNave(int pIdNave, int cX, int cY) {
        NaveAbstracta nave = findNave(pIdNave);
        if(nave != null){
           nave.setCoord(cX, cY);
        }
    }

    public int getNumNaves() {
        return listaNaves.size();
    }

    public void eliminarNave(int pIdNave) {
        NaveAbstracta nave = findNave(pIdNave);
        if(nave != null){
            listaNaves.remove(nave);
        }


    }
    public void borrarListaNaves() {
       listaNaves.clear();
    }

    private NaveAbstracta findNave(int idNave){
        for(int i = 0; i<listaNaves.size(); i++){
            if(listaNaves.get(i).tienesId(idNave)) return listaNaves.get(i);
        }
        return null;
    }

    public boolean existeNave(int idNave) {
        return findNave(idNave) != null;
    }

    /**
     *
     */
    public void alternarModoDisparo(int pIdNave){
        this.findNave(pIdNave).changeStrategy();
    }

    public ArrayList<Coordenada> getCoordBalasNave(int pIdNave){
        if(existeNave(pIdNave)) return listaNaves.get(pIdNave).getCoordBalas();
        else return null;
    }

}
