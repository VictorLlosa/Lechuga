package model.Strategy;

import model.Balas.BalaAbstracta;
import model.Factorias.FactoriaBala;
import model.Tipos.TipoBala;

/**
 * Representa un disparo de una unica bala
 */
public class DisparoPixel implements DisparoStrategy{
    /**
     * Dispara 1 pixel por encima de las coordenadas del cannon
     * @oaram pCoordCannon
     * @return Devuelve el CompositeCoordenada en la que se ha creado la bala
     */
    @Override
    public BalaAbstracta disparar(int cX, int cY) {
        BalaAbstracta bala = FactoriaBala.getFactoriaBala().generar(TipoBala.pixel, cX, cY);
        bala.ponerEnEspacio();
        return bala;
    }


}
