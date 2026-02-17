package viewController;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Dimension;



import java.awt.Color;
import java.awt.GridLayout;

public class Pantalla extends JPanel {

	private static final long serialVersionUID = 1L;

	// Necesitamos guardar las etiquetas
	private JLabel[][] matrizLabels =new JLabel[10][6];
	
	/**
	 * Create the panel.
	 */
	public Pantalla() {
		setLayout(new GridLayout(10,6));
		//Está más grande(x10), cambiar a (100,60)
		setPreferredSize(new Dimension(1000, 600));
		for(int i=0; i < 10 ; i++) {
			for (int j=0; j < 6 ; j++) {
				JLabel casillaLabel = new JLabel();
				casillaLabel.setOpaque(true);
				casillaLabel.setBackground(Color.black);
				casillaLabel.setBorder(new LineBorder(Color.gray));
				matrizLabels[i][j] = casillaLabel;
				add(casillaLabel);	
			}
		}
		

	}
}
