package model.Enemigos;

import model.CompositeCoordenada.CompositeCoordenada;
import model.CompositeCoordenada.Pixel;
import model.Tipos.TipoEntidad;

public class EnemigoBoss2 extends EnemigoBoss {

    public EnemigoBoss2(int cX, int cY){
        super(20);
        CompositeCoordenada coordForma = new CompositeCoordenada();
        coordForma.addComponent(new Pixel(cX, cY)); //TODO: CAMBIAR FORMA
        coordForma.addComponent(new Pixel(cX - 1, cY - 1));
        coordForma.addComponent(new Pixel(cX + 1, cY - 1));
        coordForma.addComponent(new Pixel(cX + 2, cY - 1));
        coordForma.addComponent(new Pixel(cX - 2, cY - 1));

        this.setCoord(coordForma);
    }

    /**
     * Lo mismo que EnemigoBoss1
     * @return
     */
    @Override
    public void moverEnEspacio() {
        getCoord().moverEnEspacio(0,1, TipoEntidad.boss2, getId());
    }

}
