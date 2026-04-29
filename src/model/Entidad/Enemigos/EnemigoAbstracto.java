package model.Entidad.Enemigos;

import model.DisparoStrategy.DisparoStrategy;
import model.Entidad.Balas.BalaAbstracta;
import model.Entidad.Balas.ListaBalas;
import model.Entidad.ShootingAbstractEntity;
import model.MoverStrategy.MoverAbajo;
import model.MoverStrategy.MoverStrategy;
import model.Tipos.TipoEnemigo;

public abstract class EnemigoAbstracto extends ShootingAbstractEntity {

    private MoverStrategy[] secuenciaMovimientos;
    private int movAct = 0;
    private TipoEnemigo tipoEnemigo;
    private int vidas;
    private MoverStrategy movimiento;

    protected EnemigoAbstracto(int pVidas, DisparoStrategy pDisparo, TipoEnemigo pTipoEnem){
        vidas = pVidas;
        tipoEnemigo = pTipoEnem;
        super(pDisparo);
    }

    public boolean eresTipo(TipoEnemigo pTipo){
        return tipoEnemigo == pTipo;
    }
    public abstract void moverEnEspacio();

    @Override
    public void disparar() {
        BalaAbstracta bala = getDisparo().disparar(getCannon().getX(), getCannon().getY(), new MoverAbajo());
        if(bala != null) ListaBalas.getListaBalas().anadirBala(bala);
    }

    public boolean haLLegadoAbajo() {
        return !getCoord().sePuedeMover(0, 1);
    }

    public void changeMoverStrategy(MoverStrategy pStrat){
        movimiento = pStrat;
    }
    public void lethalHit(){
        vidas--;
        if (vidas == 0) matar();
    }
    protected MoverStrategy getMovimiento(){
        return movimiento;
    }

    protected void setSecuenciaMovimientos(MoverStrategy[] pSecuencia) {
        this.secuenciaMovimientos = pSecuencia;
        movAct = 0;
        changeMoverStrategy(secuenciaMovimientos[movAct]);
    }

    public void toggleMoverStrategy() {
        this.movAct++;
        if (movAct > secuenciaMovimientos.length - 1) movAct = 0;
        this.changeMoverStrategy(secuenciaMovimientos[movAct]);

    }

    public int getSpawnY() {
        return getCannon().getY() + 5;
    }
}