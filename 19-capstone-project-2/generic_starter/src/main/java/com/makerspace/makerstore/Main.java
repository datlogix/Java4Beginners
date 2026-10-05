package com.makerspace.makerstore;

import com.makerspace.makerstore.ui.ConsoleApp;

/** Starts MakerStore.  ./mvnw -q compile exec:java   (or java -jar target/makerstore-1.0.jar) */
public class Main {
    public static void main(String[] args) {
        new ConsoleApp().run();
    }
}
