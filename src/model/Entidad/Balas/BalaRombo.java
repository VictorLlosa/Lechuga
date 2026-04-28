package model.Entidad.Balas;

import model.Formas.FactoriaFormas;
import model.Tipos.TipoForma;

public class BalaRombo extends BalaAbstracta{
    public BalaRombo(int cX, int cY){
        super();
        setForma(FactoriaFormas.getFactoriaFormas().crearForma(TipoForma.formaBalaRombo));
        inicializarCoordenadas(cX, cY);
    }
}
