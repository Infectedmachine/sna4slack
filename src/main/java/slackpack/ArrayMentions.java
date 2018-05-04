package slackpack;
import java.util.ArrayList; 

public class ArrayMentions {
	
	ArrayList<Mention> mentions; 
	
	public ArrayMentions() {
		
		//CONSTRUCTOR
		
	}
	
	public void setArray(ArrayList<Mention> marr) {
		
		this.mentions = marr; 
	}
	
	public ArrayList<Mention> getMentions(){
		
		return this.mentions; 
	}
	
	public void printMentions() {
		
		//SET OF INSTRUCTIONS TO PRINT ARRAY'S CONTENT
	}

}
