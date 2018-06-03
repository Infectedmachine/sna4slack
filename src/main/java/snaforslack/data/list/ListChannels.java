package snaforslack.data.list;

import java.util.ArrayList;
import java.util.List;

import snaforslack.data.structures.SlackChannel;
import snaforslack.interfaces.list.IntListChannels;
import snaforslack.interfaces.structures.IntChannel;

/**
 * List Channels class for a List of Channels.
 **/
public class ListChannels implements IntListChannels {
	/**
	 * Indicates the not found channel.
	 */
	public static final IntChannel NOTFOUND = new SlackChannel(null, "CHANNEL NOT FOUND");
	/**
	 * Private list of channels.
	 **/
	private final List<IntChannel> channelList;

	/**
	 * Public constructor.
	 **/
	public ListChannels() {
		this.channelList = new ArrayList<IntChannel>();
	}

	/**
	 * Gets the list of channels.
	 *
	 * @return List<IntChannel>
	 **/
	@Override
	public List<IntChannel> getChannelsList() {
		return this.channelList;
	}

	/**
	 * Adds a channel given in input.
	 *
	 * @param channel
	 *            The channel to add.
	 **/
	@Override
	public void addChannel(final IntChannel channel) {
		this.channelList.add(channel);
	}

	/**
	 * Gets the numbers of channels.
	 *
	 * @return int
	 **/
	@Override
	public int listSize() {
		return this.channelList.size();
	}

	/**
	 * Checks if there are no Channels.
	 *
	 * @return Boolean
	 **/
	@Override
	public boolean listIsEmpty() {
		return this.channelList.isEmpty();
	}

	/**
	 * Prints members of a channel.
	 * 
	 * @param name
	 *            channel's name.
	 */
	public void printUsersOfChannels(final String name) {
		if (name != null) {
			for (final IntChannel channel : this.channelList) {
				if (channel.checkName(name)) {
					channel.printMembers();
				}
			}
		}
	}

	/**
	 * Check if there is any channel by this name.
	 * 
	 * @param name
	 *            channel's name.
	 * @return boolean value.
	 */
	public boolean checkChannel(final String name) {
		for (final IntChannel channel : this.channelList) {
			if (channel.checkName(name)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns a Channel checked by name.
	 * 
	 * @param name
	 *            channel's name.
	 * @return IntChannel object.
	 */
	public IntChannel getChannel(final String name) {
		for (final IntChannel channel : this.channelList) {
			if (channel.checkName(name)) {
				return channel;
			}
		}
		return NOTFOUND;
	}
}
