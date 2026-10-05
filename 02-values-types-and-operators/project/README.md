# Module 2 Project: Conversion Cheat Sheet

Build a program that prints a neat, useful **conversion cheat sheet**:
the kind of reference card you'd stick on a wall or keep on your phone.
Java does every calculation. You never work out an answer by hand.

```
==================================================
           MY CONVERSION CHEAT SHEET
==================================================
TEMPERATURE (Celsius -> Fahrenheit)
   0 C  =  32.0 F    water freezes
  25 C  =  77.0 F    a pleasant day
  37 C  =  98.6 F    body temperature
 100 C  = 212.0 F    water boils

DISTANCE (kilometres -> miles)
  ...
```

## Requirements

Your cheat sheet must include **at least four sections**, with at least
three conversions each:

1. **Temperature**: Celsius to Fahrenheit (`F = C × 9 / 5 + 32`).
2. **Distance**: kilometres to miles (1 km ≈ 0.621371 miles).
3. **Time**: break large numbers of minutes or seconds into
   hours/minutes/seconds using integer `/` and `%`
   (e.g. `200 minutes = 3 h 20 min`).
4. **One section of your choice**: currency (look up today's cedi
   exchange rate and write the date you checked it in a comment), kg to
   pounds (1 kg ≈ 2.20462 lb), litres to gallons, data sizes (bytes to
   KB/MB/GB), or anything else you find useful.

And it must:

5. Use **at least five different operators** from this module,
   including integer `/` and `%`.
6. Use `Math.round` so no result shows more than **2 decimal places**.
7. Use `"=".repeat(50)` (or similar) for at least one divider line.
8. Have a comment at the start of each section, and a comment giving the
   formula for each kind of conversion.

## Ideas if you're stuck

- Get **one line** working first, e.g.
  `System.out.println(" 37 C =  " + (37 * 9.0 / 5 + 32) + " F");`,
  then copy and adapt it.
- Getting `98.60000000000001`? That's the double fuzziness from this
  module. `Math.round(x * 10) / 10.0` fixes it.
- Getting `32` where you expected `98.6`? You've used int division
  somewhere: `37 * 9 / 5` is `66`, not `66.6`. Make one of the numbers a
  double (`9.0`).
- Getting `3.0` instead of `3.5` after rounding? You divided by `10`
  instead of `10.0`.
- Notice how often you're typing the same numbers (like `0.621371`) over
  and over? That's annoying, and it's easy to mistype one copy. Module 3
  introduces proper **variables and constants**, which fix exactly this
  problem. Remember the pain so you appreciate the cure!
- Finished early? Add a reverse section (Fahrenheit back to Celsius), and
  check that converting there and back gets you the original number.

## Getting started

Copy [`CheatSheet.java`](CheatSheet.java) into your coursework folder
and follow the TODOs. Run it after every section.

```bash
java CheatSheet.java
```

## Done?

```bash
git add .
git commit -m "Complete Module 2 project: conversion cheat sheet"
git push
```
