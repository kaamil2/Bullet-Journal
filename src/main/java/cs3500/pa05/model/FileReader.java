package cs3500.pa05.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.json.JournalJson;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Responsible for reading from a File containing a JSON object that can be used to create
 * a new Bullet Journal.
 */
public class FileReader {
  /**
   * The main objective of this class. Writes given BulletJournal to
   *
   * @param file - the file from which we are abstracting a JSON object we can use to create
   *             a BulletJournal
   *
   * @return BulletJournal created from the JSON object in the given file
   */
  public BulletJournal convertFileToWeek(File file) {
    try {
      FileInputStream fromFile = new FileInputStream(file);
      ObjectMapper mapper = new ObjectMapper();
      JsonParser parser = mapper.getFactory().createParser(fromFile);
      JournalJson jsonFromFile = parser.readValueAs(JournalJson.class);
      return jsonFromFile.toJournal();
    } catch (IOException e) {
      throw new RuntimeException("Couldn't read from file due to error: " + e);
    }
  }
}
