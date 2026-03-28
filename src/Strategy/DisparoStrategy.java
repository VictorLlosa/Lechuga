package Strategy;

import model.Coordenada;

/**
 * he puesto que tenga un propio atributo coordenada (pq es la madre de las strategy's)/
 * Las Interfaces no pueden instanciar atributos que indiquen un estado. Si algunos por defecto
 * como podria ser poner un color por defecto a las balas o valores ctes.
 */
public interface DisparoStrategy {

    public void setCentro(int cX, int cY) {}

    protected Coordenada getCentro() {}

    protected Coordenada[] getComponentesDelDisparo(){}

    public void setCoord(int cX, int cY){}

    public void actualizarPos(){}

    /**
     * Actualiza la relacion entre las posiciones de las casillas para que la bala se vea bien
     */
    public void darFormaAlDisparo(int cX, int cY){}

}
