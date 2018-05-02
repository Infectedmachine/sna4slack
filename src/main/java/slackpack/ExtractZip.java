package slackpack;

import net.lingala.zip4j.exception.ZipException;

import net.lingala.zip4j.core.ZipFile;

import java.io.File;



public class ExtractZip {
	
	String dirsource;

	public ExtractZip(String source) {

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
			
			
		// 	StampaHelp.stampaHelp();
		} catch (ZipException e) {
			System.out.println("\nERROR: WRONG DIRECTORY");
		}
		
		this.dirsource=destination;
	}
}
