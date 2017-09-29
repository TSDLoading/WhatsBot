package Frames;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Exceptions.ExBus;
import Options.OpBus;
import Options.profile;


public class ProfileChooser extends JFrame{
	ExBus exbus;
	OpBus opbus;
	
	File f;
	File pdir;
	
	static File[] directories;
	
	public ProfileChooser(ExBus eb, OpBus ob) {
		exbus = eb;
		opbus = ob;
		
		exbus.getLog().add(opbus.getInit().getProperty(opbus.getInit().ProfilesPath));
		pdir = new File(opbus.getInit().getProperty(opbus.getInit().ProfilesPath));
		
		directories = pdir.listFiles(File::isDirectory);
		if(directories.length < 1){
			
		}
		for(int x = 0; x < directories.length; x++) {
			System.out.println(directories[x]);
		}
		
	}
	
	public Profile getProfile() {
		
	    String input = (String) JOptionPane.showInputDialog(null, "Choose your profile", "Choose a profile", JOptionPane.QUESTION_MESSAGE, null, directories, directories[1]);
		
		profile p = new profile(init);
		
		
		return p;
	}
}
