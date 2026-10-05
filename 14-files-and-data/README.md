# Module 14: Files & Data

## Hook: a program that remembers you

Run [`examples/Ex00Remember.java`](examples/Ex00Remember.java):

```bash
java Ex00Remember.java
```

```
Hello! I don't think we've met. What's your name? Kojo
Nice to meet you, Kojo. I'll remember you.
```

Now run it **again**:

```
Welcome back, Kojo!
This is visit number 2. Last time was 4 Oct 2026 at 17:06.
```

And again. It counts every visit. Close VS Code, restart your computer,
come back next week: it still knows you. Every program you've written so
far has had **amnesia**: the moment it ended, everything in its
variables was gone. This one writes what it knows to a **file**,
`memory.txt`, and reads it back next time. Open the file in VS Code and
look: three ordinary lines of text. Delete it, and the program forgets
you.

Files are how programs keep data between runs: save games, settings,
spreadsheets, logs, photos. This module covers reading and writing text
files, the **CSV** format that spreadsheets and lab equipment use, and
how to survive the broken data that real files always contain.

## Paths: where a file is

A **`Path`** (from `java.nio.file`) says where a file is:

```java
Path file = Path.of("greeting.txt");             // in the current folder
Path data = Path.of("data", "students.csv");     // data/students.csv
```

`Path.of("data", "students.csv")` joins the parts with the right
separator for the computer it's running on: `/` on macOS and Linux, `\`
on Windows. So use separate parts rather than typing slashes yourself.

A path like `data/students.csv` is **relative**: it starts from the
**working directory**, the folder you were in when you ran `java`. If
Java says a file doesn't exist and you're sure it does, you're almost
always running from a different folder. `Path.of("").toAbsolutePath()`
tells you where Java is looking. (In VS Code, the ▶ button usually runs
from the folder you opened. Run the examples from the terminal, in the
`examples` folder, to be sure.)

## Writing and reading whole files

The `Files` class does most jobs in one line:

```java
Files.writeString(file, "Akwaaba!\nWelcome to file handling.\n");   // write text
Files.write(Path.of("shopping.txt"), List.of("rice", "tomatoes"));    // write lines

String everything = Files.readString(file);                // the whole file as one String
List<String> lines = Files.readAllLines(file);             // one String per line
boolean there = Files.exists(file);
```

- Writing **replaces** whatever was in the file before. If the file
  doesn't exist, it's created.
- `Files.write` adds the line endings for you; `writeString` writes
  exactly what you give it, so include the `\n`s.
- Every one of these can throw an **`IOException`** (a checked
  exception, from Module 13): the file might be missing, locked by
  another program, or on a disk that's full. So you must either catch it
  or declare `throws IOException`. Reading a file that doesn't exist
  throws its subclass `NoSuchFileException`.

See [`examples/Ex01WritingText.java`](examples/Ex01WritingText.java) and
[`examples/Ex02ReadingText.java`](examples/Ex02ReadingText.java).

### Appending

To add to the end of a file instead of replacing it, pass **open
options**:

```java
Files.writeString(log, line, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
```

`CREATE` makes the file if it's missing; `APPEND` adds to the end. This
is how **logs** work: every run adds a line, and nothing is ever lost.
Run [`examples/Ex03Appending.java`](examples/Ex03Appending.java) a few
times and watch `lab-log.txt` grow.

## Line by line, with try-with-resources

`readAllLines` loads the whole file into memory at once. That's fine for
the files in this course, but a sensor log can be gigabytes. To process
a file **one line at a time**, open a **reader**:

```java
try (BufferedReader reader = Files.newBufferedReader(input);
     PrintWriter writer = new PrintWriter(Files.newBufferedWriter(output))) {

    String line;
    while ((line = reader.readLine()) != null) {     // null means "end of the file"
        writer.println(line.toUpperCase());
    }
}
```

A reader or writer holds the file **open** while you use it, and must be
**closed** afterwards, or the operating system can run out of file
handles, and written data may never actually reach the disk. The
**try-with-resources** statement does it for you: anything opened in
the brackets after `try` is closed automatically when the block ends,
whether it finishes normally or with an exception. It's a neater,
safer version of the `finally` from Module 13. Always open readers and
writers this way.

`PrintWriter` gives a file the same `println` and `printf` you use with
`System.out`. You can also read a file with a `Scanner`, exactly as you
read the keyboard: `new Scanner(Path.of("data", "readings.txt"))`. See
[`examples/Ex04TryWithResources.java`](examples/Ex04TryWithResources.java)
and [`examples/Ex05ScannerOverFile.java`](examples/Ex05ScannerOverFile.java).

## CSV: data in rows and columns

A **CSV** (comma-separated values) file is a table in plain text. The
first line is usually a **header** naming the columns, and each
following line is one row:

```
id,name,programme,gpa
S001,Akua Mensah,BME,3.85
S002,Kwesi Appiah,EEE,3.10
```

Every spreadsheet can open and save CSV, and so can most lab equipment
and databases. Reading one is a combination of things you know: read
the lines, skip the header, `split(",")` each line, and turn the fields
into an object:

```java
for (int i = 1; i < lines.size(); i++) {        // start at 1: skip the header
    String[] f = lines.get(i).split(",");
    students.add(new Student(f[0], f[1], f[2], Double.parseDouble(f[3])));
}
```

Records (Module 12) are perfect for rows of data. Give the record a
`toCsv()` method that turns it back into a line, and a static
`fromCsv(line)` that builds one from a line, and saving and loading
becomes a **round trip**: save a list, load it back, and you get equal
objects. See [`examples/Ex07WritingCSV.java`](examples/Ex07WritingCSV.java).

> **Commas inside the data** (an address like `12 High St, Accra`)
> break simple splitting. Real CSV files put such fields in quotes,
> and libraries like OpenCSV handle that. In this course, reject commas
> in text fields instead.

### Real data is messy

Real files contain mistakes: a word where a number should be, a missing
field, a blank line. A program that crashes on the first bad line is
useless. Wrap the processing of **each line** in its own `try`/`catch`,
so a bad line is **skipped and reported**, and the rest still load:

```java
try {
    if (f.length != 4) {
        throw new IllegalArgumentException("expected 4 fields, found " + f.length);
    }
    students.add(new Student(f[0], f[1], f[2], Double.parseDouble(f[3])));
} catch (IllegalArgumentException e) {           // NumberFormatException is one of these
    System.out.println("  Skipping line " + (i + 1) + ": " + e.getMessage());
}
```

Telling the user **which line** and **why** turns a mystery into a
two-second fix. Run [`examples/Ex06ReadingCSV.java`](examples/Ex06ReadingCSV.java):
`data/students.csv` has a GPA written in words, a missing field and a
blank line, and the program loads everything else.

## Folders, and `Properties`

`Files` can also manage folders:

```java
Files.createDirectories(Path.of("output", "reports"));   // every missing folder in the path
Path report = folder.resolve("january.txt");             // folder + file name
Files.list(folder)                                       // the folder's contents
Files.delete(report);                                    // gone for good: no recycle bin!
```

See [`examples/Ex08Directories.java`](examples/Ex08Directories.java). For
simple settings, the **`Properties`** class reads and writes
`key=value` files, like a `HashMap` that saves itself. See
[`examples/Ex09Settings.java`](examples/Ex09Settings.java).

> **What about JSON?** Most data on the web is in **JSON** format
> (`{"name": "Akua", "gpa": 3.85}`). Java's standard library can't read
> JSON by itself. You need a **library**, such as Gson or Jackson. In
> Module 17 you'll add Gson to a project with Maven in one line.

## A note on text and bytes

Everything in this module reads and writes **text**: Java turns
characters into bytes using an encoding (UTF-8, by default, on modern
Java). Images, audio and compressed files aren't text. For those, use
`Files.readAllBytes(path)` and `Files.write(path, bytes)`, which work
with raw `byte[]` arrays. (In older Java code and textbooks you'll see
`FileReader`/`FileWriter` for **character streams** and
`FileInputStream`/`FileOutputStream` for **byte streams**: the same
ideas, with more typing.)

## Common beginner mistakes

- **The wrong working directory**, so relative paths point somewhere
  else. Check with `Path.of("").toAbsolutePath()`.
- **Forgetting that writing replaces the file.** Use `APPEND` to add to
  it.
- **Not closing readers and writers.** Use try-with-resources, always.
- **Typing `"data\\students.csv"` or `"data/students.csv"`** instead
  of `Path.of("data", "students.csv")`, so it breaks on another
  operating system.
- **Forgetting to skip the header line** of a CSV, so the first row
  fails to parse.
- **Letting one bad line crash the whole load.** Catch errors **per
  line**.
- **`split(",")` and empty last fields.** `"a,b,".split(",")` gives just
  two fields; Java drops empty strings at the end. Check the length.
- **Saving only when the program quits.** If it crashes, or the power
  goes off, everything is lost. Save after every change.

## Try it yourself

1. Run every file in [`examples/`](examples/), from the `examples`
   folder. Open every file they create, and look inside.
2. Complete [`exercises/Exercise1.java`](exercises/Exercise1.java) and
   [`exercises/Exercise2.java`](exercises/Exercise2.java).
3. Build the [module project](project/README.md) in the track of your
   choice: an expense tracker, a bench data analyser, or a patient
   vitals log.
4. Commit and push your work. Data files in `data/` folders are part of
   your project, so commit them too. Files your programs *generate*
   don't belong in Git: add a line saying `output/` to your coursework
   repository's `.gitignore` file, and they'll be left out.

   ```bash
   git add .
   git commit -m "Complete Module 14: files and data"
   git push
   ```

Next: **[Module 15: Generics, Collections & Lambdas](../15-generics-collections-and-lambdas/README.md)**.
