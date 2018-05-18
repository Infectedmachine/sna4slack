package slackpack;

import java.io.IOException;

public class SlackMentionsFinder {
	private ArrayMentions mentions;
	private ArrayMember members;
	private ArrayChannel channels;
	private String workspacedir;

	public SlackMentionsFinder(String dir) {
		this.setMentions(new ArrayMentions());
		this.setMembers(new ArrayMember());
		this.setChannels(new ArrayChannel(this.getMembers()));
		this.setWorkspaceDir(dir);
	}

	public final void executeFinderOnWorkspace() throws Exception {
		this.initializeMembers();
		this.initializeChannels();
		for (String channelname : this.getChannels().getAllChannelsName()) {
			JFileScanner jfiles = new JFileScanner(this.getWorkspaceDir().concat("/" + channelname));
			ArrayMentions mentionstofilter = new ArrayMentions();
			try {
				mentionstofilter.fillMentionsFromFileList(jfiles.getArray());
			} catch (IOException e) {
				System.out.println(e);
			}
			this.getMentions().mergeArray(this.filterMentionsByChannel(mentionstofilter, channelname));
		}

	}

	public boolean isEmpty() {
		return this.getMentions().getArray().isEmpty();
	}

	public boolean isAbsent(String iduser) {
		for (Mention mention : this.getMentions().getArray()) {
			if (mention.getFROM().equals(iduser)) {
				return false;
			}
		}
		return true;
	}

	public final void executeFinderOnChannel(String channelname) throws Exception {
		this.initializeMembers();
		this.initializeChannels();
		ArrayMentions mentionstofilter = new ArrayMentions();
		JFileScanner jfiles = new JFileScanner(this.getWorkspaceDir().concat("/" + channelname));
		try {
			mentionstofilter.fillMentionsFromFileList(jfiles.getArray());
		} catch (IOException e) {
			System.out.println(e);
		}
		this.setMentions(this.filterMentionsByChannel(mentionstofilter, channelname));
	}

	private ArrayMentions filterMentionsByChannel(ArrayMentions tofilter, String channelname) throws Exception {
		ArrayMentions mentions = new ArrayMentions();
		for (Mention m : tofilter.getArray()) {
			Mention mention = new Mention();
			mention.setFROM(m.getFROM());
			for (String id : m.getTO()) {
				if (this.isMemberOfChannel(id, channelname)) {
					mention.addTO(id);
					mention.getWeight().put(id, m.getWeight().get(id));
				}
			}
			if (!mention.isEmptyTO()) {
				mentions.add(mention);
			}
		}
		return mentions;
	}

	public final void initializeMembers() {
		JsonFileParser slackusers = new JsonFileParser();
		slackusers.fillContentsFromJSONFileDir(this.getWorkspaceDir().concat("/users.json"));
		try {
			this.getMembers().fillArrayFromJSONArray(slackusers.getArray());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public final void initializeChannels() {
		JsonFileParser slackusers = new JsonFileParser();
		JsonFileParser slackchannels = new JsonFileParser();
		slackusers.fillContentsFromJSONFileDir(this.getWorkspaceDir().concat("/users.json"));
		slackchannels.fillContentsFromJSONFileDir(this.getWorkspaceDir().concat("/channels.json"));
		try {
			this.getChannels().fillArrayFromJSONArray(slackchannels.getArray());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private boolean isMemberOfChannel(String id, String channelname) throws Exception {
		return this.getChannels().getChannel(channelname).getMembersList().checkMemberById(id);
	}

	public void printNamedMentions() {
		if (!this.getMentions().getArray().isEmpty()) {
			for (Mention mention : this.getMentions().getArray()) {
				for (String id : mention.getTO()) {
					System.out.println("FROM: " + this.getMembers().getMemberById(mention.getFROM()).getNameByPriority()
							+ " TO: " + this.getMembers().getMemberById(id).getNameByPriority());
				}
				System.out.println("");
			}
		} else {
			System.out.println("NONE MENTIONS");
		}
	}

	public void printNamedMentionsFROM(String iduser) {
		if (!this.getMentions().getArray().isEmpty()) {
			if (!this.isAbsent(iduser)) {
				for (Mention mention : this.getMentions().getArray()) {
					if (mention.getFROM().equals(iduser)) {
						for (String id : mention.getTO()) {
							System.out.println(
									"FROM: " + this.getMembers().getMemberById(mention.getFROM()).getNameByPriority()
											+ " TO: " + this.getMembers().getMemberById(id).getNameByPriority());
						}
						System.out.println("");
					}
				}
			} else {
				System.out.println("NONE MENTION BY THIS USER");
			}
		} else {
			System.out.println("NONE MENTIONS");
		}
	}

	public void printNamedMentionsTO(String iduser) {
		if (!this.getMentions().getArray().isEmpty()) {
			if (!this.isAbsent(iduser)) {
				for (Mention mention : this.getMentions().getArray()) {
					for (String id : mention.getTO()) {
						if (id.equals(iduser)) {
							System.out.println(
									"FROM: " + this.getMembers().getMemberById(mention.getFROM()).getNameByPriority()
											+ " TO: " + this.getMembers().getMemberById(id).getNameByPriority());
						}
					}
				}
			} else {
				System.out.println("NONE MENTION TO THIS USER");
			}
		} else {
			System.out.println("NONE MENTIONS");
		}
	}

	public void printNamedMentionsWithWeight() {
		if (!this.getMentions().getArray().isEmpty()) {
			for (Mention mention : this.getMentions().getArray()) {
				for (String id : mention.getTO()) {
					System.out.println("FROM: " + this.getMembers().getMemberById(mention.getFROM()).getNameByPriority()
							+ " TO: " + this.getMembers().getMemberById(id).getNameByPriority() + " WEIGHT: "
							+ mention.getWeight().get(id));
				}
				System.out.println("");
			}
		} else {
			System.out.println("NONE MENTIONS");
		}
	}

	public void printNamedMentionsWithWeightFROM(String iduser) {
		if (!this.getMentions().getArray().isEmpty()) {
			if (!this.isAbsent(iduser)) {
				for (Mention mention : this.getMentions().getArray()) {
					if (mention.getFROM().equals(iduser)) {
						for (String id : mention.getTO()) {
							System.out.println(
									"FROM: " + this.getMembers().getMemberById(mention.getFROM()).getNameByPriority()
											+ " TO: " + this.getMembers().getMemberById(id).getNameByPriority()
											+ " WEIGHT: " + mention.getWeight().get(id));
						}
						System.out.println("");
					}
				}
			} else {
				System.out.println("NONE MENTION BY THIS USER");
			}
		} else {
			System.out.println("NONE MENTIONS");
		}
	}

	public void printNamedMentionsWhithWheightTO(String iduser) {
		if (!this.getMentions().getArray().isEmpty()) {
			if (!this.isAbsent(iduser)) {
				for (Mention mention : this.getMentions().getArray()) {
					for (String id : mention.getTO()) {
						if (id.equals(iduser)) {
							System.out.println(
									"FROM: " + this.getMembers().getMemberById(mention.getFROM()).getNameByPriority()
											+ " TO: " + this.getMembers().getMemberById(id).getNameByPriority()
											+ " WEIGHT: " + mention.getWeight().get(id));
						}
					}
				}
			} else {
				System.out.println("NONE MENTION TO THIS USER");
			}
		} else {
			System.out.println("NONE MENTIONS");
		}
	}

	public final void setWorkspaceDir(String dir) {
		this.workspacedir = dir;
	}

	public final String getWorkspaceDir() {
		return this.workspacedir;
	}

	public final void setMentions(ArrayMentions globalmentions) {
		this.mentions = globalmentions;
	}

	public final void setMembers(ArrayMember members) {
		this.members = members;
	}

	public final void setChannels(ArrayChannel channels) {
		this.channels = channels;
	}

	public ArrayMentions getMentions() {
		return this.mentions;
	}

	public ArrayMember getMembers() {
		return this.members;
	}

	public ArrayChannel getChannels() {
		return this.channels;
	}

}