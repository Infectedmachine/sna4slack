package snaforslack.obj.global.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import snaforslack.interfaces.json.IntJsonArray;
import snaforslack.interfaces.json.IntJsonFileParser;
import snaforslack.interfaces.obj.IntObjUsers;
import snaforslack.interfaces.structures.IntUser;
import snaforslack.obj.global.Users;
import snaforslack.utility.json.JsonFileParser;

public class UsersTest {

	private static IntObjUsers tUsers;
	private static IntJsonArray uArray;
	private static IntJsonFileParser tParser;
	private static IntUser tUser;

	@BeforeAll
	public static void setUpAll() {
		tUsers = new Users();
		tParser = new JsonFileParser();
		tParser.initializeFromJSONFileDir("res/TestsFiles/workspaceTest/users.json");
		uArray = tParser.getArray();
		tUsers.initializeFromJsonArray(uArray);
		tUser = tUsers.getUser("Lanubile");
	}

	@Test
	public void testGetUser() {
		assertEquals(tUser, tUsers.getUser("Lanubile"));
	}

	@Test
	public void testCheckUserTrue() {
		assertTrue(tUsers.checkUser("Lanubile"));
	}

	@Test
	public void testCheckUserFalse() {
		assertFalse(tUsers.checkUser("fakeuser"));
	}

	@Test
	public void testPrintList() {
		System.out.println("TESTING CLASS USERS PRINT LIST: ");
		tUsers.printList();
	}
}