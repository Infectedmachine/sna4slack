package slackpack;
import java.util.ArrayList;
import slackpack.Member; 

public class Channel {
	
	 String id_channel; 
	 String name_channel;
	 String id_creator;
	 ArrayList<Member> members;
	 
	public Channel(){
		
		this.id_channel = ""; 
		this.name_channel = ""; 
		this.id_creator = ""; 
		this.members = null; 
	}
	 
	public void setName(String name) {
		
		this.name_channel = name; 
		
	}
	
	public void setID(String id) {
		
		this.id_channel = id; 
		
	}
	
	
	public void setIDCreator(String id) {
		
		this.id_creator = id; 
	}
	
	public void addMArray(ArrayList<Member> members) {
		
		this.members = members; 
		
	}
	
	public ArrayList<Member> getMembersList(){
		
		return this.members; 
		
	}
	
	public void printMembersList() {
		
	    for( Object member : this.members) {
	    	Member m = (Member) member; 
	    	System.out.println(m.getRealName());
	    }
	}
	
	public String getName() {
		
		return this.name_channel; 
	}
	
	public String getID() {
		
		return this.id_channel; 
		
	}
	
	public String getIDCreator() {
		
		return this.id_creator; 
		
	}
	
	public ArrayList<Member> convertIDtoNAME(ArrayList<String> idarr, ArrayList<Member> marr) {
		
		ArrayList<Member> convertedarr = new ArrayList<Member>();
		
		for (Object obj_i: idarr) {
			
			String mobj_id = (String) obj_i;
			for (Object obj_j: marr) {
				
				Member mobj_marr = (Member) obj_j;
				if(mobj_id.equals(mobj_marr.getID())) {
					convertedarr.add(mobj_marr);
				}
			}
			
		}
		
		return convertedarr; 
	}
	 

}
