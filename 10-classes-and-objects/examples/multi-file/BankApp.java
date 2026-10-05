// A program split across three files. Compile them all, then run the one with main:
//
//     javac -d out *.java
//     java -cp out BankApp
//
// -d out puts the .class files in a folder called out, keeping your source folder tidy.
// -cp out ("class path") tells java where to find them.

public class BankApp {
    public static void main(String[] args) {
        Bank bank = new Bank("Makerspace Credit Union");
        BankAccount ama = bank.open("Ama");
        BankAccount kojo = bank.open("Kojo");
        ama.deposit(250);
        kojo.deposit(80.5);
        ama.deposit(40);
        bank.printStatement();
    }
}
