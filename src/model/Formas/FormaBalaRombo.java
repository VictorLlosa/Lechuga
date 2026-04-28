package model.Formas;

public class FormaBalaRombo extends FormaAbstracta{

    public FormaBalaRombo(){
        super();
    }

    @Override
    protected int[][] crearForma() {
        return new int[][]{
                {0,1,0},
                {1,1,1},
                {0,1,0}
        };
    }
}
