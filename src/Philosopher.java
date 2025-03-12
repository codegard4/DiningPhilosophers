package src;

/**
 * Philosopher class
 * A philosopher will dine, which includes eating when they can get two
 * chopsticks and thinking in between eating
 */
public class Philosopher implements Runnable {
    /**
     * The duration in milliseconds that a philosopher will think before attempting
     * to eat again
     */
    private final int THINK_TIME = 1000 * 1;

    /**
     * The duration in milliseconds that a philosopher spends eating
     */
    private final int EAT_TIME = 1000 * 1;

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
        synchronized (leftChopstick) {
            synchronized (rightChopstick) {
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
                // Got both chopsticks, eat
                eat();
                eatCount++;

                // Release the chopsticks (still in the synchronized blocks)
                rightChopstick.release();
                leftChopstick.release();
            }
        }

        // Think after eating
        think();
    }

    /**
     * Give up both of the chopsticks
     */
    public void releaseChopsticks() {
        leftChopstick.release();
        rightChopstick.release();
    }

    /**
     * Eat for a certain amount of time
     */
    public void eat() {
        try {
            Thread.sleep(EAT_TIME);
        } catch (InterruptedException ignore) {
        }
    }

    /**
     * A philosopher thinks for a certain amount of time
     */
    public void think() {
        try {
            Thread.sleep(THINK_TIME);
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
