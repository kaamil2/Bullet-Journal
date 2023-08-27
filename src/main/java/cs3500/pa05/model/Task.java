package cs3500.pa05.model;

import cs3500.pa05.model.json.TaskJson;

/**
 * represents a task
 */
public class Task extends AbstractItem {
  private boolean complete;

  /**
   * Builds a new TaskItem using given name, description, day, and category (complete set to false)
   *
   * @param name      - the name of this item
   * @param desc      - the description of this item
   * @param dayOfWeek - the day this item belongs to
   * @param belongsTo - the category this item belongs to
   */
  public Task(String name, String desc, DayOfWeek dayOfWeek, Category belongsTo) {
    super(name, desc, dayOfWeek, belongsTo);
    this.complete = false;
  }

  /**
   * Builds a new TaskItem using given name, description, day, and category, and
   * boolean for completion (should only be used when converting from JSON
   *
   * @param name      - the name of this item
   * @param desc      - the description of this item
   * @param dayOfWeek - the day this item belongs to
   * @param belongsTo - the category this item belongs to
   * @param complete  - whether the task has been completed or not
   */
  public Task(String name, String desc, DayOfWeek dayOfWeek, Category belongsTo, boolean complete) {
    super(name, desc, dayOfWeek, belongsTo);
    this.complete = complete;
  }

  /**
   * returns if the task is complete or not.
   *
   * @return - the completion status of this task.
   */
  public boolean getComplete() {
    return this.complete;
  }

  /**
   * sets the completion status of this task.
   *
   * @param complete - the completion status of this task.
   */
  public void setComplete(Boolean complete) {
    this.complete = complete;
  }

  /**
   * returns the string representation of this task.
   *
   * @return - the string representation of this task.
   */
  public String toString(boolean withTitle) {
    String message = "";

    if (withTitle) {
      message += this.getName() + "\n";
    }

    message += this.getDescription() + "\n";
    message += this.getDay().toString() + "\n";
    message += "Category: " + this.getCategory().getName() + "\n";
    String completeMessage = this.complete ? "Completed!" : "Not Completed!";
    message += completeMessage += "\n";
    return message;
  }

  /**
   * returns a JSON Record representing this task.
   *
   * @return - a JSON Record representing this task.
   */
  public TaskJson toJson() {
    String completeMessage = this.complete ? "true" : "false";
    return new TaskJson(this.getName(), this.getDescription(), this.getCategory().getName(),
        completeMessage);
  }
}
