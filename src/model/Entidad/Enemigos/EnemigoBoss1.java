package model.Entidad.Enemigos;

import model.CompositeCoordenada.CompositeCoordenada;
import model.CompositeCoordenada.Pixel;
import model.Formas.FactoriaFormas;
import model.Formas.FormaAbstracta;
import model.Tipos.TipoEntidad;
import model.Tipos.TipoForma;

public class EnemigoBoss1 extends EnemigoAbstracto {

    private static int contadorMovimientos;

    public EnemigoBoss1(int cX, int cY){
        super(10);
        contadorMovimientos=0;
        setForma(FactoriaFormas.getFactoriaFormas().crearForma(TipoForma.formaBoss1));
        inicializarCoordenadas(cX, cY);
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
