package snaforslack.data.structures;

import snaforslack.data.list.ListUsers;
import snaforslack.interfaces.list.IntListUsers;
import snaforslack.interfaces.structures.IntChannel;

/**
 * Contains and manages all of the informations inside a slack channel.
 */
public class SlackChannel implements IntChannel {
	/**
	 * the code that identifies the channel.
	 */
	private final String channelId;

	/**
	 * the name that identifies the channel.
	 */
	private final String channelName;

	/**
	 * Indicates if the channel is archived.
	 */
	private boolean archived;

	/**
	 * Lists the channel users.
	 */
	private IntListUsers listUsers;

	/**
	 * Initializes the class attributes to the given parameters.
	 *
	 * @param channelid
	 *            a String that indicates the channel id.
	 * @param name
	 *            a string that indicate the channel name.
	 */
	public SlackChannel(final String channelid, final String name) {
		this.channelId = channelid;
		this.channelName = name;
		this.listUsers = new ListUsers();
	}

	/**
	 * Sets the channel users to the given paramter.
	 * 
	 * @param list
	 *            a ListUsers object.
	 */
	@Override
	public final void setListUsers(final IntListUsers list) {
		this.listUsers = list;
	}

	/**
	 * Sets the class attribute archived.
	 * 
	 * @param state
	 *            a boolean value.
	 */
	@Override
	public final void setArchived(final boolean state) {
		this.archived = state;
	}

	/**
	 * @return a ListUsers object containing the channel users.
	 */
	@Override
	public IntListUsers getListUsers() {
		if (this.listUsers == null) {
			this.listUsers = new ListUsers();
			return this.listUsers;
		} else {
			return this.listUsers;
		}
	}

	/**
	 * Checks if the user specified in the given parameter matches any channel user.
	 * 
	 * @param value
	 *            a String indicating the user to check.
	 * @return a boolean value.
	 */
	@Override
	public boolean checkUser(final String value) {
		return this.listUsers.checkUser(value);
	}

	/**
	 * @return class attribute channelId.
	 */
	@Override
	public String getChannelId() {
		return this.channelId;
	}

	/**
	 * @return class attribute channelName.
	 */
	@Override
	public String getChannelName() {
		return this.channelName;
	}

	/**
	 * @return class attribute archived.
	 */
	@Override
	public boolean isArchived() {
		return this.archived;
	}

	/**
	 * Checks if the List of users is empty.
	 * 
	 * @return boolean value
	 */
	public boolean isUsersListEmpty() {
		return this.listUsers.listIsEmpty();
	}

	/**
	 * Prints all the users by the name from the list.
	 */
	public void printMembers() {
		this.getListUsers().printList();
	}

	/**
	 * Checks if the channel has the same name of the parameter.
	 * 
	 * @param name
	 *            channel's name.
	 * @return boolean value.
	 */
	public boolean checkName(final String name) {
		return channelName.equals(name);
	}
}
