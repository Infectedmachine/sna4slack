package slackpack;

import java.io.IOException;

/**
 * This class consists of the attributes and methods to find and print mentions.
 **/
public class SlackMentionsFinder {
	/**
	 * Private attribute mentions, an ArrayMentions.
	 **/
	private ArrayMentions mentions;
	/**
	 * Private attribute members, an ArrayMembers.
	 **/
	private ArrayMember members;
	/**
	 * Private attribute channels, an ArrayChannels.
	 **/
	private ArrayChannel channels;
	/**
	 * Private attribute workspacedir,the workspace path.
	 **/
	private String workspacedir;

	/**
	 * Public constructor.
	 *
	 * @param dir the path of the workspace
	 **/
	public SlackMentionsFinder(final String dir) {
		this.setMentions(new ArrayMentions());
		this.setMembers(new ArrayMember());
		this.setChannels(new ArrayChannel(this.getMembers()));
		this.setWorkspaceDir(dir);
	}

	/**
	 * This method populates the mentions from all channels.
	 *
	 * @throws Exception IOException
	 **/
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

	/**
	 * This method checks if there are mentions.
	 *
	 * @return true if there are mentions in general.
	 **/
	public boolean isEmpty() {
		return this.getMentions().getArray().isEmpty();
	}

	/**
	 * This method checks if there is at least a mention made by iduser.
	 *
	 * @param iduser the member to be checked.
	 *
	 * @return true if there is a mentions from that user.
	 **/
	public boolean isAbsentFrom(final String iduser) {
		for (Mention mention : this.getMentions().getArray()) {
			if (mention.getFROM().equals(iduser)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This method checks if there is at least a mention made to iduser.
	 *
	 * @param iduser the member to be checked.
	 *
	 * @return true if there is a mentions to that user.
	 **/
	public boolean isAbsentTo(final String iduser) {
		for (Mention mention : this.getMentions().getArray()) {
			for (String id : mention.getTO()) {
				if (id.equals(iduser)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * This method finds mentions only in the channel given in input.
	 *
	 * @param channelname the channel you want to search for mentions.
	 *
	 * @throws Exception IOException
	 **/
	public final void executeFinderOnChannel(final String channelname) throws Exception {
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

	/**
	 * Private method checks if there is at least a mention made by iduser.
	 *
	 * @param tofilter the mentions to be filtered.
	 * @param chname the channel of the wanted mentions.
	 *
	 * @return mentions the list of mentions passed, but only in the channel selected.
	 *
	 * @throws Exception if the param are not valid.
	 **/
	private ArrayMentions filterMentionsByChannel(final ArrayMentions tofilter, final String chname) throws Exception {
		ArrayMentions tmpmentions = new ArrayMentions();
		for (Mention m : tofilter.getArray()) {
			Mention mention = new Mention();
			mention.setFROM(m.getFROM());
			for (String id : m.getTO()) {
				if (this.isMemberOfChannel(id, chname)) {
					mention.addTO(id);
					mention.getWeight().put(id, m.getWeight().get(id));
				}
			}
			if (!mention.isEmptyTO()) {
				tmpmentions.add(mention);
			}
		}
		return tmpmentions;
	}

	/**
	 * It initialize the users by getting them from JSON file.
	 **/
	public final void initializeMembers() {
		JsonFileParser slackusers = new JsonFileParser();
		slackusers.fillContentsFromJSONFileDir(this.getWorkspaceDir().concat("/users.json"));
		try {
			this.getMembers().fillArrayFromJSONArray(slackusers.getArray());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * It initialize the channels by getting them from JSON file.
	 **/
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

	/**
	 * It checks if the id is in channelname.
	 *
	 * @param id user to check.
	 * @param channelname the channel where to check.
	 *
	 * @return true if the user belongs to that channel.
	 *
	 * @throws Exception if id is not a member of channel.
	 **/
	private boolean isMemberOfChannel(final String id, final String channelname) throws Exception {
		return this.getChannels().getChannel(channelname).getMembersList().checkMemberById(id);
	}

	/**
	 * It prints all the mentions.
	 **/
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

	/**
	 * It prints all the mentions from a user.
	 *
	 * @param iduser the user that wrote the mentions.
	 **/
	public void printNamedMentionsFROM(final String iduser) {
		if (!this.getMentions().getArray().isEmpty() && !this.isAbsentFrom(iduser)) {
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
			System.out.println("NONE MENTIONS BY THIS USER");
		}
	}

	/**
	 * It prints all the mentions made to a user.
	 *
	 * @param iduser the user that received the mentions.
	 **/
	public void printNamedMentionsTO(final String iduser) {
		if (!this.getMentions().getArray().isEmpty() && !this.isAbsentTo(iduser)) {
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
			System.out.println("NONE MENTIONS TO THIS USER");
		}
	}

	/**
	 * It prints all the mentions from a user with weight.
	 *
	 **/
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

	/**
	 * It prints all the mentions from a user with weight.
	 *
	 * @param iduser the user that wrote the mentions.
	 **/
	public void printNamedMentionsWithWeightFROM(final String iduser) {
		if (!this.getMentions().getArray().isEmpty() && !this.isAbsentFrom(iduser)) {
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
			System.out.println("NONE MENTIONS BY THIS USER");
		}
	}

	/**
	 * It prints all the mentions made to a user with weight.
	 *
	 * @param iduser the user that received the mentions.
	 **/
	public void printNamedMentionsWhithWheightTO(final String iduser) {
		if (!this.getMentions().getArray().isEmpty() && !this.isAbsentTo(iduser)) {
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
			System.out.println("NONE MENTIONS TO THIS USER");
		}
	}

	/**
	 * It sets the workspacedir.
	 * @param dir the workspace path.
	 **/
	public final void setWorkspaceDir(final String dir) {
		this.workspacedir = dir;
	}

	/**
	 * It sets all the mentions.
	 * @param globalmentions all the mentions.
	 **/
	public final void setMentions(final ArrayMentions globalmentions) {
		this.mentions = globalmentions;
	}

	/**
	 * It sets all the members.
	 * @param globalmembers all the members.
	 **/
	public final void setMembers(final ArrayMember globalmembers) {
		this.members = globalmembers;
	}

	/**
	 * It sets all the channels.
	 * @param globalchannels all the channels.
	 **/
	public final void setChannels(final ArrayChannel globalchannels) {
		this.channels = globalchannels;
	}

	/**
	 * It gets the workspace path.
	 * @return workspacedir string.
	 **/
	public final String getWorkspaceDir() {
		return this.workspacedir;
	}

	/**
	 * It gets the menions.
	 * @return mentions Array.
	 **/
	public ArrayMentions getMentions() {
		return this.mentions;
	}

	/**
	 * It gets the members.
	 * @return members Array.
	 **/
	public ArrayMember getMembers() {
		return this.members;
	}

	/**
	 * It gets the channels.
	 * @return channels Array.
	 **/
	public ArrayChannel getChannels() {
		return this.channels;
	}

}
