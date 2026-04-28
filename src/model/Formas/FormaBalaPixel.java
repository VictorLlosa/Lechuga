package model.Formas;

import model.CompositeCoordenada.CompositeCoordenada;

public class FormaBalaPixel extends FormaAbstracta{
    public FormaBalaPixel(){
        super();
    }

    @Override
    protected int[][] crearForma() {
        return new int[][]{new int[]{1}};
    }

}
