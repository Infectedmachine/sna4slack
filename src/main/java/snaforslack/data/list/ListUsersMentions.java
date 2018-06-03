package snaforslack.data.list;

import java.util.ArrayList;
import java.util.List;

import snaforslack.data.structures.SlackUserMentions;
import snaforslack.interfaces.list.IntListUserMentions;
import snaforslack.interfaces.structures.IntUserMentions;
import snaforslack.interfaces.structures.IntUser;

/**
 * List Mentions class for a List of mentions.
 **/
public class ListUsersMentions implements IntListUserMentions {
	/**
	 * Constant NOTFOUND as a default UserMention.
	 **/
	private static final IntUserMentions NOTFOUND = new SlackUserMentions(null, null);
	/**
	 * List of mentions.
	 **/
	private final List<IntUserMentions> mentionsList;

	/**
	 * Public constructor.
	 **/
	public ListUsersMentions() {
		this.mentionsList = new ArrayList<IntUserMentions>();
	}

	/**
	 * Gets the list of mentions.
	 *
	 * @return List<IntUserMentions>
	 **/
	@Override
	public List<IntUserMentions> getMentionsList() {
		return this.mentionsList;
	}

	/**
	 * Adds a mention given in input.
	 *
	 * @param mentions
	 *            The mentions to add.
	 **/
	public void addMentions(final IntUserMentions mentions) {
		this.mentionsList.add(mentions);
	}

	/**
	 * Returns true is the list is empty.
	 * 
	 * @return boolean value.
	 */
	public boolean isEmpty() {
		return this.mentionsList.isEmpty();
	}

	/**
	 * Checks if the users mentioned someone.
	 *
	 * @param value
	 *            String
	 *
	 * @return Boolean
	 **/
	@Override
	public boolean checkSender(final String value) {
		final IntUserMentions mentions = this.getSenderMentions(value);
		return mentions != ListUsersMentions.NOTFOUND;
	}

	/**
	 * Gets the mentions from a User.
	 *
	 * @param value
	 *            String
	 *
	 * @return IntUserMentions
	 **/
	@Override
	public IntUserMentions getSenderMentions(final String value) {
		if (value == null || value.isEmpty()) {
			return ListUsersMentions.NOTFOUND;
		} else {
			final List<IntUserMentions> list = this.getMentionsList();
			for (final IntUserMentions mentions : list) {
				if (mentions.checkSender(value)) {
					return mentions;
				}
			}
			return ListUsersMentions.NOTFOUND;
		}
	}

	/**
	 * Checks if the User is ever mentioned.
	 * 
	 * @param user
	 *            a IntUser value.
	 * @return a boolean value.
	 */
	public boolean checkReceiver(final IntUser user) {
		if (user == null) {
			return false;
		} else {
			final List<IntUserMentions> list = this.getMentionsList();
			for (final IntUserMentions mentions : list) {
				if (mentions.checkReceiver(user)) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * Checks for sender and adds missed users to the receivers list.
	 * 
	 * @param userMentions
	 *            sender's mentions object
	 */
	public void addMissingReceivers(final IntUserMentions userMentions) {
		if (userMentions != null) {
			final String senderId = userMentions.getSenderId();
			final List<IntUserMentions> list = this.getMentionsList();

			for (final IntUserMentions mentions : list) {
				if (mentions.checkSender(senderId)) {
					mentions.addMissingReceivers(userMentions.getListReceiver());
				}
			}
		}
	}
}
