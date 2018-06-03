package snaforslack.utility.json;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import snaforslack.interfaces.json.IntJsonArray;
import snaforslack.interfaces.json.IntJsonObject;

/**
 * This class consists of the attributes and methods to crate a JsonArray. Using
 * org.json.simple API.
 **/
public class JsonArray implements IntJsonArray {
	/**
	 * Contains a list of jsonarray.
	 */
	private final List<IntJsonObject> jArray;

	/**
	 * Creates a JSONArray.
	 *
	 * @param jarray a JSONArray to create.
	 */
	public JsonArray(final JSONArray jarray) {
		this.jArray = new ArrayList<IntJsonObject>();
		if (jarray != null) {
			this.createArray(jarray);
		}
	}

	/**
	 * Initializes a JSONArray.
	 *
	 * @param jarray a JSONArray to initialize.
	 */
	private void createArray(final JSONArray jarray) {
		for (final Object o : jarray) {
			final JSONObject jobj = (JSONObject) o;
			final IntJsonObject json = new JsonObject(jobj);
			this.jArray.add(json);
		}
	}

	/**
	 * Gets a list of json objects.
	 *
	 * @return List<IntJsonObject>
	 */
	@Override
	public List<IntJsonObject> getList() {
		return this.jArray;
	}

	/**
	 * Checks if the array is empty.
	 *
	 * @return Boolean
	 */
	@Override
	public boolean isEmpty() {
		return this.jArray.isEmpty();
	}
}
