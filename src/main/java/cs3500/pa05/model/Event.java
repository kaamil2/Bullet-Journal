package cs3500.pa05.model;

import cs3500.pa05.model.json.EventJson;

/**
 * represents an event
 */
public class Event extends AbstractItem {
  private final EventTime time;

  /**
   * Builds a new Abstract (Schedule) Item using given name, description, day, and category
   *
   * @param name      - the name of this item
   * @param desc      - the description of this item
   * @param dayOfWeek - the day this item belongs to
   * @param belongsTo - the category this item belongs to
   * @param time      - the time this event occurs and for how long
   */
  public Event(String name, String desc, DayOfWeek dayOfWeek, Category belongsTo, EventTime time) {
    super(name, desc, dayOfWeek, belongsTo);
    this.time = time;
  }

  /**
   * returns the category of this event.
   *
   * @return - the category of this event.
   */
  public Category getCategory() {
    return super.getCategory();
  }

  /**
   * returns the day of this event.
   *
   * @return - the day of this event.
   */
  public DayOfWeek getDay() {
    return super.getDay();
  }

  /**
   * returns the description of this event.
   *
   * @return - the description of this event.
   */
  public String getDescription() {
    return super.getDescription();
  }

  /**
   * returns the name of this event.
   *
   * @return - the name of this event.
   */
  public String getName() {
    return super.getName();
  }

  /**
   * returns the time of this event.
   *
   * @return - the time of this event.
   */
  public EventTime getTime() {
    return this.time;
  }

  /**
   * Returns a JSON record representing this event
   *
   * @return - a json record representing this event
   */
  public EventJson toJson() {
    return new EventJson(this.getName(), this.getDescription(), this.getCategory().getName(),
        this.getTime().getStartTime().toString(), this.getTime().getEndTime().toString());
  }

  /**
   * Returns information/summary of this event as a String, either including the title or not
   * based on the given boolean.
   *
   * @param withTitle - whether the title of the event should be included or not
   * @return - this event as a string.
   */
  public String toString(boolean withTitle) {
    String message = "";
    if (withTitle) {
      message += this.getName() + "\n";
    }

    message += this.getDescription() + "\n";
    message += this.getDay() + "\n";
    message += "Category: " + this.getCategory() + "\n";
    message += this.time.toString();

    return message;
  }
}
