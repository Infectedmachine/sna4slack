package snaforslack.utility.json;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import snaforslack.interfaces.json.IntJsonArray;
import snaforslack.interfaces.json.IntJsonFileParser;

/**
 * This class consists of the attributes and methods to parse a JSON file.
 * Using org.json.simple API.
 **/
public class JsonFileParser implements IntJsonFileParser {
	/**
	 * Private attribute contentsarray, an IntJsonArray that hold the content of a JSON file.
	 **/
	private IntJsonArray contentsarray;

	/**
	 * Public constructor.
	 **/
	public JsonFileParser() {
		this.setArray(new JsonArray(null));
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
	@Override
	public final void initializeFromJSONFileDir(final String filedir) {

		final JSONParser parser = new JSONParser();

		try {
			final InputStream inputStream = new FileInputStream(filedir);
			final Reader fileReader = new InputStreamReader(inputStream, "UTF-8");
			final IntJsonArray jarray = new JsonArray((JSONArray) parser.parse(fileReader));
			this.setArray(jarray);

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
	 * @param jsonarray a IntJsonArray.
	 */
	private void setArray(final IntJsonArray jsonarray) {
		this.contentsarray = jsonarray;
	}

	/**
	 * Returns the class contentsarray.
	 * @return contentsarray a IntJsonArray.
	 */
	@Override
	public IntJsonArray getArray() {
		return this.contentsarray;
	}
}
