# Module 5: Loops

## Hook: a million dice rolls in the blink of an eye

How often do you get a 7 when you roll two dice? You could roll them a
few hundred times and keep a tally. Or run
[`examples/Ex00DiceMillion.java`](examples/Ex00DiceMillion.java):

```bash
java Ex00DiceMillion.java
```

```
Rolled two dice 1,000,000 times in 40 ms.

 2 | ******                                       2.74%
 3 | *************                                5.54%
 4 | ********************                         8.34%
 5 | ***************************                 11.08%
 6 | **********************************          13.86%
 7 | *****************************************   16.69%
 8 | **********************************          13.89%
 9 | ***************************                 11.14%
10 | ********************                         8.39%
11 | *************                                5.55%
12 | ******                                       2.78%
```

A million rolls, counted and charted, in less time than it takes you to
blink. Run it again: the numbers wobble a little, but the shape never
changes. (7 is the most common total because more pairs of dice add up
to 7 than to anything else: 1+6, 2+5, 3+4, 4+3, 5+2 and 6+1.)

The program is only a few lines long, because of one idea: a **loop**,
which tells the computer to repeat some code. Repetition is where
computers leave humans far behind. They never get bored, never lose
count, and do millions of repetitions a second. This module covers
Java's three kinds of loop, and the patterns that make them useful.

## `while`: repeat as long as a condition is true

```java
int count = 10;
while (count > 0) {
    System.out.println(count + "...");
    count--;
}
System.out.println("Liftoff!");
```

A `while` loop looks like an `if`, and it starts the same way: it checks
the condition, and if it's `true`, it runs the block. The difference is
what happens at the closing brace: instead of carrying on, Java **goes
back up and checks the condition again**. It keeps going round until the
condition is `false`, then carries on after the loop.

Each trip round the loop is called an **iteration**. For a loop to
finish, something inside it must eventually make the condition false.
Here, that's `count--`. Delete that line and the condition stays `true`
forever: an **infinite loop**. If that happens, press **Ctrl+C** in the
terminal to stop the program. See
[`examples/Ex01WhileCountdown.java`](examples/Ex01WhileCountdown.java),
which also uses `Thread.sleep(300)` to pause between numbers.

> **Why `throws InterruptedException`?** `Thread.sleep` can, in
> principle, be interrupted early, and Java insists that you either
> handle that or declare that `main` might pass the problem on. Module 13
> explains exactly what this means. For now, add it to `main` whenever
> you use `Thread.sleep`.

### Pattern: keep asking until the answer is valid

```java
System.out.print("Rate the course from 1 to 10: ");
int rating = Integer.parseInt(in.nextLine().trim());

while (rating < 1 || rating > 10) {
    System.out.print("That's not between 1 and 10. Try again: ");
    rating = Integer.parseInt(in.nextLine().trim());
}
```

The loop condition describes a **bad** answer: "keep looping while it's
wrong." When the user finally types something valid, the loop ends. See
[`examples/Ex02InputValidation.java`](examples/Ex02InputValidation.java).

### Pattern: the sentinel loop

When you don't know in advance how many values the user will type, let
them type a special **sentinel** value, such as `done`, to say they've
finished:

```java
System.out.print("Price: ");
String entry = in.nextLine().trim();
while (!entry.equalsIgnoreCase("done")) {
    total += Double.parseDouble(entry);
    count++;
    System.out.print("Price: ");
    entry = in.nextLine().trim();
}
```

Notice the shape: read once **before** the loop, and again at the
**end** of the loop body. That way the condition always checks the most
recent thing the user typed. See
[`examples/Ex03SentinelLoop.java`](examples/Ex03SentinelLoop.java).

## `do`-`while`: run first, check afterwards

```java
String choice;
do {
    System.out.println("1) Say hello   2) Tell me the time   3) Quit");
    System.out.print("Choose: ");
    choice = in.nextLine().trim();
    // ... act on the choice ...
} while (!choice.equals("3"));
```

A `do`-`while` loop runs its block **first** and checks the condition
**afterwards**, so the block always runs at least once. That's exactly
right for a menu: you always want to show it at least once. Note the
semicolon after the final `)`, and that `choice` is declared *before*
the loop, so the condition at the bottom can see it. See
[`examples/Ex04DoWhile.java`](examples/Ex04DoWhile.java).

## `for`: counting loops

Most loops count: "do this 10 times", "for every number from 1 to 100".
You can count with `while`:

```java
int i = 1;                  // start
while (i <= 5) {            // condition
    System.out.print(i + " ");
    i++;                    // step
}
```

But this is so common that Java has a loop that puts all three parts on
one line:

```java
for (int i = 1; i <= 5; i++) {
    System.out.print(i + " ");      // 1 2 3 4 5
}
```

Read the `for` line as: "start with `i` at 1; keep going while `i <= 5`;
after each iteration, do `i++`." The order of events is: start (once),
then check, body, step, check, body, step… until the check is false.

| Loop | Gives |
|---|---|
| `for (int i = 0; i < 5; i++)` | `0 1 2 3 4` (five numbers, starting at 0: the most common form) |
| `for (int i = 1; i <= 5; i++)` | `1 2 3 4 5` |
| `for (int i = 0; i <= 100; i += 10)` | `0 10 20 … 100` |
| `for (int i = 10; i >= 1; i--)` | `10 9 8 … 1` |

The variable `i` is created by the loop and **only exists inside it**.
Try to use it after the loop and you get "cannot find symbol". (`i` is
the traditional name for a loop counter, but use a more meaningful name
when there is one: `row`, `day`, `student`.)

**Which loop should I use?** Use `for` when you know how many times to
repeat (or you're counting through a range). Use `while` when you're
waiting for something to happen (a valid answer, a sentinel, a target
being reached). Use `do`-`while` when the body must run at least once.
See [`examples/Ex05ForLoop.java`](examples/Ex05ForLoop.java).

### Looping over a String

The indexes of a string go from `0` to `length() - 1`, which is exactly
what the standard `for` loop counts through:

```java
for (int i = 0; i < text.length(); i++) {
    char c = text.charAt(i);
    // ... do something with c ...
}
```

[`examples/Ex06ForOverString.java`](examples/Ex06ForOverString.java)
counts vowels, spells a word out with dashes, and builds a reversed
copy by looping **backwards** from the last index. That's what
`new StringBuilder(name).reverse()` did in the Module 3 hook.

## Accumulator patterns

Many loops build up an answer, one iteration at a time, in a variable
created *before* the loop. These patterns come up constantly:

```java
int total = 0;                     // a running total starts at 0
int count = 0;                     // so does a counter
int largest = Integer.MIN_VALUE;   // the smallest possible int, so anything beats it
long product = 1;                  // a product starts at 1 (0 would ruin it)

for (int i = 0; i < 10; i++) {
    int mark = /* the next value */;
    total += mark;
    count++;
    largest = Math.max(largest, mark);
}
```

After the loop, `total / (double) count` gives the average. See
[`examples/Ex07Accumulators.java`](examples/Ex07Accumulators.java).

## `break` and `continue`

- **`break`** leaves the loop **immediately**, skipping the rest of the
  body and any remaining iterations.
- **`continue`** skips the rest of **this** iteration, and goes straight
  on to the next one.

```java
for (int n = 1001; n < 2000; n++) {
    if (n % 17 == 0 && n % 23 == 0) {
        found = n;
        break;          // we have our answer: stop looking
    }
}
```

A common shape is `while (true) { … if (done) { break; } … }`, a loop
whose exit is in the middle. Use these sparingly: a loop with one clear
condition is usually easier to read. See
[`examples/Ex08BreakContinue.java`](examples/Ex08BreakContinue.java).

## Nested loops

A loop can go inside another loop. **The inner loop runs completely,
every time the outer loop goes round once.** That's how you work with
anything that has rows and columns:

```java
for (int row = 1; row <= 6; row++) {
    for (int col = 1; col <= 6; col++) {
        System.out.printf("%4d", row * col);
    }
    System.out.println();          // end of the row
}
```

With 6 rows and 6 columns, the inner body runs 6 × 6 = 36 times. The
inner loop can even depend on the outer one: in a triangle of stars,
row `r` has `r` stars. See
[`examples/Ex09NestedLoops.java`](examples/Ex09NestedLoops.java).

## Random numbers

Games, simulations and tests all need randomness. Java's `Random` class
(import `java.util.Random`) provides it:

```java
Random random = new Random();         // create one, and reuse it
int dice = random.nextInt(6) + 1;     // 1 to 6
boolean heads = random.nextBoolean(); // true or false
double chance = random.nextDouble();  // 0.0 up to (not including) 1.0
```

`random.nextInt(n)` gives a whole number from `0` up to `n - 1`. For any
range from `low` to `high` (both included), use
`random.nextInt(high - low + 1) + low`. `Math.random()` also gives a
double from 0.0 to just under 1.0, if you'd rather not create a
`Random`.

If you give `Random` a number when you create it, like `new Random(42)`,
it produces the **same** "random" sequence every run. That's called a
**seed**, and it's very useful for testing. See
[`examples/Ex10Random.java`](examples/Ex10Random.java), then play
[`examples/Ex11ComputerGuesses.java`](examples/Ex11ComputerGuesses.java):
think of a number from 1 to 100, and the computer finds it in at most
7 guesses by always guessing the middle of what's left. You'll see why
that works so well in Module 16.

## Loops and drawing

Loops and the drawing tools from Module 1 make a great team.
[`examples/Ex12DrawingWithLoops.java`](examples/Ex12DrawingWithLoops.java)
draws concentric circles, a set of fading squares, and a checkerboard
(nested loops!), each from a few lines of code.

Now go back to the Module 1 spiral hook. You can read the whole of its
`paintComponent` method: a `for` loop runs 180 times, each line is
`step * 2` pixels long (so they get longer and longer), the colour
cycles through six colours using `step % 6`, and the heading turns by
59° each time. `Math.cos` and `Math.sin` turn "move this far in this
direction" into how far to move in `x` and `y`. The only piece you
haven't met is `Color[] colours = {…}`, a list of values called an
**array**. That's next.

## Common beginner mistakes

- **An infinite loop**: the condition never becomes false, because
  nothing in the body changes it. Press **Ctrl+C** to stop it, then
  check the variable in the condition.
- **Off-by-one errors**: `i <= 10` versus `i < 10`, or starting at 1
  versus 0. Ask: "what's the first value, what's the last value, and how
  many times does it run?"
- **A semicolon after the loop line.** `for (int i = 0; i < 5; i++);`
  loops five times doing *nothing*, then runs the "body" once.
  `while (x > 0);` is even worse: an infinite loop that does nothing.
- **Resetting an accumulator inside the loop.** `int total = 0;` must go
  *before* the loop, or it's reset to 0 every iteration.
- **Using the loop variable after the loop.** It only exists inside.
- **Creating a new `Random` (or `Scanner`) inside a loop.** Create one
  before the loop and reuse it.
- **Forgetting to read again** at the end of a sentinel loop, so it
  checks the same old value forever.

## Try it yourself

1. Run every file in [`examples/`](examples/). In `Ex01WhileCountdown`,
   delete `count--`, run it, then stop it with Ctrl+C.
2. Complete [`exercises/Exercise1.java`](exercises/Exercise1.java) and
   [`exercises/Exercise2.java`](exercises/Exercise2.java).
3. Build the [module project](project/README.md): a games arcade.
4. Commit and push your work:

   ```bash
   git add .
   git commit -m "Complete Module 5: loops"
   git push
   ```

Next: **[Module 6: Arrays](../06-arrays/README.md)**.
