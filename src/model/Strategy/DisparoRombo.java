package model.Strategy;

import model.Entidad.Balas.BalaAbstracta;
import model.Factorias.FactoriaBala;
import model.Tipos.TipoBala;

public class DisparoRombo extends DisparoAbstracto {

    public DisparoRombo() {
        super(20, 7);
    }

    @Override
    public BalaAbstracta disparar(int cX, int cY) {
        if (!puedeDisparar()) return null;

        BalaAbstracta bala = FactoriaBala.getFactoriaBala().generar(TipoBala.rombo, cX, cY);

        bala.ponerEnEspacio();
        return bala;
    }
}
