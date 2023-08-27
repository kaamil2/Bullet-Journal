package cs3500.pa05.model;

/**
 * Represents one of seven days in the week
 */
public enum DayOfWeek {
  /**
   * Represents monday
   */
  MONDAY,
  /**
   * Represents tuesday
   */
  TUESDAY,
  /**
   * Represents wednesday
   */
  WEDNESDAY,
  /**
   * Represents thursday
   */
  THURSDAY,
  /**
   * Represents friday
   */
  FRIDAY,
  /**
   * Represents saturday
   */
  SATURDAY,
  /**
   * Represents sunday
   */
  SUNDAY;

  /**
   * Returns a DayOfWeek corresponding to given string
   *
   * @param extractFrom string to extract from
   *
   * @return DayOfWeek corresponding to given string
   */
  public static DayOfWeek dayFromString(String extractFrom) {
    // goes through each day in list
    for (DayOfWeek dayFromList : DayOfWeek.values()) {
      if (dayFromList.toString().equalsIgnoreCase(extractFrom)) {
        return dayFromList;
      }
    }

    throw new IllegalArgumentException("No corresponding day found.");
  }
}
