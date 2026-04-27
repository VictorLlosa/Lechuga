package model.Strategy;

import model.Entidad.Balas.BalaAbstracta;
import model.Factorias.FactoriaBala;
import model.Tipos.TipoBala;

/**
 * Representa un disparo de una unica bala
 */
public class DisparoPixel extends DisparoAbstracto{
    public DisparoPixel() {
        super(-1, 8);
    }

    /**
     * Dispara 1 pixel por encima de las coordenadas del cannon
     * @oaram pCoordCannon
     * @return Devuelve el CompositeCoordenada en la que se ha creado la bala
     */
    @Override
    public BalaAbstracta disparar(int cX, int cY) {
        if (!puedeDisparar()) return null;

        BalaAbstracta bala = FactoriaBala.getFactoriaBala().generar(TipoBala.pixel, cX, cY);
        bala.ponerEnEspacio();
        return bala;
    }


}
