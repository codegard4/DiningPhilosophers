public class Chopstick {
    public volatile boolean acquired;

    public Chopstick() {
        acquired = false;
    }

    public boolean acquire() {
        if (acquired) {
            return false;
        } else {
            acquired = true;
            return true;
        }
    }

    public void release() {
        if (acquired) {
            acquired = false;
        } else {
            System.err.println("Tried to release unacquired chopstick");
        }
    }
}
