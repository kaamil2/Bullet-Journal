package cs3500.pa05.controller.handlers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Popup;


/**
 * Represents a controller for a game of popupButtonHandler.
 */
public class PopupButtonHandler {

  private Popup popup;

  @FXML
  private Button popupClose;

  @FXML
  private Button popupSave;


  /**
   * Constructs a new controller for the given journal.
   **/
  public PopupButtonHandler() {

  }

  /**
   * Initializes a popup button handler
   */
  public void popupButtonHandeler() {
    this.popup = new Popup();
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("popup.fxml"));
    loader.setController(this);
    Scene s;
    try {
      s = loader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

}
