package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

/**
 * tests Category class
 */
public class CategoryTest {

  /**
   * Tests the getName method
   */
  @Test
  public void testGetName() {
    Category studying = new Category("Studying");
    assertEquals("Studying", studying.getName());
  }

  /**
   * tests our overridden .equals for Categories - compares just on names
   */
  @Test
  public void testEquals() {
    Category studying = new Category("Studying");
    Category alsoStudying = new Category("Studying");
    Category notStudying = new Category("Not Studying");

    assertEquals(studying, studying);
    assertEquals(studying, alsoStudying);

    assertNotEquals(studying, notStudying);
    assertNotEquals(1, studying);
  }
}
