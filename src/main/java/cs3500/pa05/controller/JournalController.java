package cs3500.pa05.controller;

import java.io.IOException;

/**
 * Represents a controller for the Week journal view
 */
public interface JournalController {
  /**
   * Initializes a JournalController.
   *
   * @throws IllegalStateException if the week board is not defined
   * @throws IOException           if the week board cannot be read
   */
  void run() throws IllegalStateException, IOException;
}
