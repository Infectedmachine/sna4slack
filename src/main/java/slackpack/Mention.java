package slackpack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;

public class Mention {
	private String FROM;
	private ArrayList<String> TO;
	private HashMap<String, Integer> weight;

	public Mention() {
		this.setFROM("");
		this.setTO(new ArrayList<String>());
		this.setWeight(new HashMap<String, Integer>());
	}

	public final void fillMentionFromJSONObject(JSONObject json) {
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

	private final void setWeight(HashMap<String, Integer> weight) {
		this.weight = weight;
	}
	public HashMap<String, Integer> getWeight() {
		return this.weight;
	}

	public final void setFROM(String value) {
		this.FROM = value;
	}

	public final void setTO(ArrayList<String> arr) {
		this.TO = arr;
	}

	public String getFROM() {
		return this.FROM;
	}

	public ArrayList<String> getTO() {
		return this.TO;
	}

	public void addTO(String value) {
		this.TO.add(value);
	}

	public boolean checkUser(String iduser) {
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

	public boolean isEmptyTO() {
		return (this.getTO().size() == 0 ? true : false);
	}
}
