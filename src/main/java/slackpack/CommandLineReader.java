package slackpack;

import java.util.HashMap;

/**
 * This class consists of the attributes and methods to read commands.
 **/
public class CommandLineReader {
	/**
	 * Private attribute workspacedir, the first parameter.
	 **/
	private String workspacedir;
	/**
	 * Private attribute inputcommand, the second parameter.
	 **/
	private String inputcommand;
	/**
	 * Private attribute channelname, the name of a specified channel.
	 **/
	private String channelname;
	/**
	 * Private attribute username, the name of a specified member.
	 **/
	private String username;
	/**
	 * Private attribute CommandTable, it consists in the command and their meaning.
	 **/
	private HashMap<String, String> commandTable;
	/**
	 * Public constant, the max numbers of parameters.
	 **/
	public static final int MAX_PARAMETERS = 4;
	/**
	 * Public constant, the index of the workspace.
	 **/
	public static final int INDEX_WORKSPACE = 0;
	/**
	 * Public constant, the index of command.
	 **/
	public static final int INDEX_COMMAND = 1;
	/**
	 * Public constant, the index of user name.
	 **/
	public static final int INDEX_USERNAME = 2;
	/**
	 * Public constant, the index of channel name.
	 **/
	public static final int INDEX_CHNAME = 3;

	/**
	 * Public constructor.
	 **/
	public CommandLineReader() {
		this.setWorkspaceDir("");
		this.setInputCommand("");
		this.setChannelName("");
		this.setUsername("");
		this.setCommandTable(new HashMap<String, String>());
		this.setDefaultCommandTable();
	}

	/**
	 * It sets the commandtable with the input parameter.
	 *
	 * @param commandtable the table to be set.
	 *
	 **/
	public final void setCommandTable(final HashMap<String, String> commandtable) {
		this.commandTable = commandtable;
	}

	/**
	 * It sets the commandtable with the default parameter.
	 **/
	public final void setDefaultCommandTable() {
		this.commandTable.put("-m", "PRINT_MEMBERS");
		this.commandTable.put("-c", "PRINT_CHANNELS");
		this.commandTable.put("-mc", "ALL_CHANNELS_MEMBERS");
		this.commandTable.put("-cm", "CHANNEL_MEMBERS");
		this.commandTable.put("@m", "MENTIONS");
		this.commandTable.put("@mf", "MENTIONS_FROM");
		this.commandTable.put("@mt", "MENTIONS_TO");
		this.commandTable.put("@mw", "MENTIONS_WEIGHT");
		this.commandTable.put("@mfw", "MENTIONS_FROM_WEIGHT");
		this.commandTable.put("@mtw", "MENTIONS_TO_WEIGHT");
	}

	/**
	 * It sets the workspacedir with the input parameter.
	 *
	 * @param wdir the workspace path
	 *
	 **/
	public final void setWorkspaceDir(final String wdir) {
		this.workspacedir = wdir;
	}

	/**
	 * It sets the inputcommand with the input parameter.
	 *
	 * @param icommand the command to be set
	 *
	 **/
	public final void setInputCommand(final String icommand) {
		this.inputcommand = icommand;
	}

	/**
	 * It sets the channelname with the input parameter.
	 *
	 * @param chname
	 *            the channel's name to be set.
	 *
	 **/
	public final void setChannelName(final String chname) {
		this.channelname = chname;
	}

	/**
	 * It sets the username with the input parameter.
	 *
	 * @param usname the name of the member
	 *
	 **/
	public final void setUsername(final String usname) {
		this.username = usname;
	}

	/**
	 * It gets the workspace path.
	 * @return workspacedir string
	 **/
	public String getWorkspaceDir() {
		return this.workspacedir;
	}
	/**
	 * It gets the inputcommand.
	 * @return inputcommand string
	 **/
	public String getInputCommand() {
		return this.inputcommand;
	}
	/**
	 * It gets the channel's name.
	 * @return channelname string
	 **/
	public String getChannelName() {
		return this.channelname;
	}
	/**
	 * It gets the username.
	 * @return username string
	 **/
	public String getUsername() {
		return this.username;
	}

	/**
	 * It fills the attributes depending on what the user writes.
	 *
	 * @param icommand the args from the main class.
	 *
	 * @throws Exception when the command are not valid.
	 *
	 **/
	public final void fillCommandsSetFromArgs(final String[] icommand)  throws Exception {
		if (icommand.length < 2 || !isValidCommand(icommand[1])) {
			throw new Exception("INVALID COMMAND");
		} else {
			this.setWorkspaceDir(icommand[INDEX_WORKSPACE]);			//position 0
			this.setInputCommand(icommand[INDEX_COMMAND]);				//position 1
			if (icommand.length == MAX_PARAMETERS) {					// 4
				this.setUsername(icommand[INDEX_USERNAME]);				//position 2
				this.setChannelName(icommand[INDEX_CHNAME]);			//position 3
			} else
				if (icommand.length == MAX_PARAMETERS - 1) {			// 3
					if (this.getInputCommand().matches("-cm|@m|@mw")) {
						this.setChannelName(icommand[INDEX_USERNAME]);	//position 2
					} else {
						this.setUsername(icommand[INDEX_USERNAME]);		//position 2
					}
			}
		}
	}

	/**
	 * It checks if the commands are valid.
	 *
	 * @param command the command to be checked.
	 *
	 * @return true if the commands are valid.
	 **/
	public boolean isValidCommand(final String command) {
		return ((this.commandTable.get(command) != null));
	}
}

