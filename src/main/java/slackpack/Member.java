package slackpack;

public class Member {
	
	 String id_member; 
	 String name_member;
	 String real_name; 
	
	
	public Member(){
		
		this.id_member = ""; 
		this.name_member = ""; 
		this.real_name = ""; 
		
	}
	
	 
	public void setRealName(String realname) {
		
		this.real_name = realname; 
		
	}
	
	public void setName(String name) {
		
		this.name_member = name; 
		
	}
	
	
	public void setID(String id) {
		
		this.id_member = id; 
	}
	
	public String getID() {
		
		return this.id_member; 
	}
		
	public String getName() {
		
		return this.name_member; 
	}
	
	public String getRealName() {
		
		return this.real_name; 
		
	}

}
