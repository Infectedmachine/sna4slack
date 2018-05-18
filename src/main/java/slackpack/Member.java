package slackpack;

import org.json.simple.JSONObject;

public class Member {
	private String id;
	private String name;
	private String realname;
	private String displayname;
	private boolean deleted;

	public Member() {
		this.setID("");
		this.setName("");
		this.setRealName("");
		this.setDisplayName("");
		this.setDeleted(false);
	}

	public final void fillMemberFromJSONObject(JSONObject user) {
		JSONObject userprofile = (JSONObject) user.get("profile"); 
		this.setRealName((String) user.get("real_name"));
		this.setName((String) user.get("name"));
		this.setID((String) user.get("id"));
		if ((boolean) user.get("deleted"))
			this.setDeleted(true);
		if (userprofile.get("display_name") != null)
			this.setDisplayName((String) userprofile.get("display_name"));
	}

	public final void setDisplayName(String displayname) {
		this.displayname = displayname;
	}

	public String getDisplayName() {
		return this.displayname;
	}

	public final void setDeleted(boolean flag) {
		this.deleted = flag;
	}

	public boolean getDeleted() {
		return this.deleted;
	}

	public final void setRealName(String realname) {
		this.realname = realname;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final void setID(String id) {
		this.id = id;
	}

	public String getID() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getRealName() {
		return this.realname;
	}
	
	public boolean checkId(String id) {
		return (this.getID().equals(id) ? true : false); 
	}

	public String getNameByPriority() {
		if (this.getDisplayName().length() > 0)
			return this.getDisplayName();
		else if (this.getRealName().length() > 0)
			return this.getRealName();
		else
			return this.getName();
	}
}
