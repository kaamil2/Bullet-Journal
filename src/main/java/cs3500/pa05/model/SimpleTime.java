package cs3500.pa05.model;

/**
 * Represents a single moment in time on a given day.
 */
public class SimpleTime {
  private final int startHour;
  private final int startMin;
  private final boolean useAm;
  private final DayOfWeek day;

  /**
   * Builds a new EventTime for given hour/minute and whether in AM or PM. Throws an exception
   * if invalid time is given.
   *
   * @param hour - the hour for the time we are tracking.
   * @param min  - the minute for the time we are tracking.
   * @param inAm - whether the time we are tracking is in AM or PM.
   * @param day  - the day of the week this time is on.
   */
  public SimpleTime(int hour, int min, boolean inAm, DayOfWeek day) {
    this.startHour = this.validateHour(hour);
    this.startMin = this.validateMin(min);
    this.useAm = inAm;
    this.day = day;
  }

  /**
   * Returns the given hour if it is valid, throws an exception if not.
   *
   * @param hour - the hour we want to validate.
   * @return - the validated hour or an exception if invalid
   */
  private int validateHour(int hour) {
    if (hour >= 1 && hour <= 12) {
      return hour;
    } else {
      throw new IllegalArgumentException("Cannot have hour of " + hour);
    }
  }

  /**
   * Returns the given minute if it is valid, throws an exception if not.
   *
   * @param min - the minute we want to validate.
   * @return - the validated minute or an exception if invalid
   */
  private int validateMin(int min) {
    if (min >= 0 && min <= 59) {
      return min;
    } else {
      throw new IllegalArgumentException("Cannot have minute of " + min);
    }
  }

  /**
   * returns the hour when this event starts
   *
   * @return - the start hour of this event
   */
  public int getHour() {
    return this.startHour;
  }

  /**
   * returns the minute when this event starts
   *
   * @return - the start minute of this event
   */
  public int getMin() {
    return this.startMin;
  }

  /**
   * returns if this event is in the morning (true) or the evening (false)
   *
   * @return - whether this event is in AM or PM
   */
  public boolean getAm() {
    return this.useAm;
  }

  /**
   * Returns the day associated with this event.
   *
   * @return the day of this time
   */
  public DayOfWeek getDay() {
    return this.day;
  }

  /**
   * Returns whether this time comes before/same time that time.
   *
   * @param after - the time we are comparing this to.
   * @return - whether this comes before/at the same time as that time.
   */
  public boolean compareTo(SimpleTime after) {
    // on a day after --> immediate fail
    if (this.day.ordinal() > after.day.ordinal()) {
      return false;
    } else if (this.day.ordinal() < after.day.ordinal()) {
      return true;
    } else {
      return this.comparingByAm(after);
    }
  }

  /**
   * Given that two events are on the same day, handles the logic of continuing to compare two
   * SimpleTimes, starting by comparing whether they use AM or PM and taking further action.
   *
   * @param after - the time we are comparing to.
   * @return - whether this time comes before/same time as that time.
   */
  private boolean comparingByAm(SimpleTime after) {
    if (this.useAm && !after.useAm) {
      return true;
    } else if (!this.useAm && after.useAm) {
      return false;
    } else {
      return this.comparingByTime(after);
    }
  }

  /**
   * Given that two events are either both in AM or PM, handles comparing the times by comparing
   * the actual times they start at and taking further action.
   *
   * @param after - the time we are comparing to.
   * @return - whether this time comes before/same time as that time.
   */
  private boolean comparingByTime(SimpleTime after) {
    if (this.startHour < after.startHour) {
      return true;
    } else if (this.startHour > after.startHour) {
      return false;
    } else {
      return this.startMin <= after.startMin;
    }
  }

  /**
   * Returns this time represented as a String
   *
   * @return - this time as a String
   */
  @Override
  public String toString() {
    String timeMessage = this.useAm ? "AM" : "PM";
    // so it says double zeroes
    String minuteMessage = this.startMin < 10 ? "0" + this.startMin : String.valueOf(this.startMin);
    return this.day + ", " + this.startHour + ":" + minuteMessage + " " + timeMessage;
  }
}
