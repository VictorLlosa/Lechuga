package viewController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controlador implements ActionListener{

	private static Controlador miControlador=new Controlador();
	
	private Controlador() {
		
	}
	
	public static Controlador getControlador() {
		return miControlador;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}


}
