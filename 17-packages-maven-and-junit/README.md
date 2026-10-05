# Module 17: Packages, Maven & JUnit Testing

## Hook: a program that checks itself

Open a terminal in [`examples/00-leap-year-bug`](examples/00-leap-year-bug)
and type one command:

```bash
cd examples/00-leap-year-bug
./mvnw test                  # Windows: mvnw test
```

The first time, it downloads a tool called **Maven** and a testing
library called **JUnit** (it takes a minute, and needs the internet).
Then it compiles the project and runs seven **automated tests**. Two
fail:

```
[ERROR] Failures:
[ERROR]   DateToolsTest.lastDayOfALeapYear:40 expected: <366> but was: <365>
[ERROR]   DateToolsTest.yearDivisibleBy400IsLeap:30 2000 divides by 400, so it IS a leap year ==> expected: <true> but was: <false>
[ERROR] Tests run: 7, Failures: 2, Errors: 0, Skipped: 0
[ERROR] BUILD FAILURE
```

Somebody wrote the leap-year rule from Module 4 and forgot the 400
rule. Nobody had to run the program and notice a wrong date: the tests
**caught it automatically**, said exactly which check failed, what was
expected and what actually happened, and even pointed to the line.

Now open `src/main/java/com/makerspace/dates/DateTools.java`, fix
`isLeapYear`, and run `./mvnw test` again:

```
[INFO] Tests run: 7, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

That's how professional software is built. This module covers the three
things that hook used: **packages** (how large programs are organised),
**Maven** (the tool that builds them and fetches libraries), and
**JUnit** (automated tests).

## Packages

Every class so far has lived in the same folder, with no package. That's
fine for a dozen classes, but real projects have hundreds, and they
use libraries with thousands more. Two classes called `Reading` or
`Utils` would clash. **Packages** solve this: they group related
classes, like folders, and give each class a full name.

```java
package com.makerspace.util;      // the FIRST line of the file

public class TextTools {
    ...
}
```

- The class's full name is now `com.makerspace.util.TextTools`.
- The file **must** be in a matching folder: `com/makerspace/util/TextTools.java`.
- Package names are lower case, and by convention start with a reversed
  web domain (`com.makerspace`, `gh.edu.ug`), so they're unique worldwide.
  Java's own classes are in `java.util`, `java.nio.file`, and so on.

To use a class from **another** package, import it, exactly as you've
imported `java.util.Scanner` since Module 3:

```java
package com.makerspace.app;

import com.makerspace.util.TextTools;

public class Main { ... TextTools.titleCase("ama") ... }
```

Classes in `java.lang` (`String`, `Math`, `System`, `Integer`) are
imported automatically. And now the **package-private** access level
from Module 11 makes sense: a member with no modifier is visible only
inside its own package.

### Compiling packages by hand, and JAR files

[`examples/01-packages`](examples/01-packages) has two classes in two
packages, under a `src` folder:

```bash
cd examples/01-packages
javac -d out src/com/makerspace/util/TextTools.java src/com/makerspace/app/Main.java
java -cp out com.makerspace.app.Main
```

`-d out` puts the `.class` files in `out`, in matching package folders.
To run, give `java` the class's **full** name.

To hand a program to someone, you bundle its classes into one **JAR**
file (Java ARchive: a zip file with a description inside):

```bash
jar --create --file greeter.jar --main-class com.makerspace.app.Main -C out .
java -jar greeter.jar
```

Typing out every file and every library by hand quickly becomes
impossible. That's what build tools are for.

## Maven: the build tool

**Maven** is the most widely used Java build tool. You describe your
project once, in a file called **`pom.xml`**, and Maven does the rest:
compiling, downloading libraries, running tests, and building JARs.

### The standard layout

Every Maven project has the same shape, so anyone can find their way
around it:

```
my-project/
├── pom.xml                     what the project is, and what it needs
├── mvnw, mvnw.cmd, .mvn/       the Maven Wrapper (see below)
└── src/
    ├── main/java/              your code, in package folders
    │   └── com/makerspace/dates/DateTools.java
    └── test/java/              your tests, in the SAME packages
        └── com/makerspace/dates/DateToolsTest.java
```

Maven puts everything it builds in a `target` folder, which can always
be deleted and rebuilt. (Don't commit it to Git.)

### `pom.xml`

The **POM** (Project Object Model) is an XML file. The important parts:

```xml
<groupId>com.makerspace</groupId>        <!-- who made it -->
<artifactId>leap-year-bug</artifactId>   <!-- what it's called -->
<version>1.0</version>

<properties>
    <maven.compiler.release>17</maven.compiler.release>   <!-- the Java version -->
</properties>

<dependencies>
    <dependency>                         <!-- a library this project needs -->
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.11.4</version>
        <scope>test</scope>              <!-- only needed for testing -->
    </dependency>
</dependencies>
```

**Dependencies** are the big win. Name a library, and Maven downloads
it, and everything *it* needs, from **Maven Central**, a huge public
store of Java libraries, and puts it on the classpath for you.
[`examples/02-maven-json`](examples/02-maven-json) adds the **Gson**
library with five lines of XML, and uses it to save a list of records
as **JSON** and load them back, the job Module 14 said needed a library.
To find a library's `<dependency>` lines, search
[central.sonatype.com](https://central.sonatype.com).

### The Maven Wrapper and the lifecycle

Every project in this module includes the **Maven Wrapper**: the
`mvnw` script (`mvnw.cmd` on Windows) and the `.mvn` folder. It
downloads the right version of Maven the first time you use it, so you
never need to install Maven yourself. (If you *do* install it, `mvn`
works in place of `./mvnw`.)

You ask Maven to carry out **phases** of a standard **lifecycle**. Each
phase runs all the ones before it:

| Command | Does |
|---|---|
| `./mvnw compile` | Compiles `src/main/java` into `target/classes` |
| `./mvnw test` | Compiles, then runs every test in `src/test/java` |
| `./mvnw package` | Compiles, tests, and builds a JAR in `target/` (it won't build one if a test fails!) |
| `./mvnw clean` | Deletes the `target` folder |
| `./mvnw -q compile exec:java` | Compiles and runs the main class (set up in the course's `pom.xml` files) |

On macOS and Linux, type `./mvnw`; on Windows, type `mvnw`. If macOS or
Linux says "Permission denied", run `chmod +x mvnw` once. In VS Code,
open the folder containing `pom.xml`: the Java extension recognises the
Maven project, and a **Maven** panel lets you run these phases with a
click.

## JUnit: automated testing

In Module 8 you wrote a little `check` method that printed PASS or
FAIL. **JUnit** is the professional version: the standard testing
library for Java. A test is a method marked `@Test` that calls your code
and **asserts** what should be true:

```java
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class DateToolsTest {

    @Test
    void februaryInALeapYear() {
        assertEquals(29, DateTools.daysInMonth(2, 2024));    // (expected, actual)
    }
}
```

If every assertion holds, the test **passes**. If one doesn't, the test
**fails**, and JUnit reports the test's name, what it expected, and what
it got. Each test is independent: a failure in one doesn't stop the
others running.

| Assertion | Passes when… |
|---|---|
| `assertEquals(expected, actual)` | they're equal (using `equals`) |
| `assertEquals(expected, actual, delta)` | two doubles are within `delta` of each other. **Always** use a delta for doubles (Module 2's 0.1 + 0.2!) |
| `assertTrue(condition)` / `assertFalse(condition)` | the condition is true / false |
| `assertNull(x)` / `assertNotNull(x)` | `x` is / isn't `null` |
| `assertThrows(Type.class, () -> code)` | the code throws that exception (it gives you the exception, so you can check its message too) |
| `assertAll(() -> ..., () -> ...)` | every assertion inside passes (and it reports **every** failure, not just the first) |

Every assertion can take a last argument: a message to show if it fails,
as the hook's `yearDivisibleBy400IsLeap` test does.

[`examples/03-junit-basics`](examples/03-junit-basics) tests a shopping
cart, and shows the other features you'll use most:

- **`@BeforeEach`** marks a method that runs before **every** test,
  usually to create a fresh object, so tests can't affect each other.
- **`@DisplayName("...")`** gives a test a readable name in reports.
- **`@ParameterizedTest`** with **`@CsvSource`** runs the same test
  with a whole table of inputs and expected outputs: perfect for
  boundaries.

```java
@ParameterizedTest
@CsvSource({
    "499.99, 0",
    "500, 0.05",          // boundaries: test exactly AT them
    "1000, 0.10",
})
void discountRates(double subtotal, double expectedRate) {
    assertEquals(expectedRate, ShoppingCart.discountRate(subtotal), 0.0001);
}
```

Test classes live in `src/test/java`, in the **same package** as the
class they test (so they can even test package-private methods), and
are named after it: `ShoppingCart` → `ShoppingCartTest`. Name each test
method after **what it checks**: `zeroQuantityIsRefused` tells you what
broke before you even open the file. In VS Code, the **Testing** panel
(the flask icon) lists every test, with a ▶ to run one, and a green tick
or a red cross for the result.

### What to test

A good set of tests checks three kinds of case for every method:

1. **Ordinary cases**: typical inputs, with answers you've worked out
   by hand.
2. **Boundaries and edge cases**: exactly 500, not just 450 and 550; an
   empty list; a single item; zero; the largest allowed value. Most bugs
   live at boundaries.
3. **Errors**: invalid input must be refused (`assertThrows`).

Tests can only check what you think to check. They show the **presence**
of bugs, never their absence. But a project with good tests can be
changed with confidence: change something, run `./mvnw test`, and in
seconds you know whether you broke anything.

### Test-driven development

**Test-driven development (TDD)** turns testing round: write a test
**first**, for something the code doesn't do yet. Watch it fail
(**red**). Write just enough code to make it pass (**green**). Then
tidy the code, running the tests to check you haven't broken anything
(**refactor**). Repeat. Writing the test first forces you to decide
exactly what the code should do before you write it. Exercise 2 is a
TDD exercise: the tests are written, and you make them pass.

## Common beginner mistakes

- **A `package` line that doesn't match the folder.** `package
  com.makerspace.util;` must be in `com/makerspace/util/`.
- **Forgetting to import** a class from another package: "cannot find
  symbol".
- **Running a class without its full name**: `java -cp out Main`
  instead of `java -cp out com.makerspace.app.Main`.
- **Putting tests in `src/main/java`.** They go in `src/test/java`.
- **`assertEquals(actual, expected)`**: the wrong way round. The test
  still works, but failure messages say "expected" for the wrong value,
  which is very confusing. Expected comes first.
- **Comparing doubles without a delta.**
- **Tests that depend on each other**, or on the order they run in.
  Use `@BeforeEach` to start each test fresh.
- **Committing the `target` folder.** Add `target/` to `.gitignore`.
- **Testing only the easy cases.** Test the boundaries and the errors.

## Try it yourself

1. Fix the bug in [`examples/00-leap-year-bug`](examples/00-leap-year-bug)
   and get `BUILD SUCCESS`. Then run `./mvnw package` and
   `java -jar target/leap-year-bug-1.0.jar`.
2. Compile and run [`examples/01-packages`](examples/01-packages) by
   hand, and build its JAR. Run
   [`examples/02-maven-json`](examples/02-maven-json) and open the
   `stock.json` it writes. Run the tests in
   [`examples/03-junit-basics`](examples/03-junit-basics), then break
   `ShoppingCart` on purpose and watch which tests catch it.
3. Complete [`exercises/exercise1-find-the-bugs`](exercises/exercise1-find-the-bugs)
   (write tests that catch four hidden bugs) and
   [`exercises/exercise2-test-driven`](exercises/exercise2-test-driven)
   (make the given tests pass).
4. Build the [module project](project/README.md) in the track of your
   choice: a tested text, electronics, or clinical toolkit.
5. Commit and push your work (without any `target` folders):

   ```bash
   git add .
   git commit -m "Complete Module 17: packages, Maven and JUnit"
   git push
   ```

Next: **[Module 18: GUIs with JavaFX](../18-guis-with-javafx/README.md)**.
