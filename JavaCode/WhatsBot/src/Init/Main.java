package Init;

import Exceptions.ConfirmDialog;
import Exceptions.ErrorHandler;
import Options.init;

public class Main {
	final static String Version = "1.0";
	
	static ErrorHandler errhandler;
	
	public static void main(String[] args) {
		String[] test = {"1","zwei","drai"};
		System.out.println(test[0]);
		System.out.println(test[1].toString());
		System.out.println((String) test[2]);
//		
//		errhandler = new ErrorHandler();
//		
//		init init = new init(errhandler);
//		
//		if(!init.checkFile(Version)) {
//			if(ConfirmDialog.YesNo("Would you like to try updating the existing init.config?")) {
//				if(!init.updateFile())errhandler.newError(errhandler.Fatal, "Could not Update init.config.");
//			}
//		}
	
	}
}
