package model.DisparoStrategy;

import model.Entidad.Balas.BalaAbstracta;
import model.Factorias.FactoriaBala;
import model.Tipos.TipoBala;


public class DisparoFlecha extends DisparoAbstracto {
    /**
     * Dispara 3 pixeles por encima de las coordenadas del cannon
     * Dos a los lados y una en medio justo en el pixel superior
     * @oaram pCoordCannon
     * @return Devuelve el CompositeCoordenada en la que se ha creado la bala
     */
    public DisparoFlecha(int pMunicion, int pCadencia){
        super(pMunicion,pCadencia);
    }
    @Override
    public BalaAbstracta disparar(int cX, int cY){
        if (!puedeDisparar()) return null;

        BalaAbstracta bala = FactoriaBala.getFactoriaBala().generar(TipoBala.flecha, cX, cY);

        bala.ponerEnEspacio();
        return bala;
    }
}
