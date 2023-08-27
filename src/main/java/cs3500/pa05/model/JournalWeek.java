package cs3500.pa05.model;

import cs3500.pa05.model.json.DayJson;
import cs3500.pa05.model.json.WeekJson;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Represents a week of a BulletJournal
 */
public class JournalWeek {
  private String nameForWeek;
  private final ArrayList<JournalDay> daysOfWeek;
  private final ArrayList<Category> availableCategories;

  /**
   * Builds a new journal week with given name, associated days, and list of available categories
   * to label tasks and events with
   *
   * @param name - the name of the week
   * @param days - the individual days that compose this week
   * @param categories - the categories events and tasks may have in this week
   */
  public JournalWeek(String name, ArrayList<JournalDay> days, ArrayList<Category> categories) {
    this.nameForWeek = name;
    this.daysOfWeek = days;
    this.availableCategories = categories;
  }

  /**
   * @return a shallow copy of the categories in this week
   */
  public ArrayList<Category> getCategories() {
    return new ArrayList<>(this.availableCategories);
  }

  /**
   * @return a shallow copy of the days in this week
   */
  public ArrayList<JournalDay> getDays() {
    return new ArrayList<>(this.daysOfWeek);
  }

  /**
   * @return the name for this week
   */
  public String getName() {
    return this.nameForWeek;
  }

  /**
   * @param name the new name for this week
   */
  public void setName(String name) {
    this.nameForWeek = name;
  }

  /**
   * Adds given ScheduleItem to given day of the week.
   *
   * @param item - the item to add to the schedule for the day
   */
  public void addCommitment(ScheduleItem item) {
    for (JournalDay dayInList : daysOfWeek) {
      if (dayInList.getDay() == item.getDay()) {
        dayInList.addCommitment(item);
        return;
      }
    }

    throw new NoSuchElementException("No day " + item.getDay() + " found.");
  }

  /**
   * Adds a new category to this bullet journal's available categories.
   *
   * @param categoryName - the name of the new category
   */
  public void newCategory(String categoryName) {
    // prevents duplicates
    for (Category existingCategory : this.availableCategories) {
      if (categoryName.equals(existingCategory.getName())) {
        return;
      }
    }

    this.availableCategories.add(new Category(categoryName));
  }

  /**
   * Returns a WeekJson record representing this week in JSON form
   *
   * @return - a weekJson record representing this week in JSON Form
   */
  public WeekJson toJson() {
    DayJson[] daysAsJson = new DayJson[this.daysOfWeek.size()];
    for (int i = 0; i < daysOfWeek.size(); i++) {
      DayJson listDayAsJson = this.daysOfWeek.get(i).toJson();
      daysAsJson[i] = listDayAsJson;
    }

    return new WeekJson(this.nameForWeek, daysAsJson);
  }

  /**
   * Returns a list of Integers corresponding to the number of tasks contained in each day
   * of this week.
   *
   * @return - a list of integers representing the number of tasks found in each day of this week
   */
  public ArrayList<Integer> countTasks() {
    ArrayList<Integer> result = new ArrayList<>();

    for (JournalDay dayFromWeek : this.daysOfWeek) {
      result.add(dayFromWeek.countOfTasks());
    }

    return result;
  }

  /**
   * Returns a list of Integers corresponding to the number of tasks contained in each day
   * of this week.
   *
   * @return - a list of integers representing the number of tasks found in each day of this week
   */
  public ArrayList<Integer> countEvents() {
    ArrayList<Integer> result = new ArrayList<>();

    for (JournalDay dayFromWeek : this.daysOfWeek) {
      result.add(dayFromWeek.countOfEvents());
    }

    return result;
  }

  /**
   * Returns a list of JournalDays corresponding to the number of tasks contained in each day
   * of this week.
   *
   * @return - a list of JournalDays representing the number of tasks found in each day of this week
   */
  public ArrayList<JournalDay> getDaysOfWeek() {
    return this.daysOfWeek;
  }

  /**
   * counts the number of completed tasks in this week
   *
   * @return the number of completed tasks in this week
   */
  public int countCompleteTasks() {
    int ans = 0;
    for (JournalDay day : this.daysOfWeek) {
      ans += day.countCompleteTasks();
    }
    return ans;
  }

}
