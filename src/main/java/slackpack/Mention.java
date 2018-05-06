package slackpack;

import java.util.ArrayList;

public class Mention {

	String FROM;
	ArrayList<String> TO;

	public Mention() {

		FROM = "";
		TO = null;
	}

	public void setFROM(String value) {

		this.FROM = value;
	}

	public void setTO(ArrayList<String> arr) {

		this.TO = arr;
	}

	public String getFROM() {

		return this.FROM;
	}

	public ArrayList<String> getTO() {

		return this.TO;
	}

	public String getNameFROM(String id, ArrayList<Member> member_array) {
		String nameFROM = new String();
		for (Object e : member_array) {
			Member mobj = (Member) e;
			if (mobj.getID().equals(id))
				nameFROM = mobj.getRealName();
		}
		return nameFROM;
	}

	public ArrayList<String> getNameTO(ArrayList<String> ids, ArrayList<Member> members_array) {
		ArrayList<String> namesTO = new ArrayList<String>();
		for (Object obj_i : ids) {
			String mobj_id = (String) obj_i;
			for (Object obj_j : members_array) {
				Member mobj_marr = (Member) obj_j;
				if (mobj_id.equals(mobj_marr.getID()))
					namesTO.add(mobj_marr.getRealName());
			}
		}
		return namesTO;
	}
	
}
