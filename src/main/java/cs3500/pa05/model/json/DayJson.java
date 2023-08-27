package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.JournalDay;
import cs3500.pa05.model.ScheduleItem;
import cs3500.pa05.model.ScheduleItemList;
import java.util.ArrayList;

/**
 * Represents a JournalDay in JSON form.
 *
 * @param dayName - the name of the day
 * @param tasks - the day's tasks
 * @param events - the day's events
 */
public record DayJson(@JsonProperty ("day-name") String dayName,
                      @JsonProperty ("tasks") TaskJson[] tasks,
                      @JsonProperty ("events") EventJson[] events) {

  /**
   * Transforms this JSON record into a JournalDay.
   *
   * @return - a JournalDay representing the information contained in this record
   */
  public JournalDay toDay() {
    DayOfWeek day = DayOfWeek.dayFromString(dayName);
    ArrayList<ScheduleItem> itemsFromRecord = new ArrayList<>();

    for (TaskJson task : this.tasks) {
      itemsFromRecord.add(task.toTask(day));
    }

    for (EventJson event : this.events) {
      itemsFromRecord.add(event.toEvent(day));
    }

    ScheduleItemList listOfItems = new ScheduleItemList(itemsFromRecord);
    return new JournalDay(day, listOfItems);
  }
}
