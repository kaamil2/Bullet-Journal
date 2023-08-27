package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Category;
import cs3500.pa05.model.JournalDay;
import cs3500.pa05.model.JournalWeek;
import java.util.ArrayList;

/**
 * Represents a BulletJournal week in Json
 *
 * @param name - the name/title of the week
 * @param days - the days associated with the week. Each contains their own events/tasks
 */
public record WeekJson(@JsonProperty ("week-name") String name,
                       @JsonProperty ("days") DayJson[] days) {

  /**
   * Returns a JournalWeek resembling the information contained within this record.
   *
   * @return - a JournalWeek resembling the information in this record
   */
  public JournalWeek toWeek() {
    ArrayList<JournalDay> dayObjects = new ArrayList<>();

    for (DayJson day : days) {
      dayObjects.add(day.toDay());
    }

    ArrayList<Category> catList = Category.getCategoriesFrom(dayObjects);

    return new JournalWeek(name, dayObjects, catList);
  }
}
