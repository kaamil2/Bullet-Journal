package cs3500.pa05.controller.handlers;

import cs3500.pa05.model.BulletJournal;
import cs3500.pa05.model.CommitmentMonitor;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.JournalDay;
import cs3500.pa05.model.JournalWeek;
import java.util.ArrayList;
import javafx.stage.Stage;

/**
 * handles opening a .bujo as a template
 */
public class OpenTemplateHandler {

  /**
   * the stage the file chooser will be displayed on
   */
  private final Stage toDisplayOn;

  /**
   * @param toDisplayOn the stage the file chooser will be displayed on
   */
  public OpenTemplateHandler(Stage toDisplayOn) {
    this.toDisplayOn = toDisplayOn;
  }

  /**
   * allows the user the open a .bujo file to be opened as a template
   * the monitor and
   *
   * @return a template of the BulletJournal represented by the chosen .bujo file
   */
  public BulletJournal openTemplate() {
    OpenFileHandler fileOpener = new OpenFileHandler(this.toDisplayOn);
    BulletJournal template = fileOpener.openFile();

    ArrayList<JournalDay> days = new ArrayList<>();
    for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
      days.add(new JournalDay(dayOfWeek));
    }

    JournalWeek week = new JournalWeek(template.getWeek().getName(), days, template.getWeek()
        .getCategories());

    CommitmentMonitor monitor =
        new CommitmentMonitor(week, template.getMonitor().getMaxTasksPerDay(),
            template.getMonitor().getMaxEventsPerDay());

    return new BulletJournal(week, monitor);
  }
}
