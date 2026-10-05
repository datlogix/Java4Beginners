# Module 4: Making Decisions

## Hook: the birthday detective

Run [`examples/Ex00BirthdayDetective.java`](examples/Ex00BirthdayDetective.java)
and type in your date of birth:

```bash
java Ex00BirthdayDetective.java
```

```
Day you were born (1-31): 15
Month (1-12): 3
Year: 2008

You were born on a SATURDAY.
Your Akan day name would be Kwame or Ama.
A weekend baby: your parents didn't have to take a day off work!
2008 was a leap year, with 366 days.
```

Check it against your real Akan day name (or a family member's). Then
try the birthday `29`, `2`, `2000`. The program says something extra
that it didn't say before. Every program you've written so far ran the
same lines every time, from top to bottom. This one **chooses**: it
looks at the data and decides which lines to run. By the end of this
module you'll be able to write every decision in it, including the
leap-year rule that trips up professional programmers every four years.

## `if`: run some code only when a condition is true

```java
if (temperature > 28) {
    System.out.println("It's hot. Drink plenty of water.");
}
System.out.println("This line always runs.");
```

- The **condition** goes in brackets after `if`. It must be a `boolean`:
  something that is either `true` or `false`, usually a comparison from
  Module 2 (`>`, `<`, `>=`, `<=`, `==`, `!=`).
- The **block** in braces `{ … }` runs only when the condition is
  `true`. When it's `false`, Java skips straight past the closing brace.
- There's **no semicolon** after `if (…)`. (See the mistakes section for
  what happens if you add one.)

Indent everything inside the braces by four spaces, so you can see at a
glance which lines belong to the `if`. Java doesn't need the
indentation, but your readers do. See
[`examples/Ex01If.java`](examples/Ex01If.java).

## `else`: one path or the other

```java
if (number % 2 == 0) {
    System.out.println(number + " is even.");
} else {
    System.out.println(number + " is odd.");
}
```

Exactly one of the two blocks runs, never both, never neither. See
[`examples/Ex02IfElse.java`](examples/Ex02IfElse.java).

## `else if`: choosing between many paths

```java
String grade;
if (mark >= 80) {
    grade = "A";
} else if (mark >= 70) {
    grade = "B";
} else if (mark >= 60) {
    grade = "C";
} else if (mark >= 50) {
    grade = "D";
} else {
    grade = "F";
}
```

Java checks the conditions **in order, top to bottom**, runs the block
of the **first** one that's true, and skips the rest. So when Java
reaches `mark >= 70`, it already knows the mark is below 80. That's why
you don't need to write `mark >= 70 && mark < 80`.

This means **order matters**. Put `mark >= 50` first, and a mark of 95
gets a D, because 95 is indeed ≥ 50, and Java never looks any further.
See [`examples/Ex03ElseIfChain.java`](examples/Ex03ElseIfChain.java),
which includes the broken version.

The final `else` (with no condition) catches everything that's left.
It's optional, but it's usually a good idea: it's where unexpected
values end up.

## Combining conditions: `&&`, `||` and `!`

| Operator | Name | True when… | Example |
|---|---|---|---|
| `&&` | and | **both** sides are true | `hr >= 60 && hr <= 100` |
| `\|\|` | or | **at least one** side is true | `hr < 60 \|\| hr > 100` |
| `!` | not | the condition is false | `!isNormal` |

```java
if (heartRate >= 60 && heartRate <= 100) {
    System.out.println("Normal resting heart rate.");
}
```

In maths you might write `60 ≤ hr ≤ 100`, but Java can't chain
comparisons like that: `60 <= hr <= 100` doesn't compile. Write it as two
comparisons joined by `&&`.

Java is lazy in a useful way: `&&` and `||` stop as soon as they know
the answer. In `count != 0 && total / count > 50`, if `count` is 0 the
left side is false, so the whole thing must be false, and Java never
attempts the division that would crash. This is called
**short-circuiting**. See
[`examples/Ex04LogicalOperators.java`](examples/Ex04LogicalOperators.java).

## Nested decisions

An `if` can go inside another `if`. The inner decision only happens if
the outer one let you in:

```java
if (ticket.equalsIgnoreCase("yes")) {
    if (age >= 16) {
        System.out.println("Enjoy the film!");
    } else {
        System.out.println("Sorry, this film is for ages 16 and over.");
    }
} else {
    System.out.println("Please buy a ticket first.");
}
```

Each `else` belongs to the nearest `if` above it in the same pair of
braces. Good indentation makes this obvious; bad indentation makes it
a trap. See [`examples/Ex05NestedIf.java`](examples/Ex05NestedIf.java).

## Comparing strings: `.equals()`, never `==`

Run [`examples/Ex06StringEquality.java`](examples/Ex06StringEquality.java)
and type `yes`:

```java
String answer = in.nextLine();     // you type: yes
if (answer == "yes") { ... }       // FALSE!
if (answer.equals("yes")) { ... }  // true
```

Why? A `String` isn't a primitive like `int`. A String variable holds a
**reference**: the address of where the text is stored in memory.
`==` compares the *addresses*, asking "are these the very same object?"
The text you typed and the `"yes"` written in your code are stored in
two different places, so `==` says no, even though the characters are
identical. `.equals()` compares the **characters** themselves, which is
what you almost always want. (Module 10 explains references properly.)

To make matters more confusing, `==` *sometimes* seems to work with
strings that are both typed into your code, because Java reuses
identical string literals. Don't let that fool you. **Always** compare
strings with `.equals()`, or `.equalsIgnoreCase()` to accept `YES`,
`Yes` and `yes` alike.

## `switch`: choosing by value

When you're comparing **one value** against a list of possibilities, a
`switch` is often clearer than a long `else if` chain. Java has two
forms.

### The classic `switch` statement

```java
switch (colour) {
    case "red":
        System.out.println("Stop.");
        break;
    case "amber":
        System.out.println("Get ready to stop.");
        break;
    default:
        System.out.println("That's not a traffic light colour.");
}
```

Java jumps to the matching `case` and runs from there **until it hits a
`break`**. Forget the `break`, and it **falls through** into the next
case's code too. That's occasionally useful (several cases sharing one
block), but far more often it's a bug. `default` catches everything
else. See [`examples/Ex07Switch.java`](examples/Ex07Switch.java).

### The modern arrow `switch`

Since Java 14 there's a neater form, using `->`:

```java
switch (month) {
    case 12, 1, 2 -> System.out.println("Harmattan season.");
    case 4, 5, 6, 7 -> System.out.println("Major rainy season (in the south).");
    default -> System.out.println("Some other part of the year.");
}
```

No `break`, no fall-through, and several values can share a case. Even
better, an arrow switch can be a **switch expression** that works out to
a value you can store:

```java
int days = switch (month) {
    case 4, 6, 9, 11 -> 30;
    case 2 -> 28;
    default -> 31;
};                  // note the semicolon: this whole thing is one statement
```

A switch expression must cover **every** possible value, so it usually
needs a `default`. This course uses the arrow form from now on. You can
switch on `int`, `char` and `String` values (and on enums, in
Module 12), but not on `double` or `boolean`. See
[`examples/Ex08SwitchExpressions.java`](examples/Ex08SwitchExpressions.java).

## The ternary operator: a tiny if-else that gives a value

```java
int daysInFebruary = leap ? 29 : 28;
```

Read it as "is `leap` true? If so, 29, otherwise 28." The pattern is
`condition ? valueIfTrue : valueIfFalse`. It's handy for short,
simple choices. For anything longer, an `if` is clearer.

[`examples/Ex09LeapYear.java`](examples/Ex09LeapYear.java) writes the
leap-year rule three ways: as an `else if` chain, as one boolean
expression, and with a ternary. The rule: a year is a leap year if it
divides by 4, **except** century years, which must also divide by 400.
So 2024 and 2000 were leap years, but 1900 wasn't, and 2100 won't be:

```java
boolean leap = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
```

Now re-read the hook. You can explain every decision in it. The only
new thing is `LocalDate`, a class from `java.time` that knows the
calendar: `LocalDate.of(year, month, day).getDayOfWeek()` works out
the day of the week for any date.

## Common beginner mistakes

- **A semicolon after the condition.** `if (age >= 18);` ends the `if`
  right there, with an empty statement. The block after it then runs
  *every time*. No error, just a very confusing bug.
- **`=` instead of `==`.** `if (x = 5)` is a compile error in Java
  ("incompatible types: int cannot be converted to boolean"). At least
  Java catches this one.
- **Comparing strings with `==`.** Use `.equals()` or
  `.equalsIgnoreCase()`.
- **Chaining comparisons.** `0 <= mark <= 100` doesn't compile. Write
  `mark >= 0 && mark <= 100`.
- **The wrong order in an `else if` chain**, so a later case can never
  be reached.
- **Missing `break` in a classic `switch`**, so one case falls through
  into the next. The arrow form avoids this.
- **Leaving out the braces.** Java allows `if (x > 0) doSomething();`
  without braces, but only the *one* statement after it belongs to the
  `if`. Add a second indented line and it runs every time. Always use
  braces.
- **Forgetting the user might type capitals or spaces.** Use `trim()`
  and `equalsIgnoreCase()` (or `toLowerCase()`) on answers.

## Try it yourself

1. Run every file in [`examples/`](examples/) more than once, with input
   that sends it down **every** branch.
2. Complete [`exercises/Exercise1.java`](exercises/Exercise1.java) and
   [`exercises/Exercise2.java`](exercises/Exercise2.java).
3. Build the [module project](project/README.md): a choose-your-own
   adventure story.
4. Commit and push your work:

   ```bash
   git add .
   git commit -m "Complete Module 4: making decisions"
   git push
   ```

Next: **[Module 5: Loops](../05-loops/README.md)**.
