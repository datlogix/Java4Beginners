# Module 5 Project: The Games Arcade

Build a small **arcade**: a menu that lets the player choose between
three games, play as many rounds as they like, and quit when they're
done.

```
*** WELCOME TO THE JAVA ARCADE ***

1) Guess the Number
2) Dice Duel
3) Times Table Challenge
4) Quit
Choose a game: 1
I'm thinking of a number from 1 to 100.
Your guess: 50
Too high!
Your guess: 25
Too low!
...
You got it in 6 guesses.
```

## Requirements

**The menu**

1. Uses a `do`-`while` loop, so it keeps coming back after each game
   until the player chooses **Quit**.
2. Handles an invalid choice with a friendly message, then shows the
   menu again.
3. Counts the games played, and prints the total when the player quits.

**Game 1: Guess the Number**

4. The computer picks a random number from 1 to 100.
5. The player guesses until they get it; after each wrong guess, print
   `Too high!` or `Too low!`.
6. When they get it, print how many guesses it took, and a comment that
   depends on the number (e.g. 7 or fewer: "Excellent!").

**Game 2: Dice Duel**

7. The player and the computer take turns: the player presses Enter to
   roll (`in.nextLine()` waits for Enter), then the computer rolls.
8. Show each roll and both running totals. The first to reach **20 or
   more** wins. (Decide what happens if both pass 20 in the same round,
   and explain your decision in a comment.)

**Game 3: Times Table Challenge**

9. Ask **5** questions like `7 x 8 = ?`, with both numbers random from
   2 to 12.
10. Say whether each answer was right (and give the correct answer if
    not). Print the score at the end, e.g. `4/5`.

**Everywhere**

11. Use **at least one** of each: `while`, `do`-`while`, and `for`.
12. Use `break` or `continue` at least once, somewhere it makes the code
    simpler.

## Ideas if you're stuck

- **One game at a time.** Get Guess the Number working perfectly before
  you write a single line of Dice Duel.
- Test with the secret number printed (`System.out.println("[secret: "
  + secret + "]");`), so you can check the "too high/too low" logic.
  Delete the line when it works.
- For the dice duel, write down the steps of *one round* in plain
  English first, then put a loop around them.
- Stuck in an endless loop? Press **Ctrl+C** in the terminal to stop the
  program. Then check that something in the loop's condition changes
  every time round.
- Finished early? Add a **fourth game** (rock-paper-scissors, a coin-toss
  streak, a hangman with one word), or let the player choose the
  difficulty of Guess the Number (1–10, 1–100 or 1–1000), and keep a best
  score for each.

## Getting started

Copy [`Arcade.java`](Arcade.java) into your coursework folder and follow
the TODOs.

```bash
java Arcade.java
```

## Done?

```bash
git add .
git commit -m "Complete Module 5 project: games arcade"
git push
```
