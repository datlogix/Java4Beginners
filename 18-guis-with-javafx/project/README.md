# Module 18 Project: A Real Windowed Application

Pick **one** track, and build a complete desktop application with a
JavaFX interface: forms, buttons, a table or list, a picture or chart
that updates by itself, and data that's saved between runs.

Each starter is a Maven project (Module 17) with JavaFX already set up:

```bash
cd generic_starter              # or ee_starter, or biomedical_starter
./mvnw javafx:run               # Windows: mvnw javafx:run
./mvnw test                     # the model's tests: no window needed
```

## What every track must do

1. **Model separate from view.** The data and rules live in classes
   with **no JavaFX code** (records, enums, a store class), each with
   JUnit tests in `src/test/java`. The window only displays the model
   and passes the user's actions to it, like `Ex12ToDoList` and
   Exercise 2's calculator. (Track B's `BandColour` uses JavaFX's
   `Color`, which is fine: it has no windows.)
2. **A sensible layout**, built from nested panes and at least **two**
   different layout panes (`BorderPane`, `HBox`, `VBox`, `GridPane`),
   that still looks right when the window is resized.
3. **At least five kinds of control**, chosen from: `Button`,
   `TextField`, `ComboBox`, `ListView`, `TableView`, `CheckBox`,
   `RadioButton`, `Slider`, `DatePicker`, `TextArea`, `MenuBar`.
4. **At least one binding or listener** that keeps part of the window up
   to date by itself (a label bound to a property, a button disabled
   while a field is empty, a picture that changes when a combo box does).
5. **Bad input never crashes** and never prints a stack trace: show a
   message in the window (a red label, or an `Alert`).
6. **Data persists**: it's saved to a file and loaded when the program
   starts (Module 14), with file errors reported in a dialog.
7. **Nothing slow on the JavaFX thread.** Use a `Timeline` or
   `AnimationTimer`, never `Thread.sleep`, for anything that happens
   later.

**Stretch (all tracks):**

- keyboard shortcuts for the main actions (a menu with accelerators);
- a confirmation dialog before anything is deleted;
- a CSS stylesheet;
- store the data in **SQLite** instead of a CSV file, if you did the
  optional [Module 14b](../../14b-databases-with-sqlite-and-jdbc/README.md).
  Add the `org.xerial:sqlite-jdbc` dependency to `pom.xml`, and
  `requires java.sql;` to `module-info.java`.

## Track A: Generic, Study Planner

1. `Task` (a record) and `Priority` (an enum with a colour) are started.
   Finish `Task`'s validation and `fromCsv`, and `TaskStore`, which saves
   and loads tasks as CSV. Test them.
2. The window: a **`TableView`** of tasks (title, subject, due date,
   priority, done); a form to add a task (title, subject, a
   **`DatePicker`** for the due date, and a `Priority` combo box), with
   the Add button disabled until the title is filled in; **Mark done**
   and **Delete** buttons; and a filter: **All**, **To do**, **Overdue**,
   or one subject (a `FilteredList`, as in `Ex10TableView`).
3. A **progress chart**: a `BarChart` with a bar for each subject
   showing how many of its tasks are done, and a label counting the
   overdue tasks. It updates after every change.
4. Sort the table by due date, then priority (your Module 12
   `Comparator`s), and keep the column headers clickable (`SortedList`).

**Stretch:** colour each task's priority cell with a **cell factory**:

```java
priorityColumn.setCellFactory(column -> new TableCell<>() {
    @Override
    protected void updateItem(Priority p, boolean empty) {
        super.updateItem(p, empty);
        setText(empty || p == null ? null : p.toString());
        setStyle(empty || p == null ? "" : "-fx-text-fill: " + p.getColour() + ";");
    }
});
```

## Track B: EE, Resistor Colour Code Calculator

1. `BandColour` (your Module 12 enum, now with a JavaFX `Color` for each
   band) is given. `ColourCode` has `ohms`; finish `describe` and
   `encode`, and test them.
2. `ResistorView` draws a resistor body from **shapes**: finish it so it
   creates the four band `Rectangle`s, and `setBands` changes their
   fills. (Because the bands are objects, there's nothing to repaint.)
3. **Colours → value:** four `ComboBox<BandColour>`s, each listing only
   the colours allowed in that position (no gold digits, no white
   multiplier). Changing any of them updates a big label (`4.7 kΩ ±5%`)
   and the resistor straight away (a listener on each combo box's
   `valueProperty()`).
4. **Value → colours:** a text field that accepts values like `4k7`,
   `220`, `1M` (your SI parser from Module 13 or 17) and sets the four
   combo boxes to match.
5. Show whether the value is a **standard E12** value, and if not,
   the nearest one (Module 16).

**Check:** yellow-violet-red-gold is 4.7 kΩ ±5%; typing `220` gives
red-red-brown; `1M` gives brown-black-green.

## Track C: Biomedical, Vitals Dashboard

1. `Reading` (a record with normal-range checks) is started. Finish its
   validation, using the ranges from Module 13, and test every boundary.
2. The window: a form for heart rate, SpO₂ and temperature, with a
   **Record** button that adds a `Reading` at the current time; three
   big **status labels** that turn green or red for the latest reading
   (style classes, as in `Ex13FxmlAndCss`); and a history `ListView` or
   `TableView`.
3. The **`LineChart`** of heart rate is started. Add a point every time
   a reading is recorded, and label the points by reading number (or
   switch to a `CategoryAxis` of times).
4. **Save** and **Load** the readings as CSV, from a menu, with a
   `FileChooser`.

**Stretch:** a `ComboBox` that switches the chart between heart rate,
SpO₂ and temperature, each with its own axis range; or a **Simulate**
check box that adds a made-up reading every two seconds with a
`Timeline`.

> Thresholds are simplified for teaching. This is not a clinical tool.

## Ideas if you're stuck

- **Sketch the window on paper first**, and draw boxes round the parts.
  Each box is a pane with its own layout.
- Build the window a piece at a time, running it after each piece.
- Nothing appears? You probably forgot to add the node to a pane's
  `getChildren()` (or a `BorderPane` region), or added it to the wrong
  pane. A node can only be in **one** place at a time.
- The table doesn't update? Change the `ObservableList`, not a copy of
  it. A record in the list can't change, so replace it:
  `tasks.set(i, task.markedDone())`.
- A lambda that needs to change a local variable can't (lambdas need
  effectively final variables). Keep the state in a field, a property,
  or the model object.
- "Module ... does not read ..." or "cannot access class": check
  `module-info.java` lists what you use (`requires java.sql;` for
  SQLite).

## Done?

```bash
git add .
git commit -m "Complete Module 18 project: a JavaFX application"
git push
```

Take a screenshot of your finished window for your coursework README.
