package model.Entidad.Enemigos;

import model.CompositeCoordenada.CompositeCoordenada;
import model.CompositeCoordenada.Pixel;
import model.Tipos.TipoEntidad;

public class EnemigoBoss1 extends EnemigoBossAbstracto {

    private static int contadorMovimientos;

    public EnemigoBoss1(int cX, int cY){
        super(10);
        CompositeCoordenada coordForma = new CompositeCoordenada();
        coordForma.addComponent(new Pixel(cX, cY));
        coordForma.addComponent(new Pixel(cX, cY - 1));
        coordForma.addComponent(new Pixel(cX + 1, cY + 1));
        coordForma.addComponent(new Pixel(cX - 1, cY + 1));
        coordForma.addComponent(new Pixel(cX + 1, cY));
        coordForma.addComponent(new Pixel(cX - 1, cY));
        coordForma.addComponent(new Pixel(cX + 2, cY));
        coordForma.addComponent(new Pixel(cX -2 , cY));
        coordForma.addComponent(new Pixel(cX + 1, cY - 1));
        coordForma.addComponent(new Pixel(cX -1 , cY - 1));
        coordForma.addComponent(new Pixel(cX + 2, cY - 2));
        coordForma.addComponent(new Pixel(cX - 2, cY - 2));
        coordForma.addComponent(new Pixel(cX - 3, cY + 1));
        coordForma.addComponent(new Pixel(cX -3 , cY + 2));
        coordForma.addComponent(new Pixel(cX + 3, cY + 1));
        coordForma.addComponent(new Pixel(cX  + 3 , cY + 2));

        contadorMovimientos=0;
        this.setCoord(coordForma);
    }

    /**
     * Se mueve de lado a lado, diferente a un enemigo normal. Usa el atributo estático de la clase "contadorMovimientos"
     * @return
     */
    @Override
    public void moverEnEspacio() {
        int dx,dy = 0;

        contadorMovimientos++;
        contadorMovimientos++;
        if (contadorMovimientos < 10) { //derecha
            dx =1;
        }
        else if (contadorMovimientos < 20) {//abajo
            dx = -1;
        } else { //reseteo
            contadorMovimientos=0;
            return;
        }
        getCoord().moverEnEspacio(dx, dy, TipoEntidad.boss1, getId());

    }

}
