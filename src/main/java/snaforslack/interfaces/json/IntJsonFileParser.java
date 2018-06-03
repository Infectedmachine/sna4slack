package snaforslack.interfaces.json;

/**
 * Interface of the Pareser.
 **/
public interface IntJsonFileParser {
	/**
	 * Initialize the parsing from files in the directory.
	 *
	 * @param filedir Directory of the JSON files.
	 **/
	void initializeFromJSONFileDir(String filedir);

	/**
	 * Gets a JSON Array.
	 *
	 * @return IntJsonArray
	 **/
	IntJsonArray getArray();
}
