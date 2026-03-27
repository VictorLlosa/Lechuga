package model.Factorias;

import model.NaveAbstracta;
import model.Nave;

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
     *
     * @param pTipo el color de la nave
     * @return
     */
    public NaveAbstracta generar(String pTipo){
        return new Nave(pTipo);
    }
}
