package slackpack;

import java.util.ArrayList;

public class Mention {

	String FROM;
	ArrayList<String> TO;

	public Mention() {

		FROM = "";
		TO = new ArrayList<String>();
	}

	public void setFROM(String value) {

		this.FROM = value;
	}

	public void setTO(ArrayList<String> arr) {

		this.TO = arr;
	}

	public String getFROM() {

		return this.FROM;
	}

	public ArrayList<String> getTO() {

		return this.TO;
	}	
	
	public void addTO(String value) {
		
		this.TO.add(value); 
	}
}
