package src;

/**
 * A Table holds a group of n philosophers that dine together and n chopsticks.
 * Dining includes each philosopher attempting to get two chopsticks, eating
 * then thinking
 */
public class Table {

    /**
     * Main method to start the dining philosophers simulation.
     * Creates a table with 5 philosophers and starts the dining process.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Table table = new Table(5);
        table.dine();
    }

    /**
     * The duration in milliseconds that the philosophers will dine for
     */
    private final int DINING_TIME = 5000;

    /**
     * Flag to determine verbose output mode
     */
    private static final boolean VERBOSE = Boolean.getBoolean("verbose");

    /**
     * Array of philosophers sitting at the table
     */
    private final Philosopher[] philosophers;

    /**
     * Array of chopsticks available at the table
     */
    private final Chopstick[] chopsticks;

    /**
     * Constructor for the philosopher group
     *
     * @param num_philosophers number of philosophers and chopsticks
     */
    public Table(int num_philosophers) {
        philosophers = new Philosopher[num_philosophers];
        chopsticks = new Chopstick[num_philosophers];

        // Set verbose flag for chopsticks
        Chopstick.setVerbose(VERBOSE);

        // instantiate chopsticks
        for (int i = 0; i < chopsticks.length; i++) {
            chopsticks[i] = new Chopstick(i + 1);
        }

        // Instantiate philosophers
        for (int i = 0; i < philosophers.length; i++) {
            if (i + 1 == philosophers.length) {
                philosophers[i] = new Philosopher(i + 1, chopsticks[i], chopsticks[0]);
            } else {
                philosophers[i] = new Philosopher(i + 1, chopsticks[i], chopsticks[i + 1]);
            }
        }
    }

    /**
     * Starts the philosophers dining, gives them time to dine, then stops them
     */
    public void dine() {
        System.out.println("Let the feast begin");
        startThreads();
        try {
            Thread.sleep(DINING_TIME);
        } catch (InterruptedException ignore) {
        }
        System.out.println("I'm full; the feast is done");
        stopThreads();
        summarizeDining();
    }

    /**
     * Starts all the philosophers eating & thinking
     */
    public void startThreads() {
        for (Philosopher p : philosophers) {
            p.startDining();
        }

    }

    /**
     * Stops all the philosophers from eating & thinking
     */
    public void stopThreads() {
        for (Philosopher p : philosophers) {
            p.stopDining();
        }
    }

    /**
     * Prints a summary of how many times each philosopher was able to eat
     * during the dining session
     */
    public void summarizeDining() {
        for (Philosopher p : philosophers) {
            System.out.println("Philosopher " + p.getId() + " ate " + p.getEatCount() + " times");
        }
    }

}
