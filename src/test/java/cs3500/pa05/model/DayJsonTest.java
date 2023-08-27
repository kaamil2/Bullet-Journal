package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.json.DayJson;
import cs3500.pa05.model.json.EventJson;
import cs3500.pa05.model.json.TaskJson;
import org.junit.jupiter.api.Test;

/**
 * Tests the DayJson record and the toDay method
 */
public class DayJsonTest {
  EventJson testRecord = new EventJson("Birthday Party",
      "It's not a surprise",
      "Parties",
      "MONDAY, 7:30 PM",
      "MONDAY, 10:45 PM");

  EventJson testRecordTwo = new EventJson("Final Exam",
      "For some class",
      "School",
      "WEDNESDAY, 4:00 PM",
      "WEDNESDAY, 5:00 PM");

  EventJson[] events = new EventJson[] {testRecord, testRecordTwo};

  TaskJson testTaskRecord = new TaskJson("Test Task",
      "A task for testing",
      "Testing Tasks",
      "true");

  TaskJson testTaskRecordTwo = new TaskJson("Another test task",
      "Shows how fun testing is",
      "Testing Tasks",
      "false");

  TaskJson[] tasks = new TaskJson[] {testTaskRecord, testTaskRecordTwo};

  DayJson testDay = new DayJson("monday", tasks, events);

  /**
   * Ensures the toDay method's basics (day name, size of event/task list, etc.) are accurate
   */
  @Test
  public void testToDay() {
    JournalDay result = testDay.toDay();

    assertEquals(4, result.getScheduleItems().getItemList().size());
    assertEquals(2, result.countOfEvents());
    assertEquals(2, result.countOfTasks());

    assertEquals(DayOfWeek.MONDAY, result.getDay());
  }
}
