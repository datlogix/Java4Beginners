# Module 10 Project: Model Your World with Classes

Pick **one** track. In each, you'll design a small set of classes that
work together, where one class holds a collection of objects of another,
and a menu program that uses them. This is how almost all large software
is organised.

Each track's starter is a **folder of files**, one public class per
file, compiled and run like the multi-file example in the lesson:

```bash
cd generic_starter          # or ee_starter, or biomedical_starter
javac -d out *.java
java -cp out LibraryApp     # or CircuitApp, or WardApp
```

## What every track must do

1. **At least three classes**, as listed for your track, **each in its
   own file**. Each class has a Javadoc comment, a constructor, and a
   `toString`.
2. **Every field is `private`**, and `final` unless it genuinely needs
   to change.
3. **Constructors validate their arguments** and throw
   `IllegalArgumentException` with a clear message for anything invalid.
   It should be impossible to create a nonsense object.
4. **One class holds a collection** (a `List` or `Map`) of objects of
   another class, and provides methods to add, find, and remove them.
5. **All changes go through methods.** The menu code never changes an
   object's fields directly, and never reaches into another class's
   collection. (`library.lend(isbn, id)` is how a book goes on loan;
   `book.setOnLoan(true)` inside `LibraryApp` is not allowed.)
6. **Methods that answer questions return values** (`canBorrow`,
   `overloaded`, `needsAttention`). Only the `...App` class prints for
   the user.
7. A **menu-driven `main`** that creates the objects, calls their
   methods, and catches the `IllegalArgumentException`s they throw to
   show friendly messages. The starter's `try`/`catch` already does this:
   Module 13 explains exactly how it works.
8. **Starting data:** create a few objects at start-up so the program
   can be demonstrated straight away.

**Stretch (all tracks):** add a `static` counter that gives each new
object an automatic ID (`M001`, `M002`…, or `R1`, `R2`…), and a menu
option that lists every object in a sorted order.

## Track A: Generic, Library System

| Class | Knows | Can do |
|---|---|---|
| `Book` | ISBN, title, author, whether it's on loan | `toString` showing availability |
| `Member` | member ID, name, the books they have borrowed (a list), a borrowing limit of 3 | `canBorrow()` |
| `Library` | a map of books by ISBN, a map of members by ID | `addBook`, `addMember`, `lend(isbn, memberId)`, `returnBook(isbn, memberId)`, `search(text)` (title or author), `report()` (counts of books, loans, members) |

`lend` must refuse (with `IllegalArgumentException`) if the book is
already on loan, the member has reached the limit, or either the ISBN or
the member ID doesn't exist. `returnBook` must refuse a book that member
doesn't have.

## Track B: EE, Series Circuit Analyser

| Class | Knows | Can do |
|---|---|---|
| `Resistor` | a label (`R1`), resistance (Ω), power rating (W, e.g. 0.25) | `toString` like `R1: 4700 ohms (0.25 W)` |
| `PowerSupply` | voltage, maximum current | `canSupply(current)` |
| `Circuit` | a power supply and a list of resistors in series | `add(resistor)`, `remove(label)`, `totalResistance()`, `current()`, `voltageAcross(label)`, `powerIn(label)`, `overloaded()` (resistors dissipating more than their rating), `analyse()` (returns a multi-line report) |

Reject resistances ≤ 0, power ratings ≤ 0, duplicate labels, and
supplies with a voltage ≤ 0. `current()` should throw
`IllegalArgumentException` if the circuit has no resistors. The report
should list every resistor's voltage and power, and warn if the current
exceeds the supply's maximum or any resistor is overloaded. **Check your
analyser by hand** for one circuit: 12 V across 100 Ω + 220 Ω gives
37.5 mA, 3.75 V across R1, and 0.14 W in R1.

## Track C: Biomedical, Ward Monitor

| Class | Knows | Can do |
|---|---|---|
| `VitalReading` | time label, heart rate, SpO₂ (%), temperature (°C) | `problems()`: a list of reasons it's abnormal (HR < 60 or > 100, SpO₂ < 94, temperature ≥ 38.0) |
| `Patient` | patient ID, name, age, bed number, a list of `VitalReading`s | `addReading(reading)`, `latest()`, `averageHeartRate()`, `needsAttention()` (latest reading has problems) |
| `Ward` | name, number of beds, a map of patients by ID | `admit(patient)` (refuse if the ward is full or the bed is taken), `discharge(patientId)`, `record(patientId, reading)`, `alerts()` (patients needing attention), `report()` |

Reject impossible values in `VitalReading` (heart rate outside 20–250,
SpO₂ outside 50–100, temperature outside 30–45 °C), ages outside 0–120,
and bed numbers outside 1 to the number of beds.

> Thresholds are simplified for teaching. This is not a clinical tool.

## Ideas if you're stuck

- **Write the smallest class first** (`Book`, `Resistor`,
  `VitalReading`) and test it with a few lines in a temporary `main`
  before writing the next one.
- If several methods need to find an object by ID or label, write one
  `private` helper method (`find(label)`) that returns it or throws, and
  reuse it everywhere.
- A method that returns a list from inside an object should return a
  **copy** (`new ArrayList<>(borrowed)`), so callers can't change the
  object's data behind its back. The starter's `Member.getBorrowed`
  shows this.
- Compile errors in one file stop *all* the files compiling. Fix the
  first error, recompile, repeat, exactly as in Module 1.

## Done?

```bash
git add .
git commit -m "Complete Module 10 project: classes and objects"
git push
```
