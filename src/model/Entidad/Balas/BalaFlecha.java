package model.Entidad.Balas;

import model.Formas.FactoriaFormas;
import model.Tipos.TipoForma;

public class BalaFlecha extends BalaAbstracta {
    public BalaFlecha(int cX, int cY){
        super();
        setForma(FactoriaFormas.getFactoriaFormas().crearForma(TipoForma.formaBalaFlecha));
        inicializarCoordenadas(cX, cY);
    }
}
