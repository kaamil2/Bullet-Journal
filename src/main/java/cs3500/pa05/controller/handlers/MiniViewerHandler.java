package cs3500.pa05.controller.handlers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Popup;

/**
 * Handler for creating a MiniViewer when a ScheduleItem is clicked on in the BulletJournal
 */
public class MiniViewerHandler {

  private final Popup miniViewer;

  /**
   * builds a new mini viewer handler that handles the displaying of information
   *
   * @param miniViewer - the popup window that content is shown on
   */
  public MiniViewerHandler(Popup miniViewer) {
    this.miniViewer = miniViewer;
  }

  /**
   * Returns a scene representing the information in this miniViewer
   *
   * @return - a scene representing the information in this miniViewer
   */
  @FXML
  public Scene handle() {
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader()
        .getResource("miniView.fxml"));
    loader.setController(this);
    Scene x;
    try {
      x = loader.load();
      //textArea.setText(itemToShow.toString());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return x;

  }

  /**
   * Returns a popup of the content in this miniViewer that was generated
   *
   * @return - a popup of the content in this miniViewer that was generated
   */
  public Popup getMiniViewer() {
    this.miniViewer.getContent().add(this.handle().getRoot());

    return this.miniViewer;
  }
}