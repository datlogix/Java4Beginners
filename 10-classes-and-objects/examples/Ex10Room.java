// Example 10: the Module 9 text adventure, with a Room class.
// Each Room keeps its own name, description, exits and items together,
// and exits lead straight to other Room OBJECTS.
// Run it with:  java Ex10Room.java

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Ex10Room {

    static class Room {
        private final String name;
        private final String description;
        private final Map<String, Room> exits = new HashMap<>();
        private final List<String> items = new ArrayList<>();

        Room(String name, String description) {
            this.name = name;
            this.description = description;
        }

        void connect(String direction, Room other, String back) {
            exits.put(direction, other);
            other.exits.put(back, this);     // a two-way door in one call
        }

        Room exit(String direction) {
            return exits.get(direction);     // null if there's no exit that way
        }

        void addItem(String item) { items.add(item); }

        void describe() {
            System.out.println();
            System.out.println(name.toUpperCase());
            System.out.println(description);
            if (!items.isEmpty()) {
                System.out.println("You see: " + String.join(", ", items));
            }
            System.out.println("Exits: " + String.join(", ", exits.keySet()));
        }
    }

    public static void main(String[] args) {
        Room workshop = new Room("Workshop", "Soldering irons cool on the benches.");
        Room store = new Room("Store Room", "Shelves of components stretch into the dark.");
        Room lab = new Room("Robotics Lab", "A robot arm waves slowly at you.");
        workshop.connect("north", store, "south");
        workshop.connect("east", lab, "west");
        store.addItem("battery");

        Scanner in = new Scanner(System.in);
        Room here = workshop;
        here.describe();
        while (true) {
            System.out.print("\nDirection (or quit): ");
            String direction = in.nextLine().trim().toLowerCase();
            if (direction.equals("quit")) {
                break;
            }
            Room next = here.exit(direction);
            if (next == null) {
                System.out.println("You can't go that way.");
            } else {
                here = next;
                here.describe();
            }
        }
    }
}
