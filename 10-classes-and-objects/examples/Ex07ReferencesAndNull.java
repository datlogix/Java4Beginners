// Example 7: object variables hold REFERENCES, just like arrays.
// Run it with:  java Ex07ReferencesAndNull.java

public class Ex07ReferencesAndNull {

    static class Lamp {
        boolean on;
        String room;

        Lamp(String room) {
            this.room = room;
        }
    }

    static void switchOn(Lamp lamp) {
        lamp.on = true;                   // changes the caller's object
    }

    public static void main(String[] args) {
        Lamp kitchen = new Lamp("kitchen");
        Lamp sameLamp = kitchen;          // NOT a copy: two names, one object
        sameLamp.on = true;
        System.out.println("kitchen.on = " + kitchen.on);      // true

        Lamp hall = new Lamp("hall");
        switchOn(hall);                   // methods get a copy of the reference
        System.out.println("hall.on = " + hall.on);            // true

        Lamp otherKitchen = new Lamp("kitchen");
        System.out.println(kitchen == sameLamp);       // true:  same object
        System.out.println(kitchen == otherKitchen);   // false: different objects, same data

        // null means "no object". Using it crashes:
        Lamp missing = null;
        System.out.println("missing is " + missing);
        System.out.println(missing.room);              // NullPointerException
    }
}
