// Module 16 Project, Track A: Maze Solver
// Author: YOUR NAME

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/** A maze: '#' is a wall, ' ' is open floor, 'S' is the start and 'E' the exit. */
public class Maze {
    private final char[][] grid;

    public Maze(char[][] grid) {
        this.grid = grid;
    }

    public static Maze load(Path file) throws IOException {
        List<String> lines = Files.readAllLines(file);
        char[][] grid = new char[lines.size()][];
        for (int r = 0; r < lines.size(); r++) {
            grid[r] = lines.get(r).toCharArray();
        }
        return new Maze(grid);
    }

    public int rows() { return grid.length; }
    public int cols() { return grid[0].length; }
    public char get(int r, int c) { return grid[r][c]; }
    public void set(int r, int c, char ch) { grid[r][c] = ch; }

    public boolean isOpen(int r, int c) {
        return r >= 0 && r < rows() && c >= 0 && c < cols() && grid[r][c] != '#';
    }

    /** Returns {row, col} of the first cell holding ch. */
    public int[] find(char ch) {
        // TODO
        return new int[]{1, 1};
    }

    /** A copy, so a solver can mark the path without changing the original. */
    public Maze copy() {
        char[][] g = new char[rows()][];
        for (int r = 0; r < rows(); r++) {
            g[r] = grid[r].clone();
        }
        return new Maze(g);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char[] row : grid) {
            sb.append(row).append('\n');
        }
        return sb.toString();
    }
}
