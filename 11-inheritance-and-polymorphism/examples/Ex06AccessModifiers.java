// Example 6: who can see what? private, (package), protected, public.
// Run it with:  java Ex06AccessModifiers.java

public class Ex06AccessModifiers {

    static class Account {
        public final String number;        // anyone
        protected double balance;          // this class, its subclasses, and the same package
        String branch = "Accra";           // no word: anything in the same package
        private String pin = "1234";       // ONLY code inside Account

        Account(String number) {
            this.number = number;
        }

        boolean checkPin(String attempt) {
            return pin.equals(attempt);    // Account's own code can use pin
        }
    }

    static class SavingsAccount extends Account {
        SavingsAccount(String number) {
            super(number);
        }

        void addInterest(double rate) {
            balance += balance * rate;     // allowed: balance is protected
            // pin = "0000";               // error: pin has private access in Account
        }
    }

    public static void main(String[] args) {
        SavingsAccount s = new SavingsAccount("SAV-001");
        s.balance = 1000;                  // allowed here only because it's the same file/package
        s.addInterest(0.05);
        System.out.println(s.number + ": " + s.balance + " at " + s.branch);
        System.out.println("PIN 1234 ok? " + s.checkPin("1234"));
        // Rule of thumb: fields private, methods public, protected only when
        // subclasses genuinely need direct access.
    }
}
