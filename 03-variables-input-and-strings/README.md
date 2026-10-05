# Module 3: Variables, Input & Strings

## Hook: a program that knows things about you

Run [`examples/Ex00NameAnalyser.java`](examples/Ex00NameAnalyser.java)
and answer its two questions:

```bash
java Ex00NameAnalyser.java
```

```
What's your first name? Ama
What year were you born? 2007

Hello, AMA!
Your name has 3 letters. It starts with 'A' and ends with 'a'.
Backwards, it's amA.
You're about 19, so you've been alive for roughly 599,184,000 seconds.
In the year 2050 you'll be 43.
```

Run it again with a friend's name. Every program so far has printed the
same thing every time. This one **listens**: it stores what you type,
works things out from it, and talks back in neatly formatted sentences.
That's three ideas, and they're this module: **variables** (storing
values), **input** (reading the keyboard with a `Scanner`), and
**strings** (taking text apart and formatting it).

## Variables: named boxes

You met variables briefly in Module 2. A **variable** is a named box
that holds one value. In Java, every box has a **type** that never
changes:

```java
String name = "Kofi";
int age = 19;
double heightInMetres = 1.78;

System.out.println(name + " is " + age + " years old.");
```

`int age = 19;` does two things: it **declares** the variable (makes a
box for an `int` called `age`) and **initialises** it (puts 19 in it).
You can do them separately:

```java
int marks;          // declare: an empty box
marks = 74;         // assign: put a value in
```

Java won't let you *use* a box before something has been put in it.
`System.out.println(marks);` straight after `int marks;` is the
compile-time error `variable marks might not have been initialized`.

Once a variable exists, you use its **name** wherever you'd use its
value. Java looks inside the box each time. See
[`examples/Ex01Variables.java`](examples/Ex01Variables.java).

### Changing a variable

A single `=` means **assign**: "work out the right-hand side, then store
it in the box on the left." So this, which looks wrong in maths, is
perfectly normal in programming:

```java
score = score + 5;      // read score, add 5, store the answer back in score
```

It's so common that Java has shortcuts:

| Shortcut | Same as |
|---|---|
| `score += 10;` | `score = score + 10;` |
| `score -= 3;` | `score = score - 3;` |
| `score *= 2;` | `score = score * 2;` |
| `score /= 4;` | `score = score / 4;` |
| `score++;` | `score = score + 1;` |
| `score--;` | `score = score - 1;` |

`++` and `--` will be everywhere once you meet loops in Module 5. See
[`examples/Ex02UpdatingVariables.java`](examples/Ex02UpdatingVariables.java),
which also shows how to **swap** two variables (you need a third,
temporary box).

### Naming variables

The rules (break them and the program won't compile):

- Letters, digits, `_` and `$` only, and the name can't **start** with a
  digit. `player2` is fine, `2ndPlayer` isn't.
- No spaces. `my score` is two names.
- Not a **reserved word** that already means something in Java: `class`,
  `int`, `public`, `new`, `if`…
- Case matters: `age`, `Age` and `AGE` are three different variables.

The **style** every Java programmer uses (break this and your code
compiles, but looks wrong to everyone who reads it):

- **camelCase** for variables: start lower case, and capitalise each new
  word. `numberOfStudents`, `averageMark`, `isLoggedIn`.
- Choose names that say what the value **means**. `speedKmPerHour` beats
  `s`, `x` or `data`.
- Booleans read well as questions: `isFull`, `hasPaid`, `canVote`.

### Constants: values that must never change

Add `final` and the variable can be assigned only once. Try to change it
and the compiler refuses. By convention, constants are written in
**UPPER_SNAKE_CASE**:

```java
final double VAT_RATE = 0.15;
final int SECONDS_PER_HOUR = 3600;

VAT_RATE = 0.2;     // error: cannot assign a value to final variable VAT_RATE
```

Remember the Module 2 project, where you typed `0.621371` again and
again? A constant fixes that: type the number once, give it a name, and
if it ever changes, you change it in one place.

### `var`: let Java work out the type

Inside a method, you can write `var` instead of the type, and Java works
out the type from the value:

```java
var town = "Kumasi";     // Java decides: String
var count = 3;           // Java decides: int
count = "three";         // still an error: count is an int forever
```

`var` saves typing, but the type is just as fixed as before. This course
mostly writes types out in full, because while you're learning, seeing
the type is helpful. See
[`examples/Ex03NamingAndConstants.java`](examples/Ex03NamingAndConstants.java).

## Input: reading the keyboard with `Scanner`

To read what the user types, you use a **`Scanner`**. It isn't
automatically available like `System` and `Math`, so you **import** it
at the very top of the file, above the class:

```java
import java.util.Scanner;

public class Greeter {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);    // one Scanner for the keyboard

        System.out.print("What's your name? ");
        String name = in.nextLine();            // waits for the user to press Enter

        System.out.println("Hello, " + name + "!");
    }
}
```

- `import java.util.Scanner;` says "I'll be using the `Scanner` class,
  which lives in the `java.util` package." (Module 17 explains packages.)
- `new Scanner(System.in)` creates a Scanner that reads from the
  keyboard. Create **one**, near the top of `main`, and reuse it.
- `print` (not `println`) leaves the cursor on the same line as the
  question, which looks nicer.

The Scanner can read different types:

| Method | Reads | Example input |
|---|---|---|
| `in.nextLine()` | A whole line of text, up to Enter | `Kofi Boateng` |
| `in.nextInt()` | One whole number | `19` |
| `in.nextDouble()` | One decimal number | `1.78` |
| `in.next()` | One word (stops at a space) | `Kofi` |

If the user types `nineteen` when `nextInt()` expects a number, the
program crashes with an `InputMismatchException`. That's fine for now.
Module 13 shows how to recover. See
[`examples/Ex04Input.java`](examples/Ex04Input.java).

### The `nextLine` trap

Run [`examples/Ex05TheNextLineTrap.java`](examples/Ex05TheNextLineTrap.java)
and watch it **skip** a question:

```java
System.out.print("Age: ");
int age = in.nextInt();         // you type 19 and press Enter
System.out.print("Name: ");
String name = in.nextLine();    // doesn't wait! name is ""
```

When you type `19` and press Enter, the keyboard sends `19` followed by
an invisible "end of line" character. `nextInt()` takes the `19` and
**leaves the end of line behind**. Then `nextLine()` reads up to the
next end of line, finds one immediately, and returns an empty string.

There are two fixes. You can call `in.nextLine();` on its own after each
`nextInt()` to throw the leftover away. Or, simpler and safer, **always
read whole lines, and convert them yourself**:

```java
System.out.print("Age: ");
int age = Integer.parseInt(in.nextLine().trim());
```

This course uses the second pattern everywhere: `nextLine()` for every
answer, then `Integer.parseInt` or `Double.parseDouble` for numbers. The
`.trim()` removes any spaces the user accidentally typed around the
number.

## Formatting output with `printf`

Joining strings with `+` gets messy, and it can't line things up or
control decimal places. `printf` ("print formatted") can. You write the
text once with **placeholders**, and list the values to drop in:

```java
String item = "Notebook";
int quantity = 3;
double price = 4.5;

System.out.printf("%d x %s costs GHS %.2f%n", quantity, item, quantity * price);
// 3 x Notebook costs GHS 13.50
```

| Placeholder | For | Example | Gives |
|---|---|---|---|
| `%s` | a String (or anything) | `printf("%s", "Ama")` | `Ama` |
| `%d` | a whole number | `printf("%d", 42)` | `42` |
| `%.2f` | a decimal, 2 places (rounded) | `printf("%.2f", 3.14159)` | `3.14` |
| `%,d` | a whole number with commas | `printf("%,d", 34000000)` | `34,000,000` |
| `%n` | a new line | | |
| `%%` | a real percent sign | `printf("15%%")` | `15%` |

A number before the letter sets a minimum **width**, so columns line up.
A `-` means "line up on the left":

```java
System.out.printf("[%10s]%n", "right");    // [     right]
System.out.printf("[%-10s]%n", "left");    // [left      ]
System.out.printf("[%8.2f]%n", 3.14159);   // [    3.14]
```

`printf` doesn't add a new line by itself, so remember the `%n`. The
values must match the placeholders in number, order and type: `%d` with
a `double` crashes with an `IllegalFormatConversionException`.

`String.format` works exactly like `printf`, but **gives back** the text
instead of printing it, so you can store it in a variable:

```java
String label = String.format("%s (%d)", item, quantity);   // "Notebook (3)"
```

See [`examples/Ex06Printf.java`](examples/Ex06Printf.java).

## What strings can do

A `String` comes with dozens of built-in **methods**, called with a dot:

| Method | Gives | `"  Hello, Java!  ".trim()` then… |
|---|---|---|
| `s.length()` | the number of characters | `12` |
| `s.toUpperCase()` / `s.toLowerCase()` | a copy in capitals / small letters | `"HELLO, JAVA!"` |
| `s.trim()` | a copy without spaces at the ends | `"Hello, Java!"` |
| `s.contains(t)` | does `s` contain `t`? | `contains("Java")` is `true` |
| `s.startsWith(t)` / `s.endsWith(t)` | does it start / end with `t`? | `endsWith("!")` is `true` |
| `s.indexOf(t)` | where `t` first appears, or `-1` | `indexOf("Java")` is `7` |
| `s.replace(a, b)` | a copy with every `a` replaced by `b` | `replace("Java", "Ghana")` |
| `s.equals(t)` | is it exactly the same text? | |
| `s.equalsIgnoreCase(t)` | the same text, ignoring capitals? | |
| `s.isEmpty()` | is it `""`? | `false` |

### Strings never change

Strings are **immutable**: no method changes the original string. Each
one *gives back a new string*, and if you don't store it, it's lost:

```java
String shout = "quiet";
shout.toUpperCase();              // the new string is thrown away
System.out.println(shout);        // quiet
shout = shout.toUpperCase();      // store the result
System.out.println(shout);        // QUIET
```

See [`examples/Ex07StringMethods.java`](examples/Ex07StringMethods.java).

### Characters and pieces: `charAt` and `substring`

Each character in a string has a position, called its **index**. Indexes
start at **0**, not 1:

```
 E  N  G  I  N  E  E  R
 0  1  2  3  4  5  6  7
```

```java
String word = "ENGINEER";
word.charAt(0)                    // 'E'  the first character
word.charAt(word.length() - 1)    // 'R'  the last character (index 7, not 8!)
word.substring(0, 3)              // "ENG"  from 0 up to, but NOT including, 3
word.substring(5)                 // "EER"  from 5 to the end
```

`charAt` gives a `char`; `substring` gives a `String`. Asking for an
index that doesn't exist, like `word.charAt(8)`, crashes with a
`StringIndexOutOfBoundsException`.

The "up to but not including" rule seems odd at first, but it's handy:
`substring(0, 3)` gives exactly 3 characters, and `substring(3, 6)`
carries on from where it stopped. Combine `indexOf` and `substring` to
split text:

```java
String fullName = "Abena Owusu";
int space = fullName.indexOf(" ");                // 5
String first = fullName.substring(0, space);      // "Abena"
String last = fullName.substring(space + 1);      // "Owusu"
```

See [`examples/Ex08CharAtAndSubstring.java`](examples/Ex08CharAtAndSubstring.java),
and then [`examples/Ex09Receipt.java`](examples/Ex09Receipt.java), which
puts the whole module together. Now re-read the hook: you can explain
every line except `java.time.Year.now()` (which asks the computer's
clock for the current year) and `new StringBuilder(name).reverse()`
(a ready-made way to reverse text, which Module 5 shows you how to do by
hand).

## Common beginner mistakes

- **Using a variable before giving it a value**: "variable might not
  have been initialized".
- **Declaring the same variable twice.** `int age = 19;` then later
  `int age = 20;` is an error ("variable age is already defined"). The
  second time, leave out the type: `age = 20;`.
- **The `nextLine` trap**: a question gets skipped after `nextInt()`.
  Read every answer with `nextLine()` and convert it.
- **Forgetting `import java.util.Scanner;`**: "cannot find symbol: class
  Scanner".
- **Calling a String method and not keeping the answer.**
  `name.trim();` on its own does nothing useful. Write
  `name = name.trim();`.
- **Off-by-one indexes.** The first character is at index 0; the last is
  at `length() - 1`.
- **`printf` without `%n`**, so the next output runs onto the same line.
- **Comparing strings with `==`.** Use `.equals()`. Module 4 explains
  why `==` sometimes seems to work and then suddenly doesn't.

## Try it yourself

1. Run every file in [`examples/`](examples/). In
   `Ex05TheNextLineTrap.java`, make sure you understand *why* the first
   name comes out empty.
2. Complete [`exercises/Exercise1.java`](exercises/Exercise1.java) and
   [`exercises/Exercise2.java`](exercises/Exercise2.java).
3. Build the [module project](project/README.md): a Mad Libs story
   generator.
4. Commit and push your work:

   ```bash
   git add .
   git commit -m "Complete Module 3: variables, input and strings"
   git push
   ```

Next: **[Module 4: Making Decisions](../04-making-decisions/README.md)**.
