# Module 6: Arrays

## Hook: a picture made of numbers

Run [`examples/Ex00PixelArt.java`](examples/Ex00PixelArt.java):

```bash
java Ex00PixelArt.java
```

A window opens with a green space invader, and the same invader is
printed in the terminal in `#` characters. Now open the file and look at
the picture's source:

```java
int[][] grid = {
    {0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0},
    {0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0},
    {0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0},
    {0, 1, 1, 2, 1, 1, 1, 2, 1, 1, 0},
    ...
```

The whole picture is a **grid of numbers**: 0 is black, 1 is green, 2 is
white. Squint and you can see the invader in the numbers. Change a few
0s to 1s, run it again, and you've edited the picture. Add a `3` and a
fourth colour to the `palette` line, and you've added a colour.

Every photo on your phone works the same way: millions of numbers in a
grid. Until now, each variable held **one** value. This module is about
holding **many values under one name**, which is what made this picture
possible: an **array**.

## Why arrays?

Suppose you want to store the marks of a class of 40 students. With what
you know so far, you'd need 40 variables: `mark1`, `mark2`, … `mark40`.
Working out the average would need a 40-term sum, and adding a 41st
student would mean editing the program. An array solves all of this:

```java
int[] marks = {72, 85, 64, 90, 58};
```

`int[]` (say "int array") is the type: an array of `int`s. The variable
`marks` holds the whole collection, in order. One name, any number of
values, and a loop can visit them all.

## Creating arrays

There are two ways:

```java
// 1. List the values, in braces, when you know them already
int[] marks = {72, 85, 64, 90, 58};
String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri"};

// 2. Say how many values, and fill them in later
int[] scores = new int[40];         // 40 ints
String[] names = new String[3];     // 3 Strings
```

With `new`, Java fills every slot with a **default value**: `0` for
numbers, `false` for booleans, `'\0'` (an invisible "nothing" character)
for chars, and **`null`** for Strings. `null` means "no object at all",
and calling a method on it, like `names[0].length()`, crashes with a
`NullPointerException`.

Two important facts:

- **All the values have the same type.** An `int[]` can't hold a
  `String`.
- **The size is fixed** when the array is created. An array of 5 values
  stays at 5. There's no "add". (Module 7's `ArrayList` can grow.)

`marks.length` gives the size. Note: `length` with **no brackets** for
arrays, but `length()` **with** brackets for Strings. Everyone mixes
these up at first; the compiler will remind you.

Printing an array directly gives something like `[I@5cdd8682`: Java's
code for "an array of ints, stored at this address". To see the values,
use `Arrays.toString(marks)`, after `import java.util.Arrays;`. See
[`examples/Ex01CreatingArrays.java`](examples/Ex01CreatingArrays.java).

## Indexes: reading and changing values

Each value has a position, its **index**, starting at **0**, exactly
like the characters of a String:

```java
String[] planets = {"Mercury", "Venus", "Earth", "Mars"};
//                      0         1        2       3

planets[0]                     // "Mercury"
planets[planets.length - 1]    // "Mars": the last one
planets[3] = "MARS";           // replace a value
```

`array[index]` works on both sides of `=`: on the right it **reads** a
value, on the left it **stores** one. And it works with every operator
you know: `counts[2]++`, `totals[i] += price`.

Ask for an index that doesn't exist and the program crashes:

```
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 4 out of bounds for length 4
	at Ex02IndexingAndChanging.main(Ex02IndexingAndChanging.java:28)
```

The message is very helpful: you asked for index 4, but the array only
has 4 values (indexes 0 to 3). See
[`examples/Ex02IndexingAndChanging.java`](examples/Ex02IndexingAndChanging.java).

## Looping over arrays

Arrays and `for` loops were made for each other. There are two styles:

```java
// The index loop: you get the position i, and can read or change fruits[i]
for (int i = 0; i < fruits.length; i++) {
    System.out.println((i + 1) + ". " + fruits[i]);
}

// The for-each loop: "for each String fruit in fruits"
for (String fruit : fruits) {
    System.out.println(fruit.toUpperCase());
}
```

The **for-each** loop is shorter and impossible to get off-by-one, so
use it when you just need each **value**. Use the **index** loop when
you need the **position**, when you're working with two arrays at once,
or when you want to **change** the values: assigning to `fruit` inside
a for-each loop changes the loop variable, not the array. See
[`examples/Ex03LoopingOverArrays.java`](examples/Ex03LoopingOverArrays.java).

## The algorithms you'll use again and again

Most of what programs do with arrays is built from a few patterns:

```java
// Total (and so the average)
double total = 0;
for (double t : temperatures) {
    total += t;
}
double average = total / temperatures.length;

// The maximum, and WHERE it is: track the index, not just the value
int hottest = 0;
for (int i = 1; i < temperatures.length; i++) {
    if (temperatures[i] > temperatures[hottest]) {
        hottest = i;
    }
}

// Linear search: where is a value? (-1 means "not found")
int position = -1;
for (int i = 0; i < days.length; i++) {
    if (days[i].equals("Fri")) {
        position = i;
        break;
    }
}
```

Counting the values that match a condition is the counter pattern from
Module 5, inside a for-each loop. See
[`examples/Ex04ArrayAlgorithms.java`](examples/Ex04ArrayAlgorithms.java).

## The `Arrays` class

`java.util.Arrays` has ready-made versions of the jobs you'd otherwise
write by hand:

| Method | Does |
|---|---|
| `Arrays.toString(a)` | Gives a readable String like `[3, 7, 19]` |
| `Arrays.sort(a)` | Sorts the array **in place**: numbers smallest first, Strings alphabetically |
| `Arrays.copyOf(a, n)` | Gives a **new** array of length `n` with `a`'s values (padded with defaults if `n` is bigger) |
| `Arrays.fill(a, v)` | Sets every value to `v` |
| `Arrays.equals(a, b)` | Do two arrays hold the same values in the same order? |

`Arrays.sort` changes the original array. If you still need the
original order, sort a copy. See
[`examples/Ex05ArraysClass.java`](examples/Ex05ArraysClass.java).

## Arrays are references: the aliasing gotcha

Run [`examples/Ex06ReferencesAndAliasing.java`](examples/Ex06ReferencesAndAliasing.java):

```java
int[] original = {1, 2, 3};
int[] alias = original;
alias[0] = 99;
System.out.println(Arrays.toString(original));   // [99, 2, 3]  !!
```

Changing `alias` changed `original`. Why? An `int` variable holds a
number directly. But an array variable holds a **reference**: the
address of where the array lives in memory. `alias = original` copies
the *address*, so both names point at the **same** array:

```
original ──┐
           ├──▶ [ 99 | 2 | 3 ]
alias ─────┘
```

This is the same reason `==` on two Strings compares addresses (Module
4), and why `==` on two arrays with the same values is `false`. To get
an independent copy, use `Arrays.copyOf`. To compare values, use
`Arrays.equals`. You'll meet references again with every object you
create in Part 2.

## Parallel arrays

Values at the same index in two arrays can belong together:

```java
String[] students = {"Akua", "Kwesi", "Nana"};
int[] marks = {78, 64, 91};          // Akua got 78, Kwesi 64, Nana 91
```

This works, and [`examples/Ex07ParallelArrays.java`](examples/Ex07ParallelArrays.java)
uses it to print a report. But it's fragile: sort one array and the
names and marks no longer line up. Module 10 shows the proper solution:
a class that keeps a student's name and mark together.

## Splitting text into arrays

`split` cuts a String wherever a separator appears, and gives back an
array of the pieces. It's how you'll read data files in Module 14:

```java
String[] fields = "Kofi,19,Kumasi,1.75".split(",");
String name = fields[0];                      // "Kofi"
int age = Integer.parseInt(fields[1]);        // 19

String[] words = "the quick brown fox".split(" ");
String.join(" - ", words)                     // "the - quick - brown - fox"
```

`split(" ")` gives empty strings when there are several spaces in a row.
`split(" +")` treats any run of spaces as one separator. (The argument
is a **regular expression**, a mini-language for patterns: `+` means "one
or more".) See [`examples/Ex08SplitAndJoin.java`](examples/Ex08SplitAndJoin.java).

## Two-dimensional arrays: grids

An array can hold other arrays. A **2D array** is an array of rows, and
each row is an array of values:

```java
char[][] seats = {
    {'X', '.', '.', 'X', 'X'},
    {'.', '.', '.', '.', '.'},
    {'X', 'X', '.', 'X', '.'},
};

seats[0][3]          // row 0, column 3: 'X'
seats[1][2] = 'X';   // book row 1, seat 2
seats.length         // 3: the number of rows
seats[0].length      // 5: the number of columns in row 0
```

Always **row first, then column**: `grid[row][col]`. To visit every cell,
use nested loops, rows on the outside:

```java
for (int row = 0; row < seats.length; row++) {
    for (int col = 0; col < seats[row].length; col++) {
        System.out.print(seats[row][col] + " ");
    }
    System.out.println();
}
```

`new int[4][4]` creates an empty 4 × 4 grid of zeros. See
[`examples/Ex09Grids2D.java`](examples/Ex09Grids2D.java), then re-read
the hook: `grid[row][col]` picks a number, `palette[…]` turns the
number into a colour, and nested loops draw one square per cell.

[`examples/Ex10CardDealer.java`](examples/Ex10CardDealer.java) puts
the module together: it builds a 52-card deck with nested loops,
shuffles it, and deals two hands.

## `String[] args`, explained at last

Since Module 1, every `main` method has said `String[] args`. Now you
can read it: `args` is an **array of Strings**. It holds any words you
type after the program's name when you run it:

```bash
java Ex11CommandLineArgs.java Ama 3
```

Here `args[0]` is `"Ama"` and `args[1]` is `"3"`. These are called
**command-line arguments**, and they let you give a program its input
without any questions. See
[`examples/Ex11CommandLineArgs.java`](examples/Ex11CommandLineArgs.java).

## Common beginner mistakes

- **Going one past the end**: `for (int i = 0; i <= a.length; i++)`
  crashes on the last iteration. It's `<`, not `<=`.
- **`length` versus `length()`**: arrays use `a.length`; Strings use
  `s.length()`.
- **Printing an array with `println(a)`** and getting `[I@…`. Use
  `Arrays.toString(a)`.
- **Thinking `b = a` copies an array.** It copies the reference. Use
  `Arrays.copyOf`.
- **Comparing arrays with `==`.** Use `Arrays.equals`.
- **Using a slot of a `new String[n]` before filling it in**, causing a
  `NullPointerException`.
- **Mixing up rows and columns** in a 2D array. It's always
  `grid[row][col]`, like reading a book: down to the line, then along.
- **Expecting an array to grow.** Its size is fixed. Make a bigger copy,
  or use an `ArrayList` (next module).

## Try it yourself

1. Run every file in [`examples/`](examples/). In the hook, design your
   own picture (a heart, a robot, your initials) in the grid.
2. Complete [`exercises/Exercise1.java`](exercises/Exercise1.java) and
   [`exercises/Exercise2.java`](exercises/Exercise2.java).
3. Build the [module project](project/README.md): two-player
   Tic-Tac-Toe.
4. Commit and push your work:

   ```bash
   git add .
   git commit -m "Complete Module 6: arrays"
   git push
   ```

Next: **[Module 7: ArrayList, HashMap & HashSet](../07-arraylists-and-hashmaps/README.md)**.
