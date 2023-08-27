package cs3500.pa05.model;

import java.util.ArrayList;

/**
 * represents a list of Schedule Items on a single day
 */
public class ScheduleItemList {
  private final ArrayList<ScheduleItem> itemList;

  /**
   * Builds a new ScheduleItemList composed of the given list of items
   *
   * @param itemList the list of ScheduleItems this represents
   */
  public ScheduleItemList(ArrayList<ScheduleItem> itemList) {
    this.itemList = itemList;
  }

  /**
   * Builds a new empty ScheduleItemList
   */
  public ScheduleItemList() {
    this(new ArrayList<>());
  }

  /**
   * Returns the items associated with this list.
   *
   * @return - a shallow copy of this list of Schedule Items
   */
  public ArrayList<ScheduleItem> getItemList() {
    return new ArrayList<>(this.itemList);
  }

  /**
   * adds the given commitment to this list of ScheduleItems
   *
   * @param commitment the commitment to be added to this list
   */
  public void addCommitment(ScheduleItem commitment) {
    this.itemList.add(commitment);
  }

  /**
   * Removes the given item from the list. Returns true if it was found and removed, false if not.
   *
   * @param commitment the commitment to be removed to this list
   * @return whether the item was found in the list and removed or not
   */
  public boolean removeCommitment(ScheduleItem commitment) {
    return this.itemList.remove(commitment);
  }

  /**
   * Returns the number of tasks in this list.
   *
   * @return - the number of tasks in this list
   */
  public int countTasks() {
    int count = 0;
    for (ScheduleItem item : this.itemList) {
      if (item instanceof Task) {
        count++;
      }
    }
    return count;
  }

  /**
   * Returns the number of events in this list.
   *
   * @return - the number of events in this list
   */
  public int countEvents() {
    int count = 0;
    for (ScheduleItem item : this.itemList) {
      if (item instanceof Event) {
        count++;
      }
    }
    return count;
  }

  /**
   * @return a list of the categories in this
   */
  public ArrayList<Category> getCategories() {
    ArrayList<Category> categoriesFound = new ArrayList<>();

    for (ScheduleItem item : this.itemList) {
      categoriesFound.add(item.getCategory());
    }

    return categoriesFound;
  }

  /**
   * counts the number of completed tasks on this day
   *
   * @return the number of completed tasks on this day
   */
  public int countCompleteTasks() {
    int ans = 0;
    for (ScheduleItem item : this.itemList) {
      if (item instanceof Task task) {
        if (task.getComplete()) {
          ans++;
        }
      }
    }
    return ans;
  }

}
