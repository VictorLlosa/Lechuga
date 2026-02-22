package viewController;

import javax.swing.*;
import java.awt.*;

public class PantallaFin extends JPanel{
    private static final long serialVersionUID = 1L;

    private static final String rutaImagenFondo = "/resources/imagenEspacio.jpg";
    private static final String rutaImagenGameOver = "/resources/imagenGameOver.png";
    private static final String rutaImagenWinner = "/resources/imagenWinner.png";
    private static PantallaFin miPantallaFin = null;

    private JLabel labelCentral;
    private Image imagenFondo;

    private PantallaFin() {


        this.setLayout(new BorderLayout());

        labelCentral = new JLabel("", SwingConstants.CENTER);
        this.add(labelCentral, BorderLayout.CENTER);

        imagenFondo = new ImageIcon(getClass().getResource(rutaImagenFondo)).getImage();

    }
    public static PantallaFin getPantallaFin() {
        if(miPantallaFin== null) {
            miPantallaFin = new PantallaFin();
        }
        return miPantallaFin;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenFondo != null) {
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }


    }

    public void setPerdido() {
        labelCentral.setIcon(
                new ImageIcon(getClass().getResource(rutaImagenGameOver))
        );
    }
    public void setGanado() {
        labelCentral.setIcon(
                new ImageIcon(getClass().getResource(rutaImagenWinner))
        );
    }
}
