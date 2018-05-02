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
				System.out.println("JSON File not found");
			} catch (IOException e) {
				System.out.println("PARSE IOEXCEPTION");
			} catch (ParseException e) {
				System.out.println("PARSE EXCEPTION");
			}

	}
	
	public JSONArray Array() {
		return this.jarr; 
	}	
}