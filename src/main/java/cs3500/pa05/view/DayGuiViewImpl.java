package cs3500.pa05.view;

import cs3500.pa05.controller.JournalController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * Represents a simple BulletJournal GUI view.
 */
public class DayGuiViewImpl implements DayGuiView {
  private final FXMLLoader loader;

  /**
   * Loads an instance of a simple BulletJournal GUI layout from disk.
   *
   * @param controller the controller to be used with the layout
   */
  public DayGuiViewImpl(JournalController controller) {
    // look up and store the layout
    this.loader = new FXMLLoader();

    this.loader.setLocation(getClass().getClassLoader().getResource("BulletJournal.fxml"));

    this.loader.setController(controller);

  }

  /**
   * Loads the scene from the simple BulletJournal GUI layout.
   *
   * @return the layout
   */
  public Scene load() {
    // load the layout
    try {
      return this.loader.load();
    } catch (IOException exc) {
      throw new IllegalStateException("Unable to load layout.");
    }
  }
}
