package model.Entidad.Enemigos;
import model.DisparoStrategy.DisparoPixel;
import model.Formas.FactoriaFormas;
import model.Tipos.TipoEntidad;
import model.Tipos.TipoForma;

public class Enemigo extends EnemigoAbstracto{
    public Enemigo (int cX, int cY) {
        super(1, new DisparoPixel(-1, 8));
        setForma(FactoriaFormas.getFactoriaFormas().crearForma(TipoForma.formaEnemigo));
        inicializarCoordenadas(cX, cY);
        inicializarCannon(cX,cY);
    }

    public void ponerEnEspacio() {
        getCoord().colocarEnEspacio(TipoEntidad.enemigo, getId());
    }

    public void moverEnEspacio() {
        getCoord().moverEnEspacio(0, 1, TipoEntidad.enemigo, getId());
    }
}
