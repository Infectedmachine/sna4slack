package snaforslack.obj.global.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import snaforslack.interfaces.json.IntJsonArray;
import snaforslack.interfaces.json.IntJsonFileParser;
import snaforslack.interfaces.obj.IntObjChannels;
import snaforslack.interfaces.obj.IntObjMentions;
import snaforslack.interfaces.obj.IntObjUsers;
import snaforslack.interfaces.structures.IntChannel;
import snaforslack.obj.global.Channels;
import snaforslack.obj.global.Mentions;
import snaforslack.obj.global.Users;
import snaforslack.utility.json.JsonFileParser;

public class MentionsTest {
	private static IntObjUsers tUsers;
	private static IntObjChannels tChannels;
	private static IntJsonArray uArray;
	private static IntJsonArray cArray;
	private static IntJsonArray aArray;
	private static IntJsonArray aArray2;
	private static IntJsonFileParser uParser;
	private static IntJsonFileParser cParser;
	private static IntJsonFileParser aParser;
	private static IntJsonFileParser aParser2;
	private static IntObjMentions tMentions1;
	private static IntObjMentions tMentions2;
	private static IntObjMentions tMentions3;
	private static IntChannel tChannel;

	@BeforeAll
	public static void setUpAll() {
		tUsers = new Users();
		uParser = new JsonFileParser();
		cParser = new JsonFileParser();
		aParser = new JsonFileParser();
		aParser2 = new JsonFileParser();
		uParser.initializeFromJSONFileDir("res/TestsFiles/workspaceTest/users.json");
		uArray = uParser.getArray();
		cParser.initializeFromJSONFileDir("res/TestsFiles/workspaceTest/channels.json");
		cArray = cParser.getArray();
		tUsers.initializeFromJsonArray(uArray);
		tChannels = new Channels(tUsers);
		tChannels.initializeFromJsonArray(cArray);
		tMentions1 = new Mentions(tUsers, true);
		tMentions2 = new Mentions(tUsers, false);
		tMentions3 = new Mentions(tUsers, false);
		tChannel = tChannels.getChannel("general");
		aParser.initializeFromJSONFileDir("res/TestsFiles/workspaceTest/general/2018-04-20.json");
		aParser2.initializeFromJSONFileDir("res/TestsFiles/workspaceTest/general/2018-04-12.json");
		aArray = aParser.getArray();
		aArray2 = aParser2.getArray();
		tMentions1.initializeFromJsonArray(aArray, tChannel);
		tMentions1.initializeFromJsonArray(aArray2, tChannel);
		tMentions3.initializeFromJsonArray(aArray2, tChannel);
	}

	@Test
	public void mentionsWeightNotEmptyTest() {
		System.out.println("TESTING WEIGHTED MENTIONS: ");
		tMentions1.printList();
	}

	@Test
	public void mentionsNotEmptyTest() {
		System.out.println("TESTING NOT WEIGHTED MENTIONS: ");
		tMentions3.printList();
	}

	@Test
	public void mentionsEmptyTest() {
		System.out.println("TESTING EMPTY MENTIONS: ");
		tMentions2.printList();
	}

}