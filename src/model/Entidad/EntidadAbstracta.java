package model.Entidad;

import model.CompositeCoordenada.Coordenada;
import model.Formas.FormaAbstracta;
import model.GeneradorId;
import model.Tipos.TipoEntidad;

public abstract class EntidadAbstracta {
    private Coordenada coord;
    private FormaAbstracta forma;
    private TipoEntidad tipoEntidad;
    private final int id;
    private boolean muerta = false;

    protected EntidadAbstracta(TipoEntidad pTipoent) {
        this.id = GeneradorId.getGeneradorId().nextId();
        tipoEntidad = pTipoent;
    }

    public int getId() {
        return id;
    }

    public void matar() {
        muerta = true;
    }

    public boolean estaMuerta() {
        return muerta;
    }

    public void borrar() {
        coord.borrar();
    }

    protected Coordenada getCoord() {
        return coord;
    }

    public abstract void ponerEnEspacio();
    public abstract void moverEnEspacio();

    protected void inicializarCoordenadas(int cX, int cY){
        coord = forma.getComposite(cX,cY);
    }
    protected void setForma(FormaAbstracta pForma) {
        forma = pForma;
    }


    protected FormaAbstracta getForma() {
        return forma;
    }
    protected TipoEntidad getTipoEntidad(){ return tipoEntidad;}
}
