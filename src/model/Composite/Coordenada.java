package model.Composite;

public interface Coordenada {
    void actualizarCoord(int dx, int dy);
    boolean esPixel();

    boolean estasEnIntervalo(int pX0, int pX1, int pY0, int pY1);
}
