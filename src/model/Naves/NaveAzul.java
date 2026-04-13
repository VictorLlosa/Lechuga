package model.Naves;

import model.Composite.CompositeCoordenada;
import model.Composite.Pixel;
import model.Strategy.DisparoFlecha;
import model.Strategy.DisparoPixel;
import model.Strategy.DisparoRombo;
import model.Strategy.DisparoStrategy;

import java.util.Observable;

public class NaveAzul extends NaveAbstracta{
    public NaveAzul(Pixel pCoordCentro){
        super();
        this.setStrategies(new DisparoStrategy[]{ new DisparoPixel(),  new DisparoRombo()});
        int cX = pCoordCentro.getX();
        int cY = pCoordCentro.getY();
        CompositeCoordenada coordForma = new CompositeCoordenada();
        coordForma.addComponent(new Pixel(cX,cY)); //centro
        coordForma.addComponent(new Pixel(cX,cY-1)); //arriba (tb es el cannon)
        coordForma.addComponent(new Pixel(cX-1,cY)); //izq
        coordForma.addComponent(new Pixel(cX+1,cY)); //derecha
        this.setCoord(coordForma);
        this.setCannon(new Pixel(pCoordCentro.getX(),pCoordCentro.getY()-1));
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
