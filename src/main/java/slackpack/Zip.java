package slackpack;

import java.io.File;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
/**
 * This class consists of one String attribute end three methods used to uzip a file given its path.
 **/
public class Zip {
	/**
	 * Private attribute string, the path.
	 **/
	private String dirpath;

	/**
	 * Constructor.
	 **/
	public Zip() {
		this.setDirPath("");
	}

	/**
	 * It sets the given string to dirpath.
	 * @param path the String to be set
	 **/
	public final void setDirPath(final String path) {
		this.dirpath = path;
	}

	/**
	 * It gets the path.
	 * @return path string
	 **/
	public String getDirPath() {
		return this.dirpath;
	}

	/**
	 * Final method to extract a file given its path.
	 * @param source the path string
	 **/
	public final void extractZip(final String source) {

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

