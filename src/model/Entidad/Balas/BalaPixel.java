package model.Entidad.Balas;

import model.Formas.FactoriaFormas;
import model.MoverStrategy.MoverStrategy;
import model.Tipos.TipoForma;

public class BalaPixel extends BalaAbstracta{
    public BalaPixel(int cX, int cY, MoverStrategy pMovStrat) {
        super(pMovStrat);
        setForma(FactoriaFormas.getFactoriaFormas().crearForma(TipoForma.formaBalaPixel));
        inicializarCoordenadas(cX, cY);
    }
}
