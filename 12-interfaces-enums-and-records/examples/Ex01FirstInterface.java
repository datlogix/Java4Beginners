// Example 1: an interface is a CONTRACT. It lists methods a class promises
// to provide, without saying how.
// Run it with:  java Ex01FirstInterface.java

import java.util.List;

public class Ex01FirstInterface {

    interface Chargeable {
        int batteryPercent();          // interface methods are automatically public and abstract
        void charge(int minutes);
    }

    // Completely unrelated classes can sign the same contract with "implements".
    static class Phone implements Chargeable {
        private int battery = 20;
        public int batteryPercent() { return battery; }
        public void charge(int minutes) { battery = Math.min(100, battery + minutes); }
    }

    static class ElectricBike implements Chargeable {
        private double kwh = 0.2, capacity = 0.5;
        public int batteryPercent() { return (int) (kwh / capacity * 100); }
        public void charge(int minutes) { kwh = Math.min(capacity, kwh + minutes * 0.002); }
    }

    static class Robot implements Chargeable {
        private int battery = 5;
        public int batteryPercent() { return battery; }
        public void charge(int minutes) { battery = Math.min(100, battery + minutes / 2); }
        void dance() { System.out.println("The robot dances!"); }
    }

    // This works with ANYTHING chargeable: it doesn't care what the thing is.
    static void chargeAll(List<Chargeable> things, int minutes) {
        for (Chargeable c : things) {
            c.charge(minutes);
            System.out.println(c.getClass().getSimpleName() + " is now at " + c.batteryPercent() + "%");
        }
    }

    public static void main(String[] args) {
        chargeAll(List.of(new Phone(), new ElectricBike(), new Robot()), 60);
        // Chargeable c = new Chargeable();   // error: an interface can't be instantiated
    }
}
