package model.Factorias;
import model.Enemigos.Enemigo;
import model.Enemigos.EnemigoAbstracto;
import model.Enemigos.EnemigoBoss;
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


    public EnemigoAbstracto generar(TipoEnem pTipo, int cX, int cY){
        EnemigoAbstracto enem;

        switch(pTipo){
            case TipoEnem.normal:
                enem = new Enemigo(cX, cY);
                break;
            case TipoEnem.boss:
                enem = new EnemigoBoss(cX, cY);
                break;
            default: throw new IllegalArgumentException();
        }
        return enem;
    }

}
