# Module 7: ArrayList, HashMap & HashSet

## Hook: what does this text say most?

Run [`examples/Ex00WordFrequency.java`](examples/Ex00WordFrequency.java):

```bash
java Ex00WordFrequency.java
```

```
80 words, 43 different ones.

Top 8:
  the        18 ******************
  robot       4 ****
  it          4 ****
  lab         3 ***
  found       3 ***
  ...

Used only once: [a, arm, asleep, back, bench, broken, but, by, corner, ...]
```

In a fraction of a second, Java counted every word in a paragraph,
ranked them, and listed every word used only once, in alphabetical
order. Now paste something of your own into the `TEXT` at the top of the
file: a speech, an essay, the lyrics of a song you like. Which word wins?

Doing this with arrays from Module 6 would be painful. You don't know
in advance how many different words there will be, and to count a word
you'd have to search a whole array for it every time. This module gives
you three new tools that make it easy: a list that **grows**
(`ArrayList`), a table that **looks things up by name** (`HashMap`), and
a collection that **never holds duplicates** (`HashSet`). Together
they're called **collections**, and real Java programs use them far more
than plain arrays.

## `ArrayList`: a list that grows

```java
import java.util.ArrayList;

ArrayList<String> queue = new ArrayList<>();   // an empty list of Strings
queue.add("Ama");                               // add to the end
queue.add("Kojo");
queue.add(0, "Yaw");                            // insert at index 0
System.out.println(queue);                      // [Yaw, Ama, Kojo]
```

The type in angle brackets, `<String>`, says what the list holds. It's
called a **type parameter**, and it means Java will stop you from
putting anything else in the list. The `<>` on the right (the "diamond")
tells Java to fill in the same type again. Unlike arrays, lists **print
nicely** with plain `println`.

| Method | Does | Array equivalent |
|---|---|---|
| `list.add(x)` | Add `x` to the end | (impossible: fixed size) |
| `list.add(i, x)` | Insert `x` at index `i`, moving the rest along | |
| `list.get(i)` | The value at index `i` | `a[i]` |
| `list.set(i, x)` | Replace the value at index `i` | `a[i] = x` |
| `list.size()` | How many values | `a.length` |
| `list.remove(i)` | Remove the value at index `i` (and give it back) | |
| `list.remove(x)` | Remove the first `x` (gives `true` if it was there) | |
| `list.contains(x)` | Is `x` in the list? | (a search loop) |
| `list.indexOf(x)` | Where is `x`? (`-1` if it isn't) | (a search loop) |
| `list.isEmpty()` / `list.clear()` | Is it empty? / Remove everything | |

Indexes start at 0, just like arrays, and a bad index crashes with an
`IndexOutOfBoundsException`. See
[`examples/Ex01ArrayListBasics.java`](examples/Ex01ArrayListBasics.java).

### Lists of numbers: wrapper classes

Collections can only hold **objects**, not primitives. So
`ArrayList<int>` doesn't compile. Instead, each primitive type has a
matching **wrapper class**:

| Primitive | Wrapper |
|---|---|
| `int` | `Integer` |
| `double` | `Double` |
| `boolean` | `Boolean` |
| `char` | `Character` |

```java
ArrayList<Integer> marks = new ArrayList<>();
marks.add(72);                 // Java wraps the int in an Integer: "autoboxing"
int first = marks.get(0);      // ...and unwraps it again: "unboxing"
```

This mostly happens without you noticing. But it causes two famous
traps:

1. **`remove(int)` removes by index.** On an `ArrayList<Integer>`,
   `numbers.remove(1)` removes whatever is at **index 1**, not the
   number 1. To remove the value, write `numbers.remove(Integer.valueOf(1))`.
2. **Compare `Integer`s with `.equals()`, not `==`.** They're objects, so
   `==` compares references, just like Strings. It *seems* to work for
   small numbers (Java reuses objects from −128 to 127), and then fails
   for bigger ones.

See [`examples/Ex02WrapperClasses.java`](examples/Ex02WrapperClasses.java).

### Looping, and removing safely

Loop over a list exactly as you would an array: for-each when you just
need the values, an index loop (with `size()` and `get(i)`) when you
need positions.

But **never add or remove items inside a for-each loop over the same
list**. Java detects it and crashes with a
`ConcurrentModificationException`. To remove matching items, either
loop **backwards** by index (so removing an item doesn't shift the ones
you haven't visited yet), or use an `Iterator`, which has its own safe
`remove()`:

```java
for (int i = tasks.size() - 1; i >= 0; i--) {
    if (tasks.get(i).startsWith("buy")) {
        tasks.remove(i);
    }
}
```

(Module 15 shows a one-line way: `tasks.removeIf(...)`.) See
[`examples/Ex03LoopingAndRemoving.java`](examples/Ex03LoopingAndRemoving.java).

### The `Collections` class

`java.util.Collections` does for lists what `Arrays` does for arrays:
`Collections.sort(list)`, `reverse`, `shuffle`, `max`, `min` and
`frequency(list, x)` (how many times `x` appears).

`List.of(1, 2, 3)` creates a list in one line, but it's **fixed**:
trying to add to it crashes. To get a list you can change, copy it:
`new ArrayList<>(List.of(1, 2, 3))`. See
[`examples/Ex04CollectionsClass.java`](examples/Ex04CollectionsClass.java).

## `HashMap`: look things up by key

A list finds things by **position**. But often you want to find things
by **name**: the capital of Kenya, the price of an LED, the number of
votes for Akua. A **map** stores **key → value** pairs:

```java
import java.util.HashMap;

HashMap<String, String> capitals = new HashMap<>();   // keys: String, values: String
capitals.put("Ghana", "Accra");
capitals.put("Kenya", "Nairobi");

capitals.get("Kenya")                    // "Nairobi"
capitals.get("Mali")                     // null: no such key
capitals.getOrDefault("Mali", "?")       // "?"
```

Think of a real dictionary: you look up a **word** (the key) to find its
**meaning** (the value). Each **key appears only once**: `put` with an
existing key **replaces** its value. Values can repeat.

| Method | Does |
|---|---|
| `map.put(key, value)` | Add a pair, or replace the value for an existing key |
| `map.get(key)` | The value for `key`, or `null` if it isn't there |
| `map.getOrDefault(key, d)` | The value for `key`, or `d` if it isn't there |
| `map.containsKey(key)` / `map.containsValue(v)` | Is that key / value there? |
| `map.remove(key)` | Remove a pair (gives back the old value, or `null`) |
| `map.size()`, `map.isEmpty()` | How many pairs / are there none? |

Finding a key in a `HashMap` is **fast**, even with millions of
entries: it doesn't search one by one. (The "hash" in the name is the
trick it uses: Module 11 gives you a peek at how it works.) See
[`examples/Ex05HashMapBasics.java`](examples/Ex05HashMapBasics.java).

### Looping over a map

```java
for (String name : ages.keySet()) { ... }            // every key
for (int age : ages.values()) { ... }                // every value
for (Map.Entry<String, Integer> e : ages.entrySet()) {
    System.out.println(e.getKey() + " -> " + e.getValue());   // both together
}
```

A `HashMap` keeps **no particular order**: entries come out in an order
that looks random, and can change as the map grows. If order matters,
choose a different map. They all work the same way:

| Map | Order |
|---|---|
| `HashMap` | None (the fastest) |
| `TreeMap` | Keys in **sorted** order (alphabetical, or smallest number first) |
| `LinkedHashMap` | Keys in the order you **added** them |

You'll often see a map's variable declared with the general type `Map`,
as in `Map<String, Integer> ages = new HashMap<>();`. That says "I need
*a* map; this one happens to be a HashMap." Module 12 explains why that's
good style. See
[`examples/Ex06LoopingOverMaps.java`](examples/Ex06LoopingOverMaps.java).

### The counting pattern

The single most useful thing to do with a map is **count** things:

```java
Map<String, Integer> tally = new TreeMap<>();
for (String vote : votes) {
    tally.put(vote, tally.getOrDefault(vote, 0) + 1);
}
```

Read it as: "look up this vote's count (or 0 if we haven't seen it),
add 1, and put it back." That one line is the heart of the hook. You'll
also see `tally.merge(vote, 1, Integer::sum)`, which does the same job:
"put 1, or *add* 1 to what's already there." (`Integer::sum` is a
ready-made "add two numbers" method, passed in by name. Module 15
explains this `::` syntax.) See
[`examples/Ex07Counting.java`](examples/Ex07Counting.java).

### Collections inside collections

A map's values can be lists, and a list can hold maps:

```java
Map<String, List<Integer>> marks = new TreeMap<>();
marks.put("Akua", new ArrayList<>(List.of(70, 82, 91)));
marks.get("Akua").add(65);           // add a mark to Akua's list
```

Before adding to a list inside a map, make sure the list exists: if
`marks.get("Esi")` is `null`, calling `.add` on it crashes with a
`NullPointerException`. See
[`examples/Ex08NestedCollections.java`](examples/Ex08NestedCollections.java).

## `HashSet`: no duplicates allowed

A **set** holds values with **no duplicates** and no positions. Adding
something that's already there does nothing (and `add` gives back
`false` to tell you):

```java
Set<String> visited = new HashSet<>();
visited.add("Accra");       // true
visited.add("Accra");       // false: already there
visited.contains("Accra")   // true, and fast, like a map's containsKey
```

Sets are perfect for "have I seen this before?" (voter IDs, visited
rooms, used usernames) and for removing duplicates in one line:
`new TreeSet<>(list)`. As with maps, `HashSet` has no order, `TreeSet`
keeps values sorted, and `LinkedHashSet` keeps them in the order added.

Sets also do the set operations from maths:

| Operation | Method | Gives |
|---|---|---|
| Intersection | `a.retainAll(b)` | Keeps only what's in **both** |
| Union | `a.addAll(b)` | Everything in **either** |
| Difference | `a.removeAll(b)` | What's in `a` but **not** `b` |

These **change** `a`, so make a copy first if you still need it. See
[`examples/Ex09HashSet.java`](examples/Ex09HashSet.java).

## Which collection should I use?

| You need… | Use |
|---|---|
| A fixed number of values, or a grid | an **array** |
| An ordered list that grows and shrinks | `ArrayList` |
| To look values up by a key | `HashMap` (`TreeMap` for sorted keys) |
| To know whether you've seen something, or to remove duplicates | `HashSet` (`TreeSet` for sorted) |

[`examples/Ex10PhoneBook.java`](examples/Ex10PhoneBook.java) puts the
module together in a menu-driven phone book. Now re-read the hook. You
can explain all of it except two lines. `split("[^a-z']+")` splits the
text at anything that *isn't* a letter or an apostrophe (another regular
expression). And `entries.sort((a, b) -> b.getValue() - a.getValue())`
sorts the entries by count, biggest first, using a **lambda**: a tiny
function written inline. You'll write your own in Module 12.

## Common beginner mistakes

- **`ArrayList<int>`.** Collections need wrapper classes:
  `ArrayList<Integer>`.
- **`remove(1)` on a list of `Integer`s** removes index 1, not the
  value 1.
- **Comparing `Integer`s (or any objects) with `==`.** Use `.equals()`.
- **Removing from a list inside a for-each loop over it**:
  `ConcurrentModificationException`. Loop backwards, or use an
  `Iterator`.
- **Forgetting that `get` can return `null`.** Use `getOrDefault`, or
  check `containsKey` first.
- **Expecting a `HashMap` or `HashSet` to keep things in order.** Use
  `TreeMap`/`TreeSet` (sorted) or `LinkedHashMap`/`LinkedHashSet`
  (insertion order).
- **Thinking `put` adds a second entry for the same key.** It replaces
  the first.
- **Forgetting the imports.** Everything in this module lives in
  `java.util`. `import java.util.*;` imports all of it at once.

## Try it yourself

1. Run every file in [`examples/`](examples/). Put your own text into
   the hook.
2. Complete [`exercises/Exercise1.java`](exercises/Exercise1.java) and
   [`exercises/Exercise2.java`](exercises/Exercise2.java).
3. Build the [module project](project/README.md): a vocabulary quiz.
4. Commit and push your work:

   ```bash
   git add .
   git commit -m "Complete Module 7: ArrayList, HashMap and HashSet"
   git push
   ```

Next: **[Module 8: Methods](../08-methods/README.md)**.
