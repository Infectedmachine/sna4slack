package snaforslack.interfaces.list;

import java.util.List;

import snaforslack.interfaces.structures.IntUser;
import snaforslack.interfaces.structures.IntUserMentions;

/**
 * Interface of ListUserMentions.
 **/
public interface IntListUserMentions {
	/**
	 * Gets a list of mentions.
	 *
	 * @return List<IntUserMentions>
	 **/
	List<IntUserMentions> getMentionsList();

	/**
	 * Adds to the list a mentions object.
	 * 
	 * @param mentions
	 *            object to add to the list.
	 */
	void addMentions(IntUserMentions mentions);

	/**
	 * Checks if there is a sender like the one in input.
	 *
	 * @param sender
	 *            String
	 *
	 * @return Boolean
	 **/
	boolean checkSender(String sender);

	/**
	 * Checks if the User is ever mentioned.
	 * 
	 * @param user
	 *            a IntUser value.
	 * @return a boolean value.
	 */
	boolean checkReceiver(IntUser user);

	/**
	 * Gets the mentions made by a member.
	 *
	 * @param sender
	 *            String
	 *
	 * @return IntUserMentions
	 **/
	IntUserMentions getSenderMentions(String sender);

	/**
	 * Returns true is the list is empty.
	 * 
	 * @return boolean value.
	 */
	boolean isEmpty();

	/**
	 * Checks for sender and adds missed users to the receivers list.
	 * 
	 * @param userMentions
	 *            sender's mentions object
	 */
	void addMissingReceivers(IntUserMentions userMentions);
}
