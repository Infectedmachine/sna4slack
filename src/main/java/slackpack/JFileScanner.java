package slackpack;

import java.util.ArrayList;
import java.io.File;

public class JFileScanner {

	private ArrayList<File> jfiles;

	public JFileScanner(String dir) {
		jfiles = new ArrayList<File>();
		try {
			File file = new File(dir);
			scanDirectory(file);
		} catch (Exception e) {
			System.out.println("\nWRONG WORKSPACE DIRECTORY");
		}
	}

	public void scanDirectory(File dir) {
		try {
			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					scanDirectory(file);
				} else if(file.getName().equals("users.json")  || file.getName().equals("channels.json") || file.getName().equals("integration_logs.json")){
					// DO NOTHING
				} else {
					jfiles.add(file);
				}
			}
		} catch (Exception e) {
			System.out.println("\nWRONG WORKSPACE DIRECTORY");
		}

	}
	
	public ArrayList<File> getArray(){
		
		return this.jfiles; 
	}
}
