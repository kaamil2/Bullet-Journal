package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * tests the scheduleItemList
 */
class ScheduleItemListTest {

  ScheduleItemList emptyList = new ScheduleItemList();

  SimpleTime startsAt = new SimpleTime(1, 30, false, DayOfWeek.FRIDAY);
  SimpleTime endsAt = new SimpleTime(2, 0, false, DayOfWeek.FRIDAY);
  EventTime time = new EventTime(startsAt, endsAt);

  Category category = new Category("category");
  Event event = new Event("test", "testtest", DayOfWeek.FRIDAY, category, time);

  Task task = new Task("testTask", "tasktask", DayOfWeek.MONDAY, category);
  ArrayList<ScheduleItem> scheduleItems = new ArrayList<>(Arrays.asList(event, task));

  ScheduleItemList itemList = new ScheduleItemList(scheduleItems);

  /**
   * Tests the addCommitment method
   */
  @Test
  public void testAddCommitment() {
    emptyList.addCommitment(task);
    assertEquals(task, emptyList.getItemList().get(0));

    emptyList.addCommitment(event);
    assertEquals(event, emptyList.getItemList().get(1));
  }

  /**
   * Tests the removeCommitment method
   */
  @Test
  public void testRemoveCommitment() {
    assertTrue(itemList.removeCommitment(task));
    assertEquals(event, itemList.getItemList().get(0));

    assertFalse(itemList.removeCommitment(task));
  }

  /**
   * Tests the getItemList method
   */
  @Test
  public void testGetItemList() {
    ArrayList<ScheduleItem> result = itemList.getItemList();
    assertEquals(2, result.size());

    result.remove(event);
    assertEquals(1, result.size());
    // arrayList above should remain unchanged
    assertEquals(2, scheduleItems.size());
  }

  /**
   * Tests the countTasks method
   */
  @Test
  public void testCountTasks() {
    ArrayList<ScheduleItem> noTasks = new ArrayList<>(Arrays.asList(event, event));
    ScheduleItemList noTasksItemList = new ScheduleItemList(noTasks);

    assertEquals(0, noTasksItemList.countTasks());

    ScheduleItemList exampleList = new ScheduleItemList();
    exampleList.addCommitment(task);
    exampleList.addCommitment(task);
    exampleList.addCommitment(task);

    assertEquals(3, exampleList.countTasks());
  }

  /**
   * Tests the countEvents method
   */
  @Test
  public void testCountEvents() {
    ArrayList<ScheduleItem> noEvents = new ArrayList<>(Arrays.asList(task, task));
    ScheduleItemList noEventsItemList = new ScheduleItemList(noEvents);

    assertEquals(0, noEventsItemList.countEvents());

    ScheduleItemList exampleList = new ScheduleItemList();
    exampleList.addCommitment(event);
    exampleList.addCommitment(event);
    exampleList.addCommitment(event);

    assertEquals(3, exampleList.countEvents());
  }

  /**
   * tests countCompleteTasks
   */
  @Test
  void countCompleteTasks() {
    assertEquals(0, itemList.countCompleteTasks());
    task.setComplete(true);
    assertEquals(1, itemList.countCompleteTasks());
  }
}