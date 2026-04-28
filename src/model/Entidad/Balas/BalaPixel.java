package model.Entidad.Balas;

import model.Formas.FactoriaFormas;
import model.Tipos.TipoForma;

public class BalaPixel extends BalaAbstracta{
    public BalaPixel(int cX, int cY) {
        super();
        setForma(FactoriaFormas.getFactoriaFormas().crearForma(TipoForma.formaBalaPixel));
        inicializarCoordenadas(cX, cY);
    }
}
