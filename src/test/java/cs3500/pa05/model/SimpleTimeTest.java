package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * tests SimpleTime
 */
public class SimpleTimeTest {
  SimpleTime onethirtypm = new SimpleTime(1, 30, false, DayOfWeek.MONDAY);
  SimpleTime twelvefiftyam = new SimpleTime(12, 50, true, DayOfWeek.FRIDAY);

  /**
   * Tests the validity of SimpleTimes
   */
  @Test
  public void testValidTime() {
    assertDoesNotThrow(() -> new SimpleTime(1, 30, true, DayOfWeek.SATURDAY));
    assertDoesNotThrow(() -> new SimpleTime(10, 57, false, DayOfWeek.TUESDAY));

    assertThrows(
        IllegalArgumentException.class,
        () -> new SimpleTime(13, 30, false, DayOfWeek.MONDAY));
    assertThrows(
        IllegalArgumentException.class,
        () -> new SimpleTime(0, 30, false, DayOfWeek.MONDAY));

    assertThrows(
        IllegalArgumentException.class,
        () -> new SimpleTime(5, -1, false, DayOfWeek.MONDAY));
    assertThrows(
        IllegalArgumentException.class,
        () -> new SimpleTime(5, 60, false, DayOfWeek.MONDAY));
  }

  /**
   * Tests the getHour method
   */
  @Test
  public void testGetHour() {
    assertEquals(1, onethirtypm.getHour());
    assertEquals(12, twelvefiftyam.getHour());
  }

  /**
   * Tests the getMin method
   */
  @Test
  public void testGetMin() {
    assertEquals(30, onethirtypm.getMin());
    assertEquals(50, twelvefiftyam.getMin());
  }

  /**
   * Tests the getAM method
   */
  @Test
  public void testgetam() {
    assertFalse(onethirtypm.getAm());
    assertTrue(twelvefiftyam.getAm());
  }

  /**
   * Tests the getDya method
   */
  @Test
  public void testgetday() {
    assertEquals(DayOfWeek.MONDAY, onethirtypm.getDay());
    assertEquals(DayOfWeek.FRIDAY, twelvefiftyam.getDay());
  }

  /**
   * Tests the compare method
   */
  @Test
  public void testCompare() {
    // day comes before
    assertTrue(onethirtypm.compareTo(twelvefiftyam));

    // day comes after
    assertFalse(twelvefiftyam.compareTo(onethirtypm));

    SimpleTime seventhirtyam = new SimpleTime(7, 30, true, DayOfWeek.FRIDAY);
    SimpleTime seventhirtypm = new SimpleTime(7, 30, false, DayOfWeek.FRIDAY);

    // AM before PM
    assertTrue(seventhirtyam.compareTo(seventhirtypm));

    // PM before AM
    assertFalse(seventhirtypm.compareTo(seventhirtyam));

    // Hour before other
    SimpleTime eighttwentyam = new SimpleTime(8, 20, true, DayOfWeek.FRIDAY);
    assertTrue(seventhirtyam.compareTo(eighttwentyam));

    // hour after other
    assertFalse(eighttwentyam.compareTo(seventhirtyam));

    // minute before / equal to other
    assertTrue(onethirtypm.compareTo(onethirtypm));

    // minute after other
    SimpleTime onethirtyonepm = new SimpleTime(1, 31, false, DayOfWeek.MONDAY);
    assertFalse(onethirtyonepm.compareTo(onethirtypm));
  }

  /**
   * Tests the tostring method
   */
  @Test
  public void testToString() {
    assertEquals("MONDAY, 1:30 PM", onethirtypm.toString());
    assertEquals("FRIDAY, 12:50 AM", twelvefiftyam.toString());
  }
}
