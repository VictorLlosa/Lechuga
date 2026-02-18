package main;

import java.awt.*;

import javax.swing.*;
import viewController.PantallaJuego;

public class SpaceInvaders extends JFrame {

    private static final long serialVersionUID = 1L;
	private CardLayout cardLayout;
    private JPanel contenedor;
    private JLabel lblInstrucciones;
    private JLabel labelImagen;
    private JLabel lblPlay;
    
    public static void main(String[] args) {
        new SpaceInvaders();
    }
    
    public SpaceInvaders() {
    	
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Crear CardLayout
        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);

        // Crear paneles
        JPanel panelInicio = crearPanelInicio();
        
        JPanel panelJuego = PantallaJuego.getPantallaJuego();

        // Agregar paneles al contenedor
        contenedor.add(panelInicio, "Inicio");
        contenedor.add(panelJuego, "Juego");
        //Para cambiar entre pantallas:
        //cardLayout.show(contenedor, "Juego");

        getContentPane().add(contenedor);
        setVisible(true);
    }

    private JPanel crearPanelInicio() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 0));
        panel.add(getLblInstrucciones(), BorderLayout.NORTH);
        panel.add(getLabelImagen(), BorderLayout.CENTER);
        panel.add(getLblPlay(), BorderLayout.SOUTH);
       
        

        return panel;
    }


	private JLabel getLblInstrucciones() {
		if (lblInstrucciones == null) {
			lblInstrucciones = new JLabel("Press mdmmdmd");
		}
		return lblInstrucciones;
	}
	private JLabel getLabelImagen() {
		if (labelImagen == null) {
			labelImagen = new JLabel("");
		}
		return labelImagen;
	}
	private JLabel getLblPlay() {
		if (lblPlay == null) {
			lblPlay = new JLabel("play");
		}
		return lblPlay;
	}
}
