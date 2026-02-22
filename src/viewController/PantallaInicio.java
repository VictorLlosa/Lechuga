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
	 * Contiene los textos de como iniciar la partida y como moverse, aparte del logo. con
	 * .getInputMap le asignamos una accion codificada como "startGame", a la que luego le asociamos
	 * una acción 
	 */
	private PantallaInicio() {

		this.setLayout(new BorderLayout(10, 10));
		//this.setOpaque(false);
		
		imagenFondo = new ImageIcon(getClass().getResource(rutaImagenFondo)).getImage();
		
		this.add(getLblInstrucciones(), BorderLayout.NORTH);
		this.add(getLabelImagen(), BorderLayout.CENTER);
		this.add(getLblPlay(), BorderLayout.SOUTH);

		//pulsar enter
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
		    .put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "startGame");

		this.getActionMap().put("startGame", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SpaceInvaders.getSpaceInvaders().cambioPantallaJuego();
			}
		});

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

