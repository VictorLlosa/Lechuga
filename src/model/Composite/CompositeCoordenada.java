package model.Composite;

import java.util.ArrayList;


public class CompositeCoordenada implements Coordenada{
    ArrayList<Coordenada> components;

    public CompositeCoordenada(){
        components = new ArrayList<>();
    }
    public void addComponent(Coordenada pC){
        components.add(pC);
    }

    public void deleteComponent(Coordenada pC){
        components.remove(pC);
    }

    public ArrayList<Coordenada> getChildren(){
        return components;
    }
    @Override
    public void actualizarCoord(int dx, int dy) {
        for(Coordenada comp : components ){
            comp.actualizarCoord(dx, dy);
        }
    }

    @Override
    public boolean esPixel() {
        return false;
    }

    @Override
    public boolean estasEnIntervalo(int pX0, int pX1, int pY0, int pY1) {
        boolean esta = true;
        for(Coordenada coord : components){
            esta = coord.estasEnIntervalo(pX0, pX1, pY0, pY1);
            if(!esta) return false;
        }
        return esta;
    }
}
