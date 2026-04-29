package model.Entidad.Naves;


import model.Formas.FactoriaFormas;
import model.DisparoStrategy.DisparoPixel;
import model.DisparoStrategy.DisparoFlecha;
import model.DisparoStrategy.DisparoStrategy;
import model.Tipos.TipoForma;

public class NaveVerde extends NaveAbstracta {
    public NaveVerde(int cX, int cY){
        super(new DisparoPixel(-1, 8));
        this.setStrategies(new DisparoStrategy[]{ new DisparoPixel(-1, 8),  new DisparoFlecha(30,7)});
        setForma(FactoriaFormas.getFactoriaFormas().crearForma(TipoForma.formaNave));
        inicializarCoordenadas(cX, cY);
        inicializarCannon(cX, cY );

    }
}
