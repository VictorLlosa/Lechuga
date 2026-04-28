package model.Factorias;
import model.Entidad.Enemigos.*;
import model.Tipos.TipoEnemigo;
import model.Tipos.TipoEntidad;


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


    public EnemigoAbstracto generar(TipoEnemigo pTipo, int cX, int cY){
        EnemigoAbstracto enem;

        switch(pTipo){
            case normal:
                enem = new Enemigo(cX, cY);
                break;
            case boss1:
                enem = new EnemigoBoss1(cX, cY);
                break;
            case boss2:
                enem = new EnemigoBoss2(cX, cY);
                break;
            default: throw new IllegalArgumentException();
        }
        return enem;
    }

}
