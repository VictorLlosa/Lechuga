package model.Entidad.Naves;


import model.Formas.FactoriaFormas;
import model.Formas.FormaNave;
import model.Strategy.DisparoPixel;
import model.Strategy.DisparoRombo;
import model.Strategy.DisparoStrategy;
import model.Tipos.TipoForma;


public class NaveAzul extends NaveAbstracta{
    public NaveAzul(int cX, int cY){
        super();
        this.setStrategies(new DisparoStrategy[]{ new DisparoPixel(),  new DisparoRombo()});
        setForma(FactoriaFormas.getFactoriaFormas().crearForma(TipoForma.formaNave));
        inicializarCoordenadas(cX, cY);
        inicializarCannon(cX, cY);
    }
}
