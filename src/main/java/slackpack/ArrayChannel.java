package slackpack;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ArrayChannel {

	private ArrayList<Channel> channels;
	private ArrayMember slackmembers;

	public ArrayChannel(ArrayMember slackmembers) {
		this.setArray(new ArrayList<Channel>());
		this.setMembersArray(slackmembers);
	}

	public final void fillArrayFromJSONArray(JSONArray jsonarray) throws Exception {
		if (jsonarray == null)
			throw new Exception("JSON FILE'S CONTENT IS NULL");
		else {
			for (Object obj : jsonarray) {
				JSONObject json = (JSONObject) obj;
				Channel channel = new Channel(this.getSlackArray());
				channel.fillChannelFromJSONObject(json);
				this.addChannel(channel);
			}
		}
	}

	private final void setMembersArray(ArrayMember slackmembers) {
		this.slackmembers = slackmembers;
	}

	private ArrayMember getSlackArray() {
		return this.slackmembers;
	}

	public void addChannel(Channel channel) {
		getArray().add(channel);
	}

	public ArrayList<Channel> getArray() {
		return this.channels;
	}

	public final void setArray(ArrayList<Channel> carr) {
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
		for (String channel : this.getAllChannelsName()) {
			System.out.println(channel);
		}
	}

	public Channel getChannel(String name) throws Exception{
		for (Channel channel : getArray()) {
			if (channel.getName().equals(name))
				return channel;
		}
		throw new Exception ("NONE CHANNEL BY THIS NAME");
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
		for (Channel channel : this.getArray())
			allchannelslist.add(channel.getName());
		return allchannelslist;
	}
}
