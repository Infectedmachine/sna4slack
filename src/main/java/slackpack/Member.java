package slackpack;

public class Member {

	String id;
	String name;
	String realname;
	String displayname;
	boolean deleted;

	public Member() {

		setID("");
		setName("");
		setRealName("");
		setDisplayName("");
		setDeleted(false);

	}

	public void setDisplayName(String name) {
		this.displayname = name;
	}

	public String getDisplayName() {
		return this.displayname;
	}

	public void setDeleted(boolean flag) {
		this.deleted = flag;
	}

	public boolean getDeleted() {

		return this.deleted;

	}

	public void setRealName(String realname) {

		this.realname = realname;

	}

	public void setName(String name) {

		this.name = name;

	}

	public void setID(String id) {

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

	public String getNameByPriority() {
		if (getDisplayName().length() > 0)
			return getDisplayName();
		else if (getRealName().length() > 0)
			return getRealName();
		else
			return getName();
	}

}
