package slackpack;

import java.io.File;
import java.util.ArrayList;

/**
 * Gets all of the JSON files paths extracted from the directory specified in
 * the constructor.
 *
 * @author aleningi
 *
 */
public class JFileScanner {

	/**
	 * Contains all of the JSON files paths extracted from the specidied directory.
	 */
	private ArrayList<File> jfiles;

	/**
	 * Allocates the space for the only attribute of the class, an array of files.
	 */
	public JFileScanner() {
		this.setArray(new ArrayList<File>());
	}

	/**
	 * Allocates the space for the only attribute of the class and initializes it.
	 *
	 * @param dir
	 *            path of directory.
	 */
	public JFileScanner(final String dir) {
		jfiles = new ArrayList<File>();
		try {
			File file = new File(dir);
			scanDirectory(file);
		} catch (Exception e) {
			System.out.println("\nWRONG WORKSPACE DIRECTORY");
		}
	}

	/**
	 * Scans the directory for JSON files and adds them to jfiles.
	 *
	 * @param dir
	 *            directory to work with.
	 */
	public void scanDirectory(final File dir) {
		try {
			if (dir != null) {
				File[] files = dir.listFiles();
				if (files != null) {
					for (File file : files) {
						if (file.isDirectory()) {
							scanDirectory(file);
						} else if (!file.getName().matches("users.json|integration_logs.json|channels.json")) {
							jfiles.add(file);
						}
					}
				} else {
					System.out.println("\nWRONG CHANNEL NAME");
				}
			}
		} catch (Exception e) {
			System.out.println("\nWRONG CHANNEL NAME");
		}
	}

	/**
	 * Sets the class attribute to the specified parameter.
	 *
	 * @param jsonfiles
	 *            array of files
	 */
	public final void setArray(final ArrayList<File> jsonfiles) {
		this.jfiles = jsonfiles;
	}

	/**
	 * Returns the class attribute.
	 *
	 * @return jfiles array of files.
	 */
	public ArrayList<File> getArray() {
		return this.jfiles;
	}
}

