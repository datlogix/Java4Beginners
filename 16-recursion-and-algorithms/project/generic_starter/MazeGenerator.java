import java.util.Random;

/** Makes random mazes with the "recursive backtracker". */
public class MazeGenerator {

    /**
     * Returns a new rows x cols maze (both must be odd numbers, at least 5).
     * Start with every cell a wall. Then carve(1, 1):
     *   carve(r, c): make (r, c) open; for each direction, in a RANDOM order, look
     *   at the cell TWO squares away. If it's inside the grid and still a wall,
     *   open the wall between, and carve from there (recursion!).
     * Finally put 'S' at (1, 1) and 'E' at (rows - 2, cols - 2).
     */
    public static Maze generate(int rows, int cols, Random random) {
        // TODO
        return new Maze(new char[][]{"#####".toCharArray(), "#S E#".toCharArray(), "#####".toCharArray()});
    }
}
