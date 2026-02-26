package viewController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;

import main.SpaceInvaders;

public class PantallaInicio extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private static final String rutaImagenFondo = "/resources/imagenEspacio.jpg";
	private static final String rutaImagenLogo = "/resources/imagenLogo.png";
	private static PantallaInicio miPantallaInicio = null;
	
    private JLabel lblInstrucciones;
    private JLabel labelImagen;
    private JLabel lblPlay;
	
	private Image imagenFondo;

	/**
	 * Contiene los textos de como iniciar la partida y el logo.
	 */
	private PantallaInicio() {

		this.setLayout(new BorderLayout(10, 10));
		//this.setOpaque(false);
		this.addKeyListener(Controlador.getControlador());
		imagenFondo = new ImageIcon(getClass().getResource(rutaImagenFondo)).getImage();
		
		this.add(getLblInstrucciones(), BorderLayout.NORTH);
		this.add(getLabelImagen(), BorderLayout.CENTER);
		this.add(getLblPlay(), BorderLayout.SOUTH);

	}
	public static PantallaInicio getPantallaInicio() {
		if(miPantallaInicio == null) {
			miPantallaInicio = new PantallaInicio();
		}
		return miPantallaInicio;
	}



    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
        if (imagenFondo != null) {
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }
        
    }
	
	private JLabel getLblInstrucciones() {
		if (lblInstrucciones == null) {
	        lblInstrucciones = new JLabel("Press <A-W-S-D> to move and <SPACE> to fire", SwingConstants.CENTER);
	        lblInstrucciones.setFont(new Font("Bitstream Charter", Font.BOLD, 22));
	        lblInstrucciones.setForeground(Color.WHITE);
		}
		return lblInstrucciones;
	}
	private JLabel getLabelImagen() {
		if (labelImagen == null) {
			labelImagen = new JLabel("",SwingConstants.CENTER);
			labelImagen.setIcon(new ImageIcon(this.getClass().getResource(rutaImagenLogo)));
		}
		return labelImagen;
	}
	private JLabel getLblPlay() {
		if (lblPlay == null) {
	        lblPlay = new JLabel("Press <ENTER> to start playing", SwingConstants.CENTER);
	        lblPlay.setFont(new Font("Bitstream Charter", Font.BOLD, 22));
	        lblPlay.setForeground(Color.WHITE);
		}
		return lblPlay;
	}
	
}

