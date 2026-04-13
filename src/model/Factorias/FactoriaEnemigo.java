package model.Factorias;
import model.Balas.Bala;
import model.Composite.CompositeCoordenada;
import model.Composite.Pixel;
import model.Enemigos.Enemigo;
import model.Enemigos.EnemigoAbstracto;
import model.Tipos.TipoBala;
import model.Tipos.TipoEnem;


public class FactoriaEnemigo {

    private static FactoriaEnemigo miFactoriaEnemigo = null;

    private FactoriaEnemigo(){
    }

    public static FactoriaEnemigo getFactoriaEnemigo(){
        if(miFactoriaEnemigo == null){
            miFactoriaEnemigo = new FactoriaEnemigo();
        }
        return miFactoriaEnemigo;
    }

    /**
     * //TODO poner niveles de enemigos
     * @param pTipo tipo enumerado, tipo de enemigo
     * @param pCoordCentro es el centro del Enemigo
     * @return
     */
    public EnemigoAbstracto generar(TipoEnem pTipo, Pixel pCoordCentro){
        EnemigoAbstracto enem;

        switch(pTipo){
            case TipoEnem.normal:
                enem = new Enemigo(pCoordCentro);
                break;

            default: throw new IllegalArgumentException();
        }
        return enem;
    }

}
