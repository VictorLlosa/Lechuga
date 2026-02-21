package model;

import java.awt.Color;
import java.util.Observer;


public class Espacio {

	private static Espacio miEspacio;
	private final int hDim = 100 ;
	private final int vDim = 60;

	
	private Casilla[][] matriz;
	
	private Espacio() {
		
		matriz = new Casilla[hDim][vDim];
		for(int i = 0; i<hDim; i++) {
			for(int j = 0; j<vDim; j++) {
				matriz[i][j] = new Casilla();
			}
		}
	}
	
	public void anadirNave(Coordenada centro, Nave nave) {
		int cX = centro.getX();
		int cY = centro.getY();

		matriz[55][50].dibujarNave(nave.getColor());
		/* NAVE DE + DE 1 PIXEL
		for(int i = 55 - cX; i<= 55 + cX; i++) {
			for(int j = 50 - cY; j<= 50 + cY; j++) {
				matriz[i][j].cambiarColor(nave[55 - i][50 - j]);
			}
		}
		*/
	}

	@SuppressWarnings("deprecation")
	public void asignarObserver(Observer o, int pX, int pY) {
		matriz[pX][pY].asignarObserver(o);
	}
	
	public static Espacio getEspacio() {
		if(miEspacio == null) {
			miEspacio = new Espacio();
		}
		return miEspacio;
	}

	public boolean esCoordenadaValida(int x, int y){
        return x < this.hDim && y < this.vDim;
    }
	public Coordenada moverNave(Coordenada coordNave, Color colorNave, String tecla) {
		int cX = coordNave.getX();
		int cY = coordNave.getY();

		switch (tecla){
			case "w":
				if (esCoordenadaValida(cX,cY-1)){
					matriz[cX][cY].vaciar();
					matriz[cX][cY-1].dibujarNave(colorNave);
					return new Coordenada(cX,cY-1);
				}
				else{
					return null;
				}
            case "a":
				if (esCoordenadaValida(cX-1,cY)){
					matriz[cX][cY].vaciar();
					matriz[cX-1][cY].dibujarNave(colorNave);
					return new Coordenada(cX-1,cY);
				}
				else{
					return null;
				}
            case "s":
				if (esCoordenadaValida(cX,cY+1)){
					matriz[cX][cY].vaciar();
					matriz[cX][cY+1].dibujarNave(colorNave);
					return new Coordenada(cX,cY+1);
				}
				else{
					return null;
				}
            case "d":
				if (esCoordenadaValida(cX+1,cY)){
					matriz[cX][cY].vaciar();
					matriz[cX+1][cY].dibujarNave(colorNave);
					return new Coordenada(cX+1,cY);
				}
				else{
					return null;
				}
            default:
				return null;
        }

	}
}