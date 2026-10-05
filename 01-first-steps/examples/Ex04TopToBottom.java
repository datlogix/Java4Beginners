// Example 4: Java runs the statements inside main in order, top to bottom.
// Run it, then swap the two "socks" and "shoes" lines and run it again.
// Java won't complain. It doesn't know what socks are.

public class Ex04TopToBottom {
    public static void main(String[] args) {
        System.out.println("Getting ready:");
        System.out.println("1. Put on socks");
        System.out.println("2. Put on shoes");
        System.out.println("3. Walk out of the door");
    }
}
