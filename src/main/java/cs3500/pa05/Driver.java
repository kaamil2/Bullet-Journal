package cs3500.pa05;

import cs3500.pa05.controller.JournalController;
import cs3500.pa05.controller.JournalControllerImpl;
import cs3500.pa05.controller.PreLoadController;
import cs3500.pa05.model.BulletJournal;
import cs3500.pa05.model.Category;
import cs3500.pa05.model.CommitmentMonitor;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.JournalDay;
import cs3500.pa05.model.JournalWeek;
import cs3500.pa05.view.DayGuiView;
import cs3500.pa05.view.DayGuiViewImpl;
import cs3500.pa05.view.SplashScreenView;
import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * The main driver of this project.
 */
public class Driver extends Application {
  /**
   * Starts the GUI for a game of BulletJournal.
   *
   * @param stage the JavaFX stage to add elements to
   */
  @Override
  public void start(Stage stage) throws IOException {


    // add a title to the stage
    ArrayList<JournalDay> journalDayList = new ArrayList<>();
    for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
      journalDayList.add(new JournalDay(dayOfWeek));
    }

    ArrayList<Category> categories = new ArrayList<>();
    categories.add(new Category("DEFAULT"));

    JournalWeek week = new JournalWeek("test", journalDayList, categories);
    CommitmentMonitor monitor = new CommitmentMonitor(week, 3, 3);
    BulletJournal model = new BulletJournal(week, monitor);
    Application app = this;
    JournalController journal = new JournalControllerImpl(model, stage, app);

    // instantiate the view
    DayGuiView wgv = new DayGuiViewImpl(journal);

    JournalController preLoadController = new PreLoadController();

    DayGuiView splashScreen = new SplashScreenView(preLoadController);

    try {
      stage.setScene(splashScreen.load());

      preLoadController.run();

      stage.show();

      PauseTransition delay = new PauseTransition(javafx.util.Duration.seconds(2));


      delay.setOnFinished(event -> {
        try {
          stage.setScene(wgv.load());
          journal.run();
          // load and place the view's scene onto the stage
          stage.setScene(wgv.load());

          // run controller
          journal.run();


        } catch (IOException e) {
          System.err.println("Unable to load GUI.");
        }
      });

      delay.play();

    } catch (IllegalStateException exc) {
      System.err.println(exc.getMessage());

    }

  }

  /**
   * Entry point for a BulletJournal.
   *
   * @param args the command line arguments... ignored
   */
  public static void main(String[] args) {
    launch(args);
  }
}
