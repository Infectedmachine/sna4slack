/**
* <p>Title: sna4slack</p>
* <p>Description: Analizzatore file zip di esportazione Slack Workspace</p>
* <p>Copyright: None    (c)2018</p>
* <p>Company: Dipartimento di Informatica, Universit� degli studi di Bari</p>
* <p>Class description: Classe AppMain
*
* @author Dijkstra Group
* @version 0.1
*/

package it.uniba.main;

import slackpack.CommandLineReader;
import slackpack.Helper;
import slackpack.SNA4Slack;

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

		CommandLineReader command = new CommandLineReader();
		try {
			command.fillCommandsSetFromArgs(args);
		} catch (Exception e) {
			System.out.println(e);
			Helper.stampaLogo();
			Helper.stampaHelp();
		}
		SNA4Slack sna4slack = new SNA4Slack(command);
		try {
			sna4slack.run();
		} catch (Exception e) {
			System.out.println(e);
			Helper.stampaLogo();
			Helper.stampaHelp();
		}
	}

}
