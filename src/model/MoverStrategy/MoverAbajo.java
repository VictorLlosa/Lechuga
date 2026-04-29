package model.MoverStrategy;

import model.CompositeCoordenada.Coordenada;
import model.Tipos.TipoEntidad;

public class MoverAbajo implements MoverStrategy {
    @Override
    public void mover(TipoEntidad pTipoEnt, int pId, Coordenada pCoord) {
        pCoord.moverEnEspacio(0, 1, TipoEntidad.bala, pId);
    }
}
