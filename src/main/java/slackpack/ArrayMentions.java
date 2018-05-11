package slackpack;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;

public class ArrayMentions {

	ArrayList<Mention> mentions;

	public ArrayMentions() {

		this.mentions = new ArrayList<Mention>();
	}

	// NEW CONSTRUCTOR 2.0
	public ArrayMentions(String filedir, ArrayMember globalmembers, ArrayMember members) {

		WSParser parser = new WSParser(filedir);
		setArray(new ArrayList<Mention>());
		boolean flag = false;

		for (Object obj : parser.Array()) {
			JSONObject jobj = (JSONObject) obj;
			Mention mobj = new Mention();
			String text = (String) jobj.get("text");
			String iduser = (String) jobj.get("user");
			String sub_type = (String) jobj.get("subtype");

			String textmention;

			if (sub_type == null) {
				Pattern findmention = Pattern.compile("<@.+?>");
				Matcher match = findmention.matcher(text);
				while (match.find()) {
					textmention = match.group().subSequence(2, match.group().length() - 1).toString();
					if (members.checkMemberByID(textmention)) {
						if (mobj.getFROM().length() == 0) {
							mobj.setFROM(getNameFROM(iduser, globalmembers.getArray()));
							if (checkDoubles(mobj, getNameFROM(textmention, members.getArray())) == false)
								mobj.addTO(getNameFROM(textmention, members.getArray()));
							flag = true;
						} else {
							if (checkDoubles(mobj, getNameFROM(textmention, members.getArray())) == false) {
								mobj.addTO(getNameFROM(textmention, members.getArray()));
							}
						}
					}
				}
				if (flag == true) {
					if (getArray().size() == 0) {
						getArray().add(mobj);
					} else {
						merge(mobj);
					}
				}
				flag = false;
			}

		}
	}

	public void merge(Mention mobj) {

		boolean flag = false; // FLAG FOR 'TO'
		boolean FROMflag = false; // FLAG FOR 'FROM'

		ArrayList<String> tmparr = new ArrayList<String>();

		if (getArray().size() == 0) {
			getArray().add(mobj);
		} else {
			for (Object obj : getArray()) {
				Mention mention = (Mention) obj;

				if (mention.getFROM().equals(mobj.getFROM()) && FROMflag == false) { // IF BOTH 'FROM' ARE EQUALS THEN
																						// CHECK
																						// FOR 'TO' DOUBLES
					FROMflag = true;
					for (String to_i : mobj.getTO()) { // CHECK IF ANY 'TO' FROM MOBJ IS PRESENT IN THE MAIN MANTION OBJ
														// OF
														// THE ARRAY
						for (String to_j : mention.getTO()) {
							if (to_i.equals(to_j)) {
								flag = true;
							}
						}
						if (flag == false) { // IF IT IS PRESENT THEN IGNORE IT, IF NOT ADD IT TO THE TEMP ARRAY
							tmparr.add(to_i);
						}
						flag = false;
					}
					if (tmparr.size() > 0) {
						for (String s : tmparr) {
							mention.addTO(s); // ADD EVERY NEW 'TO' TO THE MAIN MENTION OBJ
						}
					}
				}
			}
			if (FROMflag == false) {
				getArray().add(mobj); // IF NONE OF 'FROM' WAS PRESENT IN THE MAIN MENTION ARRAY THEN WHOLE MENTION
										// OBJ TO IT
			}
		}
	}

	public void mergeArray(ArrayList<Mention> marr) {

		for (Mention mention : marr) {
			merge(mention);
		}
	}

	public void setArray(ArrayList<Mention> marr) {

		this.mentions = marr;
	}

	public ArrayList<Mention> getArray() {

		return this.mentions;
	}

	public void printMentions() {

		if (getArray().size() > 0) {
			for (Mention mention : getArray()) {
				for (String TO : mention.getTO())
					System.out.println("FROM: " + mention.getFROM() + " TO: " + TO);
				System.out.println("\n");
			}
		}

	}

	public void printMentionsOf(String from) {

		for (Object obj : getArray()) {
			Mention mobj = (Mention) obj;
			if (mobj.getFROM().equals(from)) {
				for (Object to : mobj.getTO()) {
					String TO = (String) to;
					System.out.println("FROM: " + from + " TO: " + TO);
				}
			}
		}
	}

	public boolean checkUser(String user) {

		for (Mention mention : getArray()) {
			if (mention.getFROM().equals(user))
				return true;
		}
		return false;
	}

	public boolean checkDoubles(Mention mention, String value) {

		if (mention.getFROM().equals(value))
			return true;
		else {
			for (String tmp : mention.getTO()) {
				if (tmp.equals(value))
					return true;
			}

		}

		return false;
	}

	public String getNameFROM(String id, ArrayList<Member> member_array) {
		String nameFROM = new String();
		for (Object e : member_array) {
			Member mobj = (Member) e;
			if (mobj.getID().equals(id))
				nameFROM = mobj.getNameByPriority();
		}
		return nameFROM;
	}
}
