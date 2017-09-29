package Options;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import Exceptions.ConfirmDialog;
import Exceptions.ErrorHandler;
import Exceptions.ExBus;
import Exceptions.Log;

public class Init extends Properties{
	public final String LogPath = "LogPath";
	public final String ProfilesPath = "ProfilesPath";
	public final String Version = "Version";
	
	static File dir;
	static File f;
	
	static ExBus exbus;
	
	public Init(ExBus eb) {
		exbus = eb;
		
		dir = new File(System.getenv("APPDATA"));
		exbus.getLog().add(dir.toString());
		f = new File(dir + "/WhatsBot/init.config");
	}
	
	public void loadFile() {
		try {
			this.load(new BufferedReader(new FileReader(f)));
		} catch (Exception e) {
			e.printStackTrace();
			exbus.getErrorHandler().newError(exbus.getErrorHandler().Fatal, "Couldn't load init.config at init.loadFile()");
		}
	}
	
	public boolean checkFile(String version) {
		boolean ok = true;
		if(f.exists()) {
			Properties tmpprops = new Properties();
			try {
				tmpprops.load(new BufferedReader(new FileReader(f)));
			} catch (Exception e) {
				e.printStackTrace();
				exbus.getErrorHandler().newError(exbus.getErrorHandler().Fatal, "Couldn't find init.config at AppData/Roaming/WhatsBot.");
			}
			
			if(!tmpprops.getProperty("Version").equals(version)) {
				exbus.getLog().add("read props " + tmpprops.getProperty("Version") + " version " + version);
				exbus.getErrorHandler().newError(exbus.getErrorHandler().Warning, "Version of init.config does not match. \nConfig version: " + tmpprops.getProperty("Version") + " \nThis version " + version);
				ok = false;
			}
		}else {
			exbus.getErrorHandler().newError(exbus.getErrorHandler().Fatal, "Couldn't find init.config at AppData/Roaming/WhatsBot.");
		}
		return(ok);
	}
	
	public void updateFile(String version) {
		String[] required = {Version, ProfilesPath, LogPath};
		String[] optional = {};
		
		Properties oldprops = new Properties();
		
		try {
			oldprops.load(new BufferedReader(new FileReader(f)));
		} catch (Exception e) {
			e.printStackTrace();
			exbus.getErrorHandler().newError(exbus.getErrorHandler().Fatal, "Unkown error at updateFile(): oldprops.load()");
		} 
		
		for(int x = 0; x < required.length; x++) {
			if(!oldprops.containsKey(required[x]))exbus.getErrorHandler().newError(exbus.getErrorHandler().Fatal, "Required Property not available. Updating failed. Please update manually.");
		}
		
		Properties newprops = new Properties();
		
		for(int x = 0; x < required.length; x++) {
			if(required[x] == "Version") {
				newprops.setProperty(required[x], version);
			}else {
				newprops.setProperty(required[x], oldprops.getProperty(required[x]));
			}
		}
		for(int x = 0; x < optional.length; x++) {
			if(oldprops.containsKey(optional[x])) {
				newprops.setProperty(optional[x], oldprops.getProperty(optional[x]));
			}else {
				newprops.setProperty(optional[x], "");
			}
		}
		
		try {
			newprops.store(new BufferedWriter(new FileWriter(f)),"");
		} catch (IOException e) {
			e.printStackTrace();
			exbus.getErrorHandler().newError(exbus.getErrorHandler().Fatal, "An error occured while storing the config.");
		}
		ConfirmDialog.Confirm("Updating complete.");
	}
	
	public String get(String Key) {
		return this.getProperty(Key);
	}
	public void set(String Key, String value) {
		this.setProperty(Key, value);
	}

}
