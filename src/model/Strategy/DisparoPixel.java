package model.Strategy;

import model.Balas.BalaAbstracta;
import model.Factorias.FactoriaBala;

/**
 * Representa un disparo de una unica bala
 */
public class DisparoPixel implements DisparoStrategy{
    /**
     * Dispara 1 pixel por encima de las coordenadas del cannon
     * @oaram pX comp. X del pixel del cannon
     * @param pY com. Y del pixel del cannon
     * @return Devuelve el CompositeCoordenada en la que se ha creado la bala
     */
    @Override
    public BalaAbstracta disparar(int pX, int pY){
        return FactoriaBala.getFactoriaBala().generar("pixel", pX, pY);
    }


}
