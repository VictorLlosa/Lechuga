package model.DisparoStrategy;

import model.Entidad.Balas.BalaAbstracta;
import model.Factorias.FactoriaBala;
import model.MoverStrategy.MoverStrategy;
import model.Tipos.TipoBala;

/**
 * Representa un disparo de una unica bala
 */
public class DisparoPixel extends DisparoAbstracto{
    public DisparoPixel(int pMunicion, int pCadencia) {
        super(pMunicion, pCadencia);
    }

    /**
     * Dispara 1 pixel por encima de las coordenadas del cannon
     * @oaram pCoordCannon
     * @return Devuelve el CompositeCoordenada en la que se ha creado la bala
     */
    @Override
    public BalaAbstracta disparar(int cX, int cY, MoverStrategy pMovStrat) {
        if (!puedeDisparar()) return null;

        BalaAbstracta bala = FactoriaBala.getFactoriaBala().generar(TipoBala.pixel, cX, cY, pMovStrat);
        bala.ponerEnEspacio();
        return bala;
    }
}
