package viewController;

import main.SpaceInvaders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class PantallaFin extends JPanel{
    private static final long serialVersionUID = 1L;

    private static final String rutaImagenFondo = "/resources/imagenEspacio.jpg";
    private static final String rutaImagenGameOver = "/resources/imagenGameOver.png";
    private static final String rutaImagenWinner = "/resources/imagenWinner.png";
    private static PantallaFin miPantallaFin = null;

    private JLabel labelCentral;
    private Image imagenFondo;
    private JLabel lblInstruccionesF;

    private PantallaFin() {


        this.setLayout(new BorderLayout());

        labelCentral = new JLabel("", SwingConstants.CENTER);
        this.add(labelCentral, BorderLayout.CENTER);
        this.add(getLblInstruccionesF(),BorderLayout.SOUTH);

        imagenFondo = new ImageIcon(getClass().getResource(rutaImagenFondo)).getImage();

       asignarRReinicio();
    }
    public static PantallaFin getPantallaFin() {
        if(miPantallaFin== null) {
            miPantallaFin = new PantallaFin();
        }
        return miPantallaFin;
    }

    private void asignarRReinicio(){
        //si pulsamos la R, reiniciamos la partida
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), "startGame");

        this.getActionMap().put("startGame", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SpaceInvaders.getSpaceInvaders().cambioPantallaInicio();
            }
        });
    }

    private JLabel getLblInstruccionesF() {
        if (lblInstruccionesF == null) {
            lblInstruccionesF = new JLabel("Press <R> to RESTART your game", SwingConstants.CENTER);
            lblInstruccionesF.setFont(new Font("Bitstream Charter", Font.BOLD, 22));
            lblInstruccionesF.setForeground(Color.WHITE);
        }
        return lblInstruccionesF;
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
