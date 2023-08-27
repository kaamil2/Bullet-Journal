package cs3500.pa05.controller.handlers;

import cs3500.pa05.model.BulletJournal;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

/**
 * Handler for adding a new category to a journal from a text field.
 */
public class CategoryEntryAddHandler implements EventHandler {
  private final BulletJournal toAddTo;
  private final TextField toReadFrom;
  private final Scene toEventuallyHide;

  /**
   * Builds a new handler that reads from given text field and will write to given journal
   *
   * @param journal - the journal to add the new category to
   * @param toReadFrom - the field to read text and get a new category from
   * @param toHide - the scene to hide after event done.
   */
  public CategoryEntryAddHandler(BulletJournal journal, TextField toReadFrom, Scene toHide) {
    this.toAddTo = journal;
    this.toReadFrom = toReadFrom;
    this.toEventuallyHide = toHide;
  }

  /**
   * Handles an event by adding a new category to this handler's journal from the field
   * based on whatever text is in there.
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {
    String fromField = toReadFrom.toString();
    this.toAddTo.newCategory(fromField);
    this.toEventuallyHide.getWindow().hide();
  }
}