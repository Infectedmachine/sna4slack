/**
* <p>Title: sna4slack</p>
* <p>Description: Analizzatore file zip di esportazione Slack Workspace.</p>
* <p>Copyright: None(c)2018</p>
* <p>Company: Dipartimento di Informatica, Università degli studi di Bari.</p>
* <p>Class description: Classe AppMain.
*
* @author Dijkstra Group
* @version 0.1
*/

package it.uniba.main;

import slackpack.CommandLineReader;
import slackpack.Helper;
import slackpack.SNA4Slack;

/**
 * The main class.
 */
public final class AppMain {

	/**
	 * Private constructor.
	 */
	private AppMain() {

	}

	/**
	 * This is the main entry of the application.
	 *
	 * @param args The command-line arguments
	 *
	 */
	public static void main(final String[] args) {

		CommandLineReader command = new CommandLineReader();
		SNA4Slack sna4slack = new SNA4Slack(command);
		try {
			command.fillCommandsSetFromArgs(args);
			sna4slack.run();
		} catch (Exception e) {
			System.out.println(e);
			Helper.stampaLogo();
			Helper.stampaHelp();
		}
	}
}

