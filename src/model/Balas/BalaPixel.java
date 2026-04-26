package model.Balas;

import model.CompositeCoordenada.CompositeCoordenada;
import model.CompositeCoordenada.Pixel;

public class BalaPixel extends BalaAbstracta{
    public BalaPixel(int cX, int cY) {
        super();
        CompositeCoordenada coordForma = new CompositeCoordenada();
        coordForma.addComponent(new Pixel(cX, cY));
        super.setCoord(coordForma);
    }
}
