import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class DinnerSemaphoreImpl extends Dinner {

    private final Semaphore mutex;

    public DinnerSemaphoreImpl(int n_philosophers) {
        super(n_philosophers);
        this.mutex = new Semaphore(1);
        this.states = new States[n_philosophers];
        this.philosophers = new Semaphore[n_philosophers];

        for (int i = 0; i < n_philosophers; i++) {
            this.states[i] = States.THINKING;
            this.philosophers[i] = new Semaphore(0);
        }
    }

    private void down(Semaphore semaphore) {
        try {
            semaphore.acquire();
            System.out.println(Arrays.toString(states));
        } catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }
    }

    private void up(Semaphore semaphore) {
        semaphore.release();
        System.out.println(Arrays.toString(states));
    }

    @Override
    public void take_fork(int p_id) {
        down(mutex);
        this.states[p_id] = States.HUNGRY;
        System.out.println("Philosopher " + p_id + " is hungry");
        System.out.println(Arrays.toString(states));

        test(p_id);

        up(mutex);
        down((Semaphore) philosophers[p_id]);
    }

    @Override
    public void put_fork(int p_id) {
        down(mutex);
        this.states[p_id] = States.THINKING;
        System.out.println("Philosopher " + p_id + " is thinking");
        System.out.println(Arrays.toString(states));

        test(getLeft(p_id));
        test(getRight(p_id));

        up(mutex);
    }

    @Override
    protected void test(int position) {
        if (canEat(position)) {
            ((Semaphore) philosophers[position]).release();
            this.states[position] = States.EATING;
            System.out.println("Philosopher " + position + " eating");
            System.out.println(Arrays.toString(states));
        }
    }
}
