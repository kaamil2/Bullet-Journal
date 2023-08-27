package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa05.model.json.WeekJson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

/**
 * Tests the JournalWeek class
 */

public class JournalWeekTest {
  JournalDay monday = new JournalDay(DayOfWeek.MONDAY, new ScheduleItemList());
  JournalDay tuesday = new JournalDay(DayOfWeek.TUESDAY, new ScheduleItemList());
  JournalDay wednesday = new JournalDay(DayOfWeek.WEDNESDAY, new ScheduleItemList());

  ArrayList<JournalDay> dayList = new ArrayList<>(Arrays.asList(monday, tuesday, wednesday));
  ArrayList<Category> categoryList = new ArrayList<>(Arrays.asList(
      new Category("cat1"), new Category("cat2")));

  JournalWeek testWeek = new JournalWeek("Example Week", dayList, categoryList);

  Task mondayTask = new Task("This is", "a task", DayOfWeek.MONDAY,
      new Category("hi"));
  Task mondayTask2 = new Task("This is", "a task", DayOfWeek.MONDAY,
      new Category("hi"));
  Task tuesdayTask1 = new Task("This is", "a task", DayOfWeek.TUESDAY,
      new Category("hi"));
  Task tuesdayTask2 = new Task("This is", "a task", DayOfWeek.TUESDAY,
      new Category("hi"));
  Task tuesdayTask3 = new Task("This is", "a task", DayOfWeek.TUESDAY,
      new Category("hi"));
  Task wednesdayTask = new Task("This is", "a task", DayOfWeek.WEDNESDAY,
      new Category("hi"));
  Event mondayEvent = new Event("This is", "an event", DayOfWeek.MONDAY,
      new Category("hey"), null);
  Event mondayEvent2 =
      new Event("This is", "an event", DayOfWeek.MONDAY,
          new Category("hey"), null);
  Event tuesdayEvent =
      new Event("This is", "an event", DayOfWeek.TUESDAY,
          new Category("hey"), null);
  Event tuesdayEvent2 =
      new Event("This is", "an event", DayOfWeek.TUESDAY,
          new Category("hey"), null);
  Event tuesdayEvent3 =
      new Event("This is", "an event", DayOfWeek.TUESDAY,
          new Category("hey"), null);
  Event wednesdayEvent =
      new Event("This is", "an event", DayOfWeek.WEDNESDAY,
          new Category("hey"), null);


  /**
   * Tests the addCommitment method
   */
  @Test
  public void testAddCommitment() {
    assertThrows(
        NoSuchElementException.class,
        () -> testWeek.addCommitment(
            new Task("hello", "task", DayOfWeek.FRIDAY,
                new Category("cat1"))));

    testWeek.addCommitment(new Task("hello", "task", DayOfWeek.MONDAY,
        new Category("cat1")));

    assertEquals(1, monday.getScheduleItems().getItemList().size());
  }

  /**
   * newCategory method
   */
  @Test
  public void testNewCategory() {
    assertEquals(2, categoryList.size());

    testWeek.newCategory("cat2");
    assertEquals(2, categoryList.size());

    testWeek.newCategory("cat3");
    assertEquals(3, categoryList.size());
  }

  /**
   * Tests the toJson method
   */
  @Test
  public void testToJson() {
    WeekJson resultRecord = testWeek.toJson();
    assertEquals("Example Week", resultRecord.name());
    assertEquals(3, resultRecord.days().length);
  }

  /**
   * Tests the tasksPerDay method
   */
  @Test
  public void testTasksPerDay() {
    testWeek.addCommitment(mondayTask);
    testWeek.addCommitment(mondayTask2);

    testWeek.addCommitment(tuesdayTask1);
    testWeek.addCommitment(tuesdayTask2);
    testWeek.addCommitment(tuesdayTask3);

    testWeek.addCommitment(wednesdayTask);

    ArrayList<Integer> numList = new ArrayList<>(Arrays.asList(2, 3, 1));
    assertEquals(numList, testWeek.countTasks());
  }

  /**
   * Tests the tasksPerDay method
   */
  @Test
  public void testEventsPerDay() {
    testWeek.addCommitment(mondayEvent);
    testWeek.addCommitment(mondayEvent2);

    testWeek.addCommitment(tuesdayEvent);
    testWeek.addCommitment(tuesdayEvent2);
    testWeek.addCommitment(tuesdayEvent3);

    testWeek.addCommitment(wednesdayEvent);

    ArrayList<Integer> numList = new ArrayList<>(Arrays.asList(2, 3, 1));
    assertEquals(numList, testWeek.countEvents());
  }

  /**
   * Tests the countCompleteTasks method
   */
  @Test
  public void countCompleteTasks() {
    Task task2 = new Task("This is", "another task", DayOfWeek.MONDAY,
        new Category("hi"));
    Task task3 = new Task("This is", "a different task", DayOfWeek.WEDNESDAY,
        new Category("hi"));
    testWeek.addCommitment(task2);
    testWeek.addCommitment(task3);
    assertEquals(0, testWeek.countCompleteTasks());
    task2.setComplete(true);
    assertEquals(1, testWeek.countCompleteTasks());
    task3.setComplete(true);
    assertEquals(2, testWeek.countCompleteTasks());
  }

  /**
   * Tests the getDays method
   */
  @Test
  public void testGetDays() {
    assertEquals(dayList, testWeek.getDays());
  }

  /**
   * Tests the getDaysOfWeek method
   */
  @Test
  public void testGetDaysOfWeek() {
    assertEquals(dayList, testWeek.getDaysOfWeek());
  }

  /**
   * Tests the getName method
   */
  @Test
  public void getName() {
    assertEquals("Example Week", testWeek.getName());
  }
}
