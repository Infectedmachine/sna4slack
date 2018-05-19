package slackpack;

import java.io.File;
import java.util.ArrayList;

public class JFileScanner {
	private ArrayList<File> jfiles;

	public JFileScanner() {
		this.setArray(new ArrayList<File>());
	}

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
			if (dir != null) {
				File[] files = dir.listFiles();
				for (File file : files) {
					if (file.isDirectory()) {
						scanDirectory(file);
					} else if (file.getName().equals("users.json") || file.getName().equals("channels.json")
							|| file.getName().equals("integration_logs.json")) {
						// DO NOTHING
					} else {
						jfiles.add(file);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("\nWRONG CHANNEL NAME");
		}
	}

	public final void setArray(ArrayList<File> jfiles) {
		this.jfiles = jfiles;
	}

	public ArrayList<File> getArray() {
		return this.jfiles;
	}
}
