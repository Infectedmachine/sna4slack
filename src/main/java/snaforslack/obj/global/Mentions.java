package snaforslack.obj.global;

import java.util.List;

import snaforslack.data.list.ListUsers;
import snaforslack.data.list.ListUsersMentions;
import snaforslack.data.structures.SlackUserMentions;
import snaforslack.interfaces.json.IntJsonArray;
import snaforslack.interfaces.json.IntJsonObject;
import snaforslack.interfaces.list.IntListUserMentions;
import snaforslack.interfaces.list.IntListUsers;
import snaforslack.interfaces.obj.IntObjMentions;
import snaforslack.interfaces.obj.IntObjUsers;
import snaforslack.interfaces.structures.IntChannel;
import snaforslack.interfaces.structures.IntUser;
import snaforslack.interfaces.structures.IntUserMentions;

/**
 * Contains and manages all of the mentions in the workspace.
 */
public class Mentions implements IntObjMentions {
	/**
	 * List of globalUsers.
	 */
	private final IntObjUsers globalUsers;
	/**
	 * List of globalMentions.
	 */
	private final IntListUserMentions globalMentions;
	/**
	 * Indicates if the weight is required.
	 */
	private final boolean weighed;

	/**
	 * Initializes and allocates the space for the class attribute.
	 *
	 * @param users
	 *            a List of user.
	 * @param weight
	 *            a boolean value.
	 */
	public Mentions(final IntObjUsers users, final boolean weight) {
		this.globalUsers = users;

		this.globalMentions = new ListUsersMentions();
		this.weighed = weight;
	}

	@Override
	/**
	 * Fills the class attributes with the needed data.
	 *
	 * @param jarray
	 *            a JSON array.
	 * @param name
	 *            a specified channel name.
	 */
	public final void initializeFromJsonArray(final IntJsonArray jarray, final IntChannel channel) {
		if (jarray != null) {
			for (final IntJsonObject json : jarray.getList()) {
				final String sender = json.getSender();
				final List<String> receivers = json.getReceivers();
				final IntUser user = this.globalUsers.getUser(sender);
				final IntListUsers usersList = new ListUsers();

				for (final String receiver : receivers) {
					if (this.globalUsers.checkUser(receiver) && channel.checkUser(receiver)) {
						usersList.addUser(globalUsers.getUser(receiver));
					}
				}
				if (!usersList.listIsEmpty()) {
					final IntUserMentions userMentions = new SlackUserMentions(user, usersList.getUsersList());
					this.mergeMentions(userMentions);
				}
			}
		}
	}

	/**
	 * Merges two mentions if the are equals.
	 *
	 * @param userMentions
	 *            The mentions to merge.
	 **/
	private void mergeMentions(final IntUserMentions userMentions) {
		if (this.globalMentions.isEmpty()) {
			this.globalMentions.addMentions(userMentions);
		} else {
			if (globalMentions.checkSender(userMentions.getSenderId())) {
				globalMentions.addMissingReceivers(userMentions);
			} else {
				this.globalMentions.addMentions(userMentions);
			}
		}
	}

	@Override
	/**
	 * prints all of the mentions.
	 */
	public void printList() {
		if (this.globalMentions.getMentionsList().isEmpty()) {
			System.out.println("NONE MENTIONS");
		} else {
			for (final IntUserMentions userMentions : this.globalMentions.getMentionsList()) {
				this.printUserMentions(userMentions);
			}
		}
	}

	/**
	 * Prints on video mentions of a certain Sender.
	 * 
	 * @param sender
	 *            a String value, name of a sender (user).
	 */
	public void printSenderMentions(final String sender) {
		if (globalMentions.checkSender(sender)) {
			final IntUserMentions senderMentions = this.globalMentions.getSenderMentions(sender);
			this.printUserMentions(senderMentions);
		} else {
			System.out.println("NONE MENTIONS BY THIS USER");
		}
	}

	/**
	 * Prints on video mentions to a certain User.
	 * 
	 * @param receiver
	 *            a String value, name of a receiver (user).
	 */
	public void printReceiverMentions(final String receiver) {
		final IntUser user = globalUsers.getUser(receiver);
		if (globalMentions.checkReceiver(user)) {
			for (final IntUserMentions userMentions : this.globalMentions.getMentionsList()) {
				if (userMentions.checkReceiver(user)) {
					this.printLine(user, userMentions);
				}
			}
		} else {
			System.out.println("NONE MENTIONS TO THIS USER");
		}
	}

	/**
	 * Prints on video mentions of specific user.
	 * 
	 * @param userMentions
	 *            a IntUserMentions value.
	 */
	private void printUserMentions(final IntUserMentions userMentions) {
		if (userMentions != null) {
			for (final IntUser user : userMentions.getListReceiver()) {
				this.printLine(user, userMentions);
			}
			System.out.println("");
		}
	}

	/**
	 * Prints line FROM: name TO: name on video from UserMentions.
	 * 
	 * @param user
	 *            IntUser value.
	 * @param userMentions
	 *            IntUserMentions value.
	 */
	private void printLine(final IntUser user, final IntUserMentions userMentions) {
		final String senderName = userMentions.getSenderName();
		final String receiverName = user.getUserName();
		if (this.weighed) {
			System.out.println("FROM: " + senderName + " TO: " + receiverName + " WEIGHT: "
					+ userMentions.getWeight(user.getUserId()));
		} else {
			System.out.println("FROM: " + senderName + " TO: " + receiverName);
		}
	}
}
