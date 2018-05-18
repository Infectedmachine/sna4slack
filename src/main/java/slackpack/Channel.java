package slackpack;

import org.json.simple.JSONObject; 
import java.util.ArrayList;

public class Channel {

	private String id;
	private String name;
	private String idcreator;
	private boolean archived; 
	private ArrayMember channelmembers;
	private ArrayMember slackmembers; 
	
	public Channel(ArrayMember slackmembers) {

		setID("");
		setName("");
		setIDCreator("");
		setArchived(false); 
		setArray(new ArrayMember());
		setSlackArray(slackmembers); 
	}
	
	@SuppressWarnings("unchecked")
	public final void fillChannelFromJSONObject(JSONObject channels) {
		ArrayList<String> channelsmembersid = (ArrayList<String>) channels.get("members");
		this.setName((String) channels.get("name"));
		this.setID((String) channels.get("id"));
		this.setIDCreator((String) channels.get("creator"));
		this.setArchived((boolean) channels.get("is_archived"));
		this.setArray(this.getSlackArray().extractMembersSelectedByIds(channelsmembersid)); 
		
	}
	
	private void setSlackArray(ArrayMember slackmembers) {
		this.slackmembers = new ArrayMember(); 
		this.slackmembers = slackmembers; 
	}
	
	private ArrayMember getSlackArray() {
		return this.slackmembers; 
	}
	
	public final void setArchived(boolean flag) {
		this.archived = flag; 
	}
	
	public boolean getArchived() {
		return this.archived; 
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final void setID(String id) {
		this.id = id;
	}

	public final void setIDCreator(String idcreator) {
		this.idcreator = idcreator;
	}

	public final void setArray(ArrayMember channelmembers) {
		this.channelmembers = channelmembers;
	}

	public ArrayMember getMembersList() {
		return this.channelmembers;
	}

	public void printMembersList() {
		getMembersList().printArray();
	}

	public String getName() {
		return this.name;
	}

	public String getID() {
		return this.id;
	}

	public String getIDCreator() {
		return this.idcreator;
	}
}
