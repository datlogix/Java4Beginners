// Example 11: String[] args is an array too! It holds the words typed
// after the program's name on the command line.
// Run it with:  java Ex11CommandLineArgs.java Ama 3

public class Ex11CommandLineArgs {
    public static void main(String[] args) {
        System.out.println("You gave " + args.length + " argument(s).");
        for (int i = 0; i < args.length; i++) {
            System.out.println("args[" + i + "] = " + args[i]);
        }

        if (args.length >= 2) {
            String name = args[0];
            int times = Integer.parseInt(args[1]);
            for (int i = 0; i < times; i++) {
                System.out.println("Hello, " + name + "!");
            }
        } else {
            System.out.println("Try: java Ex11CommandLineArgs.java YourName 3");
        }
    }
}
