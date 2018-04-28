package slackpack;

public class Commander {

	String command;

	public Commander() {
		command = "";
	}

	public Commander(String[] InputCommand) {

		switch (InputCommand[1]) {
		case "-m":
			String zipdir = (InputCommand[0]);
			ExtractZip Z = new ExtractZip(zipdir);
			String dir = Z.dirsource;
			dir=dir.concat("/users.json");
			ArrayMember Members = new ArrayMember(dir);
			Members.printArray();
			break;
		case "-c":
			zipdir = (InputCommand[0]);
			Z = new ExtractZip(zipdir);
			dir = Z.dirsource;
			dir=dir.concat("/channels.json");
			Members = new ArrayMember(dir);
			Members.printArray();
			break;
		}
	}
}
