package slackpack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;

/**
 * Contains and manages the mention informations.
 * @author aleningi
 *
 */
public class Mention {

	/**
	 * Contains the ID of the mentions sender.
	 */
	private String from;

	/**
	 * Contains all of the ID of the mentioned users.
	 */
	private ArrayList<String> to;

	/**
	 * Contains all of the ID of the mentioned users associated to the number
	 * of times they have been mentioned.
	 */
	private HashMap<String, Integer> weight;

	/**
	 * Allocates the class Attributes required memory.
	 */
	public Mention() {
		this.setFROM("");
		this.setTO(new ArrayList<String>());
		this.setWeight(new HashMap<String, Integer>());
	}

	/**
	 * Finds and adds to the class attributes all of the mention contained
	 * in the JSON object.
	 * @param json JSON object.
	 */
	public final void fillMentionFromJSONObject(final JSONObject json) {
		if (json.get("subtype") == null) {
			this.setFROM((String) json.get("user"));
			String text = (String) json.get("text");
			Pattern mentionpattern = Pattern.compile("<@.+?>");
			Matcher match = mentionpattern.matcher(text);
			while (match.find()) {
				String idmentioneduser = match.group().subSequence(2, match.group().length() - 1).toString();
				if (!this.checkUser(idmentioneduser)) {
					this.addTO(idmentioneduser);
					this.weight.put(idmentioneduser, 1);
				}
			}
		}
	}

	/**
	 * Sets the class attribute weight.
	 *
	 * @param w  HashMap.
	 */
	private void setWeight(final HashMap<String, Integer> w) {
		this.weight = w;
	}

	/**
	 * Returns the class attribute weight.
	 *
	 * @return weight.
	 */
	public HashMap<String, Integer> getWeight() {
		return this.weight;
	}

	/**
	 * Sets the class attribute from.
	 *
	 * @param value ID of the mention sender.
	 */
	public final void setFROM(final String value) {
		this.from = value;
	}

	/**
	 * Sets the class attribute to.
	 *
	 * @param arr  an array of mentioned users.
	 */
	public final void setTO(final ArrayList<String> arr) {
		this.to = arr;
	}

	/**
	 * Returns class attribute from.
	 *
	 * @return form.
	 */
	public String getFROM() {
		return this.from;
	}

	/**
	 * Returns class attribute to.
	 *
	 * @return to.
	 */
	public ArrayList<String> getTO() {
		return this.to;
	}

	/**
	 * Adds a mentioned user to the class attribute to.
	 *
	 * @param value  a mentioned user.
	 */
	public void addTO(final String value) {
		this.to.add(value);
	}

	/**
	 * Checks if a user is present in the mention informations.
	 *
	 * @param iduser user to check.
	 * @return boolean value.
	 */
	public boolean checkUser(final String iduser) {
		if (this.getFROM().equals(iduser)) {
			return true;
		} else {
			for (String id : this.getTO()) {
				if (id.equals(iduser)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks if class attribute to is empty.
	 *
	 * @return boolean value.
	 */
	public boolean isEmptyTO() {
		return (this.getTO().isEmpty());
	}
}
