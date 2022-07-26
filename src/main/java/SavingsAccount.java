public class SavingsAccount {

    private long total = 0;

    public synchronized boolean withdraw(long amount) {
        // why return boolean? how can I use this on main?
        // Should I let my total be negative?
        if (amount > total) {
            // If this case hits, final amount won't be the initial amount
            System.out.println("not enough balance... transaction canceled");
            return false;
        } else {
            total -= amount;
            System.out.println("withdrew => " + amount + " || Total: " + getTotal());
            return true;
        }
    }

    public synchronized void deposit(long amount) {
        total += amount;
        System.out.println("deposited => " + amount + " || Total: " + getTotal());
    }

    public long getTotal() {
        return total;
    }
}