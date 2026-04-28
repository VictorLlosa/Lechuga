package model.Entidad.Naves;


import model.Formas.FactoriaFormas;
import model.Strategy.DisparoFlecha;
import model.Strategy.DisparoPixel;
import model.Strategy.DisparoRombo;
import model.Strategy.DisparoStrategy;
import model.Tipos.TipoForma;

public class NaveRoja extends NaveAbstracta{
    public NaveRoja(int cX, int cY){
        super();
        this.setStrategies(new DisparoStrategy[]{ new DisparoPixel(),  new DisparoRombo(),  new DisparoFlecha()});
        setForma(FactoriaFormas.getFactoriaFormas().crearForma(TipoForma.formaNave));
        inicializarCoordenadas(cX, cY);
        setCannon(getForma().getTop(cX,cY-1));
    }
}
