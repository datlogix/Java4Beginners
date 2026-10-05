# Module 8 Project: Tic-Tac-Toe, Restructured (Now with a Computer Opponent)

In Module 6 you built Tic-Tac-Toe as one long block of code in `main`.
It worked, but it was hard to read, hard to change, and hard to test.
Now you'll rebuild it the way professional programmers would: as a set
of **short, named methods**, each doing one job, and add a **computer
opponent** that's surprisingly hard to beat.

```
TIC-TAC-TOE: you are X, the computer is O.
   |   |   
---+---+---
   | X |   
---+---+---
   |   |   
The computer chooses square 1.
 O |   |   
---+---+---
   | X |   
---+---+---
   |   |   
Your move (1-9): 9
...
```

## Requirements

The starter's `main` and `playGame` methods are already written. Read
them first: they show exactly how your methods will be used. Then:

1. Write the body of **every** method in the starter: `newBoard`,
   `printBoard`, `rowOf`, `colOf`, `isFree`, `placeMove`,
   `askHumanMove`, `isWinner`, `isFull`, `computerMove` and `askYesNo`.
2. Each method does **only** what its Javadoc comment says. For example,
   `isWinner` returns a boolean and never prints anything; `printBoard`
   prints and never changes the board.
3. `askHumanMove` must never crash and never accept a taken square or a
   number outside 1–9.
4. `computerMove` follows the four-step strategy in its comment: win if
   it can, block if it must, take the centre, otherwise choose randomly.
5. All the checks pass: run `java TicTacToe.java test` and get 16 PASS
   lines.
6. No method is longer than about 25 lines. If one is, split it into
   smaller methods.

**Stretch goals** (pick any):

- Keep a score across games (`You 2  Computer 3  Draws 4`).
- Let the human choose whether to go first.
- Add difficulty levels: *easy* (always random), *medium* (the strategy
  above), and *hard*, which also prefers corners over edges when nothing
  better is available. (Is the hard computer unbeatable? Try!)

## Ideas if you're stuck

- Do the methods **in order**, and run the checks after each one. The
  checks for `rowOf` and `colOf` will pass long before `computerMove`
  does, and that's fine.
- `isWinner` gets simpler if you write a helper first:
  `static boolean isLine(char a, char b, char c, char symbol)`, which
  returns true when all three equal `symbol`.
- For `computerMove`'s "win" step, loop over squares 1 to 9. For each
  free square, place an `'O'`, check `isWinner(board, 'O')`, then put
  `EMPTY` back. The "block" step is the same with `'X'`. That's the
  same loop twice, so make it a method: `findWinningSquare(board,
  symbol)`, returning the square, or `-1` if there isn't one.
- `askYesNo` and `askHumanMove` both repeat "ask until the answer is
  valid", the pattern from Module 5.

## Getting started

Copy [`TicTacToe.java`](TicTacToe.java) into your coursework folder.

```bash
java TicTacToe.java test     # run the checks
java TicTacToe.java          # play
```

## Done?

```bash
git add .
git commit -m "Complete Module 8 project: tic-tac-toe with methods and AI"
git push
```
