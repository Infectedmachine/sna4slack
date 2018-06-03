package snaforslack.obj.global;

import java.util.ArrayList;
import java.util.List;

import snaforslack.data.list.ListChannels;
import snaforslack.data.list.ListUsers;
import snaforslack.data.structures.SlackChannel;
import snaforslack.interfaces.json.IntJsonArray;
import snaforslack.interfaces.json.IntJsonObject;
import snaforslack.interfaces.list.IntListChannels;
import snaforslack.interfaces.list.IntListUsers;
import snaforslack.interfaces.obj.IntObjChannels;
import snaforslack.interfaces.obj.IntObjUsers;
import snaforslack.interfaces.structures.IntChannel;
import snaforslack.interfaces.structures.IntUser;

/**
 * Contains and manages all the channels objects.
 *
 */
public class Channels implements IntObjChannels {
	/**
	 * Array of Channel objects.
	 */
	private final IntListChannels globalChannels;

	/**
	 * Array of User objects.
	 */
	private final IntObjUsers globalUsers;

	/**
	 * Allocates all of the memory needed by the class attributes.
	 * 
	 * @param users
	 *            an Array of Users.
	 */
	public Channels(final IntObjUsers users) {
		this.globalChannels = new ListChannels();
		this.globalUsers = users;
	}

	/**
	 * Initializes the class attributes with the content of the given parameter.
	 * 
	 * @param jarray
	 *            a JSONArray.
	 */
	@Override
	public final void initializeFromJsonArray(final IntJsonArray jarray) {
		if (!jarray.isEmpty()) {
			for (final IntJsonObject json : jarray.getList()) {
				final String channelId = json.getId();
				final String channelName = json.getName();
				final boolean archived = json.isArchived();
				final List<String> idmembers = json.getMembers();

				final IntChannel channel = new SlackChannel(channelId, channelName);
				channel.setArchived(archived);
				final IntListUsers usersList = this.createUsersList(idmembers);
				channel.setListUsers(usersList);
				this.globalChannels.addChannel(channel);
			}
		}
	}

	/**
	 * Creates a UsersList with the content of the given parameter.
	 * 
	 * @param idmembers
	 *            a String List.
	 * @return the UsersList created.
	 */
	private IntListUsers createUsersList(final List<String> idmembers) {
		final IntListUsers usersList = new ListUsers();
		if (idmembers != null) {
			for (final String memberid : idmembers) {
				final IntUser tempUser = this.globalUsers.getUser(memberid);
				if (tempUser != Users.NOTFOUND) {
					usersList.addUser(tempUser);
				}
			}
		}
		return usersList;
	}

	/**
	 * Prints all of the channels.
	 */
	@Override
	public void printList() {
		if (!this.globalChannels.listIsEmpty()) {
			for (final IntChannel channel : this.globalChannels.getChannelsList()) {
				System.out.println(channel.getChannelName());
			}
		}
	}

	/**
	 * Prints all of the channels and for each its users.
	 */
	@Override
	public void printListDetailed() {
		if (!this.globalChannels.listIsEmpty()) {
			for (final IntChannel channel : this.globalChannels.getChannelsList()) {
				System.out.println(channel.getChannelName() + ":\n");
				channel.printMembers();
				System.out.println("\n");
			}
		}
	}

	/**
	 * Prints the users of the specified channel.
	 * 
	 * @param channelName
	 *            a String.
	 */
	@Override
	public void printUsersOfChannel(final String channelName) {
		this.globalChannels.printUsersOfChannels(channelName);
	}

	/**
	 * Controls if the specified channel is present in the class.
	 * 
	 * @return boolean value.
	 */
	@Override
	public boolean checkChannel(final String name) {
		return this.globalChannels.checkChannel(name);
	}

	/**
	 * Returns the channel object that matches the specified channel name.
	 * 
	 * @return a channel object.
	 */
	@Override
	public IntChannel getChannel(final String channelName) {
		return this.globalChannels.getChannel(channelName);
	}

	/**
	 * Returns all channels name.
	 * 
	 * @return a List of Strings.
	 */
	@Override
	public List<String> getAllChannelsName() {
		final List<String> channelsName = new ArrayList<String>();
		for (final IntChannel channel : this.globalChannels.getChannelsList()) {
			channelsName.add(channel.getChannelName());
		}
		return channelsName;
	}
}
