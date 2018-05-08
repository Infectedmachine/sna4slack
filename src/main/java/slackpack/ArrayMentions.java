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
	public ArrayMentions(String filedir, ArrayMember members) {

		WSParser parser = new WSParser(filedir);
		this.mentions = new ArrayList<Mention>();
		boolean flag = false;

		for (Object obj : parser.Array()) {
			JSONObject jobj = (JSONObject) obj;
			Mention mobj = new Mention();
			String text = (String) jobj.get("text");
			String user = (String) jobj.get("user");
			String sub_type = (String) jobj.get("subtype");

			String tmp;

			if (sub_type == null) {
				Pattern findmention = Pattern.compile("<@.+?>");
				Matcher match = findmention.matcher(text);
				while (match.find()) {
					tmp = match.group().subSequence(2, match.group().length() - 1).toString();
					if (mobj.getFROM().length() == 0) {
						mobj.setFROM(getNameFROM(user, members.getArray()));
						mobj.addTO(getNameFROM(tmp, members.getArray()));
						flag = true;
					} else {
						if (checkDoubles(mobj, tmp) == false) {
							mobj.addTO(getNameFROM(tmp, members.getArray()));
						}
					}
				}
				if (flag == true) {
					if (this.mentions.size() == 0) {
						this.mentions.add(mobj);
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

		if (this.mentions.size() == 0) {
			this.mentions.add(mobj);
		} else {
			for (Object obj : this.mentions) {
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
				this.mentions.add(mobj); // IF NONE OF 'FROM' WAS PRESENT IN THE MAIN MENTION ARRAY THEN WHOLE MENTION
											// OBJ TO IT
			}
		}
	}

	public void setArray(ArrayList<Mention> marr) {

		this.mentions = marr;
	}

	public ArrayList<Mention> getMentions() {

		return this.mentions;
	}

	public void printMentions() {

		if (this.mentions.size() > 0) {
			for (Object obj : this.mentions) {
				Mention mobj = (Mention) obj;
				System.out.println("FROM : " + mobj.getFROM());
				System.out.println("TO : " + mobj.getTO());
				System.out.println("\n");

			}

		}

	}

	public boolean checkDoubles(Mention mention, String value) {

		for (String tmp : mention.getTO()) {
			if (tmp.equals(value))
				return true;

		}

		return false;
	}

	public String getNameFROM(String id, ArrayList<Member> member_array) {
		String nameFROM = new String();
		for (Object e : member_array) {
			Member mobj = (Member) e;
			if (mobj.getID().equals(id))
				nameFROM = mobj.getName();
		}
		return nameFROM;
	}
}
