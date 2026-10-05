# Module 13: Exceptions & Debugging

## Hook: try to break this calculator

Run [`examples/Ex00CrashProofCalculator.java`](examples/Ex00CrashProofCalculator.java)
and do your worst:

```bash
java Ex00CrashProofCalculator.java
```

```
=== ROUND 1: no safety net ===
Sum: 7 / 0
CRASH! java.lang.ArithmeticException: You can't divide by zero.
(Without a try/catch, the program would end here.)

=== ROUND 2: crash-proof (type quit to stop) ===
Sum: ten + 2
Those don't look like numbers. Try: 12 * 4
Sum: 7 / 0
You can't divide by zero.
Sum: 2 ^ 10
Unknown operator: ^
Sum:
Type a sum like: 12 * 4
Sum: 12 * 4
= 48.0
```

In Round 1, a single bad answer ends everything: that's what every
program you've written so far does. Round 2 runs the **same**
calculation code, but it survives letters, division by zero, unknown
operators and empty lines, and explains each problem in plain English.
The difference is a few lines of `try` and `catch`.

Real software is used by real people, who type anything. A program that
crashes on bad input is a broken program. This module shows you how to
handle problems gracefully with **exceptions**, how to create your own,
and how to hunt down the bugs that *don't* crash, using the **debugger**.

## What an exception is

When something goes wrong while a program is running (a division by
zero, text where a number should be, an index past the end of an
array), Java **throws an exception**: it creates an object describing the
problem, stops what it was doing, and looks for code that will
**catch** it. If nothing catches it, the program ends and prints a
**stack trace**.

You've met plenty already: `ArithmeticException`,
`NumberFormatException`, `ArrayIndexOutOfBoundsException`,
`NullPointerException`, and the `IllegalArgumentException`s you've been
throwing since Module 10.

## Reading a stack trace

Run [`examples/Ex01ReadingStackTraces.java`](examples/Ex01ReadingStackTraces.java):

```
Akua: average 80
Exception in thread "main" java.lang.ArithmeticException: / by zero
	at Ex01ReadingStackTraces.average(Ex01ReadingStackTraces.java:13)
	at Ex01ReadingStackTraces.report(Ex01ReadingStackTraces.java:17)
	at Ex01ReadingStackTraces.main(Ex01ReadingStackTraces.java:22)
```

1. **The first line** says *what*: the exception's type
   (`ArithmeticException`) and its message (`/ by zero`).
2. **The `at` lines** say *where*, as a chain of method calls with the
   **most recent first**: the crash happened in `average` at line 13,
   which was called by `report` at line 17, which was called by `main`
   at line 22. This is the **call stack** from Module 8, printed out.
3. **Look for the first line that mentions your own code.** In bigger
   programs the top lines are often inside Java's library
   (`at java.base/...`). The bug is almost always where *your* code
   called it.

Here the crash is in `average`, but the *cause* is at line 22: `main`
passed it an empty array. Reading the whole chain tells you both.

## `try` and `catch`

Put code that might throw in a **`try`** block, and say what to do if it
does in a **`catch`** block:

```java
try {
    int age = Integer.parseInt(text.trim());
    System.out.println("Next year you'll be " + (age + 1));
} catch (NumberFormatException e) {
    System.out.println("'" + text + "' isn't a whole number.");
}
System.out.println("The program carries on either way.");
```

- If nothing goes wrong, the `try` block runs to the end and the `catch`
  is skipped.
- If a line throws, Java **jumps immediately** to the matching `catch`.
  The rest of the `try` block is skipped (so there's no "next year"
  line).
- After the `catch`, the program **carries on** normally.
- `e` is the exception object. `e.getMessage()` gives its message, and
  printing `e` gives its type and message.

Combine `try`/`catch` with a loop, and you get the most useful pattern in
this module, **keep asking until it works**:

```java
while (true) {
    System.out.print("Type a whole number: ");
    try {
        number = Integer.parseInt(in.nextLine().trim());
        break;                     // only reached if parseInt succeeded
    } catch (NumberFormatException e) {
        System.out.println("  Not a whole number. Try again.");
    }
}
```

See [`examples/Ex02TryCatch.java`](examples/Ex02TryCatch.java).

### Several catches

A `try` can have several `catch` blocks, one per kind of problem. Java
uses the **first** one that matches:

```java
try {
    ...
} catch (NumberFormatException e) {
    System.out.println("Please type whole numbers.");
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("There's no such box.");
} catch (ArithmeticException e) {
    System.out.println("Nobody to share with!");
}
```

To handle several kinds the same way, join them with `|`:
`catch (ClassCastException | NullPointerException e)`. See
[`examples/Ex03MultipleCatches.java`](examples/Ex03MultipleCatches.java).

### `finally`

A **`finally`** block runs **whatever happens**: after the `try`
succeeds, after a `catch` handles a problem, even after a `return`. It's
where clean-up goes: switching equipment off, closing a file (Module 14),
printing a footer. See [`examples/Ex04Finally.java`](examples/Ex04Finally.java).

## The exception family tree

Exceptions are objects, and their classes form an inheritance hierarchy
(Module 11):

```
Throwable
├── Error                      serious problems with the JVM itself (OutOfMemoryError,
│                              StackOverflowError): don't catch these
└── Exception
    ├── IOException            CHECKED: problems outside your program (files, networks)
    ├── InterruptedException   CHECKED
    └── RuntimeException       UNCHECKED: usually bugs, or bad input
        ├── ArithmeticException
        ├── IllegalArgumentException
        │   └── NumberFormatException
        ├── IllegalStateException
        ├── IndexOutOfBoundsException
        │   ├── ArrayIndexOutOfBoundsException
        │   └── StringIndexOutOfBoundsException
        ├── NullPointerException
        └── ClassCastException
```

Because of inheritance, a `catch` catches its type **and every subclass**:
`catch (IllegalArgumentException e)` also catches
`NumberFormatException`; `catch (Exception e)` catches almost
everything. So put specific catches **before** general ones (the
compiler complains if a general catch makes a later one unreachable).
Catch the most specific type you can: `catch (Exception e)` everywhere
hides bugs. See [`examples/Ex05Hierarchy.java`](examples/Ex05Hierarchy.java).

## Throwing exceptions

You've thrown exceptions since Module 10 to stop invalid objects being
created. The two you'll use most:

```java
if (ohms <= 0) {
    throw new IllegalArgumentException("Resistance must be positive, not " + ohms);
}
if (amount > charge) {
    throw new IllegalStateException("Only " + charge + "% left");
}
```

- **`IllegalArgumentException`**: the **argument** is bad (a negative
  resistance, an empty name).
- **`IllegalStateException`**: the argument is fine, but the **object**
  can't do it right now (the battery is flat, the queue is empty).

Throwing early, with a precise message, is far better than carrying on
with bad data and failing mysteriously later. A method that detects a
problem it can't sensibly fix should **throw**; the code that *can*
decide what to do (usually the part talking to the user) should
**catch**. See [`examples/Ex06ThrowingExceptions.java`](examples/Ex06ThrowingExceptions.java).

## Checked and unchecked exceptions

Look at the family tree again. Java treats two groups differently:

- **Unchecked** exceptions (`RuntimeException` and its subclasses)
  usually mean a bug or bad input. You *may* catch them, but the compiler
  doesn't insist.
- **Checked** exceptions (every other `Exception`, such as
  `IOException`) are problems outside your program's control: a missing
  file, a network failure. The compiler **insists** that you deal with
  them, in one of two ways: catch them, or **declare** that your method
  passes them on, with `throws`:

```java
static String firstLine(String fileName) throws IOException {
    return Files.readAllLines(Path.of(fileName)).get(0);
}
```

Forget, and the code doesn't compile: "unreported exception IOException;
must be caught or declared to be thrown". This is why Module 5's
countdown needed `throws InterruptedException` on `main`: `Thread.sleep`
throws a checked exception. See
[`examples/Ex07CheckedExceptions.java`](examples/Ex07CheckedExceptions.java).

## Your own exceptions

When the built-in types don't say exactly what went wrong, write your
own. An exception class is an ordinary class that extends `Exception`
(checked) or `RuntimeException` (unchecked):

```java
class InsufficientFundsException extends Exception {
    private final double shortBy;

    InsufficientFundsException(double shortBy) {
        super(String.format("Insufficient funds: short by GHS %.2f", shortBy));
        this.shortBy = shortBy;
    }

    double getShortBy() {
        return shortBy;
    }
}
```

`super(message)` passes the message up to `Exception`, so `getMessage()`
works. Extra fields carry details the caller can use, such as how much
more money is needed. Then a method can say exactly what can go wrong,
and callers can catch exactly that:

```java
void withdraw(double amount) throws InsufficientFundsException { ... }

try {
    account.withdraw(120);
} catch (InsufficientFundsException e) {
    System.out.println(e.getMessage() + " (top up at least " + e.getShortBy() + ")");
}
```

Make an exception **checked** when the caller can reasonably be expected
to recover (not enough money, a wrong PIN). Make it **unchecked** when it
signals a mistake in the calling code. See
[`examples/Ex08CustomExceptions.java`](examples/Ex08CustomExceptions.java).

## Crash-proof input helpers

[`examples/Ex09InputHelpers.java`](examples/Ex09InputHelpers.java) wraps
the "keep asking" pattern in reusable methods: `readInt(prompt, min,
max)`, `readDouble(...)` and `readNonEmpty(...)`. Write them once, and
every program you write from now on can read numbers without crashing.
You'll build your own versions in Exercise 1.

## The debugger: finding bugs that don't crash

The worst bugs don't throw anything. The program runs happily and gives
the **wrong answer**.
[`examples/Ex10BuggyAverage.java`](examples/Ex10BuggyAverage.java) says
the average of 70, 85 and 90 is 87.50. It should be 81.67. Where's the
bug?

You could add `println`s everywhere. Or you could use the **debugger**,
which lets you pause a running program, look inside every variable, and
run it one line at a time. Here's how, in VS Code:

1. Open `Ex10BuggyAverage.java`. Click in the margin **just left of the
   line number** of the line `int total = 0;`. A red dot appears: a
   **breakpoint**, a place where the program will pause.
2. Click **Debug** in the little menu that appears above `main` (or press
   **F5**). The program starts, and stops at your breakpoint. That line
   is highlighted: it's about to run.
3. Look at the **Variables** panel on the left. You can see `values`
   (click the arrow to expand it: `[70, 85, 90]`).
4. Press **F10** (**Step Over**) to run one line at a time. Watch `i` and
   `total` in the Variables panel as the loop goes round. The first value
   of `i` is **1**, so `values[0]`, the 70, is never added. Found it.
5. Press **Shift+F5** to stop. Fix the loop to start at 0, fix the
   division, and run it again.

The other controls on the debug toolbar:

| Button | Key | Does |
|---|---|---|
| Continue | F5 | Run until the next breakpoint |
| Step Over | F10 | Run this line (including any method it calls), then pause |
| Step Into | F11 | If this line calls one of *your* methods, go inside it |
| Step Out | Shift+F11 | Finish this method, and pause back in its caller |
| Restart / Stop | Ctrl+Shift+F5 / Shift+F5 | |

You can also **hover** over any variable in the code to see its value,
and add expressions like `total / values.length` to the **Watch** panel.
When a program *does* crash, put a breakpoint a few lines before the line
in the stack trace and step up to it: you'll see exactly which value was
wrong.

## Common beginner mistakes

- **Catching an exception and doing nothing**: `catch (Exception e) { }`.
  The problem is hidden, not solved. At the very least, print a message.
- **Catching `Exception` everywhere.** Catch the specific type you
  expect, so real bugs still show up.
- **A general `catch` before a specific one**, making the specific one
  unreachable (a compile error).
- **Forgetting that the rest of the `try` block is skipped** after an
  exception. Variables assigned there may not have their values.
- **Declaring a variable inside `try` and using it after.** It's out of
  scope. Declare it before the `try`.
- **Throwing `Exception` itself** instead of a meaningful type. Use
  `IllegalArgumentException`, `IllegalStateException`, or your own class.
- **Using exceptions for ordinary decisions.** Checking `if (list.isEmpty())`
  is better than catching `IndexOutOfBoundsException`.
- **Guessing instead of debugging.** If you've changed the same line
  three times and it's still wrong, set a breakpoint and look.

## Try it yourself

1. Run every file in [`examples/`](examples/). Find the bug in
   `Ex10BuggyAverage.java` **with the debugger**, following the steps
   above.
2. Complete [`exercises/Exercise1.java`](exercises/Exercise1.java) and
   [`exercises/Exercise2.java`](exercises/Exercise2.java).
3. Build the [module project](project/README.md) in the track of your
   choice: an ATM, a safe circuit calculator, or a vitals entry
   validator.
4. Commit and push your work:

   ```bash
   git add .
   git commit -m "Complete Module 13: exceptions and debugging"
   git push
   ```

Next: **[Module 14: Files & Data](../14-files-and-data/README.md)**.
