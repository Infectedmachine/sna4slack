package snaforslack.utility.json.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import snaforslack.utility.json.JFileScanner;

public class JFileScannerTest {
	private static JFileScanner scan = new JFileScanner();
	private static List<File> testList = new ArrayList<File>();

	@BeforeAll
	public static void setUpAll() {
		scan = new JFileScanner("res/TestsFiles/workspaceTest");
		testList = scan.getList();
	}

	@Test
	public void scannerTest() {
		assertTrue(!testList.isEmpty());
	}

}
