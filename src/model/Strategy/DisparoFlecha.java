package model.Strategy;

import model.Coordenada;
import model.ListaBalas;
import model.ListaNaves;

public class DisparoFlecha {
    /**
     * Si la coordenada es valida, anadimos la bala a listaBalas y hacemos un
     * "cambiarObjeto("Bala")" en la matriz de casillas.
     * @param idNave id de la nave. Si es null, el metodo no hace nada (hay que quitarlo en el futuro)
     */
    public void disparar(int idNave) {
        Coordenada coordNave = ListaNaves.getListaNaves().getCoordNave(idNave);
        if (coordNave == null) return;
        Coordenada coordBala = new Coordenada(coordNave.getX(), coordNave.getY() - 1);
        if (esCoordenadaValida(coordBala.getX(), coordBala.getY())) {
            ListaBalas.getListaBalas().anadirBala(idNave, coordBala);
            matriz[coordBala.getX()][coordBala.getY()].cambiarObjeto("Bala");
        }
    }

}
