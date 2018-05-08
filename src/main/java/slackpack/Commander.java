package slackpack;

import java.io.File;
import java.util.HashMap;

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
				} else
					System.out.println("\n Please type a valid command"); // LESS OR MORE ARGUMENTS IN THE COMMAND

				break;

			case "-mc": // PRINT MEMBERS LIST PER CHANNEL
				dir = dir.concat("/users.json");
				members = new ArrayMember(dir);
				dir = dir.replace("/users.json", "/channels.json");
				channels = new ArrayChannel(dir, members);
				channels.printArray();
				break;

			case "@m": // PRINT @MENTIONS LIST - ALL/CHANNEL
				// CHECK IF THERE IS A CHANNEL NAME RIGHT AFTER THE COMMAND TO RESTRICT MENTIONS
				// TO ONLY SPECIFIED CHANNEL
				// IF NOT LIST THEM ALL
				File ch = new File(dir.concat("/" + InputCommand[2]));
				File[] files = ch.listFiles();
				members = new ArrayMember(dir.concat("/users.json"));

				if (InputCommand.length > 2 && ch.isDirectory()) { // IF CHANNEL IS SPECIFIED CHECK IF IT'S A VALID
																	// DIRECTORY
					HashMap<String, ArrayMentions> chmentions = new HashMap<String, ArrayMentions>(); // THE HASHMAP
																										// WILL STORE
																										// FILES AND FOR
																										// EACH HIS
																										// MENTIONS
					ArrayMentions marrglobal = new ArrayMentions();

					for (File file : files) {
						ArrayMentions datementions = new ArrayMentions(file.getCanonicalPath(), members);
						/*
						 * if(datementions.mentions != null) { datementions.printMentions(); // TEST }
						 */
						// System.out.println("END OF SINGLE JSON FILE...");
						chmentions.put(file.getName(), datementions);
					}
					for (Object keyobj : chmentions.keySet()) {
						String key = keyobj.toString();
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
						//System.out.println(key);
						//value.printMentions();

					}
					marrglobal.printMentions();

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
