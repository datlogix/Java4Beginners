// Example 1: a while loop repeats its block for as long as its condition is true.
// Run it with:  java Ex01WhileCountdown.java

public class Ex01WhileCountdown {
    public static void main(String[] args) throws InterruptedException {
        int count = 10;
        while (count > 0) {
            System.out.println(count + "...");
            Thread.sleep(300);          // pause for 300 milliseconds, for drama
            count--;                    // without this line, the loop never ends!
        }
        System.out.println("Liftoff!");
        System.out.println("After the loop, count is " + count);
    }
}
