package slackpack;

import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;

public class Commander {

	String workspacedir;

	public Commander(String[] InputCommand) {

		try {
			String zipdir = (InputCommand[0]); // THIS BLOCK OF INSTRUTIONS TAKES FILEDIRECTORY NAME FROM THE COMMAND
			ExtractZip Z = new ExtractZip(zipdir); // AND EXTRACT IT IN THE SAME DIRECTORY IN A FOLDER WITH THE SAME
													// NAME
			String dir = Z.dirsource;// AT THE END IT SAVES THE FOLDERNAME WHERE EXTRACTED FOLDER IS
			this.workspacedir = dir; // SAVE THE WORKSPACE DIRECTORY FOR ANY OTHER USE

			switch (InputCommand[1]) {
			case "-m": // PRINT ALL MEMBERS NAME OF A WORKSPACE
				dir = dir.concat("/users.json");// ADDS TO THE FOLDERNAME JSON'S FILE NAME
				ArrayMember members = new ArrayMember(dir);// PROCIDE TO CREATE AN ARRAY WITH ALL MEMBERS OF A WORKSPACE
				members.printArray();
				break;

			case "-c": // PRINT ALL CHANNELS NAMES OF A WORKSPACE
				dir = dir.concat("/users.json");
				members = new ArrayMember(dir);
				dir = dir.replace("/users.json", "/channels.json");
				ArrayChannel channels = new ArrayChannel(dir);
				channels.printChannels();
				break;

			case "-cm": // PRINT MEMBERS OF A SINGLE CHANNEL
				if (InputCommand.length == 3) {
					dir = dir.concat("/users.json");
					members = new ArrayMember(dir);
					dir = dir.replace("/users.json", "/channels.json");
					channels = new ArrayChannel(dir, members);
					int counter = 0; // SET A COUNTER TO CHECK IF THERE IS ANY CHANNEL BY THIS NAME
					for (Object obj : channels.channels) {
						Channel cobj = (Channel) obj;
						if (cobj.getName().equals(InputCommand[2])) { // CHECK FOR CHANNELS WITH THE SAME NAME
							System.out.println(cobj.getName() + ":\n");
							cobj.printMembersList();
							counter = 1; // A CHANNEL WAS FOUND
						}
					}
					if (counter == 0) { // IF NONE CHANNEL WAS FOUND MEANS THERE IS NO SUCH ONE OR INVALID NAME
						System.out.println(
								"\n NONE CHANNEL BY THIS NAME. Please type a valid Channel's Name after command");
						Helper.stampaLogo();
						Helper.stampaHelp();
					}
				} else {
					System.out.println("\n Please type a valid command"); // LESS OR MORE ARGUMENTS IN THE COMMAND
					Helper.stampaLogo();
					Helper.stampaHelp();
				}

				break;

			case "-mc": // PRINT MEMBERS LIST PER CHANNEL
				dir = dir.concat("/users.json");
				members = new ArrayMember(dir);
				dir = dir.replace("/users.json", "/channels.json");
				channels = new ArrayChannel(dir, members);
				channels.printArray();
				break;

			case "@m":
				// PRINT @MENTIONS LIST - ALL/CHANNEL
				// CHECK IF THERE IS A CHANNEL NAME RIGHT AFTER THE COMMAND TO RESTRICT MENTIONS
				// TO ONLY SPECIFIED CHANNEL
				// IF NOT LIST THEM ALL
				if (InputCommand.length == 3) {
					File ch = new File(dir.concat("/" + InputCommand[2]));
					// IF CHANNEL IS SPECIFIED CHECK IF IT'S A VALID
					// DIRECTORY
					if (ch.isDirectory()) {
						File[] files = ch.listFiles();
						members = new ArrayMember(dir.concat("/users.json"));
						// THE HASHMAP WILL STORE JSON FILES AND FOR EACH HIS MENTIONS
						HashMap<String, ArrayMentions> chmentions = new HashMap<String, ArrayMentions>();
						ArrayMentions marrglobal = new ArrayMentions();

						for (File file : files) {
							ArrayMentions datementions = new ArrayMentions(file.getCanonicalPath(), members);
							chmentions.put(file.getName(), datementions);
						}
						for (Object keyobj : chmentions.keySet()) {
							ArrayMentions value = chmentions.get(keyobj);
							// MERGE EVERY SINGLE MENTION OBJ TO THE MAIN @M.ARRAY
							if (marrglobal.getMentions().size() == 0) {
								marrglobal.setArray(value.getMentions());
							} else {
								for (Object o : value.getMentions()) {
									Mention mobj = (Mention) o;
									marrglobal.merge(mobj);
								}
							}
						}
						if (marrglobal.getMentions().size() > 0) {
							marrglobal.printMentions();
						} else {
							System.out.println("\n NONE MENTIONS IN THIS CHANNEL");
						}

					} else {
						System.out.println("\n THERE IS NO CHANNELS BY THIS NAME");
						Helper.stampaLogo();
						Helper.stampaHelp();
					}
				} else if (InputCommand.length == 2) {
					members = new ArrayMember(dir.concat("/users.json"));
					ArrayMentions marrglobal = new ArrayMentions();
					ArrayList<ArrayMentions> buffer = new ArrayList<ArrayMentions>();
					// THIS ALGORITHM WILL FIND EVERY JSON CHAT FILE AND FILL THE BUFFER
					File ws = new File(dir);
					File[] wsfiles = ws.listFiles();
					for (File wsfile : wsfiles) {
						if (wsfile.isDirectory()) {
							File[] chfiles = wsfile.listFiles();
							for (File jfile : chfiles) {
								ArrayMentions datementions = new ArrayMentions(jfile.getCanonicalPath(), members);
								buffer.add(datementions);
							}
						}
					}
					// MERGE EVERY SINGLE MENTION FROM THE BUFFER TO THE GLOBAL MENTIONS ARRAY
					for (Object buffobj : buffer) {
						ArrayMentions marr = (ArrayMentions) buffobj;
						if (marrglobal.getMentions().size() == 0) {
							marrglobal.setArray(marr.getMentions());
						} else {
							for (Object mobj : marr.getMentions()) {
								Mention mention = (Mention) mobj;
								marrglobal.merge(mention);
							}
						}
					}
					if (marrglobal.getMentions().size() > 0) {
						marrglobal.printMentions();
					} else {
						System.out.println("\n NONE MENTIONS IN THIS WORKSPACE");
					}
				} else {
					System.out.println("\n Please type a valid command"); // LESS OR MORE ARGUMENTS IN THE COMMAND
					Helper.stampaLogo();
					Helper.stampaHelp();
				}

				break;

			default:
				Helper.stampaLogo();
				Helper.stampaHelp();
			}
		} catch (Exception e) {

			System.out.println("\nERROR: WRONG COMMAND, Please type HELP for Command List");
			Helper.stampaLogo();
			Helper.stampaHelp();
		}

	}
}
