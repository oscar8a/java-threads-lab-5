import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Savings Account Threading Example");

        SavingsAccount oscarSavingsAcc = new SavingsAccount();

        // Initial amount
        oscarSavingsAcc.deposit(1000000);

        // Create Threads
        List<Thread> transactionArray = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            if (i % 2 == 0) {
                transactionArray.add(new Thread(() -> loopDeposits(oscarSavingsAcc)));
            } else {
                transactionArray.add(new Thread(() -> loopWithdrawals(oscarSavingsAcc)));
            }
        }

        // Run all threads
        for (Thread t : transactionArray){
            t.start();
        }

        // Run join() on the threads in order for the main program to
        // complete and print final total in correct sequence
        for (Thread t : transactionArray){
            t.join();
        }

        // At current setup, should end up being the same as initial amount
        System.out.println("FINAL TOTAL => " + oscarSavingsAcc.getTotal());
    }

    public static void loopWithdrawals(SavingsAccount acc) {
        System.out.println("Withdrawals thread running...");
        int transactions = 10000, amount = 100, sleep = 100;

        for (int j = 0; j < transactions; j++) {
            acc.withdraw(amount);
            // accountSleep(sleep);
        }
    }

    public static void loopDeposits(SavingsAccount acc) {
        System.out.println("Deposits thread running...");
        int transactions = 10000, amount = 100, sleep = 90;

        for (int i = 0; i < transactions; i++) {
            acc.deposit(amount);
            // accountSleep(sleep);
        }
    }

    static void accountSleep(int sleep) {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
