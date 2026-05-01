package model.Entidad.Balas;

import model.Factorias.FactoriaFormas;
import model.MoverStrategy.MoverStrategy;
import model.Tipos.TipoForma;

public class BalaFlecha extends BalaAbstracta {
    public BalaFlecha(int cX, int cY, MoverStrategy pMovStrat){
        super(pMovStrat);
        setForma(FactoriaFormas.getFactoriaFormas().crearForma(TipoForma.formaBalaFlecha));
        inicializarCoordenadas(cX, cY);
    }
}
