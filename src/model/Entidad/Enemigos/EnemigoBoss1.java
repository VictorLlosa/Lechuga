package model.Entidad.Enemigos;

import model.CompositeCoordenada.CompositeCoordenada;
import model.CompositeCoordenada.Pixel;
import model.Tipos.TipoEntidad;

public class EnemigoBoss1 extends EnemigoBossAbstracto {

    private static int contadorMovimientos;

    public EnemigoBoss1(int cX, int cY){
        super(10);
        CompositeCoordenada coordForma = new CompositeCoordenada();
        coordForma.addComponent(new Pixel(cX, cY)); //TODO: CAMBIAR FORMA
        coordForma.addComponent(new Pixel(cX - 1, cY - 1));
        coordForma.addComponent(new Pixel(cX + 1, cY - 1));
        contadorMovimientos=0;
        this.setCoord(coordForma);
    }

    /**
     * Se mueve de lado a lado, diferente a un enemigo normal. Usa el atributo estático de la clase "contadorMovimientos"
     * @return
     */
    @Override
    public void moverEnEspacio() {
        if (contadorMovimientos < 10) {
            getCoord().moverEnEspacio(1, 0, TipoEntidad.boss1, getId()); //derecha
            //Al ser la primera fase del boss, debe de pasar una entidad de TipoEntidad "boss1".
        }
        else if (contadorMovimientos >= 10 && contadorMovimientos <20){
            getCoord().moverEnEspacio(-1, 0, TipoEntidad.boss1, getId()); //izquierda
        }
        else { //reseteo
            contadorMovimientos=0;
        }
        contadorMovimientos++;

    }

}
