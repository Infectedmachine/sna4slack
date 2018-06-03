package snaforslack.data.list;

import java.util.ArrayList;
import java.util.List;

import snaforslack.data.structures.SlackUser;
import snaforslack.interfaces.list.IntListUsers;
import snaforslack.interfaces.structures.IntUser;

/**
 * List User class for a List of Users.
 **/
public class ListUsers implements IntListUsers {
	/**
	 * Private list of users.
	 **/
	private final List<IntUser> usersList;

	/**
	 * Public constructor.
	 **/
	public ListUsers() {
		this.usersList = new ArrayList<IntUser>();
	}

	/**
	 * Gets the list of users.
	 *
	 * @return List<IntUser>
	 **/
	@Override
	public List<IntUser> getUsersList() {
		return this.usersList;
	}

	/**
	 * Adds a user given in input.
	 *
	 * @param user
	 *            The user to add.
	 **/
	@Override
	public void addUser(final IntUser user) {
		this.usersList.add(user);
	}

	/**
	 * Gets the numbers of members.
	 *
	 * @return int
	 **/
	@Override
	public int listSize() {
		return this.usersList.size();
	}

	/**
	 * Checks if there are no Users.
	 *
	 * @return Boolean
	 **/
	@Override
	public boolean listIsEmpty() {
		return this.usersList.isEmpty();
	}

	/**
	 * Returns a SlackUser object which name matches the given parameter.
	 * 
	 * @param value
	 *            a String object.
	 * @return a SlackUser object.
	 */
	public IntUser getUser(final String value) {
		for (final IntUser user : this.usersList) {
			if (user.checkUser(value)) {
				return user;
			}
		}
		return new SlackUser(null, "USER NOT FOUND");
	}

	/**
	 * Checks if there is a user's name that matches the given parameter.
	 * 
	 * @param value
	 *            a String.
	 * @return a boolean value.
	 */
	public boolean checkUser(final String value) {
		for (final IntUser user : this.usersList) {
			if (user.checkUser(value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Prints all the users by the name from the list.
	 */
	public void printList() {
		if (this.usersList.isEmpty()) {
			System.out.println("NONE MEMBERS");
		} else {
			for (final IntUser user : this.usersList) {
				System.out.println(user.getUserName());
			}
		}
	}
}
