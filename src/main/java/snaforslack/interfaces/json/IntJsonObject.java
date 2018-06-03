package snaforslack.interfaces.json;

import java.util.List;

/**
 * Interface of the JsonObject.
 **/
public interface IntJsonObject {
	/**
	 * Gets the channel id.
	 *
	 * @return String
	 **/
	String getId();

	/**
	 * Gets the channel name.
	 *
	 * @return String
	 **/
	String getName();

	/**
	 * True if a channel is archived.
	 *
	 * @return Boolean
	 **/
	boolean isArchived();

	/**
	 * Gets the list of members of a channel.
	 *
	 * @return List<String>
	 **/
	List<String> getMembers();

	/**
	 * Gets the sender of a mention.
	 *
	 * @return String
	 **/
	String getSender();

	/**
	 * Gets a list of mentioned users.
	 *
	 * @return List<String>
	 **/
	List<String> getReceivers();
}

