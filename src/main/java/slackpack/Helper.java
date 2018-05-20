package slackpack;
/**
* <h1>Helper</h1>
* The Help interface contains two methods that print:
* -All available commands.
* -An ASCII art of Sna4Slack.
*
*/
public interface Helper {
	/**
	 * This method prints all the available commands.
	 */
	static void stampaHelp() {
		System.out.println("PLEASE TYPE COMMANDS LIKE THIS: <directory> <command>\n");
		System.out.print("ns/path/file.zip\tPercorso locale del file, deve essere il primo parametro\n");
		System.out.print("-m\t\t\tVisualizza tutti i membri del workspace\n");
		System.out.print("-c\t\t\tVisualizza tutti i canali del workspace\n");
		System.out.print("-mc\t\t\tVisualizza tutti i canali e i rispettivi membri\n");
		System.out.print("-cm <canale>\t\tVisualizza tutti i membri di <canale>\n");
		System.out.print("@m\t\t\tVisualizza tutti i mention del workspace\n");
		System.out.print("@m  <canale>\t\tVisualizza tutti i mention del canale\n");
		System.out.print("@mf <membro>\t\tVisualizza tutti i mention del workspace fatti da un membro\n");
		System.out.print("@mf <membro> <canale>\tVisualizza tutti i mention del canale fatti da un membro\n");
		System.out.print("@mt <membro>\t\tVisualizza tutti i mention del workspace arrivati a un membro\n");
		System.out.print("@mt <membro> <canale>\tVisualizza tutti i mention del canale arrivati a un membro\n");
	    System.out.print("@mw\t\t\tVisualizza tutti i mention pesati del workspace\n");
	    System.out.print("@mw <canale>\t\tVisualizza tutti i mention pesati del canale\n");
	    System.out.print("@mfw <membro>\t\tVisualizza tutti i mention pesati del workspace fatti da un membro\n");
	    System.out.print("@mfw <membro> <canale>\tVisualizza tutti i mention pesati del canale fatti da un membro\n");
	    System.out.print("@mtw <membro>\t\tVisualizza tutti i mention pesati del workspace arrivati a un membro\n");
	    System.out.print("@mtw <membro> <canale>\tVisualizza tutti i mention pesati del canale arrivati a un membro\n");
		System.out.print("help\t\t\tVisualizza lista comandi\n");
		System.out.print("ns/path/file.zip -cm dijkstra (Es. per visualizzare i membri del gruppo dijkstra)\n");
		System.out.print("\n<N.B. Docker: Creare una directory virtuale all'interno del conteiner>\n");
		System.out.print("Windows:\tdocker run -v /c:/ns softenginfuniba/dijkstra ns/path/file.zip\n");
		System.out.print("Linux:\t\tdocker run -v /:/ns softenginfuniba/dijkstra ns//path/file.zip\n");
	}
	/**
	 * This method prints an ASCII art.
	 */
	static void stampaLogo() {
		System.out.print("                      ___       _               _ \n");
		System.out.print("                     /   |     | |             | | \n");
		System.out.print(" ___  _ __    __ _  / /| | ___ | |  __ _   ___ | | __ \n");
		System.out.print("/ __|| '_ \\  / _` |/ /_| |/ __|| | / _` | / __|| |/ / \n");
		System.out.print("\\__ \\| | | || (_| |\\___  |\\__ \\| || (_| || (__ |   < \n");
		System.out.print("|___/|_| |_| \\__,_|    |_/|___/|_| \\__,_| \\___||_|\\_\\ \n");
		System.out.print("Gruppo Dijkstra\n\n");
	}
}

