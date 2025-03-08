public class PhilosopherGroup {

    public static void main(String[] args) {
        PhilosopherGroup pg = new PhilosopherGroup(5);

        pg.startThreads();

        delay(); // TODO

        pg.stopThreads(); // TODO: Implement

        // TODO: Print summary statistics

    }

    private final Philosopher[] philosophers;
    private final Chopstick[] chopsticks;

    public PhilosopherGroup(int num_philosophers) {
        philosophers = new Philosopher[num_philosophers];
        chopsticks = new Chopstick[num_philosophers];
        
        // TODO: Instantiate chopsticks

        // Instantiate philosophers
        for (int i = 0; i < philosophers.length; i++) {
            if (i + 1 == philosophers.length) {
                philosophers[i] = new Philosopher(chopsticks[i], chopsticks[0]);
            } else {
                philosophers[i] = new Philosopher(chopsticks[i], chopsticks[i + 1]);
            }
        }
    }

    public void startThreads() {
        // TODO: Start each philosopher

    }


}
