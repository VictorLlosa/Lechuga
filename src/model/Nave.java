package model;

import java.awt.Color;

public class Nave {

	private Color[][] nave;
	private Coordenada centro;
	private Color color;
	private int dimX, dimY;
	
	public Nave(Color pColor) {
		dimX = 3;
		dimY = 2;
		color = pColor;
		
		nave = new Color[dimX][dimY];
		//empieza todo en negro
		for(int i = 0; i<dimX; i++) {
			for(int j = 0; j<dimX; j++) {
					nave[i][0] = Color.BLACK;
				}
		}
		
		for(int i = 0; i<dimX; i++) {
			if(i==1) {
				nave[i][0] = pColor;
				nave[i][1] = pColor;
			}else {
				nave[i][0] = pColor;
			}
		}
		
		centro = new Coordenada(1,2);
		/*Nave:
		 *          O
		 *        O O O
		 *          ^
		 *          |
		 *Centro: (1,2)
		 */
		
	}
	
	public int getDimX() {
		return dimX;
	}
	public int getDimY() {
		return dimY;
	}
	
	public Color[][] getMatriz(){
		return nave;
	}
	public Coordenada getCentro() {
		return centro;
	}

	public Color getColor() {
		return color;
	}
}
