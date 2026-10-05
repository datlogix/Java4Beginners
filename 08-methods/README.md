# Module 8: Methods

## Hook: one method, a whole village

Run [`examples/Ex00Village.java`](examples/Ex00Village.java):

```bash
java Ex00Village.java
```

A window opens showing a village: nine houses of different sizes and
colours, a row of trees, grass and a sun. Now look at the code. The
instructions for drawing a house (walls, roof, door, window) are
written **once**, in a block called `drawHouse`:

```java
static void drawHouse(Graphics2D pen, int x, int y, int size, Color wall) {
    pen.setColor(wall);
    pen.fillRect(x, y - size, size, size);       // walls
    ...
}
```

Then the village is drawn by **calling** it nine times, with a different
position, size and colour each time:

```java
drawHouse(pen, 20 + i * 85, 330 + random.nextInt(60), size, walls[i % walls.length]);
```

Change the loop to draw 20 houses. Change the roof colour inside
`drawHouse`, and *every* house changes at once. That's the power of a
**method**: a named, reusable piece of code that you write once and use
as often as you like. You've been *using* methods since Module 1
(`println`, `nextLine`, `Math.sqrt`). This module is about *writing your
own*, which is how every large program is built.

## Why methods?

Look at [`examples/Ex01WhyMethods.java`](examples/Ex01WhyMethods.java).
It prints the same kind of banner several times. Without a method, that
means copying and pasting three lines for each banner. With a method,
you write those lines once and give them a name:

```java
static void printBanner(String title) {
    System.out.println("+" + "-".repeat(title.length() + 4) + "+");
    System.out.println("|  " + title + "  |");
    System.out.println("+" + "-".repeat(title.length() + 4) + "+");
}
```

Then `printBanner("Results");` prints a banner. Methods give you:

- **No repetition.** Fix a bug once, and it's fixed everywhere.
- **Names.** `printBanner("Report")` says what it does. Three lines of
  `println` and `repeat` make the reader work it out.
- **Smaller pieces.** A 300-line `main` is impossible to hold in your
  head. Twenty 15-line methods, each doing one job, are not.
- **Testing.** You can check a small method on its own, with chosen
  inputs, before you trust it inside a bigger program.

## Defining and calling a method

```java
public class Ex02DefiningAndCalling {

    static void sayHello() {
        System.out.println("Hello!");
    }

    public static void main(String[] args) {
        sayHello();          // call it
        sayHello();          // and again
    }
}
```

- A method is defined **inside the class**, but **outside** every other
  method. Its order in the class doesn't matter: `main` can call a
  method written below it.
- `static` means the method belongs to the class itself. (Every method
  in Part 1 is `static`. Module 10 shows what non-static methods are
  for.)
- `void` means the method doesn't give back a value. It just does
  something.
- `sayHello` is its name: camelCase, usually a **verb**, saying what it
  does.
- The brackets `()` hold its **parameters** (none here), and the braces
  hold its **body**.

Defining a method does nothing by itself. Its code only runs when the
method is **called**. When Java reaches a call, it jumps into the
method, runs the whole body, then **comes back** to exactly where it
left off. Run [`examples/Ex02DefiningAndCalling.java`](examples/Ex02DefiningAndCalling.java)
and follow its output: `main` calls `countToThree`, which calls
`sayHello`, and each one returns to its caller in turn. Java keeps
track of who called whom in the **call stack**. That's what the `at`
lines in a stack trace are: the chain of calls that led to the crash,
most recent first.

## Parameters: handing values to a method

```java
static void greet(String name) {
    System.out.println("Akwaaba, " + name + "!");
}

greet("Afua");        // Akwaaba, Afua!
greet("Kwabena");     // Akwaaba, Kwabena!
```

`name` is a **parameter**: a variable that belongs to the method, filled
in each time the method is called. `"Afua"` is the **argument**: the
actual value given in the call. A method can have several parameters,
of any types, separated by commas, and each needs its own type:

```java
static void printReceiptLine(String item, int quantity, double price) { ... }

printReceiptLine("Breadboard", 2, 25.0);
```

The arguments must match the parameters in **number, order and type**.
`printReceiptLine(2, "Breadboard", 25.0)` doesn't compile. An argument
can be anything that gives the right type: a literal, a variable, a
calculation, even another method call. See
[`examples/Ex03Parameters.java`](examples/Ex03Parameters.java).

## Return values: getting an answer back

Many methods calculate an answer. `return` sends it back to the caller:

```java
static double celsiusToFahrenheit(double celsius) {
    return celsius * 9 / 5 + 32;
}

double f = celsiusToFahrenheit(37);    // f is 98.6
```

The type before the method's name (`double` here, `void` before) is its
**return type**: the type of value it gives back. Think of the call as
being **replaced by its result**, so you can use it anywhere a value of
that type fits: in a variable, in a calculation, in a `println`, or in
an `if`:

```java
static boolean isPrime(int n) {
    if (n < 2) {
        return false;
    }
    for (int d = 2; d * d <= n; d++) {
        if (n % d == 0) {
            return false;
        }
    }
    return true;
}

if (isPrime(97)) { ... }
```

`return` **ends the method immediately**, even in the middle of a loop.
That's why `isPrime` can stop as soon as it finds a divisor. A method
with a return type must return a value on **every** path through it.
Miss one and the compiler says `missing return statement`.

Methods that answer yes/no questions return `boolean`, and read best
with names like `isPrime`, `hasPaid` or `canBorrow`. See
[`examples/Ex04ReturnValues.java`](examples/Ex04ReturnValues.java).

> **Print or return?** A method that *works something out* should
> usually **return** it, and leave printing to whoever called it. That
> way the caller decides what to do with the answer: print it, store it,
> compare it, or pass it to another method. `celsiusToFahrenheit` that
> prints instead of returning is useless for a temperature table that
> needs `printf` columns.

## Overloading: same name, different parameters

Two methods can share a name, as long as their parameter lists differ:

```java
static double area(double radius) { return Math.PI * radius * radius; }
static double area(double width, double height) { return width * height; }

area(2)       // calls the first one
area(3, 4)    // calls the second one
```

Java chooses which one to run from the arguments. You've relied on this
all along: `println` has separate versions for `String`, `int`,
`double`, `char` and `boolean`. See
[`examples/Ex05Overloading.java`](examples/Ex05Overloading.java).

## Scope: where variables live

A variable exists only inside the braces where it was created. That's
its **scope**:

```java
static double withVat(double price) {
    double vat = price * VAT_RATE;     // vat only exists inside withVat
    return price + vat;
}
```

- Variables created inside a method (including its parameters) are
  **local** to that method. Other methods can't see them, and they
  disappear when the method returns.
- Two methods can each have a local variable with the same name. They're
  completely separate boxes.
- A **constant** declared in the class, outside every method, as
  `static final double VAT_RATE = 0.15;`, is visible to every method in
  the class. That's the right place for values the whole program shares.

See [`examples/Ex06Scope.java`](examples/Ex06Scope.java).

## Java passes copies

When you call a method, each parameter gets a **copy** of the argument's
value. So a method can't change the caller's variables:

```java
static void tryToChange(int number) {
    number = 999;              // changes the local copy only
}

int number = 5;
tryToChange(number);
System.out.println(number);    // still 5
```

But remember Module 6: an array variable holds a **reference**. The
method gets a copy of the *reference*, which points at the **same
array**. So a method *can* change the contents of an array (or list)
you pass it:

```java
static void doubleAll(int[] values) {
    for (int i = 0; i < values.length; i++) {
        values[i] *= 2;        // changes the caller's array
    }
}
```

Assigning a whole new array to the parameter, though, only changes the
local copy of the reference. See
[`examples/Ex07PassByValue.java`](examples/Ex07PassByValue.java). If a
method needs to give back new data, the clearest way is to **return**
it, as in [`examples/Ex08ArraysAndListsInMethods.java`](examples/Ex08ArraysAndListsInMethods.java).

## Documenting and checking methods

A **Javadoc** comment, `/** … */`, written just above a method, describes
what it does, its parameters (`@param`) and its result (`@return`):

```java
/**
 * Works out the body mass index (BMI) from weight and height.
 *
 * @param weightKg the person's weight in kilograms
 * @param heightM the person's height in metres
 * @return the BMI, which is weight divided by height squared
 */
static double bmi(double weightKg, double heightM) {
    return weightKg / (heightM * heightM);
}
```

Hover over a call to `bmi` in VS Code and this comment pops up as help.
It's also how the official Java documentation at
[docs.oracle.com](https://docs.oracle.com/en/java/javase/21/docs/api/)
is generated.

Because a method takes inputs and returns a result, you can **check** it
on its own. [`examples/Ex09JavadocAndChecks.java`](examples/Ex09JavadocAndChecks.java)
has a small `check` method that prints PASS or FAIL for chosen inputs,
including the **boundaries** (is a BMI of exactly 18.5 "underweight" or
"healthy"?), which is where bugs love to hide. Module 17 turns this idea
into professional automated testing with JUnit.

## Designing with methods

When a program has a clear structure, `main` reads like a **table of
contents**:

```java
public static void main(String[] args) {
    printWelcome();
    int count = askInt("How many temperatures? ", 1, 10);
    double[] temps = readTemperatures(count);
    printReport(temps);
}
```

Each method does **one** job, its name says what that job is, and it's
short enough to see all at once. A good rule of thumb: if you can't
describe a method without using the word "and", it should probably be
two methods. See [`examples/Ex10TableOfContents.java`](examples/Ex10TableOfContents.java).

Now re-read the hook. `drawHouse` and `drawTree` take the drawing pen
and the position as parameters, and `draw` calls them in loops. The
`/** … */` above each one is a Javadoc comment. You can explain every
line.

## Common beginner mistakes

- **Defining a method inside another method** (such as inside `main`).
  Methods go side by side inside the class.
- **Forgetting the brackets in a call**: `sayHello;` doesn't compile.
  It's `sayHello();`.
- **Forgetting to use the return value.** `celsiusToFahrenheit(20);` on
  its own line calculates the answer and throws it away.
- **`missing return statement`**: some path through a non-`void`
  method doesn't return. Check every `if` branch.
- **Printing inside a method that should return.** Return the answer
  and let the caller print it.
- **Expecting a method to change an `int` you passed it.** It gets a
  copy. Return the new value instead: `score = addBonus(score);`.
- **Arguments in the wrong order** when two parameters have the same
  type, like `(width, height)`. The compiler can't catch this one. Good
  parameter names and Javadoc help.
- **Giant methods.** If a method doesn't fit on the screen, split it.

## Try it yourself

1. Run every file in [`examples/`](examples/). In the hook, write a
   `drawCar` or `drawCloud` method and add some to the village.
2. Complete [`exercises/Exercise1.java`](exercises/Exercise1.java) and
   [`exercises/Exercise2.java`](exercises/Exercise2.java).
3. Build the [module project](project/README.md): Tic-Tac-Toe,
   restructured into methods, with a computer opponent.
4. Commit and push your work:

   ```bash
   git add .
   git commit -m "Complete Module 8: methods"
   git push
   ```

Next: **[Module 9: Capstone 1, Text Adventure](../09-capstone-text-adventure/README.md)**.
