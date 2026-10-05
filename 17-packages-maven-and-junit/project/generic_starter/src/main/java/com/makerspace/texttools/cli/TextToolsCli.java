package com.makerspace.texttools.cli;

import com.makerspace.texttools.TextTools;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Command-line front end. Analyses a text file:
 *     ./mvnw -q compile exec:java -Dexec.args="some-file.txt"
 * or, after ./mvnw package:
 *     java -jar target/texttools-1.0.jar some-file.txt
 */
public class TextToolsCli {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Usage: texttools <file.txt> [--json report.json]");
            return;
        }
        String text = Files.readString(Path.of(args[0]));
        System.out.println(TextTools.words(text).size() + " words");
        // TODO: a full report using every TextTools method; with --json, also save
        //       the report as JSON using Gson (see examples/02-maven-json)
    }
}
