# Module 16: Recursion & Algorithms

## Hook: a tree from one method that calls itself

Run [`examples/Ex00FractalTree.java`](examples/Ex00FractalTree.java):

```bash
java Ex00FractalTree.java
```

A window opens with a tree: a trunk, two branches, four smaller
branches, eight smaller still, all the way out to over a thousand green
twigs. Here is the code that draws **all** of it:

```java
void branch(Graphics2D pen, double x, double y, double length, double heading, int depth) {
    if (depth == 0) {
        return;                         // too small: stop
    }
    ... draw one line from (x, y), length long, in the direction heading ...
    branch(pen, endX, endY, length * SHRINK, heading + ANGLE, depth - 1);
    branch(pen, endX, endY, length * SHRINK, heading - ANGLE, depth - 1);
}
```

`branch` draws one line, then **calls itself twice**, to draw two
smaller trees growing from the tip. Each of those draws a line and calls
itself twice more, and so on, until `depth` reaches 0. A method that
calls itself is **recursive**. Change `ANGLE` to 60, or `SHRINK` to 0.8,
and the whole tree changes shape.

Recursion is the natural way to handle anything that contains smaller
copies of itself: trees, folders inside folders, mazes, family trees,
circuits built from smaller circuits. This module teaches you to think
recursively, and then uses that skill on two of the most important jobs
in computing: **searching** and **sorting**, and how to measure which
way of doing them is fastest.

## Recursion: a smaller version of the same problem

Every recursive method has two parts:

1. A **base case**: a version of the problem so small you can answer it
   directly, without recursion.
2. A **recursive case**: a way to solve a bigger version using the
   answer to a **smaller** version.

```java
/** n! = n x (n-1)!   and   0! = 1 */
static long factorial(int n) {
    if (n == 0) {
        return 1;                        // base case
    }
    return n * factorial(n - 1);         // recursive case: a smaller problem
}
```

`factorial(4)` is `4 * factorial(3)`, which is `4 * 3 * factorial(2)`…
down to `factorial(0)`, which is `1`. The base case is what stops it.
Every recursive call must move **closer** to the base case; otherwise
the recursion never ends. See
[`examples/Ex01CountdownAndFactorial.java`](examples/Ex01CountdownAndFactorial.java),
which also adds up the digits of a number (`sumDigits(4071)` is
`1 + sumDigits(407)`) and works out powers.

### What really happens: the call stack

Remember the call stack from Module 8: each method call waits for the
calls it makes to finish. Recursion is no different. Each call gets its
own copy of its parameters and local variables, and waits for the call
below it:

```
factorial(4) called
    factorial(3) called
        factorial(2) called
            factorial(1) called
                factorial(0) called
                base case: returning 1
            factorial(1) returns 1 x 1 = 1
        factorial(2) returns 2 x 1 = 2
    factorial(3) returns 3 x 2 = 6
factorial(4) returns 4 x 6 = 24
```

That's the output of [`examples/Ex02CallStackTrace.java`](examples/Ex02CallStackTrace.java):
five calls go **down** to the base case, then the answers come back
**up**, each call finishing its multiplication.

### Thinking recursively

The trick is to **trust the recursion**. To write `reverse(s)`, don't
try to picture every call. Just ask: "if I already had a method that
reversed a *shorter* string, how would I use it?"

```java
static String reverse(String s) {
    if (s.length() <= 1) {
        return s;                                  // base case: nothing to reverse
    }
    return reverse(s.substring(1)) + s.charAt(0);  // reverse the rest, then add the first letter
}
```

The same pattern, "deal with the first item, let recursion deal with
the rest", works for checking palindromes, adding up arrays and counting
characters. A method can also make **several** recursive calls, as the
tree does, and as the permutations method in
[`examples/Ex03RecursionOnStringsAndArrays.java`](examples/Ex03RecursionOnStringsAndArrays.java)
does to list every way of ordering `ABC`.

### When recursion goes wrong

Leave out the base case, or make a call that isn't smaller, and the
calls never stop. Each one takes a little memory on the call stack, and
eventually it runs out: a **`StackOverflowError`**. Run
[`examples/Ex05StackOverflow.java`](examples/Ex05StackOverflow.java):
it gets through about twenty thousand calls first. That limit also
means very deep recursion (a million levels) isn't practical in Java,
even when it's correct. A loop is better there.

### Making recursion fast: memoisation

The Fibonacci numbers (0, 1, 1, 2, 3, 5, 8, 13…, each the sum of the
two before) have a beautifully simple recursive definition:

```java
static long fib(int n) {
    if (n < 2) {
        return n;
    }
    return fib(n - 1) + fib(n - 2);
}
```

But run [`examples/Ex04Fibonacci.java`](examples/Ex04Fibonacci.java):

```
fibSlow(30) =      832,040     2,692,537 calls      3.9 ms
fibSlow(40) =  102,334,155   331,160,281 calls    479.0 ms
fibFast(40) = 102,334,155   79 calls
```

Over 300 million calls for `fib(40)`, because the same values are
calculated again and again: `fib(40)` calls `fib(38)` twice, `fib(37)`
three times, and so on. The fix is **memoisation**: store each answer in
a `HashMap` the first time you work it out, and look it up after that.
79 calls instead of 331 million.

## Searching

### Linear search

The search you've written since Module 6 checks every item in turn.
For `n` items it might need `n` comparisons. Double the data, double the
work.

### Binary search

If the data is **sorted**, there's a much better way: look at the
**middle** item. If it's the one you want, you're done. If the target is
bigger, it can only be in the **upper half**; if smaller, the **lower
half**. Throw away the other half, and repeat:

```java
static int binarySearch(int[] a, int target) {
    int low = 0, high = a.length - 1;
    while (low <= high) {
        int mid = (low + high) / 2;
        if (a[mid] == target) {
            return mid;
        } else if (a[mid] < target) {
            low = mid + 1;          // it must be in the upper half
        } else {
            high = mid - 1;         // it must be in the lower half
        }
    }
    return -1;
}
```

Each step halves what's left, so a million items need only about 20
steps, and a hundred million about 27. That's how the computer guessed
your number in at most 7 tries in Module 5. Run
[`examples/Ex06LinearVsBinarySearch.java`](examples/Ex06LinearVsBinarySearch.java):

```
       1,000 items: linear        1,000 comparisons, binary 10
   1,000,000 items: linear    1,000,000 comparisons, binary 20
 100,000,000 items: linear  100,000,000 comparisons, binary 27
```

Binary search can also be written recursively (the example shows how).
Java has it built in: `Arrays.binarySearch` and `Collections.binarySearch`.

## Sorting

Binary search needs sorted data, so how do you sort? There are dozens
of algorithms. Three show the main ideas:

- **Selection sort** ([`Ex07`](examples/Ex07SelectionSort.java)): find
  the smallest item and swap it to the front; then the smallest of the
  rest, and so on.
- **Insertion sort** ([`Ex08`](examples/Ex08InsertionSort.java)): take
  each item in turn and slide it left into its place among the items
  already sorted, the way you'd sort a hand of cards.
- **Merge sort** ([`Ex09`](examples/Ex09MergeSort.java)), which is
  **recursive**: split the array in half, sort each half (by merge sort!),
  then **merge** the two sorted halves by repeatedly taking the smaller
  of their front items. The base case is an array of 0 or 1 items, which
  is already sorted.

Each example prints its progress step by step. Follow them with a pen
and paper on a short array: it's the best way to understand them.

## Big-O: how the work grows

How long an algorithm takes depends on the computer. A better question
is: **how does the work grow** as the input grows? That's what
**Big-O notation** describes:

| Big-O | Name | Double the input, and the work… | Example |
|---|---|---|---|
| O(1) | constant | stays the same | `array[i]`, `map.get(key)` |
| O(log n) | logarithmic | goes up by **one step** | binary search |
| O(n) | linear | **doubles** | linear search, adding up a list |
| O(n log n) | "n log n" | a little more than doubles | merge sort, `Arrays.sort` |
| O(n²) | quadratic | **quadruples** | selection sort, insertion sort, nested loops over the data |
| O(2ⁿ) | exponential | squares (adding just one more item doubles it) | the slow `fib`, the Towers of Hanoi |

Run [`examples/Ex10SortingRace.java`](examples/Ex10SortingRace.java):

```
      size   insertion ms     merge ms Arrays.sort ms
    16,000           24.8          1.4            1.4
    32,000           90.1          2.8            1.6
    64,000          362.7          7.6            4.4
```

Every time the size doubles, insertion sort takes about **four times** as
long (O(n²)), while merge sort only roughly doubles (O(n log n)). For a
million items, that's the difference between a couple of minutes and a
fraction of a second. For small inputs almost anything is fast; Big-O
matters when the data gets big. (`Arrays.sort` uses a highly tuned
O(n log n) algorithm. In real programs, use it.)

Finally, [`examples/Ex11TowersOfHanoi.java`](examples/Ex11TowersOfHanoi.java)
solves a puzzle that's very hard to solve with loops and takes three
lines with recursion, and shows what O(2ⁿ) means in practice.

## Common beginner mistakes

- **No base case**, or a base case that's never reached:
  `StackOverflowError`.
- **A recursive call that isn't smaller**, such as `factorial(n)`
  calling `factorial(n)`.
- **Forgetting to `return` the recursive call's result.** Writing
  `factorial(n - 1);` on its own throws the answer away.
- **Binary search on unsorted data.** It gives wrong answers without any
  error. Sort first.
- **Off-by-one errors in binary search**: `low < high` instead of
  `low <= high`, or `mid` instead of `mid + 1`. Test with the first item,
  the last item, and a missing item.
- **Timing tiny inputs once.** Use big inputs, warm up first, and repeat.
- **Recursion where a loop is simpler.** Adding up a list is clearer as a
  loop. Use recursion when the problem itself is recursive.

## Try it yourself

1. Run every file in [`examples/`](examples/). In the hook, give the
   tree three branches at each level instead of two.
2. Complete [`exercises/Exercise1.java`](exercises/Exercise1.java) and
   [`exercises/Exercise2.java`](exercises/Exercise2.java).
3. Build the [module project](project/README.md) in the track of your
   choice: a maze solver, resistor networks, or a DNA toolkit.
4. Commit and push your work:

   ```bash
   git add .
   git commit -m "Complete Module 16: recursion and algorithms"
   git push
   ```

Next: **[Module 17: Packages, Maven & JUnit Testing](../17-packages-maven-and-junit/README.md)**.
