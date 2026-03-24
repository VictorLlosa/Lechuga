package model.Factorias;

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

    }
}
