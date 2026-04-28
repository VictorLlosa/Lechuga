package model.Formas;

import model.CompositeCoordenada.Coordenada;

public class FormaNave extends FormaAbstracta{

    public FormaNave(){
        super();
    }

    @Override
    protected int[][] crearForma() {
        return new int[][]{
                {0,0,1,0,0},
                {0,1,1,1,0},
                {1,1,0,1,1},
        };
    }
}
