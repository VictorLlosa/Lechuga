package model.Formas;

import model.CompositeCoordenada.Coordenada;
import model.CompositeCoordenada.Pixel;

public class FormaNave extends FormaAbstractShootingEntity{

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

    @Override
    public Pixel getCannon(int cX, int cY){
        return getTop(cX, cY - 2);
    }
}
