// Example 8: checking what kind of object you have, with instanceof.
// Run it with:  java Ex08InstanceofAndCasting.java

import java.util.List;

public class Ex08InstanceofAndCasting {

    static class Device {
        final String id;
        Device(String id) { this.id = id; }
    }

    static class Phone extends Device {
        int battery = 64;
        Phone(String id) { super(id); }
        void call(String who) { System.out.println(id + " calling " + who + "..."); }
    }

    static class Printer extends Device {
        int paper = 120;
        Printer(String id) { super(id); }
    }

    public static void main(String[] args) {
        List<Device> devices = List.of(new Phone("PH-1"), new Printer("PR-7"), new Phone("PH-2"));

        for (Device d : devices) {
            // instanceof asks: "is this object a Phone (or a subclass of Phone)?"
            if (d instanceof Phone phone) {        // if so, ALSO give me it as a Phone called phone
                System.out.println(d.id + " is a phone at " + phone.battery + "% battery");
                phone.call("Ama");
            } else if (d instanceof Printer printer) {
                System.out.println(d.id + " is a printer with " + printer.paper + " sheets");
            }
        }

        // The old way, still common in older code: check, then cast.
        Device first = devices.get(0);
        if (first instanceof Phone) {
            Phone p = (Phone) first;
            System.out.println("Cast worked: " + p.battery);
        }
        // Casting to the wrong type compiles, but crashes when it runs:
        Printer wrong = (Printer) first;           // ClassCastException
    }
}
