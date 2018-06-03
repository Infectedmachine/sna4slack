package snaforslack.data.list.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import snaforslack.data.list.ListUsers;
import snaforslack.data.list.ListUsersMentions;
import snaforslack.data.structures.SlackUser;
import snaforslack.data.structures.SlackUserMentions;
import snaforslack.interfaces.list.IntListUserMentions;
import snaforslack.interfaces.list.IntListUsers;
import snaforslack.interfaces.structures.IntUser;
import snaforslack.interfaces.structures.IntUserMentions;

public class ListUsersMentionsTest {

	private static IntListUserMentions mentionListTest;
	private static IntListUserMentions noMentionListTest;
	private static IntListUsers receivers1;
	private static IntListUsers receivers2;
	private static IntListUsers receivers3;
	private static IntUser sender1;
	private static IntUser sender2;
	private static IntUser sender3;

	private static IntUserMentions testMention1;
	private static IntUserMentions testMention2;
	private static IntUserMentions testMention3;

	private static List<IntUserMentions> testMentionList;

	@BeforeAll
	public static void serUpAll() {
		sender1 = new SlackUser("senderid1", "sendername1");
		sender2 = new SlackUser("senderid2", "sendername2");
		sender3 = new SlackUser("senderid3", "sendername3");

		final SlackUser receiver1 = new SlackUser("receiverid1", "receivername1");
		final SlackUser receiver2 = new SlackUser("receiverid2", "receivername2");
		final SlackUser receiver3 = new SlackUser("receiverid3", "receivername3");
		final SlackUser receiver4 = new SlackUser("receiverid4", "receivername4");
		final SlackUser receiver5 = new SlackUser("receiverid5", "receivername5");
		final SlackUser receiver6 = new SlackUser("receiverid6", "receivername6");
		final SlackUser receiver7 = new SlackUser("receiverid7", "receivername7");
		final SlackUser receiver8 = new SlackUser("receiverid8", "receivername8");
		final SlackUser receiver9 = new SlackUser("receiverid9", "receivername9");

		receivers1 = new ListUsers();
		receivers2 = new ListUsers();
		receivers3 = new ListUsers();
		receivers1.addUser(receiver1);
		receivers1.addUser(receiver2);
		receivers1.addUser(receiver3);
		receivers2.addUser(receiver4);
		receivers2.addUser(receiver5);
		receivers2.addUser(receiver6);
		receivers3.addUser(receiver7);
		receivers3.addUser(receiver8);
		receivers3.addUser(receiver9);

		testMention1 = new SlackUserMentions(sender1, receivers1.getUsersList());
		testMention2 = new SlackUserMentions(sender2, receivers2.getUsersList());
		testMention3 = new SlackUserMentions(sender3, receivers3.getUsersList());

		testMentionList = new ArrayList<IntUserMentions>();
		testMentionList.add(testMention1);
		testMentionList.add(testMention2);
		testMentionList.add(testMention3);

		mentionListTest = new ListUsersMentions();
		noMentionListTest = new ListUsersMentions();
		mentionListTest.addMentions(testMention1);
		mentionListTest.addMentions(testMention2);
		mentionListTest.addMentions(testMention3);
		mentionListTest.addMissingReceivers(testMention1);
		mentionListTest.addMissingReceivers(testMention2);
		mentionListTest.addMissingReceivers(testMention3);

	}

	@Test
	public void testGetMentionsListFull() {
		assertEquals(testMentionList, mentionListTest.getMentionsList());
	}

	@Test
	public void testGetMentionsListEmpty() {
		assertEquals(Collections.emptyList(), noMentionListTest.getMentionsList());
	}

	@Test
	public void testIsEmptyTrue() {
		assertTrue(noMentionListTest.isEmpty());
	}

	@Test
	public void testIsEmptyFalse() {
		assertFalse(mentionListTest.isEmpty());
	}

	@Test
	public void testCheckSender1True() {
		assertTrue(mentionListTest.checkSender(sender1.getUserId()));
	}

	@Test
	public void testCheckSender2True() {
		assertTrue(mentionListTest.checkSender(sender2.getUserId()));
	}

	@Test
	public void testCheckSender3True() {
		assertTrue(mentionListTest.checkSender(sender3.getUserId()));
	}

	@Test
	public void testCheckSenderFalse() {
		assertFalse(mentionListTest.checkSender("fakesender"));
	}

	@Test
	public void testGetSenderMentions1() {
		assertEquals(testMention1, mentionListTest.getSenderMentions(sender1.getUserId()));
	}

	@Test
	public void testGetSenderMentions2() {
		assertEquals(testMention2, mentionListTest.getSenderMentions(sender2.getUserId()));
	}

	@Test
	public void testGetSenderMentions3() {
		assertEquals(testMention3, mentionListTest.getSenderMentions(sender3.getUserId()));
	}

	@Test
	public void testGetSenderMentionsNOTFOUND() {
		testMention1 = mentionListTest.getSenderMentions("FAKESENDER");
		sender1 = testMention1.getSender();
		assertNull(sender1);
	}
}
