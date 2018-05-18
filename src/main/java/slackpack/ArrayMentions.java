package slackpack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

public class ArrayMentions {
	private ArrayList<Mention> mentions;

	public ArrayMentions() {
		this.setArray(new ArrayList<Mention>());
	}

	public final void fillMentionsFromFileList(ArrayList<File> channelfiles) throws IOException {
		JsonFileParser slackmessages = new JsonFileParser();
		for (File file : channelfiles) {
			slackmessages.fillContentsFromJSONFileDir(file.getCanonicalPath());
			for (Object obj : slackmessages.getArray()) {
				JSONObject json = (JSONObject) obj;
				Mention mention = new Mention();
				mention.fillMentionFromJSONObject(json);
					if(!mention.isEmptyTO()) {
						this.addByMerging(mention);
					}
				}
			}
	}

	public final void add(Mention mention) {
		this.getArray().add(mention);
	}

	public void addByMerging(Mention mentionToMerge) {

		boolean flag = false; // FLAG FOR 'TO'
		boolean FROMflag = false; // FLAG FOR 'FROM'

		ArrayList<String> arrayToAdd = new ArrayList<String>();

		if (this.getArray().isEmpty()) {
			this.getArray().add(mentionToMerge);
		} else {
			for (Mention mention : this.getArray()) {
				if (mention.getFROM().equals(mentionToMerge.getFROM()) && FROMflag == false) {
					FROMflag = true;
					for (String i : mentionToMerge.getTO()) {
						for (String j : mention.getTO()) {
							if (i.equals(j)) {
								flag = true;
								mention.getWeight().put(j, (mention.getWeight().get(j))+1);
							}
						}
						if (flag == false) {
							arrayToAdd.add(i);
						}
						flag = false;
					}
					if (!arrayToAdd.isEmpty()) {
						for (String s : arrayToAdd) {
							mention.addTO(s);
							mention.getWeight().put(s, (mentionToMerge.getWeight().get(s)));
						}
					}
				}
			}
			if (FROMflag == false) {
				getArray().add(mentionToMerge);
			}
		}
	}

	public void mergeArray(ArrayMentions marr) {
		for (Mention mention : marr.getArray()) {
			addByMerging(mention);
		}
	}

	public final void setArray(ArrayList<Mention> mentions) {
		this.mentions = mentions;
	}

	public ArrayList<Mention> getArray() {
		return this.mentions;
	}
}
