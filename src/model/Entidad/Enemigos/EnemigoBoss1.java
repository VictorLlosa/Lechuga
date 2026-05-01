package model.Entidad.Enemigos;

import model.DisparoStrategy.DisparoFlecha;
import model.Factorias.FactoriaFormas;
import model.MoverStrategy.MoverAbajo;
import model.MoverStrategy.MoverDerecha;
import model.MoverStrategy.MoverIzquierda;
import model.MoverStrategy.MoverStrategy;
import model.Tipos.TipoEnemigo;
import model.Tipos.TipoEntidad;
import model.Tipos.TipoForma;

public class EnemigoBoss1 extends EnemigoAbstracto {

    public EnemigoBoss1(int cX, int cY){
        super(10, new DisparoFlecha(-1, 5), TipoEnemigo.boss1);
        MoverStrategy[] secuenciaMovs= {new MoverDerecha(), new MoverAbajo(), new MoverIzquierda(), new MoverAbajo()};
        setSecuenciaMovimientos(secuenciaMovs);
        setForma(FactoriaFormas.getFactoriaFormas().crearForma(TipoForma.formaBoss1));
        inicializarCoordenadas(cX, cY);
        inicializarCannon(cX,cY);
    }

    public void ponerEnEspacio() {
        getCoord().colocarEnEspacio(TipoEntidad.boss1, getId());
    }

    /**
     * Se mueve de lado a lado, diferente a un enemigo normal. Usa el atributo estático de la clase "contadorMovimientos"
     * @return
     */
    @Override
    public void moverEnEspacio() {
        int[] diffs = getMovimiento().mover(TipoEntidad.boss1, getId(),getCoord());
        if(diffs != null) getCannon().actualizarCoord(diffs[0],diffs[1]);
    }

}
