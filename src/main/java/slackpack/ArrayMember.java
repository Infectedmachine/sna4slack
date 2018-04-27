package slackpack;
<<<<<<< HEAD
import slackpack.Member;
=======

>>>>>>> 1ab2b2beda9e2dc85c37aa57f8a4919f09eff1c7
import java.util.ArrayList;

import org.json.simple.JSONObject;

<<<<<<< HEAD
import slackpack.WSParser;
=======
>>>>>>> 1ab2b2beda9e2dc85c37aa57f8a4919f09eff1c7

public class ArrayMember {
	
	ArrayList<Member> members; 
	
	public ArrayMember() {
		
		this.members = null; 
	}
	
	public ArrayMember(String filedir) {
		
		WSParser parser = new WSParser(filedir);
		this.members = new ArrayList<Member>(); 
		
		for (Object obj : parser.Array()) {
			JSONObject jobj = (JSONObject) obj;
			Member mobj = new Member();  
			mobj.setRealName((String) jobj.get("real_name"));
			mobj.setName((String) jobj.get("name"));
			mobj.setID((String)jobj.get("id"));
			members.add(mobj);
		}
	}
	
	public ArrayList<Member> getArray() {
		
		return this.members; 
	}
	
	public void setArray(ArrayList<Member> marr) {
		
		this.members = marr; 
	}
	
	public void printArray() {
		
		for (Object obj : this.members) {
			Member mobj = (Member) obj; 
			System.out.println(mobj.getRealName()); 
			System.out.println(mobj.getName()); 
			System.out.println(mobj.getID());
		}
	}
	

}
