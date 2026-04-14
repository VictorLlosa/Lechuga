package model.Enemigos;
import model.Composite.CompositeCoordenada;
import model.Composite.Pixel;

import java.util.Observable;

public class Enemigo extends EnemigoAbstracto{
    public Enemigo (int cX, int cY) {
        super();
        CompositeCoordenada coordForma = new CompositeCoordenada();
        coordForma.addComponent(new Pixel(cX, cY - 2));
        coordForma.addComponent(new Pixel(cX - 1, cY - 1));
        coordForma.addComponent(new Pixel(cX + 1, cY - 1));
        this.setCoord(coordForma);
    }

}
