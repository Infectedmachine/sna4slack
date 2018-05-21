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

/**
 * This class consists of the attributes and methods to parse a JSON file.
 * Using org.json.simple API.
 **/
public class JsonFileParser {
	/**
	 * Private attribute contentsarray, an JSONArray that hold the content of a JSON file.
	 **/
	private JSONArray contentsarray;

	/**
	 * Public constructor.
	 **/
	public JsonFileParser() {
		this.setArray(new JSONArray());
	}

	/**
	 * This method opens and parse JSON file in the path given in input.
	 *
	 * @param filedir the path of a JSON file.
	 *
	 * @exception FileNotFoundException
	 * @exception IOException
	 * @exception ParseException
	 **/
	public final void fillContentsFromJSONFileDir(final String filedir) {

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

	/**
	 * Sets the class contentsarray to the specified parameter.
	 * @param jsonarray a JSONArray.
	 */
	public final void setArray(final JSONArray jsonarray) {
		this.contentsarray = jsonarray;
	}

	/**
	 * Returns the class contentsarray.
	 * @return contentsarray a jsonarray.
	 */
	public JSONArray getArray() {
		return this.contentsarray;
	}
}
