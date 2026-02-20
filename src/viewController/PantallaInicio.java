package viewController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;

import main.SpaceInvaders;

public class PantallaInicio extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private static final String rutaImagen = "/resources/imagenEspacio.jpg";
	private static PantallaInicio miPantallaInicio = new PantallaInicio(rutaImagen);
	
    private JLabel lblInstrucciones;
    private JLabel labelImagen;
    private JLabel lblPlay;
	
	private Image imagen;

	private PantallaInicio(String rutaImagen) {

		this.setLayout(new BorderLayout(10, 10));
		//this.setOpaque(false);
		
		imagen = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
		
		this.add(getLblInstrucciones(), BorderLayout.NORTH);
		this.add(getLabelImagen(), BorderLayout.CENTER);
		this.add(getLblPlay(), BorderLayout.SOUTH);
		
		
		
		
		
		
		//pulsar enter
		// Key Binding para ENTER
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
		    .put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "startGame");

		this.getActionMap().put("startGame", new AbstractAction() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        SpaceInvaders.getSpaceInvaders().cambioPantalla();;
		    }
		});

	}
	

    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        }
        
    }

	public static PantallaInicio getPantallaInicio() {
		return miPantallaInicio;
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
			labelImagen.setIcon(new ImageIcon(this.getClass().getResource("/resources/imagenLogo.png")));
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

