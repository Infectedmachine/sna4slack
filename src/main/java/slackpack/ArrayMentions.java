package slackpack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
/**
 * Creates and manage an array of objects mention.
 * @author aleningi
 *
 */
public class ArrayMentions {
	/**
	 * Contains all of the mentions needed.
	 */
	private ArrayList<Mention> mentions;

	/**
	 * Allocates the space for the class attribute.
	 */
	public ArrayMentions() {
		this.setArray(new ArrayList<Mention>());
	}

	/**
	 * Finds and adds to the class attribute all of the mentions contained in
	 * the given file array.
	 *
	 * @param channelfiles array of files containing the mentions.
	 * @throws IOException standard IOException
	 */
	public final void fillMentionsFromFileList(final ArrayList<File> channelfiles) throws IOException {
		JsonFileParser slackmessages = new JsonFileParser();
		for (File file : channelfiles) {
			slackmessages.fillContentsFromJSONFileDir(file.getCanonicalPath());
			for (Object obj : slackmessages.getArray()) {
				JSONObject json = (JSONObject) obj;
				Mention mention = new Mention();
				mention.fillMentionFromJSONObject(json);
					if (!mention.isEmptyTO()) {
						this.addByMerging(mention);
					}
				}
			}
	}

	/**
	 * Adds a mention to the mentions array.
	 *
	 * @param mention object mention.
	 */
	public final void add(final Mention mention) {
		this.getArray().add(mention);
	}

	/**
	 * Merges the given mention to the array of mentions.
	 *
	 * @param mentionToMerge mention to merge.
	 */
	public void addByMerging(final Mention mentionToMerge) {

		boolean flag = false; // FLAG FOR 'TO'
		boolean fromFlag = false; // FLAG FOR 'FROM'

		ArrayList<String> arrayToAdd = new ArrayList<String>();

		if (this.getArray().isEmpty()) {
			this.getArray().add(mentionToMerge);
		} else {
			for (Mention mention : this.getArray()) {
				if (mention.getFROM().equals(mentionToMerge.getFROM()) && !fromFlag) {
					fromFlag = true;
					for (String i : mentionToMerge.getTO()) {
						for (String j : mention.getTO()) {
							if (i.equals(j)) {
								flag = true;
								mention.getWeight().put(j, (mention.getWeight().get(j)) + 1);
							}
						}
						if (!flag) {
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
			if (!fromFlag) {
				getArray().add(mentionToMerge);
			}
		}
	}

	/**
	 * Merges the given array to the class attribute array.
	 *
	 * @param marr  array to merge.
	 */
	public void mergeArray(final ArrayMentions marr) {
		for (Mention mention : marr.getArray()) {
			addByMerging(mention);
		}
	}

	/**
	 * Sets the class attribute array to the given array.
	 *
	 * @param marr given array.
	 */
	public final void setArray(final ArrayList<Mention> marr) {
		this.mentions = marr;
	}

	/**
	 * Returns the class attribute.
	 * @return mentions.
	 */
	public ArrayList<Mention> getArray() {
		return this.mentions;
	}
}
