// Example 3: instance methods. They're NOT static: each one works on the
// fields of the particular object it's called on.
// Run it with:  java Ex03Methods.java

public class Ex03Methods {

    static class Counter {
        String label;
        int count;

        Counter(String label) {
            this.label = label;
        }

        void click() {
            count++;                    // THIS counter's count
        }

        void clickMany(int times) {
            for (int i = 0; i < times; i++) {
                click();                // a method can call another method on the same object
            }
        }

        void reset() {
            count = 0;
        }

        boolean isOver(int limit) {     // methods can return values, as in Module 8
            return count > limit;
        }

        String describe() {
            return label + ": " + count;
        }
    }

    public static void main(String[] args) {
        Counter visitors = new Counter("Visitors");
        Counter cars = new Counter("Cars");

        visitors.click();
        visitors.click();
        cars.clickMany(5);
        System.out.println(visitors.describe());    // Visitors: 2
        System.out.println(cars.describe());        // Cars: 5
        System.out.println("Too many cars? " + cars.isOver(4));

        cars.reset();
        System.out.println(cars.describe());        // Cars: 0
        System.out.println(visitors.describe());    // still 2
    }
}
