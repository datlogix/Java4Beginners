// Example 3: layout managers decide where components go, and how they
// stretch when the window is resized. Resize this window and watch.
// Run it with:  java Ex03Layouts.java

import java.awt.*;
import javax.swing.*;

public class Ex03Layouts {

    static JPanel buildUi() {
        // BorderLayout: five regions. CENTER gets all the spare space.
        JPanel main = new JPanel(new BorderLayout(8, 8));
        main.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JLabel title = new JLabel("BorderLayout: NORTH", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        main.add(title, BorderLayout.NORTH);

        // FlowLayout (a JPanel's default): left to right, wrapping like words
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for (String name : new String[]{"New", "Open", "Save", "Print"}) {
            toolbar.add(new JButton(name));
        }
        toolbar.setBorder(BorderFactory.createTitledBorder("FlowLayout (SOUTH)"));
        main.add(toolbar, BorderLayout.SOUTH);

        // GridLayout: a grid of equal-sized cells, filled row by row
        JPanel keypad = new JPanel(new GridLayout(4, 3, 4, 4));
        for (String key : new String[]{"7", "8", "9", "4", "5", "6", "1", "2", "3", "*", "0", "#"}) {
            keypad.add(new JButton(key));
        }
        keypad.setBorder(BorderFactory.createTitledBorder("GridLayout (CENTER)"));
        main.add(keypad, BorderLayout.CENTER);

        // BoxLayout: one column (or row), each component at its natural size
        JPanel side = new JPanel();
        side.setLayout(new BoxLayout(side, BoxLayout.Y_AXIS));
        side.setBorder(BorderFactory.createTitledBorder("Box (EAST)"));
        for (String s : new String[]{"Red", "Green", "Blue"}) {
            side.add(new JCheckBox(s));
        }
        main.add(side, BorderLayout.EAST);

        main.add(new JLabel("WEST"), BorderLayout.WEST);
        return main;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("Layouts");
            window.add(buildUi());
            window.setSize(560, 420);
            window.setLocationRelativeTo(null);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
        });
    }
}
