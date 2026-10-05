# Module 1: First Steps

## Hook: a spiral in a window

Open [`examples/Ex00Spiral.java`](examples/Ex00Spiral.java), or better,
create a file called `Spiral.java` in your
`java-coursework/01-first-steps` folder and type it in yourself (don't
worry about understanding it yet; just make sure every bracket and
semicolon matches). Then run it:

```bash
java Ex00Spiral.java
```

A window opens and a glowing rainbow spiral appears on a black
background. Close the window when you've enjoyed it.

Now find the line `heading = heading + 59;`, change `59` to `90`, and
run it again. Then try `121`. Then `144`. One number changes the whole
picture, because the program is a list of instructions, and the
computer follows them *exactly*. By the end of this module you'll
understand the shape of this program and how its drawing commands
work, and in the module project you'll draw your own initials. (The
`for` line, which repeats the indented lines 180 times, gets a whole
module of its own: Module 5.)

> If you typed the program yourself and got errors, good: keep them.
> This module teaches you how to read them.

## What is a program, really?

A **program** is a list of instructions for a computer, written in a
language both you and the computer can work with. Computers only truly
understand **machine code**, long strings of numbers that are almost
impossible for people to read. So we write in a **programming language**
like Java, designed to be readable by humans, and let other programs
translate it for us.

Java does this translation in **two steps**:

```
you write          the compiler           the Java Virtual Machine
Hello.java  --->   javac checks and  ---> java runs the bytecode
(Java code)        translates it to       on your computer
                   Hello.class
                   (bytecode)
```

1. The **compiler**, `javac`, reads your whole `.java` file, checks that
   it follows Java's grammar rules, and translates it into **bytecode**,
   saved in a `.class` file. If it finds a mistake, it refuses to
   produce anything, and tells you what's wrong.
2. The **Java Virtual Machine** (the **JVM**), started with the `java`
   command, runs that bytecode.

Why two steps? Bytecode isn't tied to one kind of computer. The same
`Hello.class` file runs on Windows, macOS, Linux, a server or an
Android-style device, as long as it has a JVM. Java's original slogan
was **"write once, run anywhere."** That's a big reason Java runs
banking systems, Android apps, huge websites, and the software in
devices from smart cards to Blu-ray players.

Other languages make other choices. C and C++ compile straight to
machine code for one kind of computer. Python skips the separate
compile step and runs your code directly. Java sits in between.

## The shape of every Java program

Here's the smallest useful Java program,
[`examples/Ex01Hello.java`](examples/Ex01Hello.java):

```java
public class Ex01Hello {
    public static void main(String[] args) {
        System.out.println("Hello, world!");
    }
}
```

There's more ceremony here than you might expect for one line of
output. For now, treat the outer lines as a **frame** that every program
needs, and learn what each part is for:

| Part | What it means (for now) |
|---|---|
| `public class Ex01Hello` | Every piece of Java code lives inside a **class**. This one is called `Ex01Hello`. The file **must** be called `Ex01Hello.java`, with exactly the same spelling and capitals. |
| `{` … `}` | **Braces** mark where something starts and ends. The class's braces hold everything in the class. |
| `public static void main(String[] args)` | The **main method**: the place your program starts. When you run the program, Java looks for exactly this line and runs what's between its braces. |
| `System.out.println(...)` | An instruction (a **statement**) that prints a line of text. |
| `;` | Every statement ends with a **semicolon**, like a full stop at the end of a sentence. |

You'll understand `public`, `static`, `void` and `String[] args`
properly by Modules 8 and 10. Until then, type them exactly as shown.
VS Code can type the frame for you: in a new, empty `.java` file, type
`main` and press Enter to get the `main` line and its braces.

**Indentation** (the spaces at the start of lines) doesn't change what
a Java program does: Java uses braces, not spaces, to see where things
start and end. But it's how *humans* see the structure, so always indent
the inside of each pair of braces by four spaces. VS Code does it for
you, and **Shift+Alt+F** (Windows/Linux) or **Shift+Option+F** (Mac)
tidies a whole file.

## Three ways to run Java

### 1. jshell, for quick experiments

Type `jshell` in a terminal. You get the `jshell>` prompt you met in
Module 0:

```java
jshell> 2 + 3
$1 ==> 5
jshell> System.out.println("Hello!")
Hello!
jshell> 7 * 6
$2 ==> 42
```

jshell reads what you type, evaluates it, prints the result, and loops
back for more (a **REPL**: Read–Evaluate–Print Loop). You don't need a
class or a `main` method, and you can even leave out the semicolon.
It's perfect for "what happens if I do this?" Nothing you type there is
saved. Type `/exit` to leave.

### 2. One command: `java File.java`

For anything you want to keep, write your code in a `.java` file and
run it:

```bash
java Ex01Hello.java
```

This compiles the file in memory and runs it straight away. It's the
quickest way to run a single-file program, and it's what VS Code's ▶
button does for you. **Almost everything in Part 1 runs this way.**

### 3. The classic two steps: `javac`, then `java`

This is what's happening behind the scenes, and what real projects do:

```bash
javac Ex01Hello.java     # compile: creates Ex01Hello.class
java Ex01Hello           # run the class (no .java, no .class on the end)
```

Try it, and look in the folder afterwards: a new `Ex01Hello.class` file
appeared. That's the bytecode. Open it in VS Code if you're curious:
it's not meant for humans. From Module 10 onwards, when programs are
split across several files, you'll use these two steps.

> **The #1 beginner mix-up:** the `jshell>` prompt and your terminal's
> prompt are two different places. `java Ex01Hello.java` is a terminal
> command. Typed at `jshell>`, it's an error. If you see `jshell>`, type
> `/exit` first.

## Your first instructions: printing

```java
System.out.println("Hello, world!");
```

- `System.out` is the **standard output**: the terminal.
- `println` ("print line") is a **method**: a named, ready-made piece of
  code that does a job. Its job is to show something and then move to a
  new line.
- The **parentheses** `( )` are how you *call* (use) a method. Whatever
  you put inside them is what you're handing to it.
- `"Hello, world!"` is a **string**: a piece of text. The double quotes
  tell Java "this is text, not code." In Java, text **must** use double
  quotes.

You can join text and other values together with `+`:

```java
System.out.println("I am " + 17 + " years old");   // I am 17 years old
System.out.println("2 + 3 = " + (2 + 3));          // 2 + 3 = 5
System.out.println("2 + 3 = " + 2 + 3);            // 2 + 3 = 23  (!)
System.out.println(7 * 6);                         // 42
System.out.println();                              // an empty line
```

Notice that `2 + 3` *without* quotes is calculated, while `"2 + 3 = "`
*with* quotes is printed exactly as written. Quotes are the difference
between "say this" and "work this out." And look closely at the third
line. Java works from left to right: `"2 + 3 = " + 2` joins the text and
the 2 into `"2 + 3 = 2"`, and then `+ 3` joins a 3 on the end. The
brackets in the second line make Java add the numbers *first*. Module 2
explains exactly why.

`print` (without `ln`) prints *without* moving to a new line:

```java
System.out.print("Loading");
System.out.print("...");
System.out.println("done!");        // Loading...done!
```

Some characters can't be typed directly inside a string. A backslash
`\` gives the next character a special meaning, called an **escape
sequence**:

| Escape | Means | Example | Prints |
|---|---|---|---|
| `\n` | new line | `"one\ntwo"` | `one` and `two` on separate lines |
| `\t` | tab | `"Name:\tAma"` | `Name:   Ama` |
| `\"` | a double quote | `"say \"hi\""` | `say "hi"` |
| `\\` | a backslash | `"C:\\Users"` | `C:\Users` |

See [`examples/Ex02PrintManyThings.java`](examples/Ex02PrintManyThings.java).

## Comments: notes for humans

Java ignores comments completely. They're for the people reading your
code, including you, three weeks from now. There are three kinds:

```java
// A line comment: everything after // on this line is ignored.

/* A block comment can
   span several lines. */

/** A Javadoc comment, which documentation tools read (Module 8). */
```

Good comments explain **why**, not what.
`System.out.println("Welcome!"); // prints Welcome` adds nothing.
`// Greet first so the user knows the program started` is useful. A
handy trick: put `//` in front of a line to switch it off temporarily
without deleting it ("commenting it out"). In VS Code, **Ctrl+/**
(**Cmd+/** on a Mac) comments out the current line. See
[`examples/Ex03Comments.java`](examples/Ex03Comments.java).

## Programs run top to bottom

Java runs the statements inside `main` **one at a time, from the first
to the last**. Order matters:

```java
System.out.println("1. Put on socks");
System.out.println("2. Put on shoes");
```

Swap those lines and the computer happily tells you to put your shoes on
first. It doesn't know what socks are. It just follows the order you
gave it. See [`examples/Ex04TopToBottom.java`](examples/Ex04TopToBottom.java).

## Errors are messages, not failures

You *will* see errors constantly. Every programmer does, every day.
An error message is Java telling you exactly what confused it and
where. Learning to read them is one of the most valuable skills in this
whole course. Java has two very different kinds.

### Compile-time errors: nothing runs

The compiler checks your *whole* file before any of it is allowed to
run. Here's a file with a missing semicolon:

```java
public class Ex05CompileError {
    public static void main(String[] args) {
        System.out.println("Line one");
        System.out.println("Line two is missing something")
        System.out.println("Line three");
    }
}
```

Running it gives:

```
Ex05CompileError.java:11: error: ';' expected
        System.out.println("Line two is missing something")
                                                           ^
1 error
error: compilation failed
```

Read it piece by piece:

1. **`Ex05CompileError.java:11`**: the file, and the **line number**
   (line 11 of the real example file, which starts with comments).
2. **`error: ';' expected`**: what the compiler wanted and didn't find.
3. The line of code itself, with a `^` **caret** pointing at the exact
   spot.

Notice that `Line one` did **not** print, even though line one was
fine. A compile-time error means **nothing runs at all**. VS Code
underlines these mistakes in red as you type, before you even press
▶. Hover the mouse over the red squiggle to read the message.

### Runtime errors (exceptions): the program crashes partway

Some problems can only be discovered while the program is running.
This file compiles perfectly:

```java
System.out.println("Sharing 10 sweets between 0 friends...");
System.out.println("Each friend gets " + (10 / 0));
System.out.println("Finished.");
```

But running it gives:

```
Sharing 10 sweets between 0 friends...
Exception in thread "main" java.lang.ArithmeticException: / by zero
	at Ex06RuntimeError.main(Ex06RuntimeError.java:12)
```

This is a **stack trace**. Java calls a runtime error an
**exception**:

1. **The first line** says *what* went wrong: an `ArithmeticException`,
   because you can't divide by zero.
2. **The `at` line** says *where*: in the `main` method of
   `Ex06RuntimeError`, file `Ex06RuntimeError.java`, line 12.

Notice that the first line *did* print. Java ran it, then stopped the
moment it hit the bad line. `Finished.` never printed.

### Errors you'll meet this week

| Error | Kind | Usually means | Example |
|---|---|---|---|
| `';' expected` | compile | A statement is missing its semicolon. The caret often points at the *end* of the line. | `System.out.println("hi")` |
| `cannot find symbol` | compile | A misspelt name. The `symbol:` line under the message says which name. | `System.out.printn("hi");` |
| `package system does not exist` | compile | A capital letter is missing: it's `System`, not `system`. | `system.out.println("hi");` |
| `unclosed string literal` | compile | A string is missing its closing `"` (or used `'` at one end). | `println("hi');` |
| `class X is public, should be declared in a file named X.java` | compile | The class name and the file name don't match. | `public class Greeting` in `Hello.java` |
| `ArithmeticException: / by zero` | runtime | A whole number was divided by zero. | `10 / 0` |

See [`examples/Ex05CompileError.java`](examples/Ex05CompileError.java) and
[`examples/Ex06RuntimeError.java`](examples/Ex06RuntimeError.java). Both
are broken on purpose. Run each, read the message, then fix it.

> **Several errors at once?** The compiler reports every problem it can
> find, and one mistake can confuse it into reporting several, like a
> missing quote making it misread the rest of the line. **Always fix the
> first error first, then run again.** Often the others disappear.

## Drawing in a window

The hook drew in a window using Java's built-in graphics tools, which
come with every JDK. [`examples/Ex07DrawingSquare.java`](examples/Ex07DrawingSquare.java)
shows the pattern you'll use. All your drawing goes inside a `draw`
section, and the code below it (which opens the window) stays the same:

```java
void draw(Graphics2D pen) {
    pen.setColor(Color.BLUE);
    pen.setStroke(new BasicStroke(6));     // a thicker pen
    pen.drawRect(100, 80, 200, 200);        // x, y, width, height
    pen.setColor(Color.ORANGE);
    pen.fillOval(150, 130, 100, 100);       // a filled circle
}
```

`pen` is the drawing tool, and every command is a method you call on it:

| Command | What it does |
|---|---|
| `pen.drawLine(x1, y1, x2, y2)` | Draw a straight line from `(x1, y1)` to `(x2, y2)` |
| `pen.drawRect(x, y, w, h)` / `pen.fillRect(...)` | A rectangle outline / a filled rectangle. `(x, y)` is its top-left corner |
| `pen.drawOval(x, y, w, h)` / `pen.fillOval(...)` | An oval that fits inside that rectangle. Make `w` and `h` equal for a circle |
| `pen.drawArc(x, y, w, h, start, angle)` | Part of an oval: start at `start` degrees and sweep `angle` degrees |
| `pen.setColor(Color.RED)` | Change the colour. Try `BLUE`, `GREEN`, `ORANGE`, `MAGENTA`, `BLACK`… or mix your own with `new Color(255, 140, 0)` |
| `pen.setStroke(new BasicStroke(5))` | Change the line thickness |
| `pen.setFont(new Font("SansSerif", Font.BOLD, 24))` | Choose a font, a style and a size for text |
| `pen.drawString("Hi", x, y)` | Write text, starting at `(x, y)` |

The window's **coordinates** work like this:

```
(0, 0) ------------- x grows to the right ---------->
  |
  |        (100, 80)
  |            +----------+
  y grows      |          |
  DOWNWARDS    |          |
  |            +----------+
  v                    (300, 280)
```

`(0, 0)` is the **top-left corner**, and `y` grows *downwards*. Most
graphics systems work this way, because screens are drawn line by line
from the top.

Don't worry yet about the `import` lines, `extends JPanel`, or why
some code says `pen.` and some says `Color.`. For now, copy the pattern.
Modules 10 and 11 explain most of it, and the optional Appendix A
explains every line. Re-read the hook now: you
can recognise `setColor`, `setStroke` and `drawLine`, and the frame
around them. The `for` loop and the maths that turns a heading into
`x` and `y` arrive in Modules 2 and 5.

## Common beginner mistakes

- **Capital letters.** Java is **case-sensitive**: `System` works,
  `system` doesn't; `String` works, `string` doesn't. `main` must be
  lower case.
- **A missing semicolon** at the end of a statement. Lines that end
  with `{` or `}` don't need one; statements do.
- **The file name doesn't match the class name.** `public class Hello`
  must live in `Hello.java`, capital H included.
- **Single quotes around text.** `'Hello'` is an error in Java. Text
  uses double quotes: `"Hello"`. (Single quotes are for single
  characters, as Module 2 explains.)
- **Mismatched braces or brackets.** Every `{` needs a matching `}` and
  every `(` a matching `)`. VS Code highlights the partner when you
  click next to one.
- **Forgetting the brackets around a sum** when joining it to text:
  `"Total: " + 2 + 3` prints `Total: 23`.
- **Forgetting to save** before running. VS Code shows a dot ● on the
  file's tab when it has unsaved changes. Turn on **File → Auto Save**
  to stop this happening.
- **Changing the code, but the output doesn't change.** If you're using
  the two-step method, `java Ex01Hello` runs the *old* `.class` file
  until you run `javac` again. Recompile after every change (or use
  `java Ex01Hello.java`, which always compiles first).
- **Trying to fix every error message at once.** Fix the *first* one,
  run again, repeat.

## Try it yourself

1. Run every file in [`examples/`](examples/), including the two broken
   ones (fix them after you've read their error messages). Run
   `Ex01Hello` both ways: `java Ex01Hello.java`, and `javac` then
   `java`.
2. Complete [`exercises/Exercise1.java`](exercises/Exercise1.java) and
   [`exercises/Exercise2.java`](exercises/Exercise2.java).
3. Build the [module project](project/README.md): your initials in
   Java art.
4. Commit and push your work:

   ```bash
   git add .
   git commit -m "Complete Module 1: first steps"
   git push
   ```

Next: **[Module 2: Values, Types & Operators](../02-values-types-and-operators/README.md)**.
