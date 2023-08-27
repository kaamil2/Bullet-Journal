package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the BulletJournal object
 */
class BulletJournalTest {
  BulletJournal journal;
  CommitmentMonitor monitor;
  JournalWeek week;
  JournalDay monday;
  Task task;
  Event event;
  EventTime time;
  SimpleTime startTime;
  SimpleTime endTime;
  Category category;

  /**
   * Initializes the data before each test
   */
  @BeforeEach
  public void init() {
    startTime = new SimpleTime(1, 0, false, DayOfWeek.MONDAY);
    endTime = new SimpleTime(2, 30, false, DayOfWeek.MONDAY);
    time = new EventTime(startTime, endTime);
    category = new Category("category test");
    event = new Event("testEvent", "event description", DayOfWeek.MONDAY, category, time);
    task = new Task("testTask", "task desc", DayOfWeek.MONDAY, category);
    ArrayList<ScheduleItem> itemList = new ArrayList<>(List.of(event));
    monday = new JournalDay(DayOfWeek.MONDAY, new ScheduleItemList(itemList));
    ArrayList<JournalDay> dayList = new ArrayList<>(List.of(monday));
    ArrayList<Category> categoryList = new ArrayList<>();
    week = new JournalWeek("testWeek", dayList, categoryList);
    monitor = new CommitmentMonitor(week, 0, 1);
    journal = new BulletJournal(week, monitor);
  }

  /**
   * Tests the addCommitment method
   */
  @Test
  public void addCommitment() {
    journal.addCommitment(task);
    assertEquals(task, monday.getScheduleItems().getItemList().get(1));
  }

  /**
   * Tests the newCategory method
   */
  @Test
  public void newCategory() {
    journal.newCategory("category test");
    assertEquals(category.getName(), week.getCategories().get(0).getName());
  }

  /**
   * Tests the problematicCommitments method
   */
  @Test
  public void problematicCommitments() {
    assertFalse(journal.problematicCommitments());
    journal.addCommitment(task);
    assertTrue(journal.problematicCommitments());
  }

  /**
   * Tests the numberOfTasks method
   */
  @Test
  public void numOfTasks() {
    assertEquals(0, journal.numOfTasks());
    journal.addCommitment(task);
    assertEquals(1, journal.numOfTasks());
  }

  /**
   * Tests the numberOfEvents Method
   */
  @Test
  public void numOfEvents() {
    assertEquals(1, journal.numOfEvents());
  }

  /**
   * Tests the getWeek getter
   */
  @Test
  public void testGetWeek() {
    assertEquals(week, journal.getWeek());
  }

  /**
   * Tests the countCompleteTasks method
   */
  @Test
  public void testCountCompleteTasks() {
    Task task1 = new Task("monday", "task for Monday", DayOfWeek.MONDAY, category);
    Task task2 = new Task("other task", "task for tuesday", DayOfWeek.MONDAY, category);
    Task task3 = new Task("new task", "task for friday", DayOfWeek.MONDAY, category);

    journal.addCommitment(task1);
    journal.addCommitment(task2);
    journal.addCommitment(task3);

    assertEquals(0, journal.countCompleteTasks());

    task1.setComplete(true);
    assertEquals(1, journal.countCompleteTasks());

    task2.setComplete(true);
    task3.setComplete(true);
    assertEquals(3, journal.countCompleteTasks());
  }

  @Test
  void getMonitor() {
    assertEquals(monitor, journal.getMonitor());
  }
}