// Example 1: the smallest useful window.
// Run it with:  java Ex01FirstWindow.java

import java.awt.*;
import javax.swing.*;

public class Ex01FirstWindow {

    static JPanel buildUi() {
        JPanel panel = new JPanel();                   // a container for components
        JLabel label = new JLabel("Akwaaba! This is a Swing window.");
        label.setFont(new Font("SansSerif", Font.PLAIN, 22));
        panel.add(label);                              // put the label in the panel
        return panel;
    }

    public static void main(String[] args) {
        // Swing must be used from its own thread, the "event dispatch thread".
        // invokeLater runs the code there. Always start a Swing program this way.
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame("My first window");   // the title bar text
            window.add(buildUi());
            window.setSize(480, 120);
            window.setLocationRelativeTo(null);              // centre it on the screen
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // closing it ends the program
            window.setVisible(true);                         // windows start hidden!
        });
    }
}
