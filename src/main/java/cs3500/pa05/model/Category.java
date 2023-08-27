package cs3500.pa05.model;

import java.util.ArrayList;

/**
 * Represents a category of ScheduleItems created by the user.
 */
public class Category {
  private final String category;

  /**
   * Builds a new Category of given name.
   *
   * @param nameOfCategory - the name of the category to use.
   */
  public Category(String nameOfCategory) {
    this.category = nameOfCategory;
  }

  /**
   * Returns a shallow copy of this category's name.
   *
   * @return - this category's name.
   */
  public String getName() {
    return this.category;
  }

  /**
   * Returns a list of unique categories found from given list of Journal Days.
   *
   * @param days - list of days to extract categories from
   * @return - list of unique categories found from given list of days
   */
  public static ArrayList<Category> getCategoriesFrom(ArrayList<JournalDay> days) {
    ArrayList<Category> uniqueCategories = new ArrayList<>();

    for (JournalDay dayFromList : days) {
      ArrayList<Category> categoriesFromDay = dayFromList.getCategories();
      for (Category toConsiderAdding : categoriesFromDay) {
        if (!uniqueCategories.contains(toConsiderAdding)) {
          uniqueCategories.add(toConsiderAdding);
        }
      }
    }


    return uniqueCategories;
  }

  /**
   * Override .equals to compare based on category names exclusively
   *
   * @param o - the object we are checking for equality
   * @return - whether the two have the same name
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof Category) {
      return this.category.equals(((Category) o).category);
    }

    return false;
  }

  /**
   * @return a string representation of this category (the name)
   */
  @Override
  public String toString() {
    return this.category;
  }
}
