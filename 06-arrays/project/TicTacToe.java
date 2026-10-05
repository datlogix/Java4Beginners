// Module 6 Project: Tic-Tac-Toe
// Author: YOUR NAME
//
// Follow the TODOs in order. Run the program after each one.
// Run it with:  java TicTacToe.java

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // The board: 3 rows of 3 cells. ' ' means empty.
        char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '},
        };
        char player = 'X';
        int moves = 0;
        boolean gameOver = false;

        System.out.println("TIC-TAC-TOE: choose a square from 1 to 9");
        System.out.println(" 1 | 2 | 3 ");
        System.out.println("---+---+---");
        System.out.println(" 4 | 5 | 6 ");
        System.out.println("---+---+---");
        System.out.println(" 7 | 8 | 9 ");

        while (!gameOver) {
            // --- Show the board ---
            // TODO: print the board in the same style as the numbered one above

            // --- Ask for a move ---
            System.out.print("Player " + player + ", choose a square (1-9): ");
            int square = Integer.parseInt(in.nextLine().trim());
            // TODO: turn the square number (1-9) into a row and column (0-2).
            //       Hint: row = (square - 1) / 3, and the column uses %.
            // TODO: reject a number outside 1-9, or a square that's already taken,
            //       and ask again (use continue to skip the rest of this turn)

            // TODO: put the player's symbol on the board, and count the move

            // --- Check for a winner ---
            // TODO: check all 3 rows, all 3 columns and both diagonals.
            //       If the player has three in a line, show the board,
            //       announce the winner and set gameOver = true.
            // TODO: if nobody has won after 9 moves, it's a draw

            // --- Next player's turn ---
            player = (player == 'X') ? 'O' : 'X';
            gameOver = true;    // TODO: delete this line once the game can end properly!
        }
    }
}
