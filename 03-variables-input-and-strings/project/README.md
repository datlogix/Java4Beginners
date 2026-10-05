# Module 3 Project: Mad Libs Story Generator

**Mad Libs** is a word game: one person asks for a list of words
("an adjective", "a place", "a number") *without saying what they're
for*, then reads out a story with the words dropped in. The results are
usually ridiculous, which is the point.

Your program asks the player for words, then prints a story built from
them.

```
========================================
          MAD LIBS: THE BIG DAY
========================================
Answer each question. Don't think too hard!

A name: Yaw
An adjective (a describing word, like 'smelly'): sparkly
A noun: goat
A place: Kejetia Market
...
----------------------------------------
Once upon a time, Yaw woke up feeling very sparkly.
Yaw grabbed a GOAT and ran all the way to Kejetia Market.
It took 45 minutes, which is 2700 seconds of pure panic.
...
----------------------------------------
THE END
```

## Requirements

Your program must:

1. Ask for **at least eight** words, including at least one name, one
   adjective, one noun, one verb, one place, and **two numbers**.
2. Read every answer with `nextLine()`, using `trim()` to remove stray
   spaces. Convert the numbers with `Integer.parseInt` or
   `Double.parseDouble`.
3. Use **every** word at least once in the story, and at least one word
   twice.
4. Do **at least one calculation** with the numbers that appears in the
   story (a total, a difference, minutes into seconds, a price with
   VAT…).
5. Use **at least two `printf` lines**, including one that formats a
   decimal number with `%.2f`.
6. Use **at least two String methods** on the player's words, such as
   `toUpperCase()` for something shouted, `length()`, or `charAt(0)`.
7. Store at least one fixed value (a price, a rate, a title) as a
   `final` constant.
8. Print a title at the start and `THE END` at the finish.

## Ideas if you're stuck

- **Write the story first**, on paper, with blanks: "Once upon a time,
  ______ (name) woke up feeling very ______ (adjective)." Then write
  the questions.
- Ask for the words in a *different* order from the order they appear
  in the story. It makes the result funnier.
- If the program skips a question, check you used `nextLine()` every
  time (the trap from Example 5).
- Getting `NumberFormatException`? The player typed something that isn't
  a number where you used `parseInt`. Run it again and type a number.
  (Module 13 shows how to handle this without crashing.)
- Finished early? Ask for the player's name at the start and use it in a
  personalised title, like `=== YAW'S BIG DAY ===`, centred with
  `printf` widths. Or tell the same story twice, the second time in
  capitals.

## Getting started

Copy [`MadLibs.java`](MadLibs.java) into your coursework folder and
follow the TODOs.

```bash
java MadLibs.java
```

## Done?

```bash
git add .
git commit -m "Complete Module 3 project: Mad Libs"
git push
```
