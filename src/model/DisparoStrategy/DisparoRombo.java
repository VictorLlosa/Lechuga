package model.DisparoStrategy;

import model.Entidad.Balas.BalaAbstracta;
import model.Factorias.FactoriaBala;
import model.Tipos.TipoBala;

public class DisparoRombo extends DisparoAbstracto {

    public DisparoRombo(int pMunicion, int pCadencia) {
        super(pMunicion, pCadencia);
    }

    @Override
    public BalaAbstracta disparar(int cX, int cY) {
        if (!puedeDisparar()) return null;

        BalaAbstracta bala = FactoriaBala.getFactoriaBala().generar(TipoBala.rombo, cX, cY - 1);//TODO: OFFSET ESTA HARDCODEADO

        bala.ponerEnEspacio();
        return bala;
    }
}
