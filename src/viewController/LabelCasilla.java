package viewController;

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
		// TODO Auto-generated method stub
		
	}
	

}
