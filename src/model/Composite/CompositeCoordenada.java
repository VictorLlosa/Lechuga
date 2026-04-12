package model.Composite;

import model.Entidad;
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
     * Actualizamos las coordendas en .this, si las nuevas son validas, vaciamos las coord iniciales con esp.vaciarCasillas(compAnterior)
     * y pintamos la nueva entidad pasandosela como parametro en: esp.colocarEntidad(components, pEnt);
     * @param pEnt Es el tipo de entidad (nave,enemigo o bala) que queremos mover
     * @param dx comp x a donde queremos mover la entidad
     * @param dy comp y a donde queremos mover la entidad
     * @return false si NO ha podido mover la Entidad
     */
    public boolean moverEnEspacio(int dx, int dy, Entidad pEnt) {
        Espacio.getEspacio().moverEntidad(generarNuevaCoord(dx, dy), pEnt);
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

}
