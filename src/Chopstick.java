package src;
/**
 * Chopstick class
 * A chopstick can be acquired by an owner and released
 */
public class Chopstick {
    public volatile boolean acquired;
    public final int id;
    public Philosopher owner;

    /**
     * Constructor for a chopstick
     */
    public Chopstick(int id) {
        acquired = false;
        this.id = id;
        owner = null;
    }

    /**
     * Allows a philosopher to acquire a chopstick
     * @return whether the chopstick can be acquired
     */
    public synchronized boolean acquire(Philosopher owner) {
        if (acquired) {
            return false;
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
            System.err.println("Tried to release unacquired chopstick: " + id + " Owner: " + owner);
        }
    }
}
