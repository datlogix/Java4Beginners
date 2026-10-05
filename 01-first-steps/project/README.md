# Module 1 Project: Your Initials in Java Art

Use Java's drawing tools to draw **your initials** (or a short word, like
your nickname) in big, coloured block letters, then sign your artwork
with your full name underneath. It's a small program, but it uses
everything from this module: statements that run top to bottom,
semicolons and braces, comments, reading compiler errors when something
goes wrong, and the drawing commands.

## Requirements

Your program must:

1. Draw **at least two letters** using straight lines (`drawLine`)
   and/or shapes (`drawRect`, `drawOval`, `drawArc`).
2. Use **at least two different colours** (`setColor`) and a thicker
   pen than the default (`setStroke`).
3. Keep the letters separate: no stray line joining one letter to the
   next.
4. Write your full name under the drawing using `drawString`, in a font
   you chose with `setFont`.
5. Print a short message in the terminal when the window opens, e.g.
   `Artwork complete! Close the window to exit.`
6. Include comments that label each letter's section of code, e.g.
   `// --- Letter A ---`.

## Plan on paper first

Grab some squared paper and draw a 700 × 400 box: that's your window.
Draw your letters as straight-line shapes, and for each line write down
its start and end points: "from (100, 80) to (100, 260)…" Five minutes
of planning saves thirty minutes of guessing.

Remember: in Java's drawing window, `(0, 0)` is the **top-left
corner**. `x` grows to the **right** and `y` grows **downwards**, which
is the opposite of the graphs you drew at school. A point at
`(350, 200)` is in the middle of a 700 × 400 window.

## Ideas if you're stuck

- Get **one** letter perfect first, run it, *then* start the next.
  Never write the whole program before running it once.
- If a line isn't where you expected, check whether you swapped `x` and
  `y`, or forgot that `y` goes *down*.
- Curved letters (C, O, S, U, J) can use
  `pen.drawArc(x, y, width, height, startAngle, arcAngle)`. It draws
  part of an oval that fits inside the box at `(x, y)`. Angles are in
  degrees, `0` is "3 o'clock" and positive angles go anticlockwise. For
  example `pen.drawArc(300, 80, 160, 180, 90, 180)` draws the left half
  of an oval: a big letter C.
- Finished early? Add a coloured background, a border around the whole
  picture (`drawRect`), or fill a shape in with `fillRect`, `fillOval`
  or `fillArc`. Colours can be mixed by hand, too:
  `new Color(255, 140, 0)` is red, green and blue values from 0 to 255.

## Getting started

Copy [`Initials.java`](Initials.java) into your
`java-coursework/01-first-steps/project` folder, open it, and follow the
TODO comments in order. Run it after each TODO, not just at the end.

```bash
java Initials.java
```

## Done?

Commit and push it:

```bash
git add .
git commit -m "Complete Module 1 project: initials in Java art"
git push
```

Take a screenshot of your artwork too. It's a nice thing to add to your
coursework repo's README later.
