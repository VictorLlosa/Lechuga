package model.Entidad.Balas;

import model.CompositeCoordenada.CompositeCoordenada;
import model.CompositeCoordenada.Pixel;

public class BalaFlecha extends BalaAbstracta {
    public BalaFlecha(int cX, int cY){
        super();
        CompositeCoordenada coordForma = new CompositeCoordenada();
        coordForma.addComponent(new Pixel(cX, cY - 2));
        coordForma.addComponent(new Pixel(cX - 1, cY - 1));
        coordForma.addComponent(new Pixel(cX + 1, cY - 1));
        super.setCoord(coordForma);
    }
}
