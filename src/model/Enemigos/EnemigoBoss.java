package model.Enemigos;

import model.Composite.CompositeCoordenada;
import model.Composite.Pixel;

public class EnemigoBoss extends EnemigoAbstracto{
    public EnemigoBoss(int cX, int cY){
        super();
        CompositeCoordenada coordForma = new CompositeCoordenada();
        coordForma.addComponent(new Pixel(cX, cY)); //TODO: CAMBIAR FORMA
        coordForma.addComponent(new Pixel(cX - 1, cY - 1));
        coordForma.addComponent(new Pixel(cX + 1, cY - 1));
        this.setCoord(coordForma);
    }

    public boolean estaMuerto (){
        return this.estaMuerto();
    }
}
