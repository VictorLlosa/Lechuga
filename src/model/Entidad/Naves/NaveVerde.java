package model.Entidad.Naves;


import model.Formas.FactoriaFormas;
import model.Strategy.DisparoPixel;
import model.Strategy.DisparoFlecha;
import model.Strategy.DisparoStrategy;
import model.Tipos.TipoForma;

public class NaveVerde extends NaveAbstracta {
    public NaveVerde(int cX, int cY){
        super();
        this.setStrategies(new DisparoStrategy[]{ new DisparoPixel(),  new DisparoFlecha()});
        setForma(FactoriaFormas.getFactoriaFormas().crearForma(TipoForma.formaNave));
        inicializarCoordenadas(cX, cY);
        inicializarCannon(cX, cY);

    }
}
