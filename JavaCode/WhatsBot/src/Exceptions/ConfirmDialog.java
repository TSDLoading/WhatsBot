package Exceptions;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ConfirmDialog {
	
	public static boolean YesNo(String Msg) {
		JPanel panel = new JPanel();
		int response = JOptionPane.showConfirmDialog(panel, Msg);
		if(response == JOptionPane.YES_OPTION) {
			return true;
		}else {
			return false;
		}
	}

}
