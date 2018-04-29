package slackpack;

public interface Helper {
	  
		public static void stampaHelp() {
	        System.out.print("ns/path/file.zip\tPercorso locale del file, deve essere il primo parametro\n");
	        System.out.print("-m\t\t\tVisualizza tutti i membri del workspace\n");
	        System.out.print("-c\t\t\tVisualizza tutti i canali del workspace\n");
	        System.out.print("-mc\t\t\tVisualizza tutti i canali e i rispettivi membri\n");
	        System.out.print("-cm <nome_canale>\tVisualizza tutti i membri di <nome_canale>\n");			
	        System.out.print("help\t\t\tVisualizza lista comandi\n");
	        System.out.print("ns/path/file.zip -cm dijkstra (Esempio per visualizzare i membri del gruppo dijkstra)\n");
	        System.out.print("\n<N.B. Docker: Creare una directory virtuale all'interno del conteiner>\n");
	        System.out.print("Windows:\tdocker run -v /c:/ns softenginfuniba/dijkstra ns/path/file.zip\n");
	        System.out.print("Linux:\t\tdocker run -v /:/ns softenginfuniba/dijkstra ns//path/file.zip\n");
	    }
	 
	    public static void stampaLogo() {
	        System.out.print("                      ___       _               _ \n");
	        System.out.print("                     /   |     | |             | | \n");
	        System.out.print(" ___  _ __    __ _  / /| | ___ | |  __ _   ___ | | __ \n");
	        System.out.print("/ __|| '_ \\  / _` |/ /_| |/ __|| | / _` | / __|| |/ / \n");
	        System.out.print("\\__ \\| | | || (_| |\\___  |\\__ \\| || (_| || (__ |   < \n");
	        System.out.print("|___/|_| |_| \\__,_|    |_/|___/|_| \\__,_| \\___||_|\\_\\ \n");
	        System.out.print("Gruppo Dijkstra\n\n");
	    }
	 
	    
	 
}
