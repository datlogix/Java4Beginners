# Module 12: Interfaces, Enums & Records

## Hook: five rankings, five lines of code

Run [`examples/Ex00Leaderboard.java`](examples/Ex00Leaderboard.java):

```bash
java Ex00Leaderboard.java
```

```
By score, highest first:
  Kojo    PRESEC         92   47.9 s
  Akosua  ACHIMOTA       92   44.1 s
  Ama     WESLEY_GIRLS   88   41.2 s
  ...

Score, then fastest as the tie-breaker:
  Akosua  ACHIMOTA       92   44.1 s
  Kojo    PRESEC         92   47.9 s
  Efua    ACHIMOTA       88   39.5 s
  ...
```

The same six results, ranked five different ways. Each ranking is **one
line**:

```java
Comparator.comparingInt(Result::score).reversed()
Comparator.comparing(Result::name)
Comparator.comparingDouble(Result::seconds)
Comparator.comparing(Result::school).thenComparing(Result::score, Comparator.reverseOrder())
Comparator.comparingInt(Result::score).reversed().thenComparingDouble(Result::seconds)
```

And look how the data is defined: a school is one of a **fixed list**
of values, and a result is a single line:

```java
enum School { ACHIMOTA, PRESEC, WESLEY_GIRLS, MFANTSIPIM, ST_PETERS }

record Result(String name, School school, int score, double seconds) { }
```

That `record` line gives you a complete class with a constructor,
getters, `equals`, `hashCode` and `toString`, all written by Java. This
module covers the three tools in the hook: **interfaces** (which is
what a `Comparator` is), **enums**, and **records**.

## Interfaces: a contract

An **interface** lists methods that a class **promises** to provide,
without saying how:

```java
interface Chargeable {
    int batteryPercent();
    void charge(int minutes);
}

class Phone implements Chargeable {
    private int battery = 20;
    public int batteryPercent() { return battery; }
    public void charge(int minutes) { battery = Math.min(100, battery + minutes); }
}
```

- `implements Chargeable` signs the contract. The class **must** provide
  every method in the interface, or it won't compile.
- Interface methods are automatically `public` and `abstract`, so the
  implementing methods must be `public`.
- You can't create an interface (`new Chargeable()`), but you can use it
  as a **type**: a `Chargeable` variable can hold a Phone, an electric
  bike, a robot, anything that implements it.

```java
static void chargeAll(List<Chargeable> things, int minutes) {
    for (Chargeable c : things) {
        c.charge(minutes);
    }
}
```

This is polymorphism again, as in Module 11, but **without inheritance**.
A phone, a bike and a robot have nothing else in common, and they don't
need to. See [`examples/Ex01FirstInterface.java`](examples/Ex01FirstInterface.java).

### Interfaces versus abstract classes

| | Abstract class | Interface |
|---|---|---|
| A class can… | **extend one** | **implement many** |
| Fields | any | only constants |
| Constructors | yes | no |
| Methods with code | yes | only `default` and `static` methods |
| Use it for | a family that shares state and code ("is a") | a capability unrelated classes share ("can do") |

Because a class can implement any number of interfaces, they're how Java
lets one class play several roles:
`class Player extends GameCharacter implements Drawable, Movable, Named`.
See [`examples/Ex02MultipleInterfaces.java`](examples/Ex02MultipleInterfaces.java).

### Default and static methods

An interface can include a **`default`** method, with a body that every
implementing class gets for free (and may override), and **`static`**
helper methods, called on the interface itself:

```java
interface Sensor {
    double read();
    String unit();

    default String describe() {
        return String.format("%.2f %s", read(), unit());
    }

    static double celsiusToFahrenheit(double c) {
        return c * 9 / 5 + 32;
    }
}
```

See [`examples/Ex03DefaultAndStaticMethods.java`](examples/Ex03DefaultAndStaticMethods.java).

### Coding to the interface

You've already been using interfaces. `List`, `Map` and `Set` are
interfaces; `ArrayList`, `HashMap` and `HashSet` are classes that
implement them. That's why good Java style declares variables with the
interface type:

```java
List<String> names = new ArrayList<>();
Map<String, Integer> counts = new HashMap<>();
```

The rest of the program only relies on `List`'s contract, so switching to
a different implementation later means changing one line.

## `Comparable`: a natural order

`Collections.sort`, `TreeSet`, `TreeMap` and `Collections.max` need to
know how to order your objects. A class gives itself a **natural order**
by implementing the `Comparable` interface, which has one method:

```java
class Student implements Comparable<Student> {
    ...
    @Override
    public int compareTo(Student other) {
        return id.compareTo(other.id);       // natural order: by ID
    }
}
```

`a.compareTo(b)` returns a **negative** number if `a` comes first, a
**positive** number if `b` comes first, and **0** if they're equal in this
order. `String`, `Integer`, `Double` and enums already implement it,
so the easiest way to write a `compareTo` is usually to call theirs:
`id.compareTo(other.id)`, or `Double.compare(gpa, other.gpa)` for
primitives. (Don't subtract numbers to compare them: with large values
the subtraction can overflow.) See
[`examples/Ex04Comparable.java`](examples/Ex04Comparable.java).

## Lambdas

An interface with exactly **one** abstract method is called a
**functional interface**. Instead of writing a whole class to implement
it, you can write a **lambda**: a tiny function, written inline:

```java
interface Operation {
    double apply(double a, double b);
}

Operation multiply = (a, b) -> a * b;
multiply.apply(12, 4)          // 48.0
```

Read `(a, b) -> a * b` as "given `a` and `b`, give back `a * b`". The
parameter types come from the interface, so you don't write them. A
lambda with several statements uses braces and `return`:

```java
Operation safeDivide = (a, b) -> {
    if (b == 0) {
        return 0;
    }
    return a / b;
};
```

A lambda is an object like any other: you can store it in a variable,
pass it to a method, or put it in a list. Java has many built-in
functional interfaces, such as `Runnable` (no parameters, no result:
`() -> System.out.println("Hi")`). Module 15 introduces the most useful
ones. See [`examples/Ex05Lambdas.java`](examples/Ex05Lambdas.java).

## `Comparator`: as many orders as you like

`Comparable` gives a class **one** natural order, built in. A
**`Comparator`** is an order that lives **outside** the class, so you
can have as many as you want. It's a functional interface, so a lambda
can be one:

```java
phones.sort((a, b) -> Double.compare(a.getPrice(), b.getPrice()));
```

Even neater, `Comparator` has static methods that build an order from a
**key**, the thing to compare by:

```java
phones.sort(Comparator.comparingDouble(Phone::getPrice));            // cheapest first
phones.sort(Comparator.comparingDouble(Phone::getPrice).reversed()); // dearest first
phones.sort(Comparator.comparingInt(Phone::getStorageGb).reversed()
        .thenComparingDouble(Phone::getPrice));                       // most storage, then cheapest
```

`Phone::getPrice` is a **method reference**: a short way to write the
lambda `p -> p.getPrice()`. Use `comparing` for keys that are objects
(`String`, enums), and `comparingInt` / `comparingDouble` for
numbers. `reversed()` flips the order; `thenComparing` breaks ties. Now
you can read every line of the hook. See
[`examples/Ex06Comparator.java`](examples/Ex06Comparator.java).

## Enums: a fixed set of values

Some types have a small, fixed set of possible values: the days of the
week, the colours of a traffic light, the grades A to F. You *could* use
Strings (`"MONDAY"`), but then nothing stops a typo like `"MONDYA"`. An
**enum** (enumeration) makes the set official:

```java
enum TrafficLight { RED, AMBER, GREEN }

TrafficLight light = TrafficLight.RED;
```

- The compiler rejects any value that isn't on the list.
  `TrafficLight.BLUE` doesn't compile.
- Enum values **can** be compared with `==`: there's only ever one
  `TrafficLight.RED` object.
- They work beautifully with `switch`, and a switch expression over an
  enum needn't have a `default` if it covers every value (the compiler
  checks).
- `TrafficLight.values()` gives every value, in the order declared;
  `ordinal()` gives a value's position; `TrafficLight.valueOf("RED")`
  turns text into a value (and throws `IllegalArgumentException` for
  anything else).
- Enums are `Comparable`, in declaration order, and have a ready-made
  `toString` that gives the value's name.

See [`examples/Ex07Enums.java`](examples/Ex07Enums.java).

### Enums with data

An enum is really a special kind of class, so each value can carry its
own data, through fields and a constructor:

```java
enum Grade {
    A(80, 4.0), B_PLUS(75, 3.5), B(70, 3.0), C(60, 2.0), D(50, 1.0), F(0, 0.0);

    final int minimumMark;
    final double point;

    Grade(int minimumMark, double point) {
        this.minimumMark = minimumMark;
        this.point = point;
    }

    static Grade forMark(int mark) {
        for (Grade g : values()) {
            if (mark >= g.minimumMark) {
                return g;
            }
        }
        throw new IllegalArgumentException("Bad mark: " + mark);
    }
}
```

Compare this with the Module 4 grade exercise: the whole grading table
now lives in one place, as data. See
[`examples/Ex08EnumsWithFields.java`](examples/Ex08EnumsWithFields.java),
which also works out your weight on four planets.

## Records: data classes in one line

Many classes just **hold data**: a point, a reading, a result. Writing
private final fields, a constructor, getters, `equals`, `hashCode` and
`toString` for each is repetitive. A **record** (Java 16+) does it all:

```java
record Point(int x, int y) { }

Point p = new Point(3, 4);
p.x()                           // 3: the getter has the field's name, no "get"
System.out.println(p);          // Point[x=3, y=4]
p.equals(new Point(3, 4))       // true: compares the data
```

Records are **immutable**: their fields are `final`, and there are no
setters. To "change" one, make a new one. That makes them safe to share
and perfect as keys in a `HashMap` or values in a `HashSet`.

A record can still check its data, in a **compact constructor** (no
parameter list; the fields are assigned automatically afterwards), and
can have extra methods:

```java
record Reading(String sensor, double value, String unit) {
    Reading {
        if (sensor == null || sensor.isBlank()) {
            throw new IllegalArgumentException("A reading needs a sensor name.");
        }
    }

    String describe() {
        return sensor + ": " + value + " " + unit;
    }
}
```

Use a **record** for plain data that shouldn't change. Use a **class**
when an object has state that changes over time (a bank balance, a
pet's hunger) or behaviour that protects it. See
[`examples/Ex09Records.java`](examples/Ex09Records.java).

## An abstract data type

[`examples/Ex10StackInterface.java`](examples/Ex10StackInterface.java)
puts interfaces to classic use. A `Stack` interface says **what** a
stack does (push, pop, peek: last in, first out), and `ArrayStack` says
**how**, with an array that doubles in size when it fills up. A bracket
checker uses the stack without knowing anything about arrays. Swap in a
different implementation and the checker doesn't change. Describing a
data structure by its operations, separately from how it's stored, is
called an **abstract data type**, and interfaces are how Java expresses
one.

## Common beginner mistakes

- **Forgetting `public`** on methods that implement an interface:
  "attempting to assign weaker access privileges".
- **A `compareTo` that isn't consistent**, such as returning `1` for
  both `a.compareTo(b)` and `b.compareTo(a)`. Use `Integer.compare`,
  `Double.compare` and `String.compareTo` rather than inventing your
  own.
- **Sorting by subtracting numbers** (`return a.gpa - b.gpa;` doesn't
  even compile for doubles, and overflows for big ints).
- **Calling `valueOf` on text with the wrong capitals.** Enum names are
  usually UPPER_CASE: `Day.valueOf(text.toUpperCase())`.
- **Trying to change a record's field.** Create a new record instead.
- **Using a record for something that changes**, like a stock level
  or a balance. That's a job for a class.
- **Using Strings for a fixed set of categories** when an enum would
  catch typos at compile time.
- **Writing `p.getX()` for a record.** Record getters are named after
  the field: `p.x()`.

## Try it yourself

1. Run every file in [`examples/`](examples/). In the hook, add a sixth
   ranking of your own.
2. Complete [`exercises/Exercise1.java`](exercises/Exercise1.java) and
   [`exercises/Exercise2.java`](exercises/Exercise2.java).
3. Build the [module project](project/README.md) in the track of your
   choice: a media library, a logic gate simulator, or an emergency
   triage queue.
4. Commit and push your work:

   ```bash
   git add .
   git commit -m "Complete Module 12: interfaces, enums and records"
   git push
   ```

Next: **[Module 13: Exceptions & Debugging](../13-exceptions-and-debugging/README.md)**.
