package model.Strategy;

import model.Entidad.Balas.BalaAbstracta;

/**
 * he puesto que tenga un propio atributo coordenada (pq es la madre de las strategy's)/
 * Las Interfaces no pueden instanciar atributos que indiquen un estado. Si algunos por defecto
 * como podria ser poner un color por defecto a las balas o valores ctes.
 */
public interface DisparoStrategy {
    public BalaAbstracta disparar(int cX, int cY);
}
