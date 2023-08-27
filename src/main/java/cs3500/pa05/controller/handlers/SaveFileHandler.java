package cs3500.pa05.controller.handlers;

import cs3500.pa05.model.BulletJournal;
import cs3500.pa05.model.FileWriter;
import java.io.File;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Action handler for the Save Button
 */
public class SaveFileHandler implements EventHandler {
  private final Stage toDisplay;
  private final BulletJournal toSave;

  /**
   * Builds a new handler for the save button that shows on the given stage and will write
   * the given bullet journal to a file.
   *
   * @param toDisplayOn - the stage the file dialogues should be displayed on
   * @param toWrite - the journal we want to write
   */
  public SaveFileHandler(Stage toDisplayOn, BulletJournal toWrite) {
    this.toDisplay = toDisplayOn;
    this.toSave = toWrite;
  }

  /**
   * handles the pressing of the save button by opening up the file dialogue and writing
   * the file as a JSON to the user's chosen path
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(
        new FileChooser.ExtensionFilter("bujo", "*.bujo"));
    File outputFile = fileChooser.showSaveDialog(toDisplay);
    if (outputFile != null) {
      FileWriter writer = new FileWriter();
      writer.writeJournalToFile(this.toSave, outputFile);
    }
  }
}
