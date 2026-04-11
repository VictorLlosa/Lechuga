package model.Factorias;

import model.Balas.*;
import model.Composite.CompositeCoordenada;
import model.Composite.Pixel;
import model.Espacio;

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
     * Aqui generamos las balas ya con su forma
     * @param pTipo
     * @param pX
     * @param pY
     * @return
     */
    public BalaAbstracta generar(String pTipo, int pX, int pY){
        BalaAbstracta b;

        switch(pTipo){
            case "pixel":
                b = new BalaPixel(pX,pY);
                break;

            case "rombo":
                b = new BalaRombo(pX,pY);
                break;
            case "flecha":
                b = new BalaFlecha(pX,pY);
                break;

            default: return null;
        }
        return b;
    }
}
