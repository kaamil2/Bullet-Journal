package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.json.EventJson;
import org.junit.jupiter.api.Test;

/**
 * Tests the EventJsons ability to convert into an Event object
 */
public class EventJsonTest {
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

  /**
   * Tests the toEvent method
   */
  @Test
  public void testToEvent() {
    Event recordAsEvent = testRecord.toEvent(DayOfWeek.MONDAY);
    assertEquals(DayOfWeek.MONDAY, recordAsEvent.getDay());
    assertEquals("It's not a surprise", recordAsEvent.getDescription());
    assertEquals(7, recordAsEvent.getTime().getStartTime().getHour());
    assertEquals(10, recordAsEvent.getTime().getEndTime().getHour());


    Event recordAsEventTwo = testRecordTwo.toEvent(DayOfWeek.WEDNESDAY);
    assertEquals(DayOfWeek.WEDNESDAY, recordAsEventTwo.getDay());
    assertEquals("For some class", recordAsEventTwo.getDescription());
    assertEquals(4, recordAsEventTwo.getTime().getStartTime().getHour());
    assertEquals(5, recordAsEventTwo.getTime().getEndTime().getHour());
  }
}
