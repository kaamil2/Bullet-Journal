package cs3500.pa05.view;

import javafx.scene.Scene;

/**
 * Represents a GUI view for a game of BulletJournal.
 */
public interface DayGuiView {
  /**
   * Loads a scene from a BulletJournal GUI layout.
   *
   * @return the layout
   * @throws IllegalStateException if the layout cannot be loaded
   */
  Scene load() throws IllegalStateException;
}
