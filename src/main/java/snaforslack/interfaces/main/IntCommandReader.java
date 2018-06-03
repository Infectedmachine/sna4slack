package snaforslack.interfaces.main;

/**
 * Interface of the CommandReader.
 **/
public interface IntCommandReader {

	/**
	 * It gets the workspace path.
	 * 
	 * @return String
	 **/
	String getWorkspaceDir();

	/**
	 * It gets the inputcommand.
	 * 
	 * @return inputcommand string
	 **/
	String getInputCommand();

	/**
	 * It gets the channel's name.
	 * 
	 * @return channelname string
	 **/
	String getChannelName();

	/**
	 * It gets the username.
	 * 
	 * @return username string
	 **/
	String getUsername();

	/**
	 * It fills the attributes depending on what the user writes.
	 *
	 * @param args
	 *            the args from the main class.
	 * @return int value -1 if command is not valid, 1 otherwise
	 **/
	int initializeFromArgs(String... args);

	/**
	 * It checks if the commands are valid.
	 *
	 * @param command
	 *            the command to be checked.
	 *
	 * @return true if the commands are valid.
	 **/
	boolean isValidCommand(String command);

	/**
	 * Check if channelname is set from command line.
	 * 
	 * @return a boolean value.
	 */
	boolean isChannelSet();
}
