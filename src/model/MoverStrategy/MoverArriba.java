package model.MoverStrategy;

import model.CompositeCoordenada.Coordenada;
import model.Tipos.TipoEntidad;

public class MoverArriba implements MoverStrategy{
    @Override
    public int[] mover(TipoEntidad pTipoEnt, int pId, Coordenada pCoord) {
        if(pCoord.moverEnEspacio(0, -1, pTipoEnt, pId)) return new int[]{0, -1};
        else return null;
    }
}
