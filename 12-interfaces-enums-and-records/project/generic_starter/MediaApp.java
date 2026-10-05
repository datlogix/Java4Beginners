// Run it with:
//     javac -d out *.java
//     java -cp out MediaApp

import java.util.Scanner;

public class MediaApp {
    private static final Scanner IN = new Scanner(System.in);

    public static void main(String[] args) {
        MediaLibrary library = new MediaLibrary();
        library.add(new Song("Ye Ye Ye", "Kwesi Arthur", Genre.AFROBEATS, 185));
        library.add(new Song("Kwaku the Traveller", "Black Sherif", Genre.AFROBEATS, 189));
        library.add(new Song("Sweetie Pie", "Daddy Lumba", Genre.HIGHLIFE, 412));
        // TODO: write the PodcastEpisode and Audiobook records, and add several of each

        String choice;
        do {
            System.out.println();
            System.out.println("1) List (choose an order)  2) By genre  3) Rate  4) Top rated  5) Total time  6) Quit");
            choice = ask("Choose: ");
            try {
                switch (choice) {
                    case "1" -> {
                        // TODO: ask "title, longest or genre?", then print library.sorted(...)
                    }
                    case "2" -> {
                        // TODO: list the genres (Genre.values()), ask for one, and print its items
                    }
                    case "3" -> {
                        // TODO
                    }
                    case "4" -> {
                        // TODO: print the top 5 with their stars as * characters
                    }
                    case "5" -> {
                        // TODO
                    }
                    case "6" -> System.out.println("Goodbye.");
                    default -> System.out.println("Please choose 1-6.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Sorry: " + e.getMessage());
            }
        } while (!choice.equals("6"));
    }

    private static String ask(String prompt) {
        System.out.print(prompt);
        return IN.nextLine().trim();
    }
}
