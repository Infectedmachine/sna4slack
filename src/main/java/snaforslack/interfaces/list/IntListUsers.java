package snaforslack.interfaces.list;

import java.util.List;

import snaforslack.interfaces.structures.IntUser;

/**
 * Interface of ListUsers.
 **/
public interface IntListUsers {
	/**
	 * Gets a list of Users.
	 *
	 * @return List<IntUser>
	 **/
	List<IntUser> getUsersList();

	/**
	 * Adds a user given in input.
	 *
	 * @param user
	 *            The user to add.
	 **/
	void addUser(IntUser user);

	/**
	 * Gets the numbers of members.
	 *
	 * @return int
	 **/
	int listSize();

	/**
	 * Checks if there are no Users.
	 *
	 * @return Boolean
	 **/
	boolean listIsEmpty();

	/**
	 * Gets the user by a parameter such as id or name.
	 * 
	 * @param value
	 *            id or name to check
	 * @return IntUser
	 */
	IntUser getUser(String value);

	/**
	 * Checks if there is any user by value such as id or name.
	 * 
	 * @param value
	 *            id or name to check
	 * @return boolean value
	 */
	boolean checkUser(String value);

	/**
	 * Prints all the users by the name from the list.
	 */
	void printList();
}
