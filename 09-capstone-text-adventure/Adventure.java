// Capstone 1: Text Adventure, starter code
// Team: YOUR TEAM NAME
// Members: NAMES HERE
//
// This starter has two rooms and working go / look / help / quit commands.
// Replace the world with your own, then add the rest of the features from
// the capstone README, one method at a time.
//
// Run it with:  java Adventure.java

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Adventure {

    static final String TITLE = "THE LOST ROBOT OF MAKERSPACE";

    // The world is DATA. Each room has a key (like "workshop"), and four maps
    // look up everything about a room by its key:
    static final Map<String, String> ROOM_NAMES = new HashMap<>();
    static final Map<String, String> DESCRIPTIONS = new HashMap<>();
    static final Map<String, Map<String, String>> EXITS = new HashMap<>();   // room -> (direction -> room)
    static final Map<String, List<String>> ITEMS = new HashMap<>();          // room -> items lying there

    // Short forms the player can type instead of full directions
    static final Map<String, String> DIRECTION_SHORTCUTS =
            Map.of("n", "north", "s", "south", "e", "east", "w", "west");
    static final List<String> DIRECTIONS = List.of("north", "south", "east", "west");

    static final Scanner IN = new Scanner(System.in);

    /** Fills the four world maps with every room, exit and item. */
    static void buildWorld() {
        addRoom("workshop", "Workshop",
                "Soldering irons cool on the benches. A half-built robot slumps in\n"
                + "the corner, missing its battery. A door leads north.");
        addRoom("store room", "Store Room",
                "Shelves of components stretch into the dark. The door to the south\n"
                + "is the way back.");

        addExit("workshop", "north", "store room");
        addExit("store room", "south", "workshop");

        addItem("workshop", "screwdriver");
        addItem("store room", "battery");
        addItem("store room", "torch");
        // TODO: build YOUR world: at least 6 rooms, 5 items, and a locked route
    }

    /** Adds a room with no exits and no items (yet). */
    static void addRoom(String key, String name, String description) {
        ROOM_NAMES.put(key, name);
        DESCRIPTIONS.put(key, description);
        EXITS.put(key, new HashMap<>());
        ITEMS.put(key, new ArrayList<>());
    }

    /** Adds a ONE-WAY exit. To go both ways, add an exit in each direction. */
    static void addExit(String from, String direction, String to) {
        EXITS.get(from).put(direction, to);
    }

    /** Puts an item in a room. */
    static void addItem(String room, String item) {
        ITEMS.get(room).add(item);
    }

    /** Prints the name, description and items of a room. */
    static void describeRoom(String room) {
        System.out.println();
        System.out.println(ROOM_NAMES.get(room).toUpperCase());
        System.out.println(DESCRIPTIONS.get(room));
        if (!ITEMS.get(room).isEmpty()) {
            System.out.println("You see: " + String.join(", ", ITEMS.get(room)));
        }
    }

    /**
     * Splits the player's input into a verb and a noun, cleaned and lower case.
     *   "  Take Battery " -> {"take", "battery"}
     *   "go store room"   -> {"go", "store room"}
     *   "look"            -> {"look", ""}
     *   ""                -> {"", ""}
     */
    static String[] parseCommand(String text) {
        String cleaned = text.trim().toLowerCase().replaceAll(" +", " ");
        int space = cleaned.indexOf(" ");
        if (space == -1) {
            return new String[]{cleaned, ""};
        }
        return new String[]{cleaned.substring(0, space), cleaned.substring(space + 1)};
    }

    /**
     * Returns the room reached by going in a direction from room.
     * If there's no exit that way, prints a message and returns room unchanged.
     */
    static String move(String room, String direction) {
        direction = DIRECTION_SHORTCUTS.getOrDefault(direction, direction);
        Map<String, String> exits = EXITS.get(room);
        if (exits.containsKey(direction)) {
            return exits.get(direction);
        }
        if (direction.isEmpty()) {
            System.out.println("Go where?");
        } else {
            System.out.println("You can't go " + direction + " from here.");
        }
        return room;
    }

    /** Prints the list of commands. */
    static void showHelp() {
        System.out.println("Commands: go <direction> (or n/s/e/w), look, help, quit");
        // TODO: add your new commands here as you write them
    }

    // TODO: static void takeItem(String room, List<String> inventory, String item)
    // TODO: static void dropItem(String room, List<String> inventory, String item)
    // TODO: static void showInventory(List<String> inventory)
    // TODO: static boolean hasWon(String room, List<String> inventory)
    // TODO: your own command(s)

    public static void main(String[] args) {
        buildWorld();
        String currentRoom = "workshop";
        List<String> inventory = new ArrayList<>();

        System.out.println("=== " + TITLE + " ===");
        System.out.println("Type 'help' for a list of commands.");
        describeRoom(currentRoom);

        while (true) {
            System.out.print("\n> ");
            String[] command = parseCommand(IN.nextLine());
            String verb = command[0];
            String noun = command[1];

            // Let a direction on its own ("n", "north") mean "go north"
            if (DIRECTION_SHORTCUTS.containsKey(verb) || DIRECTIONS.contains(verb)) {
                noun = verb;
                verb = "go";
            }

            switch (verb) {
                case "" -> { }      // empty input: do nothing, ask again
                case "go" -> {
                    String newRoom = move(currentRoom, noun);
                    if (!newRoom.equals(currentRoom)) {
                        currentRoom = newRoom;
                        describeRoom(currentRoom);
                    }
                }
                case "look" -> describeRoom(currentRoom);
                case "help" -> showHelp();
                case "quit" -> {
                    System.out.println("Thanks for playing!");
                    return;
                }
                // TODO: take, drop, inventory, your own commands
                default -> System.out.println("I don't understand that. Type 'help' for a list of commands.");
            }

            // TODO: check whether the player has won (or lost) and end the game
        }
    }
}
