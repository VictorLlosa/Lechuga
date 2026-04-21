package model.Strategy;

import model.Balas.BalaAbstracta;
import model.Composite.Pixel;
import model.Factorias.FactoriaBala;
import model.Tipos.TipoBala;

public class DisparoRombo implements DisparoStrategy{
    int municion = 20;

    /**
     * Dispara 3 pixeles por encima de las coordenadas del cannon
     * Dos a los lados y una en medio justo en el pixel superior
     * @oaram pCoordCannon
     * @return Devuelve el CompositeCoordenada en la que se ha creado la bala
     */
    @Override
    public BalaAbstracta disparar(int cX, int cY){
        municion--;
        if(municion<0) return null;
        else {
            BalaAbstracta bala = FactoriaBala.getFactoriaBala().generar(TipoBala.rombo, cX, cY);
            bala.ponerEnEspacio();
            return bala;
        }
    }
}
