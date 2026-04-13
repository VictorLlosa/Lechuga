package model.Factorias;

import model.Composite.Pixel;
import model.Naves.NaveAbstracta;
import model.Naves.NaveAzul;
import model.Naves.NaveRoja;
import model.Naves.NaveVerde;
import model.Tipos.TipoNave;

public class FactoriaNave {
    private static FactoriaNave miFactoriaNave = null;

    private FactoriaNave(){

    }

    public static FactoriaNave getFactoriaNave(){
        if(miFactoriaNave == null){
            miFactoriaNave = new FactoriaNave();
        }
        return miFactoriaNave;
    }

    /**
     * Dependiendo del tipo de nave, tendrá un modo de disparo u otro (unas Strategy's diferentes)
     * @param pTipo
     * @param pCoordCentro
     * @return
     */
    public NaveAbstracta generar(TipoNave pTipo, Pixel pCoordCentro){
        NaveAbstracta nave;

        switch(pTipo){
            case TipoNave.green:
                nave = new NaveVerde(pCoordCentro);
                break;

            case TipoNave.blue:
                nave = new NaveAzul(pCoordCentro);
                break;

            case TipoNave.red:
                nave = new NaveRoja(pCoordCentro);
                break;

            default:
                throw new IllegalArgumentException();
        }

        return nave;
    }
}
