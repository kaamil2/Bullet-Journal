package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.json.EventJson;
import org.junit.jupiter.api.Test;

/**
 * Tests the event class
 */
public class EventTest {
  SimpleTime onepm = new SimpleTime(1, 00, false, DayOfWeek.MONDAY);
  SimpleTime twopm = new SimpleTime(2, 00, false, DayOfWeek.MONDAY);

  EventTime eventTime = new EventTime(onepm, twopm);
  Category studying = new Category("studying");

  Event exampleEvent = new Event("Final Exam", "For CS3500", DayOfWeek.MONDAY, studying, eventTime);

  /**
   * Tests the getCategory method
   */
  @Test
  public void testGetCategory() {
    assertEquals(studying, exampleEvent.getCategory());
  }

  /**
   * test the getDay method
   */
  @Test
  public void testGetDay() {
    assertEquals(DayOfWeek.MONDAY, exampleEvent.getDay());
  }

  /**
   * Tests the getDescription method
   */
  @Test
  public void testGetDescription() {
    assertEquals("For CS3500", exampleEvent.getDescription());
  }

  /**
   * Tests the getName method
   */
  @Test
  public void testGetName() {
    assertEquals("Final Exam", exampleEvent.getName());
  }

  /**
   * Tests the getTime method
   */
  @Test
  public void testGetTime() {
    assertEquals(eventTime, exampleEvent.getTime());
  }

  /**
   * Tests the toJson method
   */
  @Test
  public void testToJson() {
    EventJson resultRecord = exampleEvent.toJson();
    assertEquals("Final Exam", resultRecord.eventName());
    assertEquals("For CS3500", resultRecord.description());
    assertEquals("MONDAY, 1:00 PM", resultRecord.startTime());
    assertEquals("MONDAY, 2:00 PM", resultRecord.endTime());
  }

  /**
   * Tests the toString method
   */
  @Test
  public void testToString() {
    Event event = new Event("NAME", "DESCRIPTION", DayOfWeek.MONDAY,
        new Category("CATEGORY"),
        new EventTime("MONDAY, 1:00 PM", "MONDAY, 2:00 PM"));
    String expectedWithTitle = "NAME\n"
        + "DESCRIPTION\n"
        + "MONDAY\n"
        + "Category: CATEGORY\n"
        + "MONDAY, 1:00 PM -\n"
        + " MONDAY, 2:00 PM";
    String expectedNoTitle = "DESCRIPTION\n"
        + "MONDAY\n"
        + "Category: CATEGORY\n"
        + "MONDAY, 1:00 PM -\n"
        + " MONDAY, 2:00 PM";

    assertEquals(expectedWithTitle, event.toString(true));
    assertEquals(expectedNoTitle, event.toString(false));
  }
}
