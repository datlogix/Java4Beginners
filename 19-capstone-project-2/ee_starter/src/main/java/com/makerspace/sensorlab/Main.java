package com.makerspace.sensorlab;

import com.makerspace.sensorlab.ui.ConsoleApp;

/** Starts SensorLab.  ./mvnw -q compile exec:java   (or java -jar target/sensorlab-1.0.jar) */
public class Main {
    public static void main(String[] args) {
        new ConsoleApp().run();
    }
}
