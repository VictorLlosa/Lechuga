package viewController;
import model.Balas.ListaBalas;
import model.Enemigos.ListaEnemigos;
import model.GestorPartida;
import model.Naves.ListaNaves;
import model.Naves.NaveAbstracta;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

public class PantallaJuego extends JPanel {

	private static final long serialVersionUID = 1L;
	private final int hDim = 100 ;
	private final int vDim = 60;
	private final int ALTO_CASILLA = 25;
	private final int ANCHO_CASILLA = 25;
	private static PantallaJuego miPantallaJuego = null;

	private Color colorNave = Color.RED;

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
				casillaLabel.setSize(ANCHO_CASILLA, ALTO_CASILLA);
				matrizLabels[i][j] = casillaLabel;
				add(casillaLabel);
			}
		}
		setPreferredSize(new Dimension(  hDim * ALTO_CASILLA,  vDim * ANCHO_CASILLA));
		asignarObservers();
	}

	/**
	 * Asigna los observers a todas las casillas del tablero: la matriz de labels, ListaEnemigos, ListaNaves
	 */
	void asignarObservers() {
		for(int i = 0; i < hDim; i++) {
			for (int j = 0; j < vDim; j++) {
				GestorPartida.getGestorPartida().asignarObserverCasilla(matrizLabels[i][j], i, j); //matriz de labels
				GestorPartida.getGestorPartida().asignarObserverCasilla(ListaEnemigos.getListaEnemigos(),i,j); //listaEnemigos
				GestorPartida.getGestorPartida().asignarObserverCasilla(ListaNaves.getListaNaves(),i,j); //ListaNaves
				/* TODO falta asignar los observer a todas las listas de balas  ;
				    for (int i:ListaNaves.getListaNaves().getListaIds()){

				}
				*/

			}
		}
	}

	public static PantallaJuego getPantallaJuego() {
		if(miPantallaJuego == null) {
			miPantallaJuego = new PantallaJuego();
		}
		return miPantallaJuego;
	}

	public void cambiarColorNave(Color pColor) {
		this.colorNave = pColor;
	}

	public Color getColorNave() {
		return colorNave;
	}
}

