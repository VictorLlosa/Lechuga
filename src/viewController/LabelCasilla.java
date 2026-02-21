package viewController;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

@SuppressWarnings("deprecation")
public class LabelCasilla extends JLabel implements Observer{

	private static final long serialVersionUID = 1L;
	private static final int ANCHO_CASILLA = 10;
	private static final int ALTO_CASILLA = 10;

	public LabelCasilla() {
		super();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(ANCHO_CASILLA, ALTO_CASILLA);
	}

	@Override
	public void update(Observable o, Object arg) {
		Object[] params = (Object[])arg;
		this.setBackground((Color) params[0]);
	}
	

}
