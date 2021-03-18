public class Philosopher implements Runnable {

    private final int id;
    private final int eatingDuration;
    private final int thinkingDuration;
    private final Dinner dinner;

    public Philosopher(int id, int eatingDuration, int thinkingDuration, Dinner dinner) {
        this.id = id;
        this.eatingDuration = eatingDuration;
        this.thinkingDuration = thinkingDuration;
        this.dinner = dinner;
        new Thread(this, "Philosopher").start();
    }

    @Override
    public void run() {
        while (true) {
            think();
            take_forks();
            eat();
            put_forks();
        }
    }

    public void think() {
        try {
            Thread.sleep(this.thinkingDuration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void eat() {
        try {
            Thread.sleep(this.eatingDuration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void take_forks() {
        dinner.take_fork(this.id);
    }

    public void put_forks() {
        dinner.put_fork(this.id);
    }

}
