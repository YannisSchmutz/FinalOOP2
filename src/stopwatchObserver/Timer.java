package stopwatchObserver;

import java.util.Observable;

/**
 * The class Timer implements a timer.
 */
public class Timer extends Observable implements Runnable {
	/**
	 * The number of ticks.
	 */
	private int ticks;

	/**
	 * The time interval (in milliseconds) of a tick.
	 */
	private int interval;

	/**
	 * The number of milliseconds of a second.
	 */
	private final double factor = 1000.0;

	/**
	 * The gui of the stopwatchObserver.
	 */
	//private Stopwatch gui;

	/**
	 * The thread which triggers the ticks.
	 */
	private Thread thread;

	/**
	 * Creates a Timer object.
	 * 
	 * @param intv
	 *            the time interval (in milliseconds) of the timer
	 */
	public Timer(int intv) {

		this.interval = intv;
	}

	/**
	 * Attachs a Gui to the timer
	 * 
	 * @param g
	 *            the Stopwatch object to attach to the timer
	 */
	//public final void attach(Stopwatch g) {
	//
	//	this.gui = g;
	//}

	/**
	 * @return the time (in seconds) of the timer.
	 */
	public final double getTime() {
		return ticks * interval / factor;
	}

	/**
	 * @return the time (in seconds) converted to a String.
	 */
	public final String getTimeString() {
		return String.valueOf(this.getTime());
	}

	/**
	 * @return true if the timer is running, otherwise false.
	 */
	public final boolean isRunning() {
		return thread != null;
	}

	/**
	 * Starts the timer.
	 */
	public final void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.setDaemon(true);
			thread.start();
			//gui.update();
			this.setChanged();
			this.notifyObservers();
		}
	}

	/**
	 * Stops the timer.
	 */
	public final void stop() {
		if (thread != null) {
			thread = null;
		}
		//gui.update();
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Resets the time of the timer.
	 */
	public final void reset() {
		ticks = 0;
		//gui.update();
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Triggers ticks at the expiration of the time interval.
	 */
	@Override
	public final void run() {
		while (thread != null) {
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				// do nothing
			}
			if (thread != null) {
				ticks++;
				//gui.update();
				this.setChanged();
				this.notifyObservers();
			}
		}
	}
}
