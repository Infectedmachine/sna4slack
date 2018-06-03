/**
* <p>Title: sna4slack</p>
* <p>Description: Analizzatore file zip di esportazione Slack Workspace</p>
* <p>Copyright: None    (c)2018</p>
* <p>Company: Dipartimento di Informatica, Università degli studi di Bari</p>
* <p>Class description: Classe AppMain
*
* @author Dijkstra Group
* @version 0.1
*/

package it.uniba.main;

import snaforslack.main.CommandReader;
import snaforslack.main.SNA4Slack;
import snaforslack.interfaces.main.IntCommandReader;
import snaforslack.interfaces.main.IntSNA4Slack;

/**
 * The main class for the project. It must be customized to meet the project
 * assignment specifications.
 *
 * <b>DO NOT RENAME</b>
 */
public final class AppMain {

	/**
	 * Private constructor. Change if needed.
	 */
	private AppMain() {

	}

	/**
	 * This is the main entry of the application.
	 *
	 * @param args
	 *            The command-line arguments.
	 */
	public static void main(final String[] args) {

		final IntCommandReader command = new CommandReader();
		final int valid = command.initializeFromArgs(args);
		if (valid > 0) {
			final IntSNA4Slack sna4slack = new SNA4Slack(command);
			sna4slack.run();
		}
	}
}
