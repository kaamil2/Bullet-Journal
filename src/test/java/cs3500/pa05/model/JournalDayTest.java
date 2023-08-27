package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.json.DayJson;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the JournalDay
 */
class JournalDayTest {
  JournalDay day;
  JournalDay emptyDay;
  EventTime time;
  Category category;
  Event event;
  Task task;
  ArrayList<ScheduleItem> scheduleItems;
  ScheduleItemList itemList;

  /**
   * Reinitializes the data before each test
   */
  @BeforeEach
  public void init() {
    SimpleTime startAt = new SimpleTime(1, 30, false, DayOfWeek.THURSDAY);
    SimpleTime endAt = new SimpleTime(2, 0, false, DayOfWeek.THURSDAY);
    time = new EventTime(startAt, endAt);
    category = new Category("category");
    event = new Event("test", "testtest", DayOfWeek.THURSDAY, category, time);
    task = new Task("testTask", "tasktask", DayOfWeek.TUESDAY, category);
    scheduleItems = new ArrayList<>(Collections.singletonList(event));
    itemList = new ScheduleItemList(scheduleItems);
    day = new JournalDay(DayOfWeek.WEDNESDAY, itemList);
    emptyDay = new JournalDay(DayOfWeek.SUNDAY);
  }

  /**
   * Tests the getDay method
   */
  @Test
  public void getDay() {
    assertEquals(DayOfWeek.SUNDAY, emptyDay.getDay());
  }

  /**
   * Tests the getScheduleItems method
   */
  @Test
  public void getScheduleItems() {
    assertEquals(1, day.getScheduleItems().getItemList().size());
    assertEquals(event, day.getScheduleItems().getItemList().get(0));
  }

  /**
   * Tests the addCommitment method
   */
  @Test
  public void addCommitment() {
    day.addCommitment(task);
    assertEquals(2, day.getScheduleItems().getItemList().size());
    assertEquals(event, day.getScheduleItems().getItemList().get(0));
    assertEquals(task, day.getScheduleItems().getItemList().get(1));
  }

  /**
   * Tests the toJson method
   */
  @Test
  public void testToJson() {
    day.addCommitment(task);
    DayJson resultRecord = day.toJson();
    assertEquals("WEDNESDAY", resultRecord.dayName());
    assertEquals(1, resultRecord.events().length);
    assertEquals(1, resultRecord.tasks().length);

    assertEquals("test", resultRecord.events()[0].eventName());
    assertEquals("testTask", resultRecord.tasks()[0].taskName());
  }

  /**
   * Tests the countTasks method
   */
  @Test
  public void testCountTasks() {
    day.addCommitment(task);
    day.addCommitment(task);
    day.addCommitment(event);
    assertEquals(2, day.countOfTasks());

    day.addCommitment(event);
    assertEquals(2, day.countOfTasks());
  }

  /**
   * Tests the countEvents method
   */
  @Test
  public void testCountEvents() {
    assertEquals(1, day.countOfEvents());

    day.addCommitment(event);
    day.addCommitment(event);
    day.addCommitment(task);
    assertEquals(3, day.countOfEvents());

    day.addCommitment(task);
    assertEquals(3, day.countOfEvents());
  }
}