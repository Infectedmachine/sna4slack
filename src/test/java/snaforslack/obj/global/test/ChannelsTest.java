package snaforslack.obj.global.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import snaforslack.interfaces.json.IntJsonArray;
import snaforslack.interfaces.json.IntJsonFileParser;
import snaforslack.interfaces.obj.IntObjChannels;
import snaforslack.interfaces.obj.IntObjUsers;
import snaforslack.interfaces.structures.IntChannel;
import snaforslack.obj.global.Channels;
import snaforslack.obj.global.Users;
import snaforslack.utility.json.JsonFileParser;

public class ChannelsTest {

	private static IntObjUsers tUsers;
	private static IntJsonArray uArray;
	private static IntJsonArray cArray;
	private static IntJsonFileParser uParser;
	private static IntJsonFileParser cParser;
	private static IntObjChannels tChannels;
    private static IntChannel tChannel;
    private static List<String> channelsNames;
    private static String channel = "general";

	@BeforeAll
	public static void setUpAll() {
		tUsers = new Users();
		uParser = new JsonFileParser();
		uParser.initializeFromJSONFileDir("res/TestsFiles/workspaceTest/users.json");
		uArray = uParser.getArray();
		tUsers.initializeFromJsonArray(uArray);
		tChannels = new Channels (tUsers);
		cParser = new JsonFileParser();
		cParser.initializeFromJSONFileDir("res/TestsFiles/workspaceTest/channels.json");
		cArray = cParser.getArray();
		tChannels.initializeFromJsonArray(cArray);
		tChannel = tChannels.getChannel(channel);
		channelsNames = tChannels.getAllChannelsName();
	}

	@Test
	public void testCheckChannelTrue() {
		assertTrue(tChannels.checkChannel(channel));
	}

	@Test
	public void testCheckChannelFasle() {
		assertFalse(tChannels.checkChannel("fakechannel"));
	}

	@Test
	public void testGetChannel() {
		assertEquals(tChannel, tChannels.getChannel(channel));
	}

	@Test
	public void testGetAllChannelsName() {
		assertEquals(channelsNames, tChannels.getAllChannelsName());
	}

	@Test
	public void testPrintList() {
		System.out.println("TESTING CLASS CHANNELS PRINT LIST: ");
		tChannels.printList();
	}

	@Test
	public void testPrintListDetailed() {
		System.out.println("TESTING CLASS CHANNELS PRINT LIST DETAILED: ");
		tChannels.printListDetailed();
	}

	@Test
	public void testPrintUsersOfChannel() {
		System.out.println("TESTING CLASS CHANNELS PRINT USERS OF CHANNEL (general): ");
		tChannels.printUsersOfChannel(channel);
	}
}