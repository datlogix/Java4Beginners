# Worked Example: Student Records (JavaFX + SQLite + Maven)

This is a complete, small desktop application that puts three parts of
the course together:

| Part | From | Used for |
|---|---|---|
| **JavaFX** | Module 18 | the window: a table, a form, a search box, a chart |
| **SQLite through JDBC** | Module 14b | storing the students in a database file, `students.db` |
| **Maven** (and JUnit) | Module 17 | downloading both libraries, building, running, and testing |

Read it, run it, and change it. It's the shape to copy when an
assignment asks for "a JavaFX application with an SQLite database, built
with Maven".

```bash
cd 18-guis-with-javafx/worked-example-student-records
./mvnw javafx:run          # Windows: mvnw javafx:run
./mvnw test                # 11 tests of the database code, no window
```

The first run creates `students.db` in the folder you ran it from, with
ten sample students. Add, choose a row and change it, delete, and search.
Close the window and run it again: everything is still there. To start
afresh, close the program and delete `students.db`.

## How the pieces fit

### 1. `pom.xml`: three dependencies

```xml
<dependency>                                  <!-- the window -->
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-controls</artifactId>
    <version>${javafx.version}</version>
</dependency>
<dependency>                                  <!-- the database driver -->
    <groupId>org.xerial</groupId>
    <artifactId>sqlite-jdbc</artifactId>
    <version>3.53.4.0</version>
</dependency>
<dependency>                                  <!-- the tests -->
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.11.4</version>
    <scope>test</scope>
</dependency>
```

That's all it takes to add both libraries: no `lib/` folder, no
downloads by hand. The `javafx-maven-plugin` runs the program.

### 2. `module-info.java`: say what the program uses

```java
module com.makerspace.records {
    requires javafx.controls;        // the window
    requires java.sql;               // JDBC: Connection, PreparedStatement, ResultSet
    requires org.xerial.sqlitejdbc;  // the SQLite driver itself

    exports com.makerspace.records;  // so JavaFX can start RecordsApp
}
```

Compared with the other Module 18 projects, two lines are new:
`java.sql` (JDBC is a separate module of the JDK) and
`org.xerial.sqlitejdbc` (the driver's module name). Strictly, either
line alone would do: the driver's module itself requires `java.sql`, and
Java finds a JDBC driver by itself once its jar is on the module path.
Writing both says plainly what the program depends on. Leave out both,
and the compiler says "package java.sql is not visible".

### 3. Three classes, three jobs

```
src/main/java/com/makerspace/records/
├── Student.java              the MODEL:   a record with its rules. No JavaFX, no SQL.
├── StudentRepository.java    the STORAGE: ALL the SQL, and nothing else.
└── RecordsApp.java           the VIEW:    the window. No SQL at all.
src/test/java/com/makerspace/records/
└── StudentRepositoryTest.java   tests the storage against an in-memory database
```

This is the model-view split from Module 18, with the repository from
Module 14b in the middle. Each class can be read, tested and changed on
its own:

- **`Student`** is a record whose constructor refuses bad values (an ID
  that isn't like `S001`, a blank name, a level that isn't 100–400, a
  GPA outside 0–4). The window turns those exceptions into red messages.
- **`StudentRepository`** opens the `Connection`, creates the table
  (with `PRIMARY KEY`, `NOT NULL` and `CHECK` constraints as a second
  line of defence), and has one method per job: `add`, `addAll` (in one
  transaction), `find`, `search`, `averageGpaByProgramme`, `count`,
  `update` and `delete`. **Every** value goes in through a `?`
  placeholder, so a search for `x' OR '1'='1` finds nothing, as one of
  the tests proves. It implements `AutoCloseable`, so the tests open it
  in try-with-resources style and the window closes it when the program
  ends.
- **`RecordsApp`** builds the window and, for every action, does the same
  three things: read the form into a `Student`, ask the repository, then
  `refresh()` the table and the chart from the database.

### 4. Opening and closing the database

```java
@Override
public void start(Stage stage) {
    try {
        repository = new StudentRepository("jdbc:sqlite:students.db");
        ...
    } catch (SQLException e) {
        new Alert(Alert.AlertType.ERROR, "Couldn't open students.db:\n" + e.getMessage()).showAndWait();
        return;
    }
    ...
}

@Override
public void stop() throws SQLException {   // JavaFX calls this when the window closes
    if (repository != null) {
        repository.close();
    }
}
```

`Application` has a `stop()` method, called when the program ends, that
does nothing unless you override it. It's the right place to close the
database.

### 5. Binding and listening (Module 18)

```java
add.disableProperty().bind(idField.textProperty().isEmpty().or(nameField.textProperty().isEmpty()));
update.disableProperty().bind(table.getSelectionModel().selectedItemProperty().isNull());
searchField.textProperty().addListener((obs, oldText, newText) -> refresh());
```

Add stays greyed out until there's an ID and a name; Update and Delete
until a row is chosen. The table re-runs the SQL search on every key
press. (With thousands of rows, you'd wait until the user stops typing;
with a class list, it's instant.)

## Running it without Maven (the JavaFX SDK)

If your lab uses the JavaFX SDK instead of Maven, put the SQLite driver
jar in a `lib` folder next to `src`, and give both folders to `javac`
and `java` on the **module path**:

```bash
javac --module-path /path/to/javafx-sdk-21/lib:lib -d out $(find src/main/java -name "*.java")
java  --module-path /path/to/javafx-sdk-21/lib:lib:out -m com.makerspace.records/com.makerspace.records.RecordsApp
```

On Windows (PowerShell), the separator is `;`, so quote the path, and
list the source files yourself:

```powershell
javac --module-path "C:\javafx-sdk-21\lib;lib" -d out src\main\java\module-info.java src\main\java\com\makerspace\records\*.java
java  --module-path "C:\javafx-sdk-21\lib;lib;out" -m com.makerspace.records/com.makerspace.records.RecordsApp
```

`-m module/class` runs a class inside a module. Because this program has
a `module-info.java` that `requires` what it needs, it doesn't need
`--add-modules`. (The driver jar is in Module 14b's `lib/` folder, or on
[Maven Central](https://central.sonatype.com/artifact/org.xerial/sqlite-jdbc).)

## When it goes wrong

| Message | Cause |
|---|---|
| JavaFX runtime components are missing | Java was started without JavaFX. Use `./mvnw javafx:run`, or the module path above. |
| package java.sql is not visible | `module-info.java` has neither `requires java.sql;` nor `requires org.xerial.sqlitejdbc;`. |
| Module org.xerial.sqlitejdbc not found (or "module not found" from `javac`) | The driver jar isn't on the module path: check `pom.xml`, or the `lib` folder in the SDK route. |
| No suitable driver found for jdbc:sqlite:students.db | The URL is misspelt (it's `jdbc:sqlite:` with two colons), or the driver jar is missing and nothing `requires` it. |
| [SQLITE_BUSY] database is locked | Another program has `students.db` open with changes: a second copy of the app, or DB Browser for SQLite with unsaved edits. |
| The data vanished | You ran from a different folder: `students.db` is created in the folder you run from. |

## Make it yours

Each of these is a good exercise in changing all three layers carefully:

1. **Add an email column.** Change the record (with validation), the
   table (`email TEXT`), every SQL statement in the repository, the
   form, and a table column. Run the tests after each step. (An existing
   `students.db` won't have the new column: delete it to start again.)
2. **A "probation" check box** that shows only students with a GPA below
   2.0. Add a repository method with `WHERE gpa < ?`.
3. **Export to CSV** from a menu, with a `FileChooser` (Module 14).
4. **Import a CSV** of students in one transaction, reporting bad lines
   in a dialog.
5. **A second table**, such as `courses` and `enrolments`, and a `JOIN`.

Add `*.db` to your `.gitignore`: the database is data, not source code.
