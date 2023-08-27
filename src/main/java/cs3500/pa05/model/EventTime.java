package cs3500.pa05.model;

/**
 * Represents a time associated with an event in AM/PM form.
 */
public class EventTime {
  private final SimpleTime startTime;
  private final SimpleTime endTime;

  /**
   * Builds a new EventTime referencing the given start and end time.
   *
   * @param startAt - the time an event starts at
   * @param endAt - the time an event ends at
   */
  public EventTime(SimpleTime startAt, SimpleTime endAt) {
    this.exceptionIfInvalid(startAt, endAt);
    this.startTime = startAt;
    this.endTime = endAt;
  }

  /**
   * Builds a new event time from the two strings which must be written in the form
   * "DAY, HOUR:MIN XM"
   *
   * @param startString - the start time written in the mentioned form
   * @param endString - the end time written in the mentioned form
   */
  public EventTime(String startString, String endString) {
    SimpleTime startsAt = this.timeFromString(startString);
    SimpleTime endsAt = this.timeFromString(endString);

    this.exceptionIfInvalid(startsAt, endsAt);
    this.startTime = startsAt;
    this.endTime = endsAt;
  }

  /**
   * Extracts a SimpleTime from the given String.
   *
   * @param extractFrom - the String from which to extract a SimpleTime
   * @return - a SimpleTime corresponding to the given String.
   */
  private SimpleTime timeFromString(String extractFrom) {
    int commaIndex = extractFrom.indexOf(",");
    int colonIndex = extractFrom.indexOf(":");
    String dayName = extractFrom.substring(0, commaIndex);
    DayOfWeek day = DayOfWeek.dayFromString(dayName);

    // exclude comma and the single space that should come after it
    String hourString = extractFrom.substring(commaIndex + 2, colonIndex);
    int hour = Integer.parseInt(hourString);

    // always two characters
    String minuteString = extractFrom.substring(colonIndex + 1, colonIndex + 3);
    int minute = Integer.parseInt(minuteString);

    // second to last character
    String secondToLast = extractFrom.substring(extractFrom.length() - 2, extractFrom.length() - 1);
    boolean useAm = secondToLast.equals("A");

    return new SimpleTime(hour, minute, useAm, day);
  }

  /**
   * Throws an exception if the "start" time does not come before / same time as "end" time.
   *
   * @param before - the time that should come first.
   * @param after - the time that should be equal or come second.
   */
  private void exceptionIfInvalid(SimpleTime before, SimpleTime after) {
    if (!before.compareTo(after)) {
      throw new IllegalArgumentException("Start time comes after end time");
    }
  }

  /**
   * Returns the startTime of this Event
   *
   * @return - startTime of this event
   */
  public SimpleTime getStartTime() {
    return this.startTime;
  }

  /**
   * Returns the endTime of this Event
   *
   * @return - end time of this event
   */
  public SimpleTime getEndTime() {
    return this.endTime;
  }

  /**
   * Returns this event time as a String
   *
   * @return - this eventTime as a string
   */
  public String toString() {
    return this.startTime + " -\n " + this.endTime;
  }
}
