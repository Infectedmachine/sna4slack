package snaforslack.interfaces.zip;

/**
 * Interface of the zip utility.
 **/
public interface IntZip {

	/**
	 * Gets the directory path.
	 *
	 * @return String
	 **/
	String getDirPath();

	/**
	 * Extracts the files.
	 * 
	 * @return an Int value. 1 no errors. -1 an error occured.
	 **/
	int extractZip();
}
