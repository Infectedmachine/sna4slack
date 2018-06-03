package snaforslack.utility.json;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import snaforslack.interfaces.json.IntJFileScanner;

/**
 * This class consists of the attributes and methods to Scan a JSON. Using
 * org.json.simple API.
 **/
public class JFileScanner implements IntJFileScanner {
	/**
	 * Contains all of the JSON files paths extracted from the specidied directory.
	 */
	private List<File> jfiles;

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
		if (dir != null) {
			final File file = new File(dir);
			if (file.exists()) {
				scanDirectory(file);
			}
		}
	}

	/**
	 * Scans the directory for JSON files and adds them to jfiles.
	 *
	 * @param dir
	 *            directory to work with.
	 */
	private void scanDirectory(final File dir) {
		final File[] files = dir.listFiles();
		if (files != null) {
			for (final File file : files) {
				if (file.isDirectory()) {
					scanDirectory(file);
				} else if (!this.checkMatch(file.getName())) {
					jfiles.add(file);
				}
			}
		}
	}

	/**
	 * Returns a boolean value if the check matches base json files (Base json files
	 * are: users, channels and integration).
	 * 
	 * @param name
	 *            a String type.
	 * @return a boolean type.
	 */
	private boolean checkMatch(final String name) {
		return name.matches("users.json|integration_logs.json|channels.json");
	}

	/**
	 * Sets the class attribute to the specified parameter.
	 *
	 * @param jsonfiles
	 *            list of files
	 */
	private void setArray(final List<File> jsonfiles) {
		this.jfiles = jsonfiles;
	}

	/**
	 * Returns the class attribute.
	 *
	 * @return List<File>
	 */
	@Override
	public List<File> getList() {
		return this.jfiles;
	}
}
