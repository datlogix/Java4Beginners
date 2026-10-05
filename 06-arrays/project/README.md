# Module 6 Project: Tic-Tac-Toe

Build a **two-player Tic-Tac-Toe** (noughts and crosses) game for the
terminal. Two people share the keyboard, taking turns to place `X` and
`O` on a 3 × 3 board, until someone gets three in a row or the board
fills up.

```
 X | O |   
---+---+---
   | X |   
---+---+---
 O |   |   
Player X, choose a square (1-9): 9

 X | O |   
---+---+---
   | X |   
---+---+---
 O |   | X 
Player X wins!
```

## Requirements

Your game must:

1. Store the board in a **2D array**, `char[][] board = new char[3][3]`
   (or the starter's version), with `' '` for an empty square.
2. Show the board before every move, with `|` and `---+---+---` lines
   between the squares.
3. Let players choose a square by number, 1 to 9 (1 is top left, 9 is
   bottom right), and convert the number to a row and column with `/`
   and `%`.
4. **Reject** numbers outside 1–9 and squares that are already taken,
   and let the same player try again, without crashing.
5. Alternate between `X` and `O`.
6. After every move, check **all eight** ways to win: three rows, three
   columns and two diagonals. Use **loops** for the rows and columns,
   not eight separate `if` statements.
7. Announce the winner, or a draw when all nine squares are full with
   no winner.
8. At the end, ask `Play again? (y/n)` and start a fresh game if the
   answer is `y`. Keep a running score, e.g. `X: 2  O: 1  Draws: 1`.

## Ideas if you're stuck

- Get the board **printing** first, with a few squares filled in by hand
  in the array, before you write any of the game.
- Work out the row and column for every square on paper: square 5 is
  `(5 - 1) / 3 = 1` and `(5 - 1) % 3 = 1`, the centre. Check a couple
  of others.
- Checking a row `r` for a win: `board[r][0] == player && board[r][1]
  == player && board[r][2] == player`. A column is the same with the
  indexes the other way round. Put it in a `for` loop over `r` from 0 to
  2.
- For "play again", wrap the whole game in another loop, and reset the
  board and the move count at the start of each game.
- Your `main` is getting long. That's expected, and slightly painful.
  In Module 8 you'll restructure this exact game into short, named
  methods, and add a computer opponent.
- Finished early? Let players type `undo` to take back the last move,
  or print a message showing which line won (`Row 2`, `Diagonal`).

## Getting started

Copy [`TicTacToe.java`](TicTacToe.java) into your coursework folder and
follow the TODOs.

```bash
java TicTacToe.java
```

## Done?

```bash
git add .
git commit -m "Complete Module 6 project: tic-tac-toe"
git push
```
