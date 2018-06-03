package snaforslack.main.test;

import org.junit.jupiter.api.Test;

import it.uniba.main.AppMain;

public class SNA4SlackTest {
	private static String channel = "general";
	private static String member = "Lanubile";
	private static String path = "res/TestsFiles/workspaceTest.zip";
	private static String[] members = {path, "-m" };
	private static String[] channels = {path, "-c" };
	private static String[] mbrOfCh = {path, "-mc" };
	private static String[] chMbr = {path, "-cm", channel };
	private static String[] mentions = {path, "@m" };
	private static String[] chMentions = {path, "@m", channel };
	private static String[] mentionsF = {path, "@mf", member };
	private static String[] mentionsF2 = {path, "@mf", member, channel };
	private static String[] mentionsT = {path, "@mt", member };
	private static String[] mentionsT2 = {path, "@mt", member, channel };
	private static String[] wMentions = {path, "@mw" };
	private static String[] wMentions2 = {path, "@mw", channel };
	private static String[] wMentionsF = {path, "@mfw", member };
	private static String[] wMentionsF2 = {path, "@mfw", member, channel };
	private static String[] wMentionsT = {path, "@mtw", member };
	private static String[] wMentionsT2 = {path, "@mtw", member, channel };
	private static String[] wrongPath = {"tst/", "-m" };
	private static String[] wrongCommand = {path, "-" };
	private static String[] wrongMember = {path, "@mtw", " ", channel };
	private static String[] wrongReceiver = {path, "@mfw", " ", channel };
	private static String[] wrongChannel= {path, "@mtw", member , " " };
	private static String[] wrongChannel2= {path, "-cm", ""};


	@Test
	public void testWrongPath() {
		System.out.println("TESTING EXCEPTION: ");
		AppMain.main(wrongPath);
	}

	@Test
	public void testWrongCommand() {
		System.out.println("TESTING EXCEPTION: ");
		AppMain.main(wrongCommand);
	}

	@Test
	public void testWrongMember() {
		System.out.println("TESTING EXCEPTION: ");
		AppMain.main(wrongMember);
	}

	@Test
	public void testReceiver() {
		System.out.println("TESTING EXCEPTION: ");
		AppMain.main(wrongReceiver);
	}

	@Test
	public void testWrongChannel() {
		System.out.println("TESTING EXCEPTION: ");
		AppMain.main(wrongChannel);
	}

	@Test
	public void testWrongChannel2() {
		System.out.println("TESTING EXCEPTION: ");
		AppMain.main(wrongChannel2);
	}
	@Test
	public void testSNA4Slack() {
		System.out.println("TESTING MEMBERS: ");
		AppMain.main(members);
	}

	@Test
	public void testSNA4Slack2() {
		System.out.println("TESTING CHANNELS: ");
		AppMain.main(channels);
	}

	@Test
	public void testSNA4Slack3() {
		System.out.println("TESTING MEMBER OF CHANNELS: ");
		AppMain.main(mbrOfCh);
	}

	@Test
	public void testSNA4Slack4() {
		System.out.println("TESTING CHANNELS MEMBERS: ");
		AppMain.main(chMbr);
	}

	@Test
	public void testSNA4Slack5() {
		System.out.println("TESTING MENTIONS: ");
		AppMain.main(mentions);
	}

	@Test
	public void testSNA4SlackCh() {
		System.out.println("TESTING CHANNEL MENTIONS: ");
		AppMain.main(chMentions);
	}
	@Test
	public void testSNA4Slack6() {
		System.out.println("TESTING MENTIONS FROM: ");
		AppMain.main(mentionsF);
	}

	@Test
	public void testSNA4Slack7() {
		System.out.println("TESTING MENTIONS FROM MEMBER OF CHANNEL: ");
		AppMain.main(mentionsF2);
	}

	@Test
	public void testSNA4Slack8() {
		System.out.println("TESTING MENTIONS TO: ");
		AppMain.main(mentionsT);
	}
	@Test
	public void testSNA4Slack9() {
		System.out.println("TESTING MENTIONS TO MEMBER OF CHANNEL: ");
		AppMain.main(mentionsT2);
	}

	@Test
	public void testSNA4Slack10() {
		System.out.println("TESTING WEIGHTED MENTIONS FROM: ");
		AppMain.main(wMentionsF);
	}

	@Test
	public void testSNA4Slack11() {
		System.out.println("TESTING WEIGHTED MENTIONS FROM MEMBER OF CHANNEL: ");
		AppMain.main(wMentionsF2);
	}

	@Test
	public void testSNA4Slack13() {
		System.out.println("TESTING WEIGHTED MENTION TO: ");
		AppMain.main(wMentionsT);
	}
	@Test
	public void testSNA4Slack14() {
		System.out.println("TESTING WEIGHTED MENTION TO MEMBER OF CHANNEL: ");
		AppMain.main(wMentionsT2);
	}

	@Test
	public void testSNA4Slack15() {
		System.out.println("TESTING WEIGHTED MENTIONS: ");
		AppMain.main(wMentions);
	}

	@Test
	public void testSNA4Slack16() {
		System.out.println("TESTING WEIGHTED MENTIONS OF CHANNEL: ");
		AppMain.main(wMentions2);
	}

}