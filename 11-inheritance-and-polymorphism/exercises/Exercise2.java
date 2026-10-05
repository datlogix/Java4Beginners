// Exercise 2: Savings and current accounts.
//
// Write an abstract Account class and two subclasses so that every check in
// main() passes.
//
// Account (abstract)
//   - private final String number, private final String owner,
//     protected double balance (subclasses need to change it)
//   - getters for number, owner and balance
//   - deposit(amount): rejects amounts <= 0
//   - withdraw(amount): rejects amounts <= 0, and amounts more than
//     availableToWithdraw(); otherwise takes the money out
//   - double availableToWithdraw(): just the balance, in Account
//   - abstract void monthEnd(): what happens at the end of each month
//   - equals(): two accounts are equal if they have the same NUMBER
//     (and override hashCode to match: Objects.hash(number))
//   - toString(): e.g.  "SAV-001 Ama: GHS 1010.00"
//
// SavingsAccount extends Account
//   - monthEnd() adds 1% interest to the balance and resets the
//     withdrawal count
//   - withdraw() refuses a 4th withdrawal in the same month (override it,
//     count the withdrawal, and call super.withdraw for the rest)
//
// CurrentAccount extends Account
//   - has an overdraft limit (given to the constructor)
//   - availableToWithdraw() is the balance PLUS the overdraft limit
//     (so the balance can go negative, down to -limit)
//   - monthEnd() charges a GHS 5 fee, plus 2% of the overdrawn amount if
//     the balance is negative
//
// Run it with:  java Exercise2.java

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Exercise2 {

    // TODO: abstract static class Account { ... }

    // TODO: static class SavingsAccount extends Account { ... }

    // TODO: static class CurrentAccount extends Account { ... }

    // ---------------- When your classes exist, remove the /* and */ below ----------------

    static int passed = 0, failed = 0;

    static void check(String description, Object actual, Object expected) {
        if (String.valueOf(actual).equals(String.valueOf(expected))) {
            passed++;
            System.out.println("PASS  " + description);
        } else {
            failed++;
            System.out.println("FAIL  " + description + ": got " + actual + ", expected " + expected);
        }
    }

    static boolean refuses(Runnable action) {
        try {
            action.run();
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    public static void main(String[] args) {
        /*
        SavingsAccount sav = new SavingsAccount("SAV-001", "Ama");
        sav.deposit(1000);
        sav.monthEnd();
        check("1% interest", sav, "SAV-001 Ama: GHS 1010.00");
        sav.withdraw(10);
        sav.withdraw(10);
        sav.withdraw(10);
        check("4th withdrawal in a month refused", refuses(() -> sav.withdraw(10)), true);
        sav.monthEnd();
        check("new month allows withdrawals again", refuses(() -> sav.withdraw(10)), false);
        check("can't withdraw more than the balance", refuses(() -> sav.withdraw(5000)), true);
        check("negative deposit refused", refuses(() -> sav.deposit(-5)), true);

        CurrentAccount cur = new CurrentAccount("CUR-001", "Kofi", 500);
        cur.deposit(200);
        cur.withdraw(600);
        check("overdraft lets balance go negative", cur.getBalance(), -400.0);
        check("can't go past the overdraft limit", refuses(() -> cur.withdraw(200)), true);
        cur.monthEnd();
        check("fee plus 2% of overdrawn amount", cur.getBalance(), -413.0);

        List<Account> all = new ArrayList<>(List.of(sav, cur));
        double total = 0;
        for (Account a : all) {
            total += a.getBalance();
        }
        check("polymorphic total", Math.round(total * 100) / 100.0, 566.8);
        check("equal if same number", new SavingsAccount("X-1", "A").equals(new CurrentAccount("X-1", "B", 0)), true);
        Set<Account> set = new HashSet<>(List.of(sav, new SavingsAccount("SAV-001", "Ama again")));
        check("HashSet sees the duplicate", set.size(), 1);
        */
        System.out.println();
        System.out.println(passed + " passed, " + failed + " failed.");
    }
}
