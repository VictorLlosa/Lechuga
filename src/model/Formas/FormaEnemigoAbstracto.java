package model.Formas;

import model.CompositeCoordenada.Pixel;

public abstract class FormaEnemigoAbstracto extends FormaAbstractShootingEntity{

    @Override
        public Pixel getCannon(int cX, int cY){
            return getBottom(cX, cY + 2 );
        }
}
