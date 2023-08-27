package cs3500.pa05.model;

/**
 * Used for testing for an illegal argument because this has no Json properties
 */
public record MockNonJsonRecord(String note) {

  /**
   * Throws an exception instead of giving you the information you want so that your tests will
   * fail and, in that way, succeed
   *
   * @return - an exception
   */
  public String note() {
    throw new RuntimeException("hahahahahah");
  }

}
