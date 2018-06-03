package snaforslack.interfaces.list;

import java.util.List;

import snaforslack.interfaces.structures.IntChannel;

/**
 * Interface of ListChannels.
 **/
public interface IntListChannels {
	/**
	 * Gets a list of channels.
	 *
	 * @return List<IntChannel>
	 **/
	List<IntChannel> getChannelsList();

	/**
	 * Adds a channel given in input.
	 *
	 * @param channel
	 *            Channel to be added.
	 **/
	void addChannel(IntChannel channel);

	/**
	 * Gets the numbers of channels.
	 *
	 * @return int
	 **/
	int listSize();

	/**
	 * Checks if a list is empty.
	 *
	 * @return Boolean
	 **/
	boolean listIsEmpty();

	/**
	 * Prints members of a channel.
	 * 
	 * @param name
	 *            channel's name.
	 */
	void printUsersOfChannels(String name);

	/**
	 * Check if there is any channel by this name.
	 * 
	 * @param name
	 *            channel's name.
	 * @return boolean value.
	 */
	boolean checkChannel(String name);

	/**
	 * Returns a Channel checked by name.
	 * 
	 * @param name
	 *            channel's name.
	 * @return IntChannel object.
	 */
	IntChannel getChannel(String name);
}
