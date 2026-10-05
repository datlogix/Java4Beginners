# Module 13 Project: Programs That Never Crash

Pick **one** track. Each is a program that people will type real (and
really wrong) data into. Your job is to make it **impossible to crash**,
and to give a **precise, helpful message** for every kind of problem,
using your own exception classes.

```bash
cd generic_starter          # or ee_starter, or biomedical_starter
javac -d out *.java
java -cp out AtmApp         # or CalcApp, or VitalsApp
```

## What every track must do

1. **At least three custom exception classes**, including at least one
   **checked** exception (`extends Exception`) and one **unchecked**
   (`extends RuntimeException`). Each has a constructor that builds a
   clear message with `super(...)`, and at least one carries **extra
   data** with a getter (an amount, a value, attempts left).
2. **Methods that detect problems `throw`**, and declare checked
   exceptions with `throws`. Methods that can fix or report the problem
   `catch`. Don't catch an exception just to ignore it.
3. **No crash, ever.** Not on letters where numbers go, empty input,
   negative numbers, huge numbers, or extra spaces. Try to break your
   program before anyone else does.
4. **Catch specific exceptions**, not just `Exception`, so each problem
   gets its own message.
5. Reuse your **Exercise 1 input helpers** (or equivalent) for every
   number you read.
6. A **self-test** menu option (or a `test` command-line argument) that
   checks your validation code with good and bad values, including the
   boundaries, and prints PASS/FAIL, as in Module 8.
7. In your project README, a short **debugging diary**: one bug you
   found with the VS Code debugger, where you put the breakpoint, and
   what the Variables panel showed.

## Track A: Generic, ATM Simulator

1. `Account` has a 4-digit PIN, a balance, a daily withdrawal limit of
   GHS 2000 and a transaction log.
2. `checkPin` throws `InvalidPinException` (given, checked) with the
   attempts left. After **three** wrong PINs the account locks, and every
   later attempt throws `AccountLockedException` (write it: unchecked).
3. `withdraw` refuses amounts that aren't positive multiples of 10
   (`IllegalArgumentException`), throws `InsufficientFundsException`
   (given), and throws `DailyLimitExceededException` (write it: checked,
   carrying how much may still be withdrawn today).
4. `AtmApp`: insert a card (account number), enter the PIN, then a menu:
   balance, withdraw, deposit, mini-statement (the last five log
   entries), eject card.

## Track B: EE, Safe Circuit Calculator

1. `SIParser.parse(text)` reads engineering notation: `470`, `4.7k`,
   `4k7`, `2.2M`, `100n`, `10u`, `33m`, `1p5`, with an optional unit
   (`4.7kOhm`, `100nF`, `12V`). Anything else throws
   `InvalidValueException` (given, checked), including blank text, an
   unknown letter, two prefixes, a negative value or zero.
2. Ohm's law and power calculations that read every value with
   `SIParser`, and print answers with `SIParser.format`.
3. An **LED resistor calculator**: from the supply voltage, the LED's
   forward voltage and the desired current, work out R = (V_s
   − V_f) / I, choose the **next E12 value above it** (10, 12,
   15, 18, 22, 27, 33, 39, 47, 56, 68, 82 × powers of ten), and work out
   the real current and the resistor's power. If the power is more than
   the resistor's rating (0.25 W unless the user says otherwise), throw
   `ComponentOverloadException` (write it: checked, carrying the power)
   and suggest a rating that would be safe. Refuse a forward voltage
   that isn't less than the supply.

**Check by hand:** 9 V, a 2.0 V red LED, 20 mA → 350 Ω → **390 Ω**,
17.9 mA, 0.125 W (fine at 0.25 W). But 24 V with the same LED → 1100 Ω →
**1.2 kΩ**, 18.3 mA, **0.40 W**: overloaded at 0.25 W, so use 0.5 W.

## Track C: Biomedical, Vitals Entry Validator

1. `VitalsValidator` checks heart rate, temperature, SpO₂ and blood
   pressure (typed as `120/80`) in two stages: physically impossible
   values throw `ImplausibleReadingException` (given); possible but
   dangerous values throw `CriticalValueException` (given). Text that
   isn't a number, or blood pressure without a single `/`, is
   implausible too.
2. `VitalsApp` keeps asking for each vital until it's accepted. For a
   **critical** value it asks the nurse to confirm. A confirmed critical
   value is accepted and added to an **escalation list**, printed at the
   end.
3. A **dose calculator**: dose (mg) = weight (kg) × dose per kg. Write
   `DoseCalculator.dose(weightKg, mgPerKg, maxMg)`, which rejects
   impossible weights (`IllegalArgumentException`) and throws
   `MaximumDoseExceededException` (write it: checked, carrying the
   calculated dose and the maximum) when the dose is over the maximum.
   The app then offers the maximum dose instead.

**Check by hand:** 15 mg/kg for a 20 kg child is 300 mg; for an 80 kg
adult it's 1200 mg, over a 1000 mg maximum, so the exception is thrown.

> Ranges and doses are simplified for teaching. This is not a clinical
> tool.

## Ideas if you're stuck

- **Write the exceptions first.** They're short, and once they exist the
  compiler tells you everywhere they need to be handled.
- "unreported exception … must be caught or declared to be thrown"
  means a checked exception isn't handled. Decide: can *this* method do
  something useful about it? If yes, `catch` it. If not, add `throws`
  and let the caller deal with it.
- Validation methods should only **throw**; they shouldn't print. Leave
  the talking to the app.
- A `catch` block that does nothing (`catch (Exception e) { }`) hides
  bugs. If you catch it, say something.

## Done?

```bash
git add .
git commit -m "Complete Module 13 project: exceptions and debugging"
git push
```
