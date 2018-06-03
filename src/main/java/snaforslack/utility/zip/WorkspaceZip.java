package snaforslack.utility.zip;

import java.io.File;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import snaforslack.interfaces.zip.IntZip;

/**
 * Manages the zip file of the workspace.
 */
public class WorkspaceZip implements IntZip {
	/**
	 * path to zip file.
	 */
	private String source;

	/**
	 * initializes the class attribute with the path to the zip file.
	 * 
	 * @param dirpath
	 *            a String object.
	 */
	public WorkspaceZip(final String dirpath) {
		this.setDirPath(dirpath);
	}

	/**
	 * sets the path to the zip file.
	 * 
	 * @param path
	 *            a String object.
	 */
	private void setDirPath(final String path) {
		this.source = path;
	}

	@Override
	/**
	 * @return the class attribute source.
	 */
	public String getDirPath() {
		return this.source;
	}

	@Override
	/**
	 * extracts the workspace from the zip file.
	 * 
	 * @return an Int value. 1 no errore. -1 an error occured.
	 */
	public final int extractZip() {

		String destination = this.source;
		destination = this.refactorFileSeparator(destination);
		destination = this.removeZipExtentionFromPath(destination);
		final String password = "";
		try {
			final ZipFile zipFile = new ZipFile(this.getDirPath());
			if (zipFile.isEncrypted()) {
				zipFile.setPassword(password);
			}
			zipFile.extractAll(destination);
			System.out.println("WORKSPACE FILES EXTRACTED IN THE FOLLOWING DIRECTORY:  ");
			System.out.println(destination);
			System.out.println("\n");
		} catch (ZipException e) {
			System.out.println(e + "\nERROR: WRONG DIRECTORY");
			return -1;
		}
		this.setDirPath(destination);
		return 1;
	}

	/**
	 * Replace old file separators with standard file separator.
	 * 
	 * @param path
	 *            a String value of file/dir path.
	 * @return a String value.
	 */
	private String refactorFileSeparator(final String path) {
		return path.replace("\\", File.separator);
	}

	/**
	 * Replace ".zip" with empty space.
	 * 
	 * @param path
	 *            a String value of zipfile path.
	 * @return a String value.
	 */
	private String removeZipExtentionFromPath(final String path) {
		return path.replace(".zip", "");
	}
}
