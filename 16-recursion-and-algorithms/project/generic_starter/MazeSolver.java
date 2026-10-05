import java.util.ArrayDeque;
import java.util.Deque;

/** Two ways to get out of a maze. */
public class MazeSolver {

    /**
     * Recursive depth-first search with backtracking.
     * Base cases: (r, c) is a wall, off the grid, or already visited -> false;
     *             (r, c) is the exit -> true.
     * Recursive case: mark (r, c) as visited, then try each neighbour (up, down,
     * left, right). If any of them reaches the exit, mark (r, c) with '.' as part
     * of the path and return true. If none do, return false (backtrack).
     */
    public static boolean solve(Maze maze, int r, int c, boolean[][] visited) {
        // TODO
        return false;
    }

    /**
     * Breadth-first search: explore squares in order of their distance from S,
     * using a queue (an ArrayDeque). Returns the length of the SHORTEST path from
     * S to E (the number of moves), or -1 if there isn't one.
     * Hint: keep a distance[][] array, filled with -1 for "not reached yet".
     */
    public static int shortestPath(Maze maze) {
        Deque<int[]> queue = new ArrayDeque<>();
        // TODO
        return -1;
    }
}
