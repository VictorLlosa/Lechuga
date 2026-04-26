package model.Enemigos;

import model.Tipos.TipoEntidad;

public abstract class EnemigoBoss extends EnemigoAbstracto{
    private int vidas;
    public EnemigoBoss(int vidas){
        super();
        this.vidas = vidas;
    }


    @Override
    public void lethalHit(){
        vidas--;
        if (vidas == 0) matar();
    }

}
