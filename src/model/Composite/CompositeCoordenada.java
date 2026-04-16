package model.Composite;

import model.Tipos.TipoEntidad;

import java.util.ArrayList;


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


    /**
     *
     * @param dx
     * @param dy
     * @param pEnt
     * @param pIdEnt
     * @return
     */
    public boolean moverEnEspacio(int dx, int dy, TipoEntidad pEnt, int pIdEnt) {
        boolean exito = true;
        if(this.sePuedeMover(dx, dy) && !this.colisiona(dx, dy, pEnt, pIdEnt)){
            this.borrar();
            this.actualizarCoord(dx, dy);
            this.colocarEnEspacio(pEnt, pIdEnt);

        }else{
            exito = false;
        }
        return exito;
    }

    @Override
    public boolean colocarEnEspacio(TipoEntidad pEnt, int pIdEnt) {
        boolean exito = true;
        if(sePuedeMover(0,0) && !colisiona(0,0,pEnt, pIdEnt)){
            for(Coordenada coord : components){
                coord.colocarEnEspacio(pEnt, pIdEnt);
            }
        }else{
            exito = false;
        }
        return exito;
    }

    @Override
    public boolean abajo() {
        boolean abajo = false;
        for(Coordenada coord : components){
            if(coord.abajo()){
                abajo = true;
                break;
            }
        }
        return abajo;
    }

    @Override
    public boolean colisiona(int dx, int dy, TipoEntidad pEnt, int pIdEnt) {
        boolean colisiona = false;
        for(Coordenada coord: components){
            if(coord.colisiona(dx, dy, pEnt, pIdEnt)){
                colisiona = true;
                break;
            }
        }
        return colisiona;
    }

    /**
     * Métoodo que devuelve un true en el caso de que se pueda mover el elemento
     * @return
     */
    @Override
    public boolean sePuedeMover(int dx, int dy){
        boolean sePuedeMoverEntero = true;
        for(Coordenada coord : components){
            if(!coord.sePuedeMover(dx, dy)){
                sePuedeMoverEntero = false;
                break;
            }
        }
        return sePuedeMoverEntero;
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
