package viewController;

import model.Tipos.TipoEntidad;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

@SuppressWarnings("deprecation")
/**
 * El label es observador de la casilla. Cuando la casilla cambia de color, notifica a su Label observador; y este,
 * en su update, cambia el volor de su background.
 * Tiene un atributo estatico que guarda de que color es
 */
public class LabelCasilla extends JLabel implements Observer{

	private static final long serialVersionUID = 1L;

	public LabelCasilla() {
		super();
	}

	/**
	 * A esta funcion se le llama cada vez que una casilla notifica de un cambio
	 * @param o     la instancia de la Casilla que ha producido la notificación
	 * @param arg   el argumento que le pasamos al mét0do {@code notifyObservers}
	 *
	 */
	@Override
	public void update(Observable o, Object arg) {
		TipoEntidad entidad = (TipoEntidad)arg;
		switch (entidad){
			case TipoEntidad.vacio:
				this.setBackground(Color.BLACK);
				break;
			case TipoEntidad.nave:
				this.setBackground(PantallaJuego.getPantallaJuego().getColorNave());
				break;
			case TipoEntidad.bala:
				this.setBackground(Color.WHITE);
				break;
			case TipoEntidad.enemigo:
				this.setBackground(Color.RED);
				break;

		}

	}
	

}
