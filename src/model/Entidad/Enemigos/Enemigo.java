package model.Entidad.Enemigos;
import model.Formas.FactoriaFormas;
import model.Tipos.TipoEntidad;
import model.Tipos.TipoForma;

public class Enemigo extends EnemigoAbstracto{
    public Enemigo (int cX, int cY) {
        super(1);
        setForma(FactoriaFormas.getFactoriaFormas().crearForma(TipoForma.formaEnemigo));
        inicializarCoordenadas(cX, cY);
    }

    public void moverEnEspacio() {
        getCoord().moverEnEspacio(0, 1, TipoEntidad.enemigo, getId());
    }
}
