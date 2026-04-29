package model.Formas;

import model.CompositeCoordenada.CompositeCoordenada;
import model.CompositeCoordenada.Pixel;

public abstract class FormaAbstracta{
    private int[][] forma;

    protected FormaAbstracta(){
        forma = crearForma();
    }

    protected abstract int[][] crearForma();

    public CompositeCoordenada getComposite(int cX, int cY){
        CompositeCoordenada comp = new CompositeCoordenada();

        int offsetX = forma[0].length / 2;
        int offsetY = forma.length / 2;

        for(int f = 0; f < forma.length; f++){
            for(int c = 0; c < forma[0].length; c++){
                if(forma[f][c] == 1){
                    comp.addComponent(new Pixel(cX + (c - offsetX), cY + (f - offsetY)));
                }
            }
        }

        return comp;
    }

    public Pixel getTop(int cX, int cY){
        int offsetY = forma.length / 2;
        return new Pixel(cX, cY - offsetY);
    }
    public Pixel getBottom(int cX, int cY){
        int offsetY = forma.length / 2;
        return new Pixel(cX, cY + offsetY);
    }
}
