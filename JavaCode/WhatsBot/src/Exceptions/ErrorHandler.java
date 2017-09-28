package Exceptions;

import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ErrorHandler {
	public final String Warning = "Warning";
	public final String Error = "Error";
	public final String Fatal = "Fatal Error";
	ArrayList<String[]> Errors;
	ArrayList<String[]> Warnings;
	ArrayList<String[]> Fatals;
	
//	Calendar cal;
	
	public ErrorHandler() {
		Errors = new ArrayList<String[]>();
		Warnings = new ArrayList<String[]>();
		Fatals = new ArrayList<String[]>();
	}
	
	public void newError(String Errtype, String Message) {
		System.out.println(Errtype + " " + Message);
		switch(Errtype) {
		
		case Warning:
			CreateWarning(Message);
			break;
		case Error:
			CreateError(Message);
			break;
		case Fatal:
			CreateFatal(Message);
			break;
		default:
			break;
		}		
	}
	
	private void CreateWarning(String msg) {
		String[] tmp = {msg, Calendar.getInstance().toString()};
		Warnings.add(tmp);
	}
	
	private void CreateError(String msg) {
		String[] tmp = {msg, Calendar.getInstance().toString()};
		Errors.add(tmp);
		ThrowError(msg, Error);
	}
	
	private void CreateFatal(String msg) {
		String[] tmp = {msg, Calendar.getInstance().toString()};
		Fatals.add(tmp);
		ThrowError(msg, Fatal);
	}
	
	private void ThrowError(String msg, String type) {
		JPanel panel = new JPanel();
		int response = JOptionPane.showConfirmDialog(panel,  msg, type, JOptionPane.ERROR_MESSAGE);
		if(type == Fatal && (response == JOptionPane.CLOSED_OPTION || response == JOptionPane.OK_OPTION)) {
			System.out.println("CLOSING>>>>>");
			System.exit(1);
		}
	}
}
