package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Tests for the FileWriter
 */
public class FileWriterTest {

  JournalDay monday = new JournalDay(DayOfWeek.MONDAY, new ScheduleItemList());
  JournalDay tuesday = new JournalDay(DayOfWeek.TUESDAY, new ScheduleItemList());
  JournalDay wednesday = new JournalDay(DayOfWeek.WEDNESDAY, new ScheduleItemList());

  ArrayList<JournalDay> dayList = new ArrayList<>(Arrays.asList(monday, tuesday, wednesday));
  ArrayList<Category> categoryList = new ArrayList<>(Arrays.asList(
      new Category("cat1"), new Category("cat2")));

  JournalWeek testWeek = new JournalWeek("Example Week", dayList, categoryList);
  BulletJournal journal = new BulletJournal(testWeek, new CommitmentMonitor(testWeek, 4, 4));

  Task mondayTask = new Task("This is", "a task", DayOfWeek.MONDAY,
      new Category("hi"));
  Task mondayTask2 = new Task("This is", "a task", DayOfWeek.MONDAY,
      new Category("hi"));
  Task tuesdayTask = new Task("This is", "a task", DayOfWeek.TUESDAY,
      new Category("hi"));
  Task tuesdayTask2 = new Task("This is", "a task", DayOfWeek.TUESDAY,
      new Category("hi"));
  Task wednesdayTask = new Task("This is", "a task", DayOfWeek.WEDNESDAY,
      new Category("hi"));
  Task wednesdayTask2 = new Task("This is", "a task", DayOfWeek.WEDNESDAY,
      new Category("hi"));
  Task wednesdayTask3 = new Task("This is", "a task", DayOfWeek.WEDNESDAY,
      new Category("hi"));
  Event mondayEvent = new Event("This is", "an event", DayOfWeek.MONDAY,
      new Category("hey"), new EventTime("MONDAY, 8:30 AM", "MONDAY, 9:30 AM"));
  Event tuesdayEvent = new Event("This is", "an event", DayOfWeek.TUESDAY,
      new Category("hey"), new EventTime("MONDAY, 8:30 AM", "MONDAY, 9:30 AM"));
  Event tuesdayEvent2 = new Event("This is", "an event", DayOfWeek.TUESDAY,
      new Category("hey"), new EventTime("MONDAY, 8:30 AM", "MONDAY, 9:30 AM"));
  Event tuesdayEvent3 = new Event("This is", "an event", DayOfWeek.TUESDAY,
      new Category("hey"), new EventTime("MONDAY, 8:30 AM", "MONDAY, 9:30 AM"));
  Event wednesdayEvent = new Event("This is", "an event", DayOfWeek.WEDNESDAY,
      new Category("hey"), new EventTime("MONDAY, 8:30 AM", "MONDAY, 9:30 AM"));


  /**
   * Adds tasks to the days for testing
   */
  @BeforeEach
  public void initData() {
    testWeek.addCommitment(mondayTask);
    testWeek.addCommitment(mondayTask2);

    testWeek.addCommitment(tuesdayTask);
    testWeek.addCommitment(tuesdayTask2);

    testWeek.addCommitment(wednesdayTask);
    testWeek.addCommitment(wednesdayTask2);
    testWeek.addCommitment(wednesdayTask3);

    testWeek.addCommitment(mondayEvent);

    testWeek.addCommitment(tuesdayEvent);
    testWeek.addCommitment(tuesdayEvent2);
    testWeek.addCommitment(tuesdayEvent3);

    testWeek.addCommitment(wednesdayEvent);
  }


  /**
   * Tests writing journals to files
   */
  @Test
  public void testWriteWeekToFile() {
    FileWriter writer = new FileWriter();

    assertDoesNotThrow(
        () -> writer.writeJournalToFile(journal,
            new File("src/test/ExampleFiles/FileWriterTest.bujo"))
    );

    assertThrows(
        IllegalArgumentException.class,
        () -> writer.writeJournalToFile(journal,
            new File("src/test/ExampleFiles/FileWriterTest.txt")));

    assertThrows(
        RuntimeException.class,
        () -> writer.writeJournalToFile(journal,
            new File("src/test/java/main/hello/src/FileWriterTest.bujo")));
  }
}
