package snaforslack.data.structures.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import snaforslack.data.structures.SlackUser;

public class SlackUserTest {

	private static SlackUser userTest;
	private static final String TESTID = "USRT35TID";
	private static final String TESTNAME = "userTest";

	@BeforeAll
	public static void setupAll() {
		userTest = new SlackUser( TESTID, TESTNAME);
	}


	@Test
	public void testGetUserName() {
		assertEquals(userTest.getUserName(), TESTNAME);
	}

	@Test
	public void testGetUserId() {
		assertEquals(userTest.getUserId(), TESTID);
	}

	@Test
	public void testEmpty() {
		assertFalse(userTest.isEmpty());
	}
}