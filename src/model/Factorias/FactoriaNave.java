package model.Factorias;

import model.Composite.Pixel;
import model.NaveAbstracta;
import model.Nave;
import model.Strategy.DisparoFlecha;
import model.Strategy.DisparoPixel;
import model.Strategy.DisparoRombo;
import model.Strategy.DisparoStrategy;
import model.TipoNave;

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
    public NaveAbstracta generar(TipoNave pTipo, Pixel pCoord){//TODO: CAMBIAR STRING por Enum TipoNave
        Nave nave;
        DisparoStrategy[] strategies;

        switch(pTipo){
            case TipoNave.green:
                strategies = new DisparoStrategy[]{
                        new DisparoPixel(),
                        new DisparoFlecha()
                };
                nave = new Nave(pCoord.getX(), pCoord.getY(), strategies);
                break;

            case TipoNave.blue:
                strategies = new DisparoStrategy[]{
                        new DisparoPixel(),
                        new DisparoRombo()
                };
                nave = new Nave(pCoord.getX(), pCoord.getY(), strategies);
                break;

            case TipoNave.red:
                strategies = new DisparoStrategy[]{
                        new DisparoPixel(),
                        new DisparoRombo(),
                        new DisparoRombo()
                };
                nave = new Nave(pCoord.getX(), pCoord.getY(), strategies);
                break;

            default:
                return null;
        }

        return nave;
    }
}
