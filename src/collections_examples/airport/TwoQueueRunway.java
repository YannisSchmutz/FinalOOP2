package collections_examples.airport;

import collections_examples.airport.Runway;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implementation of a runway using two queues.
 * Landing flight have a higher priority than departing flights.
 */
public class TwoQueueRunway implements Runway {
  /** Queue containing all flights waiting for departure. */
  private Queue<String> takeoffs = new LinkedList<>();

  /** Queue containing all flights waiting to land. */
  private Queue<String> landings = new LinkedList<>();

  /** Shutdown flag for the airport. */
  private boolean shutDown = false;

  @Override
  public void enqueueTakeOff(String flightNumber) {
    takeoffs.add(flightNumber);
  }

  @Override
  public void enqueueLanding(String flightNumber) {
    landings.add(flightNumber);
  }

  @Override
  public void handleNextFlight() {
    if (landings.size() > 0) {
      String flightNr = landings.remove();
      System.out.println("Flight " + flightNr +", cleared to land.");
    } else if (takeoffs.size() > 0) {
      String flightNr = takeoffs.remove();
      System.out.println("Flight " + flightNr +", cleared for take off.");
    } else {
      System.out.println("no flights to handle");
    }
  }

  @Override
  public void shutdown() { shutDown = true; }

  @Override
  public boolean isInOperation() {
    return !shutDown;
  }
}
