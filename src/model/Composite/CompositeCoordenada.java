package model.Composite;

import model.Espacio;

import java.util.ArrayList;
import java.util.Iterator;


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

    /**
     * Llama a Espacio. Despues el espacio comprueba que la coordenada nueva sea valida con todos los componentes de 'components'. El Espacio devuelve la coordenada nueva
     * y (si esta es valida) CompositeCoordenada actualiza la(s) coordenada(s) que haga(n) falta. Por ultimo, este metodo tb llama
     * a Espacio ppara actualizar la nueva coordenada en el.
     * @param dx
     * @param dy
     */
    public void moverNave(int dx, int dy){
        boolean todasCoordValidas = true;
        Espacio esp = Espacio.getEspacio(); //esto lo ponemos porque usamos mucho "Espacio.getEspacio()"
        Iterator<Coordenada> itr = components.iterator();
        while (itr.hasNext() && todasCoordValidas) {
            Coordenada c = itr.next();
            if (!esp.esCoordenadaValida(c)) {
                todasCoordValidas= false;
            }
            else {
                esp.getCordenada
            }
        }
    }
