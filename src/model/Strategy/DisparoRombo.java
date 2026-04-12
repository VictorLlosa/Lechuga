package model.Strategy;

import model.Balas.BalaAbstracta;
import model.Factorias.FactoriaBala;

public class DisparoRombo implements DisparoStrategy{
    /**
     * Dispara 3 pixeles por encima de las coordenadas del cannon
     * Dos a los lados y una en medio justo en el pixel superior
     * @oaram pX comp. X del pixel del cannon
     * @param pY com. Y del pixel del cannon
     * @return Devuelve el CompositeCoordenada en la que se ha creado la bala
     */
    @Override
    public BalaAbstracta disparar(int pX, int pY){
        return FactoriaBala.getFactoriaBala().generar("rombo", pX, pY);
    }
}
