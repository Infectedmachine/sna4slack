/**
 * Test CommandLineReader class from snaforslack.main.test package.
 **/
package snaforslack.main.test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import snaforslack.main.CommandReader;

@Tag("commands")
public class CommandReaderTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	private static CommandReader clr4 = new CommandReader();
	private static CommandReader clr3 = new CommandReader();
	private static CommandReader clr3b = new CommandReader();
	private static String path = "path";
	private static String channel = "general";

	@BeforeAll
	static void setUpAll() {
		final String[] commandsfour = { path, "-m", "Lanubile", channel };
		final String[] commandsthree = { path, "-cm", channel };
		final String[] commandsthreebis = { path, "@mt", "Novielli" };
		try {
			clr4.initializeFromArgs(commandsfour);
			clr3.initializeFromArgs(commandsthree);
			clr3b.initializeFromArgs(commandsthreebis);
		} catch (Exception e) {
			System.out.println("Wrong Commands");
		}
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
		assertEquals(path, clr4.getWorkspaceDir());
	}

	@Test
	public void testFillChannel() {
		assertEquals(channel, clr4.getChannelName());
	}

	@Test
	public void testFillCommand() {
		assertEquals("-m", clr4.getInputCommand());
	}

	@Test
	public void testFillCommandLessThanFour() {
		assertEquals(channel, clr3.getChannelName());
	}

	@Test
	public void testFillCommandLessThanFourBis() {
		assertEquals("Novielli", clr3b.getUsername());
	}

	@Test
	public void testInvalidCommandLenght() {
		final String[] wrongcommands = {path};
		final CommandReader clrFalse = new CommandReader();
		try {
			clrFalse.initializeFromArgs(wrongcommands);
			exception.expect(Exception.class);
		} catch (Exception e) {
			System.out.println("Test Excepion Commands - Success");
		}
	}
}