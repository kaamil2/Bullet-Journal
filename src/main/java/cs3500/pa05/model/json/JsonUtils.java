package cs3500.pa05.model.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utility functions for JSON use
 */
public class JsonUtils {

  /**
   * Turns record into JSON node
   *
   * @param record - the record we want to turn into JSON Node
   * @return - record as a JSON Node
   * @throws IllegalArgumentException - if record could not be serialized
   */
  public static JsonNode serializeRecord(Record record) throws IllegalArgumentException {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.convertValue(record, JsonNode.class);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Given record cannot be serialized, " + e);
    }
  }
}
