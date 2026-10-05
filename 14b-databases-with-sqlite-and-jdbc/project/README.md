# Module 14b Project: Your Module 14 Project, on a Database

Take the data from your Module 14 track and move it into an **SQLite
database**. Answer the same questions again, this time in **SQL**, and
check that you get the same answers as before.

Each track has one starter file, and the data files from Module 14 are
in `project/data/`. Run from the module folder:

```bash
java -cp "lib/*" project/ExpenseDb.java       # Track A
java -cp "lib/*" project/BenchDb.java         # Track B
java -cp "lib/*" project/VitalsDb.java        # Track C
```

## What every track must do

1. **A repository class** that holds the `Connection` and contains
   **all** the SQL. The rest of the program works with records and plain
   values, and never sees a `ResultSet`.
2. **A table with constraints**: a primary key, `NOT NULL` columns, and
   at least one `CHECK`.
3. **Import the CSV in one transaction**, skipping and reporting bad
   rows exactly as in Module 14, and rolling back if something goes
   badly wrong.
4. **A `PreparedStatement` for every value** that comes from the user or
   the file. Prove it: try a search like `' OR '1'='1` and show that it
   finds nothing.
5. **At least four queries that make the database do the work**: `WHERE`,
   `ORDER BY`, `LIMIT`, and at least one `GROUP BY` with `COUNT`, `SUM`,
   `AVG`, `MIN` or `MAX`.
6. **try-with-resources** for every `Connection`, `Statement`,
   `PreparedStatement` and `ResultSet`.
7. **The same answers as Module 14.** Print them, and say in your README
   which queries replaced which loops.

## Track A: Expense Tracker

The `expenses` table is created for you. Add: import; add and delete;
list a month (`2026-09`) in date order; totals by category, biggest
first; monthly totals (hint: `SUBSTR(date, 1, 7)`); the five biggest
expenses; and a description search with `LIKE`. The database file
persists, so on later runs, skip the import.

**Check:** 2 bad lines; August GHS 1047.35; September GHS 723.26; Books
is the biggest category at GHS 659.15.

## Track B: Bench Data Logger

Add: import; a one-query summary (count, minimum and maximum voltage,
average current, and peak power, with `volts * amps` worked out in SQL);
the over-current samples; and the energy by the trapezium rule.
**Stretch:** calculate the energy in a single SQL query with the window
function `LAG(...) OVER (ORDER BY time_s)`.

**Check:** 60 good samples, 2 skipped; 12.219 V to 12.593 V; average
current 0.989 A; peak power 24.007 W; energy 2.013 Wh; over-current at
260 s and 270 s.

## Track C: Patient Vitals

The `readings` table has `CHECK` constraints for impossible values. Add:
import; a one-query per-patient summary with `GROUP BY`; each patient's
abnormal readings with a `PreparedStatement`; and an export of every
alert to `output/alerts.csv`.

**Check:** 2 bad lines; 8 readings per patient; P002 (average heart rate
109.2, highest temperature 38.9 °C) and P004 (lowest SpO₂ 88%) have 8
abnormal readings each; P001 and P003 none.

> Thresholds are simplified for teaching. This is not a clinical tool.

## Ideas if you're stuck

- Try each query in a tiny `main` first, or in a database browser such
  as **DB Browser for SQLite** (free), which also lets you look inside the
  `.db` file.
- Build the SQL as a text block (`"""`), so it can span several lines.
- `ResultSet` columns can be read by name (`r.getDouble("amps")`) or by
  position, counting from 1.

## Done?

```bash
git add .
git commit -m "Complete Module 14b project: SQLite and JDBC"
git push
```

Add `*.db` to your `.gitignore`: the database is rebuilt from the CSV,
so it doesn't belong in Git.
