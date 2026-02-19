package viewController;
import java.awt.*;
import javax.swing.*;

import main.Controlador;

public class PantallaInicio extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private static final String rutaImagen = "/resources/imagenEspacio.jpg";
	private static PantallaInicio miPantallaInicio = new PantallaInicio(rutaImagen);
	
    private JLabel lblInstrucciones;
    private JLabel labelImagen;
    private JLabel lblPlay;
	
	private Image imagen;

	private PantallaInicio(String rutaImagen) {

		this.setLayout(new BorderLayout(0, 0));
		//this.setOpaque(false);
		
		imagen = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
		
		this.add(getLblInstrucciones(), BorderLayout.NORTH);
		this.add(getLabelImagen(), BorderLayout.CENTER);
		this.add(getLblPlay(), BorderLayout.SOUTH);
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
	        lblInstrucciones = new JLabel("Press ENTER to Play", SwingConstants.CENTER);
	        lblInstrucciones.setForeground(Color.RED);
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
	        lblPlay = new JLabel("PLAY", SwingConstants.CENTER);
	        lblPlay.setForeground(Color.RED);
	        lblPlay.addKeyListener(Controlador.getControlador());
		}
		return lblPlay;
	}
	
}

