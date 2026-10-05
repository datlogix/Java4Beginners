# Module 16 Project: Recursion and Algorithms at Work

Pick **one** track. Each one has a problem that's naturally
**recursive**, and an **algorithm comparison** where you measure how two
approaches scale as the input grows.

```bash
cd generic_starter          # or ee_starter, or biomedical_starter
javac -d out *.java
java -cp out MazeApp        # or NetworkApp, or BioApp
```

## What every track must do

1. **At least two recursive methods**, each with a Javadoc comment that
   states its **base case** and **recursive case**.
2. **Check your results** against a trusted answer: Java's own
   (`Arrays.sort`, `Collections.binarySearch`, `String.contains`), a
   slow-but-obviously-correct brute-force version, or hand-worked
   examples. Print PASS / FAIL for each check.
3. **A timing experiment** using `System.nanoTime()`, comparing two
   approaches on **at least three input sizes**, printed as a table.
   Warm up first (run each approach a few times before timing), as
   `Ex10SortingRace` does.
4. In your project README, a short paragraph: what **Big-O** did each
   approach show in your timings, and was that what you expected? Why?
5. Clean structure: one class per file, Javadoc comments, and constants
   for file paths. Your Module 13 and 14 error handling for files.

## Track A: Generic, Maze Solver

Two mazes are provided: `data/maze_small.txt` and `data/maze_large.txt`.
`#` is a wall, `S` is the start, and `E` is the exit.

1. `MazeSolver.solve(maze, row, col, visited)`: a **recursive
   depth-first search** with **backtracking**. From the current square,
   try each neighbour. If any recursive call reaches `E`, mark the
   current square with `.` and return `true`. If none do, return `false`.
   Print the solved maze and the length of the path it found.
2. `MazeSolver.shortestPath(maze)`: a **breadth-first search** using a
   queue (`ArrayDeque`), exploring squares in order of their distance
   from `S`. Return the length of the shortest path, and show whether the
   recursive search's path was the shortest. (Often it isn't!)
3. `MazeGenerator.generate(rows, cols, random)`: your **second
   recursive method**, the "recursive backtracker" described in its
   comment. Print a generated maze and solve it.
4. **Timing experiment:** generate mazes of increasing size (e.g. 21×21,
   41×41, 81×81, 161×161) and time the recursive solver against the BFS.
   (For very big mazes the recursion may hit a `StackOverflowError`: catch
   it, report it, and explain why in your README.)

**Check:** the shortest path is **66** moves in the small maze and
**290** in the large one.

## Track B: EE, Resistor Networks and Standard Values

1. A **network** is a `Resistor`, or a `Series` or `Parallel` group of
   smaller networks, nested to any depth: this is called the
   **Composite** pattern. `resistance()` and `describe()` are
   **recursive**: a group asks each of its parts, and the parts may be
   groups themselves. Write `Parallel`, and finish `Series`.
   `describe()` gives a readable formula with brackets:
   `100 + (220 || (100 + 230)) + 47`.
2. `StandardValues.e12()` builds every E12 value from 1 Ω to 10 MΩ, in
   order, and `nearest(values, target)` finds the closest standard value
   by **binary search**. (Write the binary search yourself, then check it
   against `Collections.binarySearch`.)
3. **Two-resistor combinations:** given a target that isn't an E12 value
   (like 1234 Ω), find the pair of E12 values whose **series** or
   **parallel** combination comes closest. First by brute force (try
   every pair: O(n²)), then smarter: for each first resistor, work out
   what the second *should* be and binary search for it (O(n log n)).
   Both must give the same answer.
4. **Timing experiment:** compare the two searches using the E12, E24
   and E96 series (look up E24 and E96, or generate them:
   10^(k/24) and 10^(k/96), rounded) or more decades.

**Check:** `100 + (220 || (100 + 230)) + 47` is **279 Ω**. The nearest
E12 value to 1234 Ω is 1.2 kΩ.

## Track C: Biomedical, DNA Toolkit

`data/sequence.txt` holds 6000 bases of DNA (A, C, G, T), 60 to a line.

1. In `Dna`, write **recursively**: `reverseComplement` (reverse the
   sequence and swap A↔T and C↔G), `gcCount`, and `isReversePalindrome`
   (a sequence equal to its own reverse complement, like the
   restriction sites `GAATTC` and `GGATCC`). For a sequence thousands
   of bases long, recursion may overflow the stack: process the sequence
   in 60-base chunks, and explain why in your README.
2. Find every position of the **restriction sites** EcoRI (`GAATTC`),
   BamHI (`GGATCC`) and HindIII (`AAGCTT`), and check each with
   `isReversePalindrome`. Report the GC content as a percentage.
3. `Sorting.mergeSort(list, comparator)`: a **generic, recursive merge
   sort**. Use it to sort a list of at least ten `PatientRecord`s (a
   record you write) by different comparators: by priority then arrival,
   by age, by name. Check each result against `List.sort`.
4. **Timing experiment:** make sorted lists of 10,000, 100,000 and
   1,000,000 patient IDs (`P0000001`…), and time a **linear search**
   against your own **binary search** for IDs near the end.

**Check:** the sequence is **50.25%** GC. EcoRI appears **4** times (at
positions 120, 1685, 2615 and 2950, counting from 0), BamHI **3** times
and HindIII **2** times.

## Ideas if you're stuck

- Write the **base case first**, and test it on its own: an empty
  string, a single resistor, a maze where you start on the exit.
- Trust the recursion: write the recursive case *assuming* the call on
  the smaller problem already works. Then check that every call really
  is smaller, so it reaches the base case.
- Print with indentation by depth (as in `Ex02CallStackTrace`) to see
  what a recursive method is doing.
- If timings are all zero, the inputs are too small: make them bigger.
  If they're wildly different each run, warm up first and repeat each
  measurement a few times.

## Done?

```bash
git add .
git commit -m "Complete Module 16 project: recursion and algorithms"
git push
```
