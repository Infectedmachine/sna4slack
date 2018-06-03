package snaforslack.data.list.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import snaforslack.data.list.ListUsers;
import snaforslack.data.structures.SlackUser;
import snaforslack.interfaces.list.IntListUsers;

public class ListUsersTest {

	private static IntListUsers listUsersTest1;
	private static IntListUsers listUsersTest2;
	private static IntListUsers listUsersTest3;

	@BeforeAll
	public static void setUpAll() {

		final SlackUser user1 = new SlackUser("testid1", "testname1");
		final SlackUser user2 = new SlackUser("testid2", "testname2");
		final SlackUser user3 = new SlackUser("testid3", "testname3");
		listUsersTest1 = new ListUsers();
		listUsersTest1.addUser(user1);
		listUsersTest1.addUser(user2);
		listUsersTest1.addUser(user3);
		listUsersTest2 = new ListUsers();
		listUsersTest2.addUser(user1);
		listUsersTest2.addUser(user2);
		listUsersTest2.addUser(user3);
		listUsersTest3 = new ListUsers();

	}

	@Test
	public void getUsersListFullTest() {
		assertEquals(listUsersTest1.getUsersList(), listUsersTest2.getUsersList());
	}

	@Test
	public void getUsersListFullBisTest() {
		assertEquals(listUsersTest2.getUsersList(), listUsersTest1.getUsersList());
	}

	@Test
	public void getUsersListEmptyTest() {
		assertEquals(Collections.emptyList(), listUsersTest3.getUsersList());
	}

	@Test
	public void listSizeTest1() {
		assertEquals(listUsersTest1.listSize(), listUsersTest2.listSize());
	}

	@Test
	public void listSizeTest2() {
		assertEquals(listUsersTest2.listSize(), listUsersTest1.listSize());
	}

	@Test
	public void listIsEmptyTrueTest() {
		assertTrue(listUsersTest3.listIsEmpty());
	}

	@Test
	public void listIsEmptyFalseTest1() {
		assertFalse(listUsersTest1.listIsEmpty());
	}

	@Test
	public void listIsEmptyFalseTest2() {
		assertFalse(listUsersTest2.listIsEmpty());
	}
}