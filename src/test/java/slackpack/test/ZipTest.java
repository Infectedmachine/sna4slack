/**
 * Test Zip class from slackpack package.
 **/
package slackpack.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import slackpack.Zip;

@Tag("zip")
public class ZipTest {

	private static Zip zip1 = new Zip();
	private static Zip zip2 = new Zip();
	private static File testFile = new File("res/files/test");

	@BeforeAll
	static void setUpAll() {
		zip1.setDirPath("res/files/test.zip");
		zip2.extractZip("res/files/test.zip");
	}

	@AfterEach
	void tearDown() {
		System.out.println("Test zip funciont - Success");
	}

	@Test
	public void getDirPathTest() {
		assertEquals("res/files/test.zip", zip1.getDirPath());
	}

	@Test
	public void extractZipTest() {
		assertTrue(testFile.exists());
	}
}