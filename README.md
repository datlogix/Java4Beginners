# Introduction to Java Programming

Welcome! This course takes you from **never having written a line of
code** to **designing, testing, and building your own multi-file,
object-oriented Java applications**, tracked in Git and pushed to
GitHub. No prior programming experience is assumed.

The course comes in two parts:

- **Part 1: Modules 0–9.** From zero to a real program: how Java code
  is compiled and run, values and types, variables, input, decisions,
  loops, arrays, lists and maps, methods, and a team capstone (a text
  adventure game).
- **Part 2: Modules 10–19.** Moving from "I can write Java" to "I can
  build things in Java": classes and objects, inheritance and
  polymorphism, interfaces, enums and records, exceptions, files,
  generics and lambdas, recursion and algorithms, packages, Maven and
  automated testing with JUnit, graphical user interfaces with Swing,
  and a second, harder capstone.

Part 2 assumes Part 1 is finished. Module 10 doesn't re-explain
anything from Modules 0–9.

## Built to work with or without a lecturer in the room

Every lesson is written so you can teach yourself from it directly. The
*why* always comes before the *how*, every claim is backed by code you
actually run, and nothing assumes a lecturer will fill in a gap out loud.
If you have an instructor, they'll add depth, stories, and live
debugging. If you don't, you can still get every concept from the page.

## Every module starts with a hook

Before any theory, every module opens with a **Hook**: a short, genuinely
fun demo you run *first*, before you understand how it works. Curiosity
("wait, how did it do that?") is the whole teaching strategy. It's a far
stronger reason to keep going than "you'll need this later." By the end
of each module you'll understand exactly how its hook worked.

## How this course is built

Every module (after Setup) follows the same shape:

1. **`README.md`**: the lesson. A hook first, then concepts explained in
   plain language, with common beginner mistakes called out explicitly.
2. **`examples/`**: small, runnable `.java` files, each showing one idea.
   Run every single one yourself, then change something and run it
   again. Reading code you didn't run teaches you far less than watching
   your own changes compile (or refuse to).
3. **`exercises/`**: practice problems with starter files containing
   `TODO` markers. Reference solutions aren't included in this repo.
   Your instructor shares them after the deadline, so give each exercise
   a real attempt (at least 15 minutes) and check your own work against
   the sample runs in each file.
4. **`project/`**: a small, fun program that only uses what you've
   learned *so far*. This is the payoff for the module: something you'd
   actually want to show a friend.

In Part 2, every module project comes in three **tracks**: **Generic**,
**Electrical/Electronic Engineering (EE)**, and **Biomedical
Engineering**. They teach the same concept applied to different worlds.
Pick the one that fits you, or try more than one.

Two more folders sit alongside the modules:

- **`Student Manual/`**: every lesson in one printable document (Word
  and PDF), for reading offline.
- **`Slides/`**: the slide deck for each module (PowerPoint).

## Roadmap

### Part 1: Java Foundations

| # | Module | You will be able to... |
|---|--------|------------------------|
| 0 | [Setup: the JDK, VS Code, Git & GitHub](00-setup/README.md) | Install your tools, run Java, and push your work to GitHub |
| 1 | [First Steps](01-first-steps/README.md) | Compile and run a Java program, print output, draw in a window, and read an error message without panicking |
| 2 | [Values, Types & Operators](02-values-types-and-operators/README.md) | Work with whole numbers, decimals, characters, text and true/false, and calculate with them correctly |
| 3 | [Variables, Input & Strings](03-variables-input-and-strings/README.md) | Store data, read what the user types with `Scanner`, and format text that talks back |
| 4 | [Making Decisions](04-making-decisions/README.md) | Make a program choose what to do with `if` / `else if` / `else` and `switch` |
| 5 | [Loops](05-loops/README.md) | Repeat work with `while`, `do`-`while` and `for`, and add randomness with `Random` |
| 6 | [Arrays](06-arrays/README.md) | Store and process whole collections of values, including grids |
| 7 | [ArrayList, HashMap & HashSet](07-arraylists-and-hashmaps/README.md) | Use collections that grow, look things up by name, count things, and remove duplicates |
| 8 | [Methods](08-methods/README.md) | Break a program into reusable, named, testable pieces |
| 9 | [Capstone 1: Text Adventure](09-capstone-text-adventure/README.md) | Build a complete game as a team, in stages, with a real Git history |

### Part 2: Object-Oriented Java

| # | Module | You will be able to... |
|---|--------|------------------------|
| 10 | [Classes & Objects](10-classes-and-objects/README.md) | Design your own types that bundle data and behaviour, and protect that data with encapsulation |
| 11 | [Inheritance & Polymorphism](11-inheritance-and-polymorphism/README.md) | Build families of related classes, override methods, and write code that works with all of them at once |
| 12 | [Interfaces, Enums & Records](12-interfaces-enums-and-records/README.md) | Define contracts between classes, sort anything with `Comparable` and `Comparator`, and model fixed sets of values |
| 13 | [Exceptions & Debugging](13-exceptions-and-debugging/README.md) | Handle bad input gracefully, create your own exceptions, and find bugs with the VS Code debugger |
| 14 | [Files & Data](14-files-and-data/README.md) | Make programs remember things using text and CSV files |
| 15 | [Generics, Collections & Lambdas](15-generics-collections-and-lambdas/README.md) | Write type-safe reusable code, choose the right collection, and transform data with streams |
| 16 | [Recursion & Algorithms](16-recursion-and-algorithms/README.md) | Write methods that call themselves, and compare searching and sorting algorithms by speed |
| 17 | [Packages, Maven & JUnit Testing](17-packages-maven-and-junit/README.md) | Organise code into packages, build it with Maven, and prove it works with automated tests |
| 18 | [GUIs with Swing](18-guis-with-swing/README.md) | Build windowed, event-driven programs with buttons, text fields, menus and drawing |
| 19 | [Capstone 2](19-capstone-project-2/README.md) | Combine every Part 2 idea into one tested, persistent, track-based application |

## Ground rules for how we'll work

- **Type the code yourself.** Copy-pasting defeats the purpose. Your
  fingers and your mistakes are how the syntax sticks.
- **Read every error message fully.** Java's compiler tells you the
  file, the line number, and what it expected. When a program crashes
  while running, the *top* of the stack trace names the problem and the
  first line that mentions *your* file shows where it happened.
- **Compile and run often.** Write 3–5 lines, then run. Don't write 50
  lines and try to fix twenty compiler errors at once.
- **Commit often.** After finishing an example, exercise, or project step,
  commit it. Module 0 shows you exactly how. Small, frequent commits are a
  professional habit you're building from day one.

## Prerequisites

None, other than curiosity and a willingness to make (and fix) mistakes.
Mistakes are not a sign you're bad at this. They're the main way anyone
learns to program. Start with [Module 0: Setup](00-setup/README.md).
