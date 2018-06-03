package snaforslack.data.structures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import snaforslack.interfaces.structures.IntUser;
import snaforslack.interfaces.structures.IntUserMentions;

/**
 * Contain an manages a Slack mention informations.
 */
public class SlackUserMentions implements IntUserMentions {

	/**
	 * Indicates the default weight of a mention.
	 */
	private static final int DEFAULTWEIGHT = 1;
	/**
	 * The mention sender.
	 */
	private final IntUser sender;

	/**
	 * The mention receiver.
	 */
	private List<IntUser> listReceiver;

	/**
	 * Contains the weights of the mentions for each receiver.
	 */
	private Map<String, Integer> mentionWeightMap;

	/**
	 * Allocates and initializes the space for the class attributes.
	 *
	 * @param from
	 *            sets the sender.
	 * @param receivers
	 *            sets the listReceiver.
	 */
	public SlackUserMentions(final IntUser from, final List<IntUser> receivers) {
		this.sender = from;
		this.listReceiver = receivers;
		this.fillMapWithDefaultWeight();
	}

	/**
	 * Initializes the mentionWeightMap to default values.
	 */
	private void fillMapWithDefaultWeight() {
		final List<IntUser> listreceiver = this.listReceiver;
		final Map<String, Integer> map = new HashMap<String, Integer>();
		if (listreceiver != null) {
			for (final IntUser receiver : listreceiver) {
				map.put(receiver.getUserId(), DEFAULTWEIGHT);
			}
		}
		this.mentionWeightMap = map;
	}

	/**
	 * @return the class attribute sender.
	 */
	@Override
	public IntUser getSender() {
		return this.sender;
	}

	/**
	 * @return a ListUsers object containing all of the mentioned users.
	 */
	@Override
	public List<IntUser> getListReceiver() {
		if (this.listReceiver == null) {
			this.listReceiver = new ArrayList<IntUser>();
			return this.listReceiver;
		} else {
			return this.listReceiver;
		}
	}

	/**
	 * Adds a User to the Receiver's List.
	 *
	 * @param receiver
	 *            user to add to the list.
	 */
	private void addReceiver(final IntUser receiver) {
		this.listReceiver.add(receiver);
	}

	/**
	 * @return a Map containing the mentioned users an the mentions weights.
	 */
	@Override
	public Map<String, Integer> getMentionWeightMap() {
		return this.mentionWeightMap;
	}

	/**
	 * @param userId
	 *            id of a user
	 * @return int value weight of the mention
	 */
	@Override
	public int getWeight(final String userId) {
		return this.mentionWeightMap.get(userId);
	}

	/**
	 * @return a String containing the id of the sender user.
	 */
	@Override
	public String getSenderId() {
		return this.sender.getUserId();
	}

	/**
	 *
	 * @return a String containing the name of the sender user.
	 */
	@Override
	public String getSenderName() {
		return this.sender.getUserName();
	}

	/**
	 * @param value
	 *            value to compare in User with id or name.
	 * @return boolean value.
	 */
	@Override
	public boolean checkSender(final String value) {
		return sender.checkUser(value);
	}

	/**
	 * @param toCheck
	 *            user to check in Receivers List.
	 * @return boolean value.
	 */
	@Override
	public boolean checkReceiver(final IntUser toCheck) {
		if (toCheck != null) {
			for (final IntUser user : this.listReceiver) {
				if (user.equals(toCheck)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Adds a new user to the receiver list if it's missing.
	 *
	 * @param list
	 *            list of users to check inside receiver's list
	 */
	@Override
	public void addMissingReceivers(final List<IntUser> list) {
		for (final IntUser user : list) {
			if (this.checkReceiver(user)) {
				final Integer value = this.getMentionWeightMap().get(user.getUserId());
				this.mentionWeightMap.put(user.getUserId(), value + 1);
			} else {
				this.addReceiver(user);
				this.mentionWeightMap.put(user.getUserId(), DEFAULTWEIGHT);
			}
		}
	}
}
