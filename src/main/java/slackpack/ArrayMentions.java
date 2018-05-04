package slackpack;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject; 

public class ArrayMentions {
	
	ArrayList<Mention> mentions; 
	

	public ArrayMentions(String filedir) {	// CONSTRUCTOR
		WSParser parser = new WSParser(filedir);
		this.mentions = new ArrayList<Mention>();
		for (Object obj : parser.Array()) {
			JSONObject jobj = (JSONObject) obj;
			Mention mobj = new Mention();
			String text = (String) jobj.get("text");
			String user = (String) jobj.get("user");
			String sub_type = (String) jobj.get("subtype");
			
			ArrayList<String> tmp = new ArrayList<String>();
			
			mobj.setFROM(user); //aggiunge l'autore del messaggio
			
			if (sub_type == null) {	// significa che non è un messaggio di invio file
				Pattern findMention = Pattern.compile("<@.+?>");  // trova tutto ciò che c'è tra <@ >
				Matcher matcher = findMention.matcher(text);
				while (matcher.find()) {				
					tmp.add(matcher.group().subSequence(2, matcher.group().length()-1).toString());	
					mobj.setTO(tmp);				
				}
				mentions.add(mobj);
			}
			
			
		}
		
	}
	
	public void setArray(ArrayList<Mention> marr) {
		
		this.mentions = marr; 
	}
	
	public ArrayList<Mention> getMentions(){
		
		return this.mentions; 
	}
	
	public void printMentions() {
		
		for ( Object obj : this.mentions) {
			Mention mobj = (Mention) obj; 
			System.out.println(mobj.getFROM());
			System.out.println(mobj.getTO());
			
		}	
	}
	
	/*
	 * @REMEBER TO DELETE
	public static void main(String[] args) {
		ArrayMentions Arr = new ArrayMentions("C:\\Users\\Davide\\Downloads\\ingsw1718\\general\\2018-04-18.json");
		Arr.printMentions();
	}
	 */

}
