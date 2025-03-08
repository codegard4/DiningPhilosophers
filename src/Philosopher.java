public class Philosopher implements Runnable {

    private final int THINK_TIME = 1000 * 1;
    private final int EAT_TIME = 1000 * 1;

    private final Chopstick leftChopstick;
    private final Chopstick rightChopstick;

    private Thread thread;
    private boolean dining;

    private int eat_count = 0;

    public Philosopher(Chopstick left, Chopstick right) {
        leftChopstick = left;
        rightChopstick = right;
    }

    public void startDining() {
        dining = true;
        thread.start();
    }

    public void run() {
        while (dining) {
            grabChopsticks();
        }
    }

    public synchronized void grabChopsticks() {
        final boolean left = leftChopstick.acquire();
        final boolean right = rightChopstick.acquire();

        if (left & right) {
            eat();
            releaseChopsticks();
            think();
        } else if (left) {
            leftChopstick.release();
        } else if (right) {
            rightChopstick.release();
        }
    }


    public void releaseChopsticks() {
        leftChopstick.release();
        rightChopstick.release();
    }

    // TODO: Sleep for EAT_TIME amount of time
    // TODO: Track number of eats
    public void eat() {
        try {
            Thread.sleep(EAT_TIME);
        } catch (InterruptedException ignored) {
        }
    }

    // TODO: Sleep for THINK_TIME amount of time after eating
    public void think() {

    }

    public int get_eat_count() {
        return eat_count;
    }
}
