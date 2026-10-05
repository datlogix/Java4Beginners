package com.makerspace.app;

import com.makerspace.util.TextTools;    // a class from ANOTHER package must be imported

/**
 * Compile and run from the 01-packages folder:
 *
 *   javac -d out src/com/makerspace/util/TextTools.java src/com/makerspace/app/Main.java
 *   java -cp out com.makerspace.app.Main
 *
 * Package it as a JAR file (one file you can hand to anyone with Java), and run that:
 *
 *   jar --create --file greeter.jar --main-class com.makerspace.app.Main -C out .
 *   java -jar greeter.jar
 */
public class Main {
    public static void main(String[] args) {
        String name = args.length > 0 ? String.join(" ", args) : "ama serwaa owusu";
        System.out.println(TextTools.boxed("Akwaaba, " + TextTools.titleCase(name) + "!", 40));
        // TextTools.isBlank(name);    // error: isBlank is not public in TextTools
    }
}
