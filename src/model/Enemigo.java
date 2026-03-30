package model;

public class Enemigo {
    private final Coordenada coord;
    private boolean muerto;
    private static int contadorId = 0; // Contador global para IDs
    private int id; // ID único de cada instancia

    public Enemigo (Coordenada pCoord) {
        coord = pCoord;
        muerto = false;
        id = contadorId++;
    }

    public void setCoord (int pX, int pY) {
        this.coord.setCoord(pX, pY);
    }

    public void actualizarPos() {
        coord.setCoord(coord.getX(), coord.getY() + 1);
    }

    public Coordenada getCoord() {
        if (coord == null) return null;
        // Devolver una copia para evitar que terceros modifiquen la coordenada directamente
        return new Coordenada(coord.getX(), coord.getY());
    }

    public boolean estaEn(int cX, int cY){
        return cX == coord.getX() && cY == coord.getY();
    }

    /**
     * Devuelve el valor del atributo "muerto"
     * @return
     */
    public boolean estaMuerto(){
        return this.muerto;
    }

    public void matar() {
        muerto = true;
    }

    public Integer getId() {
        return id;
    }
}
