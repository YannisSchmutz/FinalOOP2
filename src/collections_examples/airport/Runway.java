package collections_examples.airport;

/** Interface of a runway at an airport */
public interface Runway {

  /**
   * Enqueue a departing flight for this runway. The flight is currently
   * still waiting for take-off clearance.
   * @param flightNumber flight number of the departing flight.
   */
  void enqueueTakeOff(String flightNumber);

  /**
   * Enqueue a landing flight for this runway. The flight has not
   * received landing clearance yet.
   * @param flightNumber flight number of the landing flight.
   */
  void enqueueLanding(String flightNumber);

  /**
   * Handle the next flight on this runway (departing, or landing).
   */
  void handleNextFlight();

  /** Shutdown this runway. */
  void shutdown();

  /** Returns true until the runway is shutdown. */
  boolean isInOperation();

}
