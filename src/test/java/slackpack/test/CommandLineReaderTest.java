/**
 * Test CommandLineReader class from slackpack package.
 **/
package slackpack.test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import slackpack.CommandLineReader;

@Tag("commands")
public class CommandLineReaderTest {

	private static CommandLineReader clr4 = new CommandLineReader();
	private static CommandLineReader clr3 = new CommandLineReader();
	private static CommandLineReader clr3b = new CommandLineReader();

	@BeforeAll
	static void setUpAll() {
		String[] commandsfour = { "path", "-m", "Lanubile", "general" };
		String[] commandsthree = { "path", "-cm", "general" };
		String[] commandsthreebis = { "path", "@mt", "Novielli" };
		try {
			clr4.fillCommandsSetFromArgs(commandsfour);
			clr3.fillCommandsSetFromArgs(commandsthree);
			clr3b.fillCommandsSetFromArgs(commandsthreebis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterAll
	static void tearDownAll() {

	}

	@BeforeEach
	void setUp() {

	}

	@AfterEach
	void tearDown() {
		System.out.println("Test Fill Commands - Success");
	}

	@Test
	public void testValidCommand() {
		assertTrue(clr4.isValidCommand("@m"));
	}

	@Test
	public void testInvalidCommand() {
		assertFalse(clr4.isValidCommand(" "));
	}

	@Test
	public void testFillUsername() {
		assertEquals("Lanubile", clr4.getUsername());
	}

	@Test
	public void testFillPath() {
		assertEquals("path", clr4.getWorkspaceDir());
	}

	@Test
	public void testFillChannel() {
		assertEquals("general", clr4.getChannelName());
	}

	@Test
	public void testFillCommand() {
		assertEquals("-m", clr4.getInputCommand());
	}

	@Test
	public void testFillCommandLessThanFour() {
		assertEquals("general", clr3.getChannelName());
	}

	@Test
	public void testFillCommandLessThanFourBis() {
		assertEquals("Novielli", clr3b.getUsername());
	}
}

