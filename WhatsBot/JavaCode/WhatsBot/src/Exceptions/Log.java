package Exceptions;

import java.io.File;
import java.util.ArrayList;

import Options.OpBus;

public class Log extends ArrayList<String[]>{
	static ErrorHandler errhandler;
	
	static boolean debug;
	
	public Log(boolean deb) {
		debug = deb;
	}
	
	public void add(String str) {
		this.add(new String[] {str,"normal"});
		if(debug)System.out.println(str);
	}
	
	public void add(String str, String type) {
		this.add(new String[] {str, type});
		if(debug)System.out.println(str + " " + type);
	}
	
	public static void save(ExBus exbus, OpBus opbus) {
		if(!opbus.getInit().containsKey("LogPath")) {
			exbus.getErrorHandler().newError(errhandler.Fatal, "Couldn't save Log, no LogPath in init.config found.");
		}else {
			File f = new File(opbus.getInit().get(opbus.getInit().LogPath));
			
		}
	}

}
