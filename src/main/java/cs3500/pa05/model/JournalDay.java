package cs3500.pa05.model;

import cs3500.pa05.model.json.DayJson;
import cs3500.pa05.model.json.EventJson;
import cs3500.pa05.model.json.TaskJson;
import java.util.ArrayList;

/**
 * represents a single day in a journal
 */
public class JournalDay {
  /**
   * the day of the week this JournalDay is
   */
  private final DayOfWeek day;

  /**
   * the ScheduleItems that occur on this day
   */
  private final ScheduleItemList scheduleItems;

  /**
   * @param day           the day this represents/is associated with.
   * @param scheduleItems the items scheduled for this day
   */
  public JournalDay(DayOfWeek day, ScheduleItemList scheduleItems) {
    this.day = day;
    this.scheduleItems = scheduleItems;
  }

  /**
   * creates a JournalDay with an empty list of schedule items.
   *
   * @param day the day of the week
   */
  public JournalDay(DayOfWeek day) {
    this(day, new ScheduleItemList());
  }


  /**
   * @return the day of the week this JournalDay falls on
   */
  public DayOfWeek getDay() {
    return day;
  }

  /**
   * @return a shallow copy of the commitments scheduled on this day
   */
  public ScheduleItemList getScheduleItems() {
    return new ScheduleItemList(this.scheduleItems.getItemList());
  }

  /**
   * adds the given commitment to this day
   *
   * @param commitment the ScheduleItem to be added to this day
   */
  public void addCommitment(ScheduleItem commitment) {
    this.scheduleItems.addCommitment(commitment);
  }

  /**
   * returns a JSON record resembling this day, including its commitments.
   *
   * @return a JSON record representing this Day
   */
  public DayJson toJson() {
    ArrayList<EventJson> eventsAsJson = new ArrayList<>();
    ArrayList<TaskJson> tasksAsJson = new ArrayList<>();

    for (ScheduleItem item : this.scheduleItems.getItemList()) {
      if (item instanceof Task) {
        tasksAsJson.add(((Task) item).toJson());
      } else if (item instanceof Event) {
        eventsAsJson.add(((Event) item).toJson());
      }
    }

    TaskJson[] taskArray = new TaskJson[tasksAsJson.size()];
    for (int i = 0; i < tasksAsJson.size(); i++) {
      taskArray[i] = tasksAsJson.get(i);
    }

    EventJson[] eventArray = new EventJson[eventsAsJson.size()];
    for (int i = 0; i < eventsAsJson.size(); i++) {
      eventArray[i] = eventsAsJson.get(i);
    }

    return new DayJson(this.day.toString(), taskArray, eventArray);
  }

  /**
   * Returns the number of tasks this day has (for monitoring commitments)
   *
   * @return - the number of tasks in this day's commitments.
   */
  public int countOfTasks() {
    return this.scheduleItems.countTasks();
  }

  /**
   * Returns the number of events this day has (for monitoring commitments)
   *
   * @return - the number of events in this day's commitments.
   */
  public int countOfEvents() {
    return this.scheduleItems.countEvents();
  }

  /**
   * Returns the list of categories this day has (for monitoring commitments)
   *
   * @return - the list of categories in this day's commitments.
   */
  public ArrayList<Category> getCategories() {
    return this.scheduleItems.getCategories();
  }

  /**
   * counts the number of completed tasks in this day
   *
   * @return the number of completed tasks in this week
   */
  public int countCompleteTasks() {
    return this.scheduleItems.countCompleteTasks();
  }
}
