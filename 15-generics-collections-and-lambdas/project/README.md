# Module 15 Project: Asking Questions of Data

Pick **one** track. Each one loads a data file (your Module 14 skills),
then answers questions about it with **streams**, and includes a
**generic class** of your own that would work just as well for any other
kind of data.

```bash
cd generic_starter          # or ee_starter, or biomedical_starter
javac -d out *.java
java -cp out FilmClubApp    # or InventoryApp, or ClinicApp
```

## What every track must do

1. **A generic class** (the starter names it: `Page<T>`,
   `Inventory<T extends StockItem>` or `Histogram<T extends
   Comparable<T>>`), with every TODO completed. Prove it's really generic
   by using it with **two different types** somewhere in your program.
2. **At least eight queries**, each written as **one stream pipeline**,
   using between them: `filter`, `map`, `sorted` with a `Comparator`,
   `limit`, `distinct`, a terminal operation that gives a number
   (`count`, `sum`, `average`, `max`), and **at least two** different
   `Collectors` (`groupingBy`, `counting`, `averagingDouble`,
   `summingDouble`, `joining`, `partitioningBy`).
3. **At least one method that takes a lambda** (a `Predicate`,
   `Function` or `Comparator`) as a parameter, and is called with
   different lambdas.
4. At least one query that returns an **`Optional`**, handled properly
   (`orElse`, `ifPresentOrElse` or `isPresent`) so an empty result never
   crashes.
5. A menu-driven app. Results are printed neatly with `printf`.
6. **Check your answers** against the values below.

## Track A: Generic, Film Club Explorer

`data/movies.csv` is a film club's catalogue of 60 films: title, year,
genre, runtime, rating out of 10, and country.

1. Complete `Page<T>`, which splits any list into pages, and show every
   long result 10 at a time (`n` for the next page, `q` to stop). Use
   `Page` for something other than films too, such as the list of
   genres.
2. Complete `MovieQueries`: best rated first, search titles, a genre's
   films newest first, the number of films per genre, the average rating
   per decade, the longest film from a country (an `Optional`), and the
   **film night** planner: the best-rated films that fit in the time
   available.
3. Add at least one query of your own, such as "the best film of each
   decade" or "countries with at least three films".

**Check:** Animation is the biggest genre, with 12 films. The best-rated
film is *The Northern Song* (9.3).

## Track B: EE, Parts Inventory Query Engine

`data/parts.csv` is an electronics store: part number, type, value
(`4.7k`, `100n`, `NE555`), tolerance, quantity, reorder level, unit
price and shelf location.

1. Complete the generic `Inventory<T extends StockItem>`. The bound lets
   it call `quantity()` and `stockValue()` on any item. Show it working
   with a second kind of `StockItem` too, such as a `Tool` record (soldering
   irons, multimeters).
2. `Part.numericValue()` turns values like `4.7k` into numbers (reuse
   your Module 13 SI parser), and gives `NaN` for names like `NE555`.
3. Queries: the total stock value; the value of each type
   (`groupingBy`); the reorder list; **resistors between two values**,
   in order of value; the cheapest part of each type; everything on one
   shelf; and the parts making up most of the stock value.

**Check:** 43 parts, total stock value **GHS 34,714.07**, and **19**
parts at or below their reorder level.

## Track C: Biomedical, Clinic Flow Analyser

`data/visits.csv` is a day of 120 hospital visits: department, triage
level (1 is most urgent, 5 least), arrival time, minutes waited, and age.

1. Complete `Visit.targetMinutes()` (triage 1 → 0, 2 → 10, 3 → 60,
   4 → 120, 5 → 240) and the generic `Histogram<T>`. Use one
   `Histogram<String>` for departments and one `Histogram<Integer>` for
   triage levels.
2. A report: visits per department; the average and longest wait in
   each department; the percentage seen **on time**, overall and per
   triage level; the department with the worst average wait (an
   `Optional`); and the children (under 13) who waited longer than their
   target.
3. A **queue simulation**: put every visit that arrived before 10:00 in
   a `PriorityQueue<Visit>`, ordered by triage level and then arrival
   time, and print the order in which they'd be seen.

**Check:** 75 of the 120 visits (**62.5%**) were seen on time.
Emergency has the shortest average wait (24.5 minutes); Paediatrics the
longest (101.3 minutes).

> Targets are simplified for teaching. This is not a clinical tool.

## Ideas if you're stuck

- Build a pipeline **one step at a time**. Start with
  `list.stream().toList()`, print it, then add one step and print
  again.
- `groupingBy(key)` gives a `HashMap`, in no order. Pass `TreeMap::new`
  as the second argument to get the keys sorted, as in the examples.
- `mapToDouble(...).average()` gives an `OptionalDouble`, because an
  empty stream has no average. Use `.orElse(0)`.
- If a stream gets too clever to read, it's fine to break it into named
  steps, or to write a helper method with a clear name.

## Done?

```bash
git add .
git commit -m "Complete Module 15 project: generics, collections and streams"
git push
```
