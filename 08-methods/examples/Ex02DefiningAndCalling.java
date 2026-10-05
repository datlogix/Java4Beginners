// Example 2: defining a method, and the order things happen in when you call it.
// Run it with:  java Ex02DefiningAndCalling.java

public class Ex02DefiningAndCalling {

    // A method definition: it does NOTHING until it's called.
    static void sayHello() {
        System.out.println("  (inside sayHello) Hello!");
    }

    static void countToThree() {
        System.out.println("  (inside countToThree) starting");
        for (int i = 1; i <= 3; i++) {
            System.out.println("  " + i);
        }
        sayHello();               // a method can call another method
        System.out.println("  (inside countToThree) finished");
    }

    public static void main(String[] args) {
        System.out.println("main: start");
        sayHello();               // jump into sayHello, run it, come back here
        System.out.println("main: back from sayHello");
        countToThree();
        System.out.println("main: back from countToThree");
        sayHello();
        System.out.println("main: end");
    }
}
