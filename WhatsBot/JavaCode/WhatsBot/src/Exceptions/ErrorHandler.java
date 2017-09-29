package Exceptions;

import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Init.Main;
import Options.OpBus;

public class ErrorHandler {
	public final String Warning = "Warning";
	public final String Error = "Error";
	public final String Fatal = "Fatal Error";
	
	ArrayList<String[]> Errors;
	ArrayList<String[]> Warnings;
	ArrayList<String[]> Fatals;
	
	ExBus exbus;
	
	public ErrorHandler(ExBus eb) {
		exbus = eb;
		
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
		exbus.getLog().add(msg,Warning);
		ThrowError(msg, Warning);
	}
	
	private void CreateError(String msg) {
		String[] tmp = {msg, Calendar.getInstance().toString()};
		Errors.add(tmp);
		exbus.getLog().add(msg, Error);
		ThrowError(msg, Error);
	}
	
	private void CreateFatal(String msg) {
		String[] tmp = {msg, Calendar.getInstance().toString()};
		Fatals.add(tmp);
		exbus.getLog().add(msg, Fatal);
		ThrowError(msg, Fatal);
	}
	
	private void ThrowError(String msg, String type) {
		JPanel panel = new JPanel();
		switch(type) {
		case Warning:
			JOptionPane.showMessageDialog(panel, msg, type, JOptionPane.WARNING_MESSAGE);
			break;
		case Error:
			JOptionPane.showMessageDialog(panel, msg, type, JOptionPane.ERROR_MESSAGE);
			exbus.getLog().add("CLOSING>>>>>>>");
			Main.shutdown();
			break;
		case Fatal:
			JOptionPane.showMessageDialog(panel, msg, type, JOptionPane.ERROR_MESSAGE);
			exbus.getLog().add("FORCE CLOSING>>>>>");
			Main.kill();
			break;
		default:
				
			break;
		}
	}
}
