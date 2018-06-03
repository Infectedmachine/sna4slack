package snaforslack.data.structures.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import snaforslack.data.list.ListUsers;
import snaforslack.data.structures.SlackChannel;
import snaforslack.data.structures.SlackUser;
import snaforslack.interfaces.list.IntListUsers;
import snaforslack.interfaces.structures.IntUser;



public class SlackChannelTest {

	private static SlackChannel channelTest;
	private static final String TESTID = "CH4NNT35T";
	private static final String TESTNAME = "channelTest";
	private static boolean archivedTest;
	private static IntListUsers testLIST;
	private static String testid1 = "testid1";

	@BeforeAll
	public static void setupAll() {
		channelTest = new SlackChannel(TESTID, TESTNAME);
		testLIST = new ListUsers();
	}


	@Test
	public void testGetChannelName() {
	assertEquals(TESTNAME, channelTest.getChannelName());
	}

	@Test
	public void testGetChannelId() {
		assertEquals(TESTID, channelTest.getChannelId());
	}

	@Test
	public void testSetGetListUsers() {
		final IntUser user1 = new SlackUser(testid1, "testname1");
		final IntUser user2 = new SlackUser("testid2", "testname2");
		final IntUser user3 = new SlackUser("testid3", "testname3");
		testLIST.addUser(user1);
		testLIST.addUser(user2);
		testLIST.addUser(user3);
		channelTest.setListUsers(testLIST);
		assertEquals(testLIST, channelTest.getListUsers());
	}

	@Test
	public void testGetListUsersEmpty() {
		testLIST = channelTest.getListUsers();
		assertEquals(testLIST, channelTest.getListUsers());
	}

	@Test
	public void testSetGetArchivedTrue() {
		archivedTest = true;
		channelTest.setArchived(archivedTest);
		assertTrue(channelTest.isArchived());
	}

	@Test
	public void testSetGetArchivedFalse() {
		archivedTest = false;
		channelTest.setArchived(archivedTest);
		assertFalse(channelTest.isArchived());
	}

	@Test
	public void testCheckUserTrue() {
		final IntUser user1 = new SlackUser(testid1, "testname1");
		final IntUser user2 = new SlackUser("testid2", "testname2");
		final IntUser user3 = new SlackUser("testid3", "testname3");
		testLIST.addUser(user1);
		testLIST.addUser(user2);
		testLIST.addUser(user3);
		channelTest.setListUsers(testLIST);
		assertTrue(channelTest.checkUser(testid1));
		//assertTrue(channelTest.checkUser("testname1"));
	}

	@Test
	public void testCheckUserFalse() {
		final IntUser user1 = new SlackUser(testid1, "testname1");
		final IntUser user2 = new SlackUser("testid2", "testname2");
		final IntUser user3 = new SlackUser("testid3", "testname3");
		testLIST.addUser(user1);
		testLIST.addUser(user2);
		testLIST.addUser(user3);
		channelTest.setListUsers(testLIST);
		assertFalse(channelTest.checkUser("G1ANN1G14LL027"));
		//assertFalse(channelTest.checkUser("GianniG27"));
	}

	@Test
	public void testcheckEmpty() {
		final IntUser user1 = new SlackUser(testid1, "testname1");
		final IntUser user2 = new SlackUser("testid2", "testname2");
		final IntUser user3 = new SlackUser("testid3", "testname3");
		testLIST.addUser(user1);
		testLIST.addUser(user2);
		testLIST.addUser(user3);
		channelTest.setListUsers(testLIST);
		assertFalse(channelTest.isUsersListEmpty());
	}
}