package model.Naves;

import model.CompositeCoordenada.CompositeCoordenada;
import model.CompositeCoordenada.Pixel;
import model.Strategy.DisparoPixel;
import model.Strategy.DisparoRombo;
import model.Strategy.DisparoStrategy;


public class NaveAzul extends NaveAbstracta{
    public NaveAzul(int cX, int cY){
        super();
        this.setStrategies(new DisparoStrategy[]{ new DisparoPixel(),  new DisparoRombo()});
        CompositeCoordenada coordForma = new CompositeCoordenada();
        coordForma.addComponent(new Pixel(cX,cY)); //centro
        coordForma.addComponent(new Pixel(cX,cY-1)); //arriba (tb es el cannon)
        coordForma.addComponent(new Pixel(cX-1,cY)); //izq
        coordForma.addComponent(new Pixel(cX+1,cY)); //derecha
        this.setCoord(coordForma);
        this.setCannon(new Pixel(cX , cY-2));
    }
}
