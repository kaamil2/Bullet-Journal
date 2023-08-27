package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.json.TaskJson;
import org.junit.jupiter.api.Test;

/**
 * Tests the Task object
 */
class TaskTest {

  /**
   * Tests the get/set complete methods
   */
  @Test
  public void getAndSetComplete() {
    Task t = new Task("testName", "testDescription", DayOfWeek.MONDAY,
        new Category("testCategory"));
    assertEquals(false, t.getComplete());
    t.setComplete(true);
    assertEquals(true, t.getComplete());
  }

  /**
   * Tests the getCategory method
   */
  @Test
  public void getCategory() {
    Task t = new Task("testName", "testDescription", DayOfWeek.MONDAY,
        new Category("testCategory"));
    assertEquals("testCategory", t.getCategory().getName());
  }

  /**
   * Tests the getDay method
   */
  @Test
  public void getDay() {
    Task t = new Task("testName", "testDescription", DayOfWeek.MONDAY,
        new Category("testCategory"));
    assertEquals("MONDAY", t.getDay().toString());
  }

  /**
   * Tests the getDescription method
   */
  @Test
  public void getDescription() {
    Task t = new Task("testName", "testDescription", DayOfWeek.MONDAY,
        new Category("testCategory"));
    assertEquals("testDescription", t.getDescription().toString());
  }

  /**
   * Tests the getName method
   */
  @Test
  public void getName() {
    Task t = new Task("testName", "testDescription", DayOfWeek.MONDAY,
        new Category("testCategory"));
    assertEquals("testName", t.getName().toString());
  }

  /**
   * Tests the toJson method
   */
  @Test
  public void testToJson() {
    Category school = new Category("School");
    Task example =
        new Task("Turn in last PA", "Make sure to do Extra Credit", DayOfWeek.THURSDAY, school);

    TaskJson resultRecord = example.toJson();
    assertEquals("Turn in last PA", resultRecord.taskName());
    assertEquals("Make sure to do Extra Credit", resultRecord.description());
    assertEquals("false", resultRecord.complete());

    example.setComplete(true);
    TaskJson trueResultRecord = example.toJson();
    assertEquals("Turn in last PA", trueResultRecord.taskName());
    assertEquals("Make sure to do Extra Credit", trueResultRecord.description());
    assertEquals("true", trueResultRecord.complete());
  }

  /**
   * Tests the toString method
   */
  @Test
  public void toFormattedString() {
    String expectedWithTitle = "testName\n"
        + "testDescription\n"
        + "MONDAY\n"
        + "Category: testCategory\n"
        + "Not Completed!\n";
    String expectedNoTitle = "testDescription\n"
        + "MONDAY\n"
        + "Category: testCategory\n"
        + "Not Completed!\n";

    Task task = new Task("testName", "testDescription", DayOfWeek.MONDAY,
        new Category("testCategory"));
    assertEquals(expectedWithTitle, task.toString(true));
    assertEquals(expectedNoTitle, task.toString(false));

    String expectedAndCompleted = "testName\n"
        + "testDescription\n"
        + "MONDAY\n"
        + "Category: testCategory\n"
        + "Completed!\n";
    task.setComplete(true);
    assertEquals(expectedAndCompleted, task.toString(true));
  }
}