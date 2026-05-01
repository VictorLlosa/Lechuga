package model.Entidad.Enemigos;
import model.DisparoStrategy.DisparoPixel;
import model.Factorias.FactoriaFormas;
import model.MoverStrategy.MoverAbajo;
import model.MoverStrategy.MoverStrategy;
import model.Tipos.TipoEnemigo;
import model.Tipos.TipoEntidad;
import model.Tipos.TipoForma;

public class Enemigo extends EnemigoAbstracto{
    public Enemigo (int cX, int cY) {
        super(1, new DisparoPixel(-1, 8), TipoEnemigo.normal);
        MoverStrategy[] secuenciaMovs= {new MoverAbajo()};
        setSecuenciaMovimientos(secuenciaMovs);
        setForma(FactoriaFormas.getFactoriaFormas().crearForma(TipoForma.formaEnemigo));
        inicializarCoordenadas(cX, cY);
        inicializarCannon(cX,cY);
    }

    public void ponerEnEspacio() {
        getCoord().colocarEnEspacio(TipoEntidad.enemigo, getId());
    }

    public void moverEnEspacio() {
        int[] diffs = getMovimiento().mover(TipoEntidad.enemigo, getId(),getCoord());
        if(diffs != null) getCannon().actualizarCoord(diffs[0],diffs[1]);
    }
}
