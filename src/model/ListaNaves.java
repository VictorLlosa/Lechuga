package model;

import java.awt.*;
import java.util.ArrayList;

public class ListaNaves {
    private ArrayList<Nave> listaNaves;

    public ListaNaves() {
        this.listaNaves = new ArrayList<>();
    }

    public void anadirNave(int pId, Color pColor, Coordenada pCoord) {
        Nave nave = new Nave(pId,pColor, pCoord);
        listaNaves.add(nave);
    }

    public Color getColorNave(int pIdNave) {
        Nave nave = findNave(pIdNave);
        if(nave != null){
            return nave.getColor();
        }
        return null;
    }

    public Coordenada getCoordNave(int pIdNave) {
        Nave nave = findNave(pIdNave);
        if(nave != null){
            return nave.getCoord();
        }
        return null;
    }

    public void setCoordNave(int pIdNave, int cX, int cY) {
        Nave nave = findNave(pIdNave);
        if(nave != null){
           nave.setCoord(cX, cY);
        }
    }

    public int getNumNaves() {
        return listaNaves.size();
    }

    public void eliminarNave(int pIdNave) {
        Nave nave = findNave(pIdNave);
        if(nave != null){
            listaNaves.remove(nave);
        }


    }
    public void borrarListaNaves() {
       listaNaves.clear();
    }

    private Nave findNave(int idNave){
        for(int i = 0; i<listaNaves.size(); i++){
            if(listaNaves.get(i).tienesId(idNave)) return listaNaves.get(i);
        }
        return null;
    }

    public boolean existeNave(int idNave) {
        return findNave(idNave) != null;
    }
}
