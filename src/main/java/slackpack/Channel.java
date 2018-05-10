package slackpack;

public class Channel {

	String id;
	String name;
	String idcreator;
	boolean archived; 
	ArrayMember members;

	public Channel() {

		setID("");
		setName("");
		setIDCreator("");
		setArchived(false); 
		setArray(null);
	}
	
	public void setArchived(boolean flag) {
		this.archived = flag; 
	}
	
	public boolean getArchived() {
		return this.archived; 
	}

	public void setName(String name) {

		this.name = name;

	}

	public void setID(String id) {

		this.id = id;

	}

	public void setIDCreator(String id) {

		this.idcreator = id;
	}

	public void setArray(ArrayMember members) {

		this.members = members;

	}

	public ArrayMember getMembersList() {

		return this.members;

	}

	public void printMembersList() {

		getMembersList().printArray();
	}

	public String getName() {

		return this.name;
	}

	public String getID() {

		return this.id;

	}

	public String getIDCreator() {

		return this.idcreator;

	}
}
