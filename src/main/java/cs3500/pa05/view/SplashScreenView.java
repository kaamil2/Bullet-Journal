package cs3500.pa05.view;

import cs3500.pa05.controller.JournalController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * Represents a view for a splash screen
 */
public class SplashScreenView implements DayGuiView {

  /**
   * The loader of an FXML layout.
   */
  private final FXMLLoader loader;

  /**
   * Constructs a new view for a splash screen.
   *
   * @param preLoadController the controller to be used with the layout
   */
  public SplashScreenView(JournalController preLoadController) {
    // look up and store the layout
    this.loader = new FXMLLoader();

    this.loader.setLocation(getClass().getClassLoader().getResource("PreLoader.fxml"));

    this.loader.setController(preLoadController);

  }

  /**
   * Loads a scene from a BulletJournal GUI layout.
   *
   * @return the layout
   */
  @Override
  public Scene load() throws IllegalStateException {
    try {
      return this.loader.load();
    } catch (IOException exc) {
      throw new IllegalStateException("Unable to load layout.");
    }
  }
}
