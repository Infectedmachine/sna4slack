package snaforslack.main;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import snaforslack.interfaces.json.IntJFileScanner;
import snaforslack.interfaces.json.IntJsonArray;
import snaforslack.interfaces.json.IntJsonFileParser;
import snaforslack.interfaces.main.Helper;
import snaforslack.interfaces.main.IntCommandReader;
import snaforslack.interfaces.obj.IntObjChannels;
import snaforslack.interfaces.obj.IntObjMentions;
import snaforslack.interfaces.obj.IntObjUsers;
import snaforslack.interfaces.zip.IntZip;
import snaforslack.obj.global.Channels;
import snaforslack.obj.global.Mentions;
import snaforslack.obj.global.Users;
import snaforslack.utility.json.JFileScanner;
import snaforslack.utility.json.JsonFileParser;
import snaforslack.utility.zip.WorkspaceZip;
import snaforslack.interfaces.main.IntSNA4Slack;

/**
 * Manages all the objects needed by the software.
 *
 */
public class SNA4Slack implements IntSNA4Slack {

	/**
	 * Path of JSON users file.
	 */
	private static final String USERSFILE = "/users.json";
	/**
	 * Path of JSON channels file.
	 */
	private static final String CHANNELSFILE = "/channels.json";

	/**
	 * boolean value for weighed mentions.
	 */
	protected static final boolean WEIGHED = true;
	/**
	 * boolean value for weighed mentions.
	 */
	protected static final boolean NOTWEIGHED = false;
	/**
	 * Int value meaning the status of execution.
	 */
	private static final int ERROR = -1;

	/**
	 * Class attribute Users object contains all users.
	 */
	private IntObjUsers users;

	/**
	 * Class attribute Channels object contains all channels.
	 */
	private IntObjChannels channels;

	/**
	 * Class attribute Mentions object contains all mentions.
	 */
	private IntObjMentions mentions;

	/**
	 * Class attribute JsonFileParser object parses the JSON object.
	 */
	private IntJsonFileParser jsonParser;

	/**
	 * Class attribute CommandReader object manages the input commands.
	 */
	private final IntCommandReader commands;

	/**
	 * Class attribute commandsTabel object interprets the input command.
	 */
	private final Map<String, Command> commandsTable;

	/**
	 * Class attribute String object contains the workspace directory.
	 */
	private String dir;

	/**
	 * Status of execution. 1 no errors. -1 an error occured.
	 */
	private int status;

	/**
	 * Allocates the space for the commands and commandsTable attributes.
	 * 
	 * @param command
	 *            a CommandReader object.
	 */
	public SNA4Slack(final IntCommandReader command) {
		this.commands = command;
		this.commandsTable = new HashMap<String, Command>();
	}

	/**
	 * Runs the command specified in the input command.
	 */
	public final void run() {
		this.loadDefaultSet();
		initializeZip();
		if (this.checkStatus()) {
			initializeParser(USERSFILE);
			initializeUsers();
			initializeParser(CHANNELSFILE);
			initializeChannels();
			if (this.commandsTable.get(this.commands.getInputCommand()) != null) {
				this.commandsTable.get(this.commands.getInputCommand()).runCommand();
			}
		} else {
			System.out.println("WRONG WORKSPACE. Please type a valid one!");
			Helper.stampaLogo();
			Helper.stampaHelp();
		}
	}

	/**
	 * Initializes the dir and workspaceZip attribute.
	 */
	private void initializeZip() {
		dir = commands.getWorkspaceDir();
		final IntZip workspaceZip = new WorkspaceZip(dir);
		final int zipStatus = workspaceZip.extractZip();
		dir = workspaceZip.getDirPath();
		this.status = zipStatus;
	}

	/**
	 * Initializes the jsonParser attribute.
	 * 
	 * @param filetype
	 *            a String indicating the path of the JSON file.
	 */
	private void initializeParser(final String filetype) {
		jsonParser = new JsonFileParser();
		jsonParser.initializeFromJSONFileDir(dir.concat(filetype));
	}

	/**
	 * Initializes the users attribute.
	 * 
	 */
	private void initializeUsers() {
		final IntJsonArray array = jsonParser.getArray();
		users = new Users();
		users.initializeFromJsonArray(array);
	}

	/**
	 * Initializes the channels attribute.
	 * 
	 */
	private void initializeChannels() {
		final IntJsonArray array = jsonParser.getArray();
		channels = new Channels(this.users);
		channels.initializeFromJsonArray(array);
	}

	/**
	 * Prints users list.
	 */
	protected void printUsers() {
		this.users.printList();
	}

	/**
	 * Prints channels list.
	 */
	protected void printChannels() {
		this.channels.printList();
	}

	/**
	 * Prints channels list with channel's members.
	 */
	protected void printChannelsDetailed() {
		this.channels.printListDetailed();
	}

	/**
	 * Prints all members of a Channel.
	 */
	protected void printChannelMembers() {
		final String channel = commands.getChannelName();
		if (this.channels.checkChannel(channel)) {
			this.channels.printUsersOfChannel(channel);
		} else {
			System.out.println("NO CHANNEL BY THIS NAME");
			Helper.stampaLogo();
			Helper.stampaHelp();
			this.status = ERROR;
		}
	}

	/**
	 * Initialize mentions.
	 * 
	 * @param weighed
	 *            boolean value.
	 */
	protected void initializeMentions(final boolean weighed) {
		this.mentions = new Mentions(this.users, weighed);
		if (this.commands.isChannelSet()) {
			this.initializeMentionsFromChannel();
		} else {
			this.initializeMentionsFromWorkspace();
		}
	}

	/**
	 * Initialize mentions from specific channel got from commands.
	 */
	private void initializeMentionsFromChannel() {
		final String channel = this.commands.getChannelName();
		if (this.checkChannel()) {
			final IntJFileScanner scanner = new JFileScanner(dir.concat("/" + channel));
			jsonParser = new JsonFileParser();
			for (final File file : scanner.getList()) {
				final String path = file.getAbsolutePath();
				if (file.exists()) {
					jsonParser.initializeFromJSONFileDir(path);
					mentions.initializeFromJsonArray(jsonParser.getArray(), channels.getChannel(channel));
				}
			}
		} else {
			System.out.println("NONE CHANNEL BY THIS NAME. Please retry with valid channel name");
			Helper.stampaLogo();
			Helper.stampaHelp();
			this.status = ERROR;
		}
	}

	/**
	 * Initialize mentions from workspace.
	 */
	private void initializeMentionsFromWorkspace() {
		for (final String channel : channels.getAllChannelsName()) {
			final IntJFileScanner scanner = new JFileScanner(dir.concat("/" + channel));
			jsonParser = new JsonFileParser();
			for (final File file : scanner.getList()) {
				final String path = file.getAbsolutePath();
				if (file.exists()) {
					jsonParser.initializeFromJSONFileDir(path);
					mentions.initializeFromJsonArray(jsonParser.getArray(), channels.getChannel(channel));
				}
			}
		}
	}

	/**
	 * Check if the channel's name from commands is an actual channel from the
	 * workspace.
	 * 
	 * @return a boolean value.
	 */
	private boolean checkChannel() {
		return this.channels.checkChannel(this.commands.getChannelName());
	}

	/**
	 * Print mentions list.
	 */
	protected void printMentions() {
		if (this.checkStatus()) {
			this.mentions.printList();
		}
	}

	/**
	 * Prints every mention from certain User.
	 * 
	 */
	protected void printSenderMentions() {
		final String sender = this.commands.getUsername();
		if (this.users.checkUser(sender)) {
			this.mentions.printSenderMentions(sender);
		} else {
			System.out.println("NONE USERS BY THIS NAME IN THE WORKSPACE. Please type a valid one!");
			Helper.stampaLogo();
			Helper.stampaHelp();
		}
	}

	/**
	 * Prints every mention to certain User.
	 */
	protected void printReceiverMentions() {
		final String sender = this.commands.getUsername();
		if (this.users.checkUser(sender)) {
			this.mentions.printReceiverMentions(sender);
		} else {
			System.out.println("NONE USERS BY THIS NAME IN THE WORKSPACE. Please type a valid one!");
			Helper.stampaLogo();
			Helper.stampaHelp();
		}
	}

	/**
	 * Checks the status of execution.
	 * 
	 * @return a boolean type.
	 */
	private boolean checkStatus() {
		return this.status == 1;
	}

	/**
	 * Loads all the needed interpretations for the possibles input commands.
	 */
	private void loadDefaultSet() {
		this.commandsTable.put("-m", new Command() {
			@Override
			public void runCommand() {
				printUsers();
			}
		});
		this.commandsTable.put("-c", new Command() {
			@Override
			public void runCommand() {
				printChannels();
			}
		});
		this.commandsTable.put("-mc", new Command() {
			@Override
			public void runCommand() {
				printChannelsDetailed();
			}
		});
		this.commandsTable.put("-cm", new Command() {
			@Override
			public void runCommand() {
				printChannelMembers();
			}
		});
		this.commandsTable.put("@m", new Command() {
			@Override
			public void runCommand() {
				initializeMentions(NOTWEIGHED);
				printMentions();
			}
		});
		this.commandsTable.put("@mw", new Command() {
			@Override
			public void runCommand() {
				initializeMentions(WEIGHED);
				printMentions();
			}
		});
		this.commandsTable.put("@mf", new Command() {
			@Override
			public void runCommand() {
				initializeMentions(NOTWEIGHED);
				printSenderMentions();
			}
		});
		this.commandsTable.put("@mfw", new Command() {
			@Override
			public void runCommand() {
				initializeMentions(WEIGHED);
				printSenderMentions();
			}
		});
		this.commandsTable.put("@mt", new Command() {
			@Override
			public void runCommand() {
				initializeMentions(NOTWEIGHED);
				printReceiverMentions();
			}
		});
		this.commandsTable.put("@mtw", new Command() {
			@Override
			public void runCommand() {
				initializeMentions(WEIGHED);
				printReceiverMentions();
			}
		});this.commandsTable.put("help", new Command() {
			@Override
			public void runCommand() {
				Helper.stampaLogo();
				Helper.stampaHelp();
			}
		});
	}

	/**
	 * Interface for the runCommand function.
	 *
	 */
	private interface Command {

		/**
		 * Class member function to manage.
		 * 
		 * @throws Exception
		 *             standard exception.
		 */
		void runCommand();
	}
}
