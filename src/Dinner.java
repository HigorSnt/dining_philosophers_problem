public abstract class Dinner {

    protected int n_philosophers;
    protected States[] states;
    protected Object[] philosophers;

    public Dinner(int n_philosophers) {
        this.n_philosophers = n_philosophers;
        this.states = new States[n_philosophers];
    }

    protected int getLeft(int position) {
        return (position + 1) % n_philosophers;
    }

    protected int getRight(int position) {
        return (position + n_philosophers - 1) % n_philosophers;
    }

    public void take_fork(int p_id) {
    }

    public void put_fork(int p_id) {
    }

    protected boolean canEat(int position) {
        return states[position] == States.HUNGRY
                && states[getLeft(position)] != States.EATING
                && states[getRight(position)] != States.EATING;
    }

    protected void test(int position) {
    }
}
