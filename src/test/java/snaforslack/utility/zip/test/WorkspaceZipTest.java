/**
 * Test Zip class from slackpack package.
 **/
package snaforslack.utility.zip.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import snaforslack.utility.zip.WorkspaceZip;

@Tag("zip")
public class WorkspaceZipTest {

	private static WorkspaceZip zip;
	private static WorkspaceZip ziperror;
	private static File testFile;

	@BeforeAll
	static void setUpAll() {
		zip = new WorkspaceZip("res/TestsFiles/workspaceTest.zip");
		ziperror = new WorkspaceZip("workspaceTest.zip");
		testFile = new File("res/TestsFiles/workspaceTest");
	}

	@AfterEach
	void tearDown() {
		System.out.println("Test zip funciont - Success");
	}

	@Test
	public void exctractTest() {
		assertEquals(1, zip.extractZip());
	}

	@Test
	public void exctractFailTest() {
		assertEquals(-1, ziperror.extractZip());
	}

	@Test
	public void getDirPathTest() {
		assertEquals("res/TestsFiles/workspaceTest.zip", zip.getDirPath());
	}

	@Test
	public void extractZipTest() {
		assertTrue(testFile.exists());
	}
}