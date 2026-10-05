# Module 4 Project: Choose Your Own Adventure

Write an interactive story where the **reader makes the choices**. At
each turning point, your program asks what the player wants to do, and
the story branches. Different choices lead to different endings: some
good, some bad, some ridiculous.

```
=== THE LAST BUS TO TAMALE ===
What's your name, traveller? Abena

Abena, it's 9 pm at the bus station. The last bus to Tamale
leaves in ten minutes, and you've lost your ticket.
You have 20 cedis in your pocket.

Do you (a) search the waiting room, or (b) talk to the driver?
> b
The driver frowns. "A new ticket is 15 cedis."
Do you (a) pay, or (b) offer to sing for your fare?
> b
...
```

Use the starter's story, or write your own: a haunted lab, a robot
escape, a football final, a day as a doctor. Anything works, as long as
it branches.

## Requirements

Your story must:

1. Ask the player's name at the start and use it in the story at least
   **three** times.
2. Have **at least three** points where the player chooses, with at
   least **four different endings**.
3. Use `if` / `else if` / `else`, including at least one **nested**
   decision (a choice that only happens on one branch).
4. Use a `switch` (classic or arrow form) for at least one choice.
5. Use `&&` or `||` in at least one condition.
6. Track at least one **number** that changes during the story (coins,
   health, time, battery charge…), and have at least one ending that
   depends on it, e.g. `if (coins >= 15)`.
7. Handle answers in **any capitals** (`A`, `a`, ` a `) using
   `trim()` and `toLowerCase()` or `equalsIgnoreCase()`.
8. Handle **unexpected answers** gracefully: an `else` or `default` that
   does something sensible instead of crashing or going silent.
9. End **every** path with `THE END`.

## Plan on paper first

Draw your story as a **tree**: the opening at the top, a box for each
choice, an arrow for each answer, and the endings at the bottom. Number
the boxes. Check every arrow goes somewhere. This tree *is* your
program: each box becomes an `if`, and each arrow becomes a branch.

## Ideas if you're stuck

- Get **one complete path** working from start to finish before you
  add any other branches.
- Keep the code readable: indent each nested `if` one level further, and
  put a comment at the start of each branch saying which box of your
  tree it is (`// Box 3: the driver says no`).
- Test **every** path. With four endings, that means at least four
  runs. A branch you never tested probably has a bug.
- Don't compare strings with `==`. Use `.equals()`.
- Finished early? Add a **secret ending** that only happens if the
  player types a hidden answer (`dance`?) at one particular choice, or
  that needs two conditions at once (`coins > 10 && helpedTheOldMan`).

## Getting started

Copy [`Adventure.java`](Adventure.java) into your coursework folder and
follow the TODOs.

```bash
java Adventure.java
```

## Done?

```bash
git add .
git commit -m "Complete Module 4 project: choose your own adventure"
git push
```

Then swap stories with a classmate and try to find every ending in
theirs.
