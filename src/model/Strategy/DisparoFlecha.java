package model.Strategy;

import model.Balas.BalaAbstracta;
import model.Composite.Pixel;
import model.Factorias.FactoriaBala;
import model.Tipos.TipoBala;


public class DisparoFlecha implements DisparoStrategy{
    /**
     * Dispara 3 pixeles por encima de las coordenadas del cannon
     * Dos a los lados y una en medio justo en el pixel superior
     * @oaram pCoordCannon
     * @return Devuelve el CompositeCoordenada en la que se ha creado la bala
     */
    int municion = 30;
    @Override
    public BalaAbstracta disparar(Pixel pCoordCannon){
        municion--;
        if(municion<0){
            return null;
        }
        else{
            BalaAbstracta bala = FactoriaBala.getFactoriaBala().generar(TipoBala.flecha, pCoordCannon);
            bala.ponerEnEspacio();
            return bala;
        }
    }
}
