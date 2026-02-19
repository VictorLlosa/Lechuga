package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controlador implements ActionListener, KeyListener{

	private static Controlador miControlador=new Controlador();
	
	private Controlador() {
		
	}
	
	public static Controlador getControlador() {
		return miControlador;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public void keyPressed(KeyEvent e) {
        // Detectar la tecla presionada
        int codigoTecla = e.getKeyCode();

        if (codigoTecla == KeyEvent.VK_ENTER) {
            SpaceInvaders.getSpaceInvaders().cambioPantalla();
        }
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
