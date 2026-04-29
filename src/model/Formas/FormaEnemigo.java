package model.Formas;

public class FormaEnemigo extends FormaEnemigoAbstracto{

    public FormaEnemigo(){
        super();
    }

    @Override
    protected int[][] crearForma() {
        return new int[][]{
                {0,0,1,1,0,0},
                {0,1,1,1,1,0},
                {0,1,0,0,1,0},
                {0,0,0,0,0,0}
        };
    }
}
