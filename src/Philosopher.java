package src;

/**
 * Philosopher class
 * A philosopher will dine, which includes eating when they can get two
 * chopsticks and thinking in between eating
 */
public class Philosopher implements Runnable {

    public static void main(String[] args) {

    }

    // how long will the philosopher think and eat?
    private final int THINK_TIME = 200 * 1;
    private final int EAT_TIME = 200 * 1;

    // philosopher's chopsticks
    private final Chopstick leftChopstick;
    private final Chopstick rightChopstick;

    // philosopher's thread
    private final Thread thread;
    // is the philosopher currently dining
    private boolean dining;

    // number of times each philosopher has eaten
    private int eatCount = 0;
    // identifier for the philosophers
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
        thread = new Thread(this);
    }

    /**
     * Starts the philosopher dining
     */
    public void startDining() {
        dining = true;
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
    public synchronized void grabChopsticks() {
        boolean left = leftChopstick.acquire(this);
        boolean right = rightChopstick.acquire(this);

        // if we can get both chopsticks then eat
        if (left & right) {
            eat();
            eatCount++;
            releaseChopsticks();
            think();
            // if we can only get one then release it so someone else can eat
        } else if (left) {
            leftChopstick.release();
        } else if (right) {
            rightChopstick.release();
        }
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
     * Returns the philospher's id
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
