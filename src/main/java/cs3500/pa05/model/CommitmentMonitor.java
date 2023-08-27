package cs3500.pa05.model;

import java.util.ArrayList;

/**
 * Responsible for "monitoring" the given week, has a method that determines whether the week
 * has a number of events/tasks exceeding the maximum allowed
 */
public class CommitmentMonitor {
  private final JournalWeek toMonitor;
  private int maxTasksPerDay;
  private int maxEventsPerDay;

  /**
   * Builds a new commitment monitor to ensure that the given week doesn't go beyond the maximum
   * number of tasks and events for any given day.
   *
   * @param week      - the week to monitor
   * @param maxTasks  - maximum number of tasks per day
   * @param maxEvents - maximum number of events per day
   */
  public CommitmentMonitor(JournalWeek week, int maxTasks, int maxEvents) {
    this.toMonitor = week;
    this.maxTasksPerDay = maxTasks;
    this.maxEventsPerDay = maxEvents;
  }

  /**
   * Returns whether this week (to monitor) contains a problematic number of commitments.
   *
   * @return - whether any day in this week has too many events or too many tasks
   */
  public boolean problematicCommitments() {
    return this.problematicNumEvents() || this.problematicNumTasks();
  }

  /**
   * Returns whether any day in this week (to monitor) has too many events.
   *
   * @return - whether any day in this week (to monitor) has too many events.
   */
  private boolean problematicNumEvents() {
    ArrayList<Integer> eventCounts = this.toMonitor.countEvents();

    for (Integer i : eventCounts) {
      if (i > maxEventsPerDay) {
        return true;
      }
    }

    return false;
  }

  /**
   * Returns whether any day in this week (to monitor) has too many tasks.
   *
   * @return - whether any day in this week (to monitor) has too many tasks.
   */
  private boolean problematicNumTasks() {
    ArrayList<Integer> taskCounts = this.toMonitor.countTasks();

    for (Integer i : taskCounts) {
      if (i > maxTasksPerDay) {
        return true;
      }
    }

    return false;
  }

  /**
   * Sets the maximum number of tasks per day to the given number.
   *
   * @param newMax - the new max number of tasks per day
   */
  public void setMaxTasksPerDay(int newMax) {
    this.maxTasksPerDay = newMax;
  }

  /**
   * Sets the maximum number of events per day to the given number.
   *
   * @param newMax - the new max number of events per day
   */
  public void setMaxEventsPerDay(int newMax) {
    this.maxEventsPerDay = newMax;
  }

  /**
   * returns the maximum number of tasks per day this commitmentMonitor allows
   *
   * @return - the maximum number of tasks per day this commitmentMonitor allows
   */
  public int getMaxTasksPerDay() {
    return this.maxTasksPerDay;
  }

  /**
   * returns the maximum number of events per day this commitmentMonitor allows
   *
   * @return - the maximum number of events per day this commitmentMonitor allows
   */
  public int getMaxEventsPerDay() {
    return this.maxEventsPerDay;
  }
}
