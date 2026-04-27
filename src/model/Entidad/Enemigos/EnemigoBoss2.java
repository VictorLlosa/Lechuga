package model.Entidad.Enemigos;

import model.CompositeCoordenada.CompositeCoordenada;
import model.CompositeCoordenada.Pixel;
import model.Tipos.TipoEntidad;

public class EnemigoBoss2 extends EnemigoBossAbstracto {

    private int contadorMovimientos;

    public EnemigoBoss2(int cX, int cY) {
        super(20);
        CompositeCoordenada coordForma = new CompositeCoordenada();

// Fila superior (y -6)
        coordForma.addComponent(new Pixel(cX - 9, cY - 6));
        coordForma.addComponent(new Pixel(cX - 6, cY - 6));
        coordForma.addComponent(new Pixel(cX - 3, cY - 6));
        coordForma.addComponent(new Pixel(cX,     cY - 6));
        coordForma.addComponent(new Pixel(cX + 3, cY - 6));
        coordForma.addComponent(new Pixel(cX + 6, cY - 6));
        coordForma.addComponent(new Pixel(cX + 9, cY - 6));

// Fila 2 (y -3)
        coordForma.addComponent(new Pixel(cX - 12, cY - 3));
        coordForma.addComponent(new Pixel(cX - 9,  cY - 3));
        coordForma.addComponent(new Pixel(cX - 6,  cY - 3));
        coordForma.addComponent(new Pixel(cX - 3,  cY - 3));
        coordForma.addComponent(new Pixel(cX,      cY - 3));
        coordForma.addComponent(new Pixel(cX + 3,  cY - 3));
        coordForma.addComponent(new Pixel(cX + 6,  cY - 3));
        coordForma.addComponent(new Pixel(cX + 9,  cY - 3));
        coordForma.addComponent(new Pixel(cX + 12, cY - 3));

// Fila 3 (y)
        coordForma.addComponent(new Pixel(cX - 15, cY));
        coordForma.addComponent(new Pixel(cX - 12, cY));
        coordForma.addComponent(new Pixel(cX - 9,  cY));
        coordForma.addComponent(new Pixel(cX - 6,  cY));
        coordForma.addComponent(new Pixel(cX - 3,  cY));
        coordForma.addComponent(new Pixel(cX,      cY));
        coordForma.addComponent(new Pixel(cX + 3,  cY));
        coordForma.addComponent(new Pixel(cX + 6,  cY));
        coordForma.addComponent(new Pixel(cX + 9,  cY));
        coordForma.addComponent(new Pixel(cX + 12, cY));
        coordForma.addComponent(new Pixel(cX + 15, cY));

// Parte inferior (y +3)
        coordForma.addComponent(new Pixel(cX - 12, cY + 3));
        coordForma.addComponent(new Pixel(cX - 6,  cY + 3));
        coordForma.addComponent(new Pixel(cX,      cY + 3));
        coordForma.addComponent(new Pixel(cX + 6,  cY + 3));
        coordForma.addComponent(new Pixel(cX + 12, cY + 3));
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
        int dx,dy = 0;
        contadorMovimientos++;

        if (contadorMovimientos < 10) {
             dx =1;
            //derecha
        }
        else if (contadorMovimientos < 20) {
            dx = 0;
            dy = 1; //abajo
        }
        else if (contadorMovimientos < 40) { //izquierda (x2)
            dx = -1;
        }
        else if (contadorMovimientos < 50) { //abajo
            dx = 0;
            dy = 1;
        }
        else if (contadorMovimientos < 60) { //derecha
            dx = 1;
        }
        else { //reseteo
            contadorMovimientos = 0;
            return;
        }
        getCoord().moverEnEspacio(dx, dy, TipoEntidad.boss2, getId());
    }
}

