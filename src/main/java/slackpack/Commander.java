package slackpack;

public class Commander {

	String command;

	public Commander() {
		command = "";
	}

	public Commander(String[] InputCommand) {

		switch (InputCommand[1]) {
		case "-m": //stampa tutti i nomi dei membri del workspace
			String zipdir = (InputCommand[0]);
			ExtractZip Z = new ExtractZip(zipdir);
			String dir = Z.dirsource;
			dir=dir.concat("/users.json");
			ArrayMember Members = new ArrayMember(dir);
			Members.printArray();
			break;
		case "-c": //stampa tutti i nomi dei canali
			zipdir = (InputCommand[0]);
			Z = new ExtractZip(zipdir);
			dir = Z.dirsource;
			dir=dir.concat("/users.json");
			Members = new ArrayMember(dir);
			dir=dir.replace("/users.json","/channels.json");
			ArrayChannel Channels = new ArrayChannel(dir,Members);
			for (Object obj : Channels.channels) {  //stampa solo i nomi dei canali, leggera modifica del metodo printArray
				Channel cobj = (Channel) obj; 
				System.out.println(cobj.getName());
			}
			break;
		}
	}
}
