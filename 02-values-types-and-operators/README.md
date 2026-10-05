# Module 2: Values, Types & Operators

## Hook: six lines of arithmetic that lie to you (or do they?)

Before you run anything, copy these six lines onto paper and write down
what you think each one prints:

```java
System.out.println(7 / 2);
System.out.println(0.1 + 0.2);
System.out.println(2147483647 + 1);
System.out.println('A' + 1);
System.out.println(1 + 2 + "3");
System.out.println("1" + 2 + 3);
```

Now run [`examples/Ex00MathSurprises.java`](examples/Ex00MathSurprises.java):

```bash
java Ex00MathSurprises.java
```

```
3
0.30000000000000004
-2147483648
66
33
123
```

Seven divided by two is three? Adding one to a positive number gives a
huge *negative* number? A letter plus one is 66? None of these are bugs
in Java. Every one follows a precise rule, and by the end of this
module you'll be able to explain all six, and predict lines like them
before you run them. Programmers who can't are the ones who ship
banking software that loses pesewas.

## Every value has a type

A **value** is a single piece of data: `42`, `3.75`, `true`, `'J'`,
`"Java"`. Every value in Java has a **type**, which decides what the
value is allowed to be and what you can do with it.

| Type | What it holds | Examples |
|---|---|---|
| `int` | Whole numbers, from about −2.1 billion to +2.1 billion | `42`, `-7`, `0`, `1_000_000` |
| `long` | Much bigger whole numbers (end the literal with `L`) | `8_000_000_000L` |
| `double` | Numbers with a decimal point | `3.75`, `-0.5`, `2.0`, `6.02e23` |
| `boolean` | Exactly two values: `true` or `false` | `true`, `false` |
| `char` | **One** character, in **single** quotes | `'J'`, `'7'`, `'?'`, `' '` |
| `String` | Text: zero or more characters, in **double** quotes | `"Java"`, `"Room 101"`, `""` |

The first five are Java's **primitive types**: simple, built into the
language, written in lower case. `String` is different (notice its
capital S): it's a **class**, a more complex type with lots of built-in
abilities. Module 3 explores strings properly. (Java has three more
primitives you'll rarely need: `byte`, `short` and `float`.)

The underscores in `1_000_000` are only there to help humans read the
number; Java ignores them.

### Storing a value: a first look at variables

To keep a value and use it later, you put it in a **variable**: a named
box that holds one value of one type. In Java you must say the type when
you create the variable:

```java
int age = 17;
double temperature = 36.6;
boolean isRaining = false;
char grade = 'A';
String name = "Ama";

System.out.println(name + " is " + age);    // Ama is 17
```

Read `int age = 17;` as "make a box for an `int`, call it `age`, and put
17 in it." Once a box has a type, it can only ever hold that type: you
can't put text in an `int` box. Module 3 covers variables in depth. For
now, it's enough to know that the type comes first. See
[`examples/Ex01Types.java`](examples/Ex01Types.java).

## Arithmetic

| Operator | Meaning | Example | Result |
|---|---|---|---|
| `+` | add | `7 + 2` | `9` |
| `-` | subtract | `7 - 2` | `5` |
| `*` | multiply | `7 * 2` | `14` |
| `/` | divide | `7 / 2` | `3` (!) |
| `/` | divide | `7.0 / 2` | `3.5` |
| `%` | remainder | `7 % 2` | `1` |

### Integer division: the first hook surprise explained

When **both** sides of `/` are whole numbers (`int` or `long`), Java
does **integer division**: it works out how many *whole* times one
number goes into the other and **throws the decimal part away**. It
doesn't round: `7 / 2` is `3`, and `99 / 100` is `0`.

If **either** side is a `double`, you get ordinary division with a
decimal answer: `7.0 / 2`, `7 / 2.0` and `7.0 / 2.0` are all `3.5`.

This catches everyone. Working out an average of whole-number marks?

```java
System.out.println((70 + 85 + 90) / 3);     // 81, the .666... is gone
System.out.println((70 + 85 + 90) / 3.0);   // 81.66666666666667
```

### `%`: the remainder

`%` (called **modulo** or **mod**) gives the **remainder** after integer
division. Together, `/` and `%` split a number into "how many whole
groups" and "how many left over", which turns out to be useful all the
time:

```java
System.out.println(200 / 60);       // 3   whole hours in 200 minutes
System.out.println(200 % 60);       // 20  minutes left over
System.out.println(4071 % 10);      // 1   the last digit of a number
System.out.println(17 % 2);         // 1   odd numbers leave 1, even numbers leave 0
```

See [`examples/Ex02Arithmetic.java`](examples/Ex02Arithmetic.java) and
[`examples/Ex03DivisionAndRemainder.java`](examples/Ex03DivisionAndRemainder.java).

### Order of operations

Java follows the same rules as school maths: `*`, `/` and `%` happen
before `+` and `-`, and operators on the same level go **left to
right**. Brackets always win:

```java
2 + 3 * 4        // 14, not 20
(2 + 3) * 4      // 20
20 / 2 * 5       // 50: left to right, so (20 / 2) * 5
```

If you have to stop and think about the order, add brackets. They cost
nothing and make your intention obvious. See
[`examples/Ex04Precedence.java`](examples/Ex04Precedence.java).

## Doubles are (nearly always) slightly wrong

The second hook surprise: `0.1 + 0.2` prints `0.30000000000000004`.

Computers store a `double` in **binary** (base 2). Just as one third
can't be written exactly in decimal (`0.3333…` goes on forever), most
decimal fractions, including `0.1`, can't be written exactly in binary.
Java stores the nearest value it can, which is a tiny bit off, and
occasionally the tiny errors show up when you print the answer. Every
programming language that uses standard decimals behaves exactly the
same way.

Three practical rules:

1. **Round when you display** a double. `Math.round(x)` gives the
   nearest whole number. To keep two decimal places, multiply by 100,
   round, then divide by `100.0`:

   ```java
   System.out.println(Math.round(2.4567 * 100) / 100.0);   // 2.46
   ```

   Module 3 shows a neater way to format numbers.
2. **Never compare doubles with `==`**. `0.1 + 0.2 == 0.3` is `false`.
3. **For money, count the smallest unit as an `int`**: store 1050
   pesewas, not 10.50 cedis. Whole numbers are always exact.

See [`examples/Ex05DoubleSurprises.java`](examples/Ex05DoubleSurprises.java).

## Overflow: when an `int` runs out of room

The third surprise: `2147483647 + 1` gives `-2147483648`.

An `int` is stored in exactly 32 binary digits, so it has a **largest
possible value**: `2147483647`, available as `Integer.MAX_VALUE`. Go
one past it, and the number **wraps around** to the most negative
value, like a car's mileage counter rolling over from 99999 to 00000.
This is called **overflow**, and Java doesn't warn you.

```java
System.out.println(Integer.MAX_VALUE + 1);          // -2147483648
System.out.println((long) Integer.MAX_VALUE + 1);   // 2147483648
```

If numbers could get bigger than about two billion (populations, bank
balances in pesewas, milliseconds since 1970), use `long`. Its limit is
about 9.2 quintillion (9,200,000,000,000,000,000).

## Text, characters, and what `+` really does

When at least one side of `+` is a `String`, `+` **joins**
(concatenates) instead of adding. Any number on the other side is turned
into text first:

```java
"Java" + "Script"     // "JavaScript"
"Room " + 101         // "Room 101"
```

And because Java works **left to right**, *when* the String appears
matters. That explains the last two hook lines:

```java
1 + 2 + "3"      // 1 + 2 is 3 (both ints), then 3 + "3" joins: "33"
"1" + 2 + 3      // "1" + 2 joins to "12", then "12" + 3 joins: "123"
"1" + (2 + 3)    // brackets first: "1" + 5 is "15"
```

A `char` is a single character in **single** quotes. Behind the scenes,
each character is stored as a number: its position in the **Unicode**
table, which lists every character in every writing system. `'A'` is
65, `'B'` is 66, `'a'` is 97. So the fourth hook surprise, `'A' + 1`,
is `65 + 1`, which is the `int` 66. To get a character back, **cast**
it: `(char) ('A' + 1)` is `'B'`.

Strings also have useful abilities, called with a dot:

```java
"=".repeat(30)          // "=============================="
"Ghana".length()        // 5
```

See [`examples/Ex06StringsAndChars.java`](examples/Ex06StringsAndChars.java).

## Comparisons give booleans

A **comparison** asks a yes/no question, and the answer is a `boolean`:

| Operator | Means | Example | Result |
|---|---|---|---|
| `==` | equal to | `5 == 5` | `true` |
| `!=` | not equal to | `5 != 5` | `false` |
| `<` / `>` | less than / greater than | `3 < 5` | `true` |
| `<=` / `>=` | less than or equal / greater than or equal | `5 >= 5` | `true` |

Note the **double** equals sign: `==` asks "are these equal?", while a
single `=` *stores* a value in a variable. You can combine booleans with
`&&` (and), `||` (or) and `!` (not):

```java
int age = 17;
System.out.println(age >= 13 && age <= 19);   // true: a teenager
```

Strings are the exception: compare them with `.equals()`, as in
`"java".equals("java")`, not with `==`. Module 4 explains why, and uses
all of these to make decisions. See
[`examples/Ex07Comparisons.java`](examples/Ex07Comparisons.java).

## Converting between types

Java is **strict** about types. It will automatically convert a value to
a type that can hold it *safely* (**widening**), such as `int` to
`double`. But it refuses to do anything that might lose information
unless you ask:

```java
double d = 7;              // fine: 7 becomes 7.0
int i = 36.9;              // ERROR: possible lossy conversion from double to int
int i = (int) 36.9;        // fine, but i is 36: casting CHOPS, it doesn't round
```

Writing a type in brackets before a value, like `(int)` or `(double)`,
is called a **cast**. It's you telling Java "I know, convert it
anyway." A common use is getting a decimal answer from int division:

```java
int total = 7, count = 2;
System.out.println((double) total / count);   // 3.5
```

To turn **text into a number** (you'll need this for user input), use
`Integer.parseInt` or `Double.parseDouble`. To turn anything into text,
use `String.valueOf`, or just join it to a String:

```java
int n = Integer.parseInt("42");            // the number 42
double p = Double.parseDouble("19.99");    // the number 19.99
String s = String.valueOf(42);             // the text "42"
```

`Integer.parseInt("forty-two")` crashes with a `NumberFormatException`.
Module 13 shows how to handle that gracefully. See
[`examples/Ex08Casting.java`](examples/Ex08Casting.java).

## The `Math` class

Java comes with a `Math` class full of ready-made maths methods:

| Method | Gives | Example | Result |
|---|---|---|---|
| `Math.sqrt(x)` | square root | `Math.sqrt(144)` | `12.0` |
| `Math.pow(a, b)` | a to the power b | `Math.pow(2, 10)` | `1024.0` |
| `Math.abs(x)` | distance from zero | `Math.abs(-7)` | `7` |
| `Math.max(a, b)` / `Math.min(a, b)` | the larger / smaller | `Math.max(4, 9)` | `9` |
| `Math.round(x)` | the nearest whole number | `Math.round(2.5)` | `3` |
| `Math.floor(x)` / `Math.ceil(x)` | round down / up | `Math.ceil(2.1)` | `3.0` |
| `Math.PI` | π | `Math.PI` | `3.141592653589793` |

Java has no `^` "power" operator (`^` means something else entirely),
so use `Math.pow`, or just multiply: `r * r`. See
[`examples/Ex09MathClass.java`](examples/Ex09MathClass.java). Now go back
to the Module 1 hook: `Math.cos` and `Math.sin` turned the spiral's
heading into how far to move in `x` and `y`.

## Common beginner mistakes

- **Integer division by accident.** `5 / 9 * (f - 32)` is always `0`,
  because `5 / 9` is `0`. Write `5.0 / 9`.
- **Expecting a cast to round.** `(int) 2.99` is `2`. Use
  `Math.round(2.99)` to get `3`.
- **Joining when you meant to add.** `"Total: " + 2 + 3` is
  `"Total: 23"`. Add brackets: `"Total: " + (2 + 3)`.
- **Single quotes around text.** `'Hello'` is an error: single quotes
  are for one `char`. Text needs double quotes.
- **`=` versus `==`.** `=` stores, `==` compares.
- **Comparing doubles with `==`**, or printing them without rounding.
- **Writing `^` for "to the power of".** Use `Math.pow(a, b)`.
- **`string` instead of `String`.** Primitive types are lower case
  (`int`, `double`); `String` is a class and starts with a capital.

## Try it yourself

1. Run every file in [`examples/`](examples/). Before each line runs,
   predict its output. Keep a tally of how many you get right.
2. Explain all six hook lines, in one sentence each, to someone else
   (or in a comment in your own copy of the hook).
3. Complete [`exercises/Exercise1.java`](exercises/Exercise1.java) and
   [`exercises/Exercise2.java`](exercises/Exercise2.java).
4. Build the [module project](project/README.md): a conversion cheat
   sheet.
5. Commit and push your work:

   ```bash
   git add .
   git commit -m "Complete Module 2: values, types and operators"
   git push
   ```

Next: **[Module 3: Variables, Input & Strings](../03-variables-input-and-strings/README.md)**.
