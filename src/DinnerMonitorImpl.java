import java.util.Arrays;

public class DinnerMonitorImpl extends Dinner {

    public DinnerMonitorImpl(int n_philosophers) {
        super(n_philosophers);
        this.philosophers = new Object[n_philosophers];

        for (int i = 0; i < n_philosophers; i++) {
            this.states[i] = States.THINKING;
            this.philosophers[i] = new Object();
        }
    }

    @Override
    public void take_fork(int p_id) {
        this.states[p_id] = States.HUNGRY;
        System.out.println("Philosopher " + p_id + " is hungry");
        System.out.println(Arrays.toString(states));

        synchronized (this.philosophers[p_id]) {
            test(p_id);

            if (this.states[p_id] != States.EATING) {
                try {
                    this.philosophers[p_id].wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(Arrays.toString(states));
    }

    @Override
    public void put_fork(int p_id) {
        this.states[p_id] = States.THINKING;
        System.out.println("Philosopher " + p_id + " is thinking");
        System.out.println(Arrays.toString(states));

        int left = getLeft(p_id);

        if (canEat(left)) {
            philosophers[left] = States.EATING;
            System.out.println("Philosopher " + left + " is eating");
            System.out.println(Arrays.toString(states));
            synchronized (philosophers[left]) {
                philosophers[left].notify();
            }
        }

        int right = getRight(p_id);

        if (canEat(right)) {
            philosophers[right] = States.EATING;
            System.out.println("Philosopher " + right + " is eating");
            System.out.println(Arrays.toString(states));
            synchronized (philosophers[right]) {
                philosophers[right].notify();
            }
        }

        System.out.println(Arrays.toString(states));
    }

    @Override
    protected void test(int position) {
        if (canEat(position)) {
            this.states[position] = States.EATING;
            System.out.println("Philosopher " + position + " is eating");
            System.out.println(Arrays.toString(states));
        }
    }
}
