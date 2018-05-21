package slackpack;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Contains and manages an array of all members of a specified workspace or channel.
 * @author aleningi
 *
 */
public class ArrayMember {

	/**
	 * Contains all members object.
	 */
	private ArrayList<Member> members;

	/**
	 * Allocates necessary space for class attribute.
	 */
	public ArrayMember() {
		this.setArray(new ArrayList<Member>());
	}

	/**
	 * Fills class attribute with all members contained inside JSONArray object.
	 *
	 * @param jsonarray JSONArray.
	 * @throws Exception standard exception.
	 */
	public final void fillArrayFromJSONArray(final JSONArray jsonarray) throws Exception {
		if (jsonarray == null) {
			throw new Exception("JSON FILE'S CONTENT IS NULL");
		} else {
			for (Object obj : jsonarray) {
				JSONObject json = (JSONObject) obj;
				Member member = new Member();
				member.fillMemberFromJSONObject(json);
				this.addMember(member);
			}
		}
	}

	/**
	 * Adds a member object to the class attribute.
	 *
	 * @param member Member object.
	 */
	public void addMember(final Member member) {
		this.getArray().add(member);
	}

	/**
	 * Retrurns class attribute.
	 *
	 * @return members.
	 */
	public ArrayList<Member> getArray() {
		return this.members;
	}

	/**
	 * Sets class attribute to the given parameter.
	 *
	 * @param marr Array of Members object.
	 */
	public final void setArray(final ArrayList<Member> marr) {
		this.members = marr;
	}

	/**
	 * Prints content of class attribute.
	 */
	public void printArray() {
		if (this.getArray().size() > 0) {
			for (Member member : this.getArray()) {
				System.out.println(member.getNameByPriority());
			}
		} else {
			System.out.println("NONE MEMBERS");
			}
	}

	/**
	 * Given an array of IDs returns an array containing all the members
	 * with the same IDs of the class attribute.
	 *
	 * @param idarray array containing IDs.
	 * @return marr.
	 */
	public ArrayMember extractMembersSelectedByIds(final ArrayList<String> idarray) {
		ArrayMember marr = new ArrayMember();
		for (String id : idarray) {
			if (this.checkMemberById(id)) {
				marr.addMember(this.getMemberById(id));
				}
			}
		return marr;
	}

	/**
	 * Returns the member that matches the given ID.
	 *
	 * @param id ID to look for.
	 * @return member.
	 */
	public Member getMemberById(final String id) {
		for (Member member : this.getArray()) {
			if (member.checkId(id)) {
				return member;
			}
		}
		return null;
	}

	/**
	 * Checks if there is a member that matches the given ID in the class attribute.
	 *
	 * @param id ID to look for.
	 * @return boolean value.
	 */
	public boolean checkMemberById(final String id) {
		for (Member member : this.getArray()) {
			if (member.checkId(id)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Retruns the member that matches the given name.
	 *
	 * @param name name to look for,
	 * @return member.
	 * @throws Exception standard exception.
	 */
	public Member getMemberByName(final String name) throws Exception {
		for (Member member : this.getArray()) {
			if (member.getNameByPriority().equals(name)) {
				return member;
			}
		}
		throw new Exception("NONE MEMBER BY THIS NAME");
	}
}
