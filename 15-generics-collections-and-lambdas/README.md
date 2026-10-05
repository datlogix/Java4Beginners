# Module 15: Generics, Collections & Lambdas

## Hook: six questions, six statements

Run [`examples/Ex00OneLineAnswers.java`](examples/Ex00OneLineAnswers.java):

```bash
java Ex00OneLineAnswers.java
```

```
1. Total revenue: GHS 31,357.00
2. Top three by revenue: [Raspberry Pi 5, ESP32, Arduino Uno]
3. Revenue by category: {Boards=12405.0, Components=6580.0, Motors=5096.0, ...}
4. Pocket-money items: [Jumper wires, LED pack, Soil moisture sensor, Ultrasonic sensor]
5. Units sold: 657
6. Average Boards price: GHS 421.25
...
```

A month of sales in a makerspace shop, and six questions about it. With
the loops from Part 1, question 3 alone (revenue for each category)
would need a map, a loop, `getOrDefault`, and a dozen lines. Here, each
answer is **one statement** that reads almost like the question:

```java
sales.stream()
     .sorted(Comparator.comparingDouble(Sale::revenue).reversed())
     .limit(3)
     .map(Sale::product)
     .toList();
```

"Take the sales, sort them by revenue, biggest first, keep the first
three, turn each into its product name, and give me a list." This is a
**stream**, and it's how modern Java processes data. Streams are built
from two ideas this module explains properly: **generics** (the `<T>`
in `List<T>`, which you've used since Module 7) and **lambdas** (the
`->` you met in Module 12).

## Generics: one class, any type

Since Module 7 you've written `ArrayList<String>` and
`HashMap<String, Integer>`. The part in angle brackets is a **type
argument**: it tells the list what it holds, so Java can stop you adding
the wrong thing and you never need to cast what comes out. Classes that
take type arguments are **generic**, and you can write your own:

```java
class Box<T> {
    private T contents;

    void put(T item) { contents = item; }
    T get() { return contents; }
}

Box<String> words = new Box<>();
words.put("Akwaaba");
String w = words.get();        // no cast: Java knows it's a String
words.put(42);                 // compile error: a Box<String> only holds Strings
```

`T` is a **type parameter**: a placeholder that's filled in when the
class is used. One generic class works for every type, with full
type-checking, instead of one class per type. By convention type
parameters are single capital letters: `T` (type), `E` (element), `K`
and `V` (key and value), `A` and `B`. A class can have several:
`class Pair<A, B>`. Generic types work with objects only, which is why
you write `Box<Integer>`, not `Box<int>`. See
[`examples/Ex01GenericClass.java`](examples/Ex01GenericClass.java).

### Generic methods and bounds

A single method can be generic too. The type parameter goes before the
return type:

```java
static <T> void swap(T[] array, int i, int j) { ... }
```

Often a generic method needs to *do* something with its items, such as
compare them. A **bound** says what the type must be able to do:

```java
static <T extends Comparable<T>> T max(List<T> items) {
    T best = items.get(0);
    for (T item : items) {
        if (item.compareTo(best) > 0) {
            best = item;
        }
    }
    return best;
}

max(List.of(3, 41, 7))             // 41
max(List.of("mango", "apple"))     // "mango"
```

`T extends Comparable<T>` means "any type `T`, as long as `T` objects can
be compared with each other", which is exactly what `compareTo` needs.
One method finds the maximum of `Integer`s, `String`s, `Double`s, or
your own `Comparable` classes. (`extends` is used here for interfaces
too.)

A **wildcard**, `?`, means "some type I don't need to name":
`List<? extends Number>` accepts a `List<Integer>`, a `List<Double>`,
or any list of numbers. See
[`examples/Ex02GenericMethods.java`](examples/Ex02GenericMethods.java).

## The Collections Framework

The collections you've used are part of a family of **interfaces**
(Module 12) and the classes that implement them:

| Interface | Is… | Main classes |
|---|---|---|
| `List<E>` | ordered, duplicates allowed, access by index | `ArrayList`, `LinkedList` |
| `Set<E>` | no duplicates | `HashSet`, `LinkedHashSet`, `TreeSet` |
| `Queue<E>` | first in, first out | `ArrayDeque`, `LinkedList`, `PriorityQueue` |
| `Deque<E>` | a double-ended queue: also works as a **stack** | `ArrayDeque` |
| `Map<K, V>` | keys to values | `HashMap`, `LinkedHashMap`, `TreeMap` |

Two you haven't used yet:

- A **`Deque`** used as a **stack** (`push`, `pop`, `peek`) gives you
  last in, first out, like the `ArrayStack` in Module 12. Used as a
  **queue** (`offer` to join the back, `poll` to leave from the front),
  it gives first in, first out.
- A **`PriorityQueue`** always hands out the **smallest** item first
  (or the first by a `Comparator` you give it), however the items were
  added. It's how you model a hospital queue, a to-do list by priority,
  or the next event in a simulation.

See [`examples/Ex03ChoosingACollection.java`](examples/Ex03ChoosingACollection.java).
Any class that implements **`Iterable<T>`** works in a for-each loop, as
[`examples/Ex04IterableClass.java`](examples/Ex04IterableClass.java)
shows.

## Lambdas and functional interfaces

In Module 12 you wrote lambdas for your own one-method interfaces. Java
provides ready-made ones in `java.util.function`, and the whole of the
streams library is built on them:

| Interface | Takes → gives | Method | Example |
|---|---|---|---|
| `Predicate<T>` | T → boolean (a **test**) | `test` | `n -> n % 2 == 0` |
| `Function<T, R>` | T → R (a **conversion**) | `apply` | `s -> s.length()` |
| `Consumer<T>` | T → nothing (an **action**) | `accept` | `s -> System.out.println(s)` |
| `Supplier<T>` | nothing → T (a **source**) | `get` | `() -> new ArrayList<>()` |
| `BiFunction<T, U, R>` | T, U → R | `apply` | `(v, i) -> v / i` |
| `UnaryOperator<T>` | T → T | `apply` | `s -> s.toUpperCase()` |

The real power is that **methods can take functions as parameters**:

```java
static <T> int countMatching(List<T> items, Predicate<T> test) {
    int count = 0;
    for (T item : items) {
        if (test.test(item)) {
            count++;
        }
    }
    return count;
}

countMatching(numbers, n -> n % 2 == 0)        // how many are even?
countMatching(words, w -> w.length() > 5)       // how many are long?
```

One method, any question. Predicates can also be combined with `and`,
`or` and `negate`. See
[`examples/Ex05FunctionalInterfaces.java`](examples/Ex05FunctionalInterfaces.java).

### Method references

When a lambda does nothing but call one existing method, you can **name
the method** instead, with `::`:

| Method reference | Is short for |
|---|---|
| `Integer::parseInt` | `s -> Integer.parseInt(s)` (a static method) |
| `String::toUpperCase` | `s -> s.toUpperCase()` (a method of the item) |
| `System.out::println` | `x -> System.out.println(x)` (a method of one object) |
| `ArrayList::new` | `() -> new ArrayList<>()` (a constructor) |

You've already used `Sale::revenue` and `Integer::sum`. See
[`examples/Ex06MethodReferences.java`](examples/Ex06MethodReferences.java).

## Streams

A **stream** is a pipeline for processing a collection: a **source**,
any number of **intermediate** steps, and one **terminal** step that
produces the result:

```java
List<Integer> topThree = marks.stream()     // source: every mark
        .filter(m -> m >= 50)               // keep the passes
        .sorted(Comparator.reverseOrder())  // biggest first
        .limit(3)                           // just three
        .toList();                          // terminal: collect into a list
```

| Intermediate step | Does |
|---|---|
| `filter(predicate)` | Keeps only the items that pass the test |
| `map(function)` | Turns each item into something else |
| `sorted()` / `sorted(comparator)` | Puts the items in order |
| `distinct()` | Removes duplicates |
| `limit(n)` / `skip(n)` | Keeps the first `n` / skips the first `n` |
| `mapToInt(f)` / `mapToDouble(f)` | Turns items into numbers, for `sum`, `average`, `max` |

| Terminal step | Gives |
|---|---|
| `toList()` | A list of the results |
| `count()` | How many items are left |
| `sum()`, `average()`, `max()`, `min()` | Numbers (after `mapToInt` / `mapToDouble`) |
| `anyMatch(p)`, `allMatch(p)`, `noneMatch(p)` | A boolean |
| `findFirst()`, `max(comparator)` | An `Optional` (see below) |
| `forEach(action)` | Does something with each item |
| `collect(collector)` | Anything else: maps, joined strings, groups |

Three things to know:

1. **Nothing happens until the terminal step.** The intermediate steps
   just build up the recipe.
2. **Streams never change the original collection.** They produce new
   results.
3. **A stream can only be used once.** Call `.stream()` again for a new
   one.

See [`examples/Ex07StreamBasics.java`](examples/Ex07StreamBasics.java).

### Collecting into maps

The `Collectors` class turns a stream into almost anything. The most
useful collector is **`groupingBy`**, which builds a map from a key to
something about each group:

```java
// Map<programme, List<Student>>
students.stream().collect(Collectors.groupingBy(Student::programme));

// Map<level, how many>, with the keys sorted
students.stream().collect(Collectors.groupingBy(Student::level, TreeMap::new, Collectors.counting()));

// Map<programme, average GPA>
students.stream().collect(Collectors.groupingBy(Student::programme, Collectors.averagingDouble(Student::gpa)));

// One String: "Akua, Esi, Kofi"
students.stream().map(Student::name).sorted().collect(Collectors.joining(", "));
```

`partitioningBy` splits into exactly two groups (`true` and `false`),
and `summaryStatistics()` gives count, min, max and average in one go.
See [`examples/Ex08StreamGrouping.java`](examples/Ex08StreamGrouping.java).

### `Optional`: a result that might not be there

What's the maximum of an empty list? There isn't one. Instead of
returning `null` (and risking a `NullPointerException`), methods like
`max` and `findFirst` return an **`Optional`**: a box that either holds
a value or is empty.

```java
Optional<Student> best = students.stream().filter(s -> s.level() == 400)
        .max(Comparator.comparingDouble(Student::gpa));

best.map(Student::name).orElse("(none)")      // the name, or "(none)" if empty
best.isPresent()                              // is there a value?
best.ifPresent(s -> System.out.println(s))    // do something only if there is
```

## Lambdas built into collections

Collections have their own lambda-taking methods, which replace some of
the fiddliest loops from Part 1:

```java
tasks.removeIf(t -> t.startsWith("buy"));        // no ConcurrentModificationException
tasks.replaceAll(String::toUpperCase);
map.forEach((key, value) -> System.out.println(key + ": " + value));
groups.computeIfAbsent(letter, k -> new ArrayList<>()).add(name);   // create the list if needed
```

See [`examples/Ex09CollectionShortcuts.java`](examples/Ex09CollectionShortcuts.java).
Now go back to the hook and read each answer aloud as a sentence. You
can explain every one.

## Loops or streams?

Streams are excellent for **transforming and summarising** data: filter
this, group by that, add these up. Loops are still better when you need
to **stop early in complicated ways**, change several variables at once,
or when a stream would need so many steps that it's hard to read. Good
Java code uses both. If a stream doesn't read like a sentence, consider a
loop.

## Common beginner mistakes

- **Generic types with primitives**: `List<int>`. Use `List<Integer>`.
- **Using a stream twice**: "stream has already been operated upon or
  closed". Call `.stream()` again.
- **Forgetting the terminal step**, so nothing happens at all.
- **Expecting a stream to change the original list.** Use the result,
  or use `removeIf` / `replaceAll`.
- **`.get()` on an empty `Optional`**: `NoSuchElementException`. Use
  `orElse` or `ifPresent`.
- **`groupingBy` without `TreeMap::new`** when you want the keys in
  order.
- **Changing outside variables inside a lambda.** Lambdas can only use
  local variables that never change ("effectively final"). Let the
  stream produce the result instead.
- **Over-clever one-liners.** If you can't read it tomorrow, split it up.

## Try it yourself

1. Run every file in [`examples/`](examples/). In the hook, add a
   seventh question of your own and answer it with one stream.
2. Complete [`exercises/Exercise1.java`](exercises/Exercise1.java) and
   [`exercises/Exercise2.java`](exercises/Exercise2.java).
3. Build the [module project](project/README.md) in the track of your
   choice: a film club explorer, a parts inventory, or a clinic flow
   analyser.
4. Commit and push your work:

   ```bash
   git add .
   git commit -m "Complete Module 15: generics, collections and lambdas"
   git push
   ```

Next: **[Module 16: Recursion & Algorithms](../16-recursion-and-algorithms/README.md)**.
