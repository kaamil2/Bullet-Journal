package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa05.model.json.JsonUtils;
import org.junit.jupiter.api.Test;

/**
 * Tests the JsonUtils
 */
public class JsonUtilsTest {

  MockNonJsonRecord mock = new MockNonJsonRecord("Mock");
  JsonUtils utils = new JsonUtils();

  /**
   * Tests the serialize record method
   */
  @Test
  public void testSerializeRecord() {
    assertThrows(
        IllegalArgumentException.class,
        () -> utils.serializeRecord(mock));
  }
}
