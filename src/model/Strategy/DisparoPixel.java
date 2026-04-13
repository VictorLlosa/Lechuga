package model.Strategy;

import model.Balas.BalaAbstracta;
import model.Composite.Pixel;
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
    public BalaAbstracta disparar(Pixel pCoordCannon){
        return FactoriaBala.getFactoriaBala().generar(TipoBala.pixel,pCoordCannon);
    }


}
