package model.MoverStrategy;

import model.CompositeCoordenada.Coordenada;
import model.Tipos.TipoEntidad;

public class MoverIzquierda implements MoverStrategy{
    public int[] mover(TipoEntidad pTipoEnt, int pId, Coordenada pCoord){
        if(pCoord.moverEnEspacio(-1, 0, pTipoEnt, pId))return new int[]{-1, 0};
        else return null;
    };
}
