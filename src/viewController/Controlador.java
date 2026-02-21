package viewController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import model.Espacio;

public class Controlador implements ActionListener{

	private static Controlador miControlador=new Controlador();
	
	private Controlador() {
		
	}
	
	public static Controlador getControlador() {
		return miControlador;
	}
	
	public void asignarObserver(Observer o, int pX, int pY) {
		Espacio.getEspacio().asignarObserver(o,pX,pY);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	


}
