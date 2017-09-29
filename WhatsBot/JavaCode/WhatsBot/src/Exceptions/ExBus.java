package Exceptions;

import Options.OpBus;

//Exception Bus for dealing with all kind of Exception, without having to give everything to every module
public class ExBus {
	static boolean debug;
	
	static ErrorHandler errhandler;
	static ConfirmDialog confirmdialog;
	static Log log;
	
	public ExBus(boolean deb) {
		debug = deb;
		
		errhandler = new ErrorHandler(this);
		confirmdialog = new ConfirmDialog();
		log = new Log(debug);
	}
	
	public boolean getDebug() {
		return debug;
	}
	
	public ErrorHandler getErrorHandler() {
		return errhandler;
	}
	
	public ConfirmDialog getConfirmDialog() {
		return confirmdialog;
	}
	
	public Log getLog() {
		return log;
	}
}
