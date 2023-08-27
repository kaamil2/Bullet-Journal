
package cs3500.pa05.controller;


import cs3500.pa05.controller.handlers.MiniViewerHandler;
import cs3500.pa05.controller.handlers.OpenFileHandler;
import cs3500.pa05.controller.handlers.OpenTemplateHandler;
import cs3500.pa05.controller.handlers.SaveFileHandler;
import cs3500.pa05.model.AbstractItem;
import cs3500.pa05.model.BulletJournal;
import cs3500.pa05.model.Category;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.EventTime;
import cs3500.pa05.model.JournalDay;
import cs3500.pa05.model.ScheduleItem;
import cs3500.pa05.model.Task;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Represents the controller for a game of BulletJournal.
 */
public class JournalControllerImpl implements JournalController {
  private BulletJournal journal;

  private final Application app;

  @FXML
  private VBox taskList;

  @FXML
  private Button saveButton;

  @FXML
  private Button openFileButton;

  // CommitmentPopup

  @FXML
  private Button newCategory;

  @FXML
  private Button filter;

  @FXML
  private Button popupButton;

  @FXML
  private Button categoryAddButton;

  @FXML
  private Hyperlink hyperLink;

  @FXML
  private Button categoryCancelButton;

  @FXML
  private Button popupClose;

  @FXML
  private ComboBox<String> startAmOrPm;
  @FXML
  private ComboBox<String> endAmOrPm;

  @FXML
  private TextField taskSearchBar;

  @FXML
  private Button filterButton;

  @FXML
  private Button cancelFilter;

  @FXML
  private Button popupSave;

  @FXML
  private Button taskSearchButton;

  @FXML
  private Text textArea;

  @FXML
  private TextField scheduleItem;

  @FXML
  private TextField categoryEntryField;

  @FXML
  private ListView<String> listOfAllTasks;

  @FXML
  private TextField description;

  @FXML
  private ComboBox<DayOfWeek> dayPicker;

  @FXML
  private TextField startTime;

  @FXML
  private TextField endTime;

  @FXML
  private CheckBox completed;

  @FXML
  private VBox mondayBox;

  @FXML
  private VBox tuesdayBox;

  @FXML
  private VBox wednesdayBox;

  @FXML
  private VBox thursdayBox;

  @FXML
  private VBox fridayBox;

  @FXML
  private VBox saturdayBox;

  @FXML
  private Button listOfAllTasksClose;

  @FXML
  private MenuButton categoryChoice;

  @FXML
  private VBox sundayBox;

  @FXML
  private MenuButton categoryFilter;

  private final Stage stage;

  private Popup popup;

  private Popup categoryPopup;

  private Popup miniViewer;

  private Popup filterPopup;

  private Popup taskSearchPopup;


  @FXML
  private Label totalEvents;
  @FXML
  private Label totalTasks;
  @FXML
  private Label completedTaskPercent;

  @FXML
  private Label commitmentWarning;

  /**
   * for choosing a new name in the popup
   */
  @FXML
  private TextField newName;
  @FXML
  private Button newNameSave;
  @FXML
  private Button newNameCancel;

  /**
   * for opening a file as a template
   */
  @FXML
  private Button openTemplate;

  @FXML
  private Button setMaxNumButton;

  private Popup maxNumPopup;

  @FXML
  private TextField maxTasks;

  @FXML
  private TextField maxEvents;

  @FXML
  private Button saveMaxTasks;

  @FXML
  private Button saveMaxEvents;

  @FXML
  private Button closeSetMax;


  /**
   * Instantiates a GUI of BulletJournal
   *
   * @param journal the BulletJournal
   * @param stage   the stage that will be displayed on
   * @param app     - the application to reference
   */
  public JournalControllerImpl(BulletJournal journal, Stage stage, Application app) {
    this.journal = Objects.requireNonNull(journal);
    this.stage = stage;
    this.app = app;

  }


  /**
   * Initializes a GUI for a BulletJournal.
   *
   * @throws IllegalStateException if the Journal is undefined
   * @throws IOException           if an error reading from a bujo file
   */
  @FXML
  public void run() throws IllegalStateException, IOException {
    this.runWithoutNewName();
    this.getNewName();

  }

  /**
   * initializes GUI and prompts user for a new name for the week
   *
   * @throws IOException if an input/output exception occurs
   */
  private void runWithoutNewName() throws IOException {
    for (JournalDay day : journal.getWeek().getDaysOfWeek()) {
      addCommitment(day.getScheduleItems().getItemList());
    }
    initIoHandlers();
    initButtons();
  }

  /**
   * initializes the items in the dayPicker to be the days of the week
   */
  private void initComboBoxes() {
    this.dayPicker.getItems().setAll(
        DayOfWeek.SUNDAY,
        DayOfWeek.MONDAY,
        DayOfWeek.TUESDAY,
        DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY,
        DayOfWeek.FRIDAY,
        DayOfWeek.SATURDAY);
    this.startAmOrPm.getItems().setAll(
        "AM",
        "PM");
    this.endAmOrPm.getItems().setAll(
        "AM",
        "PM");
  }

  /**
   * Initializes the buttons in the view, like the new category button, the search button,
   * etc.
   */
  @FXML
  private void initButtons() {
    newCategory.setOnAction(event -> makeCategoryPopup());
    newCategoryPopup();


    popupButton.setOnAction(e -> makePopup());
    popupButtonHandler();


    filter.setOnAction(e -> makeFilterPopup());


    taskSearchButton.setOnAction(e -> makeTaskSearchPopup());

    setMaxNumButton.setOnAction(e -> {
      makeMaxNumPopup();
    });


  }

  private void makeMaxNumPopup() {
    maxNumPopup = new Popup();
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader()
        .getResource("numOfItems.fxml"));
    loader.setController(this);
    Scene scene;
    try {
      scene = loader.load();
    } catch (IOException e1) {
      throw new RuntimeException(e1);
    }
    maxNumPopup.getContent().add(scene.getRoot());
    maxNumPopup.show(stage);
    saveMaxTasks.setOnAction(
        e -> {
          try {
          this.journal.getMonitor().setMaxTasksPerDay(Integer.parseInt(maxTasks.getText()));
            } catch (NumberFormatException e1) {
              maxNumPopup.hide();
            }
        });
    saveMaxEvents.setOnAction(
        e -> {
          try {
            this.journal.getMonitor().setMaxEventsPerDay(Integer.parseInt(maxEvents.getText()));
          } catch (NumberFormatException e1) {
            maxNumPopup.hide();
          }
        });
    closeSetMax.setOnAction(e -> maxNumPopup.hide());

    try {
      reRender();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }


  }

  /**
   * Creates the popUp for the taskSearch results
   */
  private void makeTaskSearchPopup() {
    taskSearchPopup = new Popup();
    FXMLLoader loader3 = new FXMLLoader(getClass().getClassLoader()
        .getResource("listOfAllTasks.fxml"));
    loader3.setController(this);
    Scene s3;
    try {
      s3 = loader3.load();
      taskSearchPopup.getContent().add(s3.getRoot());
    } catch (IOException e1) {
      System.err.println("failed to load Search");
    }
    taskSearchPopup.show(this.stage);
    listOfAllTasks.getItems().clear();
    listOfAllTasks.getItems().addAll(searchList(taskSearchBar.getText(), this.getTaskList()));
    listOfAllTasksClose.setOnAction(e1 -> taskSearchPopup.hide());
    try {
      reRender();
    } catch (IOException exc) {
      throw new RuntimeException(exc);
    }
  }

  /**
   * Creates the popup for filtering by category
   */
  private void makeFilterPopup() {
    this.filterPopup = new Popup();
    FXMLLoader loader2 = new FXMLLoader(getClass().getClassLoader()
        .getResource("FilterPopup.fxml"));
    loader2.setController(this);
    Scene s2;
    try {
      s2 = loader2.load();
    } catch (IOException e1) {
      throw new RuntimeException(e1);
    }
    filterPopup.getContent().add(s2.getRoot());
    filterPopup.show(this.stage);
    categoryFilter.getItems().clear();
    categoryFilter.getItems().add(new MenuItem("No Filter"));
    categoryChoice(categoryFilter);

    cancelFilter.setOnAction(e1 -> filterPopup.hide());
    filterButton.setOnAction(e1 -> {
      clearAllDays();
      filterDays(categoryFilter.getText());
      filterPopup.hide();

      initButtons();
    });
  }

  /**
   * Creates the handlers for popup buttons when creating a new item
   */
  private void popupButtonHandler() {
    this.popup = new Popup();
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("popup.fxml"));
    loader.setController(this);
    Scene s;
    try {
      s = loader.load();
      popup.getContent().add(s.getRoot());
    } catch (IOException e) {
      System.err.println("failed to load popup");
    }
    categoryChoice.getItems().clear();

    categoryChoice(categoryChoice);

    popupClose.setOnAction(e -> popup.hide());
    popupSave.setOnAction(e -> {
      this.journal.addCommitment(handlePopUpText());
      popup.hide();
      addCommitment(new ArrayList<>(Collections.singletonList(handlePopUpText())));
      initButtons();
    });
  }

  /**
   * Creates the popup for the making a new category
   */
  private void newCategoryPopup() {
    this.categoryPopup = new Popup();
    //categoryPopup.show(this.stage);
    FXMLLoader loaders = new FXMLLoader(getClass().getClassLoader()
        .getResource("CategoryEntryWindow.fxml"));
    loaders.setController(this);
    Scene ss;
    try {
      ss = loaders.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    categoryPopup.getContent().add(ss.getRoot());
    categoryCancelButton.setOnAction(b -> categoryPopup.hide());
    categoryAddButton.setOnAction(d -> {
      //NEED TO ALSO CHECK IF CATEGORY ALREADY EXISTS

      this.journal.getWeek().newCategory(categoryEntryField.getText());

      categoryPopup.hide();
      initButtons();
    });
  }

  /**
   * shows the popup for changing the name of this journal
   */
  private void getNewName() {
    Popup popup = new Popup();
    FXMLLoader loaders = new FXMLLoader(getClass().getClassLoader()
        .getResource("changeNamePopup.fxml"));
    loaders.setController(this);
    Scene scene;
    try {
      scene = loaders.load();
      popup.getContent().add(scene.getRoot());
    } catch (IOException e) {
      System.err.println("failed to load popup");
    }
    this.newNameCancel.setOnAction(e -> popup.hide());

    this.newNameSave.setOnAction(event -> {
      this.journal.getWeek().setName(this.newName.getText());
      stage.setTitle(this.journal.getWeek().getName());
      popup.hide();
      try {
        this.reRender();
      } catch (IOException e) {
        System.err.println("failed to refresh page");
      }
    });

    popup.show(this.stage);
  }

  /**
   * Builds a list for all the categories that the user can choose from when adding
   * a new commitment
   *
   * @param categoryChoice - the button we want the available categories added to as a
   *                       child (since it is a menu)
   */
  private void categoryChoice(MenuButton categoryChoice) {
    for (Category c : this.journal.getWeek().getCategories()) {
      categoryChoice.getItems().add(new MenuItem(c.getName()));
    }

    for (int i = 0; i < categoryChoice.getItems().size(); i++) {
      int finalI = i;
      categoryChoice.getItems().get(i).setOnAction(e ->
          categoryChoice.setText(categoryChoice.getItems().get(finalI).getText()));
    }
  }

  /**
   * Searches the given list of tasks for tasks which contain given text, ignoring case
   *
   * @param text     - the text we are searching for tasks that contain it
   * @param allTasks - the tasks we want to search through
   * @return - a list of strings which represent task names that include given string
   */
  private List<String> searchList(String text, ArrayList<String> allTasks) {
    List<String> filteredList = Arrays.asList(text.trim().split(" "));

    return allTasks.stream().filter(task -> filteredList.stream().allMatch(word ->
        task.toLowerCase().contains(word.toLowerCase()))).collect(Collectors.toList());
  }

  /**
   * Returns a list of taskNames contained within the journal this controller references
   *
   * @return - a list of taskNames contained within this controller's journal
   */
  private ArrayList<String> getTaskList() {
    ArrayList<String> taskList = new ArrayList<>();
    for (JournalDay day : journal.getWeek().getDaysOfWeek()) {
      for (ScheduleItem item : day.getScheduleItems().getItemList()) {
        if (item instanceof Task) {
          if (item.getName().toLowerCase().contains(taskSearchBar.getText().toLowerCase())) {
            taskList.add(item.getName());
          }
        }
      }
    }
    return taskList;
  }

  /**
   * Handles the filtering of categories and what is displayed on the GUI.
   *
   * @param text - the name of the category we want to filter by, or "No Filter" if we
   *             want no filter and to continue to display every element
   */
  private void filterDays(String text) {
    ArrayList<ScheduleItem> filteredList = new ArrayList<>();
    if (!text.equals("No Filter")) {
      for (JournalDay day : journal.getWeek().getDaysOfWeek()) {
        for (ScheduleItem item : day.getScheduleItems().getItemList()) {
          if (item.getCategory().getName().equals(text)) {
            filteredList.add(item);
          }
        }
      }
      addCommitment(filteredList);
    } else {
      for (JournalDay day : journal.getWeek().getDaysOfWeek()) {
        addCommitment(day.getScheduleItems().getItemList());
      }
    }
  }

  /**
   * Removes all the elements of every DayBox
   */
  private void clearAllDays() {
    this.mondayBox.getChildren().clear();
    this.tuesdayBox.getChildren().clear();
    this.wednesdayBox.getChildren().clear();
    this.thursdayBox.getChildren().clear();
    this.fridayBox.getChildren().clear();
    this.saturdayBox.getChildren().clear();
    this.sundayBox.getChildren().clear();
    Node taskListLabel = this.taskList.getChildren().get(0);
    this.taskList.getChildren().clear();
    this.taskList.getChildren().add(taskListLabel);
  }

  /**
   * Makes the newCategory window appear
   */
  @FXML
  private void makeCategoryPopup() {
    this.categoryPopup.show(this.stage);
  }

  /**
   * Adds given objects to box with given ordinal number than outlined in the DayOfWeek enum
   */
  @FXML
  private void addToBox(ArrayList<Object> children, int num) {
    VBox boxToAddTo;

    if (num == 0) {
      boxToAddTo = mondayBox;
    } else if (num == 1) {
      boxToAddTo = tuesdayBox;
    } else if (num == 2) {
      boxToAddTo = wednesdayBox;
    } else if (num == 3) {
      boxToAddTo = thursdayBox;
    } else if (num == 4) {
      boxToAddTo = fridayBox;
    } else if (num == 5) {
      boxToAddTo = saturdayBox;
    } else {
      boxToAddTo = sundayBox;
    }

    for (Object child : children) {
      boxToAddTo.getChildren().add((Node) child);
    }
  }

  /**
   * Makes the addCommitment popup window appear
   */
  @FXML
  private void makePopup() {
    this.initComboBoxes();
    this.popup.show(this.stage);
  }

  /**
   * Creates some handlers for the save and openFile buttons located
   * in the top left of the GUI
   */
  @FXML
  private void initIoHandlers() {
    this.saveButton.setOnAction(new SaveFileHandler(this.stage, this.journal));
    this.openFileButton.setOnAction(e -> {
      try {
        this.journal = new OpenFileHandler(this.stage).openFile();
        this.stage.setTitle(this.journal.getWeek().getName());
        this.reRender();
      } catch (IllegalStateException ignored) {
        // ignore and do nothing
      } catch (IOException ex) {
        System.err.println("failed to refresh page");
      }
    });

    this.openTemplate.setOnAction(e -> {
      try {
        this.journal = new OpenTemplateHandler(this.stage).openTemplate();
        this.reRender();
        this.getNewName();
      } catch (IOException ex) {
        System.err.println("failed to refresh page");
      }
    });
  }

  /**
   * event handler for the addCommit button
   * adds a new commitment to the journal
   *
   * @param list the list of commitments to be added
   */
  public void addCommitment(ArrayList<ScheduleItem> list) {
    ArrayList<Task> tasks = new ArrayList<>();


    for (ScheduleItem item : list) {
      Button itemButton = new Button(item.getName());
      itemButton.setBackground(null);
      //this calls upon show mini viewer to create the mini viewer for the item
      itemButton.setCursor(Cursor.HAND);
      itemButton.setOnAction(event -> {
        this.miniViewer = new Popup();
        showMiniViewer(miniViewer);
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader()
            .getResource("miniView.fxml"));
        loader.setController(this);
        Scene x;
        try {
          x = loader.load();

          hyperLink.setText(parseForHttp(item.getDescription()));
          hyperLink.setOnAction(e -> app.getHostServices()
              .showDocument(parseForHttp(item.getDescription())));

          textArea.setText(item.toString(false));


        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        miniViewer.getContent().add(x.getRoot());
        Button b = new Button("Close");
        miniViewer.getContent().add(b);
        b.setOnAction(d -> miniViewer.hide());
      });


      Text itemInfoForUser = new Text(item.toString(false));
      //sets the wrap for the text
      itemInfoForUser.setWrappingWidth(100);
      itemInfoForUser.setTextAlignment(TextAlignment.CENTER);
      itemInfoForUser.setFont(Font.font(10));

      ArrayList<Object> itemsForContainer = new ArrayList<>();
      itemsForContainer.add(itemButton);
      itemsForContainer.add(itemInfoForUser);

      if (item instanceof Task task) {
        tasks.add(task);

        CheckBox complete = new CheckBox("Complete:");
        complete.setSelected(task.getComplete());
        complete.setOnAction(event -> {
          task.setComplete(complete.isSelected());

          try {
            complete.setSelected(task.getComplete());
            reRender();
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
          this.updateWeeklyOverview();
        });

        itemsForContainer.add(complete);
      }
      addToBox(itemsForContainer, item.getDay().ordinal());
    }
    handleTaskListAdd(tasks);

    this.updateWeeklyOverview();
    this.handleCommitmentWarning();
  }

  /**
   * Parses a String for HTTP links
   *
   * @param description - the String we want to parse for links
   * @return - a String that is a link, if present, null if none
   */
  @FXML
  private String parseForHttp(String description) {
    String[] words = description.split(" ");
    String link = null;
    for (String word : words) {
      if (word.startsWith("http://") || word.startsWith("https://")) {
        link = word;
      }
    }
    return link;
  }

  /**
   * Displays the miniViewer for a given scheduleItem on the given stage using given Popup
   *
   * @param miniViewer - the popup to be displayed on / called by the handler
   */
  private void showMiniViewer(Popup miniViewer) {
    MiniViewerHandler miniViewerHandler =
        new MiniViewerHandler(miniViewer);
    this.miniViewer = miniViewerHandler.getMiniViewer();
    Scene s = miniViewerHandler.handle();
    this.miniViewer.getContent().add(s.getRoot());
    this.miniViewer.show(this.stage);
  }

  /**
   * event handler for the add button
   * adds the schedule item to the journal
   *
   * @return the schedule item to be added to the journal
   */
  @FXML
  public AbstractItem handlePopUpText() {
    DayOfWeek day = this.dayPicker.getValue();

    this.journal.getWeek().newCategory(this.categoryChoice.getText());
    Category categories = null;
    for (Category category : this.journal.getWeek().getCategories()) {
      if (category.getName().equals(this.categoryChoice.getText())) {
        categories = category;
      }
    }

    // entries for start and end time --> make an event
    if (!this.startTime.getText().equals("") && !this.endTime.getText().equals("")) {
      return new Event(this.scheduleItem.getText(), this.description.getText(), day,
          categories,
          new EventTime(day + ", " + startTime.getText() + " " + this.startAmOrPm.getValue(),
              day + ", " + endTime.getText() + " " + this.endAmOrPm.getValue()));
    } else {
      return new Task(this.scheduleItem.getText(), this.description.getText(), day,
          categories, false);
    }
  }

  /**
   * Handles the adding of given list of tasks to the view
   *
   * @param tasks - a list of tasks to be added
   */
  @FXML
  public void handleTaskListAdd(ArrayList<Task> tasks) {
    for (Task task : tasks) {
      String taskInfo = task.toString(true);
      Text user = new Text(taskInfo);
      user.setTextAlignment(TextAlignment.CENTER);
      user.setWrappingWidth(100);
      user.setFont(Font.font(11));

      String info = task.toString(true).substring(0, task.toString(true).length()
          - 15);
      for (int i = 0; i < taskList.getChildren().size(); i++) {
        if (taskList.getChildren().get(i).toString().contains(info)) {
          taskList.getChildren().remove(i);
        }
      }
      taskList.getChildren().add(user);
    }
  }

  /**
   * updates the weekly overview with the:
   * - number of events
   * - number of tasks
   * - percent of tasks completed
   */
  @FXML
  private void updateWeeklyOverview() {
    int numEvents = this.journal.numOfEvents();
    int numTasks = this.journal.numOfTasks();
    this.totalEvents.setText("Total Events: " + numEvents);
    this.totalTasks.setText("Total Tasks: " + numTasks);
    Double percentComplete = ((double) this.journal.countCompleteTasks() / (double) numTasks) * 100;
    if (percentComplete.isNaN()) {
      percentComplete = 0.0;
    }
    NumberFormat formatter = new DecimalFormat("###");
    String percentAsString = "Completed Tasks: " + formatter.format(percentComplete) + "%";
    this.completedTaskPercent.setText(percentAsString);
  }

  /**
   * Resets the children within the days and runs the controllers processes again...
   * starts fresh!
   *
   * @throws IOException - if an error is thrown during running of controller due to loading
   */
  private void reRender() throws IOException {
    this.stage.setTitle(this.journal.getWeek().getName());
    clearAllDays();
    this.runWithoutNewName();
  }

  /**
   * updates the visibility of the commitment warning as appropriate based on
   * whether the journal has "problematic commitments"
   */
  public void handleCommitmentWarning() {
    this.commitmentWarning.setVisible(this.journal.problematicCommitments());
  }


}
