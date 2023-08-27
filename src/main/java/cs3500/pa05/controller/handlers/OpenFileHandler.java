package cs3500.pa05.controller.handlers;

import cs3500.pa05.model.BulletJournal;
import cs3500.pa05.model.FileReader;
import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * handles opening a .bujo file
 */
public class OpenFileHandler {
  private final Stage toDisplayOn;

  /**
   * @param toShowDialogOn the stage that the open file dialog will the shown on
   */
  public OpenFileHandler(Stage toShowDialogOn) {
    this.toDisplayOn = toShowDialogOn;
  }

  /**
   * Handles opening a file
   *
   * @return the BulletJournal that was read out of the file
   */
  public BulletJournal openFile() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(
        new FileChooser.ExtensionFilter(".bujo", "*.bujo"));
    File file = fileChooser.showOpenDialog(this.toDisplayOn);
    if (file != null) {
      FileReader reader = new FileReader();
      return reader.convertFileToWeek(file);
    } else {
      throw new IllegalStateException("could not open file");
    }
  }
}
