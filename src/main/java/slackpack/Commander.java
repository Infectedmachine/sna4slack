package slackpack;


public class Commander {
	
	String command;
	
	public Commander() {command = "";};
	public Commander(String[] InputCommand) {
			
			switch (InputCommand[1]) {
			case "-m":
				ArrayMember Members = new ArrayMember(InputCommand[0]);
				Members.printArray();
				break;
			//case ""
			}	
	}
}
