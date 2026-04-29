package model.Entidad.Balas;

import model.Formas.FactoriaFormas;
import model.MoverStrategy.MoverStrategy;
import model.Tipos.TipoForma;

public class BalaRombo extends BalaAbstracta{
    public BalaRombo(int cX, int cY, MoverStrategy pMovStrat){
        super(pMovStrat);
        setForma(FactoriaFormas.getFactoriaFormas().crearForma(TipoForma.formaBalaRombo));
        inicializarCoordenadas(cX, cY);
    }
}
