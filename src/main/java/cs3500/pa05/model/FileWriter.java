package cs3500.pa05.model;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa05.model.json.JournalJson;
import cs3500.pa05.model.json.JsonUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * A class for writing a BulletJournal object to a File in JSON form.
 */
public class FileWriter {

  /**
   * Writes the given BulletJournal to a file.
   *
   * @param toWrite     - the BulletJournal we want to write to a file.
   * @param desiredPath - the path we want to write it to.
   */
  public void writeJournalToFile(BulletJournal toWrite, File desiredPath) {

    if (!desiredPath.toString().endsWith(".bujo")) {
      throw new IllegalArgumentException("Must write to a .bujo file, given " + desiredPath);
    } else {
      try {
        desiredPath.createNewFile();
        JournalJson journalAsJson = toWrite.toJson();
        JsonNode journalAsNode = JsonUtils.serializeRecord(journalAsJson);
        String nodeText = journalAsNode.toString();
        byte[] nodeTextData = nodeText.getBytes();
        FileOutputStream stream = new FileOutputStream(desiredPath, false);
        stream.write(nodeTextData);
      } catch (IOException e) {
        throw new RuntimeException("Could not write file due to error: " + e);
      }
    }
  }
}
