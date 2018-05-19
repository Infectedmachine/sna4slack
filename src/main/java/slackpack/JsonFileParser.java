package slackpack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonFileParser {
	private JSONArray contentsarray;

	public JsonFileParser() {
		this.setArray(new JSONArray());
	}
	public final void fillContentsFromJSONFileDir(String filedir) {

		JSONParser parser = new JSONParser();
		try {
			InputStream inputStream = new FileInputStream(filedir);
			Reader fileReader = new InputStreamReader(inputStream, "UTF-8");
			this.setArray((JSONArray) parser.parse(fileReader));

			} catch (FileNotFoundException e) {
				System.out.println("JSON File not found");
			} catch (IOException e) {
				System.out.println("PARSE IOEXCEPTION");
			} catch (ParseException e) {
				System.out.println("PARSE EXCEPTION");
			}

	}

	public final void setArray(JSONArray jsonarray) {
		this.contentsarray = jsonarray;
	}

	public JSONArray getArray() {
		return this.contentsarray;
	}
}