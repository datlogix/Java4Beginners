// Run it with:
//     javac -d out *.java
//     java -cp out AtmApp

import java.util.HashMap;
import java.util.Map;

public class AtmApp {

    public static void main(String[] args) {
        Map<String, Account> bank = new HashMap<>();
        bank.put("1001", new Account("1001", "Akosua Darko", "4321", 1500));
        bank.put("1002", new Account("1002", "Yaw Mensah", "0000", 80));

        while (true) {
            String number = Input.readLine("\nInsert card (account number), or q to quit: ");
            if (number.equalsIgnoreCase("q")) {
                break;
            }
            Account account = bank.get(number);
            if (account == null) {
                System.out.println("Card not recognised.");
                continue;
            }
            // TODO: ask for the PIN until it's right, or the account locks (catch
            //       InvalidPinException and AccountLockedException)
            // TODO: then a menu: balance, withdraw, deposit, mini-statement, eject card.
            //       Catch every checked exception with a friendly message. NOTHING may crash.
        }
        System.out.println("Goodbye.");
    }
}
