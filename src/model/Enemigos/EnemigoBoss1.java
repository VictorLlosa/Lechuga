package model.Enemigos;

import model.CompositeCoordenada.CompositeCoordenada;
import model.CompositeCoordenada.Pixel;
import model.Tipos.TipoEntidad;

public class EnemigoBoss1 extends EnemigoBoss{

    public EnemigoBoss1(int cX, int cY){
        super(10);
        CompositeCoordenada coordForma = new CompositeCoordenada();
        coordForma.addComponent(new Pixel(cX, cY)); //TODO: CAMBIAR FORMA
        coordForma.addComponent(new Pixel(cX - 1, cY - 1));
        coordForma.addComponent(new Pixel(cX + 1, cY - 1));
        this.setCoord(coordForma);
    }

    /**
     * Al ser boss, debe de pasar una entidad de TipoEntidad "boss1"
     * @return
     */
    @Override
    public void moverEnEspacio() {
        getCoord().moverEnEspacio(0,1, TipoEntidad.boss1, getId());
    }

}
