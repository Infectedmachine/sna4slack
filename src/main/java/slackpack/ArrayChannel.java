package slackpack;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * This class consists of a list of Channel and one ArrayMember.
 **/
public class ArrayChannel {
	/**
	 * Private attribute a list of channels.
	 **/
	private ArrayList<Channel> channels;
	/**
	 * Private attribute a list of channels.
	 **/
	private ArrayMember slackmembers;

	/**
	 * Public constructor.
	 *
	 * @param slackmemb
	 *            the ArrayMember to be set
	 **/
	public ArrayChannel(final ArrayMember slackmemb) {
		this.setArray(new ArrayList<Channel>());
		this.setMembersArray(slackmemb);
	}

	/**
	 * This method fills a JSONArray with channels.
	 *
	 * @param jsonarray
	 *            to be set
	 *
	 * @throws Exception
	 *             when the JSON file is empty
	 **/
	public final void fillArrayFromJSONArray(final JSONArray jsonarray) throws Exception {
		if (jsonarray == null) {
			throw new Exception("JSON FILE'S CONTENT IS NULL");
		} else {
			for (Object obj : jsonarray) {
				JSONObject json = (JSONObject) obj;
				Channel channel = new Channel(this.getSlackArray());
				channel.fillChannelFromJSONObject(json);
				this.addChannel(channel);
			}
		}
	}

	/**
	 * Private method to set slackmemb.
	 *
	 * @param slackmemb
	 *            The ArrayMember to be set
	 **/
	private void setMembersArray(final ArrayMember slackmemb) {
		this.slackmembers = slackmemb;
	}

	/**
	 * Private method to get slackmembers.
	 *
	 * @return slackmembers the members of a channel
	 **/
	private ArrayMember getSlackArray() {
		return this.slackmembers;
	}

	/**
	 * It adds a channel to the list of channels.
	 *
	 * @param channel
	 *            the channel to be added
	 **/
	public void addChannel(final Channel channel) {
		getArray().add(channel);
	}

	/**
	 * It gets the list of channels.
	 *
	 * @return channels a list of channels.
	 **/
	public ArrayList<Channel> getArray() {
		return this.channels;
	}

	/**
	 * It sets carr as the list of channels.
	 *
	 * @param carr
	 *            the list of channels
	 **/
	public final void setArray(final ArrayList<Channel> carr) {
		this.channels = carr;
	}

	/**
	 * It prints all the names of the channels and their members. If the channels is
	 * archived it's shown near the name.
	 **/
	public void printArray() {
		for (Channel channel : getArray()) {
			if (channel.getArchived()) {
				System.out.println(channel.getName() + " [ARCHIVED] :\n");
			} else {
				System.out.println(channel.getName() + ":\n");
			}
			channel.printMembersList();
			System.out.println("\n");
		}
	}

	/**
	 * It prints all channels' names.
	 **/
	public void printChannels() {
		for (String channel : this.getAllChannelsName()) {
			System.out.println(channel);
		}
	}

	/**
	 * It gets the channel id given the name in input.
	 *
	 * @param name
	 *            the name of the channel to be found.
	 *
	 * @return channel the id of the channel.
	 *
	 * @throws Exception
	 *             when the channel doesn't exist.
	 **/
	public Channel getChannel(final String name) throws Exception {
		for (Channel channel : getArray()) {
			if (channel.getName().equals(name)) {
				return channel;
			}
		}
		throw new Exception("NONE CHANNEL BY THIS NAME");
	}

	/**
	 * It checks if a channel is present.
	 *
	 * @param name
	 *            of the channel to check.
	 *
	 * @return true if it is present. false if it is not.
	 *
	 **/
	public boolean checkChannel(final String name) {
		for (Channel channel : getArray()) {
			if (channel.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * It gets a list of all channels by their name.
	 *
	 * @return allchannelslist a list with all channels' names
	 **/
	public ArrayList<String> getAllChannelsName() {
		ArrayList<String> allchannelslist = new ArrayList<String>();
		for (Channel channel : this.getArray()) {
			allchannelslist.add(channel.getName());
		}
		return allchannelslist;
	}
}

