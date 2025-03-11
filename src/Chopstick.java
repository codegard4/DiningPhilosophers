package src;

/**
 * Chopstick class
 * A chopstick can be acquired by an owner and released
 */
public class Chopstick {
    /**
     * Flag indicating whether the chopstick is currently acquired by a philosopher
     */
    private volatile boolean acquired;

    /**
     * Unique identifier for this chopstick
     */
    private final int id;

    /**
     * Reference to the philosopher currently owning this chopstick, or null if not acquired
     */
    private Philosopher owner;
    
    /**
     * Static flag to enable verbose logging of chopstick state changes
     */
    private static boolean verbose = false;
    
    /**
     * Set the verbose flag for all chopsticks
     * 
     * @param flag true to enable verbose logging, false to disable
     */
    public static void setVerbose(boolean flag) {
        verbose = flag;
    }

    /**
     * Constructor for a chopstick
     *
     * @param id the unique identifier for this chopstick
     */
    public Chopstick(int id) {
        acquired = false;
        this.id = id;
        owner = null;
    }

    /**
     * Allows a philosopher to acquire a chopstick
     *
     * @param owner the philosopher attempting to acquire this chopstick
     * @return whether the chopstick was successfully acquired
     */
    public synchronized boolean acquire(Philosopher owner) {
        if (acquired) {
            // Because we are using spinloop, we are not going to print verbose messages here, as it will be too much
            return false; // Chopstick is already in use
        } else {
            this.owner = owner;
            acquired = true;
            if (verbose) {
                System.out.println("Chopstick " + id + ": Acquired by Philosopher " + owner.getId());
            }
            return true;
        }
    }

    /**
     * Releases the chopstick
     */
    public synchronized void release() {
        if (acquired) {
            if (verbose && owner != null) {
                System.out.println("Chopstick " + id + ": Released by Philosopher " + owner.getId());
            }
            acquired = false;
            owner = null;
        } else {
            System.err.println("Tried to release unacquired chopstick: " + id + " Owner: " + owner);
        }
    }
}
