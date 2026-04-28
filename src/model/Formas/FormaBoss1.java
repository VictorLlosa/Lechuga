package model.Formas;

public class FormaBoss1 extends FormaAbstracta{

    public FormaBoss1(){
        super();
    }

    @Override
    protected int[][] crearForma() {
        return new int[][]{
                {0,0,0,0,1,1,1,1,1,0,0,0,0},
                {0,0,0,0,1,0,0,0,1,0,0,0,0},
                {0,0,0,1,1,0,1,0,1,1,0,0,0},
                {0,0,1,0,1,0,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,1,1,1,0,0,1,0}
        };
    }
}
