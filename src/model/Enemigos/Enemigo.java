package model.Enemigos;
import model.Composite.CompositeCoordenada;
import model.Composite.Pixel;

import java.util.Observable;

public class Enemigo extends EnemigoAbstracto{
    public Enemigo (int cX, int cY) {
        super();
        CompositeCoordenada coordForma = new CompositeCoordenada();
        coordForma.addComponent(new Pixel(cX, cY));
        coordForma.addComponent(new Pixel(cX, cY - 1));
        coordForma.addComponent(new Pixel(cX + 1, cY + 1));
        coordForma.addComponent(new Pixel(cX - 1, cY + 1));
        coordForma.addComponent(new Pixel(cX + 1, cY));
        coordForma.addComponent(new Pixel(cX - 1, cY));
        coordForma.addComponent(new Pixel(cX + 2, cY));
        coordForma.addComponent(new Pixel(cX -2 , cY));
        coordForma.addComponent(new Pixel(cX + 1, cY - 1));
        coordForma.addComponent(new Pixel(cX -1 , cY - 1));
        coordForma.addComponent(new Pixel(cX + 2, cY - 2));
        coordForma.addComponent(new Pixel(cX - 2, cY - 2));
        coordForma.addComponent(new Pixel(cX - 3, cY + 1));
        coordForma.addComponent(new Pixel(cX -3 , cY + 2));
        coordForma.addComponent(new Pixel(cX + 3, cY + 1));
        coordForma.addComponent(new Pixel(cX  + 3 , cY + 2));

        this.setCoord(coordForma);
    }

}
