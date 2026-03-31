package model.Factorias;
import model.Composite.Pixel;
import model.Enemigo;
import model.EnemigoAbstracto;


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
     * @param pTipo
     * @param pCoord es el centro del Enemigo
     * @return
     */
    public EnemigoAbstracto generar(String pTipo, Pixel pCoord){
        EnemigoAbstracto e;
        switch(pTipo){
            case "normal":
                e = new Enemigo(pCoord);
                break;
            default: return null;
        }
        return e;
    }
}
