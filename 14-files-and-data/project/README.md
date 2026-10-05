# Module 14 Project: Programs That Remember

Pick **one** track. Each one reads real data from a **CSV file**,
survives the broken lines that real data always contains, works things
out, and **writes results to new files**.

```bash
cd generic_starter          # or ee_starter, or biomedical_starter
javac -d out *.java
java -cp out ExpenseApp     # or BenchApp, or VitalsApp
```

Run the program **from the starter folder**, so that the relative path
`data/...` points at the data folder.

## What every track must do

1. A **record** for one row of data, with a `fromCsv(line)` method that
   throws `IllegalArgumentException` with a clear reason for a broken
   line, and (where the program writes rows) a `toCsv()` method.
2. A class that **reads** the file. Broken lines are **skipped and
   reported** (`line 8: amount 'twelve' isn't a number`), never allowed to
   crash the program. A missing file gets a friendly message.
3. Every file is opened with **`Files`** methods or in
   **try-with-resources**, and every `IOException` is handled.
4. The program **writes** at least one output file: a report, a cleaned
   CSV, or the data itself. Output files go in an `output` folder,
   created with `Files.createDirectories` if it's missing (except Track
   A's data file, which is saved in place).
5. File paths are **constants** built with `Path.of(...)`, never typed
   out in several places.
6. **Check your numbers.** Your program's results for the sample data
   must match the ones given below. If they don't, use the debugger.

**Stretch (all tracks):** let the user give the input file's path as a
command-line argument (`args[0]`), falling back to the sample file.

## Track A: Generic, Expense Tracker

`data/expenses.csv` holds two months of a student's spending:
`date,category,description,amount`.

1. `Expense` (a record) and `ExpenseStore` (load and save).
2. `ExpenseApp` loads the file at start-up, then offers: **add** an
   expense (validated: a date as `YYYY-MM-DD`, or blank for today; a
   positive amount; no commas in the description), **list a month**
   (`2026-09`) in date order, **summary by category** (totals and a
   percentage of all spending, biggest first), **monthly totals**,
   **delete** (by its number in the list), and **export** a month's
   report to `output/report-2026-09.txt`.
3. Every add or delete **saves immediately**, so quitting can never lose
   data. Prove it: add something, quit, and run the program again.

**Check:** the sample file has **2** broken lines. The rest total
GHS 1047.35 for August and GHS 723.26 for September. Books is the
biggest category, at GHS 659.15.

## Track B: EE, Bench Data Logger Analyser

`data/bench_log.csv` is a 10-minute battery discharge test, logged every
10 seconds: `time_s,voltage_V,current_A`. The load changed twice during
the test, and something went wrong around t = 260 s.

1. `Sample` (a record, with `watts()`), `LogReader` (reads line by line
   with a `BufferedReader`), and `Analyser`.
2. Report: the number of good and skipped samples, the minimum and
   maximum voltage, average current, peak power, and the **energy
   delivered** in Wh by the trapezium rule: for each pair of neighbouring
   samples, (P₁ + P₂) / 2 × (t₂ − t₁) joules.
3. List every **over-current** sample (current above 1.5 A), with its
   time.
4. Write `output/with_power.csv` (the good samples, plus a power column)
   and `output/summary.txt` (the report).

**Check:** 60 good samples and 2 skipped; voltage from 12.219 V to
12.593 V; average current 0.989 A; peak power 24.007 W; energy
**2.013 Wh**; over-current at **260 s and 270 s**.

## Track C: Biomedical, Patient Vitals Log

`data/vitals_log.csv` holds a day of two-hourly observations for four
patients: `patient_id,time,heart_rate,spo2,temperature`.

1. `Reading` (a record, with `problems()`: heart rate below 60 or above
   100, SpO₂ below 94, temperature 38.0 °C or above) and `VitalsLog`,
   which groups readings by patient in a `TreeMap<String, List<Reading>>`.
2. A summary table: for each patient, the number of readings, average
   heart rate, lowest SpO₂, highest temperature, and the number of
   abnormal readings.
3. Write `output/alerts.csv`, with one line per abnormal reading:
   `patient_id,time,reasons` (join several reasons with `; `), and
   `output/summary.txt`.
4. Spot the trend: for any patient with abnormal readings, say whether
   their **last** reading is better or worse than their **first**.

**Check:** 2 broken lines skipped; 8 good readings per patient. P002
(average heart rate 109.2, highest temperature 38.9 °C) and P004 (lowest
SpO₂ 88%) have 8 abnormal readings each; P001 and P003 have none.

> Thresholds are simplified for teaching. This is not a clinical tool.

## Ideas if you're stuck

- Print each line **before** you split it while you're developing.
  Then you can see exactly which line breaks your parsing.
- `"190,12.112,".split(",")` gives only **2** fields: `split` drops
  empty strings at the end. Check the number of fields before you use
  them.
- Get the program working with the **reading** half first (print the
  records), then add the calculations, then the writing.
- "No such file"? Print `Path.of("").toAbsolutePath()` to see which
  folder Java thinks you're in.

## Done?

```bash
git add .
git commit -m "Complete Module 14 project: files and data"
git push
```
