// Example 6: ready-made dialog boxes with JOptionPane.
// Run it with:  java Ex06Dialogs.java

import javax.swing.*;

public class Ex06Dialogs {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // A message, with an OK button
            JOptionPane.showMessageDialog(null, "Welcome to the dialog tour!");

            // A question: gives back the text typed, or null if Cancel was pressed
            String name = JOptionPane.showInputDialog(null, "What's your name?");
            if (name == null || name.isBlank()) {
                name = "friend";
            }

            // Yes / No / Cancel
            int answer = JOptionPane.showConfirmDialog(null, "Do you like Java, " + name + "?");
            String reply = switch (answer) {
                case JOptionPane.YES_OPTION -> "Excellent!";
                case JOptionPane.NO_OPTION -> "You will by Module 19.";
                default -> "Fair enough.";
            };

            // Your own buttons
            String[] choices = {"Highlife", "Afrobeats", "Gospel"};
            int pick = JOptionPane.showOptionDialog(null, "Favourite music?", "Music",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
            String music = pick >= 0 ? choices[pick] : "silence";

            // Different icons: INFORMATION, WARNING, ERROR, QUESTION, PLAIN
            JOptionPane.showMessageDialog(null, reply + "\nEnjoy some " + music + ".", "Result",
                    JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, "This is what an error looks like.", "Oops",
                    JOptionPane.ERROR_MESSAGE);
        });
    }
}
