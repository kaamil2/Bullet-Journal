package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa05.model.json.TaskJson;
import org.junit.jupiter.api.Test;

/**
 * Tests the TaskJson object
 */
public class TaskJsonTest {

  TaskJson testRecord = new TaskJson("Test Task",
      "A task for testing",
      "Testing Tasks",
      "true");

  TaskJson testRecordTwo = new TaskJson("Another test task",
      "Shows how fun testing is",
      "Testing Tasks",
      "false");

  /**
   * Tests the toTask method
   */
  @Test
  public void testToTask() {
    Task fromRecordOne = testRecord.toTask(DayOfWeek.MONDAY);
    assertTrue(fromRecordOne.getComplete());
    assertEquals(DayOfWeek.MONDAY, fromRecordOne.getDay());
    assertEquals("A task for testing", fromRecordOne.getDescription());

    Task fromRecordTwo = testRecordTwo.toTask(DayOfWeek.WEDNESDAY);
    assertFalse(fromRecordTwo.getComplete());
    assertEquals(DayOfWeek.WEDNESDAY, fromRecordTwo.getDay());
    assertEquals("Shows how fun testing is", fromRecordTwo.getDescription());
  }
}
