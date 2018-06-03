package snaforslack.interfaces.structures;

import snaforslack.interfaces.list.IntListUsers;

/**
 * Interface of Channel.
 **/
public interface IntChannel {

	/**
	 * Gets the channel id.
	 *
	 * @return String
	 **/
	String getChannelId();

	/**
	 * Gets the channel name.
	 *
	 * @return String
	 **/
	String getChannelName();

	/**
	 * Checks if the channel is archived.
	 *
	 * @return Boolean
	 **/
	boolean isArchived();

	/**
	 * Gets the list of members of a channel.
	 *
	 * @return IntListUsers
	 **/
	IntListUsers getListUsers();

	/**
	 * Sets the list of users.
	 *
	 * @param list
	 *            list to add.
	 **/
	void setListUsers(IntListUsers list);

	/**
	 * Sets the state of archive.
	 *
	 * @param state
	 *            True if the channel is archived.
	 **/
	void setArchived(boolean state);

	/**
	 * Checks if the user is present.
	 *
	 * @param value
	 *            member id to check.
	 *
	 * @return Boolean
	 **/
	boolean checkUser(String value);

	/**
	 * Prints all the users by the name from the list.
	 */
	void printMembers();

	/**
	 * Checks if the List of users is empty.
	 * 
	 * @return boolean value
	 */
	boolean isUsersListEmpty();

	/**
	 * Checks if the channel has the same name of the parameter.
	 * 
	 * @param name
	 *            channel's name.
	 * @return boolean value.
	 */
	boolean checkName(String name);
}
