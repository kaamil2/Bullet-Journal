package cs3500.pa05.model;

/**
 * Represents an item that is placed in somebody's bullet journal schedule.
 */
public interface ScheduleItem {
  /**
   * Returns the category associated with the item.
   *
   * @return - the category associated with the item.
   */
  Category getCategory();

  /**
   * Returns the dayOfWeek associated with the item
   *
   * @return - the category associated with the item
   */
  DayOfWeek getDay();

  /**
   * Returns the description of the item
   *
   * @return - the description of the item
   */
  String getDescription();

  /**
   * Returns the name of the item
   *
   * @return - the name of the item
   */
  String getName();

  /**
   * Represents the item as a String, either including the name of it or not.
   *
   * @param withTitle - whehter the name of the event should be included in the output
   * @return - the item represented as a String
   */
  String toString(boolean withTitle);
}
