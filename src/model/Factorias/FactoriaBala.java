package model.Factorias;

import model.Balas.*;
import model.Composite.CompositeCoordenada;
import model.Composite.Pixel;
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
    public BalaAbstracta generar(TipoBala pTipo, int cX, int cY){
        BalaAbstracta b;

        switch(pTipo){
            case TipoBala.pixel:
                b = new BalaPixel(cX, cY);
                break;
            case TipoBala.flecha:

                b = new BalaFlecha(cX, cY);
                break;
            case TipoBala.rombo:

                b = new BalaRombo(cX, cY);
                break;
            default: throw new IllegalArgumentException();
        }
        return b;
    }

}
