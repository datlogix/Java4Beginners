# Module 17 Project: Build a Tested Toolkit

Pick **one** track. You'll build a small, **professional** Java library:
organised into **packages**, built with **Maven**, documented with
**Javadoc**, proven correct by **at least 20 JUnit tests**, packaged as
a **runnable JAR**, and with a command-line program that uses it. In
Module 19, your capstone can depend on this toolkit.

Each starter is a complete Maven project, with the Maven Wrapper:

```bash
cd ee_starter                  # or generic_starter, or biomedical_starter
./mvnw test                    # Windows: mvnw test
./mvnw -q compile exec:java -Dexec.args="series 100 220 330"
./mvnw package                 # builds target/eetools-1.0.jar (and runs the tests first)
java -jar target/eetools-1.0.jar series 100 220 330
```

Open the starter folder (the one containing `pom.xml`) in VS Code, and
the Java extension recognises it as a Maven project: tests appear in
the **Testing** panel, with a ▶ beside each one.

## What every track must do

1. **Packages:** the tools in `com.makerspace.<toolkit>`, and the
   command-line program in `com.makerspace.<toolkit>.cli`. At least
   **three tool classes**, each `final` with a `private` constructor and
   only `static` methods (a *utility class*), like Java's own `Math`.
2. **At least 12 public methods** across the tool classes. Every method
   validates its arguments and throws `IllegalArgumentException` with a
   clear message for nonsense. **None of them prints anything.**
3. **Javadoc** on every public class and method, with `@param`,
   `@return` and `@throws` where they apply.
4. **At least 20 JUnit tests**, in test classes in the matching test
   package, including:
   - ordinary cases, **boundaries**, and **errors** (`assertThrows`);
   - at least one `@ParameterizedTest` with `@CsvSource`;
   - `assertEquals` with a **delta** for every `double` comparison.
   `./mvnw test` passes with no failures.
5. **A command-line program** (`...Cli`) that runs every tool from its
   arguments, prints friendly messages for bad input (never a stack
   trace), and supports `--json`: print or save the result as JSON using
   the **Gson** dependency already in the `pom.xml`.
6. `./mvnw package` builds a JAR that runs with `java -jar`.
7. A `README.md` in the project folder: what the toolkit does, how to
   build, test and run it, and an example of each command.

**Stretch (all tracks):** run `./mvnw javadoc:javadoc` and open
`target/reports/apidocs/index.html` (or `target/site/apidocs`) in a
browser: your own documentation, in the same style as Java's.

## Track A: Generic, texttools

A text-analysis toolkit, run on any text file:

- `TextTools` (started): `words`, `wordFrequencies`, `readingMinutes`,
  `slugify`, and at least three more, such as `sentences`,
  `averageWordLength`, `longestWords(n)`, `isPangram`.
- `Readability`: syllable counting (a simple rule: count groups of
  vowels, with a silent-`e` adjustment) and the **Flesch reading-ease
  score**: 206.835 − 1.015 × (words / sentences) − 84.6 × (syllables /
  words).
- `Ciphers`: a Caesar cipher (`encode(text, shift)`, `decode`), and a
  method that **cracks** one by trying all 26 shifts and choosing the one
  with the most common English words.
- The CLI: `texttools report file.txt`, `texttools caesar 3 "attack at
  dawn"`, `texttools crack "dwwdfn dw gdzq"`.

## Track B: EE, eetools

An electronics calculation toolkit:

- `Resistors` (started): `series`, `parallel`, `dividerOutput`, and the
  **colour code** (decode four colour names to ohms and tolerance, and
  encode a value back into colours).
- `SI`: `parse("4k7")` and `format(4700)` (your Module 13 parser, moved
  here as a tested class).
- `RcCircuits`: the time constant τ = RC, the voltage on a charging
  capacitor V(t) = V₀(1 − e^(−t/RC)), and the cut-off frequency of an RC
  filter f = 1 / (2πRC).
- `Leds`: the series resistor for an LED, rounded up to the next E12
  value (Module 13 again), with its power.
- The CLI: `eetools series 100 220 4k7`, `eetools colour yellow violet
  red gold`, `eetools rc 10k 100n`, `eetools led 9 2.0 20m`.

**Check:** a 10 kΩ, 100 nF RC filter has τ = 1 ms and a cut-off of
about 159.2 Hz; a capacitor charges to 63.2% of V₀ after one τ.

## Track C: Biomedical, biotools

A clinical calculation toolkit:

- `Body` (started): `bmi`, `bmiCategory`, `bsaMosteller`, and ideal
  body weight (Devine formula: 50 kg for men or 45.5 kg for women, plus
  2.3 kg per inch over 5 feet).
- `Dosing`: dose by weight (mg/kg) with a maximum (your Module 13
  exception, moved here), dose by body surface area (mg/m²), and
  infusion rate in mL/h from a dose and a concentration.
- `Conversions`: blood glucose mg/dL ↔ mmol/L (divide or multiply by
  18.0), temperature °C ↔ °F, and pounds ↔ kg.
- `HeartRate`: maximum heart rate (220 − age), and the five training
  zones (50–60%, 60–70%, 70–80%, 80–90% and 90–100% of the maximum),
  returned as a list of records.
- The CLI: `biotools bmi 70 1.75`, `biotools bsa 70 1.75`, `biotools
  glucose 126 mg/dL`, `biotools zones 30`.

**Check:** 70 kg and 1.75 m give a BMI of 22.9 ("healthy") and a
Mosteller BSA of about 1.85 m². 126 mg/dL is 7.0 mmol/L.

> Formulas are standard but simplified for teaching. This is not a
> clinical tool.

## Ideas if you're stuck

- **Write the test first** (Exercise 2): it forces you to decide exactly
  what the method should do, including the awkward cases.
- A test class is just a class. Use `@BeforeEach`, helper methods, and
  constants to keep it tidy.
- `mvnw: Permission denied` on macOS/Linux? Run `chmod +x mvnw` once.
- "package does not exist" or "cannot find symbol" for a class in
  another package: check the `package` line, the folder, and the
  `import`.
- The first `./mvnw` run downloads Maven and the libraries, so it needs
  the internet and takes a minute. After that it works offline.

## Done?

```bash
git add .
git commit -m "Complete Module 17 project: a tested toolkit"
git push
```

Don't commit the `target` folder: add `target/` to your coursework
repository's `.gitignore`. It's rebuilt by Maven whenever you need it.
