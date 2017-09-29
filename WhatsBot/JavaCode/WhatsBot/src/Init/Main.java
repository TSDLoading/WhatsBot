package Init;

import Exceptions.ConfirmDialog;
import Exceptions.ExBus;
import Exceptions.Log;
import Frames.ProfileChooser;
import Options.OpBus;

public class Main {
	final static String Version = "1.0";
	final static boolean debug = true;
	
	static ExBus exbus;
	static OpBus opbus;
	
	public static void main(String[] args) {
		
		exbus = new ExBus(debug);
		opbus = new OpBus(exbus);

		
		if(!opbus.getInit().checkFile(Version)) {
			if(ConfirmDialog.YesNo("Would you like to try updating the existing init.config?")) {
				opbus.getInit().updateFile(Version);
			}else {
				exbus.getErrorHandler().newError(exbus.getErrorHandler().Fatal, "Aborted updating init.config.");
			}
		}
		
		opbus.getInit().loadFile();
		
		exbus.getLog().add(opbus.getInit().getProperty("ProfilesPath"));
		
		

		
	}
	
	public static void shutdown() {
		Log.save(exbus, opbus);
	}
	
	public static void kill() {
		System.exit(1);
	}
}
