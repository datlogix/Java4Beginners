// Example 4: reading what the user types into a text field.
// Run it with:  java Ex04TextInput.java

import java.awt.*;
import javax.swing.*;

public class Ex04TextInput {

    static JPanel buildUi() {
        JTextField celsius = new JTextField("37", 8);       // 8 columns wide
        JButton convert = new JButton("Convert");
        JLabel result = new JLabel(" ");
        result.setFont(new Font("SansSerif", Font.BOLD, 18));

        Runnable doConvert = () -> {
            try {
                double c = Double.parseDouble(celsius.getText().trim());   // getText: what's typed
                result.setForeground(new Color(0, 110, 0));
                result.setText(String.format("%.1f C = %.1f F", c, c * 9 / 5 + 32));
            } catch (NumberFormatException e) {
                result.setForeground(Color.RED);
                result.setText("'" + celsius.getText() + "' isn't a number");
            }
        };
        convert.addActionListener(e -> doConvert.run());
        celsius.addActionListener(e -> doConvert.run());     // pressing Enter in the field

        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row.add(new JLabel("Celsius:"));
        row.add(celsius);
        row.add(convert);

        JTextArea notes = new JTextArea(5, 30);              // several lines of text
        notes.setText("A JTextArea holds several lines.\nType notes here.");
        notes.setLineWrap(true);

        JPanel panel = new JPanel(new BorderLayout(6, 6));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(row, BorderLayout.NORTH);
        panel.add(result, BorderLayout.CENTER);
        panel.add(new JScrollPane(notes), BorderLayout.SOUTH);   // scroll bars when it's full
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("Temperature converter");
            window.add(buildUi());
            window.pack();
            window.setLocationRelativeTo(null);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
        });
    }
}
