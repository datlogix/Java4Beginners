# Module 11 Project: Families of Classes

Pick **one** track. Each one is a family of related classes: an
**abstract superclass** that captures what they all share, several
**subclasses** that each do one thing differently, and a class that
works with the whole family **polymorphically**, without caring which
kind of object it has.

As in Module 10, each starter is a folder with one class per file:

```bash
cd generic_starter          # or ee_starter, or biomedical_starter
javac -d out *.java
java -cp out PayrollApp     # or CircuitApp, or DeviceApp
```

## What every track must do

1. **An abstract superclass** with private fields, a `protected`
   constructor that validates its arguments, at least two `abstract`
   methods, and at least one normal method that *uses* the abstract ones.
2. **At least four concrete subclasses**, each calling `super(...)` in
   its constructor and overriding every abstract method with
   `@Override`. The starter gives you one or two; write the rest.
3. **At least one subclass that extends a method** rather than replacing
   it, by calling `super.method()` and adding to the result.
4. **A collection class** (`Payroll`, `SeriesCircuit`,
   `DeviceRegistry`) holding a `List` of the **superclass** type, whose
   methods loop over the list and call the overridden methods. **No
   `if`/`switch` on the kind of object** inside these loops. That's what
   polymorphism replaces. (`instanceof` is only allowed where the
   README below says so.)
5. A **menu-driven app** with starting data, catching exceptions for
   friendly messages, as in Module 10.
6. Override **`toString`** in the superclass (and in subclasses where
   it helps).

**Stretch (all tracks):** override `equals` and `hashCode` so that two
objects with the same ID/serial/label are equal, and use a `HashSet` to
prove it.

## Track A: Generic, Staff Payroll

A university department pays its staff from a **basic pay (BP)**, with
the same allowances and deductions for everyone:

| Item | Amount |
|---|---|
| DA (dearness allowance) | 97% of BP |
| HRA (house rent allowance) | 10% of BP |
| PF (provident fund) | 12% of BP (deducted) |
| Staff club fund | 0.1% of BP (deducted) |
| **Gross pay** | BP + DA + HRA |
| **Net pay** | Gross − PF − staff club |

1. `Employee` (abstract) has an ID, name, address, email, mobile number
   and basic pay, and works out every figure in the table. Its
   `payslip()` returns a neat multi-line pay slip.
2. Subclasses `Programmer`, `AssistantProfessor`, `AssociateProfessor`
   and `Professor`, each with its own `designation()` and
   `minimumBasicPay()` (3000, 6000, 7500 and 9000). The constructor
   refuses a basic pay below the minimum for that kind of employee.
3. `Professor` also gets a **research allowance** of 5% of BP, added to
   gross pay (override `gross()` using `super.gross()`), and shown on
   the pay slip.
4. `Payroll` hires staff (no duplicate IDs), finds them, totals gross
   and net pay, finds the highest paid, and prints a summary table.

**Check by hand:** a Programmer on BP 4000 has gross GHS 8280.00 and net
GHS 7796.00. A Professor on BP 12000 has gross GHS 25440.00 and net
GHS 23988.00.

## Track B: EE, AC Circuit Components

In an AC circuit, every component has a **resistance** R and a
**reactance** X that depends on the frequency f:

| Component | R | X |
|---|---|---|
| Resistor | its resistance | 0 |
| Inductor | its (small) winding resistance | X_L = 2πfL |
| Capacitor | 0 | X_C = −1 / (2πfC) |

The **impedance** magnitude is |Z| = √(R² + X²). In a series circuit,
the R values add, and the X values add.

1. `Component` (abstract) has a label, abstract `resistance()`,
   `reactance(f)` and `valueText()`, and a normal `impedance(f)` that
   uses them. Write the static `si(value)` helper that formats numbers
   with SI prefixes (`4.7 k`, `10 m`, `100 n`).
2. Subclasses `Resistor`, `Inductor` (given), `Capacitor`, and one more
   of your choice: perhaps a `Lamp` (a resistor with a power rating), or
   a `Motor` (an inductor with a stated efficiency).
3. `SeriesCircuit` holds components and an RMS supply voltage, and works
   out total R, total X, |Z|, current I = V / |Z| and the phase angle
   φ = atan(X / R) at any frequency, plus a **frequency sweep** table.
4. `resonantFrequency()` returns f₀ = 1 / (2π√(LC)), where L is the
   total inductance and 1/C = 1/C₁ + 1/C₂ + …. This method may use
   `instanceof` to find the inductors and capacitors.

**Check by hand:** 10 V across R1 = 100 Ω, L1 = 10 mH (2.5 Ω winding)
and C1 = 100 nF resonates at about **5032.9 Hz**, where |Z| = 102.5 Ω
and I ≈ 97.6 mA. At 1 kHz, |Z| ≈ 1532.1 Ω and φ ≈ −86.2° (the circuit
is mostly capacitive).

## Track C: Biomedical, Medical Device Fleet

A hospital keeps a register of every device on its wards. Each kind of
device records different readings, alarms for different reasons, and
needs calibrating at different intervals.

| Device | Records | Alarms when | Calibrate every |
|---|---|---|---|
| `Thermometer` (given) | temperature (°C) | ≥ 38.0 or < 35.0 | 365 days |
| `PulseOximeter` | SpO₂ (%) and pulse (bpm) | SpO₂ < 94, or pulse < 50 or > 120 | 180 days |
| `BloodPressureMonitor` | systolic and diastolic (mmHg) | systolic ≥ 180 or < 90, or diastolic ≥ 120 | 180 days |
| `InfusionPump` | rate (mL/h) and volume left (mL) of a bag of known size | less than 10% of the bag left, or rate 0 (blocked) | 90 days |

1. `MedicalDevice` (abstract) has a serial number, ward and last
   calibration date (`java.time.LocalDate`), the abstract methods in the
   starter, and a normal `isCalibrationDue(today)` that uses
   `calibrationIntervalDays()`.
2. Write the three missing subclasses. Each `record(...)` method rejects
   impossible values, and `latestReading()` returns `"no readings"`
   until the first one.
3. `DeviceRegistry` registers devices (no duplicate serials), and lists
   alarms, devices due for calibration, and devices on a ward, plus a
   dashboard.
4. In the app, recording a reading needs different questions for each
   kind of device: this is where `instanceof` is allowed.

> Thresholds are simplified for teaching. This is not a clinical tool.

## Ideas if you're stuck

- Write the abstract class first, then **one** subclass, and test them
  together before writing the others.
- If two subclasses contain the same code, it probably belongs in the
  superclass.
- `@Override` on every overriding method. If you misspell a method
  name, the compiler tells you there's nothing to override, instead of
  silently creating a new method.
- Can't call a method through a superclass variable? The superclass
  doesn't declare it. Either add it to the superclass (as an abstract
  method), or ask whether you really need it there.

## Done?

```bash
git add .
git commit -m "Complete Module 11 project: inheritance and polymorphism"
git push
```
