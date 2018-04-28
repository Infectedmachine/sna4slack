package slackpack;

import java.util.ArrayList;

import org.json.simple.JSONObject;


public class ArrayChannel {
ArrayList<Channel> channels; 
	
	public ArrayChannel() {
		
		this.channels = null; 
	}
	
	public ArrayChannel(String filedir, ArrayMember marr) {
		
		WSParser parser = new WSParser(filedir);
		this.channels = new ArrayList<Channel>(); 
		
		for (Object obj : parser.Array()) {
			JSONObject jobj = (JSONObject) obj;
			Channel cobj = new Channel();  
			cobj.setName((String) jobj.get("name"));
			cobj.setID((String)jobj.get("id"));
			cobj.setIDCreator((String)jobj.get("creator"));
			ArrayList<String> idmembers = (ArrayList<String>) jobj.get("members");
			cobj.addMArray(cobj.convertIDtoNAME(idmembers, marr.getArray()));
			channels.add(cobj);
		}
	}
	
	public ArrayList<Channel> getArray() {
		
		return this.channels; 
	}
	
	public void setArray(ArrayList<Channel> carr) {
		
		this.channels = carr; 
	}
	
	public void printArray() {
		
		for (Object obj : this.channels) {
			Channel cobj = (Channel) obj; 
			System.out.println(cobj.getName()); 
			System.out.println(cobj.getIDCreator()); 
			System.out.println(cobj.getID());
			cobj.printMembersList();
			System.out.println("\n");
		}
	}
	

}