package slackpack;

import java.util.ArrayList;

import org.json.simple.JSONObject;

/**
 * The class Channels consists of all the attributes of a channel.
 **/
public class Channel {

	/**
	 * Private attribute id of a channel.
	 **/
	private String id;
	/**
	 * Private attribute name of a channel.
	 **/
	private String name;
	/**
	 * Private attribute the id of the channel's creator.
	 **/
	private String idcreator;
	/**
	 * Private boolean attribute about archive status.
	 **/
	private boolean archived;
	/**
	 * Private attribute an ArrayMember of members per channel.
	 **/
	private ArrayMember channelmembers;
	/**
	 * Private attribute an ArrayMember of workspace members.
	 **/
	private ArrayMember slackmembers;

	/**
	 * Public constructor.
	 *
	 * @param slackmemb
	 *            to be set as the list of members per channel.
	 **/
	public Channel(final ArrayMember slackmemb) {

		setID("");
		setName("");
		setIDCreator("");
		setArchived(false);
		setArray(new ArrayMember());
		setSlackArray(slackmemb);
	}

	/**
	 * This method gets the channel information from the JSON file. The suppress
	 * warning is to cast safely from obj to list.
	 *
	 * @param channels
	 *            the JSON object created from parsing the file.
	 *
	 **/
	@SuppressWarnings("unchecked")
	public final void fillChannelFromJSONObject(final JSONObject channels) {
		ArrayList<String> channelsmembersid = (ArrayList<String>) channels.get("members");
		this.setName((String) channels.get("name"));
		this.setID((String) channels.get("id"));
		this.setIDCreator((String) channels.get("creator"));
		this.setArchived((boolean) channels.get("is_archived"));
		this.setArray(this.getSlackArray().extractMembersSelectedByIds(channelsmembersid));

	}

	/**
	 * Private method to set slackmemb.
	 *
	 * @param slackmemb
	 *            The ArrayMember to be set
	 **/
	private void setSlackArray(final ArrayMember slackmemb) {
		this.slackmembers = slackmemb;
	}

	/**
	 * Private method to get channel's members.
	 *
	 * @return An ArrayMember of the channel.
	 *
	 **/
	private ArrayMember getSlackArray() {
		return this.slackmembers;
	}

	/**
	 * Public method to set the boolean archived.
	 *
	 * @param flag
	 *            True if it is archived.
	 **/
	public final void setArchived(final boolean flag) {
		this.archived = flag;
	}

	/**
	 * Public method to get channel's archived status.
	 *
	 * @return boolean of channel's archived status.
	 *
	 **/
	public boolean getArchived() {
		return this.archived;
	}

	/**
	 * Public method to set name of a channel.
	 *
	 * @param n
	 *            the name to be set.
	 *
	 **/
	public final void setName(final String n) {
		this.name = n;
	}

	/**
	 * Public method to set id.
	 *
	 * @param code
	 *            the id to be set.
	 *
	 **/
	public final void setID(final String code) {
		this.id = code;
	}

	/**
	 * Public method to set idcreator.
	 *
	 * @param codecreator
	 *            the idcreator to be set.
	 *
	 **/
	public final void setIDCreator(final String codecreator) {
		this.idcreator = codecreator;
	}

	/**
	 * Public method to set channelmembers.
	 *
	 * @param channelmemb
	 *            the ArrayMember to be set.
	 *
	 **/
	public final void setArray(final ArrayMember channelmemb) {
		this.channelmembers = channelmemb;
	}

	/**
	 * Public method to get the list of members.
	 *
	 * @return the the list of members.
	 *
	 **/
	public ArrayMember getMembersList() {
		return this.channelmembers;
	}

	/**
	 * Public method to print the list of members.
	 *
	 **/
	public void printMembersList() {
		getMembersList().printArray();
	}

	/**
	 * Public method to get the channel's name.
	 *
	 * @return name channel's name
	 *
	 **/
	public String getName() {
		return this.name;
	}

	/**
	 * Public method to get id.
	 *
	 * @return the id of channel.
	 *
	 **/
	public String getID() {
		return this.id;
	}

	/**
	 * Public method to get idcreator.
	 *
	 * @return the id of the channel's creator.
	 *
	 **/
	public String getIDCreator() {
		return this.idcreator;
	}
}

