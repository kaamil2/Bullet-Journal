package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.BulletJournal;
import cs3500.pa05.model.CommitmentMonitor;
import cs3500.pa05.model.JournalWeek;

/**
 * Represents a BulletJournal in JSON form. Retains number of maxEvents/Tasks allowed.
 *
 * @param maxEvents - max num of events per day allowed in journal
 * @param maxTasks  - max num of tasks per day allowed in journal
 */
public record JournalJson(@JsonProperty("max-events") String maxEvents,
                          @JsonProperty("max-tasks") String maxTasks,
                          @JsonProperty("journal-week") WeekJson journalWeek) {

  /**
   * Converts this Journal record into a BulletJournal object.
   *
   * @return - a BulletJournal corresponding to the information in this record.
   */
  public BulletJournal toJournal() {
    JournalWeek journalWeekObject = journalWeek.toWeek();
    CommitmentMonitor monitorToUse =
        new CommitmentMonitor(journalWeekObject, Integer.parseInt(maxTasks),
            Integer.parseInt(maxEvents));

    return new BulletJournal(journalWeekObject, monitorToUse);
  }
}
