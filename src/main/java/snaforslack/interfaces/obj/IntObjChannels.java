package snaforslack.interfaces.obj;

import java.util.List;

import snaforslack.interfaces.json.IntJsonArray;
import snaforslack.interfaces.structures.IntChannel;

/**
 * Interface of the ObjChannels.
 **/
public interface IntObjChannels {
	/**
	 * Initializes the object from a jsonarray.
	 *
	 * @param jarray IntJsonArray to be initialized.
	 **/
	void initializeFromJsonArray(IntJsonArray jarray);

	/**
	 * Prints all channels.
	 **/
	void printList();

	/**
	 * Prints all the channels with their members.
	 **/
	void printListDetailed();

	/**
	 * Prints the users of a channel.
	 *
	 * @param channel the id of the channel.
	 **/
	void printUsersOfChannel(String channel);

	/**
	 * Checks if the channel is present.
	 *
	 * @param name the name of the channel.
	 *
	 * @return True if there is a channel like the one in input.
	 **/
	boolean checkChannel(String name);

	/**
	 * Gets the channel interface given ad id.
	 *
	 * @param channel the id of the channel.
	 *
	 * @return IntChannel
	 **/
	IntChannel getChannel(String channel);

	/**
	 * Gets all channels' names.
	 *
	 * @return List of all channels' names.
	 **/
	List<String> getAllChannelsName();
}
