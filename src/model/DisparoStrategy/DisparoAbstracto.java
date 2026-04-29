package model.DisparoStrategy;

import model.Entidad.Balas.BalaAbstracta;
import model.MoverStrategy.MoverStrategy;

public abstract class DisparoAbstracto implements DisparoStrategy {

    private int municion;
    private int contDisparo = 0;
    private int cadencia;


    protected DisparoAbstracto(int pMunicion, int pCadencia){
        municion = pMunicion;
        cadencia = pCadencia;
    }
    protected boolean puedeDisparar() {

        if (!cooldownOk()) return false;

        contDisparo = 0;

        if (!tieneMunicion()) return false;

        if (municion != -1) municion--;

        return true;
    }

    private boolean tieneMunicion() {
        return municion == -1 || municion > 0;
    }

    private boolean cooldownOk() {
        return ++contDisparo >= cadencia;
    }


    public abstract BalaAbstracta disparar(int cX, int cY, MoverStrategy pMovStrat);
}
