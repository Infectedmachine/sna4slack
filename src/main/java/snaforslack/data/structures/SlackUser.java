package snaforslack.data.structures;

import snaforslack.interfaces.structures.IntUser;

/**
 * Slackuser class for a User.
 **/
public class SlackUser implements IntUser {
	/**
	 * User Id.
	 **/
	private final String userId;
	/**
	 * User Name.
	 **/
	private final String userName;

	/**
	 * Public constructor.
	 *
	 * @param userid
	 *            String User id
	 *
	 * @param name
	 *            String User name
	 **/
	public SlackUser(final String userid, final String name) {
		this.userId = userid;
		this.userName = name;
	}

	/**
	 * Gets the user id.
	 *
	 * @return userId
	 **/
	@Override
	public String getUserId() {
		return this.userId;
	}

	/**
	 * Gets the user name.
	 *
	 * @return userName
	 **/
	@Override
	public String getUserName() {
		return this.userName;
	}

	/**
	 * Checks if there is a user's name that matches the given parameter.
	 * 
	 * @param value
	 *            a String.
	 * @return a boolean value.
	 */
	public boolean checkUser(final String value) {
		if (value == null) {
			return false;
		} else {
			return userId.equals(value) || userName.equals(value);
		}
	}

	/**
	 * Checks if the user is an empty type object.
	 * 
	 * @return a boolean value.
	 */
	public boolean isEmpty() {
		return this.userId == null && this.userName.equals("USER NOT FOUND");
	}
}
