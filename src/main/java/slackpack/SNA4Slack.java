package slackpack;

import java.util.HashMap;

/**
 * Manages private sub class CommandsTable.
 *
 * @author aleningi
 *
 */
interface Command {

	/**
	 * Contains software instruction that will be associated to a command.
	 *
	 * @throws Exception standard exception.
	 */
	void runCommand() throws Exception;
}

/**
 * Manages SNA4Slack software input parameters.
 *
 * @author aleningi
 *
 */
public class SNA4Slack {

	/**
	 * Members of workspace.
	 */
	private ArrayMember members;

	/**
	 * Channels of workspace.
	 */
	private ArrayChannel channels;

	/**
	 * Mentions of workspace.
	 */
	private SlackMentionsFinder mentions;

	/**
	 * zip file containing workspace.
	 */
	private Zip zipfile;

	/**
	 * Parser for members.
	 */
	private JsonFileParser jsonusers;

	/**
	 * Parser for channels.
	 */
	private JsonFileParser jsonchannels;

	/**
	 * Parser for mentions.
	 */
	private JsonFileParser jsonmentions;

	/**
	 * Software command interpreter.
	 */
	private CommandLineReader command;

	/**
	 * Allocate space needed by class attributes.
	 * @param c commands.
	 */
	public SNA4Slack(final CommandLineReader c) {
		this.setMembers(new ArrayMember());
		this.setChannels(new ArrayChannel(this.getMembers()));
		this.setMentions(null);
		this.setZipFile(new Zip());
		this.setJsonUsers(new JsonFileParser());
		this.setJsonChannels(new JsonFileParser());
		this.setJsonMentions(new JsonFileParser());
		this.setCommand(c);

	}

	/**
	 * Runs the software.
	 *
	 * @throws Exception standard exception.
	 */
	public final void run() throws Exception {
		CommandsTable table = new CommandsTable();
		table.defaultCommandsTable();
		table.getCommandsTable().get(this.getCommand().getInputCommand()).runCommand();
	}

	/**
	 * Initialize members.
	 */
	public final void initializeMembers() {
		try {
			this.getMembers().fillArrayFromJSONArray(this.getJsonUsers().getArray());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Initializes jsonusers.
	 */
	public final void initializeSlackUsers() {
		this.getJsonUsers().fillContentsFromJSONFileDir(this.command.getWorkspaceDir().concat("/users.json"));
	}

	/**
	 * Initializes zipfile.
	 */
	public final void extractZipFile() {
		this.zipfile.extractZip(this.command.getWorkspaceDir());
		this.command.setWorkspaceDir(this.zipfile.getDirPath());
	}

	/**
	 * Initializes jsonchannels.
	 */
	public final void initializeSlackChannels() {
		this.getJsonChannels().fillContentsFromJSONFileDir(this.command.getWorkspaceDir().concat("/channels.json"));
	}

	/**
	 * Initializes channels.
	 */
	public final void initializeChannels() {
		try {
			this.getChannels().fillArrayFromJSONArray(this.getJsonChannels().getArray());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Initializes mentions.
	 */
	public final void initializeSlackMentionsFinder() {
		this.mentions = new SlackMentionsFinder(this.getCommand().getWorkspaceDir());
	}

	/**
	 *
	 * @param marr is assigned to members.
	 */
	public final void setMembers(final ArrayMember marr) {
		this.members = marr;
	}

	/**
	 *
	 * @param carr is assigned to channels.
	 */
	public final void setChannels(final ArrayChannel carr) {
		this.channels = carr;
	}

	/**
	 *
	 * @param menarr is assigned to mentions.
	 */
	public final void setMentions(final SlackMentionsFinder menarr) {
		this.mentions = menarr;
	}

	/**
	 *
	 * @param zip is assigned to zipfile.
	 */
	public final void setZipFile(final Zip zip) {
		this.zipfile = zip;
	}

	/**
	 *
	 * @param jusers is assigned to jsonusers.
	 */
	public final void setJsonUsers(final JsonFileParser jusers) {
		this.jsonusers = jusers;
	}

	/**
	 *
	 * @param jchannels is assigned to jsonchannels.
	 */
	public final void setJsonChannels(final JsonFileParser jchannels) {
		this.jsonchannels = jchannels;
	}

	/**
	 *
	 * @param jmentions is assigned to jsonmetions.
	 */
	public final void setJsonMentions(final JsonFileParser jmentions) {
		this.jsonmentions = jmentions;
	}

	/**
	 *
	 * @param c is assigned to command.
	 */
	public final void setCommand(final CommandLineReader c) {
		this.command = c;
	}

	/**
	 *
	 * @return command.
	 */
	public CommandLineReader getCommand() {
		return this.command;
	}

	/**
	 *
	 * @return members.
	 */
	public ArrayMember getMembers() {
		return this.members;
	}

	/**
	 *
	 * @return channels.
	 */
	public ArrayChannel getChannels() {
		return this.channels;
	}

	/**
	 *
	 * @return mentions.
	 */
	public SlackMentionsFinder getMentions() {
		return this.mentions;
	}

	/**
	 *
	 * @return zipfile.
	 */
	public Zip getZipFile() {
		return this.zipfile;
	}

	/**
	 *
	 * @return jsonusers.
	 */
	public JsonFileParser getJsonUsers() {
		return this.jsonusers;
	}

	/**
	 *
	 * @return jsonchannels.
	 */
	public JsonFileParser getJsonChannels() {
		return this.jsonchannels;
	}

	/**
	 *
	 * @return jsonmentions.
	 */
	public JsonFileParser getJsonMentions() {
		return this.jsonmentions;
	}

	/**
	 * Sets software actions based on command receved.
	 *
	 * @author aleningi
	 *
	 */
	private class CommandsTable {

		/**
		 * Contains an overrided function associated to the command received.
		 */
		private HashMap<String, Command> commandsTable;

		/**
		 * Allocates space needed by SubClass attribute.
		 */
		 CommandsTable() {
			this.setCommandsTable(new HashMap<String, Command>());
		}

		/**
		 *
		 * @param ctable is assigned to commandsTable.
		 */
		public final void setCommandsTable(final HashMap<String, Command> ctable) {
			this.commandsTable = ctable;
		}

		/**
		 *
		 * @return commandsTable.
		 */
		public HashMap<String, Command> getCommandsTable() {
			return this.commandsTable;
		}

		/**
		 * Overrides Command interface associating actions to commands.
		 *
		 * @throws Exception standard exception.
		 */
		public final void defaultCommandsTable() throws Exception {
			this.getCommandsTable().put("-m", new Command() {
				@Override
				public void runCommand() {
					SNA4Slack.this.extractZipFile();
					SNA4Slack.this.initializeSlackUsers();
					SNA4Slack.this.initializeMembers();
					SNA4Slack.this.getMembers().printArray();

				}
			});
			this.getCommandsTable().put("-c", new Command() {
				@Override
				public void runCommand() {
					SNA4Slack.this.extractZipFile();
					SNA4Slack.this.initializeSlackUsers();
					SNA4Slack.this.initializeMembers();
					SNA4Slack.this.initializeSlackChannels();
					SNA4Slack.this.initializeChannels();
					SNA4Slack.this.getChannels().printChannels();
				}
			});
			this.getCommandsTable().put("-mc", new Command() {
				@Override
				public void runCommand() {
					SNA4Slack.this.extractZipFile();
					SNA4Slack.this.initializeSlackUsers();
					SNA4Slack.this.initializeMembers();
					SNA4Slack.this.initializeSlackChannels();
					SNA4Slack.this.initializeChannels();
					SNA4Slack.this.getChannels().printArray();
				}
			});
			this.getCommandsTable().put("-cm", new Command() {
				@Override
				public void runCommand() throws Exception {
					SNA4Slack.this.extractZipFile();
					SNA4Slack.this.initializeSlackUsers();
					SNA4Slack.this.initializeMembers();
					SNA4Slack.this.initializeSlackChannels();
					SNA4Slack.this.initializeChannels();
					SNA4Slack.this.getChannels().getChannel(SNA4Slack.this.getCommand().getChannelName())
							.printMembersList();
				}
			});
			this.getCommandsTable().put("@m", new Command() {
				@Override
				public void runCommand() throws Exception {
					SNA4Slack.this.extractZipFile();
					SNA4Slack.this.initializeSlackMentionsFinder();
					if (SNA4Slack.this.getCommand().getChannelName().isEmpty()) {
						SNA4Slack.this.getMentions().executeFinderOnWorkspace();

					} else {
						SNA4Slack.this.getMentions()
								.executeFinderOnChannel(SNA4Slack.this.getCommand().getChannelName());
					}
					SNA4Slack.this.getMentions().printNamedMentions();
				}
			});
			this.getCommandsTable().put("@mf", new Command() {
				@Override
				public void runCommand() throws Exception {
					SNA4Slack.this.extractZipFile();
					SNA4Slack.this.initializeSlackMentionsFinder();
					if (SNA4Slack.this.getCommand().getChannelName().isEmpty()) {
						SNA4Slack.this.getMentions().executeFinderOnWorkspace();

					} else {
						SNA4Slack.this.getMentions()
								.executeFinderOnChannel(SNA4Slack.this.getCommand().getChannelName());
					}
					SNA4Slack.this.getMentions().printNamedMentionsFROM(SNA4Slack.this.getMentions().getMembers()
							.getMemberByName(SNA4Slack.this.getCommand().getUsername()).getID());
				}
			});
			this.getCommandsTable().put("@mt", new Command() {
				@Override
				public void runCommand() throws Exception {
					SNA4Slack.this.extractZipFile();
					SNA4Slack.this.initializeSlackMentionsFinder();
					if (SNA4Slack.this.getCommand().getChannelName().isEmpty()) {
						SNA4Slack.this.getMentions().executeFinderOnWorkspace();

					} else {
						SNA4Slack.this.getMentions()
								.executeFinderOnChannel(SNA4Slack.this.getCommand().getChannelName());
					}
					SNA4Slack.this.getMentions().printNamedMentionsTO(SNA4Slack.this.getMentions().getMembers()
							.getMemberByName(SNA4Slack.this.getCommand().getUsername()).getID());
				}
			});
			this.getCommandsTable().put("@mw", new Command() {
				@Override
				public void runCommand() throws Exception {
					SNA4Slack.this.extractZipFile();
					SNA4Slack.this.initializeSlackMentionsFinder();
					if (SNA4Slack.this.getCommand().getChannelName().isEmpty()) {
						SNA4Slack.this.getMentions().executeFinderOnWorkspace();

					} else {
						SNA4Slack.this.getMentions()
								.executeFinderOnChannel(SNA4Slack.this.getCommand().getChannelName());
					}
					SNA4Slack.this.getMentions().printNamedMentionsWithWeight();
				}
			});
			this.getCommandsTable().put("@mfw", new Command() {
				@Override
				public void runCommand() throws Exception {
					SNA4Slack.this.extractZipFile();
					SNA4Slack.this.initializeSlackMentionsFinder();
					if (SNA4Slack.this.getCommand().getChannelName().isEmpty()) {
						SNA4Slack.this.getMentions().executeFinderOnWorkspace();

					} else {
						SNA4Slack.this.getMentions()
								.executeFinderOnChannel(SNA4Slack.this.getCommand().getChannelName());
					}
					SNA4Slack.this.getMentions().
					printNamedMentionsWithWeightFROM(SNA4Slack.this.getMentions().
							getMembers().getMemberByName(SNA4Slack.this.getCommand().
									getUsername()).getID());
				}
			});
			this.getCommandsTable().put("@mtw", new Command() {
				@Override
				public void runCommand() throws Exception {
					SNA4Slack.this.extractZipFile();
					SNA4Slack.this.initializeSlackMentionsFinder();
					if (SNA4Slack.this.getCommand().getChannelName().isEmpty()) {
						SNA4Slack.this.getMentions().executeFinderOnWorkspace();

					} else {
						SNA4Slack.this.getMentions()
								.executeFinderOnChannel(SNA4Slack.this.getCommand().getChannelName());
					}
					SNA4Slack.this.getMentions().printNamedMentionsWhithWheightTO(SNA4Slack.this.getMentions().
							getMembers().getMemberByName(SNA4Slack.this.getCommand().
									getUsername()).getID());
				}
			});
		}

	}

}
