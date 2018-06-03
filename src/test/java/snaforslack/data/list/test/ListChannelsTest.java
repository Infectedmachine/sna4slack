package snaforslack.data.list.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import snaforslack.data.list.ListChannels;
import snaforslack.data.structures.SlackChannel;
import snaforslack.interfaces.list.IntListChannels;
import snaforslack.interfaces.structures.IntChannel;

public class ListChannelsTest {


	private static IntListChannels listch = new ListChannels();
	private static IntListChannels lsempty = new ListChannels();
	private static IntChannel channel = new SlackChannel("id","name");
	private static IntListChannels listch2 = new ListChannels();

	@BeforeAll
	static void setUpAll() {
		listch.addChannel(channel);
		listch2.addChannel(channel);

	}


		@AfterEach
		void tearDown() {
			System.out.println("Test ListChannels - Success");
		}

		@Test
		public void testSize() {
			assertEquals( listch.listSize() , listch2.listSize());
		}

		@Test
		public void testNull() {
			assertEquals(Collections.emptyList(), lsempty.getChannelsList());
		}

		@Test
		public void testNotNull() {
			assertEquals(listch.getChannelsList(), listch2.getChannelsList());
		}

		@Test
		public void testEmpty() {
			assertTrue(lsempty.listIsEmpty());
		}

		@Test
		public void testNotEmpty() {
			assertFalse( listch.listIsEmpty());
		}

}