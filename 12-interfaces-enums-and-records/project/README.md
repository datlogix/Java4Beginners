# Module 12 Project: Contracts, Categories and Data

Pick **one** track. Each one combines this module's three tools:
**interfaces** (a contract that several classes, or lambdas, fulfil),
**enums** (a fixed set of categories that carry their own data), and
**records** (plain, unchangeable data), plus **`Comparator`s** to put
things in order.

```bash
cd generic_starter          # or ee_starter, or biomedical_starter
javac -d out *.java
java -cp out MediaApp       # or LogicApp, or TriageApp
```

## What every track must do

1. **At least one interface** that more than one class (or lambda)
   implements, and code that works through the interface type without
   knowing which implementation it has.
2. **At least one enum with fields, a constructor and a method**, used
   in a `switch` or a loop over `values()`.
3. **At least two records**, with compact constructors that reject
   invalid data.
4. **At least three `Comparator`s** built with `Comparator.comparing…`,
   `reversed()` and `thenComparing`, chosen from the menu or used inside
   your classes. No hand-written `compare` methods with `if`s, except
   where a `Comparable` natural order is needed.
5. At least one **lambda** you wrote yourself (beyond `Comparator`s).
6. A menu-driven app with starting data and friendly error messages,
   as in Modules 10 and 11.

**Stretch (all tracks):** use an `EnumMap` to count or group things by
an enum value, and print the groups in the enum's order.

## Track A: Generic, Media Library

1. `Genre` is an enum with a display name. Add at least six genres,
   including some for podcasts and audiobooks.
2. `Playable` is an interface: `title()`, `genre()`, `durationSeconds()`
   and a default `durationText()` (`3:05`, or `1:02:05`). A record's
   accessor methods (`title()`…) automatically satisfy the interface.
3. Three records implement `Playable`: `Song` (given), `PodcastEpisode`
   (show name and episode number) and `Audiobook` (author and narrator).
4. `MediaLibrary` holds a `List<Playable>` and a `Map<Playable,
   Integer>` of star ratings, with the three `Comparator` constants,
   `sorted(order)`, `inGenre(genre)`, `rate(title, stars)`,
   `topRated(n)` and `totalSeconds()`.
5. The app lets the user choose the sort order, browse by genre, rate
   items, and see the top-rated list with stars (`*****`).

## Track B: EE, Logic Gate Simulator

1. `LogicGate` is a functional interface: `boolean apply(boolean a,
   boolean b)`.
2. `GateType` is an enum that **implements** `LogicGate`. Each value
   carries its own lambda and Boolean expression. Add `XOR`, `NAND`,
   `NOR` and `XNOR`, and write `truthTable()`, printing 0s and 1s.
3. `HalfAdder.add(a, b)` returns an `AddResult` record (sum, carry)
   using `GateType.XOR` and `GateType.AND`, not Java's own `^` and `&&`.
4. Write `FullAdder.add(a, b, carryIn)`: two half adders and an OR gate.
5. Write `RippleCarryAdder`, which adds two numbers bit by bit with a
   chain of full adders (4 bits by default; the bit count is a
   constructor argument). Its `add(x, y)` returns a record with the
   result and an `overflow` flag. Hint: `(x >> i) & 1` gives bit `i` of
   `x`.
6. The self-test compares your 4-bit adder with Java's `+` for **all
   256** pairs from 0 to 15, and prints how many matched (it should be
   all of them, with overflow correctly flagged when the answer is over
   15).

## Track C: Biomedical, Emergency Triage

1. `TriageLevel` is an enum (`RED`, `ORANGE`, `YELLOW`, `GREEN`) with a
   description and a maximum wait in minutes. Its declaration order is
   its urgency order.
2. `VitalSigns` and `Patient` are records with validation.
3. `TriageRule` is a functional interface. Write a class `ScoreTriage
   implements TriageRule` that gives points for each abnormal sign and
   turns the total into a level:

   | Sign | 0 points | 1 point | 2 points | 3 points |
   |---|---|---|---|---|
   | Heart rate | 51–100 | 41–50 or 101–110 | 111–129 | ≤ 40 or ≥ 130 |
   | Respiratory rate | 9–14 | 15–20 | 21–29 or ≤ 8 | ≥ 30 |
   | Systolic BP | 101–199 | 81–100 | 71–80 or ≥ 200 | ≤ 70 |
   | Temperature (°C) | 35.0–38.4 | | < 35.0 or ≥ 38.5 | |
   | SpO₂ (%) | ≥ 96 | 94–95 | 92–93 | ≤ 91 |
   | Alert? | yes | | | no (3 points) |

   A total of **7 or more** is RED, **5–6** ORANGE, **3–4** YELLOW, and
   anything less GREEN. (Kofi Boateng in the starter scores 2 + 2 + 1 +
   2 + 3 + 0 = 10: RED.)
4. `TriageQueue` admits patients (assessing them with whatever rule it
   was given), orders them by level and then arrival time, finds overdue
   patients, and counts patients by level with an `EnumMap`.
5. Show that the rule really is swappable: in the app, let the user
   switch to a second rule, written as a **lambda**, such as "anyone with
   SpO₂ below 90 is RED, everyone else is YELLOW".

> The scoring table is simplified for teaching. This is not a clinical
> tool.

## Ideas if you're stuck

- A record **is** a class. It can implement interfaces, have extra
  methods, and have a compact constructor, but its fields can never
  change. To "change" one, make a new one.
- `Comparator.comparing(Song::title)` needs a method that returns
  something `Comparable` (a `String`, a number, an enum). Enums compare
  in the order they're declared.
- If a lambda gets long, it's fine to write a small class that
  implements the interface instead. Both are instances of the
  interface.
- `GateType.valueOf("xor")` throws `IllegalArgumentException`. Use
  `valueOf(text.toUpperCase())`.

## Done?

```bash
git add .
git commit -m "Complete Module 12 project: interfaces, enums and records"
git push
```
