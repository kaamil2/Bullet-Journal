package cs3500.pa05.model;

import cs3500.pa05.model.json.JournalJson;

/**
 * represents the entire BulletJournal
 */
public class BulletJournal {

  /**
   * represents the week in this journal
   */
  private final JournalWeek week;
  /**
   * the CommitmentMonitor that will ensure too many commitments aren't made
   */
  private final CommitmentMonitor monitor;

  /**
   * @param week    the week in this journal
   * @param monitor the monitor for this week's tasks
   */
  public BulletJournal(JournalWeek week, CommitmentMonitor monitor) {
    this.week = week;
    this.monitor = monitor;
  }

  /**
   * @return the commitment monitor for this journal
   */
  public CommitmentMonitor getMonitor() {
    return monitor;
  }

  /**
   * adds the given commitment on the given day
   *
   * @param commitment the commitment to be added to this week

   */
  public void addCommitment(ScheduleItem commitment) {
    this.week.addCommitment(commitment);
  }

  /**
   * adds the given category to this week
   *
   * @param categoryName the name of the category to be added
   */
  public void newCategory(String categoryName) {
    this.week.newCategory(categoryName);
  }

  /**
   * checks if the max number of commitments has been reached
   *
   * @return if the max number of commitments is exceeded
   */
  public boolean problematicCommitments() {
    return this.monitor.problematicCommitments();
  }

  /**
   * Returns a JSON object representing this BulletJournal.
   *
   * @return - a JSON object representing this BulletJournal.
   */
  public JournalJson toJson() {
    return new JournalJson(String.valueOf(this.monitor.getMaxEventsPerDay()),
        String.valueOf(this.monitor.getMaxTasksPerDay()), this.week.toJson());
  }

  /**
   * @return the week in this journal
   */
  public JournalWeek getWeek() {
    return this.week;
  }

  /**
   * calculates the total number of tasks contained in this week
   *
   * @return the total number of tasks in this week
   */
  public int numOfTasks() {
    int sum = 0;
    for (Integer i : this.week.countTasks()) {
      sum += i;
    }
    return sum;
  }

  /**
   * calculates the total number of events contained in this week
   *
   * @return the total number of events in this week
   */
  public int numOfEvents() {
    int sum = 0;
    for (Integer i : this.week.countEvents()) {
      sum += i;
    }
    return sum;
  }

  /**
   * counts the number of completed tasks in this week
   *
   * @return the number of competed tasks in this week
   */
  public int countCompleteTasks() {
    return this.week.countCompleteTasks();
  }

}
