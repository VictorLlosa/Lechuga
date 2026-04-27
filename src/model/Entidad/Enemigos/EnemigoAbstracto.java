package model.Entidad.Enemigos;

import model.Entidad.EntidadAbstracta;
import model.Tipos.TipoEntidad;

public abstract class EnemigoAbstracto extends EntidadAbstracta {

    private int vidas;
    protected EnemigoAbstracto(int pVidas){
        vidas = pVidas;
    }

    public abstract void moverEnEspacio();

    public void ponerEnEspacio() {
        getCoord().colocarEnEspacio(TipoEntidad.enemigo, getId());
    }

    public boolean haLLegadoAbajo() {
        return !getCoord().sePuedeMover(0, 1);
    }

    public void lethalHit(){
        vidas--;
        if (vidas == 0) matar();
    }
}