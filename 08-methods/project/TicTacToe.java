// Module 8 Project: Tic-Tac-Toe, Restructured (now with a computer opponent)
// Author: YOUR NAME
//
// main() is already written: it reads like a table of contents. Your job is
// to fill in each method. Write them in the order they're listed, and use
// the checks in testMethods() (run with:  java TicTacToe.java test)
// to make sure each one works before you play a game.
//
// Run it with:  java TicTacToe.java

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static final Scanner IN = new Scanner(System.in);
    static final Random RANDOM = new Random();
    static final char EMPTY = ' ';

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("test")) {
            testMethods();
            return;
        }
        System.out.println("TIC-TAC-TOE: you are X, the computer is O.");
        boolean playAgain = true;
        while (playAgain) {
            playGame();
            playAgain = askYesNo("Play again? (y/n) ");
        }
        System.out.println("Thanks for playing!");
    }

    /** Plays one complete game, human (X) against computer (O). */
    static void playGame() {
        char[][] board = newBoard();
        char player = 'X';
        while (true) {
            printBoard(board);
            int square = (player == 'X') ? askHumanMove(board) : computerMove(board);
            placeMove(board, square, player);
            if (player == 'O') {
                System.out.println("The computer chooses square " + square + ".");
            }
            if (isWinner(board, player)) {
                printBoard(board);
                System.out.println(player == 'X' ? "You win!" : "The computer wins!");
                return;
            }
            if (isFull(board)) {
                printBoard(board);
                System.out.println("It's a draw.");
                return;
            }
            player = (player == 'X') ? 'O' : 'X';
        }
    }

    /** Returns a new 3x3 board with every square EMPTY. */
    static char[][] newBoard() {
        // TODO
        return new char[3][3];
    }

    /** Prints the board with | and ---+---+--- lines between the squares. */
    static void printBoard(char[][] board) {
        // TODO
    }

    /** Returns the row (0-2) of square 1-9. */
    static int rowOf(int square) {
        return 0;   // TODO
    }

    /** Returns the column (0-2) of square 1-9. */
    static int colOf(int square) {
        return 0;   // TODO
    }

    /** Returns true if square (1-9) is on the board and empty. */
    static boolean isFree(char[][] board, int square) {
        return false;   // TODO
    }

    /** Puts symbol on the board at square (1-9). */
    static void placeMove(char[][] board, int square, char symbol) {
        // TODO
    }

    /** Asks the human for a square until they choose a free one, and returns it. */
    static int askHumanMove(char[][] board) {
        // TODO
        return 1;
    }

    /** Returns true if symbol has three in a row, column or diagonal. */
    static boolean isWinner(char[][] board, char symbol) {
        return false;   // TODO
    }

    /** Returns true if no squares are empty. */
    static boolean isFull(char[][] board) {
        return false;   // TODO
    }

    /** Chooses the computer's move:
     *  1. a square that wins for O right now, if there is one;
     *  2. otherwise, a square that BLOCKS X from winning next turn;
     *  3. otherwise, the centre (5) if it's free;
     *  4. otherwise, a random free square.
     *  Hint for 1 and 2: try each free square, check isWinner, then undo it. */
    static int computerMove(char[][] board) {
        // TODO
        return 1;
    }

    /** Asks a yes/no question until the answer is y or n. */
    static boolean askYesNo(String question) {
        // TODO
        return false;
    }

    // ---------------- Checks: run with  java TicTacToe.java test ----------------

    static void check(String description, boolean ok) {
        System.out.println((ok ? "PASS  " : "FAIL  ") + description);
    }

    static void testMethods() {
        char[][] b = newBoard();
        check("newBoard() is all EMPTY", b[0][0] == EMPTY && b[1][1] == EMPTY && b[2][2] == EMPTY);
        check("square 1 is row 0, column 0", rowOf(1) == 0 && colOf(1) == 0);
        check("square 6 is row 1, column 2", rowOf(6) == 1 && colOf(6) == 2);
        check("square 7 is row 2, column 0", rowOf(7) == 2 && colOf(7) == 0);
        check("square 5 is free on a new board", isFree(b, 5));
        check("square 10 is not free (off the board)", !isFree(b, 10));
        placeMove(b, 5, 'X');
        check("placeMove puts X in the centre", b[1][1] == 'X');
        check("square 5 is no longer free", !isFree(b, 5));
        placeMove(b, 4, 'X');
        check("X doesn't win with only two in a row", !isWinner(b, 'X'));
        placeMove(b, 6, 'X');
        check("X wins with the middle row", isWinner(b, 'X'));
        check("O hasn't won", !isWinner(b, 'O'));
        char[][] d = newBoard();
        placeMove(d, 3, 'O');
        placeMove(d, 5, 'O');
        placeMove(d, 7, 'O');
        check("O wins with a diagonal (3, 5, 7)", isWinner(d, 'O'));
        check("a part-filled board isn't full", !isFull(d));
        char[][] w = newBoard();
        placeMove(w, 1, 'O');
        placeMove(w, 2, 'O');
        placeMove(w, 4, 'X');
        placeMove(w, 5, 'X');
        check("computer takes the winning square 3", computerMove(w) == 3);
        char[][] k = newBoard();
        placeMove(k, 1, 'X');
        placeMove(k, 2, 'X');
        placeMove(k, 5, 'O');
        check("computer blocks X at square 3", computerMove(k) == 3);
        check("computer takes the centre on an empty board", computerMove(newBoard()) == 5);
    }
}
