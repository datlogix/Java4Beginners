# Module 14b: Databases with SQLite & JDBC (optional)

> **This module is optional.** Module 15 doesn't depend on it. Do it if
> you'll build software that keeps lots of records (student records,
> stock, readings, patients), or if a course assessment asks for a
> database. It follows on directly from Module 14.

## Hook: two thousand students, five questions

From this module's folder, run
[`examples/Ex00StudentDatabase.java`](examples/Ex00StudentDatabase.java):

```bash
java -cp "lib/*" examples/Ex00StudentDatabase.java
```

```
Built school.db with 2,000 students in 1211 ms.

How many students in each programme?
  SQL: SELECT programme, COUNT(*) FROM students GROUP BY programme ORDER BY programme
    BME 375
    CIV 395
    CSC 429
    EEE 407
    MEC 394

The top five students?
  SQL: SELECT id, name, programme, gpa FROM students ORDER BY gpa DESC, id LIMIT 5
    S0950 Nii Appiah CSC 4.0
    S1043 Esi Darko BME 4.0
    ...
How many are on probation (GPA below 2.0)?
  SQL: SELECT COUNT(*) FROM students WHERE gpa < 2.0
    386
```

The program built a **database** of 2,000 students in a file called
`school.db`, and then asked it five questions. Each question is a single
line in **SQL** (Structured Query Language), the language almost every
database in the world understands. The database did the searching,
sorting, counting and averaging; Java just asked and printed.

In Module 14 you stored records in CSV files. That works, until you have
thousands of records, several programs using them at once, or data that
must never be half-saved. That's what **databases** are for. This module
uses **SQLite**, a complete database in a single file, through **JDBC**,
Java's standard way of talking to any database.

## What you need: the SQLite driver

Java's JDBC classes (in `java.sql`) know how to talk to *a* database,
but each kind of database needs its own **driver**: a library, in a
`.jar` file, that does the actual talking. The SQLite driver is
`sqlite-jdbc`, and it's already in this module's [`lib/`](lib/) folder.
It contains SQLite itself, so there's nothing else to install.

To use a library, put it on the **class path** with `-cp`:

```bash
java -cp "lib/*" examples/Ex01Connecting.java
```

`"lib/*"` means "every `.jar` file in the `lib` folder". Keep the
quotation marks: they work the same in PowerShell, Command Prompt, and
the macOS and Linux terminals. Run every example and exercise **from
this module's folder**, so `lib/` and `data/` are where Java expects.

> **In VS Code:** the Java extension automatically uses any `.jar` files
> in a `lib` folder at the top of the folder you opened. If you open
> this module's folder, the ▶ button just works. In your own coursework
> folder, either put `lib/` at the top, or add the jar under **Java
> Projects → Referenced Libraries**.

Once you've met **Maven** (Module 17), you won't need a `lib` folder:
you'll add the driver to a project as a dependency in `pom.xml`, and
Maven downloads it:

```xml
<dependency>
    <groupId>org.xerial</groupId>
    <artifactId>sqlite-jdbc</artifactId>
    <version>3.53.4.0</version>
</dependency>
```

## Tables, rows and columns

A database holds **tables**. A table looks like a CSV file: **columns**
across the top, one **row** per record. But unlike a CSV file, a table
has **rules**:

```sql
CREATE TABLE products (
    code      TEXT PRIMARY KEY,                -- unique: no two products share a code
    name      TEXT NOT NULL,                   -- must be given
    category  TEXT NOT NULL DEFAULT 'Other',
    price     REAL NOT NULL CHECK (price >= 0), -- the database refuses negatives
    quantity  INTEGER NOT NULL DEFAULT 0
)
```

| SQLite type | Holds | Java side |
|---|---|---|
| `INTEGER` | whole numbers | `int`, `long` |
| `REAL` | decimals | `double` |
| `TEXT` | text | `String` |

The **constraints** are the database version of Module 10's validation:
a `PRIMARY KEY` must be unique, `NOT NULL` means a value is required, and
`CHECK` tests any condition. Break one, and the database refuses the
change with an `SQLException`, so bad data never gets in, whichever
program is writing. (SQLite has no `true`/`false` type: use an `INTEGER`
holding 0 or 1. Store dates as `TEXT` like `2026-10-04`, which sorts
correctly.) See [`examples/Ex02CreateTable.java`](examples/Ex02CreateTable.java).

## Connecting

```java
try (Connection db = DriverManager.getConnection("jdbc:sqlite:school.db")) {
    // ... use db ...
}
```

- The **URL** `jdbc:sqlite:school.db` says "SQLite, the file
  `school.db`". If the file doesn't exist, it's created.
- `jdbc:sqlite::memory:` makes a database in memory, which vanishes when
  the connection closes. It's perfect for tests and exercises.
- A `Connection` holds a resource open, like a file reader, so open it
  in **try-with-resources** (Module 14), and it's always closed.
- Every JDBC method can throw **`SQLException`**, a checked exception
  (Module 13): catch it, or declare `throws SQLException`.

See [`examples/Ex01Connecting.java`](examples/Ex01Connecting.java).

## The four operations: CRUD

Almost everything a program does with stored data is one of four
operations, known as **CRUD**:

| Operation | SQL | JDBC method |
|---|---|---|
| **C**reate | `INSERT INTO products (code, name) VALUES (?, ?)` | `executeUpdate()` |
| **R**ead | `SELECT name, price FROM products WHERE price < ?` | `executeQuery()` → a `ResultSet` |
| **U**pdate | `UPDATE products SET quantity = ? WHERE code = ?` | `executeUpdate()` |
| **D**elete | `DELETE FROM products WHERE code = ?` | `executeUpdate()` |

`executeUpdate()` returns **how many rows changed**, which is how you
know whether an update or delete found anything to change.

### Create: `INSERT` with a `PreparedStatement`

```java
String sql = "INSERT INTO products (code, name, category, price, quantity) VALUES (?, ?, ?, ?, ?)";
try (PreparedStatement insert = db.prepareStatement(sql)) {
    insert.setString(1, "A01");          // fill in the first ?
    insert.setString(2, "Arduino Uno");
    insert.setString(3, "Boards");
    insert.setDouble(4, 180.0);
    insert.setInt(5, 12);
    insert.executeUpdate();
}
```

The SQL is written once, with a **`?` placeholder** for each value; then
`setString`, `setInt` and `setDouble` fill them in, numbered **from 1**.
The same prepared statement can be filled in and run again and again,
which is how [`examples/Ex03Insert.java`](examples/Ex03Insert.java)
inserts a whole list of records.

### Read: `SELECT` and the `ResultSet`

```java
try (PreparedStatement query = db.prepareStatement(
         "SELECT code, name, price FROM products WHERE price < ? ORDER BY price DESC")) {
    query.setDouble(1, 50.0);
    try (ResultSet rows = query.executeQuery()) {
        while (rows.next()) {
            String name = rows.getString("name");
            double price = rows.getDouble("price");
            ...
        }
    }
}
```

A `ResultSet` is a **cursor** that starts *before* the first row. Each
`next()` moves to the next row, and returns `false` when there are no
more, so `while (rows.next())` visits every row. Read the current row's
columns with `getString`, `getInt` and `getDouble`, by name or by
position (from 1). To look up one row by its key, check whether `next()`
found anything: `if (rows.next()) { ... } else { not found }`.

The parts of a `SELECT`:

| Part | Does | Example |
|---|---|---|
| `SELECT columns` | which columns (`*` for all) | `SELECT name, price` |
| `FROM table` | which table | `FROM products` |
| `WHERE condition` | which rows (`AND`, `OR`, `NOT`, `LIKE`) | `WHERE price < ? AND category = ?` |
| `ORDER BY column` | sorting (`DESC` for biggest first) | `ORDER BY price DESC` |
| `LIMIT n` | at most n rows | `LIMIT 5` |

`LIKE` matches patterns: `%` means "any characters", so
`name LIKE '%sensor%'` finds every name containing "sensor". See
[`examples/Ex04Query.java`](examples/Ex04Query.java).

### Update and delete

```java
try (PreparedStatement sell = db.prepareStatement(
         "UPDATE products SET quantity = quantity - ? WHERE code = ? AND quantity >= ?")) {
    ...
    int changed = sell.executeUpdate();     // 0 means nothing matched
}
```

**Always include a `WHERE` clause.** `UPDATE products SET price = 0`
with no `WHERE` changes *every* row, and `DELETE FROM products` empties
the whole table. Putting the rule in the `WHERE` (here, "only if there's
enough stock") makes the check and the change happen in one step. See
[`examples/Ex05UpdateDelete.java`](examples/Ex05UpdateDelete.java).

## SQL injection: why `PreparedStatement` matters

It's tempting to build SQL by joining text, as you've joined text since
Module 1:

```java
// NEVER DO THIS
String sql = "SELECT COUNT(*) FROM users WHERE name = '" + user + "' AND password = '" + password + "'";
```

Run [`examples/Ex06SqlInjection.java`](examples/Ex06SqlInjection.java).
An attacker who types the password `anything' OR '1'='1` turns the
query into:

```sql
SELECT COUNT(*) FROM users WHERE name = 'admin' AND password = 'anything' OR '1'='1'
```

The quote mark in their "password" ends the text early, and the rest of
what they typed becomes **SQL code**. `'1'='1'` is always true, so they
log in without knowing the password. This is **SQL injection**, one of
the most common ways real systems are broken into, and the same trick
can read, change or delete any data in the database.

With a `PreparedStatement`, the SQL is fixed **before** the user's text
arrives, and the text is sent separately, **only ever as data**. The
attack becomes nothing more than a strange password that doesn't match.
So the rule is simple: **every value that comes from a user, a file or
anywhere outside your code goes in through a `?` placeholder.** No
exceptions, even when you're "sure" the value is safe.

## Transactions: all or nothing

Some changes belong together. Moving money from Ama to Kojo is two
updates: take it from Ama, give it to Kojo. If the program crashed
between them, the money would vanish. A **transaction** makes a group of
changes happen **all together, or not at all**:

```java
db.setAutoCommit(false);          // start a transaction
try {
    ... take from Ama ...
    ... give to Kojo ...
    db.commit();                  // make every change permanent, together
} catch (SQLException e) {
    db.rollback();                // undo everything since setAutoCommit(false)
} finally {
    db.setAutoCommit(true);
}
```

Normally every statement is its own transaction ("auto-commit"), and
each one waits for the data to be safely written to the disk. So
transactions also make **big imports dramatically faster**:
[`examples/Ex07Transactions.java`](examples/Ex07Transactions.java)
inserts 5,000 rows one at a time (several seconds) and then in a single
transaction (a fraction of a second). Import files in one transaction.

## Let the database do the arithmetic

SQL can summarise data directly, like Module 15's collectors:

```sql
SELECT programme,
       COUNT(*)           AS students,
       ROUND(AVG(gpa), 2) AS average
FROM students
GROUP BY programme
ORDER BY average DESC
```

| Function | Gives |
|---|---|
| `COUNT(*)` | how many rows |
| `SUM(x)`, `AVG(x)` | total, average |
| `MIN(x)`, `MAX(x)` | smallest, largest |
| `GROUP BY column` | one result row per group |
| `HAVING condition` | filters the *groups* (`WHERE` filters rows before grouping) |
| `CASE WHEN … THEN … ELSE … END` | an if-else inside SQL |

See [`examples/Ex08Aggregates.java`](examples/Ex08Aggregates.java) (run
the hook first, since it uses `school.db`).

## Keep the SQL in one place: the repository

If SQL is scattered through a program, every change to the table means
hunting through every class. Instead, put **all** of it in one
**repository** class, whose methods take and return ordinary objects:

```java
class ContactRepository implements AutoCloseable {
    Contact add(String name, String phone) throws SQLException { ... }
    List<Contact> search(String text) throws SQLException { ... }
    Optional<Contact> find(int id) throws SQLException { ... }
    boolean changePhone(int id, String phone) throws SQLException { ... }
    boolean delete(int id) throws SQLException { ... }
    public void close() throws SQLException { db.close(); }
}
```

The menu code never sees a `Connection` or a `ResultSet`: it just calls
`contacts.add(...)` and gets a `Contact` record back. Implementing
`AutoCloseable` lets the repository itself go in try-with-resources. An
`INTEGER PRIMARY KEY AUTOINCREMENT` column numbers rows for you, and
`Statement.RETURN_GENERATED_KEYS` gets the new number back. See
[`examples/Ex09Repository.java`](examples/Ex09Repository.java), a
complete contacts manager. The rest of the program never sees any SQL,
so the storage could change without touching it. That's how Capstone 2
teams can swap their JSON storage for a database. Module 18's
[worked example](../18-guis-with-javafx/worked-example-student-records/README.md)
puts a repository like this behind a JavaFX window, in a Maven project.

## Common beginner mistakes

- **Joining user input into SQL.** Use `?` placeholders, always.
- **Forgetting the class path**: `No suitable driver found for
  jdbc:sqlite:...` means the jar isn't on the class path. Use
  `java -cp "lib/*" ...`, from the module folder.
- **Numbering placeholders from 0.** The first `?` is 1, and so is the
  first column of a `ResultSet`.
- **Reading a `ResultSet` without calling `next()` first.** It starts
  before the first row.
- **`UPDATE` or `DELETE` without a `WHERE`**, changing every row.
- **Not closing statements and result sets.** Use try-with-resources
  for all of them, not just the connection.
- **Inserting thousands of rows without a transaction**, and waiting.
- **Committing the `.db` file to Git.** Add `*.db` to your
  `.gitignore`: rebuild the database from your code and data files.

## Try it yourself

1. Run every file in [`examples/`](examples/), from this module's folder.
   Then open `school.db` in **DB Browser for SQLite** (free, for every
   operating system) and look inside.
2. Complete [`exercises/Exercise1.java`](exercises/Exercise1.java) (a
   library database, CRUD, with checks) and
   [`exercises/Exercise2.java`](exercises/Exercise2.java) (a CSV import
   and reports in SQL).
3. Build the [module project](project/README.md): your Module 14 track,
   on a database.
4. Commit and push your work:

   ```bash
   git add .
   git commit -m "Complete Module 14b: SQLite and JDBC"
   git push
   ```

Next: **[Module 15: Generics, Collections & Lambdas](../15-generics-collections-and-lambdas/README.md)**.
