package slackpack.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import slackpack.Zip;

public class ZipTest {

	private static Zip z1 = new Zip();
	private static Zip z2 = new Zip();
	private static File testFile = new File("res/file/test");


	@BeforeAll
	static void setUpAll() {
		z1.setDirPath("res/file/test.zip");
		z2.extractZip("res/file/test.zip");
		//System.out.println(dir);
	}

	@AfterAll
	static void tearDownAll(){

	}

	@BeforeEach
	void setUp() {

	}

	@AfterEach
	void tearDown() {
		System.out.println("Test zip funciont - Success");
	}

	@Test
	public void getDirPathTest() {
		assertEquals("res/file/test.zip", z1.getDirPath());
	}

	@Test
	public void extractZipTest() {
		assertTrue(testFile.exists());
	}
}