// Example 9: IS-A versus HAS-A. Use inheritance only when the subclass
// truly IS a kind of the superclass. Otherwise, use composition: give the
// class a field holding the other object.
// Run it with:  java Ex09CompositionVsInheritance.java

import java.util.ArrayList;
import java.util.List;

public class Ex09CompositionVsInheritance {

    static class Engine {
        private final int horsepower;
        private boolean running;
        Engine(int horsepower) { this.horsepower = horsepower; }
        void start() { running = true; }
        boolean isRunning() { return running; }
        int getHorsepower() { return horsepower; }
    }

    // A Car HAS an Engine (composition). A Car is NOT an Engine, so
    // "class Car extends Engine" would be wrong, even though it would compile.
    static class Car {
        private final String model;
        private final Engine engine;
        private final List<String> passengers = new ArrayList<>();

        Car(String model, int horsepower) {
            this.model = model;
            this.engine = new Engine(horsepower);
        }

        void start() {
            engine.start();                        // delegate the job to the part that knows how
            System.out.println(model + " started (" + engine.getHorsepower() + " hp)");
        }

        void board(String person) { passengers.add(person); }
        boolean isRunning() { return engine.isRunning(); }
    }

    // final: nobody may extend this class.
    static final class Constants {
        static final double GRAVITY = 9.81;
    }
    // static class Planet extends Constants { }   // error: cannot inherit from final

    public static void main(String[] args) {
        Car car = new Car("Toyota Corolla", 132);
        car.start();
        car.board("Efua");
        System.out.println("Running? " + car.isRunning());
        System.out.println("g = " + Constants.GRAVITY);
    }
}
