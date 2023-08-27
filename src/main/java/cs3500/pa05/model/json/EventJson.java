package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Category;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.EventTime;

/**
 * Represents an Event in JSON
 *
 * @param eventName   - the name of the event
 * @param description - description of the event
 * @param category    - category of the event
 * @param startTime   - start time in the form "DAY, HOUR:MIN _M"
 * @param endTime     - end time in the same form as startTime
 */
public record EventJson(@JsonProperty("event-name") String eventName,
                        @JsonProperty("description") String description,
                        @JsonProperty("category") String category,
                        @JsonProperty("start-time") String startTime,
                        @JsonProperty("end-time") String endTime) {

  /**
   * Returns an event corresponding to the information contained in this JSON object
   *
   * @param belongsTo - the day this event belongs to, should be passed along when converting a Day
   *                  JSON object.
   * @return - an event object representing the information in this record
   */
  public Event toEvent(DayOfWeek belongsTo) {
    return new Event(eventName, description, belongsTo, new Category(category),
        new EventTime(startTime, endTime));
  }
}
