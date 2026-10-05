// Example 2: buttons and events. Event-driven programming: instead of the
// program asking questions in order, it WAITS, and responds to whatever the
// user does.
// Run it with:  java Ex02ButtonsAndEvents.java

import java.awt.*;
import javax.swing.*;

public class Ex02ButtonsAndEvents {

    private static int count = 0;

    static JPanel buildUi() {
        JLabel display = new JLabel("0", SwingConstants.CENTER);
        display.setFont(new Font("SansSerif", Font.BOLD, 48));
        display.setPreferredSize(new Dimension(160, 70));

        JButton minus = new JButton("-");
        JButton plus = new JButton("+");
        JButton reset = new JButton("Reset");

        // An ActionListener runs when the button is clicked. A lambda is the easy way to write one.
        plus.addActionListener(e -> {
            count++;
            display.setText(String.valueOf(count));
        });
        minus.addActionListener(e -> {
            count--;
            display.setText(String.valueOf(count));
        });
        reset.addActionListener(e -> {
            count = 0;
            display.setText("0");
            System.out.println("Reset clicked (events can print to the terminal too)");
        });

        JPanel panel = new JPanel();
        panel.add(minus);
        panel.add(display);
        panel.add(plus);
        panel.add(reset);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("Clicker");
            window.add(buildUi());
            window.pack();                     // size the window to fit its contents
            window.setLocationRelativeTo(null);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
        });
    }
}
