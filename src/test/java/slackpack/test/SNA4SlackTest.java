/**
 * Test SNA4Slack class from slackpack package.
 **/
package slackpack.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import slackpack.CommandLineReader;
import slackpack.SNA4Slack;

@Tag("sna4slack")
public class SNA4SlackTest {

	private static CommandLineReader clr = new CommandLineReader();
	private static SNA4Slack sfs;

	@BeforeAll
	static void setUpAll() throws Exception {
		clr.setWorkspaceDir("res/files/test.zip");
		clr.setInputCommand("@mw");
		sfs = new SNA4Slack(clr);
		sfs.extractZipFile();
		sfs.initializeSlackUsers();
		sfs.initializeMembers();
		sfs.initializeSlackChannels();
		sfs.initializeChannels();
		sfs.initializeSlackMentionsFinder();
		sfs.getMentions().executeFinderOnWorkspace();
	}

	@AfterEach
	void tearDown() {
		System.out.println("Test commands - Success");
	}

	@Test
	public void checkMembers() {
		assertFalse(sfs.getMembers().getArray().isEmpty());
	}

	@Test
	public void checkChannels() {
		assertFalse(sfs.getChannels().getArray().isEmpty());
	}

	@Test
	public void checkName() throws Exception {
		assertTrue(sfs.getMembers().getMemberByName("Lanubile").checkId("U9BD7NMPC"));
	}

	@Test
	public void checkMentions() {
		assertFalse(sfs.getMentions().isEmpty());
	}

	@Test
	public void checkNotAbsentFrom() {
		assertFalse(sfs.getMentions().isAbsentFrom("U9BD7NMPC"));
	}

	@Test
	public void checkAbsentFrom() {
		assertTrue(sfs.getMentions().isAbsentFrom("AAA000ZZZ"));
	}

	@Test
	public void checkNotAbsentTo() {
		assertFalse(sfs.getMentions().isAbsentTo("U9BD7NMPC"));
	}

	@Test
	public void checkAbsentTo() {
		assertTrue(sfs.getMentions().isAbsentTo("AAA000ZZZ"));
	}
}
