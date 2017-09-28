package Options;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import Exceptions.ErrorHandler;

public class init extends Properties{
	static File dir;
	static File f;
	
	static ErrorHandler errhandler;
	
	public init(ErrorHandler eh) {
		errhandler = eh;
		
		dir = new File(System.getProperty("user.home"));
		f = new File(dir + "/WhatsBot/init.config");
	}
	
	public void loadFile() {
		
	}
	
	public boolean checkFile(String version) {
		boolean ok = true;;
		if(f.exists()) {
			Properties tmpprops = new Properties();
			try {
				tmpprops.load(new BufferedReader(new FileReader(f)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				errhandler.newError(errhandler.Fatal, "Couldn't find init.config at AppData/Roaming/WhatsBot.");
			} catch (IOException e) {
				e.printStackTrace();
				errhandler.newError(errhandler.Fatal, "Couldn't find init.config at AppData/Roaming/WhatsBot.");
			}
			if(tmpprops.getProperty("Version")!= version) {
				errhandler.newError(errhandler.Error, "Version of init.config does not match");
				ok = false;
			}
		}else {
			ok = false;
		}
		return(ok);
	}
	
	public void updateFile(String version) {
		String[] required = {"Version", "ClientConfigPath", "RegistrationConfigPath"};
		String[] optional = {"Password"};
		
		Properties oldprops = new Properties();
		
		try {
			oldprops.load(new BufferedReader(new FileReader(f)));
		} catch (Exception e) {
			e.printStackTrace();
			errhandler.newError(errhandler.Fatal, "Unkown error at updateFile(): oldprops.load()");
		} 
		
		for(int x = 0; x < required.length; x++) {
			if(!oldprops.containsKey(required[x]))errhandler.newError(errhandler.Error, "Required Property not available. Updating failed. Please update manually.");
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
	}
	

}
