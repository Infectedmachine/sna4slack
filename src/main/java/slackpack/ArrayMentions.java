package slackpack;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;

public class ArrayMentions {

	ArrayList<Mention> mentions;
	
	// CONSTRUCTOR
	public ArrayMentions(String filedir, ArrayMember members) { 
		
		WSParser parser = new WSParser(filedir);
		this.mentions = new ArrayList<Mention>();
		
		for (Object obj : parser.Array()) {
			JSONObject jobj = (JSONObject) obj;
			Mention mobj = new Mention();
			String text = (String) jobj.get("text");
			String user = (String) jobj.get("user");
			String sub_type = (String) jobj.get("subtype");
	
			ArrayList<String> tmp = new ArrayList<String>();

			if (sub_type == null) { // It means the message it's not the type "file_share"
				Pattern findMention = Pattern.compile("<@.+?>");
				Matcher matcher = findMention.matcher(text);	// It finds every occurrences of "<@ >"
				while (matcher.find()) {	// true when a mention is found in a text				
					tmp.add(matcher.group().subSequence(2, matcher.group().length() - 1).toString());	// It adds a trimmed string(without "<@" ">") to a tmp list
					mobj.setTO((mobj.getNameTO(tmp,members.getArray())));		
				}
			
				if (!(mobj.getTO()==null)) {			
					mobj.setFROM(mobj.getNameFROM(user,members.getArray()));	// It adds the author of the message if he mentioned someone
					mentions.add(mobj);
				}
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
		
		for (Object obj : this.mentions) {
			Mention mobj = (Mention) obj;
			System.out.println("FROM : " + mobj.getFROM());		
			System.out.println("TO : " + mobj.getTO());
			System.out.println("\n");

		}
	}
	
	public boolean checkDouble() {
			
		return false;
	}


}
