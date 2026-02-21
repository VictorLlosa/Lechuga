package viewController;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Dimension;



import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaJuego extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int ancho = 60 ;
	private static final int alto = 100;
	private static PantallaJuego miPantallaJuego = null;

	// Necesitamos guardar las etiquetas
	private static LabelCasilla[][] matrizLabels;

	/**
	 * Create the panel.
	 */
	private PantallaJuego(int x, int y) {

		setLayout(new GridLayout(x,y));
		matrizLabels = new LabelCasilla[x][y]; //fila, columna
		
		for(int i=0; i < x ; i++) {
			for (int j=0; j < y ; j++) {
				LabelCasilla casillaLabel = new LabelCasilla();
				casillaLabel.setOpaque(true);
				casillaLabel.setBackground(Color.black);
				casillaLabel.setBorder(new LineBorder(Color.gray));
				matrizLabels[i][j] = casillaLabel;
				add(casillaLabel);
				
			}
		}
		
	}
	
	public static void asignarObservers() {
		for(int i=0; i < ancho; i++) {
			for (int j=0; j < alto ; j++) {
				Controlador.getControlador().asignarObserver(matrizLabels[i][j], i, j);
		
			}
		}
	}
	public static PantallaJuego getPantallaJuego() {
		if(miPantallaJuego == null){
			miPantallaJuego = new PantallaJuego(ancho, alto);
		}
		return miPantallaJuego;
	}
	
	
	
}
