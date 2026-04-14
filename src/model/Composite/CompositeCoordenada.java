package model.Composite;

import model.Tipos.TipoEntidad;

import java.util.ArrayList;
import java.util.Iterator;


public class CompositeCoordenada implements Coordenada {
    ArrayList<Coordenada> components;

    public CompositeCoordenada() {
        components = new ArrayList<>();
    }

    public void addComponent(Coordenada pC) {
        components.add(pC);
    }

    public void deleteComponent(Coordenada pC) {
        components.remove(pC);
    }

    public ArrayList<Coordenada> getChildren() {
        return components;
    }

    @Override
    public void actualizarCoord(int dx, int dy) {
        for (Coordenada comp : components) {
            comp.actualizarCoord(dx, dy);
        }
    }


    @Override
    public boolean estasEnIntervalo(int pX0, int pX1, int pY0, int pY1) {
        boolean esta = true;
        for (Coordenada coord : components) {
            esta = coord.estasEnIntervalo(pX0, pX1, pY0, pY1);
            if (!esta) return false;
        }
        return esta;
    }

    /**
     * Solo mueve la entidad si la coordenada es valida
     * @param dx
     * @param dy
     * @return Devuelve true si se ha movido, false si se ha intentado mover fuera del espacio
     */
    public boolean moverEnEspacio(int dx, int dy, TipoEntidad pEnt, int pIdEnt) {
        boolean exito = true;
        if(this.sePuedeMover()){
            for(Coordenada coord : components){
                if(!coord.moverEnEspacio(dx, dy, pEnt, pIdEnt)){
                    break; //Un pixel ya ha colisionado asique paramos
                }
            }
        }else{
            exito = false;
        }
        return exito;
    }

    /**
     * Métoodo que devuelve un true en el caso de que se pueda mover el elemento
     * @return
     */
    public boolean sePuedeMover(){
        boolean sePuedeMoverEntero = true;
        for(Coordenada coord : components){
            if(!coord.sePuedeMover()){
                sePuedeMoverEntero = false;
                break;
            }
        }
        return sePuedeMoverEntero;
    }

    @Override
    public Coordenada generarNuevaCoord(int dx, int dy) {
        CompositeCoordenada nuevas = new CompositeCoordenada();
        for (Coordenada c : components) {
            nuevas.addComponent(c.generarNuevaCoord(dx, dy));
        }
        return nuevas;
    }


    /**
     * Borra el elemento que hubiera en esa coordenada.
     * Es llamado por BalaAbstracta
     */
    @Override
    public void borrar(){
        for(Coordenada coord : components){
            coord.borrar();
        }
    }
}
