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
     * Aquí se les da forma a las balas, con coordForma
     * @param pTipo
     * @param pCoordCentro
     * @return
     */
    public BalaAbstracta generar(TipoBala pTipo, Pixel pCoordCentro){
        BalaAbstracta b;

        switch(pTipo){
            case TipoBala.pixel:
                b = new Bala(crearFormaBalaPixel(pCoordCentro));
                break;
            case TipoBala.flecha:

                b = new Bala(crearFormaBalaFlecha(pCoordCentro));
                break;
            case TipoBala.rombo:

                b = new Bala(crearFormaBalaRombo(pCoordCentro));
                break;
            default: throw new IllegalArgumentException();
        }
        return b;
    }

    /**
     * Añadimos las componentes que forman la bala
     * @param pCentro
     * @return
     */
    private CompositeCoordenada crearFormaBalaPixel(Pixel pCentro){
        CompositeCoordenada coordForma = new CompositeCoordenada();
        int cX = pCentro.getX();
        int cY = pCentro.getY();
        coordForma.addComponent(new Pixel(cX, cY));
        return coordForma;
    }
    private CompositeCoordenada crearFormaBalaFlecha(Pixel pCentro){
        CompositeCoordenada coordForma = new CompositeCoordenada();
        int cX = pCentro.getX();
        int cY = pCentro.getY();
        coordForma.addComponent(new Pixel(cX, cY - 2));
        coordForma.addComponent(new Pixel(cX - 1, cY - 1));
        coordForma.addComponent(new Pixel(cX + 1, cY - 1));
        return coordForma;
    }
    private CompositeCoordenada crearFormaBalaRombo(Pixel pCentro){
        CompositeCoordenada coordForma = new CompositeCoordenada();
        int cX = pCentro.getX();
        int cY = pCentro.getY();
        coordForma.addComponent(new Pixel(cX, cY - 3));
        coordForma.addComponent(new Pixel(cX,cY - 4 )); //arriba
        coordForma.addComponent(new Pixel(cX,cY - 2)); //abajo
        coordForma.addComponent(new Pixel(cX + 1,cY - 3)); //derecha
        coordForma.addComponent(new Pixel(cX - 1,cY - 3)); //izquierda
        coordForma.addComponent(new Pixel(cX - 1,cY - 4)); //esquina noroeste
        coordForma.addComponent(new Pixel(cX + 1,cY - 4)); //esquina noreste
        coordForma.addComponent(new Pixel(cX + 1,cY - 2)); //esquina sudeste
        coordForma.addComponent(new Pixel(cX - 1,cY - 2)); //esquina suroeste
        coordForma.addComponent(new Pixel(cX,cY - 5)); //punta superior
        coordForma.addComponent(new Pixel(cX,cY - 1)); //punta inferior
        coordForma.addComponent(new Pixel(cX - 2,cY - 3)); //punta izquierda
        coordForma.addComponent(new Pixel(cX + 2,cY - 3)); //punta derecha
        return coordForma;
    }
}
