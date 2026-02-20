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
	private static final int ancho = 100;
	private static final int alto = 60;
	private static PantallaJuego miPantallaJuego = new PantallaJuego(ancho, alto);

	// Necesitamos guardar las etiquetas
	private JLabel[][] matrizLabels = null;
	
	/**
	 * Create the panel.
	 */
	private PantallaJuego(int x, int y) {
		
		setLayout(new GridLayout(x,y));
		matrizLabels = new JLabel[x][y];
		
		//Está más grande(x10), cambiar a (100,60)
		setPreferredSize(new Dimension(x, y));
		for(int i=0; i < x ; i++) {
			for (int j=0; j < y ; j++) {
				JLabel casillaLabel = new JLabel();
				casillaLabel.setOpaque(true);
				casillaLabel.setBackground(Color.black);
				casillaLabel.setBorder(new LineBorder(Color.gray));
				matrizLabels[i][j] = casillaLabel;
				add(casillaLabel);
				
			}
		}
		
	}

	public static PantallaJuego getPantallaJuego() {
		return miPantallaJuego;
	}
	
	
	
}
