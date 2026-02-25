package viewController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class PantallaJuego extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int hDim = 100 ;
	private static final int vDim = 60;
	private static final int TAMANO_CASILLA = 10;
	private static PantallaJuego miPantallaJuego = null;

	// Matriz de etiquetas que representan las casillas del juego
	private static LabelCasilla[][] matrizLabels;

	private PantallaJuego() {
		setLayout(new GridLayout(vDim,hDim));
		matrizLabels = new LabelCasilla[hDim][vDim];
		this.addKeyListener(Controlador.getControlador());
		// Crear y configurar todas las casillas
		// Se itera primero por j (filas del GridLayout) y luego por i (columnas del GridLayout)
		// para que la posición visual coincida con la matriz [i][j]
		for (int j = 0; j < vDim; j++) {
			for(int i = 0; i < hDim; i++) {
				LabelCasilla casillaLabel = new LabelCasilla();
				casillaLabel.setOpaque(true);

				casillaLabel.setBackground(Color.black);

				casillaLabel.setBorder(new LineBorder(Color.BLACK));
				matrizLabels[i][j] = casillaLabel;
				add(casillaLabel);
			}
		}
	}

	/**
	 * Asigna los observers a todas las casillas del tablero.
	 */
	public void asignarObservers() {
		for(int i = 0; i < hDim; i++) {
			for (int j = 0; j < vDim; j++) {
				Controlador.getControlador().asignarObserverCasilla(matrizLabels[i][j], i, j);
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(hDim * TAMANO_CASILLA, vDim * TAMANO_CASILLA);
	}

	public static PantallaJuego getPantallaJuego() {
		if(miPantallaJuego == null) {
			miPantallaJuego = new PantallaJuego();
		}
		return miPantallaJuego;
	}
}

