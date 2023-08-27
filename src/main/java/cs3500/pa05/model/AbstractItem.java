package cs3500.pa05.model;

/**
 * represents an item to be added to the schedule on a given day
 */
public abstract class AbstractItem implements ScheduleItem {
  private final String itemName;
  private final String description;
  private final DayOfWeek day;
  private final Category category;

  /**
   * Builds a new Abstract (Schedule) Item using given name, description, day, and category
   *
   * @param name - the name of this item
   * @param desc - the description of this item
   * @param dayOfWeek - the day this item belongs to
   * @param belongsTo - the category this item belongs to
   */
  public AbstractItem(String name, String desc, DayOfWeek dayOfWeek, Category belongsTo) {
    this.itemName = name;
    this.description = desc;
    this.day = dayOfWeek;
    this.category = belongsTo;
  }

  /**
   * @return - the name of this event.
   */
  public String getName() {
    return this.itemName;
  }

  /**
   * @return - the description of this event.
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * @return - the day of this event.
   */
  public DayOfWeek getDay() {
    return this.day;
  }

  /**
   * @return - the category of this event.
   */
  public Category getCategory() {
    return this.category;
  }
}
