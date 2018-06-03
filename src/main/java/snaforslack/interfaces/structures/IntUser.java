package snaforslack.interfaces.structures;

/**
 * Interface of User.
 **/
public interface IntUser {
	/**
	 * Gets the User id.
	 *
	 * @return String
	 **/
	String getUserId();

	/**
	 * Gets the user name.
	 *
	 * @return String
	 **/
	String getUserName();

	/**
	 * Checks if there is a user's name that matches the given parameter.
	 * 
	 * @param value
	 *            a String.
	 * @return a boolean value.
	 */
	boolean checkUser(String value);

	/**
	 * Checks if the user is an empty type object.
	 * 
	 * @return a boolean value.
	 */
	boolean isEmpty();
}
