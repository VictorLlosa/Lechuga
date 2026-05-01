package viewController;

import model.Tipos.TipoNave;

import java.awt.*;
import javax.swing.*;

public class PantallaInicio extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final String rutaImagenFondo          = "/resources/imagenEspacio.jpg";
	private static final String rutaImagenLogo           = "/resources/imagenLogo.png";
	private static final String rutaImagenBoton1Jugador  = "/resources/bot1.png";
	private static final String rutaImagenBoton2Jugador  = "/resources/bot2.png";

	private static PantallaInicio miPantallaInicio = null;

	private JLabel lblInstrucciones;
	private JLabel labelImagen;
	private JLabel lblPlay;
	private JLabel labelRojo;
	private JLabel labelVerde;
	private JLabel labelAzul;
	private JLabel botonJugadores;

	private Image imagenFondo;
	private JPanel panelColores;

	private boolean dosJugadores = false; // false = 1 jugador (estado inicial)

	private PantallaInicio() {
		this.setLayout(new BorderLayout(10, 10));
		this.addKeyListener(Controlador.getControlador());
		imagenFondo = new ImageIcon(getClass().getResource(rutaImagenFondo)).getImage();

		this.add(getLblInstrucciones(), BorderLayout.NORTH);
		this.add(getLabelImagen(), BorderLayout.CENTER);

		JPanel panelSur = new JPanel(new BorderLayout());
		panelSur.setOpaque(false);
		panelSur.add(getLblPlay(), BorderLayout.NORTH);
		panelSur.add(getPanelColores(), BorderLayout.SOUTH);
		panelSur.setPreferredSize(new Dimension(0, 200));
		this.add(panelSur, BorderLayout.SOUTH);
	}

	public static PantallaInicio getPantallaInicio() {
		if (miPantallaInicio == null) {
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
			lblInstrucciones = new JLabel(
					"P1: WASD + SPACE  |  P2: Flechas + ENTER  |  M: cambiar munición",
					SwingConstants.CENTER
			);
			lblInstrucciones.setFont(new Font("Bitstream Charter", Font.BOLD, 26));
			lblInstrucciones.setForeground(Color.WHITE);
			lblInstrucciones.setPreferredSize(new Dimension(0, 200));
		}
		return lblInstrucciones;
	}

	private JLabel getLabelImagen() {
		if (labelImagen == null) {
			labelImagen = new JLabel("", SwingConstants.CENTER);
			labelImagen.setIcon(new ImageIcon(this.getClass().getResource(rutaImagenLogo)));
		}
		return labelImagen;
	}

	private JLabel getLblPlay() {
		if (lblPlay == null) {
			lblPlay = new JLabel("SELECT A COLOR and Press <ENTER> to start playing", SwingConstants.CENTER);
			lblPlay.setFont(new Font("Bitstream Charter", Font.BOLD, 30));
			lblPlay.setForeground(Color.WHITE);
		}
		return lblPlay;
	}

	private JPanel getPanelColores() {
		if (panelColores == null) {
			panelColores = new JPanel();
			panelColores.setOpaque(false);
			panelColores.setLayout(new FlowLayout(FlowLayout.CENTER, 80, 50));
			panelColores.add(getLabelRojo());
			panelColores.add(getLabelVerde());
			panelColores.add(getLabelAzul());
			panelColores.add(getBotonJugadores());
		}
		return panelColores;
	}

	private JLabel crearLabelColor(TipoNave tipo, String textoMunicion) {
		JLabel label = new JLabel();
		label.setOpaque(true);
		label.setBackground(tipo.getColor());
		label.setPreferredSize(new Dimension(50, 50));

		JPopupMenu popup = new JPopupMenu();
		JLabel texto = new JLabel(textoMunicion);
		texto.setFont(new Font("Bitstream Charter", Font.BOLD, 22));
		popup.add(texto);

		label.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				int x = label.getWidth() / 2 - popup.getPreferredSize().width / 2;
				int y = label.getHeight();
				popup.show(label, x, y);
				popup.setBorder(BorderFactory.createLineBorder(tipo.getColor()));
				popup.setBackground(Color.WHITE);
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				popup.setVisible(false);
			}

			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				resetBordes();
				label.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
				Controlador.getControlador().seleccionarNave(tipo);
			}
		});

		return label;
	}

	private JLabel getLabelRojo() {
		if (labelRojo == null) {
			labelRojo = crearLabelColor(TipoNave.red, "MUNICIÓN: PIXEL, FLECHA, ROMBO");
		}
		return labelRojo;
	}

	private JLabel getLabelVerde() {
		if (labelVerde == null) {
			labelVerde = crearLabelColor(TipoNave.green, "MUNICIÓN: PIXEL, ROMBO");
		}
		return labelVerde;
	}

	private JLabel getLabelAzul() {
		if (labelAzul == null) {
			labelAzul = crearLabelColor(TipoNave.blue, "MUNICIÓN: PIXEL, FLECHA");
		}
		return labelAzul;
	}

	private void resetBordes() {
		if (labelRojo != null)  labelRojo.setBorder(null);
		if (labelVerde != null) labelVerde.setBorder(null);
		if (labelAzul != null)  labelAzul.setBorder(null);
	}

	private JLabel getBotonJugadores() {
		if (botonJugadores == null) {
			botonJugadores = new JLabel("", SwingConstants.CENTER);
			botonJugadores.setOpaque(false);
			botonJugadores.setBorder(null);
			botonJugadores.setPreferredSize(new Dimension(100, 100));
			botonJugadores.setIcon(new ImageIcon(getClass().getResource(rutaImagenBoton1Jugador)));

			botonJugadores.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mousePressed(java.awt.event.MouseEvent e) {
					dosJugadores = !dosJugadores;
					botonJugadores.setIcon(new ImageIcon(getClass().getResource(
							dosJugadores ? rutaImagenBoton2Jugador : rutaImagenBoton1Jugador
					)));
					Controlador.getControlador().seleccionarModoJugadores(dosJugadores);
				}
			});
		}
		return botonJugadores;
	}
}