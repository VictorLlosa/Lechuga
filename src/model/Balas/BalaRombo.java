package model.Balas;

import model.Balas.BalaAbstracta;
import model.Composite.CompositeCoordenada;
import model.Composite.Pixel;

public class BalaRombo extends BalaAbstracta{
    public BalaRombo(int pX, int pY) {
        CompositeCoordenada coordBala = new CompositeCoordenada();
        coordBala.addComponent(new Pixel(pX, pY - 3));
        coordBala.addComponent(new Pixel(pX,pY - 4 )); //arriba
        coordBala.addComponent(new Pixel(pX,pY - 2)); //abajo
        coordBala.addComponent(new Pixel(pX + 1,pY - 3)); //derecha
        coordBala.addComponent(new Pixel(pX - 1,pY - 3)); //izquierda
        coordBala.addComponent(new Pixel(pX - 1,pY - 4)); //esquina noroeste
        coordBala.addComponent(new Pixel(pX + 1,pY - 4)); //esquina noreste
        coordBala.addComponent(new Pixel(pX + 1,pY - 2)); //esquina sudeste
        coordBala.addComponent(new Pixel(pX - 1,pY - 2)); //esquina suroeste
        coordBala.addComponent(new Pixel(pX,pY - 5)); //punta superior
        coordBala.addComponent(new Pixel(pX,pY - 1)); //punta inferior
        coordBala.addComponent(new Pixel(pX - 2,pY - 3)); //punta izquierda
        coordBala.addComponent(new Pixel(pX + 2,pY - 3)); //punta derecha
        super(coordBala);
    }
}
