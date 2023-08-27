package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa05.model.json.DayJson;
import cs3500.pa05.model.json.EventJson;
import cs3500.pa05.model.json.JournalJson;
import cs3500.pa05.model.json.TaskJson;
import cs3500.pa05.model.json.WeekJson;
import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests accurate reading from files
 */
public class FileReaderTest {
  EventJson testRecord = new EventJson("Birthday Party",
      "It's not a surprise",
      "Parties",
      "MONDAY, 7:30 PM",
      "MONDAY, 10:45 PM");

  EventJson testRecordTwo = new EventJson("Final Exam",
      "For some class",
      "School",
      "WEDNESDAY, 4:00 PM",
      "WEDNESDAY, 5:00 PM");

  EventJson[] events = new EventJson[] {testRecord, testRecordTwo};

  TaskJson testTaskRecord = new TaskJson("Test Task",
      "A task for testing",
      "Testing Tasks",
      "true");

  TaskJson testTaskRecordTwo = new TaskJson("Another test task",
      "Shows how fun testing is",
      "Testing Tasks",
      "false");

  TaskJson[] tasks = new TaskJson[] {testTaskRecord, testTaskRecordTwo};

  DayJson testMonday = new DayJson("monday", tasks, events);

  EventJson tuesdayTestRecord = new EventJson("Voting Available",
      "Happy Election Day!",
      "News",
      "TUESDAY, 7:30 AM",
      "TUESDAY, 7:30 PM");

  EventJson tuesdayTestRecordTwo = new EventJson("TV Election Coverage",
      "I like the music and the map walls",
      "News",
      "TUESDAY, 4:00 PM",
      "WEDNESDAY, 4:00 AM");

  EventJson[] tuesdayEvents = new EventJson[] {tuesdayTestRecord, tuesdayTestRecordTwo};

  TaskJson tuesdayTestTaskRecord = new TaskJson("Bake Cake",
      "A task for tasting (haha)",
      "Baking",
      "true");

  TaskJson tuesdayTestTaskRecordTwo = new TaskJson("Bake Cupcakes",
      "Like a mini version of cake",
      "Baking",
      "false");

  TaskJson[] tuesdayTasks = new TaskJson[] {tuesdayTestTaskRecord, tuesdayTestTaskRecordTwo};

  DayJson testTuesday = new DayJson("tuesday", tuesdayTasks, tuesdayEvents);
  DayJson[] mondayTuesday = new DayJson[] {testMonday, testTuesday};
  WeekJson testWeek = new WeekJson("Monday and Tuesday", mondayTuesday);

  JournalJson journalJson = new JournalJson("5", "5", testWeek);

  BulletJournal journalAsObject = journalJson.toJournal();

  @BeforeEach
  public void setup() {
    FileWriter writer = new FileWriter();
    writer.writeJournalToFile(journalAsObject, new File("src/test/ExampleFiles/ToReadFrom.bujo"));
  }

  /**
   * Tests the readFromFile method. above setup methods automatically write a full week to a file.
   */
  @Test
  public void testReadFromFile() {
    FileReader reader = new FileReader();
    BulletJournal result =
        reader.convertFileToWeek(new File("src/test/ExampleFiles/ToReadFrom.bujo"));

    // converted to record for testing so can check info more easily
    JournalJson resultAsJson = result.toJson();
    assertEquals("5", resultAsJson.maxEvents());
    assertEquals("5", resultAsJson.maxTasks());
    assertEquals(2, resultAsJson.journalWeek().days().length);
    assertEquals("Monday and Tuesday", resultAsJson.journalWeek().name());

    assertThrows(
        RuntimeException.class,
        () -> reader.convertFileToWeek(new File("src/test/hello/main/java/bye/ToReadFrom.bujo"))
    );
  }
}
