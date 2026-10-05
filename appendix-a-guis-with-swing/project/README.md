# Appendix A Project: A Real Windowed Application

Pick **one** track, and build a complete desktop application with a
graphical user interface: forms, buttons, lists, a custom-drawn picture,
and data that's saved between runs.

```bash
cd generic_starter              # or ee_starter, or biomedical_starter
javac -d out *.java
java -cp out PlannerWindow      # or ColourCodeWindow, or DashboardWindow
```

## What every track must do

1. **Model separate from view.** The data and rules live in classes
   with **no Swing code** (records, enums, a store class). The window
   classes only display the model and pass the user's actions to it,
   like `Ex11ToDoList` and Exercise 2's calculator.
2. **A sensible layout**, built from nested panels and at least **two**
   different layout managers, that still looks right when the window is
   resized.
3. **At least five kinds of component**, chosen from: `JButton`,
   `JTextField`, `JComboBox`, `JList`, `JCheckBox`, `JRadioButton`,
   `JSlider`, `JTextArea`, `JTable`, `JMenuBar`.
4. **A custom-painted component** (a `JPanel` subclass overriding
   `paintComponent`) that changes when the data changes, via `repaint()`.
5. **Bad input never crashes** and never prints a stack trace: show a
   message in the window (a red label, or a `JOptionPane`).
6. **Data persists**: it's saved to a file and loaded when the program
   starts (Module 14), with file errors reported in a dialog.
7. Everything Swing happens on the event thread: start with
   `SwingUtilities.invokeLater`, and use `javax.swing.Timer`, never
   `Thread.sleep`, for anything that happens later.

**Stretch (all tracks):** keyboard shortcuts for the main actions (a menu
with accelerators), and a confirmation dialog before anything is
deleted.

## Track A: Generic, Study Planner

1. `Task` (a record) and `Priority` (an enum with a colour) are started;
   `TaskStore` saves and loads tasks as CSV.
2. The window: a form to add a task (title, subject, due date typed as
   `YYYY-MM-DD`, and a `Priority` combo box), a list of tasks, **Mark
   done** and **Delete** buttons, and a filter: **All**, **To do**,
   **Overdue**, or one subject.
3. A custom-painted **progress panel**: a bar for each subject showing
   how many of its tasks are done, and a count of overdue tasks.
4. Sort the list by due date, then priority (your Module 12
   `Comparator`s).

**Stretch:** colour each task in the list by its priority with a custom
**cell renderer**: `list.setCellRenderer(...)` with a
`DefaultListCellRenderer` subclass that calls `super` and then
`setForeground(task.priority().getColour())`.

## Track B: EE, Resistor Colour Code Calculator

1. `BandColour` (your Module 12 enum, now with a real `Color` for each
   band) is given. `ResistorPanel` draws a resistor body: finish it so it
   paints the four bands in their colours.
2. **Colours → value:** four `JComboBox<BandColour>`s, each listing only
   the colours allowed in that position (no gold digits, no white
   multiplier). Changing any of them updates a big label (`4.7 kΩ ±5%`)
   and redraws the resistor straight away.
3. **Value → colours:** a text field that accepts values like `4k7`,
   `220`, `1M` (your SI parser from Module 13 or 17) and sets the four
   combo boxes to match.
4. Show whether the value is a **standard E12** value, and if not,
   the nearest one (Module 16).

**Check:** yellow-violet-red-gold is 4.7 kΩ ±5%; typing `220` gives
red-red-brown; `1M` gives brown-black-green.

## Track C: Biomedical, Vitals Dashboard

1. `Reading` (a record with validation and normal-range checks) is
   started. Use the ranges from Module 13.
2. The window: a form for heart rate, SpO₂ and temperature, with a
   **Record** button that adds a `Reading` at the current time; three
   big **status labels** that turn green or red for the latest reading;
   and a history list.
3. `ChartPanel` draws a **line chart** of heart rate over time: finish
   it so it shades the normal band, joins the points with lines, and
   draws abnormal points in red. It updates every time a reading is
   recorded.
4. **Save** and **Load** the readings as CSV, from a menu, with a
   `JFileChooser`.

**Stretch:** a `JComboBox` that switches the chart between heart rate,
SpO₂ and temperature, each with its own normal band.

> Thresholds are simplified for teaching. This is not a clinical tool.

## Ideas if you're stuck

- **Sketch the window on paper first**, and draw boxes round the parts.
  Each box is a `JPanel` with its own layout.
- Build the window a piece at a time, running it after each piece.
- Nothing appears? You probably forgot `setVisible(true)`, or added the
  component to the wrong panel, or used `setSize` on a component inside
  a layout (layouts ignore it: use `setPreferredSize`).
- The picture doesn't update? Call `repaint()` after changing the data.
- A listener that needs to change a local variable can't (lambdas need
  effectively final variables). Keep the state in a field, or in the
  model object.

## Done?

```bash
git add .
git commit -m "Complete Appendix A project: a Swing application"
git push
```

Take a screenshot of your finished window for your coursework README.
