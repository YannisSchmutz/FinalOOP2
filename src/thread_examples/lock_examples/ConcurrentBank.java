package thread_examples.lock_examples;

import java.util.Random;

/**
 * Exercise 4: Problem 1 a concurrent bank that contains a number of bank accounts
 * and then uses several threads to perform random transactions among the accounts.
 * This serves to illustrate the problem of data races, solving it through
 * synchronization, i.e., the two bank accounts involved in a transaction are locked
 * via their respective monitors. However, this can lead to a deadlocks.
 *
 * T1 (A1 -> A2): { locks A1, locks A2, A1.withdraw, A2.deposit, unlock A2, unlock A1}
 * T2 (A2 -> A1): { locks A2, locks A1, A2.withdraw, A1.deposit, unlock A1, unlock A2}
 *
 *               Thread T1              Thread T2
 * ----------------------------------------------------------
 * time 1        lock A1
 * time 2                               lock A2
 * time 3        lock A2 -> T1 blocks
 * time 4                               lock A -> T2 blocks
 *               --------------- DEADLOCK ------------------
 *
 * The way to break the circular dependency (4th of the necessary condition for deadlocks)
 * is make the sequence in which the accounts are locked unique, such that, T1 and T2
 * will lock the accounts in the same order. This can be easily achieved by the choosing
 * the lock order based on the account number, e.g., a thread locks the account with the
 * smaller account first.
 *
 *               Thread T1              Thread T2
 * ----------------------------------------------------------
 * time 1        lock A1
 * time 2                               lock A1 -> T2 blocks
 * time 3        lock A2
 * time 4        operation A1 -> A2
 * time 5        unlock A2
 * time 6        unlock A1
 * time 7                               T2 gets lock for A1
 * time 8                               lock A2
 * time 9                               operation A2 -> A1
 * time 10                              unlock A2
 * time 11                              unlock A1
 */
public class ConcurrentBank {

    /** A Bank Account with an integer balance. */
    static class BankAccount {
        /** Account number */
        private final int accountNumber;

        /** Current balance of the account. */
        private int balance;
        /** Account-specific random number generator to choose credit account for transfers. */
        private Random random = new Random();

        /** Create Bank Account with specified initial balance. */
        public BankAccount(int accountNumber, int initialBalance) {
            this.accountNumber = accountNumber;
            balance = initialBalance;
        }

        /**
         * Deposit the specified amount in the account.
         * Note that this method is synchronized.
         *
         * @param amount positive amount
         */
        public synchronized void deposit(int amount) { balance += amount; }

        /**
         * Withdraw the specified amount from the account.
         * Note that this method is synchronized.
         *
         * @param amount positive amount
         * @throws IllegalArgumentException if amount is negative or greater than
         *         the balance.
         */
        public synchronized void withdraw(int amount) throws IllegalArgumentException {
            if (amount < 0 || amount > balance) {
                throw new IllegalArgumentException("invalid balance");
            }
            balance -= amount;
        }

        /** Returns the account number. */
        public int getAccountNumber() { return accountNumber; }

        /** Returns the current balance. */
        public int getBalance() { return balance; }

        /**
         * Transfer a random amount of money from this account to the other account.
         * @param other credit account.
         */
        public void transferRandomAmountTo(BankAccount other) {
            // Avoid deadlock by first locking the account with the smaller account number
            // This breaks the 4th condition: circular dependency.
            if (this.accountNumber < other.accountNumber) {
                synchronized(this) {
                    synchronized(other) {
                        int amount = random.nextInt(balance == 0 ? 1 : balance);
                        this.withdraw(amount);
                        other.deposit(amount);
                    }
                }
            } else {
                synchronized(other) {
                    synchronized(this) {
                        int amount = random.nextInt(balance == 0 ? 1 : balance);
                        this.withdraw(amount);
                        other.deposit(amount);
                    }
                }
            }
        }

        @Override
        public String toString() {
            return "BankAccount(balance="+balance+")";
        }
    }

    /** Number of bank accounts to create. */
    private static final int NUM_ACCOUNTS = 100;

    /** Number of threads that perform operations concurrently. */
    private static final int NUM_THREADS = 10;

    /** Number of seconds to run experiment. */
    private static final int RUN_TIME_SECONDS = 10;

    /** Initial balance of bank accounts. */
    private static final int INITAL_BALANCE = 1000;

    public static void main(String[] args) {
        // Create bank accounts.
        BankAccount[] accounts = new BankAccount[NUM_ACCOUNTS];
        for (int i = 0; i < NUM_ACCOUNTS; i++) {
            accounts[i] = new BankAccount(i, INITAL_BALANCE);
        }

        // Starting threads...
        Thread[] threads = new Thread[NUM_THREADS];
        for (int tid = 0; tid < NUM_THREADS; tid++) {
            threads[tid] = new Thread(() -> {
                Random rnd = new Random();
                while (!Thread.interrupted()) {
                    int i = rnd.nextInt(NUM_ACCOUNTS);
                    int j;
                    do {
                        j = rnd.nextInt(NUM_ACCOUNTS);
                    } while (j == i);
                    BankAccount debit = accounts[i];
                    BankAccount credit = accounts[j];
                    debit.transferRandomAmountTo(credit);
                }
            });
            threads[tid].start();
        }

        // Wait for some time ...
        System.out.println("letting " + NUM_THREADS + " threads execute random transactions for " +
                RUN_TIME_SECONDS + " seconds...");
        try {
            Thread.sleep(1000 * RUN_TIME_SECONDS);
        } catch (InterruptedException e) { e.printStackTrace(); }
        // ... then interrupt thread an join it.
        System.out.println("interrupting threads!");
        for (Thread t : threads) {
            t.interrupt();
            try { t.join(); } catch(InterruptedException ignored) { }
        }

        // Check balance
        long sum = 0;
        for (BankAccount account : accounts) {
            sum += account.getBalance();
        }

        System.out.println("total balance: " + sum);
        if (sum != ((long)NUM_ACCOUNTS) * INITAL_BALANCE) {
            System.out.println("DATA RACE");
        } else {
            System.out.println("ok, not data race");
        }
    }
}
