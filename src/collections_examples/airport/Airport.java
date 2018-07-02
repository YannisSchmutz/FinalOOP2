package collections_examples.airport;

import java.util.Scanner;

public class Airport {
  /** The single runway of the airport. */
  private Runway runway = new PriorityQueueRunway(); //new TwoQueueRunway();


  /** Interface representing a command typed in by the user. */
  interface Command {
    void execute();
  }

  /** Command handling the submission of a take-off. */
  class TakeOffCommand implements Command {
    final String flightNumber;
    TakeOffCommand(String flightNumber) { this.flightNumber = flightNumber; }

    @Override
    public void execute() {
      runway.enqueueTakeOff(flightNumber);
    }

    @Override
    public String toString() {
      return "TakeOff(" + flightNumber + ")";
    }
  }

  /** Command to handle the submission a landing. */
  class LandCommand implements Command {
    final String flightNumber;
    LandCommand(String flightNumber) { this.flightNumber = flightNumber; }

    @Override
    public void execute() {
      runway.enqueueLanding(flightNumber);
    }

    @Override
    public String toString() {
      return "Land(" + flightNumber + ")";
    }
  }

  /** Command to handle the next runway use. */
  class NextCommand implements Command {
    @Override
    public void execute() {
      runway.handleNextFlight();
    }
  }

  /** Command to exit the program. */
  class ExitCommand implements Command {
    @Override
    public void execute() {
      runway.shutdown();
    }
  }

  /**
   * Prompt the user for the next command and parse the input.
   * @param scanner where to read the user input from (e.g., new Scanner(System.in)
   * @param airport airport to operate on
   * @return user entered command (repeats until a valid command has been entered).
   */
  private static Command getNextComment(Scanner scanner, Airport airport) {
    String prompt = "command> ";
    while(true) {
      System.out.print(prompt);
      String textLine = scanner.nextLine().trim();
      if (textLine.equals("next")) {
        return airport.new NextCommand();
      } else if (textLine.equals("quit") || textLine.equals("exit")) {
        return airport.new ExitCommand();
      } else if (textLine.startsWith("takeoff")) {
        String flightNumber = textLine.substring("takeoff".length()).trim();
        return airport.new TakeOffCommand(flightNumber);
      } else if (textLine.startsWith("land")) {
        String flightNumber = textLine.substring("land".length()).trim();
        return airport.new LandCommand(flightNumber);
      } else {
        System.out.println("invalid command '" + textLine + "'");
        System.out.println("valid commands: 'takeoff flightnr', 'land flightnr', 'next', 'quit'");
      }
    }
  }

  /** Test Program. */
  public static void main(String[] args) {
    Airport airport = new Airport();
    Scanner scanner = new Scanner(System.in);
    do {
      Command command = getNextComment(scanner, airport);
      command.execute();
    } while (airport.runway.isInOperation());
    scanner.close();
  }
}
