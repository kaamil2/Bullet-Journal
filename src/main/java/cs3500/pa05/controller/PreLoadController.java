package cs3500.pa05.controller;

/**
 * Controller for the PreLoad/Splash of the app
 */
public class PreLoadController implements JournalController {

  private void splash() {
    new Thread(() -> {
    }).start();
  }

  /**
   * Initializes a game of Whack-A-Mole.
   *
   * @throws IllegalStateException if the game board is not defined
   */
  @Override
  public void run() throws IllegalStateException {
    this.splash();
  }
}
