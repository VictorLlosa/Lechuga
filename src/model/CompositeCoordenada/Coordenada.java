package model.CompositeCoordenada;

import model.Tipos.TipoEntidad;

public interface Coordenada {
    void actualizarCoord(int dx, int dy);

    boolean colisiona(int dx, int dy, TipoEntidad pEnt, int pIdEnt);

    boolean sePuedeMover(int dx, int dy);

    void moverEnEspacio(int dx, int dy, TipoEntidad pEnt, int pIdEnt);

    void borrar();

    boolean colocarEnEspacio(TipoEntidad tipoEntidad, int id);


}
