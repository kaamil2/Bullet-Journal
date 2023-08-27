package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests the DayOfWeek enum (methods)
 */
public class DayOfWeekTest {

  /**
   * Tests the dayFromString method
   */
  @Test
  public void testDayFromString() {
    assertEquals(DayOfWeek.MONDAY, DayOfWeek.dayFromString("monday"));
    assertEquals(DayOfWeek.MONDAY, DayOfWeek.dayFromString("MONDAY"));
    assertEquals(DayOfWeek.MONDAY, DayOfWeek.dayFromString("mOnDAy"));

    assertEquals(DayOfWeek.TUESDAY, DayOfWeek.dayFromString("TUESDAY"));
    assertEquals(DayOfWeek.WEDNESDAY, DayOfWeek.dayFromString("WEDNESDAY"));
    assertEquals(DayOfWeek.THURSDAY, DayOfWeek.dayFromString("THURSDAY"));
    assertEquals(DayOfWeek.FRIDAY, DayOfWeek.dayFromString("FRIDAY"));
    assertEquals(DayOfWeek.SATURDAY, DayOfWeek.dayFromString("SATURDAY"));
    assertEquals(DayOfWeek.SUNDAY, DayOfWeek.dayFromString("SUNDAY"));

    assertThrows(
        IllegalArgumentException.class,
        () -> DayOfWeek.dayFromString("hello!"));

    assertThrows(
        IllegalArgumentException.class,
        () -> DayOfWeek.dayFromString("mondayyyy"));
  }
}
