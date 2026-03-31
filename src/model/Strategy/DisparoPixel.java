package model.Strategy;

import model.Composite.CompositeCoordenada;
import model.Composite.Coordenada;
import model.Composite.Pixel;

/**
 * Representa un disparo de una unica bala
 */
public class DisparoPixel implements DisparoStrategy{
    /**
     * Dispara 1 pixel por encima de las coordenadas del cannon
     * @oaram pX comp. X del pixel del cannon
     * @param pY com. Y del pixel del cannon
     * @return Devuelve el CompositeCoordenada en la que se ha creado la bala
     */
    @Override
    public CompositeCoordenada disparar(int pX, int pY){
        CompositeCoordenada coordBala = new CompositeCoordenada();
        coordBala.addComponent(new Pixel(pX, pY - 1));
        return coordBala;

    }


}
