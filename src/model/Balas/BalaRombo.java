package model.Balas;

import model.Composite.CompositeCoordenada;
import model.Composite.Pixel;

public class BalaRombo extends BalaAbstracta{
    public BalaRombo(int cX, int cY){
        CompositeCoordenada coordForma = new CompositeCoordenada();
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
        super.setCoord(coordForma);
    }
}
