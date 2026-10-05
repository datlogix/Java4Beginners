# Module 18: GUIs with Swing

## Hook: a game in a window

Run [`examples/Ex00CatchTheDot.java`](examples/Ex00CatchTheDot.java):

```bash
java Ex00CatchTheDot.java
```

A window opens with a golden dot on a dark background, and a clock
counting down from 20. Click the dot. It jumps somewhere else, a little
smaller, and it starts moving faster. How many can you catch before
time runs out? When the clock hits zero, a dialog asks whether you want
to play again.

Look at how differently this program works. Every program since Module
1 ran from the top of `main` to the bottom, stopping to ask questions in
a fixed order. This one sets things up and then **waits**: it reacts to
a mouse click whenever one happens, to a timer firing every 900
milliseconds, and to another firing every second. This is
**event-driven programming**, and it's how every app on your phone and
laptop works. This module shows you how to build windowed programs with
**Swing**, the GUI toolkit that comes with every JDK.

## Windows, components and containers

A Swing program is built from **components**, the visible pieces, put
inside **containers**:

| Class | Is… |
|---|---|
| `JFrame` | A **window**, with a title bar and a close button |
| `JPanel` | An invisible **container** that groups components and arranges them |
| `JLabel` | A piece of text (or an image) |
| `JButton` | A button that can be clicked |
| `JTextField` / `JTextArea` | One line / several lines of text the user can type |
| `JCheckBox`, `JRadioButton`, `JComboBox`, `JSlider`, `JList` | Choices |

```java
JPanel panel = new JPanel();
panel.add(new JLabel("Akwaaba! This is a Swing window."));

JFrame window = new JFrame("My first window");
window.add(panel);
window.setSize(480, 120);
window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // closing the window ends the program
window.setVisible(true);                                 // windows start hidden!
```

Three rules you'll follow in every Swing program:

1. **Start Swing on its own thread.** Swing isn't safe to use from more
   than one thread at once, so it has a special one, the **event
   dispatch thread**. Wrap the code that builds your window in
   `SwingUtilities.invokeLater(() -> { ... });`, and Swing runs it
   there.
2. **Call `setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)`**, or the
   program keeps running invisibly after the window is closed.
3. **Call `setVisible(true)` last**, after adding everything.

`window.pack()` (instead of `setSize`) sizes the window to fit its
contents exactly. See [`examples/Ex01FirstWindow.java`](examples/Ex01FirstWindow.java).

## Events and listeners

A program finds out what the user did through **events**. You attach a
**listener** to a component, and Swing calls it when the event happens:

```java
JButton plus = new JButton("+");
plus.addActionListener(e -> {
    count++;
    display.setText(String.valueOf(count));
});
```

`ActionListener` is a functional interface (Module 12), so a lambda is
the neat way to write one. Clicking the button **doesn't** run the
lambda immediately when this line runs: it **registers** it, and Swing
runs it each time the button is clicked, possibly hours later. The `e`
is an `ActionEvent` object describing what happened. Pressing Enter in a
`JTextField` also fires an action event, so the same listener can serve
a button and a text field. See
[`examples/Ex02ButtonsAndEvents.java`](examples/Ex02ButtonsAndEvents.java).

## Layout managers

You don't usually place components at exact pixel positions: windows
get resized, and fonts differ between computers. Instead, each
container has a **layout manager** that decides where its components go
and how they stretch:

| Layout | Arranges components… | Good for |
|---|---|---|
| `FlowLayout` (a `JPanel`'s default) | in a row, left to right, wrapping like words | rows of buttons |
| `BorderLayout` (a `JFrame`'s default) | in five regions: `NORTH`, `SOUTH`, `EAST`, `WEST` and `CENTER`, which gets all the spare space | the overall shape of a window |
| `GridLayout(rows, cols)` | in a grid of **equal** cells | keypads, forms |
| `BoxLayout` | in one column or one row | stacks of controls |

```java
JPanel main = new JPanel(new BorderLayout());
main.add(title, BorderLayout.NORTH);
main.add(keypad, BorderLayout.CENTER);
main.add(toolbar, BorderLayout.SOUTH);
```

Real windows **nest** panels: a `BorderLayout` for the overall shape,
with a `FlowLayout` panel of buttons in the south and a `GridLayout`
keypad in the centre. Run [`examples/Ex03Layouts.java`](examples/Ex03Layouts.java)
and resize the window to see how each layout behaves. Borders such as
`BorderFactory.createEmptyBorder(10, 10, 10, 10)` (padding) and
`createTitledBorder("...")` make the groups clear.

## Text input

`textField.getText()` gives what the user typed, as a `String`, and
`label.setText(...)` changes what a label shows. Everything you know
about converting and validating input (Modules 3 and 13) applies:

```java
try {
    double c = Double.parseDouble(celsius.getText().trim());
    result.setText(String.format("%.1f C = %.1f F", c, c * 9 / 5 + 32));
} catch (NumberFormatException e) {
    result.setForeground(Color.RED);
    result.setText("'" + celsius.getText() + "' isn't a number");
}
```

In a GUI, an uncaught exception doesn't end the program. It prints a
stack trace in the terminal, where the user can't see it, and the
button just seems to do nothing. So **always** catch bad input, and say
what went wrong *in the window*. Put a `JTextArea` inside a
`JScrollPane` to give it scroll bars. See
[`examples/Ex04TextInput.java`](examples/Ex04TextInput.java).

## Choices: check boxes, radio buttons, combo boxes, sliders

| Component | Read it with |
|---|---|
| `JCheckBox` | `isSelected()` |
| `JRadioButton`, added to a `ButtonGroup` so only one can be chosen | `isSelected()` |
| `JComboBox<T>`, a drop-down list | `getSelectedItem()` (and it shows each item's `toString()`) |
| `JSlider(min, max, start)` | `getValue()` |

[`examples/Ex05ChoiceComponents.java`](examples/Ex05ChoiceComponents.java)
builds a lab-booking form from all four, laid out with a `GridLayout`.

## Dialogs and menus

`JOptionPane` has ready-made dialogs, so you don't need to build a
window for every message or question:

```java
JOptionPane.showMessageDialog(window, "Saved!");
String name = JOptionPane.showInputDialog(window, "What's your name?");   // null if cancelled
int answer = JOptionPane.showConfirmDialog(window, "Delete this task?");  // YES_OPTION, NO_OPTION...
```

See [`examples/Ex06Dialogs.java`](examples/Ex06Dialogs.java).
[`examples/Ex07Menus.java`](examples/Ex07Menus.java) builds a small text
editor with a **menu bar** (`JMenuBar`, `JMenu`, `JMenuItem`), keyboard
shortcuts (`setAccelerator`), and `JFileChooser`, the standard Open and
Save dialogs, connected to the file code from Module 14.

## Custom painting, explained at last

Since Module 1 you've drawn pictures by copying a pattern: a class that
`extends JPanel` with a `paintComponent` method. Now every line makes
sense:

```java
class WaveGraph extends JPanel {                 // a JPanel that draws itself (Module 11)
    @Override
    protected void paintComponent(Graphics g) {  // override JPanel's version
        super.paintComponent(g);                 // let JPanel paint the background first
        Graphics2D pen = (Graphics2D) g;         // the richer Graphics2D (a cast, Module 11)
        ... draw, using getWidth() and getHeight() for the CURRENT size ...
    }
}
```

**Swing** decides when to call `paintComponent`: when the window first
appears, when it's resized or uncovered, or when you ask. You **never**
call it yourself. When your data changes, call **`repaint()`**, which
asks Swing to repaint the component soon. Everything you draw must come
from the object's fields, because Swing may ask you to redraw at any
moment. [`examples/Ex08CustomPainting.java`](examples/Ex08CustomPainting.java)
draws a sine wave whose frequency follows a slider.

## Mouse events and animation

To react to the mouse, add a **`MouseListener`** (presses, releases,
clicks) or a **`MouseMotionListener`** (moving and dragging). The
`MouseAdapter` class provides empty versions of every method, so you
only override the ones you need:

```java
addMouseListener(new MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
        ... e.getX(), e.getY() ...
    }
});
```

`new MouseAdapter() { ... }` creates an **anonymous class**: a subclass
with no name, written right where it's needed. (Lambdas don't work here,
because `MouseListener` has several methods.) See the sketch pad in
[`examples/Ex09MouseEvents.java`](examples/Ex09MouseEvents.java).

For anything that happens **over time**, use a **`javax.swing.Timer`**.
It fires an action event every so many milliseconds, on Swing's own
thread:

```java
new Timer(16, e -> {          // about 60 times a second
    moveTheBalls();
    repaint();
}).start();
```

**Never** use `Thread.sleep` (or a long loop) inside a Swing listener.
While a listener runs, Swing can't do anything else: no painting, no
clicks. The window freezes. See the bouncing balls in
[`examples/Ex10Animation.java`](examples/Ex10Animation.java). Now re-read
the hook: two `Timer`s (one for the dot, one for the clock), a
`MouseAdapter`, and `paintComponent`.

## Keep the model separate from the view

A common beginner design puts everything inside the window code: the
data, the rules, and the buttons, tangled together. It works, until you
want to test the rules, save the data, or build a different interface.
Instead, split the program in two:

- The **model**: plain classes holding the data and enforcing the rules.
  No Swing at all. It can be unit tested (Module 17), and reused.
- The **view**: the window, which *displays* the model and *passes on*
  the user's actions to it.

[`examples/Ex11ToDoList.java`](examples/Ex11ToDoList.java) does this
with a to-do list: a `TaskList` class that knows nothing about windows,
and a GUI around it that saves to a file after every change. Exercise
2's calculator does it too, which is why you can test it with
`java Exercise2.java test`, without opening a window at all.

## Common beginner mistakes

- **Forgetting `setVisible(true)`**, or calling it before adding the
  components.
- **Forgetting `EXIT_ON_CLOSE`**, so the program keeps running after the
  window closes.
- **Calling `paintComponent` yourself.** Call `repaint()`.
- **Changing the data and not calling `repaint()`**, so the picture
  doesn't update.
- **Using `Thread.sleep` in a listener**, which freezes the window. Use a
  `javax.swing.Timer`.
- **Letting exceptions escape from a listener.** They vanish into the
  terminal. Catch them and show a message.
- **`setSize` on a component inside a layout.** Layouts ignore it. Use
  `setPreferredSize`, or let the layout decide.
- **Using `java.util.Timer` instead of `javax.swing.Timer`.** Only the
  Swing one runs on the event thread. Import the right one.
- **Putting all the program's logic in the listeners.** Keep a separate
  model class.

## Try it yourself

1. Run every file in [`examples/`](examples/). In the hook, add a second
   dot worth more points that appears only now and then.
2. Complete [`exercises/Exercise1.java`](exercises/Exercise1.java) (a
   unit converter) and [`exercises/Exercise2.java`](exercises/Exercise2.java)
   (a calculator whose model you can test without a window).
3. Build the [module project](project/README.md) in the track of your
   choice: a study planner, a resistor colour code calculator, or a
   vitals dashboard.
4. Commit and push your work:

   ```bash
   git add .
   git commit -m "Complete Module 18: GUIs with Swing"
   git push
   ```

Next: **[Module 19: Capstone 2](../19-capstone-project-2/README.md)**.
