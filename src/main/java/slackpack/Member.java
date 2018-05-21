package slackpack;

import org.json.simple.JSONObject;

/**
 * Contains and manages all information of a member.
 *
 * @author aleningi
 *
 */
public class Member {

	/**
	 * Member identifier code.
	 */
	private String id;

	/**
	 * Member nickname.
	 */
	private String name;

	/**
	 * Member name.
	 */
	private String realname;

	/**
	 * Member display name.
	 */
	private String displayname;

	/**
	 * Value that idicates if member is deleted.
	 */
	private boolean deleted;

	/**
	 * Allocate all of the space needed by class attributes.
	 */
	public Member() {
		this.setID("");
		this.setName("");
		this.setRealName("");
		this.setDisplayName("");
		this.setDeleted(false);
	}

	/**
	 * Extracts all member informations contained inside JSON object.
	 *
	 * @param user JSON object.
	 */
	public final void fillMemberFromJSONObject(final JSONObject user) {
		JSONObject userprofile = (JSONObject) user.get("profile");
		this.setRealName((String) user.get("real_name"));
		this.setName((String) user.get("name"));
		this.setID((String) user.get("id"));
		if ((boolean) user.get("deleted")) {
			this.setDeleted(true);
		}
		if (userprofile.get("display_name") != null) {
			this.setDisplayName((String) userprofile.get("display_name"));
		}
	}

	/**
	 *
	 * @param newdisplayname is set to class attribute displayname.
	 */
	public final void setDisplayName(final String newdisplayname) {
		this.displayname = newdisplayname;
	}

	/**
	 *
	 * @return class attribute displayname.
	 */
	public String getDisplayName() {
		return this.displayname;
	}

	/**
	 *
	 * @param flag is set to class attribute deleted.
	 */
	public final void setDeleted(final boolean flag) {
		this.deleted = flag;
	}

	/**
	 *
	 * @return class attribute deleted.
	 */
	public boolean getDeleted() {
		return this.deleted;
	}

	/**
	 *
	 * @param rname is set to class attribute realname.
	 */
	public final void setRealName(final String rname) {
		this.realname = rname;
	}

	/**
	 *
	 * @param n is set to class attribute name.
	 */
	public final void setName(final String n) {
		this.name = n;
	}

	/**
	 *
	 * @param newID is set to class attribute id.
	 */
	public final void setID(final String newID) {
		this.id = newID;
	}

	/**
	 *
	 * @return class attribute id.
	 */
	public String getID() {
		return this.id;
	}

	/**
	 *
	 * @return class attribute name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 *
	 * @return class attribute realname.
	 */
	public String getRealName() {
		return this.realname;
	}

	/**
	 *
	 * @param newID to check.
	 * @return boolean value.
	 */
	public boolean checkId(final String newID) {
		return (this.getID().equals(newID));
	}

	/**
	 * Returns a member name based on priority position:
	 * 1. displayname.
	 * 2. realname.
	 * 3. name.
	 * @return Member name.
	 */
	public String getNameByPriority() {
		if (this.getDisplayName().length() > 0) {
			return this.getDisplayName();
		} else {
			if (this.getRealName().length() > 0) {
			return this.getRealName();
			} else {
			return this.getName();
			}
		}
	}
}
