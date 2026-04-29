package model.MoverStrategy;

import model.CompositeCoordenada.Coordenada;
import model.Tipos.TipoEntidad;

public interface MoverStrategy {
    int[] mover(TipoEntidad pTipoEnt, int pId, Coordenada pCoord);
}
