package model;

public class GeneradorId {

    private static GeneradorId miGeneradorId;
    private int current = 0;

    private GeneradorId(){
    }
    public static GeneradorId getGeneradorId(){
        if(miGeneradorId == null) miGeneradorId = new GeneradorId();
        return miGeneradorId;
    }
    public int nextId() {
        return ++current;
    }

    public void reset() {
        current = 0;
    }
}
