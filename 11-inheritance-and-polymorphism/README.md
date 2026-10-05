# Module 11: Inheritance & Polymorphism

## Hook: one line of code, twelve different drawings

Run [`examples/Ex00ShapeParade.java`](examples/Ex00ShapeParade.java):

```bash
java Ex00ShapeParade.java
```

A window opens with a parade of twelve shapes: circles, squares, stars
and rings, in random colours. Now look at the code that draws them all:

```java
for (Shape shape : shapes) {
    shape.draw(pen);
}
```

That's it. One loop, one method call. There's no `if (it's a star)`,
no `switch` on the kind of shape. Yet stars come out as stars and rings
as rings, because **each kind of shape knows how to draw itself**:

```java
static class Star extends Shape {
    void draw(Graphics2D pen) { ... ten points around a centre ... }
}

static class Ring extends Circle {
    void draw(Graphics2D pen) {
        super.draw(pen);                   // draw the circle first...
        pen.setColor(Color.WHITE);
        pen.fillOval(x - 20, y - 20, 40, 40);   // ...then cut a hole in it
    }
}
```

Add a new kind of shape, a `Heart` or a `Hexagon`, and the drawing loop
doesn't change at all. This is **polymorphism** ("many forms"), and it
rests on **inheritance**: `Star extends Shape` means "a Star is a kind
of Shape". This module explains both, and they're the two ideas that
make large object-oriented programs manageable.

## Why inheritance?

Look at the first half of
[`examples/Ex01WhyInheritance.java`](examples/Ex01WhyInheritance.java).
`DogBefore` and `CatBefore` both have a `name`, an `age`, a constructor
and a `describe` method, all identical, copied and pasted. Fix a bug in
one, and you must remember to fix it in the other.

Inheritance lets you write the shared parts **once**, in a
**superclass**, and have other classes **extend** it:

```java
class Animal {
    protected final String name;
    protected final int age;

    Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    String describe() {
        return name + ", aged " + age;
    }
}

class Dog extends Animal {
    Dog(String name, int age) {
        super(name, age);
    }
    String sound() { return "Woof"; }
    void fetch() { System.out.println(name + " fetches the stick!"); }
}
```

`Dog` is a **subclass** of `Animal` (also called a child class or
derived class). It **inherits** every field and method of `Animal`, so
`rex.describe()` works even though `Dog` never defines `describe`, and
it **adds** its own: `sound` and `fetch`.

The test for whether inheritance fits is the phrase **"is a"**: a Dog
*is an* Animal; a Savings Account *is an* Account; a Professor *is an*
Employee. If the phrase sounds wrong, inheritance is the wrong tool (see
"Composition" below).

A Java class can extend only **one** superclass, but chains can be as
long as you like: a `Trotro` is a `Car`, which is a `Vehicle`.

## Constructors and `super(...)`

A subclass object is built in **layers**: the superclass part first,
then the subclass part. So a subclass constructor must start by calling
the superclass constructor with `super(...)`:

```java
class Car extends Vehicle {
    private final int seats;

    Car(String registration, int seats) {
        super(registration, 4);     // MUST come first: build the Vehicle part
        this.seats = seats;         // then the Car part
    }
}
```

If you leave out `super(...)`, Java tries to call the superclass's
constructor with no arguments, and if there isn't one, you get a
compile error: "constructor Vehicle in class Vehicle cannot be applied
to given types". [`examples/Ex02ExtendsAndSuper.java`](examples/Ex02ExtendsAndSuper.java)
prints a message from each constructor so you can watch the layers
being built, from `Vehicle` down to `Trotro`.

## Overriding: changing inherited behaviour

A subclass can **override** an inherited method: provide its own
version, with exactly the same name, parameters and return type:

```java
class Manager extends Employee {
    private final double allowance;
    ...
    @Override
    double monthlyPay() {
        return super.monthlyPay() + allowance;   // the original, plus a bit
    }
}
```

- **`@Override`** asks the compiler to check that you really are
  overriding something. Misspell the method's name, or get a parameter
  type wrong, and you get an error instead of a silent new method. Always
  use it.
- **`super.monthlyPay()`** calls the superclass's version. Use it when
  the subclass wants to **extend** the behaviour rather than replace it
  completely, as `Ring.draw` does in the hook.
- You've already done this: every `toString` you've written overrides
  the one every class inherits (more on that below).

There's a subtle and important point in
[`examples/Ex03Overriding.java`](examples/Ex03Overriding.java):
`Employee.payslip()` calls `monthlyPay()`. For a `Manager` object, that
call runs **Manager's** `monthlyPay`, even though the call is written
inside `Employee`'s code. Java always runs the version belonging to the
object's **actual** class. That's the engine behind polymorphism.

## Polymorphism

A variable of a **superclass** type can hold an object of **any
subclass**:

```java
Animal pet = new Dog("Rex");     // an Animal variable holding a Dog
System.out.println(pet.speak()); // Rex says Woof!
pet = new Cat("Tom");            // the same variable, now holding a Cat
System.out.println(pet.speak()); // Tom says Meow.
```

When you call a method, Java looks at the **object** (Dog, Cat), not
the **variable** (Animal), to decide which version to run. This is
called **dynamic dispatch**. It means you can write code that works with
*every* kind of Animal, including kinds that don't exist yet:

```java
static void chorus(List<Animal> animals) {
    for (Animal a : animals) {
        System.out.println(a.speak());
    }
}
```

The variable's type still matters for one thing: it decides **which
methods you're allowed to call**. Through an `Animal` variable, you can
only call methods that `Animal` declares. `pet.fetch()` doesn't compile,
even when `pet` holds a Dog, because not every Animal can fetch. See
[`examples/Ex04Polymorphism.java`](examples/Ex04Polymorphism.java).

## Abstract classes

In the hook, what would a plain `Shape` look like if you drew it?
There's no such thing. `Shape` is an *idea*: the shared part of every
real shape. Mark it **`abstract`**:

```java
abstract class Shape {
    private final String name;

    Shape(String name) { this.name = name; }

    abstract double area();          // no body: every subclass MUST provide one
    abstract double perimeter();

    String describe() {              // a normal method can USE the abstract ones
        return String.format("%-10s area %7.2f", name, area());
    }
}
```

- You **can't create** an object of an abstract class:
  `new Shape("blob")` is a compile error.
- An **abstract method** has no body, just a semicolon. Every
  (non-abstract) subclass **must** override it, or it won't compile.
  That's a guarantee: anything that is a `Shape` definitely has an
  `area()`.
- An abstract class can still have fields, constructors and normal
  methods, which subclasses inherit.

See [`examples/Ex05AbstractClasses.java`](examples/Ex05AbstractClasses.java),
where `Rectangle`, `Circle` and `Triangle` each work out their own area,
and one loop adds them all up.

## Access modifiers: who can see what

| Modifier | Visible to |
|---|---|
| `private` | Only code inside the same class |
| *(none)* | Code in the same **package** (for now: the same folder) |
| `protected` | The same package, **plus subclasses** anywhere |
| `public` | Everyone |

Subclasses **can't** see their superclass's `private` fields directly.
Usually that's fine: they use the superclass's methods (or `super(...)`
in the constructor). Use `protected` when subclasses genuinely need
direct access to a field, as `balance` does in
[`examples/Ex06AccessModifiers.java`](examples/Ex06AccessModifiers.java).
The rule of thumb stays the same as Module 10: fields `private` unless
there's a reason, methods `public` when other classes need them.

## `Object`: the class at the top of every family

Every class you write **secretly extends `Object`**. `class Dog` really
means `class Dog extends Object`. So every object in Java inherits a few
methods from `Object`, including:

| Method | `Object`'s version | Override it to… |
|---|---|---|
| `toString()` | the class name and a memory code | describe the object (Module 10) |
| `equals(Object other)` | the same as `==` (the same object?) | compare **data** |
| `hashCode()` | a number based on the memory address | match your `equals` |

This is why `println` works with every object (it calls `toString`), why
a method parameter of type `Object` accepts anything, and why the checks
in Module 8 could compare any two values with `.equals`.

To make two objects with the same data count as equal, override
`equals`, and **always** override `hashCode` with it:

```java
@Override
public boolean equals(Object other) {
    if (this == other) return true;
    if (!(other instanceof Point)) return false;
    Point p = (Point) other;
    return x == p.x && y == p.y;
}

@Override
public int hashCode() {
    return Objects.hash(x, y);     // built from the SAME fields as equals
}
```

`HashSet` and `HashMap` (Module 7) use `hashCode` to decide where to
store an object, and `equals` to check for duplicates. If equal objects
had different hash codes, a `HashSet` would happily store "duplicates"
in different places. The rule: **equal objects must have equal hash
codes**. `Objects.hash(...)` builds a good one from your fields. See
[`examples/Ex07ObjectClass.java`](examples/Ex07ObjectClass.java).

## `instanceof` and casting

Occasionally you have a superclass variable and need to know what kind
of object it holds. `instanceof` asks:

```java
for (Device d : devices) {
    if (d instanceof Phone phone) {        // is it a Phone? If so, call it phone
        phone.call("Ama");                 // now Phone's methods are available
    }
}
```

The `Phone phone` part (Java 16+) both tests and gives you a variable of
the subclass type. In older code you'll see the two-step version: test
with `instanceof`, then **cast** with `(Phone) d`. A cast to the wrong
type compiles but crashes with a `ClassCastException`. See
[`examples/Ex08InstanceofAndCasting.java`](examples/Ex08InstanceofAndCasting.java).

Use `instanceof` sparingly. If you find yourself writing
`if (x instanceof A) … else if (x instanceof B) …` to decide how to do
something, that "something" should usually be an overridden method
instead. That's exactly what the hook avoids.

## Composition, and `final`

Inheritance is powerful, but it's not the only way to reuse code, and
often not the best. The alternative is **composition**: give a class a
field holding another object, and let it do part of the work. The test
is **"has a"**: a Car *has an* Engine; it isn't one.

```java
class Car {
    private final Engine engine = new Engine(132);
    void start() {
        engine.start();       // delegate the job to the part that knows how
    }
}
```

`class Car extends Engine` would compile, but it would be nonsense:
every Engine method would become a Car method. **Prefer composition**
unless "is a" is clearly true.

Finally, **`final`** stops inheritance:

- A `final` **class** can't be extended. `String` is final, so nobody
  can make a "broken String".
- A `final` **method** can't be overridden.

See [`examples/Ex09CompositionVsInheritance.java`](examples/Ex09CompositionVsInheritance.java),
and then [`examples/Ex10PayrollReport.java`](examples/Ex10PayrollReport.java),
which puts the module together: an abstract `Employee`, three kinds of
staff paid in three different ways, and one polymorphic loop that
prints the whole payroll.

## Common beginner mistakes

- **Forgetting `super(...)`**, or not putting it first in the
  constructor.
- **Misspelling an overriding method** (`tostring`, `ToString`) without
  `@Override`, which creates a new method that is never called. Always
  write `@Override`.
- **Trying to use a subclass method through a superclass variable.**
  The variable's type decides which methods are available.
- **Using inheritance for "has a".** A `Library` has books; it isn't a
  `List`.
- **Overriding `equals` without `hashCode`**, so `HashSet` and
  `HashMap` misbehave.
- **`equals(Point other)` instead of `equals(Object other)`.** That
  *overloads* rather than overrides, and collections never call it.
  `@Override` catches this too.
- **Long `instanceof` chains** where an overridden method would do.
- **Trying to create an object of an abstract class.**

## Try it yourself

1. Run every file in [`examples/`](examples/). In the hook, add a new
   kind of shape (a `Heart`? a `Hexagon`? a `Smiley`?) and put a few in
   the parade.
2. Complete [`exercises/Exercise1.java`](exercises/Exercise1.java) and
   [`exercises/Exercise2.java`](exercises/Exercise2.java).
3. Build the [module project](project/README.md) in the track of your
   choice: a staff payroll, AC circuit components, or a medical device
   fleet.
4. Commit and push your work:

   ```bash
   git add .
   git commit -m "Complete Module 11: inheritance and polymorphism"
   git push
   ```

Next: **[Module 12: Interfaces, Enums & Records](../12-interfaces-enums-and-records/README.md)**.
