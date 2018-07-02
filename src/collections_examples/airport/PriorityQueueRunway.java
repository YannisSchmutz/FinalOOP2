package collections_examples.airport;

import collections_examples.airport.Runway;

import java.util.PriorityQueue;

/**
 * Implementation of a runway using one PriorityQueue.
 * Landing flight have a higher priority than departing flights.
 */
public class PriorityQueueRunway implements Runway {

  /** A queue entry that is comparable. */
  private static class QueueEntry implements Comparable<QueueEntry> {
    private static int nextSequenceNumber = 0;
    final boolean landing;
    final int sequenceNumber;
    final String flightNumber;

    QueueEntry(boolean landing, String flightNumber) {
      this.landing = landing;
      this.sequenceNumber = nextSequenceNumber++;
      this.flightNumber = flightNumber;
    }

    @Override
    public int compareTo(QueueEntry o) {
      if (landing == o.landing) {
        return Integer.compare(sequenceNumber, o.sequenceNumber);
      } else {
        return (landing) ? -1 : 1;
      }
    }
  }

  /** Queue containing all flights waiting to land. */
  private PriorityQueue<QueueEntry> queue = new PriorityQueue<>();

  /** Shutdown flag for the airport. */
  private boolean shutDown = false;

  @Override
  public void enqueueTakeOff(String flightNumber) {
    queue.add(new QueueEntry(false, flightNumber));
  }

  @Override
  public void enqueueLanding(String flightNumber) {
    queue.add(new QueueEntry(true, flightNumber));
  }

  @Override
  public void handleNextFlight() {
    if (queue.size() == 0) {
      System.out.println("no flights to handle");
    } else {
      QueueEntry entry = queue.remove();
      System.out.println("Flight " + entry.flightNumber + ", " +
          ((entry.landing) ? "cleared to land." : "cleared for take off."));
    }
  }

  @Override
  public void shutdown() {
    shutDown = true;
  }

  @Override
  public boolean isInOperation() {
    return !shutDown;
  }
}
