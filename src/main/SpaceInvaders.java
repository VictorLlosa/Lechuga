package main;


import javax.swing.JFrame;

import model.Casilla;
import viewController.Pantalla;

public class SpaceInvaders {

	public static void main(String[] args) {
		//Interfaz	
		JFrame frame = new JFrame("Space Invaders");
		Pantalla vistaPantalla = new Pantalla();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.add(vistaPantalla);
		frame.pack();
		frame.setVisible(true);
		
	}

}
