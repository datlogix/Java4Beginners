# Module 9: Capstone 1, Text Adventure

Everything from Part 1 comes together here: variables, input, strings,
decisions, loops, arrays, lists, maps, sets, and methods, combined into
one real program. You'll build a **text adventure game**, the kind of
game people played before graphics: the player explores a world by
typing commands, picks up items, solves puzzles, and tries to reach an
ending.

```
=== THE LOST ROBOT OF MAKERSPACE ===

WORKSHOP
Soldering irons cool on the benches. A half-built robot slumps in the
corner, missing its battery. Doors lead north and east.
You see: screwdriver

> take screwdriver
You pick up the screwdriver.

> go north
STORE ROOM
Shelves of components stretch into the dark. The door to the south is
the way back.
You see: battery, torch

> help
Commands: go <direction>, take <item>, drop <item>, use <item>,
look, inventory, help, quit
```

It's built in a team, in stages, with every stage committed to GitHub,
so by the end you'll have a real project with a real history in your
portfolio.

## 1. How teams are formed

Your instructor announces the class size before this module starts,
which sets your group size:

| Class size | Group size | Ratio |
|---|---|---|
| Up to 20 students | Pairs | **2:1** |
| More than 20 students | Groups of 4 | **4:1** |
| Approved solo ("daring") students, any class size | Individual | **1:1** |

**Going solo ("daring" track):** any student may ask to build the
capstone alone instead of in a group. It isn't a smaller workload. It's
the same core requirements (§4) **plus every stretch goal** in §5, since
you don't have teammates sharing the work. Ask your instructor *before*
Stage 0 (§6). Going solo is a good fit if you're comfortable with
everything through Module 8 and want the extra challenge. It isn't a way
to avoid working with others.

## 2. Setting up your team's repository

1. **One** team member creates a new **private** GitHub repository named
   `text-adventure-<teamname>` (e.g. `text-adventure-robo-rangers`),
   with a README and the **Java** `.gitignore`.
2. They go to **Settings → Collaborators** and add every teammate *and*
   the instructor.
3. Every teammate clones the **same** repository:

   ```bash
   git clone https://github.com/OWNER-USERNAME/text-adventure-teamname.git
   cd text-adventure-teamname
   ```

4. Copy [`Adventure.java`](Adventure.java) into the repository, run it
   (`java Adventure.java`), commit it, and push. That's your Stage 0
   starting point.
5. Everyone commits under their **own** Git identity (`git config
   user.name` / `user.email` from Module 0). This is how your instructor
   sees who contributed what. A history where one teammate made every
   commit is a problem to fix early, not at the deadline.

### Working together without overwriting each other

When two people edit the same file, Git has to combine their changes.
Keep it painless:

- **Always `git pull` before you start working**, and before you push.
- **Split the work by method.** One person writes `takeItem` while
  another writes `showInventory`. Methods are what make teamwork
  possible. Agree on each method's name, parameters and return type
  first (write its Javadoc comment together), then each person fills
  in their own.
- **Commit small and often**, with clear messages:
  `"Add take command"` beats `"stuff"`.
- If `git pull` reports a **merge conflict**, Git marks the clashing
  lines in the file with `<<<<<<<`, `=======` and `>>>>>>>`. Open the
  file, decide what the code should be (often you keep both changes),
  delete the markers, make sure it still compiles, then `git add`,
  `git commit`, and `git push`. It's normal. Ask your instructor the
  first time it happens.

## 3. How the game is built: data + methods

The trick to a text adventure is that **the world is data**. Every room
has a short **key** (like `"workshop"`), and maps look up everything
about a room by its key:

```java
static final Map<String, String> ROOM_NAMES = new HashMap<>();
static final Map<String, String> DESCRIPTIONS = new HashMap<>();
static final Map<String, Map<String, String>> EXITS = new HashMap<>();   // room -> (direction -> room)
static final Map<String, List<String>> ITEMS = new HashMap<>();          // room -> items lying there
```

The starter's `buildWorld` method fills these in, using three small
helper methods:

```java
addRoom("workshop", "Workshop", "Soldering irons cool on the benches...");
addRoom("store room", "Store Room", "Shelves of components stretch into the dark...");
addExit("workshop", "north", "store room");
addExit("store room", "south", "workshop");
addItem("store room", "battery");
```

The player's state is a few simple variables in `main`: the key of the
`currentRoom` (a `String`), an `inventory` (a `List<String>`), and
perhaps a `Set<String>` of rooms they've visited. Moving north is just
looking up the exit:

```java
currentRoom = EXITS.get(currentRoom).get("north");
```

The **game loop** reads a command, splits it into a verb and a noun
(`"take battery"` → `"take"` and `"battery"`), and calls the right
method. Add a room or an item by adding *data*, not by writing new `if`
statements. [`Adventure.java`](Adventure.java) sets up exactly this
structure, with two rooms and working `go`, `look`, `help` and `quit`
commands.

> Keeping one room's details in four separate maps works, but it's a
> little clumsy, a lot like the parallel arrays of Module 6. In
> Module 10 you'll see how a `Room` **class** keeps everything about a
> room together. For now, the maps are exactly right.

## 4. Requirements for every team

Your finished game must have:

**The world**

- **At least 6 rooms**, each with a name, a description, exits and
  items, all created in `buildWorld`.
- **At least 5 items** the player can pick up.
- **At least one locked or blocked route** that needs a specific item
  (a key opens a door, a torch lights a dark room, a battery powers a
  lift…).
- **A win condition** (reach a room carrying certain items, fix the
  robot, escape the building…) and **at least one way to lose**, or a
  move or time limit.

**The commands**

- `go <direction>` (and short forms such as `n`, `s`, `e`, `w`).
- `take <item>` and `drop <item>`.
- `inventory` (or `i`), `look`, `help`, and `quit`.
- At least **one command of your own design** (`use`, `read`, `talk`,
  `open`…).
- Unknown commands, impossible moves ("You can't go west from here."),
  and missing items get a helpful message. **No crashes, ever.** Not on
  empty input, extra spaces, capital letters, or a number where a word
  was expected.

**The code**

- **At least 8 methods you wrote yourselves**, each with a Javadoc
  comment and one clear job, such as `describeRoom`, `move`, `takeItem`,
  `dropItem`, `showInventory`, `showHelp`, `parseCommand`, and `hasWon`.
- A `main` method containing the game loop, and **no `static` variables
  except constants and the world data**. Keep the player's state
  (current room, inventory) in `main`, pass it into methods, and return
  results.
- Use of a **list** (the inventory), a **map** (rooms and exits), and a
  **set** (e.g. visited rooms, or the items needed to win).
- Clean, consistent style: `camelCase` names, `UPPER_SNAKE_CASE`
  constants, comments where the *why* isn't obvious, consistent
  indentation, and no copy-pasted blocks that should be methods.

**The project**

- A `README.md` in your repository explaining what the game is, how to
  run it (`java Adventure.java`), how to win (spoilers below a warning
  line are fine), and **who built which parts**.
- A **map** of your world: a drawing (photo or image file) or an ASCII
  diagram in the README.

## 5. Stretch goals

Pick as many as you like. **Solo/daring students must complete all of
them.**

1. **Scoring:** points for visiting rooms and finding items, shown with
   a `score` command and at the end.
2. **A non-player character** who says different things depending on
   what you're carrying, or gives you an item in exchange for another.
3. **A move limit** with a countdown ("The building's power fails in 12
   moves…") that creates tension and a losing ending.
4. **Smarter parsing:** ignore filler words (`"go to the north"`,
   `"take the battery"`) and accept synonyms (`get` = `take`,
   `walk` = `go`). A `Set<String>` of filler words and a
   `Map<String, String>` of synonyms make this neat.
5. **A `map` command** that prints which rooms the player has visited
   and the exits from the current room.
6. **Random events:** a power cut, a wandering cat, or a random item
   location each game, using `Random`.

## 6. Stages and timeline

| Stage | Focus | Git evidence expected |
|---|---|---|
| Stage 0 | Team formed, repository set up, world designed **on paper first** (rooms, exits, items, puzzle, ending) | First commit: `Adventure.java`, plus your map and plan in the README |
| Stage 1 | All rooms in `buildWorld`; `go`, `look`, `help`, `quit` working | Several small commits; every room reachable and described |
| Stage 2 | Items: `take`, `drop`, `inventory`; items appear in room descriptions | Commits adding each command as its own method |
| Stage 3 | The locked route, your own command, and the win/lose conditions | A commit where the game can be won from start to finish |
| Stage 4 | Playtesting and polish: try to break your game (empty input, nonsense, capitals), fix what you find, stretch goals | Commits named after the specific bugs you fixed |
| Final | README finished, every method has a Javadoc comment, last push before the deadline | Your final commit *is* your submission |

Commit at the end of every working session, even if it isn't finished,
as long as it **compiles**. Never push code that doesn't compile: your
teammates will pull it and their game will stop working too. A history
with one giant commit the night before the deadline is a red flag, not
a shortcut.

**Playtest each other's games.** Before the final stage, swap with
another team. Play their game without reading its code, and write down
everything that confused you or broke. Then fix what they found in yours.

## 7. How it's assessed

- **It works.** It compiles, it can be won, and it handles bad input
  without crashing. Every requirement in §4 is met.
- **It uses Part 1 well.** Maps, lists, sets, loops, `switch` and
  methods used because they fit the problem, not bolted on to tick a
  box. Be ready to explain any line of your code.
- **It's well organised.** Short methods with clear names and Javadoc
  comments, no repeated blocks, no unnecessary `static` variables.
- **It's fun and well written.** Descriptions that create a sense of
  place, a puzzle that makes sense, an ending worth reaching.
- **Git history.** Regular, meaningful commits from **every** team
  member, under their own name.
- **Documentation.** A README a stranger could use to run and play your
  game without asking you anything.

## Try it yourself

1. Confirm your team (or solo status) with your instructor.
2. Create and share the team repository, and push `Adventure.java`.
3. Design your world on paper: map, items, puzzle, ending.
4. Work through the stages in §6, committing and pushing regularly.
5. Submit by pushing your final commit before the deadline. There is no
   separate submission step.

You've built a real game from nothing but text and logic. Put the
repository link on your CV, and get ready for Part 2, starting with
**[Module 10: Classes & Objects](../10-classes-and-objects/README.md)**,
where you'll learn to design your own types, starting with a `Room`
class that would have made this game a lot tidier.
