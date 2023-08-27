package cs3500.pa05.controller;

import cs3500.pa05.controller.handlers.CategoryEntryAddHandler;
import cs3500.pa05.model.BulletJournal;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * A controller to be delegated to in the entry of a new Category to be created
 */
public class CategoryEntryController implements JournalController {
  private final BulletJournal toAddTo;

  @FXML
  private Scene categoryEntryScene;

  @FXML
  private VBox categoryWindowContainer;

  @FXML
  private Label categoryInfo;

  @FXML
  private TextField categoryEntryField;

  @FXML
  private HBox categoryButtonContainer;

  @FXML
  private Button categoryAddButton;

  @FXML
  private Button categoryCancelButton;

  /**
   * Builds a new CategoryEntryController that will add a new category (as prompted by the user)
   * to the given BulletJournal
   *
   * @param toAugment - the journal to which the new category will be added to
   */
  public CategoryEntryController(BulletJournal toAugment) {
    this.toAddTo = toAugment;

    this.categoryAddButton.setOnAction(
        new CategoryEntryAddHandler(this.toAddTo, this.categoryEntryField,
            this.categoryEntryScene));
    this.categoryCancelButton.setOnAction(e -> categoryEntryScene.getWindow().hide());
  }

  /**
   * Runs the processes necessary for this CategoryEntryController to use userInput and create
   * a new category as a result for the given bullet journal
   *
   * @throws IllegalStateException - if something is wrong with the BuJo itself
   * @throws IOException - if something went wrong with IO from getting journal
   */
  @Override
  public void run() throws IllegalStateException, IOException {
    this.initButtons();

    FXMLLoader loader =
        new FXMLLoader(getClass().getClassLoader().getResource("CategoryEntryWindow.fxml"));
    loader.setController(this);
    Scene s = loader.load();
  }

  /**
   * Adds the actionHandlers for each of the buttons
   */
  public void initButtons() {
    this.categoryAddButton.setOnAction(
        new CategoryEntryAddHandler(this.toAddTo, this.categoryEntryField,
            this.categoryEntryScene));

    this.categoryCancelButton.setOnAction(e -> categoryEntryScene.getWindow().hide());
  }
}
