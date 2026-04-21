package viewController;

import model.GestorPartida;
import model.Tipos.TipoEventoJuego;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

public class SpaceInvaders extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
	private CardLayout cardLayout;
    private JPanel contenedor;
    private static SpaceInvaders miSpace=null;
    private EstadoPantalla pantallaActual;


    static void main(String[] args) {
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
        contenedor.add(panelInicio, EstadoPantalla.INICIO.getCardName());
        contenedor.add(panelJuego, EstadoPantalla.JUEGO.getCardName());
        contenedor.add(panelFin, EstadoPantalla.FIN.getCardName());

        setResizable(false);
        // Agregar contenedor a la ventana
        getContentPane().add(contenedor);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack(); // Ajustar tamaño automáticamente basándose en getPreferredSize()
        GestorPartida.getGestorPartida().asignarObserver(this);
        setVisible(true);

        pantallaActual = EstadoPantalla.INICIO;

        PantallaInicio.getPantallaInicio().setFocusable(true);
        PantallaInicio.getPantallaInicio().requestFocusInWindow();


    }
    /**
     * El gestorPartida, dependiendo del string que metamos, cambiamos de pantalla. Si ponemos jugar, cambia a la patnalla de juego
     */
    @Override
    public void update(Observable o, Object arg) {
        switch ((TipoEventoJuego) arg){
            case REPAINT:
                PantallaJuego.getPantallaJuego().repaint();
                break;
            case JUGAR:
                pantallaActual = EstadoPantalla.JUEGO;
                cardLayout.show(contenedor, pantallaActual.getCardName());
                contenedor.revalidate();
                contenedor.repaint();

                PantallaJuego.getPantallaJuego().setFocusable(true);
                PantallaJuego.getPantallaJuego().requestFocusInWindow();
                break;

            case PERDIDO:
                PantallaFin.getPantallaFin().setPerdido();
                pantallaActual = EstadoPantalla.FIN;

                cardLayout.show(contenedor, pantallaActual.getCardName());
                contenedor.revalidate();
                contenedor.repaint();

                PantallaFin.getPantallaFin().setFocusable(true);
                PantallaFin.getPantallaFin().requestFocusInWindow();
                break;
            case GANADO:
                PantallaFin.getPantallaFin().setGanado();
                pantallaActual = EstadoPantalla.FIN;

                cardLayout.show(contenedor, pantallaActual.getCardName());
                contenedor.revalidate();
                contenedor.repaint();

                PantallaFin.getPantallaFin().setFocusable(true);
                PantallaFin.getPantallaFin().requestFocusInWindow();
                break;
            case REINICIAR:
                pantallaActual = EstadoPantalla.INICIO;

                cardLayout.show(contenedor, pantallaActual.getCardName());
                contenedor.revalidate();
                contenedor.repaint();

                PantallaInicio.getPantallaInicio().setFocusable(true);
                PantallaInicio.getPantallaInicio().requestFocusInWindow();
                break;
            default:

        }
    }

    public EstadoPantalla getPantallaActual() {
        return pantallaActual;
    }
}
