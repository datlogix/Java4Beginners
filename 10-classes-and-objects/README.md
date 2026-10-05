# Module 10: Classes & Objects

## Hook: adopt a virtual pet (or two)

Run [`examples/Ex00VirtualPet.java`](examples/Ex00VirtualPet.java):

```bash
java Ex00VirtualPet.java
```

```
Name your first pet: Kwaku
Name your second pet: Adjoa

Kwaku    hunger [###       ]  happiness [#######   ]  content
Adjoa    hunger [###       ]  happiness [#######   ]  content

Who do you want to look after? (1/2, or q to quit) 1
What will you do with Kwaku? (f)eed or (p)lay: p
Kwaku chases a ball around the yard!
```

Feed them, play with them, and ignore one of them for a while. Every
turn, both pets get hungrier and a little less happy, but **each pet
keeps track of its own hunger and happiness**. Play with Kwaku all day
and Adjoa sulks. Feed Adjoa and Kwaku starves.

Here's how a pet is described in the code:

```java
static class Pet {
    String name;
    int hunger = 3;
    int happiness = 7;

    Pet(String name) {
        this.name = name;
    }

    void feed() {
        hunger = Math.max(0, hunger - 3);
        System.out.println(name + " munches happily. Yum!");
    }
    ...
}
```

`class Pet` defines a brand-new **type**, just as `String` and
`ArrayList` are types. It bundles a pet's **data** (name, hunger,
happiness) with the **behaviour** that changes it (feed, play). Then
`new Pet("Kwaku")` and `new Pet("Adjoa")` create two separate
**objects** of that type, each with its own data. Java is an
**object-oriented** language: designing classes like this is what Java
programmers spend most of their time doing. By the end of this module,
you'll design your own classes to model a library, a circuit, or a
hospital ward.

## You've been using objects all along

Every `String`, `Scanner`, `Random` and `ArrayList` you've used is an
**object**, and you've been calling their **methods** since Module 3:

```java
String name = "kwame";
name.toUpperCase();                    // a String object's method
Scanner in = new Scanner(System.in);   // new creates a Scanner object...
in.nextLine();                         // ...which has a nextLine method
ArrayList<Integer> scores = new ArrayList<>();
scores.add(91);                        // THIS list's add method
```

The dot means "the thing on the left's own…". `scores.add(91)` changes
`scores` and no other list. Each object carries its own **data** and its
own **methods**. So far you've only used classes other people designed.
Now you'll design your own.

## Classes and objects

A **class** is a **blueprint**. It describes what data every object of
that type has, and what it can do. An **object** (also called an
**instance**) is one actual thing built from the blueprint:

```java
class Dog {
    String name;          // FIELDS: the data every Dog has
    String breed;
    int age;
}

Dog rex = new Dog();      // new builds an object from the blueprint
rex.name = "Rex";         // the dot reaches into THIS object's fields
Dog bingo = new Dog();    // a second, completely separate object
bingo.name = "Bingo";
```

The variables declared inside the class, but outside any method, are
its **fields** (also called instance variables). Every object gets its
own copy of every field, starting at the default values from Module 6
(`0`, `false`, `null`) unless you give them a value.

Class names use **CapitalisedWords** (`Dog`, `BankAccount`,
`VitalReading`), unlike the camelCase used for variables and methods. So
you can always tell `Pet` (a class) from `pet` (an object). See
[`examples/Ex01FirstClass.java`](examples/Ex01FirstClass.java).

### One class per file (and why the examples cheat)

In real Java programs, **each class goes in its own file**, named after
the class: `Dog` lives in `Dog.java`, marked `public class Dog`. The
lesson examples break this rule for convenience: each example file
keeps its classes *inside* the main class, marked `static class`, so
that `java Ex01FirstClass.java` still runs it as a single file. In your
projects, use one file per class, as in
[`examples/multi-file/`](examples/multi-file/), and compile and run them
in two steps:

```bash
cd examples/multi-file
javac -d out *.java          # compile every .java file; put the .class files in out/
java -cp out BankApp         # run the class with main; -cp out says where to find classes
```

The Java `.gitignore` you chose in Module 0 tells Git to ignore
`.class` files, so compiled code never clutters your repository. In VS Code, the ▶ button
on `BankApp.java` does all of this for you.

## Constructors: setting up a new object

Setting every field by hand after `new` is tedious and easy to forget.
A **constructor** is a special method that runs automatically when an
object is created:

```java
class Dog {
    String name;
    String breed;
    int age;

    Dog(String name, String breed, int age) {
        this.name = name;          // this.name is the FIELD; name is the PARAMETER
        this.breed = breed;
        this.age = age;
    }
}

Dog rex = new Dog("Rex", "Labrador", 3);
```

- A constructor has **the same name as the class** and **no return
  type** (not even `void`).
- **`this`** means "the object being created (or used) right now".
  When a parameter has the same name as a field, `this.name` is the
  field and plain `name` is the parameter.
- Constructors can be **overloaded**, and one can call another with
  `this(...)`. In [`examples/Ex02Constructors.java`](examples/Ex02Constructors.java),
  `new Dog("Patch", 1)` calls the three-argument constructor with
  `"mixed breed"`.

If you write no constructor at all, Java gives you an empty one for
free. As soon as you write one, the free one disappears, so
`new Dog()` stops compiling. That's usually what you want: it forces
everyone to supply the data a Dog needs.

## Instance methods: behaviour

The methods in Module 8 were all `static`. Methods *without* `static`
are **instance methods**: they belong to each object, and work on that
object's fields:

```java
class Counter {
    String label;
    int count;

    Counter(String label) {
        this.label = label;
    }

    void click() {
        count++;              // THIS counter's count
    }

    boolean isOver(int limit) {
        return count > limit;
    }
}

Counter visitors = new Counter("Visitors");
Counter cars = new Counter("Cars");
visitors.click();             // changes visitors.count, not cars.count
```

Inside an instance method, a field's name on its own means "this
object's field". You can write `this.count++` if you want to be
explicit. An instance method is always called **on an object**:
`visitors.click()`. That's the whole difference from `static`: a static
method like `Math.sqrt` belongs to the class and doesn't need an object;
an instance method needs one, because it works on that object's data.
See [`examples/Ex03Methods.java`](examples/Ex03Methods.java).

## `toString`: how an object prints

Print an object of your own class and you get something like
`Ex04ToString$Plain@7bc1a03d`: the class name and a code for where it
lives in memory. Add a `toString` method, and Java uses it **whenever
the object is turned into text**: in `println`, when it's joined with
`+`, and when a list of them is printed:

```java
@Override
public String toString() {
    return "(" + x + ", " + y + ")";
}
```

It must be written exactly like this: `public`, returning a `String`,
with no parameters. `@Override` tells Java "I'm replacing the standard
version every object has". Module 11 explains where that standard
version comes from. Give every class you write a `toString`: it makes
debugging enormously easier. See
[`examples/Ex04ToString.java`](examples/Ex04ToString.java).

## Encapsulation: objects that protect themselves

With public fields, anyone can put nonsense into an object:
`account.balance = -1_000_000;`. **Encapsulation** means hiding an
object's data and only allowing changes through methods that check
them:

```java
class BankAccount {
    private final String owner;
    private double balance;

    BankAccount(String owner, double openingBalance) {
        if (openingBalance < 0) {
            throw new IllegalArgumentException("Opening balance can't be negative.");
        }
        this.owner = owner;
        this.balance = openingBalance;
    }

    double getBalance() {             // a "getter": read-only access
        return balance;
    }

    void withdraw(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds.");
        }
        balance -= amount;
    }
}
```

- **`private`** means only code inside this class can use the field.
  `account.balance = 1_000_000;` elsewhere is a compile error: "balance
  has private access".
- **`final`** on a field means it's set once, in the constructor, and
  never changes. Use it for everything that shouldn't change, like an
  owner's name or an ID.
- **Getters** (`getBalance()`) give read access. Only add a **setter**
  (`setBalance(...)`) if outside code genuinely needs to change a value,
  and make it check the new value. Often, as here, a meaningful method
  like `withdraw` is better than a setter.
- **`throw new IllegalArgumentException("message")`** stops the method
  immediately and reports the problem. If nothing catches it, the
  program crashes with that message, which is far better than silently
  carrying on with bad data.

The rule of thumb: **make every field private**, and design methods
that keep the object valid at all times. To turn a thrown exception into
a friendly message instead of a crash, the caller wraps the call in
`try`/`catch`:

```java
try {
    account.withdraw(500);
} catch (IllegalArgumentException e) {
    System.out.println("Refused: " + e.getMessage());
}
```

That's all you need for now. Module 13 covers exceptions in depth. See
[`examples/Ex05Encapsulation.java`](examples/Ex05Encapsulation.java).

## `static` members: shared by the whole class

Sometimes data belongs to the class as a whole, not to any one object:
a counter of how many tickets have been sold, or a constant price. Mark
it `static`, and there's just **one copy**, shared by every object:

```java
class Ticket {
    private static int nextNumber = 1;    // one copy for the whole class
    static final double PRICE = 15.0;

    private final int number;             // every ticket has its own

    Ticket(String holder) {
        this.number = nextNumber;
        nextNumber++;
    }

    static int ticketsSold() {
        return nextNumber - 1;
    }
}

Ticket.ticketsSold()     // called on the CLASS, not on a ticket
```

That's what `Math.PI`, `Math.sqrt` and `Integer.MAX_VALUE` are: static
members of a class. A static method can't use instance fields directly,
because it isn't running on any particular object. See
[`examples/Ex06StaticMembers.java`](examples/Ex06StaticMembers.java).

## Objects are references

Exactly like arrays in Module 6, a variable of a class type holds a
**reference** to an object, not the object itself:

```java
Lamp kitchen = new Lamp("kitchen");
Lamp sameLamp = kitchen;        // two names, ONE object
sameLamp.on = true;
System.out.println(kitchen.on); // true
```

- Passing an object to a method passes a copy of the reference, so the
  method can change the object.
- `==` asks "are these the **same object**?" Two different objects with
  identical data are not `==`. (Module 11 shows how to write an
  `equals` method that compares data.)
- **`null`** means "no object at all". Calling a method or reading a
  field through a `null` reference crashes with a
  `NullPointerException`. The message names the variable or expression
  that was `null`, which is your first clue. Methods often return `null`
  to mean "not found", so check before you use the result.

See [`examples/Ex07ReferencesAndNull.java`](examples/Ex07ReferencesAndNull.java).

## Objects working together

Real programs are made of objects that **contain** other objects. A
`Course` *has* a list of `Student`s; a `Library` *has* a map of
`Book`s. This is called **composition**:

```java
class Course {
    private final String code;
    private final List<Student> students = new ArrayList<>();

    void enrol(Student s) { ... }
    Student find(String id) { ... }      // returns null if there's no such student
    double average() { ... }
    Student top() { ... }
}
```

Each class has **one job**: a `Student` knows about itself; a `Course`
manages a group of students. The code that uses them reads almost like
English: `course.enrol(new Student("S01", "Akua", 78))`,
`course.top()`. See [`examples/Ex08ObjectsTogether.java`](examples/Ex08ObjectsTogether.java).

A list of objects also fixes the problem with parallel arrays from
Module 6. Each product's name, price and stock live together inside one
`Product` object, so sorting the list can never separate them. See
[`examples/Ex09ObjectsInLists.java`](examples/Ex09ObjectsInLists.java),
and [`examples/Ex10Room.java`](examples/Ex10Room.java), which rebuilds
the Module 9 text adventure with a `Room` class: each room's exits lead
directly to other `Room` objects, and the four separate maps disappear.

## Designing a class

When you design a class, answer three questions:

1. **What does it know?** Those are its fields. Make them `private`,
   and `final` where possible.
2. **What can it do?** Those are its methods. Name them after what they
   achieve (`lend`, `withdraw`, `needsAttention`), not how.
3. **What must always be true?** A balance is never negative; a mark is
   0–100; a bed number exists on the ward. Those rules go in the
   constructor and the methods, so the object enforces them itself.

## Common beginner mistakes

- **Forgetting `new`.** `Dog rex;` creates a variable, not a dog.
  `rex.name = "Rex";` then fails ("might not have been initialized").
- **Giving a constructor a return type.** `void Dog(String name)` is a
  *method* called `Dog`, not a constructor, so the object never gets set
  up.
- **Forgetting `this.`** when a parameter has the same name as a field.
  `name = name;` assigns the parameter to itself, and the field stays
  `null`.
- **Calling an instance method from `static main` without an object.**
  "non-static method cannot be referenced from a static context" means
  you need an object first: `pet.feed()`, not `feed()`.
- **Public fields.** Make them `private`, and provide only the methods
  that are really needed.
- **A file name that doesn't match its public class**, or two public
  classes in one file.
- **Comparing objects with `==`** when you mean "same data".
- **Using a `null` result**, such as from `find`, without checking it
  first.

## Try it yourself

1. Run every file in [`examples/`](examples/), and compile and run the
   [`multi-file`](examples/multi-file/) example with `javac` and `java`.
   In the hook, give `Pet` a third ability (`sleep`?) and a new mood.
2. Complete [`exercises/Exercise1.java`](exercises/Exercise1.java) and
   [`exercises/Exercise2.java`](exercises/Exercise2.java).
3. Build the [module project](project/README.md) in the track of your
   choice: a library, a circuit analyser, or a ward monitor.
4. Commit and push your work:

   ```bash
   git add .
   git commit -m "Complete Module 10: classes and objects"
   git push
   ```

Next: **[Module 11: Inheritance & Polymorphism](../11-inheritance-and-polymorphism/README.md)**.
