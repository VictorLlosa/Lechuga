package model.Factorias;
import model.Formas.*;
import model.Tipos.TipoForma;

public class FactoriaFormas {
    private static FactoriaFormas miFactoriaFormas;

    private FactoriaFormas(){
    }

    public static FactoriaFormas getFactoriaFormas(){
        if(miFactoriaFormas == null){
            miFactoriaFormas = new FactoriaFormas();
        }
        return miFactoriaFormas;
    }

    public FormaAbstracta crearForma(TipoForma pTipo){
        FormaAbstracta forma;
        switch (pTipo){
            case formaBalaPixel:
                forma = new FormaBalaPixel();
                break;
            case formaBalaFlecha:
                forma = new FormaBalaFlecha();
                break;
            case formaBalaRombo:
                forma = new FormaBalaRombo();
                break;
            case formaNave:
                forma = new FormaNave();
                break;
            case formaEnemigo:
                forma = new FormaEnemigo();
                break;
            case formaBoss1:
                forma = new FormaBoss1();
                break;
            case formaBoss2:
                forma = new FormaBoss2();
                break;
            default:
                forma = null;
        }
        return forma;
    }

}
