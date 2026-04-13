package model.Composite;

import model.Tipos.TipoEntidad;
import model.Espacio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;


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
     * Pasamos del arrayList de coordenadas a un ArrayList de Pixel. Recursivamente, iteramos las listas para convertir los composites en arraylists de pixel
     * @return
     */
    @Override
    public ArrayList<Pixel> getPixeles() {
        ArrayList<Pixel> pixeles = new ArrayList<Pixel>();
        for(Coordenada c : components){
            pixeles.addAll(c.getPixeles());//te añade cada elemento de la coleccion 'c.getPixeles()' a la lista 'pixeles'
        }
        return pixeles;
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

    private Iterator<Coordenada> getIterator(){
        return components.iterator();
    }


    /**
     *Solo mueve la entidad si la coordenada es valida
     * @param dx
     * @param dy
     * @return la nave necesita saber si se ha podido mover para actualizar su cannon
     */
    public boolean moverEnEspacio(int dx, int dy, TipoEntidad pEnt) {
        boolean movido = Espacio.getEspacio().moverEntidad(this, generarNuevaCoord(dx, dy), pEnt);
        if(movido) actualizarCoord(dx,dy);
        return movido;
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
     * Mira si todas las coordenadas coinciden con el objeto que le hemos pasdao. Este metodo se usa en 'EstasEn' de Bala, que se usa en findBala y en
     * existebalaEn, para eliminar las balas de una nave, por ejemplo
     * @param o El CompositeCoordenada con el que queremos comparar los elementos
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CompositeCoordenada that = (CompositeCoordenada) o;
        ArrayList<Pixel> misPixeles = this.getPixeles();
        ArrayList<Pixel> susPixeles = that.getPixeles();
        return misPixeles.containsAll(susPixeles) && susPixeles.containsAll(misPixeles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPixeles());
    }

    @Override
    public boolean validarMovimiento(int dx, int dy) {
        for(Coordenada coord : components){
            if(!coord.validarMovimiento(dx, dy)) return false;
        }
        return true;
    }
}
