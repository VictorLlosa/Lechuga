package model.MoverStrategy;

import model.CompositeCoordenada.Coordenada;
import model.Tipos.TipoEntidad;

public class MoverDerecha implements MoverStrategy {
    @Override
    public int[] mover(TipoEntidad pTipoEnt, int pId, Coordenada pCoord) {
        pCoord.moverEnEspacio(1, 0, pTipoEnt, pId);
        return new int[]{1, 0};
    }
}
