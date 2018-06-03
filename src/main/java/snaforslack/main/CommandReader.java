package snaforslack.main;

import java.util.HashMap;
import java.util.Map;
import snaforslack.interfaces.main.IntCommandReader;
import snaforslack.interfaces.main.Helper;

/**
 *
 * Contains all of the information needed by the software to interpret the input
 * commands.
 * 
 * @author aleningi
 *
 */
public class CommandReader implements IntCommandReader {

	/**
	 * The position of the workspace directory in the parameter given to the
	 * initializeFromArgs function.
	 */
	private static final int WORKSPACEDIR = 0;

	/**
	 * The position of the command in the parameter given to the initializeFromArgs
	 * function.
	 */
	private static final int COMMAND = 1;

	/**
	 * The position of the username in the parameter given to the initializeFromArgs
	 * function.
	 */
	private static final int USERNAME = 2;

	/**
	 * The position of the channel name in the parameter given to the
	 * initializeFromArgs function.
	 */
	private static final int CHANNEL = 3;

	/**
	 * The position of the special command case that could be present in the
	 * parameter given to the initializeFromArgs function.
	 */
	private static final int SPECIALCASE = 2;

	/**
	 * The maximal dimension of the parameter given to the initializeFromArgs
	 * function.
	 */
	private static final int FULL = 4;

	/**
	 * The position of the specification in the parameter given to the
	 * initializeFromArgs function.
	 */
	private static final int SPECIFIC = 3;
	/**
	 * Contains the path to the workspace directory.
	 */
	private String workspacedir;

	/**
	 * Contains the command to run.
	 */
	private String inputcommand;

	/**
	 * Contains, if present, the specified channel name.
	 */
	private String channelname;

	/**
	 * Contains, if present, the specified user name.
	 */
	private String username;

	/**
	 * Contains all of the commands that the software can run and their meanings.
	 */
	private final Map<String, String> commandTable;

	/**
	 * Initialize the class attributes.
	 */
	public CommandReader() {
		this.initializeAll();
		this.commandTable = new HashMap<String, String>();
		this.setDefaultCommandTable();
	}

	/**
	 * Allocates the string class attributes needed space.
	 */
	private void initializeAll() {
		this.workspacedir = "";
		this.inputcommand = "";
		this.channelname = "";
		this.username = "";
	}

	/**
	 * Fills the commandTable with the software default commands.
	 */
	private void setDefaultCommandTable() {
		this.commandTable.put("help", "PRINT_HELPER");
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
	 * Sets attribute workspacedir to the specified parameter.
	 * 
	 * @param wsdir
	 *            a String.
	 */
	private void setWorkspaceDir(final String wsdir) {
		this.workspacedir = wsdir;
	}

	/**
	 * Sets attribute inputcommand to the specified parameter.
	 * 
	 * @param incomm
	 *            a String.
	 */
	private void setInputCommand(final String incomm) {
		this.inputcommand = incomm;
	}

	/**
	 * Sets attribute channelname to the specified parameter.
	 * 
	 * @param name
	 *            a String.
	 */
	private void setChannelName(final String name) {
		this.channelname = name;
	}

	/**
	 * Sets attribute username to the specified parameter.
	 * 
	 * @param usrname
	 *            a String.
	 */
	private void setUsername(final String usrname) {
		this.username = usrname;
	}

	/**
	 * Returns the a class attribute.
	 * 
	 * @return workspacedir a String.
	 */
	@Override
	public String getWorkspaceDir() {
		return this.workspacedir;
	}

	/**
	 * Returns the a class attribute.
	 * 
	 * @return inputcommand a String.
	 */
	@Override
	public String getInputCommand() {
		return this.inputcommand;
	}

	/**
	 * Returns the a class attribute.
	 * 
	 * @return channelname a String.
	 */
	@Override
	public String getChannelName() {
		return this.channelname;
	}

	/**
	 * Returns the a class attribute.
	 * 
	 * @return username a String.
	 */
	@Override
	public String getUsername() {
		return this.username;
	}

	/**
	 * Initializes the class attributes with the content of the given parameter.
	 * 
	 * @param commands
	 *            an array of String.
	 */
	@Override
	public final int initializeFromArgs(final String... commands) {
		final int size = this.getSize(commands);
		boolean valid = false;

		if (size >= 2) {
			valid = this.isValidCommand(commands[COMMAND]);
		}
		if (size < 2 || !valid) {
			if (!commands[0].equals("help")) {
				System.out.println("INVALID COMMAND, Please type a valid one.");
			}
			Helper.stampaLogo();
			Helper.stampaHelp();
			return -1;
		} else {
			this.setWorkspaceDir(commands[WORKSPACEDIR]);
			this.setInputCommand(commands[COMMAND]);
			if (size == FULL) {
				this.setUsername(commands[USERNAME]);
				this.setChannelName(commands[CHANNEL]);
			} else if (size == SPECIFIC) {
				if (this.getInputCommand().matches("-cm|@m|@mw")) {
					this.setChannelName(commands[SPECIALCASE]);
				} else {
					this.setUsername(commands[USERNAME]);
				}
			}
		}
		return 1;
	}

	/**
	 * Returns the number of elements of an array.
	 * 
	 * @param array
	 *            String[] type.
	 * @return integer.
	 */
	private int getSize(final String... array) {
		return array.length;
	}

	/**
	 * Controls if the given parameter is present in the commandTable.
	 * 
	 * @param command
	 *            a String.
	 */
	@Override
	public boolean isValidCommand(final String command) {
		return this.commandTable.get(command) != null;
	}

	/**
	 * Check if channelname is set from command line.
	 * 
	 * @return a boolean value.
	 */
	public boolean isChannelSet() {
		return !this.channelname.isEmpty();
	}
}
