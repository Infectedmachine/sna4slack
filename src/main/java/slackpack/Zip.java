package slackpack;

import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.core.ZipFile;
import java.io.File;

public class Zip {	
	private String dirpath;
	
	public Zip() {
		this.setDirPath(""); 
	}
	
	public final void setDirPath(String path) {
		this.dirpath = path; 
	}
	
	public String getDirPath() {
		return this.dirpath; 
	}
	
	public final void extractZip(String source) {

		String destination = source.replace(".zip", "");
		destination = destination.replace("\\", File.separator);
		String password = "";
		try {
			ZipFile zipFile = new ZipFile(source);
			if (zipFile.isEncrypted()) {
				zipFile.setPassword(password);
			}
			zipFile.extractAll(destination);
			System.out.println("WORKSPACE FILES EXTRACTED IN THE FOLLOWING DIRECTORY:  ");
			System.out.println(destination);
			System.out.println("\n");
		} catch (ZipException e) {
			System.out.println(e + "\nERROR: WRONG DIRECTORY");
		}
		this.setDirPath(destination);
	}
}
