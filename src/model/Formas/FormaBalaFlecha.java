package model.Formas;

public class FormaBalaFlecha extends FormaAbstracta{

    public FormaBalaFlecha(){
        super();
    }
    @Override
    protected int[][] crearForma() {
        return new int[][]{
                {0,1,0},
                {1,0,1},
                {0,0,0}

        };
    }
}
