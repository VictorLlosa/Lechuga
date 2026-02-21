package viewController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class PantallaJuego extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int hDim = 100 ;
	private static final int vDim = 60;
	private static PantallaJuego miPantallaJuego = null;

	// Matriz de etiquetas que representan las casillas del juego
	private static LabelCasilla[][] matrizLabels;

	private PantallaJuego() {

		setLayout(new GridLayout(vDim,hDim));
		matrizLabels = new LabelCasilla[hDim][vDim];

		// Crear y configurar todas las casillas
		// Se itera primero por j (filas del GridLayout) y luego por i (columnas del GridLayout)
		// para que la posición visual coincida con la matriz [i][j]
		for (int j = 0; j < vDim; j++) {
			for(int i = 0; i < hDim; i++) {
				LabelCasilla casillaLabel = new LabelCasilla();
				casillaLabel.setOpaque(true);

				casillaLabel.setBackground(Color.black);

				casillaLabel.setBorder(new LineBorder(Color.gray));
				matrizLabels[i][j] = casillaLabel;
				add(casillaLabel);
			}
		}

		asignarWASD();

	}

	/**
	 * Asigna los controles WASD para mover la nave.
	 */
	private void asignarWASD() {
		// Array de caracteres y sus correspondientes códigos de tecla
		String[] teclas = {"w", "a", "s", "d"};
		int[] keyCodes = {KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D};

		for (int i = 0; i < teclas.length; i++) {
			String comando = teclas[i];
			asignarTecla(keyCodes[i], comando);
		}
	}

	/**
	 * Asigna una tecla específica a una acción de movimiento.
	 */
	private void asignarTecla(int keyCode, String comando) {
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(keyCode, 0), comando);

		this.getActionMap().put(comando, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.getControlador().moverNave(comando);
			}
		});
	}


	/**
	 * Asigna los observers a todas las casillas del tablero.
	 */
	public static void asignarObservers() {
		for(int i = 0; i < hDim; i++) {
			for (int j = 0; j < vDim; j++) {

				Controlador.getControlador().asignarObserver(matrizLabels[i][j], i, j);
			}
		}
	}
	public static PantallaJuego getPantallaJuego() {
		if(miPantallaJuego == null) {
			miPantallaJuego = new PantallaJuego();
		}
		return miPantallaJuego;
	}
}


