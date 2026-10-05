// Example 1: why methods? The same three lines, copied three times...
// and then the same job done by one method, called three times.
// Run it with:  java Ex01WhyMethods.java

public class Ex01WhyMethods {
    public static void main(String[] args) {
        // WITHOUT a method: copy, paste, and hope you changed every copy correctly
        System.out.println("+----------+");
        System.out.println("| Results  |");
        System.out.println("+----------+");
        System.out.println("+----------+");
        System.out.println("| Report   |");
        System.out.println("+----------+");

        // WITH a method: write it once, give it a name, use it as often as you like
        printBanner("Results");
        printBanner("Report");
        printBanner("Summary");
    }

    static void printBanner(String title) {
        System.out.println("+" + "-".repeat(title.length() + 4) + "+");
        System.out.println("|  " + title + "  |");
        System.out.println("+" + "-".repeat(title.length() + 4) + "+");
    }
}
