// Run it with:
//     javac -d out *.java
//     java -cp out LibraryApp

import java.util.Scanner;

public class LibraryApp {
    private static final Scanner IN = new Scanner(System.in);

    public static void main(String[] args) {
        Library library = new Library("Makerspace Library");
        loadStartingData(library);

        String choice;
        do {
            System.out.println();
            System.out.println("1) Search  2) Lend  3) Return  4) Report  5) Add book  6) Quit");
            choice = ask("Choose: ");
            try {
                switch (choice) {
                    case "1" -> {
                        // TODO: ask for text, print each matching book (or "No matches.")
                    }
                    case "2" -> {
                        // TODO: ask for the ISBN and member ID, call library.lend, say "Done."
                    }
                    case "3" -> {
                        // TODO
                    }
                    case "4" -> System.out.println(library.report());
                    case "5" -> {
                        // TODO
                    }
                    case "6" -> System.out.println("Goodbye.");
                    default -> System.out.println("Please choose 1-6.");
                }
            } catch (IllegalArgumentException e) {
                // Every rule broken anywhere in the classes ends up here, as a friendly message.
                System.out.println("Sorry: " + e.getMessage());
            }
        } while (!choice.equals("6"));
    }

    private static void loadStartingData(Library library) {
        library.addBook(new Book("978-0385474542", "Things Fall Apart", "Chinua Achebe"));
        library.addBook(new Book("978-1101971062", "Homegoing", "Yaa Gyasi"));
        library.addMember(new Member("M001", "Kofi Mensah"));
        // TODO: at least three more books and two more members
    }

    private static String ask(String prompt) {
        System.out.print(prompt);
        return IN.nextLine().trim();
    }
}
