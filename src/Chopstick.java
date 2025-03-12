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
            return false; // Chopstick is already in use
        } else {
            this.owner = owner;
            acquired = true;
            return true;
        }
    }

    /**
     * Releases the chopstick
     */
    public synchronized void release() {
        if (acquired) {
            acquired = false;
            owner = null;
        } else {
            System.err.printf("Tried to release unacquired chopstick: %d Owner: %s%n", id, owner);
            System.err.flush(); // Force immediate output of error messages
        }
    }

    public boolean isAvailable() {
        return !acquired;
    }

    /**
     * Returns the chopstick's ID
     *
     * @return id of the chopstick
     */
    public int getId() {
        return id;
    }
}
