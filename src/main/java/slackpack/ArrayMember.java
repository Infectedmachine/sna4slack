package slackpack;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ArrayMember {
	private ArrayList<Member> members;

	public ArrayMember() {
		this.setArray(new ArrayList<Member>());
	}

	public final void fillArrayFromJSONArray(JSONArray jsonarray) throws Exception {
		if (jsonarray == null)
			throw new Exception("JSON FILE'S CONTENT IS NULL");
		else {
			for (Object obj : jsonarray) {
				JSONObject json = (JSONObject) obj;
				Member member = new Member();
				member.fillMemberFromJSONObject(json);
				this.addMember(member);
			}
		}
	}

	public void addMember(Member member) {
		this.getArray().add(member);
	}

	public ArrayList<Member> getArray() {
		return this.members;
	}

	public final void setArray(ArrayList<Member> members) {
		this.members = members;
	}

	public void printArray() {
		if (this.getArray().size() > 0) {
			for (Member member : this.getArray()) {
				System.out.println(member.getNameByPriority());
			}
		} else
			System.out.println("NONE MEMBERS");
	}

	public ArrayMember extractMembersSelectedByIds(ArrayList<String> idarray) {
		ArrayMember members = new ArrayMember();
		for (String id : idarray)
			if (this.checkMemberById(id))
				members.addMember(this.getMemberById(id));
		return members;
	}

	public Member getMemberById(String id) {
		for (Member member : this.getArray())
			if (member.checkId(id))
				return member;
		return null;
	}

	public boolean checkMemberById(String id) {
		for (Member member : this.getArray()) {
			if (member.checkId(id))
				return true;
		}
		return false;
	}

	public Member getMemberByName(String name) throws Exception {
		for (Member member : this.getArray())
			if (member.getNameByPriority().equals(name))
				return member;
		throw new Exception ("NONE MEMBER BY THIS NAME");
	}
}
