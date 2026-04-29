package model.Factorias;

import model.Entidad.Balas.*;
import model.MoverStrategy.MoverStrategy;
import model.Tipos.TipoBala;


public class FactoriaBala
{
    private static FactoriaBala miFactoriaBala = null;

    private FactoriaBala(){
    }

    public static FactoriaBala getFactoriaBala(){
        if(miFactoriaBala == null){
            miFactoriaBala = new FactoriaBala();
        }
        return miFactoriaBala;
    }

    /**
     *
     * @param pTipo
     * @param cX
     * @param cY
     * @return
     */
    public BalaAbstracta generar(TipoBala pTipo, int cX, int cY, MoverStrategy pMovStrat){
        BalaAbstracta b;

        switch(pTipo){
            case TipoBala.pixel:
                b = new BalaPixel(cX, cY, pMovStrat);
                break;
            case TipoBala.flecha:

                b = new BalaFlecha(cX, cY, pMovStrat);
                break;
            case TipoBala.rombo:

                b = new BalaRombo(cX, cY, pMovStrat);
                break;
            default: throw new IllegalArgumentException();
        }
        return b;
    }

}
