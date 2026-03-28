package model;

import Strategy.DisparoStrategy;

/**
 * id Empieza en 0, es un atributo autoincremental
 */
public abstract class NaveAbstracta {

    private Coordenada coord;
    private Coordenada[] arrayCoord;
    private String tipo;
    private int id;
    private DisparoStrategy disparo;

    protected NaveAbstracta(String pTipo){
        this.tipo = pTipo;
        this.disparo = new disparoPixel();
    }

    protected void setCoord(int pX, int pY) {
        coord.setCoord(pX,pY);
    }

    protected Coordenada getCoord() {
        return coord;
    }

    protected void disparar(){
        disparo.disparar();
    }

    protected  String getTipo(){
        return this.tipo;
    }

    public boolean tienesId(int idNave) {
        return this.id == idNave;
    }

    public void changeStrategy(DisparoStrategy pSt){
        this.disparo = pSt;
    }

    public void moverBalas(){
        int num = listaBalas.getNumBalas();
        for (int i = 0; i < num; i++) {
            Coordenada coordBala = listaBalas.getCoordBala(i);
        }
    }

    public ArrayList<Coordenada> getCoordBalas() {
        return listaBalas.getCoordBalas();
    }

    public void borrarBala() {
        //TODO
    }

}
