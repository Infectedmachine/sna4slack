package snaforslack.interfaces.obj;

import snaforslack.interfaces.json.IntJsonArray;
import snaforslack.interfaces.structures.IntChannel;

/**
 * Interface of ObjMentions.
 **/
public interface IntObjMentions {
	/**
	 * Initializes the mention object from a jsonarray and a string.
	 *
	 * @param array
	 *            IntJsonArray to be used.ù
	 * @param channel
	 *            channel obj.
	 **/
	void initializeFromJsonArray(IntJsonArray array, IntChannel channel);

	/**
	 * Prints a list of mentions.
	 **/
	void printList();

	/**
	 * Prints on video mentions of a certain Sender.
	 * 
	 * @param sender
	 *            a String value, name of a sender(user).
	 */
	void printSenderMentions(String sender);

	/**
	 * Prints on video mentions to a certain User.
	 * 
	 * @param receiver
	 *            a String value, name of a receiver (user).
	 */
	void printReceiverMentions(String receiver);
}
