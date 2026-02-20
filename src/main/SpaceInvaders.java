package main;

import java.awt.*;
import javax.swing.*;

import model.GestorPartida;
import viewController.PantallaInicio;
import viewController.PantallaJuego;

public class SpaceInvaders extends JFrame {

    private static final long serialVersionUID = 1L;
	private CardLayout cardLayout;
    private JPanel contenedor;
    private static SpaceInvaders miSpace=null;

    
    public static void main(String[] args) {
        SpaceInvaders.getSpaceInvaders();
    }
    
    public static SpaceInvaders getSpaceInvaders() {
    	if (miSpace==null) {
    		miSpace= new SpaceInvaders();
    	}
    	return miSpace;
    }
    
    private SpaceInvaders() {
    	
    	getContentPane().setForeground(Color.WHITE);
    	getContentPane().setBackground(Color.WHITE);
    	setForeground(Color.WHITE);
    	
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Crear CardLayout
        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);
        contenedor.setBackground(Color.WHITE);

        // Crear paneles
        JPanel panelInicio = PantallaInicio.getPantallaInicio();
        
        JPanel panelJuego = PantallaJuego.getPantallaJuego();

        // Agregar paneles al contenedor
        contenedor.add(panelInicio, "Inicio");
        contenedor.add(panelJuego, "Juego");
        //Para cambiar entre pantallas:
        
        //cardLayout.show(contenedor, "Juego");
       
        getContentPane().add(contenedor);
        setVisible(true);
    }
    
    public void cambioPantalla() {
    		
    		cardLayout.show(contenedor, "Juego");
    		//Modelo:
    		GestorPartida.getGestorPartida();
    	
    }
    
    
  



}
