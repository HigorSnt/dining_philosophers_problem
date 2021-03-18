public class DiningPhilosophersMain {

    private static final int PHILOSOPHERS_NUMBERS = 5;
    private static final int[] PHILOSOPHERS_EATING_DURATION = {100, 100, 100, 100, 100};
    private static final int[] PHILOSOPHERS_THINKING_DURATION = {100, 100, 100, 100, 100};

    public static void main(String[] args) {
        Dinner dinner = new DinnerSemaphoreImpl(PHILOSOPHERS_NUMBERS);
        //Dinner dinner = new DinnerMonitorImpl(PHILOSOPHERS_NUMBERS);

        for (int i = 0; i < PHILOSOPHERS_NUMBERS; i++) {
            new Philosopher(i, PHILOSOPHERS_EATING_DURATION[i], PHILOSOPHERS_THINKING_DURATION[i], dinner);
        }
    }

}

