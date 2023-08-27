package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Category;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Task;

/**
 * Represents a TaskItem in JSON
 *
 * @param taskName    - the name of the task
 * @param description - the description of the task
 * @param complete    - true if complete, false if not
 */
public record TaskJson(@JsonProperty("task-name") String taskName,
                       @JsonProperty("description") String description,
                       @JsonProperty("category") String category,
                       @JsonProperty("complete") String complete) {

  /**
   * Converts this TaskRecord into a Task object.
   *
   * @param dayBelongsTo - the day the task belongs to. Should be passed along in the process
   *                     of converting a larger JSON (day) record.
   * @return - a task object corresponding to this record.
   */
  public Task toTask(DayOfWeek dayBelongsTo) {
    boolean completedBoolean = complete.equals("true");
    return new Task(this.taskName, this.description, dayBelongsTo, new Category(category),
        completedBoolean);
  }
}
