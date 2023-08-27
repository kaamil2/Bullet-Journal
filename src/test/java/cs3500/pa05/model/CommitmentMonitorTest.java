package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the commitmentMonitor
 */
public class CommitmentMonitorTest {
  JournalDay monday;
  JournalDay tuesday;
  JournalDay wednesday;

  ArrayList<JournalDay> dayList;
  ArrayList<Category> categoryList;

  JournalWeek testWeek;

  Task task = new Task("This is", "a task", DayOfWeek.TUESDAY, new Category("hi"));
  Event event = new Event("This is", "an event", DayOfWeek.MONDAY, new Category("hey"), null);

  CommitmentMonitor testMonitor;

  /**
   * reinitializes the data before each test
   */
  @BeforeEach
  public void initData() {
    monday = new JournalDay(DayOfWeek.MONDAY, new ScheduleItemList());
    tuesday = new JournalDay(DayOfWeek.TUESDAY, new ScheduleItemList());
    wednesday = new JournalDay(DayOfWeek.WEDNESDAY, new ScheduleItemList());

    dayList = new ArrayList<>(Arrays.asList(monday, tuesday, wednesday));
    categoryList = new ArrayList<>(Arrays.asList(
        new Category("cat1"), new Category("cat2")));

    testWeek = new JournalWeek("Example Week", dayList, categoryList);

    testMonitor = new CommitmentMonitor(testWeek, 1, 1);
  }


  /**
   * Tests the method when there aren't a problematic number of commitments
   */
  @Test
  public void testNoProblemCommitments() {
    assertFalse(testMonitor.problematicCommitments());

    // can be up to and including the max num
    testWeek.addCommitment(task);

    testWeek.addCommitment(event);

    assertFalse(testMonitor.problematicCommitments());
  }

  /**
   * Tests when there is a probleamtic number of tasks only
   */
  @Test
  public void testProblematicTasks() {
    assertFalse(testMonitor.problematicCommitments());

    testWeek.addCommitment(task);
    testWeek.addCommitment(task);

    assertTrue(testMonitor.problematicCommitments());
  }

  /**
   * Tests when there is a problematic number of events only
   */
  @Test
  public void testProblematicEvents() {
    assertFalse(testMonitor.problematicCommitments());

    testWeek.addCommitment(event);
    testWeek.addCommitment(event);

    assertTrue(testMonitor.problematicCommitments());
  }

  /**
   * Tests when there is a problematic number of events and tasks
   */
  @Test
  public void testProblematicEventsAndTasks() {
    assertFalse(testMonitor.problematicCommitments());

    testWeek.addCommitment(task);
    testWeek.addCommitment(task);

    testWeek.addCommitment(event);
    testWeek.addCommitment(event);

    assertTrue(testMonitor.problematicCommitments());
  }

  /**
   * Tests setMaxEvents method
   */
  @Test
  public void testSetMaxEvents() {
    assertFalse(testMonitor.problematicCommitments());

    testWeek.addCommitment(event);
    testWeek.addCommitment(event);
    testWeek.addCommitment(event);

    assertTrue(testMonitor.problematicCommitments());

    testMonitor.setMaxEventsPerDay(5);
    assertFalse(testMonitor.problematicCommitments());

    testMonitor.setMaxEventsPerDay(2);
    assertTrue(testMonitor.problematicCommitments());
  }

  /**
   * Tests setMaxTasks method
   */
  @Test
  public void testSetMaxTasks() {
    assertFalse(testMonitor.problematicCommitments());

    testWeek.addCommitment(task);
    testWeek.addCommitment(task);
    testWeek.addCommitment(task);

    assertTrue(testMonitor.problematicCommitments());

    testMonitor.setMaxTasksPerDay(5);
    assertFalse(testMonitor.problematicCommitments());

    testMonitor.setMaxTasksPerDay(2);
    assertTrue(testMonitor.problematicCommitments());
  }
}
