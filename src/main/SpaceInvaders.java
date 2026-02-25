package main;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

import viewController.Controlador;
import viewController.PantallaFin;
import viewController.PantallaInicio;
import viewController.PantallaJuego;

public class SpaceInvaders extends JFrame implements Observer {

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

    /**
     * La ctr pimero crea los paneles y llama a las ctr de PantallaInicio,PantallaJuego
     * y PantallaFin (que son paneles)
     */
    private SpaceInvaders() {

    	getContentPane().setForeground(Color.WHITE);
    	getContentPane().setBackground(Color.WHITE);
    	setForeground(Color.WHITE);

        // Crear CardLayout y contenedor
        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);
        contenedor.setBackground(Color.WHITE);


        JPanel panelInicio = PantallaInicio.getPantallaInicio();
        JPanel panelJuego = PantallaJuego.getPantallaJuego();
        JPanel panelFin = PantallaFin.getPantallaFin();

        // Agregar paneles al contenedor
        contenedor.add(panelInicio, "Inicio");
        contenedor.add(panelJuego, "Juego");
        contenedor.add(panelFin, "Fin");

        // Establecer tamaño preferido del contenedor basándose en PantallaJuego
        Dimension tamano = panelJuego.getPreferredSize();
        contenedor.setPreferredSize(tamano);
        // Agregar contenedor a la ventana
        getContentPane().add(contenedor);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack(); // Ajustar tamaño automáticamente basándose en getPreferredSize()
        Controlador.getControlador().asignarObserverGestor(this);
        setVisible(true);

        Controlador.getControlador().setPantallaActual("Inicio");

        PantallaInicio.getPantallaInicio().setFocusable(true);
        PantallaInicio.getPantallaInicio().requestFocusInWindow();


    }

    @Override
    /**
     * El gestorPartida, dependiendo del string que metamos, cambiamos de pantalla. Si ponemos jugar, cambia a la patnalla de juego
     */
    public void update(Observable o, Object arg) {
        switch ((String) arg){
            case "repaint": //TODO quitar repaint
                PantallaJuego.getPantallaJuego().repaint();
                //Controlador.getControlador().procesarMovimiento();
                break;
            case "jugar":
                Controlador.getControlador().setPantallaActual("Juego");
                cardLayout.show(contenedor, "Juego");
                contenedor.revalidate();
                contenedor.repaint();

                PantallaJuego.getPantallaJuego().setFocusable(true);
                PantallaJuego.getPantallaJuego().requestFocusInWindow();
                break;

            case "perdido":
                PantallaFin.getPantallaFin().setPerdido();
                Controlador.getControlador().setPantallaActual("Fin");

                cardLayout.show(contenedor, "Fin");
                contenedor.revalidate();
                contenedor.repaint();

                PantallaFin.getPantallaFin().setFocusable(true);
                PantallaFin.getPantallaFin().requestFocusInWindow();
                break;
            case "ganado":
                PantallaFin.getPantallaFin().setGanado();
                Controlador.getControlador().setPantallaActual("Fin");

                cardLayout.show(contenedor, "Fin");
                contenedor.revalidate();
                contenedor.repaint();

                PantallaFin.getPantallaFin().setFocusable(true);
                PantallaFin.getPantallaFin().requestFocusInWindow();
                break;
            case "reiniciar":
                Controlador.getControlador().setPantallaActual("Inicio");

                cardLayout.show(contenedor,"Inicio");
                contenedor.revalidate();
                contenedor.repaint();

                PantallaInicio.getPantallaInicio().setFocusable(true);
                PantallaInicio.getPantallaInicio().requestFocusInWindow();
                break;
            default:

        }
    }



}
