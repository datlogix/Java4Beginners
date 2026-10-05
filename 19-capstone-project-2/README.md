# Module 19: Capstone Project 2

This is where Part 2 comes together: classes and objects, inheritance
and polymorphism, interfaces, enums and records, exceptions, files,
generics and streams, recursion, packages, Maven and JUnit, and (if you
choose) a Swing interface, combined into **one real application**, built
by a team, tested, documented, and tracked in Git.

Capstone 1 was a game that forgot everything when it closed. Capstone 2
is the kind of software people use at work: it **remembers** its data,
**refuses** to crash, **proves** it works with automated tests, and
**turns its data into charts**.

There are three tracks, as in every Part 2 project:

| Track | Project |
|---|---|
| **A: Generic** | **MakerStore**: an inventory and lending system for a makerspace |
| **B: Electrical/Electronic Engineering** | **SensorLab**: a multi-sensor data logger and analyser |
| **C: Biomedical Engineering** | **WardWatch**: a patient vitals monitor with early-warning alerts |

## 1. Teams

The rules are the same as Capstone 1:

| Class size | Group size | Ratio |
|---|---|---|
| Up to 20 students | Pairs | **2:1** |
| More than 20 students | Groups of 4 | **4:1** |
| Approved solo ("daring") students, any class size | Individual | **1:1** |

Solo students complete the same core requirements (§3) **plus every
stretch goal** for their track (§5). Ask your instructor before Stage 0.

## 2. Repository and setup

1. One team member creates a **private** repository named
   `capstone2-<track>-<teamname>` (e.g. `capstone2-ee-voltage-vipers`),
   with the **Java** `.gitignore` (then add `target/` to it), adds
   teammates and the instructor as collaborators, and everyone clones it.
2. Copy your track's starter folder ([`generic_starter/`](generic_starter/),
   [`ee_starter/`](ee_starter/), or [`biomedical_starter/`](biomedical_starter/))
   into the repository root (including the hidden `.mvn` folder), and
   commit it as your Stage 0 starting point.
3. **Every** team member checks that it builds and tests on their own
   computer:

   ```bash
   ./mvnw test                  # Windows: mvnw test
   ./mvnw -q compile exec:java  # run it
   ```

4. Split the work **by package and class**: one person owns `model`,
   another `storage`, and so on, with everyone writing tests for their
   own code. `git pull` before you start, commit small, and push often.
   **Never push code that doesn't compile or whose tests fail**: run
   `./mvnw test` before every push.

Each starter is a Maven project with **Gson** (for JSON) and **JUnit 5**
already in its `pom.xml`, organised into four packages:

| Package | Holds | May print or read input? |
|---|---|---|
| `model` | The classes, their rules, and the custom exceptions | **No** |
| `storage` | Saving and loading: JSON (with Gson), CSV import and export | **No** |
| `analysis` | Statistics, the recursive feature, and the charts (drawn to PNG files) | **No** |
| `ui` | The menu (and the optional Swing window) | **Yes**: the only package that talks to the user |

Keeping input and output in `ui` is what makes everything else
testable.

## 3. Requirements for every track

**Object-oriented design (Modules 10–12)**

- **At least six classes** across the `model` package, including an
  **abstract superclass with at least three subclasses** that override
  its abstract methods and are used polymorphically (no `instanceof`
  chains where an overridden method would do).
- **At least one interface** with more than one implementation (or used
  with lambdas), **at least one enum with fields**, and **at least two
  records**.
- **Encapsulation** everywhere: private fields, validation in
  constructors and methods, and copies (not the real lists) returned
  from getters.
- `equals` and `hashCode` overridden where objects are compared or used
  as keys, and `toString` everywhere it helps.
- A **custom exception hierarchy** (the starter's base exception and at
  least two more subclasses), thrown by the model and handled in `ui`.

**Data that persists (Modules 13–14, 17)**

- The application's full state is **saved to JSON** with Gson after
  every change, and **reloaded** on start-up. The starter's
  `SubtypeAdapterFactory` lets Gson save and load your class family:
  register each subclass with it.
- **CSV import** of the messy data file in your starter's `sample_data/`
  folder (skip and report every bad row), and **CSV export** of at least
  two reports.
- **Nothing crashes**: not on bad input, a missing or **corrupt** save
  file, a messy CSV row, or an empty data set.

**Modern Java (Modules 15–16)**

- **Streams** with `Comparator`s and `Collectors` for the searching,
  sorting, grouping and statistics, and generics where they make code
  reusable.
- **At least one recursive method** that fits the problem (your track
  names one), with its base case and recursive case in its Javadoc.

**Analysis**

- **At least five summary statistics** shown in the application.
- **At least three different charts** generated from the application's
  data and saved as PNG files, each with a title and labelled axes (with
  units). The starter includes one working chart class drawn with Java2D,
  the same drawing tools as Module 1, onto an image instead of a window.
  Use it as a model for the others.

**Testing (Module 17)**

- **At least 40 JUnit tests**, all passing, covering the model (normal,
  boundary and invalid cases), storage (using `@TempDir`, as the
  starter's `StorageTest` does), the recursive method, and the
  statistics. Use `assertThrows`, a delta for doubles, and at least two
  `@ParameterizedTest`s.

**Documentation and teamwork**

- The starter's `README.md`, completed: what the application does;
  **setup instructions** for a fresh computer; how to use it, with an
  example session; an **architecture** section listing every package and
  class and what it's responsible for; how to run the tests; and **who
  built what**.
- **Javadoc** on every public class and method.
- Regular, meaningful commits from **every** team member, under their
  own name.

## 4. The tracks

### Track A: MakerStore (inventory and lending for a makerspace)

A makerspace lends tools, sells components, and hands out project kits.

- **Classes:** the abstract `Item` (ID, name, category, `value()`) with
  subclasses `Tool` (borrowed and returned; has a condition),
  `Component` (a consumable with a quantity and a reorder level), and
  `Kit` (contains other items, **including other kits**). Plus a
  `Member` class, the `Loan` record (with borrow and due dates, using
  `LocalDate`), and the `Inventory` that holds everything.
- **Features:** add, edit and remove items; lend and return tools, with
  due dates and **overdue detection**; issue components, with a
  **low-stock alert** at the reorder level; search by name or category;
  members' borrowing history.
- **Recursion:** a kit's total `value()` and its full parts list
  (`allParts()`) must include the contents of any kits inside it, at any
  depth.
- **Import/export:** import stock from `sample_data/stock.csv` (it has
  bad rows); export a stock report and an overdue list to CSV.
- **Charts:** stock value by category, loans per week, and the most
  borrowed tools.

### Track B: SensorLab (multi-sensor data logger and analyser)

A lab bench with several sensors (voltage rails, a temperature probe, a
current shunt) that need logging, alarms, and analysis.

- **Classes:** the abstract `Sensor` (ID, label, unit, alarm limits,
  calibration offset), whose subclasses each turn a **raw** logger value
  into a real one in their own way: `VoltageSensor` (raw volts, given),
  `TemperatureSensor` (an LM35: 10 mV per °C) and `CurrentSensor` (raw
  millivolts across a shunt resistor: I = V / R). Plus the `Reading`
  record, a `Session` holding readings, and the `Lab` holding sensors and
  sessions.
- **Features:** configure sensors (limits and calibration, saved to
  JSON); **import** a messy log (`sample_data/bench_log.csv`: 480 good
  readings from four sensors over two hours, plus six bad rows);
  **simulate** a live session with a random walk, including occasional
  faults; raise an **alarm** for every out-of-limit reading; per-sensor
  statistics; a **moving average**; and **power and energy** for the
  matched voltage/current pair (V5 and I1).
- **Recursion:** the longest run of consecutive alarm readings
  (`Statistics.longestAlarmRun`), or a recursive equivalent-resistance
  calculator for the bench's resistor networks (Module 16).
- **Import/export:** import logs from CSV; export cleaned readings and an
  alarm report to CSV.
- **Charts:** a time series per sensor with its alarm limits drawn as
  lines (the starter's `LineChart` does this), a histogram of readings,
  and power over time.

The sample log has a story in it. Something happened to the load at
around 10:10, and the 5 V rail didn't like it. Your alarms and charts
should make it obvious.

### Track C: WardWatch (patient vitals monitor with early-warning alerts)

A ward needs to record patients' vital signs, spot deterioration early,
and summarise each patient's stay.

- **Classes:** the abstract `Measurement` with subclasses `HeartRate`
  (given), `BloodPressure`, `Temperature`, and `SpO2`, each with its own
  validation, "abnormal" rule and early-warning points. Plus the
  `Patient` record, a `PatientRecord` holding measurements, and the
  `Ward` holding patients and beds.
- **Features:** admit and discharge patients (with bed management);
  record vitals; import observations from `sample_data/ward_vitals.csv`
  (two days of four-hourly observations for the six patients in
  `sample_data/patients.csv`, plus six bad rows); the **simplified
  early-warning score** (below), with an **alert** when a patient's total
  reaches 5 or more; trends per patient; and a ward overview sorted by
  score.
- **Recursion:** the starter's `Location` class models the hospital as a
  tree (hospital → wards → bays → beds). Write the recursive `find`
  (where is a patient?) and `freeBeds` (how many empty beds?).
- **Import/export:** import vitals from CSV; export a discharge summary
  (text) and the alert log (CSV).
- **Charts:** each vital sign over time for a patient, with the normal
  range marked; early-warning scores across the ward; and a histogram of
  heart rates on the ward.

The sample data has a story too: one patient gets steadily worse over
the two days, and another gets better. Your scores should show who.

**Simplified early-warning score bands** (for teaching only):

| Points | Heart rate (bpm) | Temperature (°C) | SpO₂ (%) | Systolic BP (mmHg) |
|---|---|---|---|---|
| 3 | ≤ 40 or ≥ 131 | ≤ 35.0 | ≤ 91 | ≤ 90 or ≥ 220 |
| 2 | 111–130 | ≥ 39.1 | 92–93 | 91–100 |
| 1 | 41–50 or 91–110 | 35.1–36.0 or 38.1–39.0 | 94–95 | 101–110 |
| 0 | 51–90 | 36.1–38.0 | ≥ 96 | 111–219 |

> This score is simplified and inspired by real early-warning systems.
> Thresholds are for teaching. This is not a clinical tool.

## 5. Stretch goals

Pick as many as you like. **Solo/daring students must complete every
stretch goal for their track.**

**All tracks:**

1. **A Swing interface** (Module 18) as well as the console menu,
   using the **same** model, storage and analysis classes, with the
   charts drawn live in a window. Choose which interface to start with a
   command-line argument: `java -jar target/<app>-1.0.jar --gui`.
2. **Command-line reports**: `java -jar target/<app>-1.0.jar report`
   prints a report and exits, without the menu.
3. **Test coverage**: add the JaCoCo plugin to the `pom.xml`, run
   `./mvnw test jacoco:report`, open `target/site/jacoco/index.html`,
   and reach 80% coverage of the `model`, `storage` and `analysis`
   packages.

**Track A:** fines for overdue tools (charged per day, by tool type);
a reservation queue (a `Queue`) for a busy tool.

**Track B:** automatic detection of sensor **drift** (a moving average
moving steadily away from the calibrated value); a one-page PNG
dashboard showing every sensor.

**Track C:** a **trend alert** when a patient's score rises by 3 or more
between consecutive observations; a per-patient PNG "chart sheet"
showing all four vital signs.

## 6. Stages and timeline

| Stage | Focus | Git evidence expected |
|---|---|---|
| Stage 0 | Team formed, repository set up, everyone can build and test, **design on paper**: classes (with fields and methods), packages, and who owns what | Starter committed; design and work split in the README |
| Stage 1 | `model`: classes, validation, exceptions, `equals`/`hashCode`/`toString`, **with tests** | Commits adding each class together with its tests |
| Stage 2 | `storage`: JSON save/load, CSV import (with cleaning) and export, **with `@TempDir` tests** | A commit where data survives a restart |
| Stage 3 | `ui`: the menu, input helpers, error handling, clean exit | The application is usable end to end |
| Stage 4 | `analysis`: statistics, charts, the recursive feature | Charts generated from real application data |
| Stage 5 | Hardening and polish: attack your own app (as in Module 13), reach 40+ tests, stretch goals | Commits named after the bugs found and fixed |
| Final | README complete, every test passing, `./mvnw package` builds a runnable JAR, final push before the deadline | Your final commit *is* your submission |

**Swap and review:** before the final stage, swap with another team.
Clone their repository, run `./mvnw test`, try to break their
application, and read their code. Write them three specific, kind,
useful suggestions as a GitHub issue on their repository.

## 7. How it's assessed

| Area | What we look for |
|---|---|
| **Functionality** | Every feature in §3 and §4 works. Data persists. Nothing crashes. |
| **Design** | Sensible classes and packages with clear responsibilities. Inheritance used for real "is a" relationships, composition elsewhere. Logic separated from input and output. |
| **Code quality** | Readable names, Javadoc, no duplicated blocks, streams and records used where they help. |
| **Testing** | 40+ meaningful tests covering normal, boundary, and invalid cases, all passing. |
| **Analysis** | Correct statistics and clear, honest, well-labelled charts. |
| **Documentation** | A stranger can set up, run, and test the project from the README alone. |
| **Teamwork and Git** | Regular commits from every member. Contributions match the README's "who built what". Be ready to explain any part of the code. |

## Try it yourself

1. Confirm your team and track with your instructor.
2. Create the repository, copy in your starter, and check that every
   member can run `./mvnw test`.
3. Design on paper, and agree who owns which package.
4. Work through the stages, testing as you go, committing and pushing
   regularly.
5. Submit by pushing your final commit before the deadline.

You started this course unable to write a single line of Java. You're
finishing it with a tested, documented, multi-package application in
your GitHub portfolio. Well done. Put the link on your CV.
