package model.Factorias;

import model.Entidad.Naves.NaveAbstracta;
import model.Entidad.Naves.NaveAzul;
import model.Entidad.Naves.NaveRoja;
import model.Entidad.Naves.NaveVerde;
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
     */
    public NaveAbstracta generar(TipoNave pTipo,int pJugador, int cX, int cY){
        NaveAbstracta nave;

        switch(pTipo){
            case TipoNave.green:
                nave = new NaveVerde(pJugador, cX, cY);
                break;

            case TipoNave.blue:
                nave = new NaveAzul(pJugador, cX, cY);
                break;

            case TipoNave.red:
                nave = new NaveRoja(pJugador, cX, cY);
                break;

            default:
                throw new IllegalArgumentException();
        }

        return nave;
    }
}
