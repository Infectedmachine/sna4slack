package snaforslack.interfaces.obj;

import snaforslack.interfaces.json.IntJsonArray;
import snaforslack.interfaces.structures.IntUser;

/**
 * Interface of ObjUsers.
 **/
public interface IntObjUsers {
	/**
	 * Initializes the user object from a jsonarray.
	 *
	 * @param jarray IntJsonArray to be used.
	 **/
	void initializeFromJsonArray(IntJsonArray jarray);

	/**
	 * Prints a list of users.
	 **/
	void printList();

	/**
	 * Gets the user given its name or id.
	 *
	 * @param nameOrId name or id of a user.
	 *
	 * @return IntUser
	 **/
	IntUser getUser(String nameOrId);

	/**
	 * Checks if the user is present.
	 *
	 * @param nameOrId name or id of a user.
	 *
	 * @return True if the user id present.
	 **/
	boolean checkUser(String nameOrId);
}
