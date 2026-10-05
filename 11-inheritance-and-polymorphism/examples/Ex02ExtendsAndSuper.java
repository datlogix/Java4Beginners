// Example 2: constructors and super(...). A subclass object is built in
// layers: the superclass part first, then the subclass part.
// Run it with:  java Ex02ExtendsAndSuper.java

public class Ex02ExtendsAndSuper {

    static class Vehicle {
        protected final String registration;
        protected final int wheels;

        Vehicle(String registration, int wheels) {
            System.out.println("  Vehicle constructor runs first");
            this.registration = registration;
            this.wheels = wheels;
        }

        String describe() {
            return registration + " (" + wheels + " wheels)";
        }
    }

    static class Car extends Vehicle {
        private final int seats;

        Car(String registration, int seats) {
            super(registration, 4);        // MUST be the first line: build the Vehicle part
            System.out.println("  then the Car constructor");
            this.seats = seats;
        }

        int getSeats() { return seats; }
    }

    static class Trotro extends Car {      // a Trotro IS a Car, which IS a Vehicle
        private final String route;

        Trotro(String registration, String route) {
            super(registration, 15);
            System.out.println("  and finally the Trotro constructor");
            this.route = route;
        }

        String getRoute() { return route; }
    }

    public static void main(String[] args) {
        System.out.println("Building a Trotro:");
        Trotro t = new Trotro("GR 1234-24", "Circle - Madina");
        System.out.println(t.describe());                  // from Vehicle
        System.out.println(t.getSeats() + " seats");       // from Car
        System.out.println("Route: " + t.getRoute());      // its own
    }
}
