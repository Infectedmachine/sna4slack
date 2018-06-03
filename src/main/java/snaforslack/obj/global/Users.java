package snaforslack.obj.global;

import snaforslack.data.list.ListUsers;
import snaforslack.data.structures.SlackUser;
import snaforslack.interfaces.json.IntJsonArray;
import snaforslack.interfaces.json.IntJsonObject;
import snaforslack.interfaces.list.IntListUsers;
import snaforslack.interfaces.obj.IntObjUsers;
import snaforslack.interfaces.structures.IntUser;

/**
 * Contains and manages the informations of all of the workspace users.
 *
 */
public class Users implements IntObjUsers {
	/**
	 * Class attribute instance of a not found user.
	 */
	public static final IntUser NOTFOUND = new SlackUser(null, "USER NOT FOUND");

	/**
	 * Class attribute, a List of users.
	 */
	private final IntListUsers globalUsers;

	/**
	 * Allocates the space for the class attribute globalUsers.
	 */
	public Users() {
		this.globalUsers = new ListUsers();
	}

	/**
	 * Initializes the class attribute globalUsers with the information contained in
	 * the given parameter.
	 * 
	 * @param jarray
	 *            a JsonArray.
	 */
	@Override
	public final void initializeFromJsonArray(final IntJsonArray jarray) {
		if (!jarray.isEmpty()) {
			for (final IntJsonObject json : jarray.getList()) {
				final String userId = json.getId();
				final String userName = json.getName();
				this.globalUsers.addUser(this.createNewUser(userId, userName));
			}
		}
	}

	/**
	 * Returns a new class of type SlackUser.
	 * 
	 * @param userId
	 *            a string object
	 * @param userName
	 *            a string object
	 * @return returns a SlackUser class
	 */
	private SlackUser createNewUser(final String userId, final String userName) {
		return new SlackUser(userId, userName);
	}

	/**
	 * Prints all of the workspace users.
	 */
	@Override
	public void printList() {
		if (!this.globalUsers.listIsEmpty()) {
			for (final IntUser user : this.globalUsers.getUsersList()) {
				System.out.println(user.getUserName());
			}
		}
	}

	/**
	 * Returns a SlackUser object which name matches the given parameter.
	 * 
	 * @param value
	 *            a String object.
	 * @return a SlackUser object.
	 */
	@Override
	public IntUser getUser(final String value) {
		return this.globalUsers.getUser(value);
	}

	/**
	 * Checks if there is a user's name that matches the given parameter.
	 * 
	 * @param value
	 *            a String.
	 * @return a boolean value.
	 */
	@Override
	public boolean checkUser(final String value) {
		return this.globalUsers.checkUser(value);
	}
}
