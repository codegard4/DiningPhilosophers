package src;

import java.util.Random;

/**
 * Philosopher class
 * A philosopher will dine, which includes eating when they can get two
 * chopsticks and thinking in between eating
 */
public class Philosopher implements Runnable {
    /**
     * Static flag to enable verbose logging of philosopher actions
     */
    private static boolean verbose = false;

    /**
     * Set the verbose flag for all philosophers
     *
     * @param flag true to enable verbose logging, false to disable
     */
    public static void setVerbose(boolean flag) {
        verbose = flag;
    }

    /**
     * The maximum duration in milliseconds that a philosopher will think before attempting
     * to eat again
     * - We pick random 1 to MAX_THINK_TIME to determine think time.
     */
    private final int MAX_THINK_TIME = 1000 * 1;

    /**
     * The maximum duration in milliseconds that a philosopher will spend eating
     * - We pick random 1 to MAX_EAT_TIME to determine eat time.
     */
    private final int MAX_EAT_TIME = 1000 * 1;

    /**
     * The chopstick to the philosopher's left
     */
    private final Chopstick leftChopstick;

    /**
     * The chopstick to the philosopher's right
     */
    private final Chopstick rightChopstick;

    /**
     * The philosopher's thread.
     */
    private Thread thread; // When I tried setting this as final and initializing it in the constructor, I
    // got a warning when running using ant.

    /**
     * is the philosopher currently dining
     */
    private boolean dining;

    /**
     * number of times each philosopher has eaten
     */
    private int eatCount = 0;

    /**
     * Unique identifier for this philosopher
     */
    private final int id;

    /*
     * Random number generator for eating and thinking times
     */
    private final Random random = new Random();

    /**
     * Constructor for the philosopher
     *
     * @param id    unique id
     * @param left  left chopstick
     * @param right right chopstick
     */
    public Philosopher(int id, Chopstick left, Chopstick right) {
        leftChopstick = left;
        this.id = id;
        rightChopstick = right;
    }

    /**
     * Starts the philosopher dining
     */
    public void startDining() {
        dining = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Stops the philosopher from dining
     */
    public void stopDining() {
        dining = false;
    }

    /**
     * Runs the thread
     * Continuously tries to grab chopsticks, eat, and think while dining is true
     */
    @Override
    public void run() {
        while (dining) {
            grabChopsticks();
        }
    }

    /**
     * Try to grab the chopsticks
     * If you can only get one then release it
     * If you can get both, eat then think
     */
    public void grabChopsticks() {
        // Try to acquire the first chopstick
        boolean hasFirst = leftChopstick.acquire(this);
        if (!hasFirst) {
            return; // Couldn't get the first chopstick, try again later
        }
        boolean hasSecond = rightChopstick.acquire(this);
        if (!hasSecond) {
            // Couldn't get the second chopstick, release the first
            leftChopstick.release();
            return;
        }

        // Print verbose output when both chopsticks are acquired
        if (verbose) {
            System.out.printf("Philosopher %d: Acquired chopsticks %d and %d%n",
                    id, leftChopstick.getId(), rightChopstick.getId());
            System.out.flush();
        }

        // Got both chopsticks, eat
        eat();
        eatCount++;

        // Print verbose output when releasing chopsticks
        if (verbose) {
            System.out.printf("Philosopher %d: Releasing chopsticks %d and %d%n",
                    id, leftChopstick.getId(), rightChopstick.getId());
            System.out.flush();
        }

        // Release the chopsticks
        rightChopstick.release();
        leftChopstick.release();

        // Think after eating
        think();
    }

    /**
     * Give up both of the chopsticks
     */
    public void releaseChopsticks() {
        if (verbose) {
            System.out.printf("Philosopher %d: Forced release of chopsticks %d and %d%n",
                    id, leftChopstick.getId(), rightChopstick.getId());
            System.out.flush();
        }
        leftChopstick.release();
        rightChopstick.release();
    }

    /**
     * Eat for a certain amount of time
     */
    public void eat() {
        try {
            Thread.sleep(random.nextInt(MAX_EAT_TIME) + 1);
        } catch (InterruptedException ignore) {
        }
    }

    /**
     * A philosopher thinks for a certain amount of time
     */
    public void think() {
        try {
            Thread.sleep(random.nextInt(MAX_THINK_TIME) + 1);
        } catch (InterruptedException ignore) {
        }
    }

    /**
     * Returns the number of times a philosopher has eaten
     *
     * @return number of eats
     */
    public int getEatCount() {
        return eatCount;
    }

    /**
     * Returns the philosopher's id
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Philosopher " + id;
    }

}
