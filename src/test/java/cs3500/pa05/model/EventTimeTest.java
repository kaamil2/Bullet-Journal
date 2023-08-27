package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests the EventTime class
 */
public class EventTimeTest {
  SimpleTime onethirtypm = new SimpleTime(1, 30, false, DayOfWeek.FRIDAY);
  SimpleTime onethirtyonepm = new SimpleTime(1, 31, false, DayOfWeek.FRIDAY);
  SimpleTime sevenam = new SimpleTime(7, 00, true, DayOfWeek.SATURDAY);

  EventTime testEventDifferentDay = new EventTime(onethirtypm, sevenam);
  EventTime testEventSameDay = new EventTime(onethirtypm, onethirtyonepm);

  /**
   * Ensures exception is thrown when times don't come before each other
   */
  @Test
  public void testValidEventTime() {
    assertDoesNotThrow(() -> new EventTime(onethirtypm, onethirtyonepm));
    assertDoesNotThrow(() -> new EventTime(onethirtypm, onethirtypm));

    assertThrows(
        IllegalArgumentException.class,
        () -> new EventTime(onethirtyonepm, onethirtypm));
  }

  /**
   * Tests the toString method
   */
  @Test
  public void testToString() {
    assertEquals("FRIDAY, 1:30 PM -\n FRIDAY, 1:31 PM", testEventSameDay.toString());
    assertEquals("FRIDAY, 1:30 PM -\n SATURDAY, 7:00 AM", testEventDifferentDay.toString());
  }

  /**
   * tests the getStartTime method
   */
  @Test
  public void testGetStartTime() {
    assertEquals(onethirtypm, testEventSameDay.getStartTime());
  }

  /**
   * Tests the getEndTime method
   */
  @Test
  public void testGetEndTime() {
    assertEquals(onethirtyonepm, testEventSameDay.getEndTime());
  }

  /**
   * Tests building an EventTime from giving it two strings.
   */
  @Test
  public void testBuildingFromString() {
    EventTime exampleOne = new EventTime("MONDAY, 7:30 AM", "WEDNESDAY, 8:15 PM");
    assertEquals(7, exampleOne.getStartTime().getHour());
    assertEquals(30, exampleOne.getStartTime().getMin());
    assertEquals(DayOfWeek.MONDAY, exampleOne.getStartTime().getDay());
    assertEquals(true, exampleOne.getStartTime().getAm());

    assertEquals(8, exampleOne.getEndTime().getHour());
    assertEquals(15, exampleOne.getEndTime().getMin());
    assertEquals(DayOfWeek.WEDNESDAY, exampleOne.getEndTime().getDay());
    assertEquals(false, exampleOne.getEndTime().getAm());

    assertThrows(
        IllegalArgumentException.class,
        () -> new EventTime("WEDNESDAY, 7:30 PM", "MONDAY, 8:30 AM"));

    assertThrows(
        IllegalArgumentException.class,
        () -> new EventTime("WEDNESDAY,7:30AM", "THURSDAY,8:30 PM"));
  }
}
