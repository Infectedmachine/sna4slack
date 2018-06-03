package snaforslack.interfaces.json;

import java.util.List;

/**
 * Interface of the JsonArray.
 **/
public interface IntJsonArray {
	/**
	 * Gets a list of JsonObects.
	 *
	 * @return List<IntJsonObject>
	 **/
	List<IntJsonObject> getList();

	/**
	 * True if it is empty.
	 *
	 * @return Boolean
	 **/
	boolean isEmpty();
}
