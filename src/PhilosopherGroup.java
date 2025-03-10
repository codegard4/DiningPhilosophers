package src;

/**
 * A philosopher group is a group of philosophers that dine together
 * Dining includes each philosopher attempting to get two chopsticks, eating
 * then thinking
 */
public class PhilosopherGroup {

    public static void main(String[] args) {
        PhilosopherGroup pg = new PhilosopherGroup(5);
        pg.dine();
    }

    private final int DINING_TIME = 5000;
    private final Philosopher[] philosophers;
    private final Chopstick[] chopsticks;

    /**
     * Constructor for the philosopher group
     * 
     * @param num_philosophers number of philosophers and chopsticks
     */
    public PhilosopherGroup(int num_philosophers) {
        philosophers = new Philosopher[num_philosophers];
        chopsticks = new Chopstick[num_philosophers];

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
     * Starts all of the philosophers eating & thinking
     */
    public void startThreads() {
        for (Philosopher p : philosophers) {
            p.startDining();
        }

    }

    /**
     * Stops all of the philosophers from eating & thinking
     */
    public void stopThreads() {
        for (Philosopher p : philosophers) {
            p.stopDining();
        }
    }

    public void summarizeDining() {
        for (Philosopher p : philosophers) {
            System.out.println("Philosopher " + p.getId() + " ate " + p.getEatCount() + " times");
        }
    }

}
