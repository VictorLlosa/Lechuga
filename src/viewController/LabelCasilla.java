package viewController;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

@SuppressWarnings("deprecation")
public class LabelCasilla extends JLabel implements Observer{

	private static final long serialVersionUID = 1L;
	
	public LabelCasilla() {
		super();
	}

	@Override
	public void update(Observable o, Object arg) {
		Object[] params = (Object[])arg;
		if(!(boolean)params[1]){//no ocupado -> se dibuja
			this.setBackground((Color) params[0]);
			this.setOpaque(true);
		}else{
			this.setOpaque(false);
		}

	}
	

}
