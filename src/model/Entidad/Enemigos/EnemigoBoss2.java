package model.Entidad.Enemigos;

import model.DisparoStrategy.DisparoRombo;
import model.Factorias.FactoriaFormas;

import model.MoverStrategy.MoverDerecha;
import model.MoverStrategy.MoverIzquierda;
import model.MoverStrategy.MoverStrategy;
import model.Tipos.TipoEnemigo;
import model.Tipos.TipoEntidad;
import model.Tipos.TipoForma;

public class EnemigoBoss2 extends EnemigoAbstracto{

    public EnemigoBoss2(int cX, int cY) {
        super(30, new DisparoRombo(20,7), TipoEnemigo.boss2, TipoEntidad.boss2);
        MoverStrategy[] secuenciaMovs= {new MoverDerecha(), new MoverIzquierda()};
        setSecuenciaMovimientos(secuenciaMovs);
        setForma(FactoriaFormas.getFactoriaFormas().crearForma(TipoForma.formaBoss2));
        inicializarCoordenadas(cX, cY);
        inicializarCannon(cX,cY);
    }
}


