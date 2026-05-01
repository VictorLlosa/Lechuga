package model.Entidad.Naves;


import model.Factorias.FactoriaFormas;
import model.DisparoStrategy.DisparoFlecha;
import model.DisparoStrategy.DisparoPixel;
import model.DisparoStrategy.DisparoRombo;
import model.DisparoStrategy.DisparoStrategy;
import model.Tipos.TipoForma;

public class NaveRoja extends NaveAbstracta{
    public NaveRoja(int cX, int cY){
        super(new DisparoPixel(-1, 8));
        this.setStrategies(new DisparoStrategy[]{ new DisparoPixel(-1, 8),  new DisparoRombo(20, 7),  new DisparoFlecha(30,7)});
        setForma(FactoriaFormas.getFactoriaFormas().crearForma(TipoForma.formaNave));
        inicializarCoordenadas(cX, cY);
        inicializarCannon(cX, cY);
    }
}
