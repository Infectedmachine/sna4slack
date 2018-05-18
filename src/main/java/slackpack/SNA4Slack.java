package slackpack;

import java.util.HashMap;

interface Command {
	void runCommand() throws Exception;
}

public class SNA4Slack {
	private ArrayMember members;
	private ArrayChannel channels;
	private SlackMentionsFinder mentions;
	private Zip zipfile;
	private JsonFileParser jsonusers;
	private JsonFileParser jsonchannels;
	private JsonFileParser jsonmentions;
	private CommandLineReader command;

	public SNA4Slack(CommandLineReader command) {
		this.setMembers(new ArrayMember());
		this.setChannels(new ArrayChannel(this.getMembers()));
		this.setMentions(null);
		this.setZipFile(new Zip());
		this.setJsonUsers(new JsonFileParser());
		this.setJsonChannels(new JsonFileParser());
		this.setJsonMentions(new JsonFileParser());
		this.setCommand(command);

	}

	public final void RUN() throws Exception {
		CommandsTable table = new CommandsTable();
		table.defaultCommandsTable();
		table.getCommandsTable().get(this.getCommand().getInputCommand()).runCommand();
	}

	public final void initializeMembers() {
		try {
			this.getMembers().fillArrayFromJSONArray(this.getJsonUsers().getArray());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public final void initializeSlackUsers() {
		this.getJsonUsers().fillContentsFromJSONFileDir(this.command.getWorkspaceDir().concat("/users.json"));
	}

	public final void extractZipFile() {
		this.zipfile.extractZip(this.command.getWorkspaceDir());
		this.command.setWorkspaceDir(this.zipfile.getDirPath());
	}

	public final void initializeSlackChannels() {
		this.getJsonChannels().fillContentsFromJSONFileDir(this.command.getWorkspaceDir().concat("/channels.json"));
	}

	public final void initializeChannels() {
		try {
			this.getChannels().fillArrayFromJSONArray(this.getJsonChannels().getArray());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public final void initializeSlackMentionsFinder() {
		this.mentions = new SlackMentionsFinder(this.getCommand().getWorkspaceDir());
	}

	public final void setMembers(ArrayMember members) {
		this.members = members;
	}

	public final void setChannels(ArrayChannel channels) {
		this.channels = channels;
	}

	public final void setMentions(SlackMentionsFinder mentions) {
		this.mentions = mentions;
	}

	public final void setZipFile(Zip zipfile) {
		this.zipfile = zipfile;
	}

	public final void setJsonUsers(JsonFileParser jsonusers) {
		this.jsonusers = jsonusers;
	}

	public final void setJsonChannels(JsonFileParser jsonchannels) {
		this.jsonchannels = jsonchannels;
	}

	public final void setJsonMentions(JsonFileParser jsonmentions) {
		this.jsonmentions = jsonmentions;
	}

	public final void setCommand(CommandLineReader command) {
		this.command = command;
	}

	public CommandLineReader getCommand() {
		return this.command;
	}

	public ArrayMember getMembers() {
		return this.members;
	}

	public ArrayChannel getChannels() {
		return this.channels;
	}

	public SlackMentionsFinder getMentions() {
		return this.mentions;
	}

	public Zip getZipFile() {
		return this.zipfile;
	}

	public JsonFileParser getJsonUsers() {
		return this.jsonusers;
	}

	public JsonFileParser getJsonChannels() {
		return this.jsonchannels;
	}

	public JsonFileParser getJsonMentions() {
		return this.jsonmentions;
	}

	private class CommandsTable {
		HashMap<String, Command> commandsTable;

		public CommandsTable() {
			this.setCommandsTable(new HashMap<String, Command>());
		}

		public final void setCommandsTable(HashMap<String, Command> commandstable) {
			this.commandsTable = commandstable;
		}

		public HashMap<String, Command> getCommandsTable() {
			return this.commandsTable;
		}

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
					SNA4Slack.this.getMentions().printNamedMentionsWithWeightFROM(SNA4Slack.this.getMentions().getMembers()
							.getMemberByName(SNA4Slack.this.getCommand().getUsername()).getID());
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
					SNA4Slack.this.getMentions().printNamedMentionsWhithWheightTO(SNA4Slack.this.getMentions().getMembers()
							.getMemberByName(SNA4Slack.this.getCommand().getUsername()).getID());
				}
			});
		}

	}

}
