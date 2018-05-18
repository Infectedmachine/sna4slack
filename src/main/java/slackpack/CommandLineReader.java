package slackpack;

import java.util.HashMap;

public class CommandLineReader {
	private String workspacedir;
	private String inputcommand;
	private String channelname;
	private String username;
	HashMap<String, String> CommandTable;

	public CommandLineReader() {
		this.setWorkspaceDir("");
		this.setInputCommand("");
		this.setChannelName("");
		this.setUsername("");
		this.setCommandTable(new HashMap<String, String>());
		this.setDefaultCommandTable();
	}

	public final void setCommandTable(HashMap<String, String> commandtable) {
		this.CommandTable = commandtable;
	}

	public final void setDefaultCommandTable() {
		this.CommandTable.put("-m", "PRINT_MEMBERS");
		this.CommandTable.put("-c", "PRINT_CHANNELS");
		this.CommandTable.put("-mc", "ALL_CHANNELS_MEMBERS");
		this.CommandTable.put("-cm", "CHANNEL_MEMBERS");
		this.CommandTable.put("@m", "MENTIONS");
		this.CommandTable.put("@mf", "MENTIONS_FROM");
		this.CommandTable.put("@mt", "MENTIONS_TO");
		this.CommandTable.put("@mw", "MENTIONS_WEIGHT");
		this.CommandTable.put("@mfw", "MENTIONS_FROM_WEIGHT");
		this.CommandTable.put("@mtw", "MENTIONS_TO_WEIGHT");
	}

	public final void setWorkspaceDir(String workspacedir) {
		this.workspacedir = workspacedir;
	}

	public final void setInputCommand(String inputcommand) {
		this.inputcommand = inputcommand;
	}

	public final void setChannelName(String channelname) {
		this.channelname = channelname;
	}

	public final void setUsername(String username) {
		this.username = username;
	}

	public String getWorkspaceDir() {
		return this.workspacedir;
	}

	public String getInputCommand() {
		return this.inputcommand;
	}

	public String getChannelName() {
		return this.channelname;
	}

	public String getUsername() {
		return this.username;
	}

	public final void fillCommandsSetFromArgs(String[] inputcommand)  throws Exception {
		if (inputcommand.length < 2 || !isValidCommand(inputcommand[1]))
			throw new Exception ("INVALID COMMAND");
		else {
			this.setWorkspaceDir(inputcommand[0]);
			this.setInputCommand(inputcommand[1]);
			if (inputcommand.length == 4) {
				this.setUsername(inputcommand[2]);
				this.setChannelName(inputcommand[3]);
			} else
				if (inputcommand.length == 3){
					if (this.getInputCommand().matches("-cm|@m|@mw"))
						this.setChannelName(inputcommand[2]);
					else
						this.setUsername(inputcommand[2]);
			}
		}
	}

	public boolean isValidCommand(String command) {
		return ((this.CommandTable.get(command) != null) ? true : false);
	}
}