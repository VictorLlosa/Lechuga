package model.Entidad.Enemigos;

import model.CompositeCoordenada.CompositeCoordenada;
import model.Formas.FactoriaFormas;

import model.Tipos.TipoEntidad;
import model.Tipos.TipoForma;

public class EnemigoBoss2 extends EnemigoAbstracto{

    private int contadorMovimientos;

    public EnemigoBoss2(int cX, int cY) {
        super(20);
        contadorMovimientos = 0;
        setForma(FactoriaFormas.getFactoriaFormas().crearForma(TipoForma.formaBoss2));
        inicializarCoordenadas(cX, cY);
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


