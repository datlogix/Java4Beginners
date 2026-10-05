# Module 18: GUIs with JavaFX

## Hook: a game in a window

Open a terminal in [`examples`](examples) and type:

```bash
cd examples
./mvnw javafx:run            # Windows: mvnw javafx:run
```

The first time, Maven downloads JavaFX (it takes a minute, and needs
the internet). Then a window opens with a golden dot on a dark
background, and a clock counting down from 20. Click the dot. It jumps
somewhere else, a little smaller, and it starts moving faster. How many
can you catch before time runs out? When the clock hits zero, a dialog
asks whether you want to play again.

Look at how differently this program works. Every program since Module
1 ran from the top of `main` to the bottom, stopping to ask questions in
a fixed order. This one sets things up and then **waits**: it reacts to
a mouse click whenever one happens, to a timer firing every 900
milliseconds, and to another firing every second. This is
**event-driven programming**, and it's how every app on your phone and
laptop works. This module shows you how to build windowed programs with
**JavaFX**, Java's modern GUI toolkit.

> **JavaFX or Swing?** Since Module 1 you've drawn pictures with
> **Swing**, the older toolkit built into every JDK. JavaFX is newer,
> and has better controls, charts, styling with CSS, and animation, so
> it's the one to use for new desktop applications. It isn't part of
> the JDK, so you add it to a project like any other library. Swing is
> covered in the optional [Appendix A](../appendix-a-guis-with-swing/README.md),
> if you want to see how your Module 1 drawings work.

## Getting JavaFX

### With Maven (recommended)

Every project in this module is a Maven project (Module 17). JavaFX is
two dependencies in `pom.xml`, and a plugin that runs the program:

```xml
<dependency>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-controls</artifactId>
    <version>21.0.12</version>
</dependency>
...
<plugin>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-maven-plugin</artifactId>
    <version>0.0.8</version>
    <configuration>
        <mainClass>com.makerspace.fx/com.makerspace.fx.${main}</mainClass>
    </configuration>
</plugin>
```

Maven downloads the right JavaFX for your computer (Windows, macOS or
Linux). `./mvnw javafx:run` compiles and runs the program. The examples
project holds 15 programs, so you choose one with `-Dmain`:

```bash
./mvnw javafx:run -Dmain=Ex03Layouts
```

Each project also has a **`module-info.java`**, which says which parts
of JavaFX it uses:

```java
module com.makerspace.fx {
    requires javafx.controls;     // windows, buttons, layouts, charts
    requires javafx.fxml;         // FXML (Example 13)
    opens com.makerspace.fx to javafx.fxml;
    exports com.makerspace.fx;
}
```

You can treat it as given. (Java **modules** group packages, the way
packages group classes. JavaFX is built from modules, and this file
lets it find your classes.)

**In VS Code**, open the folder containing `pom.xml` (for example
`18-guis-with-javafx/examples`). Once the Java extension has loaded the
project, open any example and click **Run** above its `main` method.

### With the JavaFX SDK (no Maven)

You can also download the **JavaFX SDK** from
[gluonhq.com/products/javafx](https://gluonhq.com/products/javafx/),
unzip it, and tell `javac` and `java` where its `lib` folder is with two
**VM options**:

```bash
javac --module-path /path/to/javafx-sdk-21/lib --add-modules javafx.controls -d out Hello.java
java  --module-path /path/to/javafx-sdk-21/lib --add-modules javafx.controls -cp out Hello
```

(On Windows the path looks like `C:\javafx-sdk-21\lib`. Add
`,javafx.fxml` after `javafx.controls` if you use FXML.) In an IDE,
these two options go in the run configuration's **VM options** box. The
single-file shortcut `java Hello.java` doesn't work with JavaFX, so
compile first. Maven does all of this for you, which is why the course
uses it.

### "JavaFX runtime components are missing"

If you see this error, Java was started without JavaFX on the module
path: you ran a JavaFX program with plain `java`, or with an IDE's Run
button outside a Maven project, or without the VM options. Use
`./mvnw javafx:run`, or add the `--module-path` and `--add-modules`
options.

## Stage, scene and nodes

A JavaFX program is a class that **extends `Application`** and
overrides **`start`**:

```java
public class Ex01FirstWindow extends Application {

    @Override
    public void start(Stage stage) {
        Label hello = new Label("Akwaaba! This is a JavaFX window.");
        StackPane root = new StackPane(hello);

        stage.setTitle("My first window");
        stage.setScene(new Scene(root, 480, 120));
        stage.show();                          // windows start hidden!
    }

    public static void main(String[] args) {
        launch(args);                          // sets up JavaFX, then calls start
    }
}
```

The words come from the theatre:

| Class | Is… |
|---|---|
| `Stage` | The **window**, with a title bar and a close button. JavaFX creates the first one and passes it to `start` |
| `Scene` | **What's shown** in the window: one tree of nodes, with a size |
| Nodes | Everything in the scene: **controls** (`Label`, `Button`, `TextField`…), **layout panes** (`HBox`, `BorderPane`…), and **shapes** (`Circle`, `Line`…) |

The nodes form a tree called the **scene graph**: a layout pane holds
other nodes in its `getChildren()` list, and they can be panes too.
`launch` runs `start` on the **JavaFX application thread**, and closing
the last window ends the program. See
[`Ex01FirstWindow.java`](examples/src/main/java/com/makerspace/fx/Ex01FirstWindow.java).

## Events

A program finds out what the user did through **events**. You give a
node a **handler**, and JavaFX calls it when the event happens:

```java
Button plus = new Button("+");
plus.setOnAction(e -> {
    count++;
    display.setText(String.valueOf(count));
});
```

The handler is a functional interface (Module 12), so a lambda is the
neat way to write one. This line **doesn't** run the lambda: it
**registers** it, and JavaFX runs it each time the button is clicked,
possibly hours later. The `e` is an `ActionEvent` describing what
happened. Pressing Enter in a `TextField` also fires its `setOnAction`.
Other events have their own handlers: `setOnMouseClicked`,
`setOnKeyPressed`, and so on. See
[`Ex02ButtonsAndEvents.java`](examples/src/main/java/com/makerspace/fx/Ex02ButtonsAndEvents.java).

## Layout panes

You don't usually place nodes at exact pixel positions: windows get
resized, and fonts differ between computers. Instead, put them in a
**layout pane** that decides where they go and how they stretch:

| Pane | Arranges its children… | Good for |
|---|---|---|
| `HBox(spacing, ...)` | in a row | rows of buttons |
| `VBox(spacing, ...)` | in a column | forms, stacks of controls |
| `BorderPane` | in five regions: top, bottom, left, right, and centre, which gets all the spare space | the overall shape of a window |
| `GridPane` | in rows and columns: `grid.add(node, column, row)` | forms with labels, keypads |
| `StackPane` | on top of each other, centred | a message over a picture |
| `Pane` | exactly where you put them (`setLayoutX`, or a shape's own coordinates) | drawings and games |

```java
BorderPane root = new BorderPane();
root.setTop(title);
root.setCenter(keypad);
root.setBottom(new HBox(8, save, cancel));
root.setPadding(new Insets(10));               // space round the edge
HBox.setHgrow(searchField, Priority.ALWAYS);   // this one stretches to fill a row
```

Real windows **nest** panes: a `BorderPane` for the overall shape, an
`HBox` of buttons at the bottom, and a `GridPane` keypad in the centre.
Run [`Ex03Layouts.java`](examples/src/main/java/com/makerspace/fx/Ex03Layouts.java)
and resize the window to see how each pane behaves.

## Text input and choices

`textField.getText()` gives what the user typed, as a `String`, and
`label.setText(...)` changes what a label shows. Everything you know
about converting and validating input (Modules 3 and 13) applies:

```java
try {
    double c = Double.parseDouble(celsius.getText().trim());
    result.setText(String.format("%.1f C = %.1f F", c, c * 9 / 5 + 32));
} catch (NumberFormatException e) {
    result.setTextFill(Color.RED);
    result.setText("'" + celsius.getText() + "' isn't a number");
}
```

In a GUI, an uncaught exception doesn't end the program. It prints a
stack trace in the terminal, where the user can't see it, and the
button just seems to do nothing. So **always** catch bad input, and say
what went wrong *in the window*. See
[`Ex04TextInput.java`](examples/src/main/java/com/makerspace/fx/Ex04TextInput.java).

| Control | Read it with |
|---|---|
| `CheckBox` | `isSelected()` |
| `RadioButton`, each with `setToggleGroup(group)` so only one can be chosen | `isSelected()`, or `group.getSelectedToggle()` |
| `ComboBox<T>`, a drop-down list | `getValue()` (it shows each item's `toString()`) |
| `Slider(min, max, start)` | `getValue()` |
| `DatePicker` | `getValue()`, a `LocalDate` (Module 14) |
| `TextArea` | `getText()`; it scrolls by itself |

[`Ex05ChoiceControls.java`](examples/src/main/java/com/makerspace/fx/Ex05ChoiceControls.java)
builds a lab-booking form from the first four, laid out with a `GridPane`.

## Properties and binding

Most things about a JavaFX node are **properties**: a value you can get
and set, that also tells anyone who's interested when it changes. A
`Slider`'s value is `valueProperty()`, a `TextField`'s text is
`textProperty()`, a `Label`'s text is `textProperty()`.

You can **listen** to a property:

```java
slider.valueProperty().addListener((obs, oldValue, newValue) ->
        System.out.println("now " + newValue));
```

Or, better, **bind** one property to another, so it always follows it,
with no handler code at all:

```java
level.textProperty().bind(slider.valueProperty().asString("Level: %.0f"));
save.disableProperty().bind(name.textProperty().isEmpty());   // greyed out until there's a name
```

The second line is something you'll use in every form: the Save button
is disabled while the name field is empty, and enables itself the
moment you type. See
[`Ex06PropertiesAndBinding.java`](examples/src/main/java/com/makerspace/fx/Ex06PropertiesAndBinding.java).

## Dialogs, menus and files

`Alert` has ready-made dialogs, so you don't need to build a window for
every message or question:

```java
new Alert(Alert.AlertType.INFORMATION, "Saved!").showAndWait();

Alert sure = new Alert(Alert.AlertType.CONFIRMATION, "Delete this task?");
if (sure.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) { ... }

Optional<String> name = new TextInputDialog().showAndWait();   // empty if cancelled
```

`showAndWait` returns an `Optional` (Module 15), because the user might
close the dialog without choosing. See
[`Ex07Dialogs.java`](examples/src/main/java/com/makerspace/fx/Ex07Dialogs.java).

[`Ex08MenusAndFiles.java`](examples/src/main/java/com/makerspace/fx/Ex08MenusAndFiles.java)
builds a small text editor with a **menu bar** (`MenuBar`, `Menu`,
`MenuItem`), keyboard shortcuts
(`setAccelerator(KeyCombination.keyCombination("Shortcut+O"))`, which
means Ctrl on Windows and Cmd on a Mac), and `FileChooser`, the
standard Open and Save dialogs, connected to the file code from Module
14.

## Shapes and the canvas

JavaFX can draw in two ways:

- **Shapes** are nodes: `Circle`, `Rectangle`, `Line`, `Polygon`,
  `Text`. Add them to a `Pane`, and they stay there. To move one or
  change its colour, change its properties (`circle.setCenterX(...)`,
  `rect.setFill(Color.RED)`), and JavaFX redraws it for you. Shapes can
  be clicked, like the dot in the hook.
- A **`Canvas`** is a single node you paint on with a
  `GraphicsContext`, like the `Graphics2D` pen of Module 1's drawings:
  `gc.setStroke(...)`, `gc.strokeLine(...)`, `gc.fillOval(...)`. It
  keeps only the pixels, so to change the picture you clear it and paint
  it again. It's best for drawings with thousands of pieces, such as a
  graph or a game background.

[`Ex09ShapesAndCanvas.java`](examples/src/main/java/com/makerspace/fx/Ex09ShapesAndCanvas.java)
shows both side by side: shapes you can click, and a sine wave painted
on a canvas whose frequency follows a slider.

## Lists and tables

A `ListView` or a `TableView` shows the items in an
**`ObservableList`**: a list that tells the control when it changes.
Add, remove, or replace an item in the list, and the control updates by
itself:

```java
ObservableList<Student> students = FXCollections.observableArrayList();
TableView<Student> table = new TableView<>(students);

TableColumn<Student, String> name = new TableColumn<>("Name");
name.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().name()));
table.getColumns().add(name);

students.add(new Student("S001", "Akua Mensah", "BME", 300, 3.85));   // the table shows it at once
Student chosen = table.getSelectionModel().getSelectedItem();
```

Each column's **cell value factory** is a lambda that, given a row,
returns the value to show in that column, as a property. Two wrappers
make tables even more useful:

- **`FilteredList`** shows only the items that pass a test. Change the
  test (`filtered.setPredicate(s -> ...)`) whenever a search box
  changes, and the table updates.
- **`SortedList`**, bound to the table's comparator, keeps the column
  headers clickable for sorting.

[`Ex10TableView.java`](examples/src/main/java/com/makerspace/fx/Ex10TableView.java)
is a student table with a live search box, sorting, and Add and Delete
buttons.

## Animation, and the JavaFX thread

For anything that happens **over time**, use a **`Timeline`** or an
**`AnimationTimer`**. Both run your code on the JavaFX thread:

```java
Timeline clock = new Timeline(new KeyFrame(Duration.seconds(1), e -> tick()));
clock.setCycleCount(Animation.INDEFINITE);
clock.play();

new AnimationTimer() {
    @Override
    public void handle(long now) {      // about 60 times a second
        moveTheBalls();
    }
}.start();
```

**Never** use `Thread.sleep` (or a long loop) inside a handler. While a
handler runs, JavaFX can't do anything else: no drawing, no clicks. The
window freezes. See the bouncing balls in
[`Ex11Animation.java`](examples/src/main/java/com/makerspace/fx/Ex11Animation.java).
Now re-read the hook: two `Timeline`s (one for the dot, one for the
clock), a `Circle` with `setOnMouseClicked`, and an `Alert`.

## Keep the model separate from the view

A common beginner design puts everything inside the window code: the
data, the rules, and the buttons, tangled together. It works, until you
want to test the rules, save the data, or build a different interface.
Instead, split the program in two:

- The **model**: plain classes holding the data and enforcing the rules.
  No JavaFX at all. It can be unit tested (Module 17), and reused.
- The **view**: the window, which *displays* the model and *passes on*
  the user's actions to it.

[`Ex12ToDoList.java`](examples/src/main/java/com/makerspace/fx/Ex12ToDoList.java)
does this with a to-do list: a `TaskList` class that knows nothing about
windows, and a window around it that saves to a file after every change.
Exercise 2's calculator does it too, which is why `./mvnw test` can test
it without opening a window at all.

## FXML, Scene Builder and CSS

Bigger applications often describe the **layout** in a separate file,
written in **FXML** (an XML language for scene graphs), and keep the
Java code for what happens:

```xml
<VBox xmlns:fx="http://javafx.com/fxml" fx:controller="com.makerspace.fx.TemperatureController"
      spacing="12" styleClass="card">
    <TextField fx:id="celsius" onAction="#convert"/>
    <Button text="Convert" onAction="#convert"/>
    <Label fx:id="result"/>
</VBox>
```

The **controller** class gets the controls through fields marked
`@FXML`, with the same names as their `fx:id`s, and `#convert` calls its
`convert()` method. `FXMLLoader.load(...)` builds the scene graph from
the file. You don't have to write FXML by hand: **Scene Builder**
(free, from [gluonhq.com/products/scene-builder](https://gluonhq.com/products/scene-builder/))
lets you design the window by dragging controls, and saves FXML.

JavaFX nodes can also be styled with **CSS**, much as web pages are,
with `-fx-` in front of each property:

```css
.button { -fx-background-color: #F89820; -fx-text-fill: white; }
.bad    { -fx-text-fill: #C0392B; }
```

`scene.getStylesheets().add(...)` loads a stylesheet, and
`node.getStyleClass().add("bad")` gives a node a class. See
[`Ex13FxmlAndCss.java`](examples/src/main/java/com/makerspace/fx/Ex13FxmlAndCss.java),
with [`temperature.fxml`](examples/src/main/resources/com/makerspace/fx/temperature.fxml)
and [`app.css`](examples/src/main/resources/com/makerspace/fx/app.css)
in `src/main/resources`.

## Charts

JavaFX has charts built in: `LineChart`, `BarChart`, `PieChart`,
`ScatterChart`, `AreaChart`. A chart shows one or more **series** of
data points, and like a table, it updates by itself when you add a
point:

```java
LineChart<Number, Number> chart = new LineChart<>(new NumberAxis(), new NumberAxis());
XYChart.Series<Number, Number> heartRate = new XYChart.Series<>();
heartRate.setName("Heart rate");
chart.getData().add(heartRate);

heartRate.getData().add(new XYChart.Data<>(1, 72));     // appears straight away
```

See [`Ex14Charts.java`](examples/src/main/java/com/makerspace/fx/Ex14Charts.java).

## Putting it together: a window onto a database

[`worked-example-student-records`](worked-example-student-records/README.md)
is a complete application that combines this module with Module 14b and
Module 17: a JavaFX window (a table, a form, a live search and a chart)
onto an **SQLite database**, built and tested with **Maven**. Its
`pom.xml` has three dependencies (JavaFX, the SQLite driver, JUnit), its
`module-info.java` adds `requires java.sql;`, and its code is split into
a model, a repository holding all the SQL, and a view with no SQL at
all. Its README walks through every part, shows how to run it with the
JavaFX SDK instead of Maven, and lists the errors you're likely to meet.

```bash
cd worked-example-student-records
./mvnw javafx:run
./mvnw test
```

## Common beginner mistakes

- **"JavaFX runtime components are missing."** Run with
  `./mvnw javafx:run`, or add the `--module-path` and `--add-modules`
  VM options.
- **Forgetting `stage.show()`**, or never giving the stage a scene.
- **Creating a node and never adding it** to a pane's `getChildren()`
  (or a `BorderPane` region), so it doesn't appear.
- **Adding the same node in two places.** A node can only have one
  parent: the second `add` moves it (or throws "duplicate children").
- **Using `Thread.sleep` in a handler**, which freezes the window. Use a
  `Timeline`.
- **Letting exceptions escape from a handler.** They vanish into the
  terminal. Catch them and show a message.
- **Changing a copy of the list** instead of the `ObservableList` the
  table shows, so the table doesn't update.
- **Calling `setText` on a bound property.** A bound property follows
  its binding: "A bound value cannot be set". Unbind it, or change what
  it's bound to.
- **A misspelt `fx:id` or `#handler`** in FXML: the `@FXML` field stays
  `null`, or loading fails. The names must match exactly.
- **Putting all the program's logic in the handlers.** Keep a separate
  model class.

## Try it yourself

1. Run every example in [`examples`](examples) with
   `./mvnw javafx:run -Dmain=...`. In the hook, add a second dot worth
   more points that appears only now and then.
2. Complete the exercises in [`exercises`](exercises): Exercise 1 (a
   unit converter, `./mvnw javafx:run`) and Exercise 2 (a calculator
   whose model you make pass `./mvnw test` first, then
   `./mvnw javafx:run -Dmain=Exercise2`).
3. Run the [worked example](worked-example-student-records/README.md),
   and try one of its "Make it yours" changes.
4. Build the [module project](project/README.md) in the track of your
   choice: a study planner, a resistor colour code calculator, or a
   vitals dashboard.
5. Commit and push your work (without any `target` folders):

   ```bash
   git add .
   git commit -m "Complete Module 18: GUIs with JavaFX"
   git push
   ```

Next: **[Module 19: Capstone 2](../19-capstone-project-2/README.md)**.
