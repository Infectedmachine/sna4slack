package slackpack;

import slackpack.Member;
import java.util.ArrayList;

import org.json.simple.JSONObject;

public class ArrayMember {

	ArrayList<Member> members;

	public ArrayMember() {

		setArray(new ArrayList<Member>());
	}

	public ArrayMember(String filedir) {

		WSParser parser = new WSParser(filedir);
		setArray(new ArrayList<Member>());

		for (Object obj : parser.Array()) {
			JSONObject jobj = (JSONObject) obj;
			JSONObject tmp = (JSONObject) jobj.get("profile");
			Member mobj = new Member();
			if (tmp.get("display_name") != null)
				mobj.setDisplayName((String) tmp.get("display_name"));
			else
				mobj.setDisplayName("");
			mobj.setRealName((String) tmp.get("real_name"));
			mobj.setName((String) jobj.get("name"));
			mobj.setID((String) jobj.get("id"));
			if ((boolean) jobj.get("deleted"))
				mobj.setDeleted(true);
			addMember(mobj);

		}
	}

	public void addMember(Member member) {
		getArray().add(member);
	}

	public ArrayList<Member> getArray() {

		return this.members;
	}

	public void setArray(ArrayList<Member> marr) {

		this.members = marr;
	}

	public void printArray() {

		if (getArray().size() > 0) {
			for (Member mobj : getArray()) {
				if (mobj.getDisplayName().length() > 0)
					System.out.println(mobj.getDisplayName());
				else if (mobj.getRealName().length() > 0) {
					if (!mobj.getDeleted())
						System.out.println(mobj.getRealName());
					else
						System.out.println(mobj.getRealName() + "	[DELETED]");
				} else if (!mobj.getDeleted())
					System.out.println(mobj.getName());
				else
					System.out.println(mobj.getName() + "	[DELETED]");
			}
		} else
			System.out.println("NONE MEMBERS");
	}

	public ArrayMember getMembersbyID(ArrayList<String> idarr) {

		ArrayMember convertedarr = new ArrayMember();

		for (String id : idarr) {
			for (Member member : getArray()) {
				if (member.getID().equals(id)) {
					convertedarr.addMember(member);
				}
			}

		}

		return convertedarr;
	}
}
