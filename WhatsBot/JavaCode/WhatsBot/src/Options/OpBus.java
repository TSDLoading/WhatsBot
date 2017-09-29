package Options;

import Exceptions.ExBus;



//Option Bus for dealing with all kind of Options, without having to give everything to every module
public class OpBus {
	
	static Init init;
	static ProfileList pl;
	
	public OpBus(ExBus exbus) {
		init = new Init(exbus);
	}
	
	public Init getInit() {
		return init;
	}
	
	public ProfileList getPL() {
		return pl;
	}
}
