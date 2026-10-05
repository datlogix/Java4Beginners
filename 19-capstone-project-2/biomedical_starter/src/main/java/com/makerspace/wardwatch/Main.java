package com.makerspace.wardwatch;

import com.makerspace.wardwatch.ui.ConsoleApp;

/** Starts WardWatch.  ./mvnw -q compile exec:java   (or java -jar target/wardwatch-1.0.jar) */
public class Main {
    public static void main(String[] args) {
        new ConsoleApp().run();
    }
}
