package model.Strategy;

import model.Coordenada;
import model.ListaBalas;

public class DisparoPixel implements DisparoStrategy{
    @Override
    public Coordenada disparar(int pX, int pY){
        Coordenada coordBala = new Coordenada(pX, pY - 1);
        return coordBala;

    }


}
