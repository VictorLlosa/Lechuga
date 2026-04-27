package model.Entidad.Enemigos;

import model.CompositeCoordenada.CompositeCoordenada;
import model.CompositeCoordenada.Pixel;
import model.Tipos.TipoEntidad;

public class EnemigoBoss2 extends EnemigoBossAbstracto {

    public static int contadorMovimientos;

    public EnemigoBoss2(int cX, int cY) {
        super(20);
        CompositeCoordenada coordForma = new CompositeCoordenada();
        coordForma.addComponent(new Pixel(cX, cY)); //TODO: CAMBIAR FORMA
        coordForma.addComponent(new Pixel(cX - 1, cY - 1));
        coordForma.addComponent(new Pixel(cX + 1, cY - 1));
        coordForma.addComponent(new Pixel(cX + 2, cY - 1));
        coordForma.addComponent(new Pixel(cX - 2, cY - 1));
        contadorMovimientos = 0;
        this.setCoord(coordForma);
    }

    /**
     * Lo mismo que EnemigoBoss1
     *
     * @return
     */
    @Override
    public void moverEnEspacio() {
        if (contadorMovimientos < 10) {
            getCoord().moverEnEspacio(1, 0, TipoEntidad.boss2, getId()); //derecha
        }
        else if (contadorMovimientos >= 10 && contadorMovimientos < 20) {
            getCoord().moverEnEspacio(0, 1, TipoEntidad.boss2, getId()); //abajo
        }
        else if (contadorMovimientos >= 20 && contadorMovimientos < 40) { //izquierda (x2)
            getCoord().moverEnEspacio(-1, 0, TipoEntidad.boss2, getId());
        }
        else if (contadorMovimientos >= 40 && contadorMovimientos < 50) { //abajo
            getCoord().moverEnEspacio(0, 1, TipoEntidad.boss2, getId());
        }
       else if (contadorMovimientos >= 50 && contadorMovimientos < 60) { //derecha
            getCoord().moverEnEspacio(1, 0, TipoEntidad.boss2, getId());
        }
        else { //reseteo
            contadorMovimientos = 0;
        }
        contadorMovimientos++;
    }
}

