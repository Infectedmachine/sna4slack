package slackpack;

import java.util.ArrayList;

import org.json.simple.JSONObject;

public class ArrayChannel {

	ArrayList<Channel> channels;

	public ArrayChannel(String filedir) {

		WSParser parser = new WSParser(filedir);
		setArray(new ArrayList<Channel>());
		for (Object obj : parser.Array()) {
			JSONObject jobj = (JSONObject) obj;
			Channel cobj = new Channel();
			cobj.setName((String) jobj.get("name"));
			cobj.setID((String) jobj.get("id"));
			cobj.setIDCreator((String) jobj.get("creator"));
			cobj.setArchived((boolean) jobj.get("is_archived"));
			addChannel(cobj);
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayChannel(String filedir, ArrayMember marr) {

		WSParser parser = new WSParser(filedir);
		setArray(new ArrayList<Channel>());
		for (Object obj : parser.Array()) {
			JSONObject jobj = (JSONObject) obj;
			Channel cobj = new Channel();
			cobj.setName((String) jobj.get("name"));
			cobj.setID((String) jobj.get("id"));
			cobj.setIDCreator((String) jobj.get("creator"));
			cobj.setArchived((boolean) jobj.get("is_archived"));
			ArrayList<String> idmembers = (ArrayList<String>) jobj.get("members");
			cobj.setArray(marr.getMembersbyID(idmembers));
			addChannel(cobj);
		}
	}

	public void addChannel(Channel channel) {
		getArray().add(channel);
	}

	public ArrayList<Channel> getArray() {

		return this.channels;
	}

	public void setArray(ArrayList<Channel> carr) {

		this.channels = carr;
	}

	public void printArray() {

		for (Channel channel : getArray()) {
			if (channel.getArchived())
				System.out.println(channel.getName() + " [ARCHIVED] :\n");
			else
				System.out.println(channel.getName() + ":\n");
			channel.printMembersList();
			System.out.println("\n");
		}
	}

	public void printChannels() {

		for (Channel channel : getArray()) {
			System.out.println(channel.getName());

		}
	}

	public Channel getChannel(String name) {

		for (Channel channel : getArray()) {
			if (channel.getName().equals(name))
				return channel;
		}
		return null;
	}
	
	public boolean checkChannel(String name) {
		
		for (Channel channel : getArray()) {
			if(channel.getName().equals(name))
				return true; 
		}
		return false; 
	}
	
	public ArrayList<String> getAllChannelsName(){
		ArrayList<String> allchannelslist = new ArrayList<String>();
		for (Channel channel : getArray()) 
			allchannelslist.add(channel.getName()); 
		return allchannelslist; 
	}

}
