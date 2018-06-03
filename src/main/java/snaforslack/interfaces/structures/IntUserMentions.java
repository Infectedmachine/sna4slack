package snaforslack.interfaces.structures;

import java.util.List;
import java.util.Map;

/**
 * Interface of UserMentions.
 **/
public interface IntUserMentions {
	/**
	 * Gets the sender id.
	 *
	 * @return IntUser
	 **/
	IntUser getSender();

	/**
	 * Gets the list of mentioned users.
	 *
	 * @return IntListUsers
	 **/
	List<IntUser> getListReceiver();

	/**
	 * Adds a User to the Receiver's List.
	 *
	 * @param receiver
	 *            user to add to the list.
	 */
	//void addReceiver(IntUser receiver);

	/**
	 * Gets the map with weighted mentions.
	 *
	 * @return Map<String, Integer>
	 **/
	Map<String, Integer> getMentionWeightMap();

	/**
	 * @param userId
	 *            id of a user
	 * @return int value weight of the mention
	 */
	int getWeight(String userId);

	/**
	 * @return a String containing the id of the sender user.
	 */
	String getSenderId();

	/**
	 *
	 * @return a String containing the name of the sender user.
	 */
	String getSenderName();

	/**
	 * @param value
	 *            value to compare in User with id or name.
	 * @return boolean value.
	 */
	boolean checkSender(String value);

	/**
	 * @param user
	 *            user to check in Receivers List.
	 * @return boolean value.
	 */
	boolean checkReceiver(IntUser user);

	/**
	 * Adds a new user to the receiver list if it's missing.
	 *
	 * @param list
	 *            list of users to check inside receiver's list
	 */
	void addMissingReceivers(List<IntUser> list);
}
