package model.MoverStrategy;

import model.CompositeCoordenada.Coordenada;
import model.Tipos.TipoEntidad;

public interface MoverStrategy {
    void mover(TipoEntidad pTipoEnt, int pId, Coordenada pCoord);
}
