package slackpack;

import java.io.File;
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
					if (channels.checkChannel(InputCommand[2])) {
						System.out.println(channels.getChannel(InputCommand[2]).getName() + ":\n");
						channels.getChannel(InputCommand[2]).printMembersList();
					} else {
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
				if (InputCommand.length == 3) {
					JFileScanner jfiles = new JFileScanner(dir.concat("/" + InputCommand[2]));
					members = new ArrayMember(dir.concat("/users.json"));
					channels = new ArrayChannel(dir.concat("/channels.json"), members);
					ArrayMentions marrglobal = new ArrayMentions();
					for (File file : jfiles.getArray()) {
						ArrayMentions jmarr = new ArrayMentions(file.getCanonicalPath(), members,
								channels.getChannel(InputCommand[2]).getMembersList());
						marrglobal.mergeArray(jmarr.getArray());
					}
					if (marrglobal.getArray().size() > 0) {
						marrglobal.printMentions();
					} else {
						System.out.println("\n NONE MENTIONS IN THIS CHANNEL");
						Helper.stampaLogo();
						Helper.stampaHelp();
					}
				} else if (InputCommand.length == 2) {
					JFileScanner jfiles;
					members = new ArrayMember(dir.concat("/users.json"));
					channels = new ArrayChannel(dir.concat("/channels.json"), members);
					ArrayList<String> channelslist = channels.getAllChannelsName();
					ArrayMentions marrglobal = new ArrayMentions();
					for (String channelname : channelslist) {
						jfiles = new JFileScanner(dir.concat("/" + channelname));
						for (File file : jfiles.getArray()) {
							ArrayMentions jmarr = new ArrayMentions(file.getCanonicalPath(), members,
									channels.getChannel(channelname).getMembersList());
							marrglobal.mergeArray(jmarr.getArray());
						}
					}
					if (marrglobal.getArray().size() > 0) {
						marrglobal.printMentions();
					} else {
						System.out.println("\nNONE MENTIONS IN THIS WORKSPACE");
					}
				} else {
					System.out.println("\nPlease type a valid command");
					Helper.stampaLogo();
					Helper.stampaHelp();
				}
				break;
			case "@mf":
				if (InputCommand.length == 4) {

					JFileScanner jfiles = new JFileScanner(dir.concat("/" + InputCommand[3]));
					members = new ArrayMember(dir.concat("/users.json"));
					channels = new ArrayChannel(dir.concat("/channels.json"), members);
					ArrayMentions marrglobal = new ArrayMentions();
					for (File file : jfiles.getArray()) {
						ArrayMentions jmarr = new ArrayMentions(file.getCanonicalPath(), members,
								channels.getChannel(InputCommand[3]).getMembersList());
						marrglobal.mergeArray(jmarr.getArray());
					}
					if (marrglobal.getArray().size() > 0) {
						if (marrglobal.checkUser(InputCommand[2]))
							marrglobal.printMentionsOf(InputCommand[2]);
						else {
							System.out.println("\n THERE IS NO USER BY THIS NAME");
							Helper.stampaLogo();
							Helper.stampaHelp();
						}
					} else {
						System.out.println("\n NONE MENTIONS IN THIS CHANNEL BY THIS MEMBER");
						Helper.stampaLogo();
						Helper.stampaHelp();
					}

				} else if (InputCommand.length == 3) {

					JFileScanner jfiles;
					members = new ArrayMember(dir.concat("/users.json"));
					channels = new ArrayChannel(dir.concat("/channels.json"), members);
					ArrayList<String> channelslist = channels.getAllChannelsName();
					ArrayMentions marrglobal = new ArrayMentions();
					for (String channelname : channelslist) {
						jfiles = new JFileScanner(dir.concat("/" + channelname));
						for (File file : jfiles.getArray()) {
							ArrayMentions jmarr = new ArrayMentions(file.getCanonicalPath(), members,
									channels.getChannel(channelname).getMembersList());
							marrglobal.mergeArray(jmarr.getArray());
						}
					}
					if (marrglobal.getArray().size() > 0) {
						if (marrglobal.checkUser(InputCommand[2]))
							marrglobal.printMentionsOf(InputCommand[2]);
						else {
							System.out.println("\n THERE IS NO USER BY THIS NAME");
							Helper.stampaLogo();
							Helper.stampaHelp();
						}
					} else {
						System.out.println("\nNONE MENTIONS IN THIS WORKSPACE BY THIS MEMBER");
					}

				} else {
					System.out.println("\nPlease type a valid command");
					Helper.stampaLogo();
					Helper.stampaHelp();
				}

				break;
				
			case "@mt":
				if (InputCommand.length == 4) {

					JFileScanner jfiles = new JFileScanner(dir.concat("/" + InputCommand[3]));
					members = new ArrayMember(dir.concat("/users.json"));
					channels = new ArrayChannel(dir.concat("/channels.json"), members);
					ArrayMentions marrglobal = new ArrayMentions();
					for (File file : jfiles.getArray()) {
						ArrayMentions jmarr = new ArrayMentions(file.getCanonicalPath(), members,
								channels.getChannel(InputCommand[3]).getMembersList());
						marrglobal.mergeArray(jmarr.getArray());
					}
					if (marrglobal.getArray().size() > 0) {
						if (marrglobal.checkUserTo(InputCommand[2])) 
							marrglobal.printMentionTo(InputCommand[2]);
						else
							System.out.println("THIS USER WAS NEVER MENTIONED IN THIS CHANNEL");
						
					} else {
						System.out.println("\n NONE MENTIONS IN THIS CHANNEL");
						Helper.stampaLogo();
						Helper.stampaHelp();
					}

				} else if (InputCommand.length == 3) {

					JFileScanner jfiles;
					members = new ArrayMember(dir.concat("/users.json"));
					channels = new ArrayChannel(dir.concat("/channels.json"), members);
					ArrayList<String> channelslist = channels.getAllChannelsName();
					ArrayMentions marrglobal = new ArrayMentions();
					for (String channelname : channelslist) {
						jfiles = new JFileScanner(dir.concat("/" + channelname));
						for (File file : jfiles.getArray()) {
							ArrayMentions jmarr = new ArrayMentions(file.getCanonicalPath(), members,
									channels.getChannel(channelname).getMembersList());
							marrglobal.mergeArray(jmarr.getArray());
						}
					}
					if (marrglobal.getArray().size() > 0) {
						if (marrglobal.checkUserTo(InputCommand[2])) 
							marrglobal.printMentionTo(InputCommand[2]);
						else
							System.out.println("THIS USER WAS NEVER MENTIONED IN THIS WORKSPACE");
					} else {
						System.out.println("\nNONE MENTIONS IN THIS WORKSPACE");
					}

				} else {
					System.out.println("\nPlease type a valid command");
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
