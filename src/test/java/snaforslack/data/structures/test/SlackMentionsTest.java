package snaforslack.data.structures.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import snaforslack.data.list.ListUsers;
import snaforslack.data.structures.SlackUser;
import snaforslack.data.structures.SlackUserMentions;
import snaforslack.interfaces.list.IntListUsers;
import snaforslack.interfaces.structures.IntUser;
import snaforslack.interfaces.structures.IntUserMentions;

public class SlackMentionsTest {
	private static IntUserMentions mentionTest;
	private static IntUser sender;
	private static IntListUsers receivers;
	private static Map<String, Integer> testWeight;
	private static String receiverid1 = "receiverid1";

	@BeforeAll
	public static void setUpAll(){
		sender = new SlackUser("senderid", "sendername");
		final SlackUser user1 = new SlackUser(receiverid1, "receivername1");
		final SlackUser user2 = new SlackUser("receiverid2", "receivername2");
		final SlackUser user3 = new SlackUser("receiverid3", "receivername3");
		receivers = new ListUsers();
		receivers.addUser(user1);
		receivers.addUser(user2);
		receivers.addUser(user3);
		mentionTest = new SlackUserMentions(sender, receivers.getUsersList());
		testWeight = new HashMap<String, Integer>();
		testWeight.put(receiverid1, 1);
		testWeight.put("receiverid2", 1);
		testWeight.put("receiverid3", 1);
	}

	@Test
	public void testGetSender() {
		assertEquals(sender, mentionTest.getSender());
	}

	@Test
	public void testGetListReceiver() {
		assertEquals(receivers.getUsersList(), mentionTest.getListReceiver());
	}

	@Test
	public void testGetListReceiverEmpty() {
		 ArrayList<IntUser> tmp = new ArrayList<IntUser>();
		tmp = (ArrayList<IntUser>) mentionTest.getListReceiver();
		assertEquals(tmp, mentionTest.getListReceiver());
	}

	@Test
	public void testGetMentionWeightMap() {
		assertEquals(testWeight, mentionTest.getMentionWeightMap());
	}

	@Test
	public void testGetSenderId() {
		assertEquals("senderid", mentionTest.getSenderId());
	}

	@Test
	public void testGetSenderName() {
		assertEquals("sendername", mentionTest.getSenderName());
	}

	@Test
	public void testGetWeight() {
		assertEquals(1, mentionTest.getWeight(receiverid1));
	}

	@Test
	public void testGetCheckSenderIdTrue() {
		assertTrue(mentionTest.checkSender(sender.getUserId()));
	}

	@Test
	public void testGetCheckSenderNameTrue() {
		assertTrue(mentionTest.checkSender(sender.getUserName()));
	}

	@Test
	public void testGetCheckSenderNameFalse() {
		assertFalse(mentionTest.checkSender("falseName"));
	}

	@Test
	public void testGetCheckSenderIdFalse() {
		assertFalse(mentionTest.checkSender("falseId"));
	}

	@Test
	public void testGetCheckReceiverTrue() {
		assertTrue(mentionTest.checkReceiver(receivers.getUser(receiverid1)));
	}

		@Test
	public void testGetCheckReceiverFalse() {
		final SlackUser user1 = new SlackUser("fakeid", "fakename");
		assertFalse(mentionTest.checkReceiver(user1));
	}
}