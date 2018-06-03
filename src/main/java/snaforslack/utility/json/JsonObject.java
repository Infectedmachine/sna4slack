package snaforslack.utility.json;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;

import snaforslack.interfaces.json.IntJsonObject;

/**
 * Consists in initializing the values taken from a JSON file.
 **/
public class JsonObject implements IntJsonObject {

	/**
	 * Constant string message.
	 **/
	private static final String MESSAGETYPE = "message";
	/**
	 * The id of a channel.
	 **/
	private String objId;
	/**
	 * The name of a channel.
	 **/
	private String name;
	/**
	 * True if the channel is archived.
	 **/
	private boolean archived;
	/**
	 * Contains all the ids of the members.
	 **/
	private List<String> idmembers;
	/**
	 * The id of the message sender.
	 **/
	private String sender;
	/**
	 * Contains all the ids of the receivers of the message.
	 **/
	private List<String> receivers;

	/**
	 * Allocates the values of a JSONObject.
	 *
	 * @param jobj
	 *            JSONObject to be allocated
	 */
	public JsonObject(final JSONObject jobj) {
		this.initializeAll();
		if (jobj != null) {
			if (this.isMessage(jobj)) {
				this.sender = this.getSender(jobj);
				this.receivers = this.getQuotes(jobj);
			} else if (this.isChannel(jobj)) {
				this.objId = this.getId(jobj);
				this.name = this.getName(jobj);
				this.archived = this.getArchived(jobj);
				this.idmembers = this.getMembers(jobj);
			} else {
				this.objId = this.getId(jobj);
				this.name = this.getName(jobj);
			}
		}
	}

	/**
	 * Initializes all the attributes of JsonObject to default.
	 **/
	private void initializeAll() {
		this.objId = "";
		this.name = "";
		this.archived = false;
		this.idmembers = new ArrayList<String>();
		this.sender = "";
		this.receivers = new ArrayList<String>();
	}

	/**
	 * Gets the id inside of the JsonObect.
	 *
	 * @return id Channel's id.
	 **/
	@Override
	public String getId() {
		return this.objId;
	}

	/**
	 * Gets the name inside of the JsonObect.
	 *
	 * @return name Channel's name.
	 **/
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the boolean archived inside of the JsonObect.
	 *
	 * @return archived True if channel is archived.
	 **/
	@Override
	public boolean isArchived() {
		return this.archived;
	}

	/**
	 * Gets the members list inside of the JsonObect.
	 *
	 * @return idmembers List of a channel's members.
	 **/
	@Override
	public List<String> getMembers() {
		return this.idmembers;
	}

	/**
	 * Gets the sender inside of the JsonObect.
	 *
	 * @return sender The sender of the message.
	 **/
	@Override
	public String getSender() {
		return this.sender;
	}

	/**
	 * Gets the list of the receivers inside of the JsonObect.
	 *
	 * @return receivers The list of message's receiver.
	 **/
	@Override
	public List<String> getReceivers() {
		return this.receivers;
	}

	/**
	 * Gets the a list of mentions inside a JSONObject.
	 *
	 * @param jobj
	 *            The JSONObject to be examined.
	 *
	 * @return quotted A list of mentions.
	 **/
	private List<String> getQuotes(final JSONObject jobj) {
		final List<String> quotted = new ArrayList<String>();
		if ((String) jobj.get("subtype") == null) {
			final String text = (String) jobj.get("text");
			final Pattern quotes = Pattern.compile("<@.+?>");
			final Matcher match = this.getMatcherFromPattern(quotes, text);
			while (this.checkFind(match)) {
				final String mentionId = this.getCleanStringFromMatch(match);
				if (!this.checkQuote(mentionId)) {
					quotted.add(mentionId);
				}
			}
		}
		return quotted;
	}

	/**
	 * Returns true if find a pattern.
	 * 
	 * @param match
	 *            a Match value.
	 * @return a boolean value.
	 */
	private boolean checkFind(final Matcher match) {
		return match.find();
	}

	/**
	 * Returns a match object from a Pattern.
	 * 
	 * @param pattern
	 *            Pattern object.
	 * @param text
	 *            text to match.
	 * @return a Matcher object.
	 */
	private Matcher getMatcherFromPattern(final Pattern pattern, final String text) {
		return pattern.matcher(text);
	}

	/**
	 * Returns a String with the id of a user.
	 * 
	 * @param match
	 *            a Match type parameter.
	 * @return a String parameter.
	 */
	private String getCleanStringFromMatch(final Matcher match) {
		return match.group().subSequence(2, match.group().length() - 1).toString();
	}

	/**
	 * Checks if a member mentioned someone.
	 *
	 * @param msgId
	 *            The id of the sender of a message.
	 *
	 * @return True if there are mentions.
	 **/
	private boolean checkQuote(final String msgId) {
		if (this.sender.equals(msgId)) {
			return true;
		} else {
			for (final String receiverId : this.receivers) {
				if (receiverId.equals(msgId)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Gets the list of members in the channel given in input.
	 *
	 * @param channel
	 *            The id of the channel.
	 *
	 * @return list A List of members.
	 **/
	private List<String> getMembers(final JSONObject channel) {
		@SuppressWarnings("unchecked")
		final List<String> list = (List<String>) channel.get("members");
		if (list == null) {
			return Collections.emptyList();
		} else {
			return list;
		}
	}

	/**
	 * Checks if a channel is archived.
	 *
	 * @param jobj
	 *            JSONObject to check the archiviation.
	 *
	 * @return isarchived True if it is.
	 *
	 **/
	private boolean getArchived(final JSONObject jobj) {
		return (boolean) jobj.get("is_archived");
	}

	/**
	 * Gets the id from a JSONObject.
	 *
	 * @param jobj
	 *            JSONObject to get the id.
	 *
	 * @return sid The id of a channel.
	 *
	 **/
	private String getId(final JSONObject jobj) {
		return (String) jobj.get("id");
	}

	/**
	 * Gets the name inside of the JsonObect.
	 *
	 * @param jobj
	 *            JSONObject to get the name.
	 *
	 * @return name Channel's name.
	 **/
	private String getName(final JSONObject jobj) {
		if (isChannel(jobj)) {
			return this.getChannelName(jobj);
		} else {
			final JSONObject userprofile = (JSONObject) jobj.get("profile");
			return this.getUserName(userprofile);
		}
	}

	/**
	 * Gets the name of the sender inside of the JsonObect.
	 *
	 * @param jobj
	 *            JSONObject to get the name.
	 *
	 * @return usersender Name of the sender.
	 **/
	private String getSender(final JSONObject jobj) {
		return (String) jobj.get("user");
	}

	/**
	 * Gets the UserName of the sender inside of the JsonObect.
	 *
	 * @param userprofile
	 *            JSONObject to get the name.
	 *
	 * @return username The username of a member.
	 **/
	private String getUserName(final JSONObject userprofile) {
		final String displayname = (String) userprofile.get("display_name");
		final String realname = (String) userprofile.get("real_name");
		final String username = (String) userprofile.get("name");
		if (this.getSize(displayname) > 0) {
			return displayname;
		} else if (this.getSize(realname) > 0) {
			return realname;
		} else {
			return username;
		}
	}

	/**
	 * Return size of a String.
	 * 
	 * @param string
	 *            a String value.
	 * @return size of string as int type.
	 */
	private int getSize(final String string) {
		return string.length();
	}

	/**
	 * Gets the channel name inside of the JsonObect.
	 *
	 * @param channel
	 *            JSONObject to get the channel name.
	 *
	 * @return channelname The channel name.
	 **/
	private String getChannelName(final JSONObject channel) {
		return (String) channel.get("name");
	}

	/**
	 * Checks if there is a creator inside of the JsonObect.
	 *
	 * @param jobj
	 *            JSONObject to check the creator.
	 *
	 * @return True If there is a channel.
	 **/
	private boolean isChannel(final JSONObject jobj) {
		final String value = (String) jobj.get("creator");
		return value != null;
	}

	/**
	 * Checks if there is a message inside of the JsonObect.
	 *
	 * @param jobj
	 *            JSONObject to check the creator.
	 *
	 * @return True If there is a message.
	 **/
	private boolean isMessage(final JSONObject jobj) {
		final String message = (String) jobj.get("type");
		if (message == null) {
			return false;
		} else {
			return this.checkMessageType(message);
		}
	}

	/**
	 * Check if the string is equals to MESSAGETYPE.
	 * 
	 * @param type
	 *            a String value.
	 * @return boolean value.
	 */
	private boolean checkMessageType(final String type) {
		return type.equals(MESSAGETYPE);
	}
}
