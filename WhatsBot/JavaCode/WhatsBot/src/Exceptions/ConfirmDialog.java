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
	
	public static void Confirm(String Msg) {
		JPanel panel = new JPanel();
		JOptionPane.showMessageDialog(panel, Msg, "Info", JOptionPane.INFORMATION_MESSAGE);
	}

}
