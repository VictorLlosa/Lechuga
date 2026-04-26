package model.Naves;

import model.CompositeCoordenada.CompositeCoordenada;
import model.CompositeCoordenada.Pixel;
import model.Strategy.DisparoPixel;
import model.Strategy.DisparoFlecha;
import model.Strategy.DisparoStrategy;

public class NaveVerde extends NaveAbstracta {
    public NaveVerde(int cX, int cY){
        super();
        this.setStrategies(new DisparoStrategy[]{ new DisparoPixel(),  new DisparoFlecha()});
        CompositeCoordenada coordForma = new CompositeCoordenada();
        coordForma.addComponent(new Pixel(cX,cY)); //centro
        coordForma.addComponent(new Pixel(cX,cY-1)); //arriba (tb es el cannon)
        coordForma.addComponent(new Pixel(cX-1,cY)); //izq
        coordForma.addComponent(new Pixel(cX+1,cY)); //derecha
        this.setCoord(coordForma);
        this.setCannon(new Pixel(cX,cY-2));

    }
}
