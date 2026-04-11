package model.Balas;

import model.Composite.CompositeCoordenada;
import model.Composite.Pixel;

public class BalaPixel extends BalaAbstracta{
    public BalaPixel(int pX, int pY) {
        CompositeCoordenada coordBala = new CompositeCoordenada();
        coordBala.addComponent(new Pixel(pX, pY - 1));
        super(coordBala);
    }

}
