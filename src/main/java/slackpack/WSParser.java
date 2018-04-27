package slackpack;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WSParser {
	
	JSONParser parser; 
	JSONArray jarr; 
	
	public WSParser(String filedir) {
		
		this.parser = new JSONParser();
		try {
			
			this.jarr = (JSONArray) parser.parse(new FileReader(filedir));
			
			} catch (FileNotFoundException e) {
			e.printStackTrace();
			} catch (IOException e) {
			e.printStackTrace();
			} catch (ParseException e) {
			e.printStackTrace();
			}

	}
	
	public JSONArray Array() {
		return this.jarr; 
	}
	
}