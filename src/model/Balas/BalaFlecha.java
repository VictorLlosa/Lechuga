package model.Balas;

import model.Composite.CompositeCoordenada;
import model.Composite.Pixel;

public class BalaFlecha extends  BalaAbstracta {
    public BalaFlecha(int pX, int pY) {
        CompositeCoordenada coordBala = new CompositeCoordenada();
        coordBala.addComponent(new Pixel(pX, pY - 2));
        coordBala.addComponent(new Pixel(pX - 1, pY - 1));
        coordBala.addComponent(new Pixel(pX + 1, pY - 1));
        super(coordBala);
    }
}
