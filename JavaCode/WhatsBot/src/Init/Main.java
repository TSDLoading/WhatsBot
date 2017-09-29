package Init;

import Exceptions.ConfirmDialog;
import Exceptions.ErrorHandler;
import Options.init;

public class Main {
	final static String Version = "1.0";
	
	static ErrorHandler errhandler;
	
	public static void main(String[] args) {
		
		errhandler = new ErrorHandler();
		
		init init = new init(errhandler);
		
		if(!init.checkFile(Version)) {
			if(ConfirmDialog.YesNo("Would you like to try updating the existing init.config?")) {
				init.updateFile(Version);
			}
		}
	
	}
}
