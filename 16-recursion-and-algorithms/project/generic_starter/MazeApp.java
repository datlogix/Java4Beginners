// Run it from the generic_starter folder with:
//     javac -d out *.java
//     java -cp out MazeApp

import java.io.IOException;
import java.nio.file.Path;

public class MazeApp {
    public static void main(String[] args) throws IOException {
        Maze maze = Maze.load(Path.of("data", "maze_small.txt"));
        System.out.println(maze);
        // TODO: solve it recursively and print the solved copy with its path length;
        //       compare with the BFS shortest path; do the same for maze_large.txt;
        //       then the timing experiment from the README.
    }
}
